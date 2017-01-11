package spring.mvc.board_mybatis.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.board_mybatis.dao.BoardDAO;
import spring.mvc.board_mybatis.dto.BoardDTO;


@Service("listHandler")
public class ListHandler implements CommandHandler{

	@Autowired
	BoardDAO dao = null;
	
	@Override
	public String execute(Model model) {
		
		HttpServletRequest req = (HttpServletRequest)model.asMap().get("req");
		
		int pageSize = 5;		//	한 페이지당 출력한 글 개수
		int pageBlock = 5;		//	출력할 페이지 개수
		
		int cnt = 0;			// 글 개수
		int start = 0;			// 현재 페이지 시작번호 : rownum
		int end	= 0;			// 현재 페이지지 끝번호 : rownum
		int number = 0;			// 출력할 글 번호
		String pageNum = null;	// 페이지 번호
		int currentPage = 0;	// 현재 페이지
		
		int pageCount = 0;		// 페이지 개수
		int startPage = 0;		// 시작 페이지
		int endPage = 0;		// 마지막 페이지
		
		// 싱글톤, 다형성
		
		String item = req.getParameter("item");
		String keyword = req.getParameter("keyword");
		
		boolean search = (keyword != null);
		
		// 글개수 구하기
		
		if(search) {
			cnt = dao.getSearchCount(item, keyword);
		} else {
			cnt = dao.getCount();
		}
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";		// 첫페이지를 1페이지로 설정
		}
		
		currentPage = Integer.parseInt(pageNum);
		pageCount = ( cnt / pageSize ) + (cnt % pageSize > 0 ? 1 : 0);
		
		start = (currentPage -1) * pageSize + 1;	// (5-1) * 10 +1;
		end = start + pageSize -1; // 41 + 10 -1 = 50;
		
		if(end > cnt) end = cnt;
		
		// 글번호
		number = cnt - (currentPage -1) * pageSize;
		List<BoardDTO> dtos = null;
		if(cnt > 0) {
			if(search){
				dtos = dao.searchArticles(item, keyword, start, end);
			} else {
				Map<String, Integer> map = new HashMap<>();
				map.put("start", start);
				map.put("end", end);
				dtos = dao.getArticles(map);
			}
		}
		
		req.setAttribute("dtos", dtos);
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;	// (5 / 3) * 3 + 1 = 4
		if(currentPage % pageBlock == 0) startPage -= pageBlock;	//(5 % 3)
		
		endPage = startPage + pageBlock - 1;	// 4 + 3 - 1 = 6;
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		if(cnt > 0) {
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("pageBlock", pageBlock);
			
		}
		if(search) {
			model.addAttribute("item", item);
			model.addAttribute("keyword", keyword);
		}
		
		return "/board/list";
	}
	
}
