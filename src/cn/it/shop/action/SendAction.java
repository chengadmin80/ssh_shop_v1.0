package cn.it.shop.action;

import org.springframework.stereotype.Controller;

/**        
 * Title: SendAction.java    
 * Description: 此Action用来完成web-inf中jsp与jsp请求转发功能
 * @author liuweicheng       
 * @created 2016-4-3 上午11:09:50    
 */
@Controller
public class SendAction {
	
	public SendAction(){
		System.out.println("---sendAction---");
	}
	
	public String execute(){
		System.out.println("---send-execute---");
		return "send";
	}
}
