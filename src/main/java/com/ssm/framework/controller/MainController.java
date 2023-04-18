package com.ssm.framework.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.form.LoginForm;
import com.ssm.framework.form.UpdateForm;
import com.ssm.framework.service.EmployeeService;
import com.ssm.framework.service.LoginService;

@Controller
public class MainController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LoginService loginService;
	
	@GetMapping("login")
	public String loginView(Model model) {
		//LoginForm loginForm = new LoginForm();
		
		model.addAttribute("loginForm",new LoginForm());
		return"team1/Login";
		
	}
	
	@RequestMapping(value = "/emp/login", method = RequestMethod.POST)
	public String displayList(LoginForm loginForm,Model model) {
		
		Employee emp = loginService.checkId(loginForm);
		if(emp == null) {
			return "redirect:/login";
		}
		else {
			return "redirect:/emp/info";
		}
	
		
	}
	

	//debugリスト表示
	@GetMapping(value = "/employee/list")
	public String showList(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		return "output";
	}

	

	//社員情報一覧画面
	@GetMapping(value = "/emp/info")
	public String employeeInformation(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		model.addAttribute("updateForm", new UpdateForm());
		return "team2/emp2";
	}

	//社員情報登録画面
	@GetMapping(value = "/emp/add")
	public String displayAdd(Model model) {
		model.addAttribute("form", new UpdateForm());
		return "team2/register2";
	}

	//社員情報登録処理
	@RequestMapping(value = "/emp/insert", method = RequestMethod.POST)
	public String addToTable(UpdateForm updateForm) {
		// 社員情報の登録
		employeeService.add(updateForm); //情報挿入
		return "redirect:/emp/info"; //リダイレクト
	}

	
	
	@GetMapping("/emp/{id}/update")
    public String displayEdit(@PathVariable String id, Model model) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
        Employee emp = employeeService.linkId(id);
        UpdateForm updateForm = new UpdateForm();

        updateForm.setEmployeeId(emp.getEmployeeId());
       // updateForm.setJoinDate(sdf.format(emp.getJoinDate()));
        
        updateForm.setEmployeeName(emp.getEmployeeName());
        updateForm.setEmployeeNameKana(emp.getEmployeeNameKana());
        updateForm.setEmployeeGenderId(emp.getEmployeeGenderId());
        updateForm.setEmployeeAge(emp.getEmployeeAge());
        updateForm.setEmployeePhoneNumber(emp.getEmployeePhoneNumber());
        updateForm.setEmployeeMail(emp.getEmployeeMail());
        model.addAttribute("updateForm", updateForm);
        return "team2/update";
    }

	@RequestMapping(value = "/emp/update", method = RequestMethod.POST)
	public String updateToTabel(@ModelAttribute UpdateForm updateForm) {
		// 社員情報の更新
		employeeService.update(updateForm); //情報更新
		return "redirect:/emp/info"; //リダイレクト
	}

	//条件検索 完成(社員ID) 未完成(所属ID、入社年月日範囲チェック)
	@RequestMapping(value = "/employee/search", method = RequestMethod.POST)
	public String getEmployeesByCondition(Model model, UpdateForm updateForm) {
		List<Employee> employees = employeeService.findByCondition(updateForm);
		model.addAttribute("employeeList", employees);
		return "team2/emp2";

	}

	//削除フラグ用
	@PostMapping("/deleteEmployees")
	public String deleteEmployees(@RequestParam("employeeIds") String[] employeeIds) {
		for (String employeeId : employeeIds) {
			employeeService.updateDeletedFlag(employeeId);
		}
		return "redirect:/emp/info";
	}

	@GetMapping(value = "/employee/view")
	public String showList2(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		return "team2/employInformationDisplay";
	}

	@GetMapping(value = "/employee/employInformationDisplay")
	public String showList2(Model model, UpdateForm form) {
		List<Employee> empList = employeeService.findByCondition(form);
		model.addAttribute("employeeList", empList);
		return "team2/employInformationDisplay";
	}
}
