package com.pactera.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogContraller {

	@RequestMapping("/blog/index.do")
	public String index(){
		return "/blog/index";
	}
}
