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
 *一个类的对象
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDao<T> {
	
	@Inject
	private SessionFactory sessionFactory;
	
	/**
	 * 在dao层
	 * 由父类反射一个类的对象
	 */
	private Class<?> clazz;
	public BaseDao(){
		ParameterizedType  ParameterizedType = (java.lang.reflect.ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<?>) ParameterizedType.getActualTypeArguments()[0];
	}
	

	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	//添加或修改一条记录
	public void save(T t){
		getSession().saveOrUpdate(t);
	}
	//删除一条记录
	public void del(Integer id){
		getSession().delete(findById(id));
	}
	
	//根据id查询一条记录
	public T findById(Integer id){
		return (T) getSession().get(clazz, id);
	}
	
	//查询所有的记录
	public List<T> findAll(){
		Criteria ct = getSession().createCriteria(clazz);
		return ct.list();
	}
	
	//查询所有的记录,并进行排序
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
	 * 根据属性名查询单一对象
	 */
	public T findObjectByProperty(String propertyName,Object value){
		Criteria ct = getSession().createCriteria(clazz);
		ct.add(Restrictions.eq(propertyName, value));
		return (T) ct.uniqueResult();
	}
	
	/*
	 * 根据属性名查询集合对象
	 */
	public List<T> findListByProperty(String propertyName,Object value){
		Criteria c = getSession().createCriteria(clazz);
		c.add(Restrictions.eq(propertyName, value));
		return c.list();
	}
	
	
	 
	
	//Projections:聚合函数查询count(*)
	public Long count(Criteria c){
		@SuppressWarnings("static-access")
		ResultTransformer resultTransformer = c.ROOT_ENTITY;//结果集转换(目的是利用where查询的条件，最后将其还原)
		
		c.setProjection(Projections.rowCount());
		Long result = (Long) c.uniqueResult();
		
		c.setProjection(null);
		c.setResultTransformer(resultTransformer);
		return result;
	}
	
	
	/**
	 * 根据filterList查询集合(搜索查询)
	 * @param filterList
	 * @return
	 */
	public List<T> find(List<PropertyFilter> filterList){
		Criteria c = billderCriteriaByFilterList(filterList);
		return c.list();
	}
	
	/**
	 * 根据filterList查询唯一对象(搜索查询)
	 * @param filterList
	 * @return
	 */
	public T findUnique(List<PropertyFilter> filterList){
		Criteria c = billderCriteriaByFilterList(filterList);
		return (T) c.uniqueResult();
		
	}
	
	/**
	 * 无分页:加where查询条件
	 * @param filterList  ：搜索条件
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return 根据一个表中的字段查询
	 */
	public List<T> findListByPropertyfilter(List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList);
		//排序
		if(StringUtils.isNotEmpty(orderByProperty)&&StringUtils.isNotEmpty(orderByType)){
			if(orderByType.equalsIgnoreCase("desc")){
				c.addOrder(Order.desc(orderByProperty));
			}else if(orderByType.equalsIgnoreCase("asc")){
				c.addOrder(Order.asc(orderByProperty));
			}
		}
		return c.list();

	}
	
	//分页时：没加where查询条件
	 public Page<T> findPageBypageNo(int pageNo){
		Criteria c = getSession().createCriteria(clazz);//没加where查询条件
		int totalSize = count(c).intValue();//?
		Page<T> page = new Page<T>(pageNo,totalSize);
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getPageSize());
		
		List<T> result = c.list();
		page.setItems(result);
		return page;
	}
	 

	//分页，没加where查询条件，并进行排序
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
	 * 分页:加where查询条件
	 * @param pageNo
	 * @param filterList  ：搜索条件
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return 根据一个表中的字段查询
	 */
	public Page<T> findPageByPropertyFilterAndPageNo(int pageNo,List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList);
		int totalSize = count(c).intValue();
		Page<T> page = new Page<T>(pageNo,totalSize);
		//分页
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getPageSize());
		
		//排序
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
	 * 无分页:加where查询条件
	 * @param filterList  ：搜索条件
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return 根据不同表中的字段查询
	 */
	public List<T> findListByPropertyfilter(Criteria cri,List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList,cri);
		//排序
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
	 * 分页:加where查询条件
	 * @param pageNo
	 * @param filterList  ：搜索条件
	 * @param orderByType : desc asc
	 * @param orderByProperty :prodPrice
	 * @return 根据不同表中的字段查询
	 */
	public Page<T> findPageByPropertyFilterAndPageNo(Criteria cri,int pageNo,List<PropertyFilter> filterList, String orderByProperty, String orderByType){
		Criteria c = billderCriteriaByFilterList(filterList,cri);
		int totalSize = count(c).intValue();
		Page<T> page = new Page<T>(pageNo,totalSize);
		//分页
		c.setFirstResult(page.getStart());
		c.setMaxResults(page.getPageSize());
		
		//排序
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
	 * //根据搜索条件进行查询
	 * Criteria对象提供了一种面向对象的方式查询数据库,由session来获取
	 * Restrictions对象 :Restrictions.eq ...
	 * Projections对象 :Projec'ons.sum ...
	 * @param filterList
	 * @return 根据一个表中的字段查询
	 */
	private Criteria billderCriteriaByFilterList(List<PropertyFilter> filterList) {
		Criteria c = getSession().createCriteria(clazz);
		for(PropertyFilter filter:filterList){
			
			//判断是否含_or_
			if(filter.getPropertyname().contains("_or_")){
				String[] paramNames = filter.getPropertyname().split("_or_");
				
				Disjunction dis = Restrictions.disjunction();//用这个对象连接起来的都是or关系(它是Criterion的子类)
				
				for(String paramName:paramNames){
					//判断过滤类型
					dis.add(BuilderCriterion(paramName,filter.getFiltertype(),filter.getValue()));
				}
				c.add(dis);
			}else{
				//判断过滤类型
				c.add(BuilderCriterion(filter.getPropertyname(),filter.getFiltertype(),filter.getValue()));
			}
			
		}
		return c;
	}
	
	//?有别名处理搜索:根据不同表中的字段查询
	private Criteria billderCriteriaByFilterList(List<PropertyFilter> filterList,Criteria c) {
		for(PropertyFilter filter:filterList){
			
			//判断是否含_or_
			if(filter.getPropertyname().contains("_or_")){
				String[] paramNames = filter.getPropertyname().split("_or_");
				
				Disjunction dis = Restrictions.disjunction();//用这个对象连接起来的都是or关系(它是Criterion的子类)
				
				for(String paramName:paramNames){
					//判断过滤类型
					dis.add(BuilderCriterion(paramName,filter.getFiltertype(),filter.getValue()));
				}
				c.add(dis);
			}else{
				//判断过滤类型
				c.add(BuilderCriterion(filter.getPropertyname(),filter.getFiltertype(),filter.getValue()));
			}
			
		}
		return c;
	}
	
	
	
	/**
	 * Criterion 代表一个条件查询
	 * Restrictions 产生条件查询的工具类
	 * @param propertyName
	 * @param filterType
	 * @param value
	 * @return
	 */
	private Criterion BuilderCriterion(String propertyName,String filterType,Object value){
		if(filterType.equalsIgnoreCase("eq")){//==
			return Restrictions.eq(propertyName,value);
		}else if(filterType.equalsIgnoreCase("gt")){//＞
			return Restrictions.gt(propertyName,value);
		}else if(filterType.equalsIgnoreCase("lt")){//＜
			return Restrictions.lt(propertyName,value);
		}else if(filterType.equalsIgnoreCase("ge")){//≥
			return Restrictions.ge(propertyName,value);
		}else if(filterType.equalsIgnoreCase("le")){
			return Restrictions.le(propertyName,value);
		}else if(filterType.equalsIgnoreCase("like")){
			return Restrictions.like(propertyName,value.toString(),MatchMode.ANYWHERE);
		}else if(filterType.equalsIgnoreCase("between")){
			String[] values = value.toString().split("-");//查询条件值为2014/07/01-2014/07/25
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
