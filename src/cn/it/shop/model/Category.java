package cn.it.shop.model;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields
	//toString最好不要包含级联对象，否则有时不需要关联对象时lazy加载会报错
	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", hot=" + hot + "]";
	}

	private Integer id;
	private String type;
	private Boolean hot;
	private Account account;

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String type, Boolean hot, Account account) {
		this.type = type;
		this.hot = hot;
		this.account = account;
	}

	// Property accessors

	public Category(String type, boolean hot) {
		this.type=type;
		this.hot=hot;
	}

	public Category(int id, String type, boolean hot) {
		this.id=id;
		this.type=type;
		this.hot=hot;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return this.hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}