package com.ssm.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.ssm.framework.entity.MCode;
@Mapper
public interface MCodeMapper {

	
	List<MCode> iFindDepatmentName();
	/*
	 * @Select 标记的方法 getAllMCodes()，
	 * 用于获取 mcode 表中的所有数据。
	 */
}
