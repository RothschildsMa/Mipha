package com.ssm.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.Form;

@Mapper
public interface EmployeeMapper {

	//社員情報全て検索
	List<Employee> findAll();

	void add(Form form);

	void update(Form form);

	List<Employee> iFindByCondition(Form form);

	@Update("UPDATE T_Employee SET DEL_FLG = 1 WHERE ID = #{employeeId}")
	void updateEmployeeDeletedFlag(@Param("employeeId") String employeeId);

}
