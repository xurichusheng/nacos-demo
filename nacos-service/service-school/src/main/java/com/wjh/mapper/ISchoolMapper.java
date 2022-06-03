package com.wjh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wjh.school.entity.SchoolEntity;
import com.wjh.school.request.SchoolPageRequest;

/**
 * 教师Mapper接口类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Mapper
public interface ISchoolMapper {

	int save(SchoolEntity entity);

	int batchSave(List<SchoolEntity> list);

	int update(SchoolEntity entity);

	int delete(String schoolGuid);

	SchoolEntity detail(String schoolGuid);

	List<SchoolEntity> findAll();

	Integer count(SchoolPageRequest request);

	List<SchoolEntity> page(@Param("req") SchoolPageRequest request, @Param("offset") int offset,
			@Param("pageSize") int pageSize);
}
