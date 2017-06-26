package com.choa.ex6;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import com.choa.board.BoardDTO;
import com.choa.memo.MemoDAO;
import com.choa.memo.MemoDTO;
import com.choa.memo.MemoService;
import com.choa.notice.NoticeDAOImpl;
import com.choa.util.ListInfo;
import com.choa.util.PageMaker;

public class TTEst extends MyAbstractTestUnit {
//	@Inject
//	private NoticeDAOImpl noticeDAOImpl;
	@Inject
	private MemoService memoService;
	@Inject
	private MemoDAO memoDAO;
	
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test11() throws Exception{
		ListInfo listInfo = new ListInfo();
		listInfo.setCurPage(1);
		
		List<MemoDTO> ar =  memoService.boardList(listInfo);
		
		assertNotNull(ar);
		for(int i =0; i<ar.size(); i++){
		System.out.println(ar.get(i).getWriter());
		}
	}
	

}
