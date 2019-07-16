package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Category;

public interface CategoryService extends BaseService<Category> {
//	public void save(Category category);
//	
//	public void update(Category category);
//	
//	public void delete(int id);
//	
//	public Category get(int id);
//	
//	public List<Category> query();
	//查询类别信息，级联管理员
	public List<Category> queryJoinAccount(String type,int page,int pageSize);
	//根据关键总记录数
	public Long getCount(String type);
	//根据ids删除多条记录
	public void deleteByIds(String ids);
}
