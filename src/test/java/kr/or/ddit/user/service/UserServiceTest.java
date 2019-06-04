package kr.or.ddit.user.service;

import static org.junit.Assert.*;
import kr.or.ddit.user.model.UserVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceTest.class);
	
	private IuserService userService;
	
	@Before
	public void setup(){
		userService = new UserService();
	}
	
	@Test
	public void test() {
		/***Given***/
		String id = "brown";

		/***When***/
		UserVO userVo = userService.getUser(id);

		/***Then***/
		assertNotNull(userVo);
		assertEquals("brown", userVo.getUserId());
		
	}

}
