package com.choa.ex6;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDAOImpl;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeServiceImpl;
import com.choa.util.ListInfo;
import com.choa.util.PageMaker;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	//Inject 를 사용하여 DI 를 해달라고 Spring에게 전달하면 Spring Container 가 알아서 new ~~ 해줌 - xml로 가서 코드 쓰면됨.
	
	@Autowired //만들어진 서비스를 주입시키기
	private NoticeServiceImpl noticeService;
	
	//List
@RequestMapping(value="noticeList" , method=RequestMethod.GET )
	public String noticeList(Model model, ListInfo listInfo) throws Exception{
			//Exception 처리 Spring Container 가 처리 한다.
	//1. curPage null 처리하기 - getter 에서 널이면 1 초기값 세팅
	//2.Mapper 로 갈 때 4가지변수 한꺼번에 ㄱㄱ search,find, startRow,lastRow 
	
		List<BoardDTO> ar =noticeService.boardList(listInfo);
		System.out.println(ar.get(1000).getTitle());
		//throws new IndexOutOfBoundsException() 가 숨어있다.
		//얘를 noticeList.jsp 에 보내야한다
	
		model.addAttribute("list",ar); //ArrayList
		model.addAttribute("board","notice"); //Board타입이 뭔지?에 대한것
		model.addAttribute("listInfo",listInfo); //현재페이지
		return "board/boardList";
}	

@RequestMapping(value="noticeView")
public String noiceView(Model model, Integer num) throws Exception{
	BoardDTO boardDTO = noticeService.boardView(num);
	model.addAttribute("dto",boardDTO);
	model.addAttribute("board","notice");
	
	return "board/boardView";
}
	
	@RequestMapping(value="noticeWrite",method=RequestMethod.GET)
	public String noticeWrite(Model model) throws Exception{
		
		model.addAttribute("path", "Write");
		model.addAttribute("board", "notice");
		
		return "board/boardWrite";
	}
	//write(DB)
	@RequestMapping(value="noticeWrite" , method=RequestMethod.POST )
	public String noticeWrite(RedirectAttributes rd,BoardDTO boardDTO) throws Exception{
		int result = noticeService.boardWrite(boardDTO);
		String message="FAIL";
		//if로 성공 실패 여부 적기
		if(result>0){
			message="SUCCESS";
		}
//		model.addAttribute("message", message);
	
		rd.addFlashAttribute("message", message);
		//현재방법 Forwarding 
		
//		return "notice/result";
		return "redirect:noticeList?curPage=1";  //하지만 Redirect 로 가고싶다면 이방법
		// redirect로 홈을 가고싶으면 / 주면됨
		
	}
	
	//UpdateFOrm
	@RequestMapping(value="noticeUpdate" , method=RequestMethod.GET )
	public String noticeUpdate(Integer num, Model model) throws Exception{
		BoardDTO boardDTO = noticeService.boardView(num);		
		
		model.addAttribute("dto",boardDTO);
		model.addAttribute("path", "Update");
		
		return "notice/noticeWrite";
		
	}
	//Update
	@RequestMapping(value="noticeUpdate" , method=RequestMethod.POST )
	public String noticeUpdate(RedirectAttributes rd,BoardDTO boardDTO) throws Exception{			
		
			int result = noticeService.boardUpdate(boardDTO);
			String message="Update Failed";
			if(result >0){
				message="Update Success";
			}
			rd.addFlashAttribute("message",message);
			
			return "redirect:noticeList?curPage=1";
		}
	//Delete
	
	@RequestMapping(value="noticeDelete" , method=RequestMethod.GET )
	public String noticeDelete(RedirectAttributes rd, Integer num) throws Exception{
	int result = noticeService.boardDelete(num);
	String message="FAIL DELETE";
	if(result>0){
		message="SUCCESS DELETE";
	}
	rd.addFlashAttribute("message", message);
	return "redirect:noticeList?curPage=1";
				//if
	}
	
	//Controller 자체에 Exception 메서드 만들어도 됨. 하지만 비효율적 ControllerAdvice 를 사용하자
//	@ExceptionHandler(Exception.class)
//	public String exception(){
//		
//		return "error/notFound";
//	}
}
