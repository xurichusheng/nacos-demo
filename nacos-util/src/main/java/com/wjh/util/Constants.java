package com.wjh.util;

import java.math.BigDecimal;

/**
 * 公共常量类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
public final class Constants {

	private Constants() {
	}

	/** 逗号 */
	public static final String COMMA = ",";

	/** 日期长度 */
	public static final int DATE_LENGTH = 10;
	/** 日期时间长度 */
	public static final int DATE_TIME_LENGTH = 19;
	/** 分页查询默认页数 */
	public static final int DEF_PAGE_NUM = 1;
	/** 分页查询每页默认数量 */
	public static final int DEF_PAGE_SIZE = 10;
	/** sql中 in 的最大数量 */
	public static final int MAX_NUM_OF_IN = 1000;

	/** 金额小数点位数 */
	public static final int AMOUNT_SCALE = 2;

	/** 100 */
	public static final BigDecimal PERCENT = new BigDecimal("100");

	/** 默认金额 */
	public static final BigDecimal DEFAULT_AMOUNT = new BigDecimal("0.00");
}
