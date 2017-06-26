package com.choa.ex6;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class connection extends MyAbstractTestUnit{
	@Inject
	private SqlSession session;
	@Test
	public void test() {
			
		
		assertNotNull(session);

	}

}
