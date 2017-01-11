package spring.mvc.board_mybatis.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("writeFormHandler")
public class WriteFormHandler implements CommandHandler{

	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		// 제목글(답변글이 아닌경우)
		int num = 0;
		int ref = 1;		// 그룹화 아이디
		int ref_step = 0;	// 글순서
		int ref_level = 0;	// 글레벨
		
		// 답변글
		if(req.getParameter("num") != null) {
			num = Integer.parseInt(req.getParameter("num"));
			ref = Integer.parseInt(req.getParameter("ref"));
			ref_step = Integer.parseInt(req.getParameter("ref_step"));
			ref_level = Integer.parseInt(req.getParameter("ref_level"));
		}
		
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("ref_step", ref_step);
		model.addAttribute("ref_level", ref_level);
		
		return "/board/writeForm";
	}
}
