package com.wjh;

import com.wjh.stu.entity.StudentEntity;
import com.wjh.stu.request.StudentPageRequest;

import java.util.List;

/**
 * 学生Service接口类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
public interface IStudentService {

    void save(StudentEntity stu);

    void update(StudentEntity stu);

    void delete(String studentGuid);

    StudentEntity detail(String studentGuid);

    List<StudentEntity> findAll();

    List<StudentEntity> page(StudentPageRequest request);

    List<StudentEntity> queryBySchool(String schoolGuid);
}
