package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardServiceTest.class);
	
	private IboardService boardService;
	
	@Before
	public void setup(){
		boardService = new BoardService();
	}
	
	@Test
	public void showBoardListTest() {
		/***Given***/

		/***When***/
		List<BoardVO> boardVo = boardService.showBoardList();
		
		/***Then***/
		assertEquals("취미게시판", boardVo.get(2).getBoard_name());
		
	}

}
