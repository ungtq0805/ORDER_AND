/*
 * IOC - Tema2
 * 
 * Stefan-Dobrin Cosmin
 * 342C4
 */
package ctwhs.order.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * The Class Master User.
 */
@DatabaseTable(tableName = "MST_USER")
public class MstUserEnt {

	@DatabaseField(columnName = "USER_ID",generatedId = true)
	private int userId;

	/** The User name. */
	@DatabaseField(columnName = "USER_NAME")
	private String userName;

	/** The quantity. */
	@DatabaseField(columnName = "FULL_NAME")
	private String fullName;

	@DatabaseField(columnName = "PASSWORD")
	private String password;

	/** Type of reg date time */
	@DatabaseField(columnName = "ACTIVE")
	private Boolean regDateTime;

	public MstUserEnt() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Boolean regDateTime) {
		this.regDateTime = regDateTime;
	}
}
