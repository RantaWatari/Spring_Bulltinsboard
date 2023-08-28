package com.tuyano.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tuyano.springboot.repositories.AccountDataRepository;
import com.tuyano.springboot.repositories.UserDataRepository;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

@Controller
public class BulletinBoardController {
	
	// テスト用のアカウント
	@PostConstruct
	public void init(){
		AccountData testAccount = new AccountData();
		testAccount.setName("testuser");
		testAccount.setPassWord("testpass");
		accountRepository.saveAndFlush(testAccount);
		
		AccountData testAccount2 = new AccountData();
		testAccount2.setName("watari");
		testAccount2.setPassWord("ranta");
		accountRepository.saveAndFlush(testAccount2);
		
		AccountData testAccount3 = new AccountData();
		testAccount3.setName("a");
		testAccount3.setPassWord("a");
		accountRepository.saveAndFlush(testAccount3);
	}
	
	@Autowired
	UserDataRepository repository;
	
	@Autowired
	AccountDataRepository accountRepository;
	
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(
			ModelAndView mav) {
			return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/home/{name}", method = RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("formModel") UserData userdata,
			@PathVariable String name,
			ModelAndView mav) {
	
		if(Certification.check(name, accountRepository.findAll())) {
			mav.setViewName("index");
			mav.addObject("datalist",DisplayModication.reverseSort(repository.findAll()));
			return mav;
		}else {
			return new ModelAndView("redirect:/login");
		}
		
		
	}

	@RequestMapping(value = "/POST", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView form(
			@ModelAttribute("formModel") UserData userdata, 
			ModelAndView mav) {
		
		if(Certification.check(userdata.getName(), accountRepository.findAll())) {
			String time = TimeGenerate.nowToString();
			//LocalDateTime time = TimeGenerate.now();
			userdata.setStringTime(time);
			repository.saveAndFlush(userdata);
			return new ModelAndView("redirect:/home/"+userdata.getName());
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView loginForm(
			@RequestParam String name,
			@RequestParam String password,
			ModelAndView mav) {
		if(Certification.admit(name, password, accountRepository.findAll()) != null) {
			
			return new ModelAndView("redirect:/home/"+name);
		
		}else {
			mav.setViewName("login");
			mav.addObject("msg","Not Acccount!!");
			return mav;
		}
				
		
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView logout(
			@RequestParam String name,
			ModelAndView mav) {

//		accountRepository.findByNameLike(name).setLoggedIn(false);
		System.out.println(accountRepository.findByNameLike(name).get(0).getName());
		System.out.println(accountRepository.findByName(name).getName()+" !");
		
		Certification.logout(name, accountRepository.findByNameLike(name));
		
		return new ModelAndView("redirect:/login");
	}
	
}




