package com.pramati.webscraper.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.pramati.webscraper.db.model.ExtractedDataDetails;

public class WebScrapperDbDelegate {

	@Autowired
	MongoTemplate mongoTemplate;

	public void insertIntoExtractedDataDetails(ExtractedDataDetails details) {

		mongoTemplate.insert(details);

	}

	public List<ExtractedDataDetails> getAllRecordDetails() {

		return mongoTemplate.findAll(ExtractedDataDetails.class);
	}

	public void updateIntoExtractedDataDetails(ExtractedDataDetails details) {
		mongoTemplate.save(details);

	}

}
