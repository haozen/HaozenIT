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
 * ���Ʋ��߼�:ƽ̨
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
	 * ����ƽ̨�����ַҳ��
	 * @return
	 */
	@RequestMapping(value="/platurl",method=RequestMethod.GET)
	public String Add(){
		return "/platform/add";
	}
	
	/**
	 * �����ַ
	 * @param paltform
	 * @return
	 */
	@RequestMapping(value="/platurl",method=RequestMethod.POST,produces={"text/html;charset=UTF-8"})
	public @ResponseBody String AddUrl(Platform paltform){
		if(paltform.getPlatformname().equals("")){
			return "��������ַ����";
		}else if(paltform.getPlaturl().equals("")){
			return "��������ַ";
		} 
		return "ok";
	}
	
	/**
	 * ƽ̨����
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
