package cn.im.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class HelloController extends AbstractController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("handle {}","request");
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "be happy.");
		mav.setViewName("hello");
		return mav;
	}
	
	

}
