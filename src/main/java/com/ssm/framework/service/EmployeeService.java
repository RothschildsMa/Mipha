package com.ssm.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.EmployeeMapper;
import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.Form;

@Service
public class EmployeeService {

	//社員情報マッパ
	@Autowired
	private EmployeeMapper employeeMapper;

	//社員情報すべて検索

	public List<Employee> findAll() {
		return employeeMapper.findAll();

	}

	//社員情報登録
	public void add(Form form) {
		employeeMapper.add(form);
	}

	//社員情報更新
	public void update(Form form) {
		employeeMapper.update(form);
	}

	public List<Employee> findByCondition(Form form) {
		return employeeMapper.iFindByCondition(form);

	}

}
