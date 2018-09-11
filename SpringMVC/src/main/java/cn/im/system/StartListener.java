package cn.im.system;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
/**
 * 系统启动时初始化数据
 * IOC容器启动过程中，当所有bean都已处理完成后，会有一个发布事件的动作ContextRefreshedEvent
 * @author SYSTEM
 *
 */
@Service
public class StartListener implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(arg0.getApplicationContext().getParent() == null){
			System.out.println("code to init....");
		}
	}


}
