package com.choa.file;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

//일반 클래스가 아니라 View 객체 역할을 해야한다. 그 역할을 주기 위해서 Spring 에서 제공하는
//AbstractView 라는 클래스를 상속 받아야 한다..... (없는 뷰 객체를 만들어야하니 저것을 사용)
public class Download extends AbstractView{
		
	public Download() {
		setContentType("application/download;charset=UTF-8"); //요청이 어떤것이냐 라는 것이 Header 파일에 묶여서 간다.
															  //어플리케이션/다운로드 요청 하는 것
		}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception { 			//ModelAndView 에 Model 이 Map이다(키와 벨류)
			File f = (File)model.get("downloadFile");
			String oriName = (String)model.get("oriName");
			response.setCharacterEncoding(getContentType()); //getContentsType 위에서 맞춘것
			response.setContentLength((int)f.length()); //니가보내는 파일의 길이가  얼마냐? //int로 형변환
			
			//String fileName = URLEncoder.encode(f.getName(),"UTF-8"); //파일명 꺼내기 한글이면 깨질 수 있는 가능성이 높으니 UTF-8 로 맞춰줘야함
			String fileName = URLEncoder.encode(f.getName(),"UTF-8");
			fileName = fileName.substring(fileName.lastIndexOf("_")+1);
			// fileName 을 넣고 다시 테스트해보면 원래 파일명으로 다운로드가 진행된것을 볼 수 있다.
			response.setHeader("Content-Disposition","attachment;filename=\""+oriName+"\""); 
			response.setHeader("Content-Transfer-Encoding", "binary"); //파일을 2진코드로 바꾸자는 코드
			
			OutputStream out = response.getOutputStream();
			//파일을 읽어서 보내줘야함. 2진데이터로 보냄
			
			FileInputStream fi = null;
			
			fi = new FileInputStream(f);
			
			FileCopyUtils.copy(fi, out);
			
			fi.close();
			out.close();
	//@@ 여기까지 파일 다운로드 처리하는 뷰 객체
			
	}
		

}
