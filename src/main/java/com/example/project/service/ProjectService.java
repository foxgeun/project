package com.example.project.service;

import java.util.List;

import com.example.project.dto.Project;

public interface ProjectService {

	
	public List<Project> getList() throws Exception;
	
	public void insertData(Project project) throws Exception;
}
