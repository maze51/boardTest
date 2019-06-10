package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.model.AppendVO;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;

public class BoardService implements IboardService{
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardService.class);
	
	private IboardDao boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}
	
	@Override
	public List<BoardVO> showBoardList() {
		return boardDao.showBoardList();
	}

	@Override
	public List<ArticleVO> selectAllArticle(String article_board) {
		return boardDao.selectAllArticle(article_board);
	}

	@Override
	public Map<String, Object> articlePagingList(Map<String, Object> searchMap) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("articleList", boardDao.articlePagingList(searchMap));
		
		String boardId = (String) searchMap.get("boardId");
		int boardCnt = boardDao.boardCnt(boardId);
		
		int pageSize = (int) searchMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)boardCnt/pageSize);
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	@Override
	public int createBoard(Map<String, Object> cMap) {
		return boardDao.createBoard(cMap);
	}

	@Override
	public int modifyBoard(Map<String, Object> mMap) {
		return boardDao.modifyBoard(mMap);
	}

	@Override
	public ArticleVO readArticle(int articleNumber) {
		return boardDao.readArticle(articleNumber);
	}

	@Override
	public List<ReplyVO> readReply(int articleNumber) {
		return boardDao.readReply(articleNumber);
	}

	@Override
	public int deleteArticle(int articleNumber) {
		return boardDao.deleteArticle(articleNumber);
	}

	@Override
	public int writeReply(ReplyVO reply) {
		return boardDao.writeReply(reply);
	}

	@Override
	public int deleteReply(String replyId) {
		return boardDao.deleteReply(replyId);
	}

	@Override
	public int writeArticle(ArticleVO article) {
		return boardDao.writeArticle(article);
	}

	@Override
	public int getNextArticleNumber() {
		return boardDao.getNextArticleNumber();
	}

	@Override
	public int insertAppend(AppendVO append) {
		return boardDao.insertAppend(append);
	}

	@Override
	public List<AppendVO> readAppend(int appendArticle) {
		return boardDao.readAppend(appendArticle);
	}

	@Override
	public int modifyArticle(ArticleVO articleVo) {
		return boardDao.modifyArticle(articleVo);
	}

}
