package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Category;

import com.opensymphony.xwork2.ActionContext;



  
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
public class CategoryAction extends BaseAction<Category>{
	
	public String queryJoinAccount(){
		pageMap = new HashMap<String,Object>();
		System.out.println("type:"+model.getType());
		List<Category> categoryList = categoryService.queryJoinAccount(model.getType(), page, rows);
		//根据关键字查询总记录数
		System.out.println(categoryList);
		pageMap.put("total", categoryService.getCount(model.getType()));
		pageMap.put("rows",categoryList);
		return "jsonMap";
	}
	
	public String deleteByIds(){
		System.out.println("删除id为："+ids);
		categoryService.deleteByIds(ids);
		inputStream=new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public void update(){
		System.out.println("----update------");
		System.out.println(model);
		categoryService.update(model);
	}
	
	public void save(){
		System.out.println(model);
		categoryService.save(model);
	}
	
	public String query(){
		//显示值栈中root对象的内容
		System.out.println(ActionContext.getContext().getValueStack().getRoot());
		//解决方案1：采用相应***map,取代了原来内置对象，这样与jsp没有依赖，但是代码量比较大
//		ActionContext.getContext().put("categoryList", categoryService.query());
//		ActionContext.getContext().getSession().put("categoryList", categoryService.query());
//		ActionContext.getContext().getApplication().put("categoryList", categoryService.query());
		
		//解决方案2：实现相应的接口，让相应的map注入
		//RequestAware,SessionAware,ApplicationAware
		System.out.println("---方案3测试----");
		System.out.println("categoryService:"+categoryService);
//		request.put("categoryList", categoryService.query());
//		session.put("categoryList", categoryService.query());
//		application.put("categoryList", categoryService.query());
		return "index";
	}

	//方案2 继承RequestAware,SessionAware,ApplicationAwaremap接口注入map
	//如果接口写在子类代码量也会比较大，因此创建BaseAction,
	//将以下代码移到BaseAction中，再让其他Action继承便可
//	private Map<String,Object> application;
//	private Map<String,Object> session;
//	private Map<String,Object> request;
//	
//	@Override
//	public void setApplication(Map<String, Object> application) {
//		this.application=application;
//	}
//
//	@Override
//	public void setSession(Map<String, Object> session) {
//		this.session=session;
//		
//	}
//
//	@Override
//	public void setRequest(Map<String, Object> request) {
//		this.request=request;
//		
//	}
}
