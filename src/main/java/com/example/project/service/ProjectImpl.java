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
	public List<Project> getList() throws Exception {
		return projectmapper.getList();
	}


	@Override
	public void insertData(Project project) throws Exception {
		projectmapper.insertData(project);
	}
	
	

}
