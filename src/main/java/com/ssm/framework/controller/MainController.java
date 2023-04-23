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

	private boolean error=false;

	
	//debugリスト表示
	/*@GetMapping(value = "/employee/list")
	public String showList(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		return "output";
	}*/

	//AOP
	//開始画面
	@GetMapping("login")
	public String loginView(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		model.addAttribute("error",error);
		return "team1/Login";
	}

	//ID Passwordチェック処理
	@RequestMapping(value = "/emp/login", method = RequestMethod.POST)
	public String check(LoginForm loginForm, Model model) {

		Employee emp = loginService.checkId(loginForm);

		if (emp == null) {
			error = true;
			return "redirect:/login";
	    }else{
	    	error = false;
			return "redirect:/employee/view";
		}

	}
	
	//社員情報一覧画面
	@GetMapping(value = "/employee/view")
	public String showList2(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		model.addAttribute("employeeDepatmentInput", 003);
		return "team2/employInformationDisplay";
	}

	//社員情報検索処理
	@GetMapping(value = "/employee/employInformationDisplay")
	public String showList2(Model model, UpdateForm form,
			@RequestParam("employeeId") String employeeInput,
			@RequestParam("employeeDepatmentId") int employeeDepatmentInput,
			@RequestParam("startDate") String startDateInput,
			@RequestParam("endDate") String endDateInput) {
		String strDt = form.getStartDate();
		String endDt = form.getEndDate();
		form.setStartDate(strDt);
		form.setEndDate(endDt);
		model.addAttribute("employeeInput", employeeInput);
		model.addAttribute("employeeDepatmentInput", employeeDepatmentInput);
		model.addAttribute("startDateInput", startDateInput);
		model.addAttribute("endDateInput", endDateInput);
		List<Employee> empList = employeeService.findByCondition(form);
		model.addAttribute("employeeList", empList);
		return "team2/employInformationDisplay";
	}
	

	//社員情報登録画面
	@GetMapping(value = "/emp/add")
	public String addView(Model model) {
		UpdateForm updateForm = new UpdateForm();
		Employee emp = employeeService.findMaxIdOfEmployee();
		Number empId = Number.class.cast(Integer.parseInt(emp.getEmployeeId().substring(3)) + 1 );
		String empIdStr = "0000000" + empId.toString();
		String employeeId = empIdStr.substring(empIdStr.length() - 7);
		updateForm.setEmployeeId("NTS" + employeeId);
		model.addAttribute("form", updateForm);
		return "team2/register2";
	}

	//社員情報登録処理
	@RequestMapping(value = "/emp/insert", method = RequestMethod.POST)
	public String addToTable(UpdateForm updateForm) {
		try {
			employeeService.add(updateForm); //情報挿入
		} catch (Exception e) {
			// TODO: handle exception
			return "team2/addFaild";
		}
		
		return "redirect:/employee/view";
	}

	//社員情報更新画面
	@GetMapping("/emp/{id}/update")
	public String updateView(@PathVariable String id, Model model) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Employee emp = employeeService.linkId(id);
		UpdateForm updateForm = new UpdateForm();

		updateForm.setEmployeeId(emp.getEmployeeId());
		updateForm.setEmployeeName(emp.getEmployeeName());
		updateForm.setJoinDate(sdf.format(emp.getJoinDate()));
		updateForm.setEmployeeNameKana(emp.getEmployeeNameKana());
		updateForm.setEmployeeGenderId(emp.getEmployeeGenderId());
		updateForm.setEmployeeAge(emp.getEmployeeAge());
		updateForm.setEmployeeDepatmentId(emp.getEmployeeDepatmentId());
		updateForm.setEmployeePhoneNumber(emp.getEmployeePhoneNumber());
		updateForm.setEmployeeMail(emp.getEmployeeMail());
		model.addAttribute("updateForm", updateForm);
		return "team2/update";
	}

	//社員情報更新処理
	@RequestMapping(value = "/emp/update", method = RequestMethod.POST)
	public String updateToTabel(@ModelAttribute UpdateForm updateForm) {
		// 社員情報の更新
		employeeService.update(updateForm); //情報更新
		return "redirect:/employee/view"; 
	}


	//社員情報削除処理
	@PostMapping("/deleteEmployees")
	public String deleteEmployees(@RequestParam("selectedEmployees") List<Integer> selectedEmployees){
		if(!selectedEmployees.isEmpty()) {
			for (int id : selectedEmployees) {
				employeeService.deleteEmployees(id);
			}
		}
		return "redirect:/employee/view";
	}

	
	//ログアウト用
	 @GetMapping("/logout")
	 public String logout(Model model) {
	  
	     return "redirect:/login";
	 }
}
