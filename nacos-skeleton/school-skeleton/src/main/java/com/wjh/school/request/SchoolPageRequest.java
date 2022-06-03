package com.wjh.school.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页查询学校请求参数
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SchoolPageRequest implements Serializable {

    private static final long serialVersionUID = 8819505466181059675L;

    private String schoolCode;
    private String schoolName;
    private String shortName;
    private String address;
    private String beginDate;
    private String endDate;
    private Integer pageNum;
    private Integer pageSize;
}
