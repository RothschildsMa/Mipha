package com.ssm.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.EmployeeMapper;
import com.ssm.framework.entity.Employee;

@Service
public class EmployeeService {

	//社員情報マッパ
	@Autowired
	private EmployeeMapper employeeMapper;
	//社員情報すべて検索
	public List<Employee> findAll1(){
		return employeeMapper.findAll();
	}
	
}
