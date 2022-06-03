package com.wjh.tea.entity;

import com.wjh.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 教师实体类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class TeacherEntity extends BaseEntity {

	private static final long serialVersionUID = -3992360294004199741L;

	private String teacherGuid;
	private String schoolGuid;
	private String teacherCode;
	private String teacherName;
	private Integer gender;
	private Integer age;
}
