package spring.mvc.board_mybatis.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.board_mybatis.dao.BoardDAO;


@Service("deleteProHandler")
public class DeleteProHandler implements CommandHandler{

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
		map.put("passwd", passwd);
		
		// num과 일치할 경우 비밀번호 일치하는지 확인
		int selectCnt = dao.pwdCheck(map);
		
		/*
		 *	deleteCnt = -1 : 답글이 있는 경우 삭제 안함
		 *	deleteCnt = 0  : 답글이 없는 경우 삭제 실패
		 *	deleteCnt = 1  : 답글이 없는 경우 삭제 성공
		 */
		if(selectCnt != 0) {
			int deleteCnt = dao.delete(num);
			req.setAttribute("deleteCnt", deleteCnt);
			model.addAttribute("deleteCnt", deleteCnt);
		}
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
		
		return "/board/deletePro";
	}
}
