package com.ssm.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.Form;
import com.ssm.framework.service.EmployeeService;

@Controller
public class MainController {

	@Autowired
	private EmployeeService employeeService;

	//debugリスト表示
	@GetMapping(value = "/employee/list")
	public String showList(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		return "output";
	}

	@GetMapping("login")
	public String loginView() {
		return "team1/Login";
	}
	
	//社員情報一覧画面
	@GetMapping(value = "/emp/info")
	public String employeeInformation(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		//model.addAttribute("form", new Form());
		return "team2/emp2";
	}

	//社員情報登録画面
	@GetMapping(value = "/emp/add")
	public String displayAdd(Model model) {
		model.addAttribute("form", new Form());
		return "team2/register2";
	}

	//社員情報登録処理
	@RequestMapping(value = "/emp/insert", method = RequestMethod.POST)
	public String addToTable(Form form) {
		// 社員情報の登録
		employeeService.add(form); //情報挿入
		return "redirect:/emp/info"; //リダイレクト
	}
	
	
	
	
	

	@RequestMapping(value = "/emp/update", method = RequestMethod.POST)
	public String updateToTabel(Form form) {
		// 社員情報の更新
		employeeService.update(form); //情報挿入
		return "redirect:/employee/list"; //リダイレクト
	}

	//条件検索 完成(社員ID) 未完成(所属ID、入社年月日範囲チェック)
	@RequestMapping(value = "/employee/search", method = RequestMethod.POST)
	public String getEmployeesByCondition(Model model, Form form) {
		List<Employee> employees = employeeService.findByCondition(form);
		model.addAttribute("employeeList", employees);
		return "team2/emp2";

	}


}
