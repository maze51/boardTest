package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.UserVO;

public interface IuserDao {
	
	/**
	 * 
	* Method : getUser
	* 작성자 : PC10
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 입력받은 id에 해당하는 사용자 정보 조회
	 */
	UserVO getUser(String id);
}
