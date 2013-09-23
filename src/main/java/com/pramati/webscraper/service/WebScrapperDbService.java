package com.pramati.webscraper.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DeadlockLoserDataAccessException;

import com.pramati.webscraper.db.model.ExtractedDataDetails;
import com.pramati.webscraper.delegate.WebScrapperDbDelegate;
import com.pramati.webscraper.delegate.LuceneTaskDelegate;
import com.pramati.webscraper.utils.HTMLLinkExtractor;

public class WebScrapperDbService {
	
	
	private static final Logger LOGGER= Logger.getLogger(WebScrapperDbService.class);
	
	@Autowired
	WebScrapperDbDelegate dbDelegate;
	
	@Autowired
	LuceneTaskDelegate helper;
	
	@Autowired
	HTMLLinkExtractor linkExtractor;
	
	@Autowired
	ExecutorService executorService;
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private BlockingQueue<ExtractedDataDetails> updatedDataQueue = new LinkedBlockingQueue<ExtractedDataDetails>();
	
	public void insertExtractedData(ExtractedDataDetails detail) throws IOException, InterruptedException{
		
		dbDelegate.insertIntoExtractedDataDetails(detail);
		
		helper.addTodataDetailsQueue(detail);
		
	}
	
	
	public List<ExtractedDataDetails> getAllRecords(){
		
		return dbDelegate.getAllRecordDetails();
		
	}
	
	
	public void updateExtractedDataDetails(){

		List<ExtractedDataDetails> allRecordsList=dbDelegate.getAllRecordDetails();
		
		for(ExtractedDataDetails details:allRecordsList){
			String authorName=linkExtractor.grabNames(details.getHtmlData());
			String subject=linkExtractor.grabSubject(details.getHtmlData());
			details.setAuthorName(authorName);
			details.setMailSubject(subject);
			details.setModifiedDate(dateFormat.format(new Date()));
			details.setModifiedBy("WebSearcher");
			updatedDataQueue.add(details);
			
			
		}
		
	}
	
	
	
	public void stratupdatedDataPooler() throws FileNotFoundException {

		Runnable pooler = new Runnable() {
			@Override
			public void run() {

				while (!Thread.interrupted()) {

					ExtractedDataDetails details;

					while ((details = updatedDataQueue.poll()) != null) {
						
						System.out.println("Qsize "+updatedDataQueue.size());
						
						dbDelegate.updateIntoExtractedDataDetails(details);
					}
				}
			}
		};

		executorService.execute(pooler);
	}
	
	
	
	

}
