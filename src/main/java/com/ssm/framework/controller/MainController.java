package com.ssm.framework.controller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssm.framework.entity.Employee;
import com.ssm.framework.entity.MCode;
import com.ssm.framework.form.AddForm;
import com.ssm.framework.form.LoginForm;
import com.ssm.framework.form.UpdateForm;
import com.ssm.framework.service.EmployeeService;
import com.ssm.framework.service.LoginService;
import com.ssm.framework.service.MCodeService;

@Controller
public class MainController {
	@Autowired
	private MCodeService mCodeService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private LoginService loginService;

	private boolean error=false;
	
	private List<MCode> departmentList ;
	
	private LoginForm login;
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
	@RequestMapping(value = "/emp/login")
	public String check(LoginForm loginForm, Model model) {
		String inputPassword = DigestUtils.md5DigestAsHex(loginForm.getPassword().getBytes());
		loginForm.setPassword(inputPassword);
		Employee emp = loginService.checkId(loginForm);
		if (emp == null) {
			error = true;
			return "redirect:/login";
	    }else{
	    	error = false;
	    	login = loginForm;
			return "redirect:/employee/view";
		}
	}

	//社員情報一覧画面
	@GetMapping(value = "/employee/view")
	public String homeView(Model model) {
		List<Employee> empList = employeeService.findAll();
		model.addAttribute("employeeList", empList);
		departmentList = mCodeService.iFindDepatmentName();
		model.addAttribute("departmentList", departmentList);
		return "team2/employInformationDisplay";
	}

	//社員情報検索処理
	@GetMapping(value = "/employee/employInformationDisplay")
	public String searchView(Model model, UpdateForm form) {
		String strDt = form.getStartDate();
		String endDt = form.getEndDate();
		form.setStartDate(strDt);
		form.setEndDate(endDt);
		model.addAttribute("employeeInput", form.getEmployeeId());
		String iEmployeeDepatmentId = form.getEmployeeDepatmentId();
//		Integer iEmployeeDepatmentId = Integer.parseInt(form.getEmployeeDepatmentId());
		if (iEmployeeDepatmentId.equals("000")) {
			form.setEmployeeDepatmentId(null);
		}else{
			model.addAttribute("employeeDepatmentInput", iEmployeeDepatmentId);
		}
		
		model.addAttribute("startDateInput", form.getStartDate());
		model.addAttribute("endDateInput", form.getEndDate());
		List<Employee> empList = employeeService.findByCondition(form);
		model.addAttribute("employeeList", empList);
		model.addAttribute("departmentList", departmentList);
		return "team2/employInformationDisplay";
	}
	

	//社員情報登録画面
	@GetMapping(value = "/emp/add")
	public String addView(Model model) {
		AddForm addForm = new AddForm();
		Employee emp = employeeService.findMaxIdOfEmployee();
		Number empId = Number.class.cast(Integer.parseInt(emp.getEmployeeId().substring(3)) + 1 );
		String empIdStr = "0000000" + empId.toString();
		String employeeId = empIdStr.substring(empIdStr.length() - 7);
		addForm.setEmployeeId("NTS" + employeeId);
		model.addAttribute("addForm", addForm);
		return "team2/register2";
	}

	//社員情報登録処理
	@RequestMapping(value = "/emp/insert", method = RequestMethod.POST)
	public String addToTable(@Validated AddForm addForm, BindingResult result, Model model) {
//		try {
//			
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			//return "team2/addFaild";
//			return "redirect:/employee/view";
//		}
		if (result.hasErrors()) {
            // 入力チェックエラーの場合
//            List<String> errorList = new ArrayList<String>();
//            for (ObjectError error : result.getAllErrors()) {
//                errorList.add(error.getDefaultMessage());
//            }
//            model.addAttribute("validationError", errorList);
            return "team2/register2";
        }
		
		Random random = new Random();
	    StringBuilder password = new StringBuilder();
	    for (int i = 0; i < 6; i++) {
	    	password.append(random.nextInt(10));
	    }
	    String encryptedPassword = DigestUtils.md5DigestAsHex(password.toString().getBytes());
	    addForm.setUpdateUser(password.toString());// パスワードを知るために、一時設定
	    addForm.setPassword(encryptedPassword);
	    addForm.setCreateUser(login.getEmployeeId().toString());
		employeeService.add(addForm); //情報挿入
		
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
		updateForm.setUpdateUser(login.getEmployeeId());
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
	public String deleteEmployees(Model model, UpdateForm form,LoginForm loginForm,@RequestParam("selectedEmployees") List<Integer> selectedEmployees){

		String loginEmployee;
		if(loginForm == null) {
			loginEmployee = "";
		}else {
			loginEmployee = loginForm.getEmployeeId();
		}

		

		if(!selectedEmployees.isEmpty()) {
			for (int id : selectedEmployees) {
				employeeService.deleteEmployees(id,loginEmployee);
			}
		}
		String strDt = form.getStartDate();
		String endDt = form.getEndDate();
		form.setStartDate(strDt);
		form.setEndDate(endDt);
		model.addAttribute("employeeInput", form.getEmployeeId());
		Integer iEmployeeDepatmentId = Integer.parseInt(form.getEmployeeDepatmentId());
		if (iEmployeeDepatmentId == 000 ) {
			form.setEmployeeDepatmentId(null);
		}else{
			model.addAttribute("employeeDepatmentInput", iEmployeeDepatmentId);
		}
		
		model.addAttribute("startDateInput", form.getStartDate());
		model.addAttribute("endDateInput", form.getEndDate());
		List<Employee> empList = employeeService.findByCondition(form);
		model.addAttribute("employeeList", empList);
		model.addAttribute("departmentList", departmentList);
		//return "redirect:/employee/employInformationDisplay";
		return "team2/employInformationDisplay";
	}

	
	//ログアウト用
	 @GetMapping("/logout")
	 public String logout(Model model) {
	  
	     return "redirect:/login";
	 }
}
