package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.project.dto.Project;
import com.example.project.service.ProjectService;
import com.example.project.util.ProjectUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProjectController {
	
	@Autowired
	private ProjectService projectservice;
	
	@Autowired
	ProjectUtil projectUtil;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	// Get 방식으로 Request 들어올 때
	@RequestMapping(value = "/updated", method = RequestMethod.GET)
	public String updated(HttpServletRequest request, Model model) {
		
		
		try {
			
			
			int num = Integer.parseInt(request.getParameter("num"));
			Project project = projectservice.getReadData(num);
			String deleteURL = "deleted_ok";
			
			model.addAttribute("project", project);
			model.addAttribute("deleteURL",deleteURL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "bbs/updated";
		}
	
	@RequestMapping(value = "/updated_ok", method = RequestMethod.POST)
	public String updated_ok(Project project , HttpServletRequest request, Model model) {
	
		try {
			
			
			projectservice.updateData(project);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	return "redirect:/list";
	}
	
	
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Project ptoject, HttpServletRequest request, Model model) {
	
		
		try {
			
			
			String pageNum = request.getParameter("pageNum");
			int currentPage = 1;
			if(pageNum != null) currentPage = Integer.parseInt(pageNum);
			int dataCount = projectservice.getDataCount();
			int numPerPage = 5; // 페이지당 보여둘 게시글의 갯수(5개)
			int totalPage = projectUtil.getPageCount(numPerPage, dataCount); // 페이지의 전체 갯수
			if(currentPage > totalPage) currentPage = totalPage; // totalPage 보다 크면 안됨
			
			int start = (currentPage -1) * numPerPage + 1; // 1, 6, 11, 16 ....
			int end = currentPage * numPerPage; // 5, 10, 15, 20 ....
			
			
			List<Project> lists = projectservice.getList(start, end);
			String listUrl = "/list";
			
			String param = "";
			
			String pageIndexList = projectUtil.pageIndexList(currentPage, totalPage, listUrl);
			
			String articleUrl = "/article?pageNum=" + currentPage;
			
			
			
			String updatedURL = "updated";
			
			
			
			model.addAttribute("lists", lists);
			model.addAttribute("updatedURL", updatedURL);
			model.addAttribute("pageIndexList", pageIndexList); // ◀이전 6 7 8 9 10 다음▶
			model.addAttribute("dataCount", dataCount); // 전체 게시물의 갯수
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "bbs/list";		
	}
	
	@RequestMapping(value = "/room", method = RequestMethod.GET)
	public String room() {
		return "bbs/room";
	}
	
	
	@RequestMapping(value = "/created", method = RequestMethod.GET)
	public String created() {
		return "bbs/created";
	}
	
	// 게시물 등록(Post로 Request 들어올 때)
		@RequestMapping(value = "/created", method = RequestMethod.POST)
		public String createdOk(Project project, HttpServletRequest request, Model model) {
			try {
				int max = projectservice.maxNum();
				project.setNum(max+1);
				projectservice.insertData(project);
				
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage","게시글 작성 중 에러가 발생했습니다.");
				return "bbs/created";
			}
			
			return "redirect:/list";
		}
		
		@RequestMapping(value = "/deleted_ok", method= {RequestMethod.GET})
		public String deleteOK(HttpServletRequest request, Model model) {
		
			try {
				
				int num = Integer.parseInt(request.getParameter("num"));
				projectservice.deleteData(num);
				projectservice.minusData(num);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/list";
		}
		
		
		
		
		
	
	
	
	
}
