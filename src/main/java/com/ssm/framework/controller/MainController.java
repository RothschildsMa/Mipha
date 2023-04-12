package com.ssm.framework.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.service.EmployeeService;


@Controller
public class MainController {
	//DI(依存性注入)　spring 根幹※
	//7
	@GetMapping("view2") //URLマッピング
	public String start() { //リクエスト　ハンドラ　メソッド
		return "team2/input";
	}
	
	
	@Autowired
    private EmployeeService employeeService;
    
	//DB取得&出力サンプル1
    @GetMapping(value = "/employee/list")
    public String showList(Model model) {
    	//ローワーキャメルケース　命名規則
        List<Employee> empList = employeeService.findAll();
        model.addAttribute("employeeList", empList);
        return "output";
    }
	
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	
	@GetMapping("login")
	public String loginView() {
		return "team1/Login";
	}
	
	
	@GetMapping("akimi")
	public String restart() {
		return "team2/akimi";
	}
	
	@GetMapping("view3")
	public String employInformationDisplay() {
		return "team2/employInformationDisplay";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	//HTTPメソッド
	@PostMapping("confirm")
	public String confirmView(Model model,@RequestParam String name, @RequestParam Integer age,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam LocalDate birth) {
		
		
		model.addAttribute("name",name);
		model.addAttribute("age",age);
		model.addAttribute("birth",birth);
		
		return "confirm";
	}
}
//testHama
//testZ