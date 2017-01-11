package spring.mvc.board_mybatis.dao;

import java.util.List;
import java.util.Map;

import spring.mvc.board_mybatis.dto.BoardDTO;



public interface BoardDAO {

	// 커밋커밋
	
	public int getCount();	// 글개수 구하기
	public int getSearchCount(String item, String keyword);	// 검색된 글개수 구하기
	public List<BoardDTO> getArticles(Map<String, Integer> map);	// 게시글 목록
	public BoardDTO getArticle(int num);	// 상세조회, 수정내역
	public void addReadCnt(int num);	// 글 조회수 증가
	public int pwdCheck(Map<String, Object> map);	// 비밀번호 확인(게시글 수정, 게시글 삭제)
	public int update(BoardDTO dto);	// 글내용 수정
	public int insert(BoardDTO dto);	// 글작성
	public int getMaxNum();
	public void updateReply(BoardDTO dto);
	public int delete(int num);		// 글삭제
	
	// 추가
	public int checkReply(BoardDTO dto);
	// 추가
	public void updateRef_step(BoardDTO dto);
	
	public List<BoardDTO> searchArticles(String item, String keyword, int start, int end);	//글 검색
}
