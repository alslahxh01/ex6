package com.choa.ex6;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import com.choa.table.TableADTO;
import com.choa.table.TableService;

public class TableTest extends MyAbstractTestUnit{

	@Inject
	private TableService tableService;
	
	

	public void test() {
		fail("Not yet implemented");
	}
	@Test
	public void test22() throws Exception{
				TableADTO tableADTO = new TableADTO();
				tableADTO.setNum(2);
				tableADTO.setTitle("tableAAA222");
				tableADTO.setWriter("ohoh222");
				
	
				int  result= tableService.insertTable(tableADTO, 1);
				
				assertEquals(1, result);
		
	}

}
