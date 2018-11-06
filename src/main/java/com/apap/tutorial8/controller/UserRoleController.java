package com.apap.tutorial8.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tutorial8.model.PassModel;
import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	//private UserDb userDb;
	
	@RequestMapping( value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user,RedirectAttributes redirectAttrs) {
		if(validatePassword(user.getPassword())) {
			userService.addUser(user);
			String message = "User Berhasil ditambah";
			redirectAttrs.addFlashAttribute("message", message);
		}
		else {
			String message = "Password tidak sesuai ketentuan";
			redirectAttrs.addFlashAttribute("message", message);
		}
		return "redirect:/";
	}
	@RequestMapping("/ubahpass")
	public String updatePassword() {
		return "update-password";
	}
	
	public boolean validatePassword(String password) {
		if (password.length()>=8 && Pattern.compile("[0-9]").matcher(password).find() &&  Pattern.compile("[a-zA-Z]").matcher(password).find())  {
			return true;
		}
		else {
			return false;
		}
	}
	
	@RequestMapping(value = "/ubahpassword", method = RequestMethod.POST)
	private String ubahPassSubmit(@ModelAttribute PassModel user,RedirectAttributes redirectAttrs) {
		if(user.getNewPass().equals(user.getKonfPass())) {
			if(validatePassword(user.getNewPass())) {
				if(userService.ubahPass(user)) {
					String message = "Password berhasil diubah";
					redirectAttrs.addFlashAttribute("message", message);
				}
				else {
					String message = "Password tidak berhasil diubah";
					redirectAttrs.addFlashAttribute("message", message);
				}
			}
			else {
				String message = "Password baru tidak sesuai ketentuan";
				redirectAttrs.addFlashAttribute("message", message);
			}
			
		}
		else {
			String message = "Password konfirmasi tidak sesuai";
			redirectAttrs.addFlashAttribute("message", message);
		}
		return "redirect:/user/ubahpass";
		
	}

}
