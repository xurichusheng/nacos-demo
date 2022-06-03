package com.wjh.mapper;

import com.wjh.stu.entity.StudentEntity;
import com.wjh.stu.request.StudentPageRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 学生Mapper接口类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Mapper
public interface IStudentMapper {

	int save(StudentEntity entity);

	int batchSave(List<StudentEntity> list);

	int update(StudentEntity entity);

	int delete(String studentGuid);

	StudentEntity detail(String studentGuid);

	List<StudentEntity> findAll();

	Integer count(StudentPageRequest request);

	List<StudentEntity> page(@Param("req") StudentPageRequest request, @Param("offset") int offset,
			@Param("pageSize") int pageSize);

	List<StudentEntity> queryByMap(@Param("params") Map<String, Object> params);
}
