package com.rsh.project.controller;

import com.rsh.project.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	

	@Autowired
	public HomeController(UserRepository userRepository){

	}
	
	@RequestMapping("/")
	public String home(Model model){
//		model.addAttribute("person", .. currently loged in person ..);
		return "index";
	}
	
}
