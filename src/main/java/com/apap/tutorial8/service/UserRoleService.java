package com.apap.tutorial8.service;

import com.apap.tutorial8.model.PassModel;
import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
	public boolean ubahPass(PassModel pass);
}
