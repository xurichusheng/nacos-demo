package com.wjh.stu.request;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 保存学生信息请求参数
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SaveStudentRequest implements Serializable {

    private static final long serialVersionUID = -2810477673039457146L;

    private Long id;
    private String studentGuid;
    @NotBlank(message = "请选择学校")
    private String schoolGuid;
    private String studentCode;
    @NotBlank(message = "学生姓名不能为空")
    private String studentName;
    /** 性别（1:男, 2:女） */
    @NotNull(message = "请选择性别")
    private Integer gender;
    @Min(value = 1, message = "年龄最小为1")
    @Max(value = 60, message = "年龄最大为60")
    private Integer age;
}
