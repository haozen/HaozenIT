package com.haozen.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="t_city")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class City extends IdEntity{

	
	private static final long serialVersionUID = 1L;
	private String city;
	private Set<Platform> addressSet;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@OneToMany(mappedBy="city",fetch =FetchType.LAZY)//放弃关系维护
	@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
	public Set<Platform> getAddressSet() {
		return addressSet;
	}

	public void setAddressSet(Set<Platform> addressSet) {
		this.addressSet = addressSet;
	}
	
	
	
	
	
	
	
}
