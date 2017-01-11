package spring.mvc.board_mybatis.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.board_mybatis.dto.BoardDTO;


@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	SqlSession sqlSession;
	
	DataSource dataSource;
	
	
	@Override
	public int getCount() {
		
		// 방법1
//		int cnt = this.sqlSession.selectOne("spring.mvc.board_mybatis.dao.BoardDAO.getCount");
		int cnt = 0;
		// 방법2
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		cnt = dao.getCount();
		
		return cnt;
		
	}

	
	@Override
	public int getSearchCount(String item, String keyword) {
		
//		int cnt = 0;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = null;
//			
//			if(item.equals("subject")) {
//				sql = "SELECT COUNT(*) FROM mvc_board WHERE subject LIKE '%'||?||'%'";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//			} else if(item.equals("content")) {
//				sql = "SELECT COUNT(*) FROM mvc_board WHERE content LIKE '%'||?||'%'";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//			} else if(item.equals("writer")) {
//				sql = "SELECT COUNT(*) FROM mvc_board WHERE writer LIKE '%'||?||'%'";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//			} else if(item.equals("sub+con")) {
//				sql = "SELECT COUNT(*) FROM mvc_board WHERE subject LIKE '%'||?||'%' OR content LIKE '%'||?||'%'";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//				pstmt.setString(2, keyword);
//			}
//			
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				cnt = rs.getInt(1);
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn!=null)conn.close();
//				if(pstmt!=null)pstmt.close();
//				if(rs!=null)rs.close();
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
		return 0;
	}
	
	// 상세조회, 수정내역
	@Override
	public List<BoardDTO> getArticles(Map<String, Integer> map) {
		
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		List<BoardDTO> dtos = dao.getArticles(map);
		return dtos;
	}
	
	@Override
	public BoardDTO getArticle(int num) {
		
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		BoardDTO dto = dao.getArticle(num);
		
		return dto;
	}

	@Override
	public void addReadCnt(int num) {

		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		dao.addReadCnt(num);
		
	}

	
	
	//비밀번호 확인(게시글 수정, 게시글 삭제)
	@Override
	public int pwdCheck(Map<String, Object> map) {
		
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		int cnt = dao.pwdCheck(map);
		return cnt;
	}
	
	// 글수정
	@Override
	public int update(BoardDTO dto) {
		
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		int cnt = dao.update(dto);
		
		return cnt;
	}

	@Override
	public int insert(BoardDTO dto) {
		
		int num = dto.getNum();
		int ref = dto.getRef();
		int ref_level = dto.getRef_level();
		int ref_step = dto.getRef_step();
		int count = 0;
		
		if(num == 0) {
			
			count = getCount();
			
			// 첫글이 아닌 경우
			if(count > 0) {
				
				/*  
				    board_seq.nextval
				  	board_seq.currval
				  	ref = 글번호 최대값 + 1 
				 */
				 
				int maxNum = getMaxNum();
				ref = maxNum + 1;
			// 첫글인 경우
			} else {
				ref = 1;
			}
			dto.setRef(ref);
			ref_step = 0;
			ref_level = 0;
		
		// 답변글인 경우	
		} else {
			updateReply(dto);
			ref_step++;
			ref_level++;
		}
		
		dto.setRef_step(ref_step);
		dto.setRef_level(ref_level);
		
		// 글작성
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		
		int cnt = dao.insert(dto);
	
		return cnt;
	}
	
	@Override
	public int getMaxNum() {
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		int num = dao.getMaxNum();
		return num;
	}
	
	@Override
	public void updateReply(BoardDTO dto) {
		
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		dao.updateReply(dto);
		
	}

	// 게시글 삭제
	@Override
	public int delete(int num) {
		
		int cnt = 0;
		
		// 상세조회
		BoardDTO dto = getArticle(num);
		
		// 답글이 존재하는지 여부.
		int checkReply = checkReply(dto);
		
		// 답글이 없는 경우에만 삭제처리
		if(checkReply != 0) {
			// 답글이 있는 경우 삭제하지 않겠다.
			cnt = -1;
		} else {
			// 답글이 없는 경우 Ref_step 수정
			updateRef_step(dto);
			// 답글이 없는 경우 글삭제
			BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
			cnt = dao.delete(num);
		}
		
		return cnt;
	}

	// 답글이 존재하는지 여부
	public int checkReply(BoardDTO dto) {
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		int cnt = dao.checkReply(dto);
		
		return cnt;
	}

	// 답글이 없는 경우 Ref_step 수정
	@Override
	public void updateRef_step(BoardDTO dto) {
		BoardDAO dao = this.sqlSession.getMapper(BoardDAO.class);
		dao.updateRef_step(dto);
	}

	@Override
	public List<BoardDTO> searchArticles(String item, String keyword, int start, int end) {
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<BoardDTO> dtos = new ArrayList<BoardDTO>();
//		
//		try {
//			conn = dataSource.getConnection();
//			String sql = null;
//			
//			System.out.println("item : " + item + ", keyword : " + keyword);
//			
//			// 제목
//			if(item.equals("subject")) {
//				System.out.println("1번 실행");
//				sql = "SELECT * " + 
//					  " FROM (SELECT num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip, rownum rn " +
//						      " FROM mvc_board " + 
//						     " WHERE subject LIKE '%'|| ? ||'%') " +
//					 " WHERE rn BETWEEN ? and ?";   
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//				pstmt.setInt(2, start);
//				pstmt.setInt(3, end);
//				
//			// 내용	
//			} else if(item.equals("content")) {
//				System.out.println("2번 실행");
//				sql =  "SELECT * " + 
//					    " FROM (SELECT num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip, rownum rn " +
//					    	    " FROM mvc_board " + 
//					    	   " WHERE content LIKE '%'|| ? ||'%') " +
//					   " WHERE rn BETWEEN ? and ?";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//				pstmt.setInt(2, start);
//				pstmt.setInt(3, end);
//			
//			// 작성자
//			} else if(item.equals("writer")) {
//				System.out.println("3번 실행");
//				sql =  "SELECT * " + 
//					    " FROM (SELECT num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip, rownum rn " +
//					    	    " FROM mvc_board " + 
//					    	   " WHERE writer LIKE '%'|| ? ||'%') " +
//					   " WHERE rn BETWEEN ? and ?";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//				pstmt.setInt(2, start);
//				pstmt.setInt(3, end);
//			
//			// 제목 + 내용
//			} else if(item.equals("sub+con")) {
//				System.out.println("4번 실행");
//				sql =  "SELECT * " + 
//					    " FROM (SELECT num, writer, passwd, subject, content, readCnt, ref, ref_step, ref_level, reg_date, ip, rownum rn " +
//					    	    " FROM mvc_board " + 
//					    	   " WHERE subject LIKE '%'|| ? ||'%' OR content LIKE '%'|| ? ||'%' ) " +
//					   " WHERE rn BETWEEN ? and ?";
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, keyword);
//				pstmt.setString(2, keyword);
//				pstmt.setInt(3, start);
//				pstmt.setInt(4, end);
//			}
//			
//			rs = pstmt.executeQuery();
//			
//			BoardDTO dto;
//			
//			while(rs.next()){
//				dto = new BoardDTO();
//				dto.setNum(rs.getInt(1));
//				dto.setWriter(rs.getString(2));
//				dto.setPasswd(rs.getString(3));
//				dto.setSubject(rs.getString(4));
//				dto.setContent(rs.getString(5));
//				dto.setReadCnt(rs.getInt(6));
//				dto.setRef(rs.getInt(7));
//				dto.setRef_step(rs.getInt(8));
//				dto.setRef_level(rs.getInt(9));
//				dto.setReg_date(rs.getTimestamp(10));
//				dto.setIp(rs.getString(11));
//				dtos.add(dto);
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(conn!=null)conn.close();
//				if(pstmt!=null)pstmt.close();
//			} catch(SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return dtos;
		return null;
	}
	
}
