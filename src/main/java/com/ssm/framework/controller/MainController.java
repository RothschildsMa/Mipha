package com.ssm.framework.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	//DI(依存性注入)　spring 根幹※
	//7
	@GetMapping("view2") //URLマッピング
	public String start() { //リクエスト　ハンドラ　メソッド
		return "team2/input";
	}
	
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	@GetMapping("akimi") //URLマッピング
	public String restart() { //リクエスト　ハンドラ　メソッド
		return "team2/akimi";
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