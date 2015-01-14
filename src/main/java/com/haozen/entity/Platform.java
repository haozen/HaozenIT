package com.haozen.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 平台实体类
 * @author pp
 *
 */
@Entity
@Table(name="t_platform")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Platform extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String platurl;
	private String platregistermoney;
	private String platregisteraddress;
	private String platregistertime;
	private String platcompanyname;
	private String platformname;
	private String platcompanyaddress;
	private String plattel;
	private String platemail;
	private String platsitetypes;
	private String platlevel;
	private String platadvertisedata;
	private String platstoremoney;
	private String createtime;
	private City city;
	
	@ManyToOne
	@JoinColumn(name="cityid")
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getPlaturl() {
		return platurl;
	}
	public void setPlaturl(String platurl) {
		this.platurl = platurl;
	}
	public String getPlatregistermoney() {
		return platregistermoney;
	}
	public void setPlatregistermoney(String platregistermoney) {
		this.platregistermoney = platregistermoney;
	}
	public String getPlatregisteraddress() {
		return platregisteraddress;
	}
	public void setPlatregisteraddress(String platregisteraddress) {
		this.platregisteraddress = platregisteraddress;
	}
	public String getPlatregistertime() {
		return platregistertime;
	}
	public void setPlatregistertime(String platregistertime) {
		this.platregistertime = platregistertime;
	}
	public String getPlatcompanyname() {
		return platcompanyname;
	}
	public void setPlatcompanyname(String platcompanyname) {
		this.platcompanyname = platcompanyname;
	}
	public String getPlatformname() {
		return platformname;
	}
	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}
	public String getPlatcompanyaddress() {
		return platcompanyaddress;
	}
	public void setPlatcompanyaddress(String platcompanyaddress) {
		this.platcompanyaddress = platcompanyaddress;
	}
	public String getPlattel() {
		return plattel;
	}
	public void setPlattel(String plattel) {
		this.plattel = plattel;
	}
	public String getPlatemail() {
		return platemail;
	}
	public void setPlatemail(String platemail) {
		this.platemail = platemail;
	}
	public String getPlatsitetypes() {
		return platsitetypes;
	}
	public void setPlatsitetypes(String platsitetypes) {
		this.platsitetypes = platsitetypes;
	}
	public String getPlatlevel() {
		return platlevel;
	}
	public void setPlatlevel(String platlevel) {
		this.platlevel = platlevel;
	}
	public String getPlatadvertisedata() {
		return platadvertisedata;
	}
	public void setPlatadvertisedata(String platadvertisedata) {
		this.platadvertisedata = platadvertisedata;
	}
	public String getPlatstoremoney() {
		return platstoremoney;
	}
	public void setPlatstoremoney(String platstoremoney) {
		this.platstoremoney = platstoremoney;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
		
}