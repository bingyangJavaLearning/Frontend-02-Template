package com.traincamp.homework10;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UserService {

	@Resource
	UserDao userDao;
	
	public void insert(boolean haveError) throws SQLException {
		userDao.insert(new User(8, "username10", "password10"));
		if (haveError) {
			throw new SQLException();
		}
	}
	
}
