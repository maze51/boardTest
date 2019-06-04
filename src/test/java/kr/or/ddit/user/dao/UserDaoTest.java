package kr.or.ddit.user.dao;

import static org.junit.Assert.*;
import kr.or.ddit.user.model.UserVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoTest.class);
	
	private IuserDao userDao; 
	
	@Before
	public void setup(){
		userDao = new UserDao();
	}
	
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "cony";

		/***When***/
		UserVO userVo = userDao.getUser(userId);

		/***Then***/
		assertEquals("코니", userVo.getName());

	}

}
