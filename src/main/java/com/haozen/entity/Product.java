package com.haozen.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 产品实体类
 * @author pp
 *
 */
@Entity
@Table(name="t_product")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Product extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String profinanace;
	private String proweek;
	private String prorate;
	private String probid;
	private String prostate;
	private String createtime;
	private Platform platform;
	
	public String getProfinanace() {
		return profinanace;
	}
	public void setProfinanace(String profinanace) {
		this.profinanace = profinanace;
	}
	public String getProweek() {
		return proweek;
	}
	public void setProweek(String proweek) {
		this.proweek = proweek;
	}
	public String getProrate() {
		return prorate;
	}
	public void setProrate(String prorate) {
		this.prorate = prorate;
	}
	public String getProbid() {
		return probid;
	}
	public void setProbid(String probid) {
		this.probid = probid;
	}
	public String getProstate() {
		return prostate;
	}
	public void setProstate(String prostate) {
		this.prostate = prostate;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	@ManyToOne
	@JoinColumn(name="platformid")
	public Platform getPlatform() {
		return platform;
	}
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

}
