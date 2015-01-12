package com.haozen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/index")
	public String index(){
		System.out.println("Ö÷Ò³Ãæ");
		return "index";
	}				
}
