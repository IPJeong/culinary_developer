package spring.mvc.board_mybatis.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.board_mybatis.dao.BoardDAO;
import spring.mvc.board_mybatis.dto.BoardDTO;

@Service("contentFormHandler")
public class ContentFormHandler implements CommandHandler {

	@Autowired
	BoardDAO dao;
	
	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		// 상세페이지 가져오기
		BoardDTO dto = dao.getArticle(num);
		
		// 내가 쓴 글이 아닌것만 조회수 증가
//		if(!req.getRemoteAddr().equals(dto.getIp())) {
			dao.addReadCnt(num);
//		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number); 	/* db 글번호가 아닌 현재 넘버링한 글번호 */
		
		return "/board/contentForm";
	}
	
}
