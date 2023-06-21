package com.example.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.project.dto.Project;

@Mapper
public interface ProjectDao {
	
	
	
	public List<Project> getList() throws Exception;
	
	public void insertData(Project project) throws Exception;
}
