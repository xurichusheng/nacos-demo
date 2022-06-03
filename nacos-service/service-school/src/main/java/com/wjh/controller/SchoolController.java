package com.wjh.controller;

import java.util.List;

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
import com.wjh.ResultVO;
import com.wjh.clients.IStudentClient;
import com.wjh.enums.ResultCodeEnums;
import com.wjh.school.entity.SchoolEntity;
import com.wjh.school.request.SaveSchoolRequest;
import com.wjh.service.ISchoolService;
import com.wjh.stu.entity.StudentEntity;
import com.wjh.util.JsonUtils;
import com.wjh.util.ResultUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wenjianhai
 * @date 2022/6/3
 * @since JDK 1.8
 */
@Slf4j
@RestController
@RequestMapping("/sch")
public class SchoolController {

    @Autowired
    private ISchoolService schoolService;

    /** 学生服务接口 */
    @Autowired
    private IStudentClient studentClient;

    @PostMapping("/save")
    public ResultVO<?> save(@Validated @RequestBody SaveSchoolRequest request) {
        log.info("保存学校信息-开始，请求参数:{}", JSON.toJSONString(request));
        SchoolEntity sch = JsonUtils.parse(JsonUtils.toJSONBytes(request), SchoolEntity.class);
        schoolService.save(sch);
        return ResultUtils.success();
    }

    @GetMapping("/students/{schoolGuid}")
    public ResultVO<List<StudentEntity>> queryStudents(@PathVariable String schoolGuid) {
        log.info("查询学生信息-开始，学校Guid:{}", schoolGuid);
        if (StringUtils.isBlank(schoolGuid)) {
            return ResultUtils.error(ResultCodeEnums.ERROR.getCode(), "请选择学校");
        }
        ResultVO<List<StudentEntity>> result = studentClient.queryBySchool(schoolGuid);

        if (result == null || !ResultCodeEnums.SUCCESS.getCode().equals(result.getCode())) {
            log.error("查询学生信息-失败！学校Guid:{}, 结果:{}", schoolGuid, JSON.toJSONString(result));
            return ResultUtils.error(ResultCodeEnums.ERROR.getCode(), "查询学生信息失败！");
        }
        return result;
    }
}
