package com.choa.ex6;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public String exception(Exception e, Model model){
		model.addAttribute("e",e.getMessage());
		return "error/notFound";
	}
	
}
