package cn.im.file;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

public class FileUtils extends org.apache.commons.io.FileUtils {
	
//	writeLines
	
	/**
	 * @Description: 逐行读取文本文件中内容
	 * @param filePath 文件路径
	 * @return  List<String> 文本内容
	 * @throws IOException 
	 */
	public static List<String> readFileByLine(String filePath) throws IOException{
		List<String> result = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		for (String line = br.readLine(); line != null; line = br.readLine()) {
            System.out.println(line);  
            result.add(line);
		}
		br.close();
		return result;
	} 
	
	/**
	 * 根据文件路径、文件名称创建文件
	 * @param filepath 文件路径地址
	 * @param filename 文件名称
	 * @return 文件对象
	 */
	public static File createFile(String filepath,String filename){
		File file = new File(filepath);
		if(!file.isDirectory()){
			file.mkdir();
		}
		
		File f = new File(filepath+File.separator+filename);
		if(!f.isFile()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return f;
			
	}
	
	/**
	 * @Description: 以字符为单位读取文件内容
	 * @param filePath 文件路径
	 * @return  char[]	文本内容
	 */
	public static char[] readFileByChar(String filePath){
		File file = new File(filePath);
		char[] result = new char[(int) file.length()];
		// FileReader:用来读取字符文件的便捷类
		try {
			FileReader reader = new FileReader(file);
			reader.read(result);
			reader.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	 /**
	  * @Description: 向文件中追加写入内容 /还可用FileUtils写入文件
	  * @param filePath 文件路径+名称
	  * @param content 写入内容
	  * @return  void
	  */
   public static void writeToFile(String filePath, String content) {
       try {
           //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
           FileWriter writer = new FileWriter(filePath, true);
           writer.write(content+"\r\n");
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
	
   /**
    * @Description: 复制文件
    * @param infile 读取的文件
    * @param outfile 写入的文件
    * @return  void
    */
   public static void CopyFile(String infile,String outfile) throws Exception{  
           // 获取源文件和目标文件的输入输出流  
           FileInputStream fin = new FileInputStream(infile);  
           FileOutputStream fout = new FileOutputStream(outfile);  
           // 获取输入输出通道  
           FileChannel fcin = fin.getChannel();  
           FileChannel fcout = fout.getChannel();  
           // 创建缓冲区  
           ByteBuffer buffer = ByteBuffer.allocate(1024);  
           while (true) {  
               // clear方法重设缓冲区，使它可以接受读入的数据  
               buffer.clear();  
               // 从输入通道中将数据读到缓冲区  
               int r = fcin.read(buffer);  
               // read方法返回读取的字节数，可能为零，如果该通道已到达流的末尾，则返回-1  
               if (r == -1) {  
                   break;  
               }  
               // flip方法让缓冲区可以将新读入的数据写入另一个通道  
               buffer.flip();  
               // 从输出通道中将数据写入缓冲区  
               fcout.write(buffer);  
           }  
   } 
   
	public static List<String> readBytesByLine(byte[] bytes){
		List<String> result = new ArrayList<String>();
		String line;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
			while((line = br.readLine()) != null){
				result.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 根据文件名获取文件后缀
	 * @return
	 */
	public static String getSuffix(String fileName){
		return StringUtils.isNotBlank(fileName)? fileName.substring(fileName.lastIndexOf(".")):"";
	}
	
	/**
	 * 生成新的文件名
	 * @param fileOriginName 源文件名
	 * @return
	 */
	public static String getFileName(String fileOriginName){
		return UUID.randomUUID() + getSuffix(fileOriginName);
	}
	
	
	
}
