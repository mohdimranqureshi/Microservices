package com.xavient.cop_training_service.model;

import java.util.List;

public class TopicName {

	private String topicName;
	private List<TrainerDetails> trainerDetails;
	private String url;
	private String copId;
	private String trainingLevel;
	
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public List<TrainerDetails> getTrainerDetails() {
		return trainerDetails;
	}
	public void setTrainerDetails(List<TrainerDetails> trainerDetails) {
		this.trainerDetails = trainerDetails;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCopId() {
		return copId;
	}
	public void setCopId(String copId) {
		this.copId = copId;
	}
	public String getTrainingLevel() {
		return trainingLevel;
	}
	public void setTrainingLevel(String trainingLevel) {
		this.trainingLevel = trainingLevel;
	}
	
	
	
	

	
}
