package jp.co.internous.team2512.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import jp.co.internous.team2512.model.domain.MstUser;
import jp.co.internous.team2512.model.form.UserForm;
import jp.co.internous.team2512.model.mapper.MstUserMapper;
import jp.co.internous.team2512.model.mapper.TblCartMapper;
import jp.co.internous.team2512.model.session.LoginSession;


/**
 * 認証に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@RestController
@RequestMapping("/frameweb/auth")
public class AuthController {
	
	/*
	 * フィールド定義
	 */
	@Autowired
	private MstUserMapper userMapper;
	
	@Autowired
	private TblCartMapper tblCartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	private Gson gson = new Gson();
	
	/**
	 * ログイン処理をおこなう
	 * @param f ユーザーフォーム
	 * @return ログインしたユーザー情報(JSON形式)
	 */
	@PostMapping("/login")
	public String login(@RequestBody UserForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(
			f.getUserName(),
			f.getPassword()
		);
		if(user != null) {
			int userId = user.getId();
			int tmpUserId = (int) loginSession.getTmpUserId();
			tblCartMapper.updateUserId(userId,tmpUserId);
			
			loginSession.setUserId(userId);
			loginSession.setTmpUserId(0);
			loginSession.setUserName(user.getUserName());
			loginSession.setPassword(user.getPassword());
			loginSession.setLoggedIn(true);
			loginSession.setUserInfo(user);
		} else {
			loginSession.setUserId(0);
			loginSession.setUserName(null);
			loginSession.setPassword(null);
			loginSession.setLoggedIn(false);
		}
		return gson.toJson(user);
	}
	
	/**
	 * ログアウト処理をおこなう
	 * @return 空文字
	 */
	@PostMapping("/logout")
	public String logout() {
		loginSession.setUserId(0);
		loginSession.setTmpUserId(0);
		loginSession.setUserName(null);
		loginSession.setPassword(null);
		loginSession.setLoggedIn(false);
		loginSession.setUserInfo(null);
		return "";
	}

	/**
	 * パスワード再設定をおこなう
	 * @param f ユーザーフォーム
	 * @return 処理後のメッセージ
	 */
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody UserForm f) {
		String userName = loginSession.getUserName();
		String newPassword = f.getNewPassword();
		
		if (loginSession.getPassword().equals(newPassword)) {
			return "現在のパスワードと同一文字列が入力されました。";
		} else {
			userMapper.updatePassword(userName,newPassword);
			loginSession.setPassword(newPassword);
			return "パスワードが再設定されました。";
		}
	}
}
