package com.haozen.controller;


import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.haozen.entity.User;
import com.haozen.service.UserService;
/**
 *  ¿ØÖÆ²ãÂß¼­:×¢²á
 * @author pp
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
	public static Logger logger = Logger.getLogger(RegisterController.class);
		
		@Inject
		private UserService userService;
	
		@RequestMapping(value="/index",method=RequestMethod.GET)
		public String Index(){
			return "register/index";
		}
		@RequestMapping(value="/index",method=RequestMethod.POST)
		public String Add(User user){
			System.out.println(user.getUsername());
			userService.saveUser(user);
			return "redirect:/index";
		}
		
	}
