package cn.im.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.im.domain.User;
import cn.im.queue.UserQueue;

@Controller
@RequestMapping(value="/")
public class MyController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	
	@RequestMapping(value="/index")
	public ModelAndView index(@RequestParam(value="u",required=false,defaultValue="me") String userid, HttpServletRequest req, HttpServletResponse res){
		logger.info("handle index");
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", userid);
		mav.setViewName("hello");
		return mav;
	}
	
	
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	public ModelAndView handle(@PathVariable String id, HttpServletRequest req, HttpServletResponse res){
		logger.info("handle show msg:{}",id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", id);
		mav.setViewName("hello");
		return mav;
	}
	
	@RequestMapping(value="/user/add",method=RequestMethod.GET)
	public String toAddUser(){
		return "add";
	}
	
	@RequestMapping(value="/user/get",method=RequestMethod.GET)
	public ModelAndView getUserInfo(@RequestParam String userid){
		logger.info("getUserInfo...  userid:{}",userid);
		User user = UserQueue.getUser(userid);
		if(userid.equals("001")){
			user.setUserId("001");
			user.setUserName("hrw");
			user.setAge(26);
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", user);
		mav.setViewName("show");
		return mav;
	} 
	
	@RequestMapping(value="/user/save",method=RequestMethod.POST)
	public String saveUser(@ModelAttribute User user,HttpSession session,String gender){
		user.setUserId("002");
		logger.info("gender:"+ gender);
		UserQueue.addUser(user);
		session.setAttribute("user", user);
		return "redirect:/user/get?userid=002";
		
	}
	
	/**
	 * 跳转至文件上传页面
	 * @return
	 */
	@RequestMapping(value="/toupload",method=RequestMethod.GET)
	public String toUpload(){
		return "upload";
	}
	/**
	 * 上传文件方法
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/upload",method=RequestMethod.POST)
    public String upload(HttpServletRequest req) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");       
        String filePath = req.getSession().getServletContext().getRealPath("/")+
               sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.'));
        logger.info("file upload path: {}",filePath);
        File tfile = new File(filePath);
        FileOutputStream fos = new FileOutputStream(tfile);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        
        return "hello";
    }
	
	/**
	 * 异常处理页面
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("msg", ex);
       logger.info("in testExceptionHandler: {}",ex.toString());
        return mv;
    }
}
