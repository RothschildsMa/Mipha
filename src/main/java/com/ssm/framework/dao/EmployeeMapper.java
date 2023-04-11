package com.ssm.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssm.framework.entity.Employee;



@Mapper
public interface EmployeeMapper {
	
	 List<Employee> findAll();

}
