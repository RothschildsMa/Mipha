package com.ssm.framework.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SearchParams implements Serializable{
	
	//社員ID
	private String employeeId;
	
	//所属ID
	private String employeeDepatmentId;
	
	//入社年月日
	private Date startDate;
	
	//入社年月日
	private Date endDate;
	


}
