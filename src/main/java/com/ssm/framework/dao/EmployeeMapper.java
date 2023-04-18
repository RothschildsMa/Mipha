package com.ssm.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.UpdateForm;

@Mapper
public interface EmployeeMapper {

	//社員情報全て検索
	List<Employee> findAll();

	void add(UpdateForm form);

	void update(UpdateForm form);

	List<Employee> iFindByCondition(UpdateForm form);
	
	
	

	@Update("UPDATE T_Employee SET DEL_FLG = 1 WHERE ID = #{employeeId}")
	void updateEmployeeDeletedFlag(@Param("employeeId") String employeeId);

}
