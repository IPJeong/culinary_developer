package spring.mvc.board_mybatis.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.board_mybatis.dao.BoardDAO;
import spring.mvc.board_mybatis.dto.BoardDTO;


@Service("modifyProHandler")
public class ModifyProHandler implements CommandHandler{

	@Autowired
	BoardDAO dao;
	
	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		// 1. 바구니 생성
		BoardDTO dto = new BoardDTO();
		
		// 2. 바구니에 화면으로 입력받은 값을 담는다.
		dto.setNum(num);
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPasswd(req.getParameter("passwd"));
		
		// 4. update
		int cnt = dao.update(dto);
		
		System.out.println("결과 : " + cnt);
		
		// 5. setAttribute
		model.addAttribute("cnt", cnt);
		model.addAttribute("pageNum", pageNum);
		
		return "/board/modifyPro";
	}
}
