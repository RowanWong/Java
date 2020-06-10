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
	 * ʹ��channel�������ݴ��ļ��ж�д��������
	 * @param file_path
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String file_path) throws Exception{		
		StringBuffer content = new StringBuffer();
		RandomAccessFile file = new RandomAccessFile(file_path,"rw");
		//��ȡͨ��
		FileChannel channel = file.getChannel();
		//����������������48���ֽ�
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		//��ȡ�������е�����
		int byteRead = channel.read(buffer);
		while (byteRead != -1) {
			buffer.flip(); //��������дģʽ�л�
			while (buffer.hasRemaining()) {				
				char temp = (char)buffer.get();
				System.out.print(temp);
				content.append(temp);
			}
			buffer.clear(); //��ջ�����
//			buffer.compact(); //����Ѷ����ݣ�����δ�������ƶ�����ʼ��
			byteRead = channel.read(buffer); //�ܵ������ݶ��뻺����
		}
		file.close();
		return content.toString();
	}
}
