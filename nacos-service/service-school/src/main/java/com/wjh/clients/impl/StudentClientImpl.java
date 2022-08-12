package com.wjh.clients.impl;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.wjh.ResultVO;
import com.wjh.clients.IStudentClient;
import com.wjh.enums.ResultCodeEnums;
import com.wjh.util.ResultUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 学生服务容错/熔断处理
 * @author wenjianhai
 * @date 2022/6/3
 * @since JDK 1.8
 */
@Slf4j
@Component
public class StudentClientImpl implements FallbackFactory<IStudentClient> {
    @Override
    public IStudentClient create(Throwable cause) {
        return new IStudentClient() {
            @Override
            public ResultVO queryBySchool(String schoolGuid) {
                log.error("根据学校Guid查询学生信息-失败.学校Guid:{}, 失败信息:{}", schoolGuid, cause.getMessage());
                return ResultUtils.error(ResultCodeEnums.ERROR.getCode(), "查询学生信息失败");
            }
        };
    }
}
