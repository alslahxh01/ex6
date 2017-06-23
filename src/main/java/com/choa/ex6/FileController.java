package com.choa.ex6;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import com.choa.file.FileDTO;
import com.choa.file.FileService;
import com.choa.file.MultiFileDTO;
import com.choa.file.SameMultiFileDTO;
import com.choa.util.SeDTO;

@Controller

@RequestMapping(value="/file/**")
public class FileController {
			@RequestMapping(value="fileUpload", method=RequestMethod.GET)
			public void fileUpload(){
			}
			
			
			
			
			
			//Start Editor
			@RequestMapping(value="seUpload", method=RequestMethod.POST)
			public String seUpload(SeDTO seDTO,HttpSession session) throws Exception{
					//한글파일 업로드는 하지말자
				
				FileService fileService = new FileService();
				return fileService.seUpload(seDTO, session);
//				//callBack
//			String callback = seDTO.getCallback();
//			System.out.println("callback : "+callback);
//			//callBack_func
//			String callback_func = seDTO.getCallback_func();
//			//OriName
//			String original_name =seDTO.getFiledata().getOriginalFilename(); 
//			//defaultPath
//			String defaultPath = session.getServletContext().getRealPath("resources/upload");
//			System.out.println(defaultPath);
//			File f = new File(defaultPath);
//			//폴더가 존재하지 않으면 하나 만들자
//			if(!f.exists()){
//				f.mkdirs();
//			}
//			//디렉토리에 저장할 파일명
//			String realName = UUID.randomUUID().toString()+"_"+original_name;
//			//디렉토리에 저장
//			seDTO.getFiledata().transferTo(new File(f,realName)); //경로명, 파일이름
			//네이버 에디터라서 이렇게씀
//			String file_result = "&bNewLine=true&sFileName="+original_name+"&sFileURL=/ex6/resources/upload/"+realName;
//			
//			return "redirect:"+callback+"?callback_func="+callback_func+file_result;	
//				Enumeration<Object> e =  request.getParameterNames();
//			
//				while(e.hasMoreElements()){
//					System.out.println(e.nextElement());
//				}
//				Iterator<String> it=request.getFileNames();
//				while(it.hasNext()){
//					System.out.println(it.next()); //파일이름을 나타내줌	
//				}
				
				
			}
			
			//파일 다운
			@RequestMapping(value="fileDown", method=RequestMethod.GET)
			public ModelAndView fileDown(String fileName,String oriName, HttpSession session){
					String realPath = session.getServletContext().getRealPath("resources/upload");
					File f = new File(realPath,fileName);
				 //현재 .jsp로 가는게 문제다
					ModelAndView mv = new ModelAndView();
					mv.setViewName("download"); //뷰 이름을 클래스 이름과 동일하게 첫자 소문자로
					mv.addObject("downloadFile",f); //여기까지 download.jsp가 필요
					mv.addObject("oriName",oriName);
					return mv; //이것이 View 페이지로 가면 안된다.
			}
			
			
			//@@@@@@@@@@@@@@@@파일 딜리트@@@@@@@@@
			@RequestMapping(value="fileDelete", method=RequestMethod.GET)
				public void fileDelete(String fileName, HttpSession session) throws Exception{ //디비 파일이름삭제 및 upload 폴더에 실제 파일까지 삭제하고싶다.
				// 해당 파일을 지우려면 파일네임을 알아야한다.
				//어느 폴더에 있는 어느 파일을 지워야한다.
				
				FileService fileService = new FileService(); //서비스 객체는 위에Autowired ㄱㄱ
				boolean result = fileService.fileDelete(fileName, session);
				
				System.out.println(result);
				
			}
			
			
			//다중 파일업로드 인데 파라미터 이름을 모르거나 갯수가 유동적일대
			@RequestMapping(value="upload", method=RequestMethod.POST)
			public void upload(MultipartHttpServletRequest request){
				Iterator<String> it=request.getFileNames();
				ArrayList<MultipartFile> multi = new ArrayList<MultipartFile>();
				while(it.hasNext()){
					MultipartFile m = request.getFile(it.next());
					multi.add(m);
				}
				
			for(MultipartFile m:multi){
			System.out.println(m.getOriginalFilename());
			}
			}
			
			
			
			
			
			
			
			
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
//			@RequestMapping(value="fileUpload", method=RequestMethod.POST)
//			public void fileUpload(MultipartHttpServletRequest request){//
//			}
//			//2번째 방법
			@RequestMapping(value="fileUpload", method=RequestMethod.POST)
			public ModelAndView fileUpload(MultipartFile f1,HttpSession session) throws Exception{
				FileService fileService = new FileService();
				
				String fileName = fileService.fileSave(f1, session);
				ModelAndView mv = new ModelAndView();
				mv.setViewName("file/fileView");
				
				mv.addObject("fileName",fileName);//리턴받은 파일 네임 넣고
				mv.addObject("oriName",f1.getOriginalFilename()); //f1ㅇ에서 꺼낸 오리지널 네임 넣고
				
				return mv;
			}
//			//3번째 방법 
//			@RequestMapping(value="fileUpload", method=RequestMethod.POST)
//			public void fileUpload(FileDTO fileDTO){
//			}
			
			
}
