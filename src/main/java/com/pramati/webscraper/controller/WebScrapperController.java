package com.pramati.webscraper.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pramati.webscraper.delegate.WebScrapperDelegate;
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
	LuceneSearchService luceneService;

	@Autowired
	WebScrapperService webScraperService;
	
	@Autowired
	WebScrapperDelegate webScrapperDel;
	
	private static final Logger LOGGER = Logger.getLogger(WebScrapperController.class);

	@RequestMapping(value = "/searchForData", method = RequestMethod.POST)
	public ModelAndView searchForText(@ModelAttribute("contact") WebScrapperData searchedText, BindingResult result)
					throws JSONException, ParseException, IOException, InterruptedException, ExecutionException {

		if (searchedText.getWebScrapUrl().trim().length() > 0) {

			webScraperService.startWebScrapping(searchedText.getWebScrapUrl().trim());

			return new ModelAndView("search", "command", new WebScrapperData());

		}
		else {
			Set<String> nameSet= new HashSet<String>();
			List<JSONObject> searchResultJson = luceneService.searchForData(searchedText.getSearchString());
			for(JSONObject object:searchResultJson){
				String htmlData=(String) object.get("htmlData");
				nameSet.add(webScrapperDel.getUserName(htmlData));
			}
			LOGGER.debug("Names are "+nameSet.toString());
			return new ModelAndView("searchResult", "searchResultJson", nameSet);

		}

	}

	@RequestMapping("/searchData")
	public ModelAndView searchForData() throws JSONException, FileNotFoundException, MalformedURLException,
					InterruptedException, ExecutionException, IOException, ParseException {
		// webScraperService.startWebScrapping();
		return new ModelAndView("search", "command", new WebScrapperData());
	}

	/*
	 * @RequestMapping("/searchData") public ModelAndView startWebScrapping()
	 * throws JSONException, FileNotFoundException, MalformedURLException,
	 * InterruptedException, ExecutionException, IOException, ParseException {
	 * //webScraperService.startWebScrapping(); return new
	 * ModelAndView("search", "command", new WebScrapperData()); }
	 */

}
