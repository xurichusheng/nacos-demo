package com.wjh.controller;

import com.wjh.enums.ResultCodeEnums;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.wjh.IStudentService;
import com.wjh.ResultVO;
import com.wjh.stu.entity.StudentEntity;
import com.wjh.stu.request.SaveStudentRequest;
import com.wjh.util.JsonUtils;
import com.wjh.util.ResultUtils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @PostMapping("/save")
    public ResultVO save(@Validated @RequestBody SaveStudentRequest request) {
        log.info("保存学生信息-开始，请求参数:{}", JSON.toJSONString(request));
        StudentEntity stu = JsonUtils.parse(JsonUtils.toJSONBytes(request), StudentEntity.class);
        studentService.save(stu);
        return ResultUtils.success();
    }

    @GetMapping("/queryBySchool/{schoolGuid}")
    public ResultVO<List<StudentEntity>> queryBySchool(@PathVariable String schoolGuid) {
        log.info("查询学生信息-开始，学校Guid:{}", schoolGuid);
        if (StringUtils.isBlank(schoolGuid)) {
            return ResultUtils.error(ResultCodeEnums.ERROR.getCode(), "请选择学校");
        }
        return ResultUtils.success(studentService.queryBySchool(schoolGuid));
    }
}
