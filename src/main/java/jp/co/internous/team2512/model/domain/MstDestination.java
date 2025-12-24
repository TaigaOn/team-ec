package jp.co.internous.team2512.model.domain;

import java.sql.Timestamp;

import jp.co.internous.team2512.model.form.DestinationForm;

/**
 * mst_destinationのドメイン
 * @author インターノウス
 *
 */
public class MstDestination {
	
	private int id;
	private int userId;
	private String familyName;
	private String firstName;
	private String telNumber;
	private String address;
	private int status;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	public MstDestination () {
		
	}
	
	public MstDestination (DestinationForm f) {
		this.familyName = f.getFamilyName();
		this.firstName = f.getFirstName();
		this.address = f.getAddress();
		this.telNumber = f.getTelNumber();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	
	public String getTelNumber() {
		return telNumber;
	}
	
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
