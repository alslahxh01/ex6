package com.choa.ex6;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class connection {

	private SqlSession session;
	@Test
	public void test() {
			
		
		assertNotNull(session);

		
	}

}
