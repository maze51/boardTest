package kr.or.ddit.user.service;

import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;

public class UserService implements IuserService{
	
	private IuserDao userDao;
	
	public UserService() {
		userDao = new UserDao();
	}

	@Override
	public UserVO getUser(String id) {
		return userDao.getUser(id);
	}
	
	
}
