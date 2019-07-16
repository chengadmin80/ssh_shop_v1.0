package cn.it.ssh;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.it.shop.model.Category;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.impl.CategoryServiceImpl;



 /**
 * @Title  SSHTest.java
 * @package_name cn.it.ssh
 * @author Administrator
 * @date   2016-3-26
 * @todo   TODO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-*.xml")
public class SSHTest {
	
	@Resource
	private CategoryService categoryService;
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Test	//测试hibernate的开发环境，因为没有整合，也可以直接new
	public void hibernate(){
		CategoryService categoryService=new CategoryServiceImpl();
		categoryService.save(new Category("men", true));
	}
	@Test	//测试hibernate
	public void hibernateAndSpring(){
		
		categoryService.update(new Category(2,"test", true));
		System.out.println("-------------");
		//System.out.print(Integer.parseInt("xyz"));
	}
	
}
