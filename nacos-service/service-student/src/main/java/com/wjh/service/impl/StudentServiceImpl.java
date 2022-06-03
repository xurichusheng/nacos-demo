package com.wjh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wjh.IStudentService;
import com.wjh.mapper.IStudentMapper;
import com.wjh.stu.entity.StudentEntity;
import com.wjh.stu.request.StudentPageRequest;
import com.wjh.util.CommonUtils;
import com.wjh.util.Constants;
import com.wjh.util.SnowFlakeIdWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * 学生Service实现类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentMapper studentMapper;

    /** 雪花算法 */
    @Autowired
    private SnowFlakeIdWorker snowFlakeIdWorker;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(StudentEntity stu) {
        if (stu == null) {
            log.info("保存学生信息，数据为空");
            return;
        }
        int ret = 0;

        if (StringUtils.isBlank(stu.getStudentGuid())) {
            /* 新增 */
            stu.setStudentGuid(CommonUtils.getMeUUID());
            stu.setStudentCode(snowFlakeIdWorker.nextId() + "");

            ret = studentMapper.save(stu);
        } else {
            /* 修改 */
            if (stu.getId() == null || stu.getId().intValue() <= 0) {
                log.info("修改学生信息，id为空.学生信息:{}", JSON.toJSONString(stu));
                throw new RuntimeException("学生id不能为空");
            }
            ret = studentMapper.update(stu);
        }
        if (ret == 0) {
            log.error("保存学生信息-失败.学生信息:{}", JSON.toJSONString(stu));
            throw new RuntimeException("保存学生信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(StudentEntity stu) {
        if (stu == null) {
            log.info("更新学生信息，数据为空");
            return;
        }
        if (stu.getId() == null || stu.getId().longValue() <= 0) {
            log.info("修改学生信息，id为空.学生信息:{}", JSON.toJSONString(stu));
            throw new RuntimeException("学生id不能为空");
        }
        if (StringUtils.isBlank(stu.getStudentGuid())) {
            log.info("更新学生信息，学生Guid为空");
            return;
        }
        int ret = studentMapper.update(stu);

        if (ret == 0) {
            log.error("更新学生信息-失败.学生信息:{}", JSON.toJSONString(stu));
            throw new RuntimeException("更新学生信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String studentGuid) {
        if (StringUtils.isBlank(studentGuid)) {
            log.info("删除学生信息，id为空");
            return;
        }
        int ret = studentMapper.delete(studentGuid);

        if (ret == 0) {
            log.error("删除学生信息-失败.学生Guid:{}", JSON.toJSONString(studentGuid));
            throw new RuntimeException("删除学生信息失败");
        }
    }

    @Override
    public StudentEntity detail(String studentGuid) {
        return StringUtils.isBlank(studentGuid) ? null : studentMapper.detail(studentGuid);
    }

    @Override
    public List<StudentEntity> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public List<StudentEntity> page(StudentPageRequest request) {
        if (request == null) {
            request = new StudentPageRequest();
        }
        if (request.getPageNum() == null || request.getPageNum().intValue() <= 0) {
            request.setPageNum(Constants.DEF_PAGE_NUM);
        }
        if (request.getPageSize() == null || request.getPageSize().intValue() <= 0) {
            request.setPageSize(Constants.DEF_PAGE_SIZE);
        }
        String beginDate = request.getBeginDate();
        String endDate = request.getEndDate();

        if (StringUtils.isNotBlank(beginDate) && beginDate.length() == Constants.DATE_LENGTH) {
            request.setBeginDate(beginDate + " 00:00:00");
        }
        if (StringUtils.isNotBlank(endDate) && endDate.length() <= Constants.DATE_LENGTH) {
            request.setEndDate(endDate + " 23:59:59");
        }

        Integer count = studentMapper.count(request);

        List<StudentEntity> list = null;

        if (count != null && count.intValue() > 0) {
            int offset = (request.getPageNum() - 1) * request.getPageSize();
            list = studentMapper.page(request, offset, request.getPageSize());
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    @Override
    public List<StudentEntity> queryBySchool(String schoolGuid) {
        Map<String, Object> params = new HashMap<>(1);
        params.put("shoolGuid", schoolGuid);

        return studentMapper.queryByMap(params);
    }
}
