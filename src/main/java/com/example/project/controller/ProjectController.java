package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.project.dto.Project;
import com.example.project.service.ProjectService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectservice;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Project ptoject, HttpServletRequest request, Model model) {
	
		
		try {
			List<Project> lists = projectservice.getList();
			
			model.addAttribute("lists", lists);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "bbs/list";		
	}
	
	
	@RequestMapping(value = "/created", method = RequestMethod.GET)
	public String created() {
		return "bbs/created";
	}
	
	// 게시물 등록(Post로 Request 들어올 때)
		@RequestMapping(value = "/created", method = RequestMethod.POST)
		public String createdOk(Project project, HttpServletRequest request, Model model) {
			try {
			
				project.setNum(7);
				projectservice.insertData(project);
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage","게시글 작성 중 에러가 발생했습니다.");
				return "bbs/created";
			}
			
			return "redirect:/list";
		}
	
	
	
}
