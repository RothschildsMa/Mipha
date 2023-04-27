package com.ssm.framework.form;

import java.time.LocalDate;

import com.ssm.framework.validator.DateRange;

import lombok.Data;

@Data
public class AddForm {
	//社員ID
	private String employeeId;

	//パスワード
	private String password;
	
	//private String joinDate;
	
	//入社年月日
	@DateRange(startDate = "2023-01-01", endDate = "2023-12-31")
	private LocalDate joinDate;

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

	private String updateDate;

	private String updateUser;

	private String createUser;
	
}
