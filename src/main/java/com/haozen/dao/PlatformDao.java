package com.haozen.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Criteria;

import com.haozen.entity.Platform;
import com.haozen.util.PropertyFilter;

@Named
public class PlatformDao extends BaseDao<Platform>{

	@SuppressWarnings("unused")
	@Inject
	private CityDao cityDao;
	
	//���ݲ�ͬ�����ֶβ�ѯ
	@Override
	public List<Platform> findListByPropertyfilter(List<PropertyFilter> filterList ,String orderByProperty,String orderBytype){
		
		//platformʵ���cityʵ��������
		Criteria c = getSession().createCriteria(Platform.class).createAlias("city", "c");
		return  super.findListByPropertyfilter(c,filterList,orderByProperty,orderBytype);
	}
	
	

}
