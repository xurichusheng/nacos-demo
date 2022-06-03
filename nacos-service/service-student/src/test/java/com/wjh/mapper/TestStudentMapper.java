package com.wjh.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wjh.StudentApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StudentApplication.class)
public class TestStudentMapper {

	@Autowired
	private IStudentMapper studentMapper;

	@Test
	public void save() {
	}

	@Test
	public void batchSave() {
	}

	@Test
	public void update() {
	}

	@Test
	public void delete() {
	}

	@Test
	public void detail() {
	}

	@Test
	public void findAll() {
	}

	@Test
	public void count() {
	}

	@Test
	public void page() {
	}
}