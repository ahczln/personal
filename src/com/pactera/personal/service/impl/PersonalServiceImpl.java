package com.pactera.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.personal.dao.PersonalDao;
import com.pactera.personal.service.PersonalService;

@Service
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao personalDao;

	public void indexService() {
		this.personalDao.indexDao();
	}

	public PersonalDao getPersonalDao() {
		return personalDao;
	}

	public void setPersonalDao(PersonalDao personalDao) {
		this.personalDao = personalDao;
	}
}
