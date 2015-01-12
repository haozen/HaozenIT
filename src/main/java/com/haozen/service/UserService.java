package com.haozen.service;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.haozen.dao.UserDao;
import com.haozen.entity.User;
import com.haozen.util.DateTimeUtil;

@Named
@Transactional
public class UserService {
	
	@Inject
	private UserDao userDao;
	public void saveUser(User user){
		user.setCreatetime(DateTimeUtil.getNow());;
		user.setStatus("ok");
		userDao.save(user);
	}

}
