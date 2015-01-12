package com.haozen.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.omg.CORBA.Request;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haozen.entity.City;
import com.haozen.entity.Platform;
import com.haozen.service.PlatformService;
import com.haozen.util.PropertyFilter;

/**
 * 控制层逻辑:平台
 * @author pp
 *
 */
@Controller
@RequestMapping("/platform")	
public class PlatformController {
	
	public static Logger logger = Logger.getLogger(RegisterController.class);
	@Inject
	private PlatformService platformService;
	
	/**
	 * 进入平台添加网址页面
	 * @return
	 */
	@RequestMapping(value="/platurl",method=RequestMethod.GET)
	public String Add(){
		return "/platform/add";
	}
	
	/**
	 * 添加网址
	 * @param paltform
	 * @return
	 */
	@RequestMapping(value="/platurl",method=RequestMethod.POST,produces={"text/html;charset=UTF-8"})
	public @ResponseBody String AddUrl(Platform paltform){
		if(paltform.getPlatformname().equals("")){
			return "请输入网址名称";
		}else if(paltform.getPlaturl().equals("")){
			return "请输入网址";
		} 
		return "ok";
	}
	
	/**
	 * 平台搜索
	 * @param city
	 * @param platformform
	 */
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public void  getPlatformSearch(HttpServletRequest request){
		List<PropertyFilter>  filterList = PropertyFilter.builderPropertyFilterByRequest(request);
	    List<Platform> platformList = platformService.getPlatFormData(filterList);
	    System.out.println(platformList.size());
	}
}
