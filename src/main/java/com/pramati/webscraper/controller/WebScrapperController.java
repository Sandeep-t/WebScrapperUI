package com.pramati.webscraper.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pramati.webscraper.model.WebScrapperData;
import com.pramati.webscraper.service.LuceneSearchService;
import com.pramati.webscraper.service.WebScrapperService;

/**
 * @author Sandeep-t
 * 
 */

@Controller
public class WebScrapperController {

	@Autowired
	LuceneSearchService service;
	
	@Autowired
	WebScrapperService webScraperService;
	
	

	@RequestMapping(value = "/searchForData", method = RequestMethod.POST)
	public ModelAndView searchForText(@ModelAttribute("contact") WebScrapperData searchedText, BindingResult result)
					throws JSONException, ParseException, IOException {
		
		List<JSONObject> searchResultJson = service.searchForData(searchedText.getSearchString());

		return new ModelAndView("searchResult", "searchResultJson",searchResultJson);

	}

	@RequestMapping("/searchData")
	public ModelAndView searchForData() throws JSONException, FileNotFoundException, MalformedURLException, InterruptedException, ExecutionException, IOException, ParseException {
		//webScraperService.startWebScrapping();		
		return new ModelAndView("search", "command", new WebScrapperData());
	}
	
	/*@RequestMapping("/searchData")
	public ModelAndView startWebScrapping() throws JSONException, FileNotFoundException, MalformedURLException, InterruptedException, ExecutionException, IOException, ParseException {
		//webScraperService.startWebScrapping();		
		return new ModelAndView("search", "command", new WebScrapperData());
	}*/

}
