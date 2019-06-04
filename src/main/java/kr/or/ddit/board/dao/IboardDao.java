package kr.or.ddit.board.dao;

import java.util.List;

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
}
