package spring.mvc.board_mybatis.handler;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.board_mybatis.dao.BoardDAO;
import spring.mvc.board_mybatis.dto.BoardDTO;

@Service("writeProHandler")
public class WriteProHandler implements CommandHandler{

	@Autowired
	BoardDAO dao;
	
	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		// 1. 작은바구니(=DTO(data transfer object))를 만든다.
		BoardDTO dto = new BoardDTO();
		
		// 2. 입력받은 내용을 작은바구니에 담는다.
		dto.setWriter(req.getParameter("writer"));
		dto.setSubject(req.getParameter("subject"));
		dto.setContent(req.getParameter("content"));
		dto.setPasswd(req.getParameter("passwd"));
		//hidden으로 넘겨받은 값
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setRef(Integer.parseInt(req.getParameter("ref")));
		dto.setRef_level(Integer.parseInt(req.getParameter("ref_level")));
		dto.setRef_step(Integer.parseInt(req.getParameter("ref_step")));
		dto.setReg_date(new Timestamp(System.currentTimeMillis()));
		dto.setIp(req.getRemoteAddr());
		
		int cnt = dao.insert(dto);
		System.out.println("cnt : " + cnt);
		// 5. setAttribute
		model.addAttribute("cnt", cnt);
		
		return "/board/writePro";
	}
}
