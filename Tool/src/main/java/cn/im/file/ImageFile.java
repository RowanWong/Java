package cn.im.file;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;


public class ImageFile {

	/**
	 * 创建文本txt，并将写入数据
	 * @param args
	 */
	private static String path="c:\\a\\";
	private static String filename;
	public static void main(String[] args) {
		
			
			/*f.createNewFile();
			
		FileOutputStream fos = new FileOutputStream(f);
		PrintWriter pw = new PrintWriter(fos);
		pw.write(buf.toString().toCharArray());
		pw.flush(); */
		
		/*
			FileInputStream in = new FileInputStream(f);
			InputStreamReader reader = new InputStreamReader(in);
			BufferedReader bf = new BufferedReader(reader);	*/		
			
			
	}
	
	 /**
     * 通过读取文件并获取其width及height的方式，来判断判断当前文件是否图片，这是一种非常简单的方式。
     * @param imageFile
     * @return
     */
    public static boolean isImage(File imageFile) {
        if (!imageFile.exists()) {
            return false;
        }
        Image img = null;
        try {
            img = ImageIO.read(imageFile);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            img = null;
        }
    }
   
    
    /**
	 * 上传文件
	 * @param file
	 * @param request
	 * @return
	 */
//	@RequestMapping(value={"/upload"},method= RequestMethod.POST)
//	@ResponseBody
//	 public Map<String,String> uploadFile(MultipartFile file,HttpServletRequest request){
//		Map<String,String> res = new HashMap<String,String>();
//		String type = request.getParameter("type");
//		String code = "-1";
//		String msg = "";
//		System.out.println("type="+type);
//		if(file.isEmpty()){  
//			logger.info("file is null");
//			msg = "文件内容为空";
//        }else{
//        	//创建本地文件：目录+文件名
//        	String fileName = file.getOriginalFilename();
//    		File path = new File("${local_path}") ;
//    		if(!path.exists()) 
//    			path.mkdirs();
//    		long current = System.currentTimeMillis();
//    		String reName = "icso"+current+fileName.substring(fileName.lastIndexOf("."),fileName.length());
//    		File loc_file = new File(path.getAbsolutePath()+File.separator+reName);
//    		try {
//    			//复制文件到本地
//    			file.transferTo(loc_file);  
//    			if("imgType".equals(type) ){	//图片
//    				if( !isImage(loc_file)){
//        				loc_file.delete();
//        				msg = "请上传图片类型文件！";
//        			}else{
//        				code = "0";
//        				msg = loc_file.getAbsolutePath();
//        				res.put("fileName",fileName);
//        				res.put("reName",reName);
//        			}
//    			}else{							//文档文件
//    				//TODO 判断文件格式
//    				code = "0";
//    				msg = loc_file.getAbsolutePath();
//    				res.put("fileName",fileName);
//    				res.put("reName",reName);
//    			}
//    		} catch(Exception e){
//    			msg = "上传失败，请确认上传文件是否符合模板规范";
//    		};
//    		
//        }
//		
//		System.out.println(msg);
//		res.put("code", code);
//		res.put("msg", msg);
//		logger.info("uploadFile..."+res.toString());
//		return res;
//	}
	

}
