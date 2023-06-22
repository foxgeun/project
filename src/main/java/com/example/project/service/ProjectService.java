package com.example.project.service;

import java.util.List;

import com.example.project.dto.Project;

public interface ProjectService {

	
	public List<Project> getList(int start,int end) throws Exception;
	
	public void insertData(Project project) throws Exception;
	
	public Project getReadData(int num) throws Exception;
	
	public void updateData(Project project) throws Exception;
	
	public int maxNum() throws Exception;
	
	public void deleteData(int num) throws Exception;
	
	public int getDataCount() throws Exception;
	
	public void minusData(int num) throws Exception;
}
