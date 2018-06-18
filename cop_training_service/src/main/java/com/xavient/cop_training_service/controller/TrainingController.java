package com.xavient.cop_training_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xavient.cop_training_service.exception.TrainingException;
import com.xavient.cop_training_service.model.TopicName;
import com.xavient.cop_training_service.repository.TrainingRepository;
import com.xavient.cop_training_service.validation.TrainingValidator;

@RestController
public class TrainingController {

	@Autowired
	TrainingRepository trainingRepository;

	private static final Logger logger = LoggerFactory.getLogger(TrainingController.class);

	@RequestMapping(value = "getTrainingDetails", method = RequestMethod.GET)
	public ResponseEntity<String> getTrainingDetailsByCopId(@RequestParam(value = "copId", required = true) int copId) {

		logger.info("Starting TrainingController.getTrainingDetails()" + copId);

		if (copId <= 0) {

			throw new TrainingException("Cop ID is not valid");
		}
		String response = trainingRepository.getTrainingDetails(copId);
		logger.info("Leaving TrainingController.getTrainingDetails()" + copId);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "getAllTrainingDetails", method = RequestMethod.GET)
	public ResponseEntity<String> getAllTrainingDetails() {
		logger.info("Starting TrainingController.getAllTrainingDetails()");

		String response = trainingRepository.getAllTrainingDetails();
		logger.info("Leaving TrainingController.getAllTrainingDetails()");
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "saveTrainingDetails", method = RequestMethod.POST)
	public ResponseEntity<String> saveTrainingDetails(@RequestBody TopicName topicName) {

		logger.info("Starting TrainingController.saveTrainingDetails()");

		TrainingValidator validator = new TrainingValidator();
		validator.validate(topicName, null);

		String response = trainingRepository.saveTrainingDetails(topicName);

		logger.info("Leaving TrainingController.saveTrainingDetails()");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "getDetailsByTopicName", method = RequestMethod.GET)
	public ResponseEntity<String> getDetailsByTopicName(
			@RequestParam(value = "topicName", required = true) String topicName) {

		logger.info("Starting TrainingController.getDetailsByTopicName()");
		
		String response = trainingRepository.getDetailsByTopicName(topicName);
		
		logger.info("Leaving TrainingController.getDetailsByTopicName()");
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}
