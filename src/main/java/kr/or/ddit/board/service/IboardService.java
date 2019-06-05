package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;

public interface IboardService {
	
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
	* Method : articlePagingList
	* 작성자 : PC10
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징 리스트 조회
	 */
	Map<String, Object> articlePagingList(Map<String, Object> searchMap);
	
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
