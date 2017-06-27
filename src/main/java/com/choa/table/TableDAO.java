package com.choa.table;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
@Repository
public class TableDAO {
	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE="TableMapper.";
	
	public int insertTableA(TableADTO tableADTO) throws Exception{
		return sqlSession.insert(NAMESPACE+"insertTableA",tableADTO);
	}
	public int insertTableB(TableBDTO tableBDTO) throws Exception{
		return sqlSession.insert(NAMESPACE+"insertTableB",tableBDTO);
	}
		
	
	
}
