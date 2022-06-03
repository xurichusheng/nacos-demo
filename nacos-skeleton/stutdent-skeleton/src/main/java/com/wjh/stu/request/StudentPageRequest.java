package com.wjh.stu.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页查询学生请求参数
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class StudentPageRequest implements Serializable {

	private static final long serialVersionUID = -6627760690775399350L;

	private String schoolGuid;
	private String studentCode;
	private String studentName;
	/** 性别（1:男, 2:女） */
	private Integer gender;
	private String beginDate;
	private String endDate;
	private Integer pageNum;
	private Integer pageSize;
}
