package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	
	//查询热点类别
	//不写，默认也会调用
	public CategoryServiceImpl(){
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> queryJoinAccount(String type,int page,int pageSize) {
		StringBuffer sql=new StringBuffer("FROM Category c left join fetch c.account where c.type like :type");
		return getSession().createQuery(sql.toString()).setString("type", "%"+type+"%")
				.setFirstResult((page-1)*pageSize)
				.setMaxResults(pageSize)
				.list();
	}

	@Override
	public Long getCount(String type) {
		String hql = "SELECT COUNT(c) FROM Category c where c.type like :type";
		Long count = (Long) getSession().createQuery(hql)
		.setString("type", "%"+type+"%")
		.uniqueResult();
		return count;
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "DELETE FROM Category WHERE ID IN (" + ids +")";
		getSession().createQuery(hql)
		.executeUpdate();
	}
	
	

	/*
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		//从当前线程获取session，如果没有则创建一个新的session
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override //没有整合的情况
//	public void save(Category category) {
//		//通过工具类获取session
//		Session session=HibernateSessionFactory.getSession();
//		try {
//			//手动事务
//			session.getTransaction().begin();
//			//执行业务逻辑
//			session.save(category);
//			//手动提交
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally{
//			HibernateSessionFactory.closeSession();
//		}
//	}
	
	public void update(Category category){
		getSession().update(category);
	}

	@Override
	public void delete(int id) {
//		Object object = getSession().get(Category.class,id);
//		if(object!=null){
//			getSession().delete(object);
//		}
		String hql = "DELETE Category WHERE id=:id";
		getSession().createQuery(hql).setInteger("id", id)
		.executeUpdate();
	}

	@Override
	public Category get(int id) {
		return (Category)getSession().get(Category.class, id);
	}

	@Override
	public List<Category> query() {
		String hql = "FROM Category";
		return getSession().createQuery(hql).list();
	}

	@Override
	public void save(Category category) {
		getSession().save(category);
	}
	
	*/
}
