package spring.mvc.board_mybatis.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.board_mybatis.dao.BoardDAO;
import spring.mvc.board_mybatis.dto.BoardDTO;

@Service("modifyViewHandler")
public class ModifyViewHandler implements CommandHandler{

	@Autowired
	BoardDAO dao;
	
	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String passwd = req.getParameter("passwd");
	
		Map<String, Object> map = new HashMap<>();
		map.put("num", num);
		map.put("passwd",passwd);
		
		// 패스워드가 일치하면 selectCnt=1; 불일치시 selectCnt=0;
		int selectCnt = dao.pwdCheck(map);
		
		//패스워드가 일치하면
		if(selectCnt == 1) {
			BoardDTO dto = dao.getArticle(num);
			req.setAttribute("dto", dto);
		}
		System.out.println("selectCnt" + selectCnt);
		model.addAttribute("num", num);
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		
		return "/board/modifyView";
	}
}
