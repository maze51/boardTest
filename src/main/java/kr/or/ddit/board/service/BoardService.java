package kr.or.ddit.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IboardDao;
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

}
