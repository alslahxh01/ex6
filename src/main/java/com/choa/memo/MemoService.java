package com.choa.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.choa.util.ListInfo;

@Service
public class MemoService {

		@Inject
		private MemoDAO memoDAO;
			
		
		//list
		public List<MemoDTO> boardList(ListInfo listInfo) throws Exception{
			int totalCount = memoDAO.boardCount(listInfo);
			listInfo.makePage(totalCount); //Pageing
			listInfo.setRow(); //startNum lastNum
			return memoDAO.boardList(listInfo);
			}
		//view
		public MemoDTO boardView(int num) throws Exception{
			
			return memoDAO.boardView(num);
		}
		//insert
		public int boardWrite(MemoDTO memoDTO) throws Exception{
			return memoDAO.boardWrite(memoDTO);
		}
		//update
		public int boardUpdate(MemoDTO memoDTO) throws Exception{
			return memoDAO.boardUpdate(memoDTO);
		}
		//delete
		public int boardDelete(int num) throws Exception{
			return memoDAO.boardDelete(num);
		}
}	
