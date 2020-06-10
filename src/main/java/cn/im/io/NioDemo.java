package cn.im.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Java Nio
 * @author Administrator
 *
 */
public class NioDemo {

	public static String file_path = "d:\\demo.txt";
	
	public static void main(String[] args) {
		try {
			System.out.println(readFile(file_path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用channel，将数据从文件中读写至缓冲区
	 * @param file_path
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String file_path) throws Exception{		
		StringBuffer content = new StringBuffer();
		RandomAccessFile file = new RandomAccessFile(file_path,"rw");
		//获取通道
		FileChannel channel = file.getChannel();
		//创建缓冲区，分配48个字节
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		//读取缓冲区中的内容
		int byteRead = channel.read(buffer);
		while (byteRead != -1) {
			buffer.flip(); //缓冲区读写模式切换
			while (buffer.hasRemaining()) {				
				char temp = (char)buffer.get();
				System.out.print(temp);
				content.append(temp);
			}
			buffer.clear(); //清空缓冲区
//			buffer.compact(); //清除已读数据，并将未读数据移动至起始处
			byteRead = channel.read(buffer); //管道中数据读入缓冲区
		}
		file.close();
		return content.toString();
	}
}
