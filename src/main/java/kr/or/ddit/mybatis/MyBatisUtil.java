package kr.or.ddit.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static{
		String resource = "kr/or/ddit/config/mybatis/mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//위에 있던 SqlSession sqlSession = sqlSessionFactory.openSession(); 부분의 로직이 들어간다
	public static SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
	
}
