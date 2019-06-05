package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;

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
		return 0;
	}

}
