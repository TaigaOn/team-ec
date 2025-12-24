package jp.co.internous.team2512.model.domain;

import java.sql.Timestamp;

import jp.co.internous.team2512.model.form.UserForm;

/**
 * mst_userのドメイン
 * @author インターノウス
 *
 */
public class MstUser {
	
	private int id;
	private String userName;
	private String password;
	private String familyName;
	private String firstName;
	private String familyNameKana;
	private String firstNameKana;
	private Byte gender;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public MstUser() {
		
	}
	
	public MstUser(UserForm f) {
		this.userName = f.getUserName();
		this.password = f.getPassword();
		this.familyName = f.getFamilyName();
		this.firstName = f.getFirstName();
		this.familyNameKana = f.getFamilyNameKana();
		this.firstNameKana = f.getFirstNameKana();
		this.gender = f.getGender();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFamilyNameKana() {
		return familyNameKana;
	}
	
	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}
	
	public String getFirstNameKana() {
		return firstNameKana;
	}
	
	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}
	
	public Byte getGender() {
		return gender;
	}
	
	public void setGender(Byte gender) {
		this.gender = gender;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
}
