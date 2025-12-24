package jp.co.internous.team2512.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2512.model.domain.MstUser;
import jp.co.internous.team2512.model.session.LoginSession;

/**
 * マイページに関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/frameweb/mypage")
public class MyPageController {

	/*
	 * フィールド定義
	 */
	@Autowired
	private LoginSession loginSession;

	/**
	 * マイページ画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return マイページ画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		
		boolean isLoggedIn = loginSession.isLoggedIn();
		if (!isLoggedIn) {
			return "redirect:/frameweb/";
		}
		
		MstUser user = loginSession.getUserInfo();
		m.addAttribute("user", user);
		m.addAttribute("loginSession", loginSession);
		
		return "my_page";	
		
	}
}
