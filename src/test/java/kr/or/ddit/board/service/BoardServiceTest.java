package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

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
	
	@Test
	public void selectAllArticleTest(){
		/***Given***/
		String article_board = "b2";

		/***When***/
		List<ArticleVO> articleList = boardService.selectAllArticle(article_board);

		/***Then***/
		assertEquals(1, articleList.size());

	}
	
	@Test
	public void articlePagingListTest(){
		/***Given***/
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("page", 1);
		searchMap.put("pageSize", 10);
		searchMap.put("boardId", "b11");

		/***When***/
		Map<String, Object> resultMap = boardService.articlePagingList(searchMap);
		List<ArticleVO> articleList = (List<ArticleVO>) resultMap.get("articleList");

		/***Then***/
		assertEquals(10, articleList.size());
	}
	
	@Test
	public void createBoardTest(){
		/***Given***/
		Map<String, Object> cMap = new HashMap<String, Object>();
		
		cMap.put("userId", "aTestId");
		cMap.put("boardName", "junitTestBoard");
		cMap.put("useSelect", "0");

		/***When***/
		int insertCnt = boardService.createBoard(cMap);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void modifyBoardTest(){
		/***Given***/
		Map<String, Object> mMap = new HashMap<String, Object>();
		
		mMap.put("boardId", "b516");
		mMap.put("boardName", "안노는게시판");
		mMap.put("useSelect", "0");

		/***When***/
		int updateCnt = boardService.modifyBoard(mMap);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void readArticleTest(){
		/***Given***/
		int articleNumber = 64;

		/***When***/
		Map<String, Object> resultMap = boardService.readArticle(articleNumber);
		ArticleVO articleVo = (ArticleVO) resultMap.get("article");

		/***Then***/
		assertEquals("게시글", articleVo.getArticle_title());
		assertEquals("cony", articleVo.getArticle_user());
	}
	
	@Test
	public void readReplyTest(){
		/***Given***/
		int articleNumber = 11;

		/***When***/
		List<ReplyVO> rList = boardService.readReply(articleNumber);

		/***Then***/
		assertEquals(4, rList.size());
	}
	
	@Test
	public void deleteArticleTest(){
		/***Given***/
		int articleNumber = 27;

		/***When***/
		int updateCnt = boardService.deleteArticle(articleNumber);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void writeReplyTest(){
		/***Given***/
		ReplyVO reply = new ReplyVO();
		reply.setReply_user("aTestId");
		reply.setReply_article(45);
		reply.setReply_content("JUnit테스트댓글달기");

		/***When***/
		int insertCnt = boardService.writeReply(reply);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void deleteReplyTest(){
		/***Given***/
		String replyId = "r8";

		/***When***/
		int updateCnt = boardService.deleteReply(replyId);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void writeArticleTest(){
		/***Given***/
		ArticleVO articleVo = new ArticleVO(93, "aTestId", "b3", "JUnitTest제목", "내용내용", 93);
		//logger.debug("pid : {}", articleVo.getArticle_pid());
		
		/***When***/
		int insertCnt = boardService.writeArticle(articleVo);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	@Test
	public void getNextArticleNumberTest(){
		/***Given***/

		/***When***/
		int articleNumber = boardService.getNextArticleNumber();

		/***Then***/
		assertEquals(94, articleNumber);
	}
	
	@Test
	public void readAppendTest(){
		/***Given***/
		int appendArticle = 61;

		/***When***/
		List<AppendVO> list = boardService.readAppend(appendArticle);

		/***Then***/
		assertEquals(1, list.size());
	}
	
	@Test
	public void modifyArticleTest(){
		/***Given***/
		ArticleVO articleVo = new ArticleVO();
		articleVo.setArticle_number(40);
		articleVo.setArticle_title("JUinitTest수정한글제목");
		articleVo.setArticle_content("111수정한글내용");

		/***When***/
		int updateCnt = boardService.modifyArticle(articleVo);

		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	@Test
	public void getDownloadFileTest(){
		/***Given***/
		String appendId = "a30";

		/***When***/
		AppendVO appendVo = boardService.getDownloadFile(appendId);

		/***Then***/
		assertEquals("6r3vs-FPCLo.jpg", appendVo.getAppend_filename());
	}
	
	@Test
	public void readBoardNameTest(){
		/***Given***/
		String boardId = "b10";

		/***When***/
		String boardName = boardService.readBoardName(boardId);

		/***Then***/
		assertEquals("공지게시판", boardName);
	}
}
