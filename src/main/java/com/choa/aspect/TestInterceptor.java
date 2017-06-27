package com.choa.aspect;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.choa.board.BoardDTO;
import com.choa.util.ListInfo;

public class TestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { //컨트롤러 빠져나올 때
		System.out.println("컨트롤러 나간 후 ");
		        Map<String, Object> map= modelAndView.getModel();
						Object list = map.get("list");
						Object board = map.get("board");
						Object listInfo = map.get("listInfo");
						System.out.println(((List<BoardDTO>)list).get(0).getContents());
						System.out.println(((ListInfo)listInfo).getCurBlock());
						System.out.println(board);
						
//						modelAndView.addObject(attributeValue); 로 또 넣을 수 있다.
//						modelAndView.setViewName("home");
					
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			System.out.println("컨트롤러 가기전");
			
		return true; //컨트롤러로 뭔가 보내고 싶을 때 true 아니면 false
	}
		
		
}
