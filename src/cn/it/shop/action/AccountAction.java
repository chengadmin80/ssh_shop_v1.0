package cn.it.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Account;



  
    /**        
     * Title: CategoryAction.java    
     * Description: 描述
     * @author liuweicheng       
     * @created 2016-4-2 上午11:21:12    
     * 
     * ModelDriven此接口必须要实现getModel()方法，此方法会把返回的对象，压到栈顶中
     */      
@Controller
@Scope("prototype")
public class AccountAction extends BaseAction<Account> {
	
	public String update(){
		System.out.println("----update----");
		System.out.println(model);
		return "index";
	}
	
	public void save(){
		System.out.println("----save---------");
	}
	
	public String query(){
//		System.out.println("---方案3测试----");
//		request.put("accountList", accountService.query());
//		session.put("accountList", accountService.query());
//		application.put("accountList", accountService.query());
//		return "index";
		
		jsonList = accountService.query();
		return "jsonList";
	}

}
