package com.wjh.clients;

import com.wjh.ResultVO;
import com.wjh.clients.impl.StudentClientImpl;
import com.wjh.stu.entity.StudentEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    ResultVO<List<StudentEntity>> queryBySchool(@PathVariable(value = "schoolGuid") String schoolGuid);
}
