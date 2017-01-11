package spring.mvc.board_mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.board_mybatis.handler.CommandHandler;
import spring.mvc.board_mybatis.handler.ContentFormHandler;
import spring.mvc.board_mybatis.handler.DeleteFormHandler;
import spring.mvc.board_mybatis.handler.DeleteProHandler;
import spring.mvc.board_mybatis.handler.ListHandler;
import spring.mvc.board_mybatis.handler.ModifyFormHandler;
import spring.mvc.board_mybatis.handler.ModifyProHandler;
import spring.mvc.board_mybatis.handler.ModifyViewHandler;
import spring.mvc.board_mybatis.handler.WriteFormHandler;
import spring.mvc.board_mybatis.handler.WriteProHandler;


@Controller
public class BFrontController {

	String viewPage = null;
	
	@Autowired
	ListHandler listHandler;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = listHandler.execute(model);
		return viewPage;
	}
	
	@Autowired
	WriteFormHandler writeFormhandler;
	
	@RequestMapping("/writeForm")
	public String writeForm(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = writeFormhandler.execute(model);
		return viewPage;
	}
	
	@Autowired
	WriteProHandler writeProHandler;
	
	@RequestMapping("/writePro")
	public String writePro(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = writeProHandler.execute(model);
		return viewPage;
	}
	@Autowired
	ContentFormHandler contentFormHandler;
	
	@RequestMapping("/contentForm")
	public String contentForm(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = contentFormHandler.execute(model);
		return viewPage;
	}
	
	@Autowired
	ModifyFormHandler modifyFormHandler;
	
	@RequestMapping("/modifyForm")
	public String modifyForm(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = modifyFormHandler.execute(model);
		return viewPage;
	}
	
	@Autowired
	ModifyViewHandler modifyViewHandler;
	
	@RequestMapping("/modifyView")
	public String modifyView(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = modifyViewHandler.execute(model);
		return viewPage;
	}
	
	@Autowired
	ModifyProHandler modifyProHandler;
	
	@RequestMapping("/modifyPro")
	public String modifyPro(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = modifyProHandler.execute(model);
		return viewPage;
	}
	
	@Autowired
	DeleteFormHandler deleteFormHandler;
	
	@RequestMapping("/deleteForm")
	public String deleteForm(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = deleteFormHandler.execute(model);
		return viewPage;
	}	
	
	@Autowired 
	DeleteProHandler deleteProHandler;
	
	@RequestMapping("/deletePro")
	public String deletePro(HttpServletRequest req, Model model) {
		model.addAttribute("req", req);
		viewPage = deleteProHandler.execute(model);
		return viewPage;
	}
		
	
}
