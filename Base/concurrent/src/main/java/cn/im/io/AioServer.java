package cn.im.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1、初始化一个AsynchronousServerSocketChannel对象，并开始监听
 * 2、通过accept方法注册一个“完成处理器”的接受连接回调，即CompletionHandler，用于在接受到连接后的相关操作。
 * 3、当客户端连接过来后，由系统来接受，并创建好AsynchronousSocketChannel对象，然后触发该回调，并把该对象传进该回调，该回调会在Worker线程中执行。
 * 4、在接受连接回调里，再次使用accept方法注册一次相同的完成处理器对象，用于让系统接受下一个连接。就是这种注册只能使用一次，所以要不停的连续注册，人家就是这样设计的。
 * 5、在接受连接回调里，使用AsynchronousSocketChannel对象的read方法注册另一个接受数据回调，用于在接受到数据后的相关操作。
 * 6、当客户端数据过来后，由系统接受，并放入指定好的ByteBuffer中，然后触发该回调，并把本次接受到的数据字节数传入该回调，该回调会在Worker线程中执行。
 * 7、在接受数据回调里，如果数据没有接受完，需要再次使用read方法把同一个对象注册一次，用于让系统接受下一次数据。这和上面的套路是一样的。
 * 8、客户端的数据可能是分多次传到服务器端的，所以接受数据回调会被执行多次，直到数据接受完为止。多次接受到的数据合起来才是完整的数据，这个一定要处理好。
 * 9、关于ByteBuffer，要么足够的大，能够装得下完整的客户端数据，这样多次接受的数据直接往里追加即可。要么每次把ByteBuffer中的数据移到别的地方存储起来，然后清空ByteBuffer，用于让系统往里装入下一次接受的数据。
 */
public class AioServer {
    static int clientCount = 0;
    static AtomicInteger counter = new AtomicInteger(0);
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


    public static void main(String[] args) {
        try {
            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open();
            assc.bind(new InetSocketAddress("localhost", 8080));
            //非阻塞方法，其实就是注册了个回调，而且只能接受一个连接
            assc.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel asc, Object attachment) {
                    //再次注册，接受下一个连接
                    assc.accept(null, this);
                    try {
                        InetSocketAddress rsa = (InetSocketAddress)asc.getRemoteAddress();
                        System.out.println(time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + (++clientCount));
                    } catch (Exception e) {
                    }
                    readFromChannelAsync(asc);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {

                }
            });
            //不让主线程退出
            synchronized (AioServer.class) {
                AioServer.class.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void readFromChannelAsync(AsynchronousSocketChannel asc) {
        //会把数据读入到该buffer之后，再触发工作线程来执行回调
        ByteBuffer bb = ByteBuffer.allocate(1024*1024*1 + 1);
        long begin = System.currentTimeMillis();
        //非阻塞方法，其实就是注册了个回调，而且只能接受一次读取
        asc.read(bb, null, new CompletionHandler<Integer, Object>() {
            //从该连接上一共读到的字节数
            int total = 0;
            /**
             * @param count 表示本次读取到的字节数，-1表示数据已读完
             */
            @Override
            public void completed(Integer count, Object attachment) {
                counter.incrementAndGet();
                if (count > -1) {
                    total += count;
                }
                int size = bb.position();
                System.out.println(time() + "->count=" + count + ",total=" + total + "bs,buffer=" + size + "bs->" + Thread.currentThread().getId() + ":" + counter.get());
                if (count > -1) {//数据还没有读完
                    //再次注册回调，接受下一次读取
                    asc.read(bb, null, this);
                } else {//数据已读完
                    try {
                        asc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                counter.decrementAndGet();
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });
        long end = System.currentTimeMillis();
        System.out.println(time() + "->exe read req,use=" + (end -begin) + "ms" + "->" + Thread.currentThread().getId());
    }

    static String time() {
        return sdf.format(new Date());
    }
}
