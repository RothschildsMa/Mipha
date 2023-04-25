package com.ssm.framework.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MCode implements Serializable{
	
	private String codeKbn;
	
	private String codeId;
	
	private String codeName;
	
	private int delFlg;
	
	private Date createDate;
	
	private String createUser;
	
	private Date updateDate;
	
	private String updateUser;
}
