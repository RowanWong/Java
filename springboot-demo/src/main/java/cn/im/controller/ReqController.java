package cn.im.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.im.domain.User;
import cn.im.util.JacksonUtil;

/**
 * 常见get、post请求解析
 * @author Kevin
 *
 */
@RestController
@RequestMapping("/req")
public class ReqController {

	/**
	 * 使用方法的形参，读取get请求参数
	 * http://127.0.0.1:8081/req/get1?n=kevin&p=123123
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value="get1",method=RequestMethod.GET)
	public String get1(@RequestParam(value="id",required = false,defaultValue = "1") String name,@RequestParam(value="p") String pwd) {
		String result = "fail.";
		System.out.println("name: "+name);
		System.out.println("pwd: "+pwd);
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("pwd", pwd);
		result = JacksonUtil.toJson(map);
		return  result;
	}
	
	/**
	 * 使用方法的形参，读取get请求参数
	 * http://127.0.0.1:8081/req/get2?name=kevin&pwd=123123
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value="get2",method=RequestMethod.GET)
	public String get2(String name,String pwd) {
		String result = "fail.";
		System.out.println("name: "+name);
		System.out.println("pwd: "+pwd);
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("pwd", pwd);
		result = JacksonUtil.toJson(map);
		return  result;
	}
	
	/**
	 * 通过bean封装接收get请求参数
	 * http://127.0.0.1:8081/req/get3?userName=kevin&password=123123
	 * @param user
	 * @return
	 */
	@RequestMapping(value="get3",method=RequestMethod.GET)
	public String get3(User user) {
		String result = "fail.";
		System.out.println("name: "+user.getUserName());
		System.out.println("pwd: "+user.getPassword());
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", user.getUserName());
		map.put("pwd", user.getPassword());
		result = JacksonUtil.toJson(map);
		return  result;
	}
	
	/**
	 * 通过@PathVariable注解读取路径中的参数
	 * http://127.0.0.1:8081/req/get4/xxxx?userName=kevin&password=123123
	 * @param user
	 * @param i
	 * @return
	 */
	@RequestMapping(value="get4/{id}",method=RequestMethod.GET)
	public String get4(User user,@PathVariable("id") String i) {
		String result = "fail.";
		System.out.println("name: "+user.getUserName());
		System.out.println("pwd: "+user.getPassword());
		Map<String,String> map = new HashMap<String, String>();
		map.put("name", user.getUserName());
		map.put("pwd", user.getPassword());
		map.put("id", i);
		result = JacksonUtil.toJson(map);
		return  result;
	}
	
	
	/**
     * 用注解@Requestbody绑定请求参数到方法入参,注意：Content-Type要设置为application/json
     * curl 'http://127.0.0.1:8081/req/body' -X POST -H 'content-type:application/json;charset:UTF-8' -d '{"userNmae": "yihui", "password": "111"}'
      * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/body",method=RequestMethod.POST)
    public String body(@RequestBody User user) {
        System.out.println("username is:"+user.getUserName());
        System.out.println("password is:"+user.getPassword());
        
        Map<String,String> map = new HashMap<String, String>();
		map.put("name", user.getUserName());
		map.put("pwd", user.getPassword());
        return JacksonUtil.toJson(map);
    }
	
    
	/**
     *  使用@ModelAttribute注解获取POST请求的FORM表单数据
      * @param user
     * @return
     */
    @RequestMapping(value="/att",method=RequestMethod.POST)
    public String post1(@ModelAttribute("user") User user) {
        System.out.println("username is:"+user.getUserName());
        System.out.println("password is:"+user.getPassword());
        Map<String,String> map = new HashMap<String, String>();
        map.put("name", user.getUserName());
		map.put("pwd", user.getPassword());
        return  JacksonUtil.toJson(map);
    }
	
	
    //===========================多媒体消息===================================
	
    
    private String getMsg(MultipartFile file) {
        String ans = null;
        try {
            ans = file.getName() + " = " + new String(file.getBytes(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        System.out.println(ans);
        return ans;
    }

    /**
     * 文件上传
     *
     * curl 'http://127.0.0.1:8081/req/file' -X POST -F 'file=@hello.txt'
     *
     * @param file
     * @return
     */
    @PostMapping(path = "file")
    public String fileParam(@RequestParam("file") MultipartFile file) {
        return getMsg(file);
    }

    @PostMapping(path = "file2")
    public String fileParam2(MultipartHttpServletRequest request) {
        MultipartFile file = request.getFile("file");
        return getMsg(file);
    }
}
