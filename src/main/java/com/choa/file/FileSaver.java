package com.choa.file;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
		
	//파일 세이브만 전문으로 하는 클래스
	//경로명하고 파일이름만 주면 저장해줌
	public String filesave(String realPath, String oriName,byte [] filedata) throws Exception{ //바이트 타입을 받는것은 Controller에서 File 할때 byte[]배열 타입을 해서
		File file = new File(realPath);
			//폴더가 존재하지 않으면 폴더를 만들어줘라
		if(!file.exists()){
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString()+"_"+oriName; //업로드 시 저장되는 실제 파일이름.
		//DB에 셋팅 시키기
		File target = new File(file,fileName); //file : 경로명과 저장되는 fileName : 실제파일명
		FileCopyUtils.copy(filedata, target); //파일데이터를 꺼내와 타겟으로 저장해주겠다. return 파일네임
			return fileName;
	}
	//2번째 방법 멀티파트 파일이라는것을 이용하는 방법이다
	public String filesave(String realPath,MultipartFile m) throws Exception{
		System.out.println(realPath);
		File file = new File(realPath);
		if(!file.exists()){
			file.mkdirs();
		}
//		String fileName = UUID.randomUUID().toString()+"_"+m.getOriginalFilename(); 
						//[   UUID만들기     ]
		//중복만 되지 않게 하면된다.UUID 쓰지말고  밀리세컨을 이용하여 
		Calendar c = Calendar.getInstance();
		String fileName = c.getTimeInMillis()+"_"+m.getOriginalFilename(); //밀리세컨을 이용하여 밀리세컨시간+파일이름.
		File target = new File(file,fileName);
		m.transferTo(target); //transferTo : 누구한테 전달하겠다. (target)을 썻으니 타겟쪽에 전달하겠다.
		
		return fileName;
	}
	
	
}