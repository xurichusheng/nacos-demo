package com.wjh;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 接口返回值
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ResultVO<T> implements Serializable {

	private static final long serialVersionUID = 6354067728335497648L;

	/** 状态码 */
	private Integer code;
	/** 消息提示 */
	private String msg;
	/** 数据 */
	private T data;
}
