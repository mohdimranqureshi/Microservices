package com.xavient.cop_training_service.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.mysql.jdbc.Statement;
import com.xavient.cop_training_service.model.TopicName;
import com.xavient.cop_training_service.model.TrainerDetails;
import com.xavient.cop_training_service.model.TrainingDetails;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbctemplate;

	private static final Logger logger = LoggerFactory.getLogger(TrainingRepositoryImpl.class);

	Gson gson = new Gson();

	@Override
	public String getTrainingDetails(int copId) {

		logger.info("Starting TrainingRepositoryImpl.getTrainingDetails()");

		TrainingDetails trainingDetails = new TrainingDetails();

		String sql = "SELECT DISTINCT topic_name.topic_name, topic_name.training_level,trainer.trainer_name,trainer.trainer_prefrence,training_material.material_url FROM topic_name INNER JOIN trainer ON topic_name.topic_id = trainer.topic_id INNER JOIN training_material ON topic_name.topic_id = training_material.topic_id WHERE  topic_name.cop_id = ?";
		jdbctemplate.query(sql, new Object[] { copId }, new ResultSetExtractor<TrainingDetails>() {

			@Override
			public TrainingDetails extractData(ResultSet rs) throws SQLException, DataAccessException {

				HashMap<String, TopicName> hMap = new HashMap<String, TopicName>();

				while (rs.next()) {

					TopicName topicName = hMap.get(rs.getString("topic_name"));

					if (topicName == null) {
						topicName = new TopicName();
						hMap.put(rs.getString("topic_name"), topicName);
					}
					topicName.setTopicName(rs.getString("topic_name"));
					topicName.setUrl(rs.getString("material_url"));
					topicName.setTrainingLevel(rs.getString("training_level"));

					if (topicName.getTrainerDetails() == null) {
						topicName.setTrainerDetails(new ArrayList<TrainerDetails>());
					}
					TrainerDetails trainerDetails = new TrainerDetails();
					trainerDetails.setTrainerName(rs.getString("trainer_name"));
					trainerDetails.setTrainerPrefrence(rs.getString("trainer_prefrence"));

					topicName.getTrainerDetails().add(trainerDetails);

					/*
					 * if (topicName.getTrainersName() == null) {
					 * topicName.setTrainersName(new ArrayList<>()); }
					 * topicName.getTrainersName().add(rs.getString(
					 * "trainer_name"));
					 * 
					 * if (topicName.getUrl() == null) { topicName.setUrl(new
					 * ArrayList<>()); }
					 * topicName.getUrl().add(rs.getString("material_url"));
					 */
				}

				trainingDetails.setTopicNameList(hMap.values().stream().collect(Collectors.toList()));
				logger.info("Leaving TrainingRepositoryImpl.getTrainingDetails()");
				return trainingDetails;
			}

		});

		if (trainingDetails.getTopicNameList().size() <= 0) {
			return gson.toJson("No Record Found.");
		} else {
			return gson.toJson(trainingDetails);
		}
	}

	@Override
	public String getAllTrainingDetails() {

		logger.info("Starting TrainingRepositoryImpl.getAllTrainingDetails()");

		TrainingDetails trainingDetails = new TrainingDetails();

		String sql = "SELECT DISTINCT topic_name.topic_name,topic_name.training_level,trainer.trainer_name,trainer.trainer_prefrence,training_material.material_url FROM topic_name INNER JOIN trainer ON topic_name.topic_id = trainer.topic_id INNER JOIN training_material ON topic_name.topic_id = training_material.topic_id";
		jdbctemplate.query(sql, new ResultSetExtractor<TrainingDetails>() {

			@Override
			public TrainingDetails extractData(ResultSet rs) throws SQLException, DataAccessException {

				HashMap<String, TopicName> hMap = new HashMap<String, TopicName>();

				while (rs.next()) {

					TopicName topicName = hMap.get(rs.getString("topic_name"));

					if (topicName == null) {
						topicName = new TopicName();
						hMap.put(rs.getString("topic_name"), topicName);
					}
					topicName.setTopicName(rs.getString("topic_name"));
					topicName.setUrl(rs.getString("material_url"));
					topicName.setTrainingLevel(rs.getString("training_level"));

					if (topicName.getTrainerDetails() == null) {
						topicName.setTrainerDetails(new ArrayList<TrainerDetails>());
					}
					TrainerDetails trainerDetails = new TrainerDetails();
					trainerDetails.setTrainerName(rs.getString("trainer_name"));
					trainerDetails.setTrainerPrefrence(rs.getString("trainer_prefrence"));

					topicName.getTrainerDetails().add(trainerDetails);
				}

				trainingDetails.setTopicNameList(hMap.values().stream().collect(Collectors.toList()));
				logger.info("Leaving TrainingRepositoryImpl.getAllTrainingDetails()");
				return trainingDetails;
			}

		});

		if (trainingDetails.getTopicNameList().size() <= 0) {
			return gson.toJson("No Record Found.");
		} else {
			return gson.toJson(trainingDetails);
		}

	}

	@Override
	public String saveTrainingDetails(TopicName topicName) {

		logger.info("Starting TrainingRepositoryImpl.saveTrainingDetails()");

		String response = "";
		int i = 0, j = 0, k = 0;
		GeneratedKeyHolder holder = new GeneratedKeyHolder();

		i = jdbctemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement preparedStatement = con.prepareStatement(
						"INSERT INTO topic_name(cop_id,topic_name, training_level) VALUES(?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, topicName.getCopId());
				preparedStatement.setString(2, topicName.getTopicName());
				preparedStatement.setString(3, topicName.getTrainingLevel());
				return preparedStatement;
			}
		}, holder);

		int key = holder.getKey().intValue();
		for (TrainerDetails trainerDetails : topicName.getTrainerDetails()) {
			j = jdbctemplate.update(new PreparedStatementCreator() {

				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement preparedStatement = con.prepareStatement(
							"INSERT INTO trainer (topic_id, trainer_name, trainer_prefrence) VALUES(?,?,?)");
					preparedStatement.setInt(1, key);
					preparedStatement.setString(2, trainerDetails.getTrainerName());
					preparedStatement.setString(3, trainerDetails.getTrainerPrefrence());
					return preparedStatement;
				}
			});
		}
		String url = topicName.getUrl();
		k = jdbctemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

				PreparedStatement preparedStatement = con
						.prepareStatement("INSERT into training_material (topic_id, material_url) VALUES(?,?)");
				preparedStatement.setInt(1, key);
				preparedStatement.setString(2, url);
				return preparedStatement;
			}
		});

		if (i > 0 && j > 0 && k > 0) {
			response = "Record Added";
		} else {
			logger.error("Error to add data TrainingRepositoryImpl.saveTrainingDetails(): " + i, j, k);
			response = "An Error Occured";
		}
		return gson.toJson(response);
	}

	@Override
	public String getDetailsByTopicName(String topicName) {

		logger.info("Starting TrainingRepositoryImpl.updateTrainingDetails()");

		List<Integer> idList = new ArrayList<>();
		List<TrainingDetails> trainingDetailsList = new ArrayList<TrainingDetails>();

		String topicIdSql = "SELECT topic_id from topic_name where topic_name LIKE ?";

		jdbctemplate.query(topicIdSql, new Object[] { "%" + topicName + "%" }, new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					int id = rs.getInt("topic_id");
					idList.add(id);
				}
				return null;
			}

		});

		for (int id : idList) {
			TrainingDetails trainingDetails = new TrainingDetails();

			String sql = "SELECT DISTINCT topic_name.topic_name, topic_name.training_level,trainer.trainer_name,trainer.trainer_prefrence,training_material.material_url FROM topic_name INNER JOIN trainer ON topic_name.topic_id = trainer.topic_id INNER JOIN training_material ON topic_name.topic_id = training_material.topic_id WHERE  topic_name.topic_id = ?";

			jdbctemplate.query(sql, new Object[] { id }, new ResultSetExtractor<TrainingDetails>() {

				@Override
				public TrainingDetails extractData(ResultSet rs) throws SQLException, DataAccessException {

					HashMap<String, TopicName> hMap = new HashMap<String, TopicName>();

					while (rs.next()) {

						TopicName topicName = hMap.get(rs.getString("topic_name"));

						if (topicName == null) {
							topicName = new TopicName();
							hMap.put(rs.getString("topic_name"), topicName);
						}
						topicName.setTopicName(rs.getString("topic_name"));
						topicName.setUrl(rs.getString("material_url"));
						topicName.setTrainingLevel(rs.getString("training_level"));

						if (topicName.getTrainerDetails() == null) {
							topicName.setTrainerDetails(new ArrayList<TrainerDetails>());
						}
						TrainerDetails trainerDetails = new TrainerDetails();
						trainerDetails.setTrainerName(rs.getString("trainer_name"));
						trainerDetails.setTrainerPrefrence(rs.getString("trainer_prefrence"));

						topicName.getTrainerDetails().add(trainerDetails);

					}
					trainingDetails.setTopicNameList(hMap.values().stream().collect(Collectors.toList()));
					logger.info("Leaving TrainingRepositoryImpl.getTrainingDetails()");
					return trainingDetails;
				}
			});
			trainingDetailsList.add(trainingDetails);
		}
		logger.info("Leaving TrainingRepositoryImpl.updateTrainingDetails()");
		return gson.toJson(trainingDetailsList);
	}
}
