/**
 * 
 */
package cn.it.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


/*
 * Struts执行流程：先创建Action，再调用拦截器，拦截器访问成功再调用Action的方法
 * servletConfig:有如下代码：判断当前实现什么接口，则会注入相应对象
 *      if (action instanceof ApplicationAware) {
            ((ApplicationAware) action).setApplication(context.getApplication());
        }
        
        if (action instanceof SessionAware) {
            ((SessionAware) action).setSession(context.getSession());
        }
        
        if (action instanceof RequestAware) {
            ((RequestAware) action).setRequest((Map) context.get("request"));
        }
 * 
 * 在项目启动的时候struts的过滤器，已经把相应的内置对象，
 * 和内置对象对应的Map存储到了ActionContext和值栈中
 * 如果实现了相应的***Aware接口，就会从ActionContext中获取相应的Map进行转入,
 * 这一步是通过拦截器来实现的：
 * */
  
    /**        
     * Title: BaseAction.java    
     * Description: 描述
     * @author liuweicheng       
     * @created 2016-4-3 上午10:58:59    
     */      
@Controller
@Scope("prototype") 
public class BaseAction<T> extends ActionSupport implements RequestAware,SessionAware,ApplicationAware,ModelDriven<T> {

	//页面参数
	protected Map<String,Object> pageMap = null;
	
	protected Integer page;
	
	protected Integer rows;
	
	protected String ids;
	
	protected InputStream inputStream;
	
	protected List<T> jsonList = null;
	
	//泛型
	protected T model;
	//service
	@Resource
	public CategoryService categoryService;
	@Resource
	public AccountService accountService;
	
	//statckvalue
	protected Map<String,Object> application;
	protected Map<String,Object> session;
	protected Map<String,Object> request;
	
//	public void setAccountService(AccountService accountService) {
//		this.accountService = accountService;
//	}
//
//	public void setCategoryService(CategoryService categoryService) {
//		this.categoryService = categoryService;
//	}
//	
	@Override
	public T getModel() {
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		Class clazz=(Class)type.getActualTypeArguments()[0];
		try {
			model = (T)clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return model;
	}
	
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application=application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
		
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public List<T> getJsonList() {
		return jsonList;
	}
	
}
