package com.choa.ex6;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;

import com.choa.file.FileDTO;
import com.choa.file.MultiFileDTO;
import com.choa.file.SameMultiFileDTO;

@Controller

@RequestMapping(value="/file/**")
public class FileController {
			@RequestMapping(value="fileUpload", method=RequestMethod.GET)
			public void fileUpload(){
			}
			
			
			
			//다중 파일업로드 인데 파라미터 이름을 모르거나 갯수가 유동적일대
			
			
			
			
			
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//같은 파라미터 이름으로 넘어 오는 방법
			public void sameMultiFileUpload(MultipartFile [] f1){
				for(int i=0; i<f1.length;i++){
					System.out.println(f1[i].getOriginalFilename());
				}
			}
			//멀티파트 서블릿 리퀘스트사용  파라미터 이름이 같을때 f1으로 여러개 올릴 시 배열로 받기
			@RequestMapping(value="sameMultiFileUpload", method=RequestMethod.POST)
			public void sameMultiFileUpload(MultipartHttpServletRequest request){
			List<MultipartFile> ar = request.getFiles("f1");
			for(MultipartFile f:ar){
			System.out.println(f.getOriginalFilename());
			}
			}
			//파라미터 이름이 같을때 f1으로 여러개 올릴 시 배열로 받기
			public void sameMultiFileUpload(SameMultiFileDTO dto){
				for(int i=0; i<dto.getF1().length;i++){
					System.out.println(dto.getF1()[i].getOriginalFilename());
				}
			}
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			//다중 파일 업로드 - 파라미터 이름이 다를 때
			@RequestMapping(value="multiFileUpload", method=RequestMethod.POST)
			public void multiFileUpload(MultiFileDTO multiFileDTO){ //다중파일 업로드 DTO 를 하나만들어서 그것을 사용함
				System.out.println(multiFileDTO.getF1().getOriginalFilename());
				System.out.println(multiFileDTO.getF2().getOriginalFilename());
			}
			public void multiFileUpload(MultipartFile f1, MultipartFile f2){
				System.out.println(f1.getOriginalFilename());
				System.out.println(f2.getOriginalFilename());
			}
			
			public void multiFileUpload(MultipartHttpServletRequest request){
				//2개를 꺼내야한다.
				MultipartFile f1 = request.getFile("f1");
				MultipartFile f2 = request.getFile("f2");
			}
			//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			
			
				
				
			//@@@@@@@@@@@@@@@@@@@
			//단일 파일 업로드  기존에 쓰던 방법@@@
			//1번째방법
			@RequestMapping(value="fileUpload", method=RequestMethod.POST)
			public void fileUpload(MultipartHttpServletRequest request){//
			}
//			//2번째 방법
//			@RequestMapping(value="fileUpload", method=RequestMethod.POST)
//			public void fileUpload(MultipartFile f1){
//			}
//			//3번째 방법 
//			@RequestMapping(value="fileUpload", method=RequestMethod.POST)
//			public void fileUpload(FileDTO fileDTO){
//			}
			
			
}
