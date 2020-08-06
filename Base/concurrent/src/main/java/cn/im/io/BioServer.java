package cn.im.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


public class BioServer {

    static AtomicInteger counter = new AtomicInteger(0);
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket();
            ss.bind(new InetSocketAddress("localhost",8080));
            while (true){
                Socket s = ss.accept();
                processWithNewThread(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processWithNewThread(Socket s){
        Runnable run = () -> {
            InetSocketAddress rsa = (InetSocketAddress) s.getRemoteSocketAddress();
            //输出>>> 时间->IP:Port->线程Id:当前线程数
            System.out.println(time() + "->" + rsa.getHostName() + ":" + rsa.getPort() + " -> " + Thread.currentThread().getId() + ":" + counter.incrementAndGet());
            try {
                String result = readBytes(s.getInputStream());
                //输出>>>  时间->等待数据的时间,读取数据的时间,总共读取的字节数->线程Id:当前线程数
                System.out.println(time() + "->" + result + "->" + Thread.currentThread().getId() + ":" + counter.incrementAndGet());
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        new Thread(run).start();
    }

    /**
     * 读取数据流信息，返回读取时间及相关信息
     * @param is
     * @return
     * @throws Exception
     */
    static String readBytes(InputStream is) throws Exception {
        long start = 0;
        int total = 0;
        int count = 0;
        byte[] bytes = new byte[1024];

        //开始读取数据时间
        long begin = System.currentTimeMillis();
        while ((count=is.read(bytes)) > 1){
            if(start < 1){
                //第一次读取到数据的时间
                start = System.currentTimeMillis();
            }
            total += count;
        }
        // 读完数据的时间
        long end = System.currentTimeMillis();

        return "wait="+(start-begin)+"ms, read="+(end-start)+"ms, total="+total+"bs";
    }

    /**
     * 输出当前时间
     * @return
     */
    public static String time(){
        return sdf.format(new Date());
    }
}
