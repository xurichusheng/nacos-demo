package com.wjh.stu.entity;

import com.wjh.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 学生实体类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class StudentEntity extends BaseEntity {

	private static final long serialVersionUID = 3025599074749143141L;

	private String studentGuid;
	private String schoolGuid;
	private String studentCode;
	private String studentName;
	/** 性别（1:男, 2:女） */
	private Integer gender;
	private Integer age;
}
