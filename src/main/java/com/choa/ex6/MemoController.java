package com.choa.ex6;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.choa.memo.MemoDTO;
import com.choa.memo.MemoService;
import com.choa.util.ListInfo;
//모든 메서드가 @ResponseBody 를 가지고 있다면. 클래스 상단부에 @Controller 가 아닌 @RestController 로 변경
@Controller
@RequestMapping(value="/memo/**")
public class MemoController {

	
	@Inject
	private MemoService memoService;
	
	@RequestMapping(value="memoList")
	public void memoList() throws Exception{
		
	}
	@RequestMapping(value="getMemoList/{curPage}/{find}/{search}",method=RequestMethod.GET)
	@ResponseBody //return 할때 JSOn 형식으로 데이터 가 넘어간다
	public List<MemoDTO> memoList(@PathVariable int curPage,@PathVariable String find, @PathVariable String search) throws Exception{
			ListInfo listInfo = new ListInfo();
			listInfo.setCurPage(curPage);

			listInfo.setFind(find);

			listInfo.setSearch(search);
			List<MemoDTO> ar = memoService.boardList(listInfo);
		//jackson 을 사용해서 중간과정 getMemoList.jsp 로 보내는것을 없앤다.
		
		//리턴 할때 ar이 뷰의경로가 아니라 data형식으로 보낼수 있도록 만들어준다.
		//돌아가는 곳으로 data 형식으로 가진다.
		
		return ar;	
	}
		
	@RequestMapping(value="memoWrite",method=RequestMethod.POST)
	@ResponseBody
	public List<MemoDTO> memoWrite(MemoDTO memoDTO,Model model) throws Exception{
	
		int result = memoService.boardWrite(memoDTO);
//		String message="fail";
//		if(result > 0){
//			message="success";
//		}
		ListInfo listInfo = new ListInfo();
		listInfo.setCurPage(1);
		List<MemoDTO> ar = memoService.boardList(listInfo);

		return ar;
	}		
				//RestFul 방식 주소에 변수를 넣어서 오게됨.
		@RequestMapping(value="memoView/{num}") //변수명이 num
		@ResponseBody
		public MemoDTO memoView(@PathVariable("num") int num) throws Exception{
							//PathVariable 경로변수 ("num") 이라는 변수가 int num 으로 맵핑되게한다.
			MemoDTO memoDTO = memoService.boardView(num);
			return memoDTO;
		}
		
		
	
	
}
