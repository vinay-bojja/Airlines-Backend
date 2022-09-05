package com.lti.dao;

import com.lti.dto.LoginStatusDT;
import com.lti.entity.Admin;
import com.lti.entity.User;

public interface LoginDao {

	public LoginStatusDT validateUser(String email, String password);
	public LoginStatusDT validateAdmin(String username, String password);
}
