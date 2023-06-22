package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.dao.ProjectDao;
import com.example.project.dto.Project;

@Service
public class ProjectImpl implements ProjectService{
	
	@Autowired
	private ProjectDao projectmapper;
	
	
	@Override
	public List<Project> getList(int start,int end) throws Exception {
		return projectmapper.getList(start,end);
	}


	@Override
	public void insertData(Project project) throws Exception {
		projectmapper.insertData(project);
	}


	@Override
	public Project getReadData(int num) throws Exception {
		
		return projectmapper.getReadData(num);
	}


	@Override
	public void updateData(Project project) throws Exception {
		projectmapper.updateData(project);
		
	}


	@Override
	public int maxNum() throws Exception {
		return projectmapper.maxNum();
	}


	@Override
	public void deleteData(int num) throws Exception {
		projectmapper.deleteData(num);
	}


	@Override
	public int getDataCount() throws Exception {
		
		return projectmapper.getDataCount();
	}


	@Override
	public void minusData(int num) throws Exception {
		projectmapper.minusData(num);
		
	}
	
	


	
	
	
	
	

}
