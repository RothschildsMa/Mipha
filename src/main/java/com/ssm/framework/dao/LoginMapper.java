package com.ssm.framework.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.LoginForm;

@Mapper
public interface LoginMapper {
	
	 Employee checkId(LoginForm loginForm);
	 
}
