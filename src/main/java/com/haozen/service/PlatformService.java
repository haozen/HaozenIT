package com.haozen.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.haozen.dao.PlatformDao;
import com.haozen.entity.Platform;
import com.haozen.util.PropertyFilter;

@Named
@Transactional
public class PlatformService {
	
	@Inject
	private PlatformDao platformDao;
	public List<Platform> getPlatFormData(List<PropertyFilter> filterList){
		List<Platform> listPlatforms = platformDao.findListByPropertyfilter(filterList, "id","desc");
		return listPlatforms;
	}
}
