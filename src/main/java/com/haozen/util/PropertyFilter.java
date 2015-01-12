package com.haozen.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 搜索查询条件帮助类
 * 约定:q_like_s_prodname_or_provider
 * 以q开头,like是过滤条件，s是数据基本类型,s以后的是属性名
 * @author wxj
 *
 */

public class PropertyFilter {
	private String propertyname;//属性名
	private String filtertype;//过滤条件 eq(=) gt(＞) lt(＜) ge(≥) le(≤) between(and) in(...) and  or like
	private Object value;//值

	public PropertyFilter() {}
	public PropertyFilter(String propertyname,String filtertype,Object value) {
		this.propertyname = propertyname;
		this.filtertype = filtertype;
		this.value = value;
	}
	
	public String getPropertyname() {
		return propertyname;
	}
	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}
	public String getFiltertype() {
		return filtertype;
	}
	public void setFiltertype(String filtertype) {
		this.filtertype = filtertype;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	//通过request方法获取请求的url，传入这个方法中:搜索条件组成的集合
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> builderPropertyFilterByRequest(HttpServletRequest request){
		List<PropertyFilter> list = new ArrayList<PropertyFilter>();
		
		Enumeration<String> e = request.getParameterNames();//得到url中请求参数key
		while(e.hasMoreElements()){
			String paramName = e.nextElement();
			//判断key是否已q开头;由key取出的值是否为null或'';key是否包含or;判断key被分割的长度;判断数据类型
			if(paramName.startsWith("q")){
				
				String value = request.getParameter(paramName);
				System.out.println(value);
				if(!value.equals("")&&value!=null){
					
					String[] temp = null;
					
					if(paramName.contains("_or_")){
						temp=paramName.split("_",4);
					}else{
						temp=paramName.split("_");
					}
					
					if(temp.length!=4){
						
						throw new IllegalArgumentException("查询条件异常"+paramName);
						
					}else{
						
						String propertyName = temp[3];
						String dataType = temp[2];
						String filterType = temp[1];
						
						Object v = null;
						if(dataType.equalsIgnoreCase("i")){
							v = Integer.valueOf(value);
						}else if(dataType.equalsIgnoreCase("s")){
							v = String.valueOf(value);
						}else if(dataType.equalsIgnoreCase("f")){
							v = Float.valueOf(value);
						}else if(dataType.equalsIgnoreCase("d")){
							v = Double.valueOf(value);
						}else if(dataType.equalsIgnoreCase("b")){
							v = Boolean.valueOf(value);
						}
						
						PropertyFilter filter = new PropertyFilter(propertyName,filterType,v);
						list.add(filter);
					}
					
				}
			}
		}
		
		return list;
	}
}
