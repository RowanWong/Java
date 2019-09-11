package cn.im.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//import com.opslab.helper.FileHelper;
//import com.opslab.util.FileUtil;
//import com.opslab.util.FileUtilTest;

public class FileUtilsTest {

	private static String PATH="C:/a";
	private static String FILENAME="1.txt";
	
	public void testWriteLines() {
		List<String> list = new ArrayList<String>();
		list.add("openid\t姓名\t号码");
		list.add("fefsd\t小蓝\t177");
		
		File file = FileUtils.createFile(PATH, FILENAME);
		try {
			FileUtils.writeLines(file, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testIo(){
		File file = new File(PATH+File.separator+FILENAME);
//		System.out.println(FileUtil.createFile(file));
//		System.out.println(FileUtil.write(file, "think diffient."));
//		System.out.println(FileUtil.appendLine(file, "just do it."));
	}
	
//	@Test
	public void testIoWrite(){
		File file = FileUtils.createFile(PATH, FILENAME);
		
		//write to file
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.write("hello\n");
			pw.write("world");
			pw.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//read from file
		try {
			String str = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((str = br.readLine()) !=null){
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
