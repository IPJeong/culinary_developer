package spring.mvc.board_mybatis.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("modifyFormHandler")
public class ModifyFormHandler implements CommandHandler{

	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		
		return "/board/modifyForm";
	}
	
}
