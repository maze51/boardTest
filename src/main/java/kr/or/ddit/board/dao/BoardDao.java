package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.ArticleVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.ReplyVO;
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

	@Override
	public List<ArticleVO> selectAllArticle(String article_board) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ArticleVO> list = sqlSession.selectList("board.selectAllArticle", article_board);
		sqlSession.close();
		return list;
	}

	@Override
	public int boardCnt(String boardId) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int boardCnt = sqlSession.selectOne("board.boardCnt", boardId);
		sqlSession.close();
		return boardCnt;
	}

	@Override
	public List<ArticleVO> articlePagingList(Map<String, Object> searchMap) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ArticleVO> articleList = sqlSession.selectList("board.articlePagingList", searchMap);
		sqlSession.close();
		return articleList;
	}

	@Override
	public int createBoard(Map<String, Object> cMap) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int insertCnt = sqlSession.insert("board.createBoard", cMap);
		sqlSession.commit();
		sqlSession.close();
		return insertCnt;
	}

	@Override
	public int modifyBoard(Map<String, Object> mMap) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int modifyCnt = sqlSession.update("board.modifyBoard", mMap);
		sqlSession.commit();
		sqlSession.close();
		return modifyCnt;
	}

	@Override
	public ArticleVO readArticle(int articleNumber) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		ArticleVO article = sqlSession.selectOne("board.readArticle", articleNumber);
		sqlSession.close();
		return article;
	}

	@Override
	public List<ReplyVO> readReply(int articleNumber) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVO> replyList = sqlSession.selectList("board.readReply", articleNumber);
		sqlSession.close();
		return replyList;
	}

	@Override
	public int deleteArticle(int articleNumber) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.update("board.deleteArticle", articleNumber);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}
}
