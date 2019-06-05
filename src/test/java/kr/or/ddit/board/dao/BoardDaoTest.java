package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.ArticleVO;
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
	
	@Test
	public void selectAllArticleTest(){
		/***Given***/
		String article_board = "b3";

		/***When***/
		List<ArticleVO> articleList = boardDao.selectAllArticle(article_board);

		/***Then***/
		assertEquals(3, articleList.size());
	}
	
	@Test
	public void boardCntTest(){
		/***Given***/
		String article_board = "b3";

		/***When***/
		int boardCnt = boardDao.boardCnt(article_board);

		/***Then***/
		assertEquals(12, boardCnt);
	}
	
	@Test
	public void articlePagingListTest(){
		/***Given***/
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("page", 1);
		searchMap.put("pageSize", 10);
		searchMap.put("boardId", "b3");

		/***When***/
		List<ArticleVO> articleList = boardDao.articlePagingList(searchMap);
		
		/***Then***/
		assertNotNull(articleList);
		assertEquals(10, articleList.size());
	}
}
