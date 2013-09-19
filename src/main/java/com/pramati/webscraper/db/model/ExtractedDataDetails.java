package com.pramati.webscraper.db.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ExtractedDataDetails")
public class ExtractedDataDetails  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8476361648715633075L;

	@Id
	private String id;

	@Indexed(unique = true)
	private String url;
	
	private int statusCode;
	
	private String htmlData;	
	
	private String createdDate;
	
	private String modifiedDate;
	
	private String createdby;
	
	private String modifiedBy;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	/**
	 * @return the htmlData
	 */
	public String getHtmlData() {
		return htmlData;
	}

	/**
	 * @param htmlData the htmlData to set
	 */
	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}
	
	



	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public String getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public ExtractedDataDetails(){
		
	}
	
	



public ExtractedDataDetails(String url, int statusCode, String htmlData, String createdDate, String createdby) {
		super();
		this.url = url;
		this.statusCode = statusCode;
		this.htmlData = htmlData;
		this.createdDate = createdDate;
		this.createdby = createdby;
	}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "ExtractedDataDetails [id=" + id + ", url=" + url + ", statusCode=" + statusCode + ",  htmlData=" + htmlData
					+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", createdby=" + createdby
					+ ", modifiedBy=" + modifiedBy + "]";
}

	
	

}
