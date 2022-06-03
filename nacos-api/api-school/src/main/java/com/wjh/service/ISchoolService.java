package com.wjh.service;

import java.util.List;

import com.wjh.school.entity.SchoolEntity;
import com.wjh.school.request.SchoolPageRequest;

/**
 * 学校Service接口类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
public interface ISchoolService {

    void save(SchoolEntity info);

    void update(SchoolEntity info);

    void delete(String schoolGuid);

    SchoolEntity detail(String schoolGuid);

    List<SchoolEntity> findAll();

    List<SchoolEntity> page(SchoolPageRequest request);
}
