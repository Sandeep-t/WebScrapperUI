package com.pramati.webscraper.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import com.pramati.webscraper.delegate.LuceneTaskDelegate;
import com.pramati.webscraper.delegate.WebScrapperDelegate;

public class WebScrapperService {

	@Autowired
	WebScrapperDelegate webScrapper;

	@Autowired
	ExecutorService executorService;

	@Autowired
	LuceneSearchService luceneSearch;

	@Autowired
	WebScrapperDbService dbService;

	
	@Autowired
	LuceneTaskDelegate luceneDelegate;
	
	

	private static final Logger LOGGER = Logger.getLogger(WebScrapperService.class);



	public WebScrapperService() {
		
	}

	public void startWebScrapping(String url) throws FileNotFoundException, MalformedURLException, InterruptedException,
					ExecutionException, IOException, ParseException, JSONException {

		LOGGER.debug("Processing Url  " + url);

		luceneSearch.startIndexing();

		webScrapper.startDBInsertionPooler();

		webScrapper.stratHtmlDataPooler();

		webScrapper.startScrapping(url);
		while(webScrapper.getNextPageDataQueue().size()>0 || webScrapper.getChildFutureList().size()>0|| luceneDelegate.getDataDetailsQueue().size()>0){
			Thread.sleep(5000);
		}
						

	}

}
