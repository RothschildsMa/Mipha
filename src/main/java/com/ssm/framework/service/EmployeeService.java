package com.ssm.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.EmployeeMapper;
import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.UpdateForm;

@Service
public class EmployeeService {

	//社員情報マッパ
	@Autowired
	private EmployeeMapper employeeMapper;

	//社員情報すべて検索

	public List<Employee> findAll() {
		return employeeMapper.findAll();

	}

	public Employee linkId(String employeeId) {
		return employeeMapper.linkId(employeeId);
	}

	//社員情報登録
	public class DuplicateEmployeeIdException extends Exception {
		public DuplicateEmployeeIdException(String message) {
			super(message);
		}
	}

	public void add(UpdateForm form) throws DuplicateEmployeeIdException {
		String employeeId = form.getEmployeeId();

		// 重複する社員IDが存在しないかチェックする
		Employee existingEmployee = employeeMapper.findByEmployeeId(employeeId);
		if (existingEmployee != null) {
			throw new DuplicateEmployeeIdException("社員ID " + employeeId + " は既に存在します。");
		}

		// データベースに新しい社員を登録する
		employeeMapper.add(form);
	}

	}

	//社員情報更新
	public void update(UpdateForm form) {
		employeeMapper.update(form);
	}

	//削除フラグ
	public void deleteEmployees(int id) {
		employeeMapper.deleteEmployees(id);
	}

	//社員ID重複時エラー表示
	public boolean exists(int id) {
		return employeeMapper.exists(id) > 0;
	}

	public List<Employee> findByCondition(UpdateForm form) {
		return employeeMapper.iFindByCondition(form);

	}

}
