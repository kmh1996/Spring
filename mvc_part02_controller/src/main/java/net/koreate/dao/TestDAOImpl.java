package net.koreate.dao;

import org.springframework.stereotype.Repository;

@Repository
public class TestDAOImpl implements TestDAO {
	
	public TestDAOImpl() {
		System.out.println("TestDAOImpl 생성");
	}

	@Override
	public void testDAO(String message) {
		System.out.println(message+" testDAO");
	}

}


