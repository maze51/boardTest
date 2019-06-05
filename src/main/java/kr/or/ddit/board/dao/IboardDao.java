package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;

public interface IboardDao {
	
	/**
	 * 
	* Method : showBoardList
	* 작성자 : PC10
	* 변경이력 :
	* @return
	* Method 설명 : 현재 게시판 목록을 출력
	 */
	List<BoardVO> showBoardList();
	
	/**
	 * 
	* Method : selectAllArticle
	* 작성자 : PC10
	* 변경이력 :
	* @param articleBoard
	* @return
	* Method 설명 : 게시판아이디로 전체 게시글 목록을 출력
	 */
	List<ArticleVO> selectAllArticle(String article_board);
	
	/**
	 * 
	* Method : boardCnt
	* 작성자 : PC10
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 한 게시판의 전체 게시글수 조회
	 */
	int boardCnt(String boardId);
	
	/**
	 * 
	* Method : articlePagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param searchMap
	* @return
	* Method 설명 : 게시글 페이징 리스트 조회
	 */
	List<ArticleVO> articlePagingList(Map<String, Object> searchMap);
	
	/**
	 * 
	* Method : createBoard
	* 작성자 : PC10
	* 변경이력 :
	* @param cMap
	* @return
	* Method 설명 : 신규 게시판 생성
	 */
	int createBoard(Map<String, Object> cMap);
}
