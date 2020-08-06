package cn.im.io;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class NioServer {
    static int clientCount = 0;
    static AtomicInteger counter = new AtomicInteger(0);
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");


    public static void main(String[] args) {
        try {
            //1、定义一个选择器，Selector。
            Selector selector = Selector.open();
            //2、定义一个服务器端套接字通道，ServerSocketChannel，并配置为非阻塞的。
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            //3、将套接字通道注册到选择器上，并把感兴趣的操作设置为OP_ACCEPT。
            ssc.register(selector,SelectionKey.OP_ACCEPT);
            ssc.bind(new InetSocketAddress("localhost",8080));
            //4、进入死循环，选择器不时的进行选择
            while (true){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()){
                    SelectionKey key = it.next();
                    it.remove();
                    //5、选择器终于选择出了通道，发现通道是需要Acceptable的。
                    if (key.isAcceptable()) {
                        //6、于是服务器端套接字接受了这个通道，开始处理。
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = null;
                        while ((socketChannel=serverSocketChannel.accept()) != null){
                            //7、把新接受的通道配置为非阻塞的，并把它也注册到了选择器上，该通道感兴趣的操作为OP_READ。
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            InetSocketAddress rsa = (InetSocketAddress) socketChannel.socket().getRemoteSocketAddress();
                            System.out.println(time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + "->" + Thread.currentThread().getId() + ":" + (++clientCount));
                        }
                        //9、选择器终于又选择出了通道，这次发现通道是需要Readable的。
                    }else if(key.isReadable()){
                        //先将“读”从感兴趣操作移出，待把数据从通道中读完后，再把“读”添加到感兴趣操作中
                        //否则，该通道会一直被选出来
                        key.interestOps(key.interestOps() & (~ SelectionKey.OP_READ));
                        //10、把这个通道交给了一个新的工作线程去处理。
                        processWithNewThread((SocketChannel) key.channel(),key);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void processWithNewThread(SocketChannel sc, SelectionKey key) {
        Runnable run = () -> {
            counter.incrementAndGet();
            try {
                String result = readBytes(sc);
                //把“读”加进去
                key.interestOps(key.interestOps() | SelectionKey.OP_READ);
                //输出：  时间->等待数据的时间,读取数据的时间,总共读取的字节数->线程Id:当前线程数
                System.out.println(time() + "->" + result + "->" + Thread.currentThread().getId() + ":" + counter.get());
                sc.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            counter.decrementAndGet();
        };
        new Thread(run).start();
    }

    /**
     * 读取数据流信息，返回读取时间及相关信息
     * @param sc
     * @return
     * @throws Exception
     */
    static String readBytes(SocketChannel sc) throws Exception {
        long start = 0;
        int total = 0;
        int count = 0;
        ByteBuffer bb = ByteBuffer.allocate(1024);
        //开始读数据的时间
        long begin = System.currentTimeMillis();
        while ((count = sc.read(bb)) > -1) {
            if (start < 1) {
                //第一次读到数据的时间
                start = System.currentTimeMillis();
            }
            total += count;
            bb.clear();
        }
        //读完数据的时间
        long end = System.currentTimeMillis();
        return "wait=" + (start - begin) + "ms,read=" + (end - start) + "ms,total=" + total + "bs";
    }

    /**
     * 输出当前时间
     * @return
     */
    public static String time(){
        return sdf.format(new Date());
    }
}
