package com.xavient.cop_training_service.validation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xavient.cop_training_service.exception.TrainingException;
import com.xavient.cop_training_service.model.TopicName;
import com.xavient.cop_training_service.model.TrainingDetails;

@Component
public class TrainingValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(TrainingValidator.class);

	@Override
	public boolean supports(Class<?> arg0) {

		return TrainingDetails.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors error) {

		logger.info("Inside TrainingValidator.validate() validating cop details Request");
		/*TrainingDetails trainingDetails = (TrainingDetails) object;

		List<TopicName> topicNameList = trainingDetails.getTopicNameList();

		if (((TopicName) topicNameList).getTopicName() == null
				|| ((TopicName) topicNameList).getTopicName().trim().length() <= 0) {
			throw new TrainingException("Topic Name can Not be null.");
		}

		if (((TopicName) topicNameList).getTrainersName() == null
				|| ((TopicName) topicNameList).getTrainersName().size() <= 0) {
			throw new TrainingException("Topic Name can Not be null.");
		}

		if (((TopicName) topicNameList).getUrl() == null || ((TopicName) topicNameList).getUrl().size() <= 0) {
			throw new TrainingException("Url can Not be null.");
		}*/
	}

}
