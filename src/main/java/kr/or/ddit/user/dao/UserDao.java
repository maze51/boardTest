package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.mybatis.MyBatisUtil;
import kr.or.ddit.user.model.UserVO;

public class UserDao implements IuserDao{

	@Override
	public UserVO getUser(String id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		UserVO user = sqlSession.selectOne("user.getUser", id);
		sqlSession.close();
		return user;
	}
	
	
}
