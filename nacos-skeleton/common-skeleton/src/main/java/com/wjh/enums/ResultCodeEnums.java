package com.wjh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnums {
    SUCCESS(200, "成功"),
    ERROR(300 , "失败")
    ;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态码信息
     */
    private String message;
}
