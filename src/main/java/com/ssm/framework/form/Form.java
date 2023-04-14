package com.ssm.framework.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class Form implements Serializable{

	//社員ID
	private String employeeId;
	
	//パスワード
	private String password;
	
	//入社年月日
	private String joinDate;
		
	//社員名称漢字
	private String employeeName;
		
	//社員名称カナ
	private String employeeNameKana;
		
	//性別ID
	private String employeeGenderId;
		
	//年齢
	private int employeeAge;
		
	//所属ID
	private String employeeDepatmentId;
		
	//電話番号
	private String employeePhoneNumber;
		
	//社員メールアドレス
	private String employeeMail;
	
}
