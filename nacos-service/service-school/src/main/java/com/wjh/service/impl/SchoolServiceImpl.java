package com.wjh.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.wjh.mapper.ISchoolMapper;
import com.wjh.school.entity.SchoolEntity;
import com.wjh.school.request.SchoolPageRequest;
import com.wjh.service.ISchoolService;
import com.wjh.util.CommonUtils;
import com.wjh.util.Constants;
import com.wjh.util.SnowFlakeIdWorker;

import lombok.extern.slf4j.Slf4j;

/**
 * 学校Service实现类
 * 
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
@Slf4j
@Service
public class SchoolServiceImpl implements ISchoolService {

	@Autowired
	private ISchoolMapper schoolMapper;

	/** 雪花算法 */
	@Autowired
	private SnowFlakeIdWorker snowFlakeIdWorker;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SchoolEntity info) {
		if (info == null) {
			log.info("保存学校信息，数据为空");
			return;
		}
		int ret = 0;

		if (StringUtils.isBlank(info.getSchoolGuid())) {
			/* 新增 */
			info.setSchoolGuid(CommonUtils.getMeUUID());
			info.setSchoolCode(snowFlakeIdWorker.nextId() + "");

			ret = schoolMapper.save(info);
		} else {
			/* 修改 */
			if (info.getId() == null || info.getId().intValue() <= 0) {
				log.info("修改学校信息，id为空.学校信息:{}", JSON.toJSONString(info));
				throw new RuntimeException("学校id不能为空");
			}
			ret = schoolMapper.update(info);
		}
		if (ret == 0) {
			log.error("保存学校信息-失败.{}", JSON.toJSONString(info));
			throw new RuntimeException("保存学校信息失败");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SchoolEntity info) {
		if (info == null) {
			log.info("修改学校信息，数据为空");
			return;
		}
		if (info.getId() == null || info.getId().longValue() <= 0) {
			log.info("修改学校信息，id为空.学校信息:{}", JSON.toJSONString(info));
			throw new RuntimeException("学校id不能为空");
		}
		if (StringUtils.isBlank(info.getSchoolGuid())) {
			log.info("修改学校信息，学校Guid为空");
			return;
		}
		int ret = schoolMapper.update(info);

		if (ret == 0) {
			log.error("修改学校信息-失败.{}", JSON.toJSONString(info));
			throw new RuntimeException("修改学校信息失败");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String schoolGuid) {
		if (StringUtils.isBlank(schoolGuid)) {
			log.info("删除学校信息，学校Guid为空");
			return;
		}
		int ret = schoolMapper.delete(schoolGuid);

		if (ret == 0) {
			log.error("删除学校信息-失败.学校Guid:{}", JSON.toJSONString(schoolGuid));
			throw new RuntimeException("删除学校信息失败");
		}
	}

	@Override
	public SchoolEntity detail(String schoolGuid) {
		return StringUtils.isBlank(schoolGuid) ? null : schoolMapper.detail(schoolGuid);
	}

	@Override
	public List<SchoolEntity> findAll() {
		return schoolMapper.findAll();
	}

	@Override
	public List<SchoolEntity> page(SchoolPageRequest request) {
		if (request == null) {
			request = new SchoolPageRequest();
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
		Integer count = schoolMapper.count(request);

		List<SchoolEntity> list = null;

		if (count != null && count.intValue() > 0) {
			int offset = (request.getPageNum() - 1) * request.getPageSize();
			list = schoolMapper.page(request, offset, request.getPageSize());
		}
		if (list == null) {
			list = new ArrayList<>();
		}
		return list;
	}
}
