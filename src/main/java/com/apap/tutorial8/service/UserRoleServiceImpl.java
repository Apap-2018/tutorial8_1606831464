package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.PassModel;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		//userDb
		return hashedPassword;
	}
	
	public boolean ubahPass(PassModel user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserRoleModel userNow = userDb.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		boolean check = passwordEncoder.matches(user.getOldPass(), userNow.getPassword());
		if (check) {
			String pass = encrypt(user.getKonfPass());
			userNow.setPassword(pass);
			userDb.save(userNow);
			return true;
		}
		else {
			return false;
		}
	
	}
	
}
