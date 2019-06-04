package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.mybatis.MyBatisUtil;

public class BoardDao implements IboardDao{
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDao.class);

	@Override
	public List<BoardVO> showBoardList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVO> list = sqlSession.selectList("board.showBoardList");
		sqlSession.close();
		return list;
	}
	
	
}
