package com.ssm.framework.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Employee implements Serializable{
	
	//自動採番ID
	private Long id;
	
	//社員ID
	private String employeeId;
	
	//パスワード
	private String password;
	
	//入社年月日
	private Date joinDate;
	
	//社員名称漢字
	private String employeeName;
	
	//社員名称カナ
	private String employeeNameKana;
	
	//性別ID
	private String employeeGenderId;
	
	//年齢
	private int employeeAge;
	
	//所属ID
	private String employeeDepartmentId;
	
	//電話番号
	private String employeePhoneNumber;
	
	//社員メールアドレス
	private String employeeMail;

	//削除フラグ
	private int delFlg;

	//作成日時
	private Date createDate;

	//作成者id
	private int createUser;

	//更新日時
	private Date updateDate;

	//更新者id
	private int updateUser;

}
