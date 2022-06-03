package com.wjh.school.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 保存学校信息请求参数
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class SaveSchoolRequest implements Serializable {

	private static final long serialVersionUID = -2737551524442538906L;

	private Long id;
	private String schoolGuid;
	private String schoolCode;
	@NotBlank(message = "学校名称不能为空")
	private String schoolName;
	private String shortName;
	@NotBlank(message = "学校地址不能为空")
	private String address;
}
