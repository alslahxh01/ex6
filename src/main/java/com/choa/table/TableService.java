package com.choa.table;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;

@Service
public class TableService {

	
	@Inject
	private TableDAO tableDAO;
		
			public int insertTable(TableADTO tableADTO, int num) throws Exception{
				 tableDAO.insertTableA(tableADTO);
				 TableBDTO tableBDTO = new TableBDTO();
				 tableBDTO.setNum(num);
				 tableBDTO.setWriter(tableADTO.getWriter());
				 tableBDTO.setPoint(10);
				 return tableDAO.insertTableB(tableBDTO);
				 //Spring 에서 롤백해주는 처리를 해줄 수 있다
			}
	
	
	
}
