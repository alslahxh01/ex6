package com.choa.memo;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.choa.util.ListInfo;
@Repository
public class MemoDAO{

	@Inject
	private SqlSession sqlSession;
	private static final String NAMESPACE="MemoMapper.";
	//list
	public List<MemoDTO> boardList(ListInfo listInfo) throws Exception{	
		return sqlSession.selectList(NAMESPACE+"memoList",listInfo);
	}
	
	//view
	public MemoDTO boardView(int num) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memoView", num);		
	}	
	
	
	//insert
	public int boardWrite(MemoDTO memoDTO) throws Exception{
		return sqlSession.insert(NAMESPACE+"memoWrite",memoDTO);
		
	}
	
	//update
	public int boardUpdate(MemoDTO memoDTO) throws Exception{
		return sqlSession.update(NAMESPACE+"memoUpdate",memoDTO);
	}
	
	//delete
	public int boardDelete(int num) throws Exception{
		return sqlSession.delete(NAMESPACE+"memoDelete",num);
	}
	//count
	
	public int boardCount(ListInfo listInfo)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"memoCount");
	}
	
}
