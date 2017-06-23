package com.choa.file;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.choa.util.SeDTO;

@Service
public class FileService {
	
	
	//SeUpload
	public String seUpload(SeDTO seDTO,HttpSession session) throws Exception{
			FileSaver  fs = new FileSaver();
			//멀티파트 파일이 제대로 넘어 왔을 때 오리지날 네임이 널이 아닐때와 ""값이 아닐때
			String file_result = "";
			if(seDTO.getFiledata() != null && seDTO.getFiledata().getOriginalFilename() != null && !seDTO.getFiledata().getOriginalFilename().equals("")){
			String defaultPath = session.getServletContext().getRealPath("resources/upload");
			String realName = fs.filesave(defaultPath, seDTO.getFiledata());
			
				file_result = "&bNewLine=true&sFileName="+seDTO.getFiledata().getOriginalFilename()+"&sFileURL=/ex6/resources/upload/"+realName;
			}else{
				file_result = "&errstr=error";
			}
			return "redirect:"+seDTO.getCallback()+"?callback_func="+seDTO.getCallback_func()+file_result;	
	}

	//delete
	public boolean fileDelete(String fileName, HttpSession session) throws Exception{
			String realPath  = session.getServletContext().getRealPath("resources/upload");
			File file = new File(realPath,fileName); 
			boolean result = file.delete();//파일 지우기 메서드 , 제대로 지웠으면 True
			return result;
	}
		
		
		
	public String fileSave(MultipartFile m,HttpSession session) throws Exception{ //세션을 넘기던 리얼패스 만들어 가져오던 니맘대로
		FileSaver fs = new FileSaver();
	
		String fileName =fs.filesave(session.getServletContext().getRealPath("resources/upload"), m);
		
			return fileName;
	}
			
}
