package cn.im.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardingServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		resp.getWriter().write("Controller forward to Servlet.");
	}
}
