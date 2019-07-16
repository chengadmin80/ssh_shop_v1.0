package cn.it.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.it.shop.model.Account;
import cn.it.shop.service.BaseService;


  
    /**        
     * Title: BaseServiceImpl.java    
     * Description: 公共类的抽取
     * @author liuweicheng       
     * @created 2016-4-1 下午8:23:40    
     */      
@SuppressWarnings("unchecked")
@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T> {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Class clazz;//clazz中存储了当前操作的类型
	
	public BaseServiceImpl(){
		System.out.println("this 代表的是当时调用构造方法的对象");
		System.out.println("获取当前this对象的父类信息："+this.getClass().getSuperclass());
		System.out.println("获取当前this对象的父类信息(包括泛型信息)："+this.getClass().getGenericSuperclass());
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		clazz=(Class)type.getActualTypeArguments()[0];
	}
	
	protected Session getSession() {
		//从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void save(T t) {
		getSession().save(t);
	}

	@Override
	public void update(T t) {
		getSession().update(t);
		
	}

	@Override
	public void delete(int id) {
		String hql = "DELETE "+ clazz.getSimpleName() +" WHERE id=:id";
		getSession().createQuery(hql).setInteger("id", id)
		.executeUpdate();
	}

	
	@Override
	public T get(int id) {
		return (T)getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "FROM "+clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}

}
