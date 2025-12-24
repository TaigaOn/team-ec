package jp.co.internous.team2512.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2512.model.domain.MstCategory;
import jp.co.internous.team2512.model.domain.MstProduct;
import jp.co.internous.team2512.model.form.SearchForm;
import jp.co.internous.team2512.model.mapper.MstCategoryMapper;
import jp.co.internous.team2512.model.mapper.MstProductMapper;
import jp.co.internous.team2512.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb")
public class IndexController {
	
	/*
	 * フィールド定義
	 */
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		if (loginSession.isLoggedIn() == false && loginSession.getTmpUserId() == 0) {
			Integer tmp = ThreadLocalRandom.current().nextInt(100000000, 1000000000);
			loginSession.setTmpUserId(-tmp);
		}
		
		List<MstCategory> categories = categoryMapper.find();
		m.addAttribute("categories", categories);
		
		List<MstProduct> products = productMapper.find();
		m.addAttribute("products", products);
		
		m.addAttribute(loginSession);
		
		return "index"; 
		
	}
	
	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		
		String searchKeywords = f.getKeywords();
		int categoryId = f.getCategory();
		
		String[] splitKeywords;
		if (searchKeywords == null || searchKeywords.isBlank()) {
			splitKeywords = new String[] {};
		} else {
			searchKeywords = searchKeywords
					.replaceAll("　", " ")
					.replaceAll("\\s+", " ")
					.trim();
			splitKeywords = searchKeywords.split(" ");
		}
		
		List<MstProduct> searchResult;
		if (categoryId > 0) {
			searchResult = productMapper.findByCategoryAndProductName(categoryId, splitKeywords);
		} else if (splitKeywords.length > 0) {
			searchResult = productMapper.findByProductName(splitKeywords);
		} else {
			searchResult = productMapper.find();
		}
		
		m.addAttribute(loginSession);
		m.addAttribute("selected", categoryId);
		m.addAttribute("keywords", searchKeywords);
		m.addAttribute("categories", categoryMapper.find());
		m.addAttribute("products", searchResult);
		
		return "index";
		
	}
}
