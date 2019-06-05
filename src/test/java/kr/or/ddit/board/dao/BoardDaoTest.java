package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoTest.class);
	
	private IboardDao boardDao;
	
	@Before
	public void setup(){
		boardDao = new BoardDao();
	}
	
	@Test
	public void showBoardListTest() {
		/***Given***/

		/***When***/
		List<BoardVO> boardList = boardDao.showBoardList();

		/***Then***/
		assertEquals(4, boardList.size());
		assertEquals("자유게시판", boardList.get(1).getBoard_name());
		assertEquals("1", boardList.get(0).getBoard_use());
	}

}
