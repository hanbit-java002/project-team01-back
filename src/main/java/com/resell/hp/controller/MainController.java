package com.resell.hp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resell.hp.service.MainService;

@RestController
public class MainController {
	
	@Autowired
	private MainService mainService;

}
