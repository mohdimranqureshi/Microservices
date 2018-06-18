package com.xavient.cop_training_service.repository;

import com.xavient.cop_training_service.model.TopicName;

public interface TrainingRepository {
	
	public String getTrainingDetails(int copId);
	
	public String getAllTrainingDetails();
	
	public String saveTrainingDetails(TopicName topicName);
	
	public String getDetailsByTopicName(String copName);

}
