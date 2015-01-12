package com.haozen.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * ������ѯ����������
 * Լ��:q_like_s_prodname_or_provider
 * ��q��ͷ,like�ǹ���������s�����ݻ�������,s�Ժ����������
 * @author wxj
 *
 */

public class PropertyFilter {
	private String propertyname;//������
	private String filtertype;//�������� eq(=) gt(��) lt(��) ge(��) le(��) between(and) in(...) and  or like
	private Object value;//ֵ

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
	
	//ͨ��request������ȡ�����url���������������:����������ɵļ���
	@SuppressWarnings("unchecked")
	public static List<PropertyFilter> builderPropertyFilterByRequest(HttpServletRequest request){
		List<PropertyFilter> list = new ArrayList<PropertyFilter>();
		
		Enumeration<String> e = request.getParameterNames();//�õ�url���������key
		while(e.hasMoreElements()){
			String paramName = e.nextElement();
			//�ж�key�Ƿ���q��ͷ;��keyȡ����ֵ�Ƿ�Ϊnull��'';key�Ƿ����or;�ж�key���ָ�ĳ���;�ж���������
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
						
						throw new IllegalArgumentException("��ѯ�����쳣"+paramName);
						
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
