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

	//DB取得&出力サンプル
	@GetMapping(value = "/employee/list")
	public String showList(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		return "output";
	}
	
	@GetMapping(value = "/employee/list1")
	public String showList1(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		return "team2/akimi";
	}
	
	
	@GetMapping(value = "/emp/add")
    public String displayAdd(Model model) {
        model.addAttribute("form", new Form());
        return "team2/register2";
    }
	
	@RequestMapping(value = "/emp/insert", method = RequestMethod.POST)
    public String addToTable(Form form) {
        // 社員情報の登録
		employeeService.add(form); //情報挿入
        return "redirect:/employee/list"; //リダイレクト
    }

	@GetMapping("login")
	public String loginView() {
		return "team1/Login";
	}

	@GetMapping("akimi")
	public String restart() {
		return "team2/akimi";
	}

}
