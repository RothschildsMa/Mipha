package com.ssm.framework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.LoginMapper;
import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.LoginForm;

@Service
public class LoginService {
	
	@Autowired
	LoginMapper loginMapper;
	
	public Employee checkId(LoginForm loginForm){
		
		return loginMapper.checkId(loginForm);
		
	}
	

}
