package com.techelevator.npgeek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.model.Parks;
import com.techelevator.model.ParksDao;
import com.techelevator.model.Survey;
import com.techelevator.model.Weather;

@Controller
@Scope("session")
public class HomeController {

	@Autowired
	public ParksDao dao;
	
	@RequestMapping(value= {"/","/homePage"})
	public String displayHomePageRoot(HttpServletRequest request, HttpSession session) {
		request.setAttribute("allParks", dao.getAllParks());
		if(session.isNew()) {
			session.setAttribute("tempChange", "Fahrenheit");
		}
		return "homePage";
	}
	
	
	@RequestMapping(path="/detailPage", method=RequestMethod.GET)
	public String displayDetails(HttpServletRequest request, HttpSession session) {
		
		session.setAttribute("tempChange", request.getParameter("tempChange"));
		
		String parkCode = request.getParameter("code");
		for (Parks p : dao.getAllParks()) {
			if(p.getCode().equals(parkCode)) {
				request.setAttribute("park", p);
			}
		}

		Weather todayWeather = dao.getTodayWeather(parkCode);
			todayWeather.setForecast(todayWeather.getForecast().replaceAll(" ", ""));
			request.setAttribute("today", todayWeather);
			
		List<Weather> fourWeathers = dao.nextFour(parkCode);
				request.setAttribute("fourWeathers", fourWeathers);
		for (Weather w : fourWeathers) {
			w.setForecast(w.getForecast().replaceAll(" ", ""));
			if(w.getDay() == 2) {
					Weather day = w;
					request.setAttribute("day2", w);
				}
			if(w.getDay() == 3) {
				Weather day = w;
				request.setAttribute("day3", w);
			}
			if(w.getDay() == 4) {
				Weather day = w;
				request.setAttribute("day4", w);
			}
			if(w.getDay() == 5) {
				Weather day = w;
				request.setAttribute("day5", w);
			}
		}	
		
		return "detailPage";
	}
	

	
	@RequestMapping(path= "/survey", method=RequestMethod.POST)
	public String displayHomePage(@RequestParam String park,
								  @RequestParam String email,
								  @RequestParam String state,
								  @RequestParam String level,
								  @Valid @ModelAttribute("Survey") Survey surveyFormValues,
									BindingResult result,
									RedirectAttributes flash
								  ) {
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Survey", result);
			flash.addFlashAttribute("Survey", surveyFormValues);
			return "redirect:/survey";
		}
		
		Survey survey = new Survey();
		survey.setPark(park);
		survey.setEmail(email);
		survey.setState(state);
		survey.setLevel(level);
		dao.save(survey);
		return "redirect:/favoriteParks";
	}
	
	@RequestMapping(path= "/survey", method=RequestMethod.GET)
	public String displaySurveyPage(Model ModelHolder) {
		if( ! ModelHolder.containsAttribute("Survey")) {
			ModelHolder.addAttribute("Survey", new Survey());
		}
		ModelHolder.addAttribute("parks", dao.getAllParks());
		return "survey";
	}
	
	@RequestMapping("/favoriteParks")
	public String displayFavoriteParks(HttpServletRequest request) {
		List<Parks> parkList = dao.getFavoriteParks();
		request.setAttribute("favParks", parkList);
		return "favoriteParks";
	}

	
	
}
