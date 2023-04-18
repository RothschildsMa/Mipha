package com.ssm.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.UpdateForm;

@Mapper
public interface EmployeeMapper {

	//社員情報全て検索
	List<Employee> findAll();
	
	Employee linkId(String employeeId);

	void add(UpdateForm form);

	void update(UpdateForm form);

	List<Employee> iFindByCondition(UpdateForm form);
	
	
	

}
