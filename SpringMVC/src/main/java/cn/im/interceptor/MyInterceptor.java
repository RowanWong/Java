package cn.im.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * @author SYSTEM
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
	
	/**
     * 预处理回调方法，在处理器执行前先执行
     * @return true 继续流程，向下执行；false 流程中断，使用resp响应
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        logger.info("preHandle");
        return true;
    }
    
    /**
     * 后处理回调方法，在处理器执行后再执行
     */
    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp,Object handler, ModelAndView mav) throws Exception {
    	logger.info("postHandle");
    }
    
    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，还可以进行一些资源清理，
     * 类似于try-catch-finally中的finally，但仅调用处理器执行链中preHandle返回true的拦截器的afterCompletion
     */
    @Override
    public void afterCompletion(HttpServletRequest req,HttpServletResponse resp, Object handler, Exception ex)
            throws Exception {
    	logger.info("afterCompletion");
    }

    

    


}