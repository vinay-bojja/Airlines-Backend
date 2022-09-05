package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.AdminLoginDataTransfer;
import com.lti.dto.LoginStatusDT;
import com.lti.dto.UserLoginDataTransfer;
import com.lti.service.LoginService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/validateUser")
	public LoginStatusDT validateUser(@RequestBody UserLoginDataTransfer userLoginDT) {
		LoginStatusDT loginStatus = loginService.validateUser(userLoginDT.getEmailId(), userLoginDT.getPassword());
		return loginStatus;
	}

	@PostMapping("/validateAdmin")
	public LoginStatusDT validateAdmin(@RequestBody AdminLoginDataTransfer adminLoginDT) {
		LoginStatusDT loginStatus = loginService.validateAdmin(adminLoginDT.getUsername(), adminLoginDT.getPassword());
		return loginStatus;
	}

}
