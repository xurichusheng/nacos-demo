package com.wjh.handlers;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wjh.ResultVO;
import com.wjh.enums.ResultCodeEnums;
import com.wjh.util.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求参数异常拦截
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Slf4j
@RestControllerAdvice
public class RequestExceptionHandler {
	/**
	 * 参数合法性校验异常
	 *
	 * @param exception
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(BindException.class)
	public ResultVO validationBodyException(BindException exception) {

		return this.getExceptionMsg(exception.getBindingResult());
	}

	/**
	 * 参数合法性校验异常
	 *
	 * @param exception
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResultVO validationBodyException(MethodArgumentNotValidException exception) {

		return this.getExceptionMsg(exception.getBindingResult());
	}

	private ResultVO getExceptionMsg(BindingResult result) {

		StringBuffer buffer = new StringBuffer();

		if (result != null && result.hasErrors()) {

			List<ObjectError> errors = result.getAllErrors();

			errors.forEach(p -> {

				FieldError fieldError = (FieldError) p;
				log.error("Data check failure : object{" + fieldError.getObjectName() + "},field{"
						+ fieldError.getField() + "},errorMessage{" + fieldError.getDefaultMessage() + "}");
				buffer.append(fieldError.getDefaultMessage()).append(Constants.COMMA);
			});
		}
		String msg = StringUtils.isBlank(buffer.toString()) ? ResultCodeEnums.ERROR.getMessage()
				: buffer.toString().substring(0, buffer.toString().length() - 1);

		ResultVO resultVo = new ResultVO();
		resultVo.setCode(ResultCodeEnums.ERROR.getCode());
		resultVo.setMsg(msg);

		return resultVo;
	}
}
