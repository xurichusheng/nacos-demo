package com.wjh.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wjh.ResultVO;
import com.wjh.clients.impl.StudentClientImpl;

/**
 * 调用学生服务接口
 * 
 * @author wenjianhai
 * @date 2022/6/3
 * @since JDK 1.8
 */
@Component
@FeignClient(name = "service-student", fallbackFactory = StudentClientImpl.class)
public interface IStudentClient {
    @GetMapping("/stu/queryBySchool/{schoolGuid}")
    ResultVO queryBySchool(@PathVariable(value = "schoolGuid") String schoolGuid);
}
