package com.wjh.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 公共实体类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -7121169269559492234L;

	private Long id;
	/** 状态（0:禁用, 1:正常） */
	private Integer state;
	/** 创建时间 */
	private Date createTime;
	/** 创建人id */
	private String createrId;
	/** 创建人 */
	private String creater;
	/** 更新时间 */
	private Date updateTime;
	/** 更新人id */
	private String updaterId;
	/** 更新人人 */
	private String updater;
	/** 备注 */
	private String bak;
}
