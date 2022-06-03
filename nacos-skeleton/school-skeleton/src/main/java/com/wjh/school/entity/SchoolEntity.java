package com.wjh.school.entity;

import com.wjh.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 学校实体类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SchoolEntity extends BaseEntity {

    private static final long serialVersionUID = -2182456397124803176L;

    private String schoolGuid;
    private String schoolCode;
    private String schoolName;
    private String shortName;
    private String address;
}
