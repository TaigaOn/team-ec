package jp.co.internous.team2512.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.team2512.model.domain.TblCart;
import jp.co.internous.team2512.model.domain.dto.CartDto;
import jp.co.internous.team2512.model.form.CartForm;
import jp.co.internous.team2512.model.mapper.TblCartMapper;
import jp.co.internous.team2512.model.session.LoginSession;


/**
 * カート情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb/cart")
public class CartController {
	
	/*
	 * フィールド定義
	 */
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblCartMapper tblCartMapper;
	
	private Gson gson = new Gson();
	

	/**
	 * カート画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		int userId = loginSession.getUserId();
	    if (userId == 0) {
	        userId = loginSession.getTmpUserId();
	    }
	    
	    List<CartDto> cartList = tblCartMapper.findByUserId(userId);
	    m.addAttribute("carts", cartList);
	    m.addAttribute("loginSession", loginSession);
	    
		return "cart";
		
	}

	/**
	 * カートに追加処理を行う
	 * @param f カート情報のForm
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		
		int userId = loginSession.getUserId();
		if (userId == 0) {
			userId = loginSession.getTmpUserId();
		}
		
		int productId = f.getProductId();
		int productCount = f.getProductCount();
		
		int existingCount = tblCartMapper.findCountByUserIdAndProductId(userId, productId);
		
		TblCart cartRecord = new TblCart();
		cartRecord.setUserId(userId);
		cartRecord.setProductId(productId);
		cartRecord.setProductCount(productCount);
		
		if (existingCount == 0) {
			tblCartMapper.insert(cartRecord);
		} else {
			tblCartMapper.update(cartRecord);
		}
		
		return "redirect:/frameweb/cart/";
		
	}

	/**
	 * カート情報を削除する
	 * @param checkedIdList 選択したカート情報のIDリスト
	 * @return true:削除成功、false:削除失敗
	 */
	@PostMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		
		try {
			CartForm form = gson.fromJson(checkedIdList, CartForm.class);
			List<Integer> ids = form.getCheckedIdList();
			
			tblCartMapper.deleteById(ids);
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
