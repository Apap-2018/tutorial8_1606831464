package com.apap.tutorial8.model;

public class PassModel {
	String oldPass;
	String newPass;
	String konfPass;
	
	public PassModel(String oldPass, String newPass, String konfPass) {
		this.oldPass = oldPass;
		this.newPass = newPass;
		this.konfPass = konfPass;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getKonfPass() {
		return konfPass;
	}

	public void setKonfPass(String konfPass) {
		this.konfPass = konfPass;
	}
	
}
