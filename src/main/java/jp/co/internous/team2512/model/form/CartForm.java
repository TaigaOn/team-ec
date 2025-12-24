package jp.co.internous.team2512.model.form;

import java.io.Serializable;
import java.util.List;

/**
 * カートフォーム
 * @author インターノウス
 *
 */
public class CartForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Integer> checkedIdList;
	private Integer userId;
	private Integer productId;
	private Integer productCount;
	
	public List<Integer> getCheckedIdList() {
		return checkedIdList;
	}
	
	public void setCheckedIdList(List<Integer> checkedIdList) {
		this.checkedIdList = checkedIdList;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getProductCount() {
		return productCount;
	}
	
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

}
