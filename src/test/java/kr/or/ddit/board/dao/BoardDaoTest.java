package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;

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
	
	@Test
	public void createBoardTest(){
		/***Given***/
		Map<String, Object> cMap = new HashMap<String, Object>();
		cMap.put("userId", "ryan");
		cMap.put("boardName", "테스트게시판");
		cMap.put("useSelect", "1");

		/***When***/
		int insertCnt = boardDao.createBoard(cMap);

		/***Then***/
		assertEquals(1, insertCnt);
		
		//boardDao.deleteBoard();
	}
	
	@Test
	public void modifyBoardTest(){
		/***Given***/
		Map<String, Object> mMap = new HashMap<String, Object>();
		mMap.put("boardId", "b6");
		mMap.put("boardName", "없는게시판");
		mMap.put("useSelect", "0");

		/***When***/
		int modifyCnt = boardDao.modifyBoard(mMap);
		
		/***Then***/
		assertEquals(1, modifyCnt);
	}
	
	@Test
	public void readArticleTest(){
		/***Given***/
		int articleNumber = 10;

		/***When***/
		ArticleVO articleVo = boardDao.readArticle(articleNumber);

		/***Then***/
		assertEquals("ryan", articleVo.getArticle_user());
		assertEquals("글내용", articleVo.getArticle_content());
	}
	
	@Test
	public void deleteArticleTest(){
		/***Given***/
		int articleNumber = 17;

		/***When***/
		int deleteCnt = boardDao.deleteArticle(articleNumber);

		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	@Test
	public void writeReplyTest(){
		/***Given***/
		ReplyVO replyVo = new ReplyVO();
		replyVo.setReply_user("james");
		replyVo.setReply_article(12);
		replyVo.setReply_content("테스트메서드댓글내용입니다");

		/***When***/
		int insertCnt = boardDao.writeReply(replyVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteReplyTest(){
		/***Given***/
		String replyId = "r14";

		/***When***/
		int deleteCnt = boardDao.deleteReply(replyId);
		
		/***Then***/
		assertEquals(1, deleteCnt);

	}
	
	@Test
	public void getNextArticleNumberTest(){
		/***Given***/

		/***When***/
		int articleNumber = boardDao.getNextArticleNumber();
		
		/***Then***/
		assertEquals(28, articleNumber);
	}
	
	@Test
	public void writeArticleTest(){
		/***Given***/
		ArticleVO articleVo = new ArticleVO(28, "moon", "b3", "제목", "내용내용", 28);
		logger.debug("pid : {}", articleVo.getArticle_pid());
		
		/***When***/
		int insertCnt = boardDao.writeArticle(articleVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void insertAppendTest(){
		/***Given***/
		AppendVO append = new AppendVO();
		append.setAppend_article(39);
		append.setAppend_path("d:\boardFile\2019\06\7b0ec4dd-b170-4aa1-bb53-58e465d057db.png");
		append.setAppend_filename("brown.png");
		
		/***When***/
		int insertCnt = boardDao.insertAppend(append);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void readAppendTest(){
		/***Given***/
		int appendArticle = 41;

		/***When***/
		List<AppendVO> appendList = boardDao.readAppend(appendArticle);

		/***Then***/
		assertNotNull(appendList);
		assertEquals("sally.png", appendList.get(0).getAppend_filename());

	}
	
	@Test
	public void modifyArticleTest(){
		/***Given***/
		ArticleVO articleVo = new ArticleVO();
		
		articleVo.setArticle_number(40);
		articleVo.setArticle_title("수정한글제목");
		articleVo.setArticle_content("수정한글내용");

		/***When***/
		int updateCnt = boardDao.modifyArticle(articleVo);
		
		/***Then***/
		assertEquals(1, updateCnt);

	}
}
