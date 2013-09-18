package com.pramati.webscraper.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pramati.webscraper.db.model.ExtractedDataDetails;
import com.pramati.webscraper.delegate.LuceneTaskDelegate;
import com.pramati.webscraper.utils.WebScrapperJsonUtil;

public class LuceneSearchService {
	
	
	@Autowired
	LuceneTaskDelegate helper;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	private static final Logger LOGGER= Logger.getLogger(LuceneSearchService.class);
	
	
	public List<JSONObject> searchForData(String queryString) throws ParseException, IOException, JSONException {
		
		//ApplicationContext  ctx = new ClassPathXmlApplicationContext("classpath:META-INF/WebScrapperAppContext.xml");

		//WebScrapperService webScraperService = (WebScrapperService) ctx.getBean("webScraperService");
		
		LOGGER.info("Search Started for " + queryString);
		
		List<Document> responseList = helper.searchForString(queryString);
		
		LOGGER.debug("Total search results for the String " + queryString + " is " + responseList.size());
		
		List<ObjectId> objectIdList = new ArrayList<ObjectId>();

		for (Document doc : responseList) {
			
			objectIdList.add(new ObjectId(doc.get("docId")));

		}
		Query searchUserQuery = new Query(Criteria.where("id").in(objectIdList));
		
		List<ExtractedDataDetails> htmlDataList = mongoTemplate.find(searchUserQuery, ExtractedDataDetails.class);
		
		LOGGER.debug("Total number of results from DataBase  " + htmlDataList.size());
		
		List<JSONObject> searchResultJson = new ArrayList<JSONObject>();
		
		if (htmlDataList.size() > 0) {
			for (ExtractedDataDetails individualResult : htmlDataList) {
				searchResultJson.add(WebScrapperJsonUtil.toJsonString(individualResult));
			}
			
		}
		else {
			LOGGER.info("No records found for the search of the text " + queryString);
		}
		return searchResultJson;
	}
	
	public void startIndexing(){
		helper.startIndexer();
	}
	
	
	/*private JSONObject toJsonString(ExtractedDataDetails searchResult) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("htmlWebAddress", searchResult.getUrl());
		jsonObj.put("htmlData", searchResult.getHtmlData());
		
		return jsonObj;
	}
	
	private ExtractedDataDetails toObjectString(JSONObject jsonObj ) throws JSONException {
	ExtractedDataDetails details= new ExtractedDataDetails();	
	details.setUrl(jsonObj.get("htmlWebAddress").toString());
	details.setUrl(jsonObj.get("htmlData").toString());;
		
		return details;
	}*/

}
