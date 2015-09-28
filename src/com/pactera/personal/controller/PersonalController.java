package com.pactera.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pactera.personal.service.PersonalService;

@Controller
public class PersonalController {

	@Autowired
	private PersonalService personalService;

	@RequestMapping("/personal/index.do")
	public String index() {
		this.personalService.indexService();
		return "/personal/index";
	}

	@RequestMapping("/personal/blog.do")
	public String blog() {
		return "/personal/blog";
	}

	@RequestMapping("/personal/regist.do")
	public String regist() {
		return "/personal/regist";
	}

	@RequestMapping("/personal/recruit.do")
	public String recruit() {
		return "/personal/recruit";
	}

	public PersonalService getPersonalService() {
		return personalService;
	}

	public void setPersonalService(PersonalService personalService) {
		this.personalService = personalService;
	}

}
