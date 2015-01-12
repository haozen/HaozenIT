package com.haozen.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;

import com.haozen.util.DateTimeUtil;
import com.haozen.util.Page;
import com.haozen.util.PropertyFilter;

/**
 * 
 * @author wxj
 *һ����Ķ���
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> {
	
	@Inject
	private SessionFactory sessionFactory;
	
	/**
	 * ��dao��
	 * �ɸ��෴��һ����Ķ���
	 */
	private Class<?> clazz;
	public BaseDao(){
		ParameterizedType  ParameterizedType = (java.lang.reflect.ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<?>) ParameterizedType.getActualTypeArguments()[0];
	}
	

	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	//��ӻ��޸�һ����¼
	public void save(T t){
		getSession().saveOrUpdate(t);
	}
	//ɾ��һ����¼
	public void del(Integer id){
		getSession().delete(findById(id));
	}
	
	//����id��ѯһ����¼
	public T findById(Integer id){
		return (T) getSession().get(clazz, id);
	}
	
	//��ѯ���еļ�¼
	public List<T> findAll(){
		Criteria ct = getSession().createCriteria(clazz);
		return ct.list();
	}
	
	//��ѯ���еļ�¼,����������
	public List<T> findAll(String orderByProperty, String orderByType){
		Criteria ct = getSession().createCriteria(clazz);
		
		if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
			if(orderByType.equalsIgnoreCase("desc")){
				ct.addOrder(Order.desc(orderByProperty));
			}else if(orderByType.equalsIgnoreCase("asc")){
				ct.addOrder(Order.asc(orderByProperty));
			}
		}
		
		return ct.list();
	}
	
	/*
	 * ������������ѯ��һ����
	 */
	public T findObjectByProperty(String propertyName,Object value){
		Criteria ct = getSession().createCriteria(clazz);
		ct.add(Restrictions.eq(propertyName, value));
		return (T) ct.uniqueResult();
	}
	
	/*
	 * ������������ѯ���϶���
	 */
	public List<T> findListByProperty(String propertyName,Object value){
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propertyName, value));
		return c.list();
	}
	
	
	 
	
	//Projections:�ۺϺ�����ѯcount(*)
	public Long count(Criteria c){
		@SuppressWarnings("static-access")
		ResultTransformer resultTransformer = c.ROOT_ENTITY;//�����ת��(Ŀ��������where��ѯ������������仹ԭ)
		
		c.setProjection(Projections.rowCount());
		Long result = (Long) c.uniqueResult();
		
		c.setProjection(null);
		c.setResultTransformer(resultTransformer);
		return result;
	}
	
	
	/**
	 * ����filterList��ѯ����(������ѯ)
	 * @param filterList
	 * @return
	 */
	public List<T> find(List<PropertyFilter> filterList){
		Criteria c = billderCriteriaByFilterList(filterList);
		return c.list();
	}
	
	/**
	 * ����filterList��ѯΨһ����(������ѯ)
	 * @param filterList
	 * @return
	 */
	public T findUnique(List<PropertyFilter> filterList){
		Criteria c = billderCriteriaByFilterList(filterList);
		return (T) c.uniqueResult();
		
	}
	
	/**
	 * �޷�ҳ:��where��ѯ����
	 * @param filterList  ����������
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return ����һ�����е��ֶβ�ѯ
	 */
	public List<T> findListByPropertyfilter(List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList);
		//����
		if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
			if(orderByType.equalsIgnoreCase("desc")){
				c.addOrder(Order.desc(orderByProperty));
			}else if(orderByType.equalsIgnoreCase("asc")){
				c.addOrder(Order.asc(orderByProperty));
			}
		}
		return c.list();

	}
	
	//��ҳʱ��û��where��ѯ����
	 public Page<T> findPageBypageNo(int pageNo){
		Criteria c = getSession().createCriteria(clazz);//û��where��ѯ����
		int totalSize = count(c).intValue();//?
		Page<T> page = new Page<T>(pageNo,totalSize);
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getPageSize());
		
		List<T> result = c.list();
		page.setItems(result);
		return page;
	}
	 

	//��ҳ��û��where��ѯ����������������
	 public Page<T> findPageBypageNo(int pageNo,String orderByProperty, String orderByType){
			Criteria c = getSession().createCriteria(clazz);
			int totalSize = count(c).intValue();//?
			Page<T> page = new Page<T>(pageNo,totalSize);
			c.setFirstResult(page.getStart());
			c.setMaxResults(page.getPageSize());
			
			if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
				if(orderByType.equalsIgnoreCase("desc")){
					c.addOrder(Order.desc(orderByProperty));
				}else if(orderByType.equalsIgnoreCase("asc")){
					c.addOrder(Order.asc(orderByProperty));
				}
			}
			
			List<T> result = c.list();
			page.setItems(result);
			return page;
		}

	/**
	 * ��ҳ:��where��ѯ����
	 * @param pageNo
	 * @param filterList  ����������
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return ����һ�����е��ֶβ�ѯ
	 */
	public Page<T> findPageByPropertyFilterAndPageNo(int pageNo,List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList);
		int totalSize = count(c).intValue();
		Page<T> page = new Page<T>(pageNo,totalSize);
		//��ҳ
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getPageSize());
		
		//����
		if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
			if(orderByType.equalsIgnoreCase("desc")){
				c.addOrder(Order.desc(orderByProperty));
			}else if(orderByType.equalsIgnoreCase("asc")){
				c.addOrder(Order.asc(orderByProperty));
			}
		}
		
		
		List<T> result = c.list();
		page.setItems(result);
		return page;
	}	
	
	/**
	 * �޷�ҳ:��where��ѯ����
	 * @param filterList  ����������
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return ���ݲ�ͬ���е��ֶβ�ѯ
	 */
	public List<T> findListByPropertyfilter(Criteria cri,List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList,cri);
		//����
		if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
			if(orderByType.equalsIgnoreCase("desc")){
				c.addOrder(Order.desc(orderByProperty));
			}else if(orderByType.equalsIgnoreCase("asc")){
				c.addOrder(Order.asc(orderByProperty));
			}
		}		
		return c.list();
	}
	
	/**
	 * ��ҳ:��where��ѯ����
	 * @param pageNo
	 * @param filterList  ����������
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return ���ݲ�ͬ���е��ֶβ�ѯ
	 */
	public Page<T> findPageByPropertyFilterAndPageNo(Criteria cri,int pageNo,List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList,cri);
		int totalSize = count(c).intValue();
		Page<T> page = new Page<T>(pageNo,totalSize);
		//��ҳ
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getPageSize());
		
		//����
		if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
			if(orderByType.equalsIgnoreCase("desc")){
				c.addOrder(Order.desc(orderByProperty));
			}else if(orderByType.equalsIgnoreCase("asc")){
				c.addOrder(Order.asc(orderByProperty));
			}
		}
		
		
		List<T> result = c.list();
		page.setItems(result);
		return page;
	}

	/**
	 * //���������������в�ѯ
	 * Criteria�����ṩ��һ���������ķ�ʽ��ѯ���ݿ�,��session����ȡ
	 * Restrictions���� :Restrictions.eq ...
	 * Projections���� :Projec'ons.sum ...
	 * @param filterList
	 * @return ����һ�����е��ֶβ�ѯ
	 */
	private Criteria billderCriteriaByFilterList(List<PropertyFilter> filterList) {
		Criteria c = getSession().createCriteria(clazz);
		for(PropertyFilter filter:filterList){
			
			//�ж��Ƿ�_or_
			if(filter.getPropertyname().contains("_or_")){
				String[] paramNames = filter.getPropertyname().split("_or_");
				
				Disjunction dis = Restrictions.disjunction();//������������������Ķ���or��ϵ(����Criterion������)
				
				for(String paramName:paramNames){
					//�жϹ�������
					dis.add(BuilderCriterion(paramName,filter.getFiltertype(),filter.getValue()));
				}
				c.add(dis);
			}else{
				//�жϹ�������
				c.add(BuilderCriterion(filter.getPropertyname(),filter.getFiltertype(),filter.getValue()));
			}
			
		}
		return c;
	}
	
	//?�б�����������:���ݲ�ͬ���е��ֶβ�ѯ
	private Criteria billderCriteriaByFilterList(List<PropertyFilter> filterList,Criteria c) {
		for(PropertyFilter filter:filterList){
			
			//�ж��Ƿ�_or_
			if(filter.getPropertyname().contains("_or_")){
				String[] paramNames = filter.getPropertyname().split("_or_");
				
				Disjunction dis = Restrictions.disjunction();//������������������Ķ���or��ϵ(����Criterion������)
				
				for(String paramName:paramNames){
					//�жϹ�������
					dis.add(BuilderCriterion(paramName,filter.getFiltertype(),filter.getValue()));
				}
				c.add(dis);
			}else{
				//�жϹ�������
				c.add(BuilderCriterion(filter.getPropertyname(),filter.getFiltertype(),filter.getValue()));
			}
			
		}
		return c;
	}
	
	
	
	/**
	 * Criterion ����һ��������ѯ
	 * Restrictions ����������ѯ�Ĺ�����
	 * @param propertyName
	 * @param filterType
	 * @param value
	 * @return
	 */
	private Criterion BuilderCriterion(String propertyName,String filterType,Object value){
		if(filterType.equalsIgnoreCase("eq")){//==
			return Restrictions.eq(propertyName,value);
		}else if(filterType.equalsIgnoreCase("gt")){//��
			return Restrictions.gt(propertyName,value);
		}else if(filterType.equalsIgnoreCase("lt")){//��
			return Restrictions.lt(propertyName,value);
		}else if(filterType.equalsIgnoreCase("ge")){//��
			return Restrictions.ge(propertyName,value);
		}else if(filterType.equalsIgnoreCase("le")){
			return Restrictions.le(propertyName,value);
		}else if(filterType.equalsIgnoreCase("like")){
			return Restrictions.like(propertyName,value.toString(),MatchMode.ANYWHERE);
		}else if(filterType.equalsIgnoreCase("between")){
			String[] values = value.toString().split("-");//��ѯ����ֵΪ2014/07/01-2014/07/25
			String lo = DateTimeUtil.get(values[0]);
			String hi = DateTimeUtil.get(values[1]);
			if(lo.equals(hi)){
				return Restrictions.eq(propertyName,lo);
			}else{
				return Restrictions.between(propertyName, lo, hi);
			}
		
		}
		return null;
	}
	
}
