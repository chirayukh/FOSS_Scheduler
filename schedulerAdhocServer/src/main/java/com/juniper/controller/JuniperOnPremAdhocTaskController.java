package com.juniper.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.juniper.dto.AdhocTask;
import com.juniper.dto.AdhocTaskSequence;
import com.juniper.dto.BatchTableDetailsDTO;
import com.juniper.dto.RequestDto;
import com.juniper.repository.JuniperOnPremAdhocTaskRepository;
import com.juniper.util.ResponseUtil;

@CrossOrigin
@RestController
public class JuniperOnPremAdhocTaskController {

	@Autowired
	JuniperOnPremAdhocTaskRepository Repository;

	/**
	 * @param requestDto
	 * @return String
	 * @throws SQLException
	 */
	@RequestMapping(value = "/saveBatchDetails", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String saveBatchDetails(@RequestBody RequestDto requestDto) throws SQLException {
		String status = "";
		String message = "";
		int project_sequence = 0;
		String cron = "";
		String schedule_type = requestDto.getBody().get("data").get("sch_type");
		BatchTableDetailsDTO batchTableDTO = new BatchTableDetailsDTO();

		try {
			batchTableDTO.setBATCH_NAME(requestDto.getBody().get("data").get("batch_name"));
			batchTableDTO.setBATCH_DESC(requestDto.getBody().get("data").get("batch_desc"));
			batchTableDTO.setSCHEDULE_FLAG(requestDto.getBody().get("data").get("sch_flag"));
			batchTableDTO.setSCHEDULE_TYPE(requestDto.getBody().get("data").get("sch_type"));
			batchTableDTO.setProject(requestDto.getBody().get("data").get("project"));
			batchTableDTO.setUser(requestDto.getBody().get("data").get("user"));
			project_sequence = Repository.getProjectSeq(requestDto.getBody().get("data").get("project"));
			batchTableDTO.setProject_sequence(project_sequence);

			if (schedule_type.equalsIgnoreCase("adhoc")) {

			} else if (schedule_type.toLowerCase().equalsIgnoreCase("regular")) {
				cron = requestDto.getBody().get("data").get("cron");
			} else if (schedule_type.equalsIgnoreCase("event_based")) {
				String schedule_flag = requestDto.getBody().get("data").get("sch_flag");
				if (schedule_flag.toUpperCase().equalsIgnoreCase("F")) {
					batchTableDTO.setArgument_1(requestDto.getBody().get("data").get("batch_name"));
					batchTableDTO.setArgument_3("$RUN_ID$");
					batchTableDTO.setArgument_4(requestDto.getBody().get("data").get("Filepath") + "/"
							+ requestDto.getBody().get("data").get("Filepattern"));
				} else if (schedule_flag.toUpperCase().equalsIgnoreCase("K")) {
					batchTableDTO.setArgument_1(requestDto.getBody().get("data").get("batch_name"));
					batchTableDTO.setArgument_3("$RUN_ID$");
					batchTableDTO.setArgument_4(requestDto.getBody().get("data").get("kafka_topic"));
				} else if (schedule_flag.toUpperCase().equalsIgnoreCase("A")) {
					batchTableDTO.setArgument_1(requestDto.getBody().get("data").get("batch_name"));
					batchTableDTO.setArgument_3("$RUN_ID$");
					batchTableDTO.setArgument_4(requestDto.getBody().get("data").get("api_unique_key"));
				}
			}
			message = Repository.saveBatchDetails(batchTableDTO, cron);
			if (message.equalsIgnoreCase("success")) {
				status = "Success";
				message = "Batch Added Successfully";
			} else {
				status = "Failed";
			}

		} catch (Exception e) {
			status = "Failed";
			message = "Exception occurred";
		}
		return ResponseUtil.createResponse(status, message);
	}

	/**
	 * @param requestDto
	 * @return String
	 * @throws SQLException
	 */
	@RequestMapping(value = "/addScheduleData", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String addScheduleData(@RequestBody RequestDto requestDto) throws SQLException {
		String status = "";
		String message = "";
		int file_count = 0;
		int project_sequence = 0;
		ArrayList<AdhocTask> AdhocTaskdtos = new ArrayList<AdhocTask>();
		Map<String, String> d = requestDto.getBody().get("data");
		String batch_id = "";
		if (!d.get("l2").equalsIgnoreCase("")) {
			batch_id = d.get("l2");
		} else if (!d.get("l1").equalsIgnoreCase("")) {
			batch_id = d.get("l1");
		}
		BatchTableDetailsDTO dto = new BatchTableDetailsDTO();
		try {
			project_sequence = Repository.getProjectSeq(requestDto.getBody().get("data").get("project"));

			for (int x = 1; x <= Integer.parseInt(d.get("counter")); x++) {
				String path = d.get("command" + x);
				File f = new File(path);
				if (f.exists() && !f.isDirectory()) {
					file_count++;
				}
			}

			if (file_count != Integer.parseInt(d.get("counter"))) {
				status = "Failed";
				message = "Command passed for one of the task does not exists, Failed to add task";
			} else {
				for (int x = 1; x <= Integer.parseInt(d.get("counter")); x++) {
					AdhocTask AdhocTaskdto = new AdhocTask();
					AdhocTaskdto.setJOB_ID(d.get("project") + "_" + d.get("l1") + "_" + d.get("job_id" + x));
					AdhocTaskdto.setJOB_NAME(d.get("job_name" + x));
					AdhocTaskdto.setBATCH_ID(batch_id);
					AdhocTaskdto.setCommand_type(d.get("command_type" + x));
					AdhocTaskdto.setCommand(d.get("command" + x));
					AdhocTaskdto.setArgument_1(d.get("argument_1" + x));
					AdhocTaskdto.setArgument_2(d.get("argument_2" + x));
					AdhocTaskdto.setArgument_3(d.get("argument_3" + x));
					AdhocTaskdto.setProject_sequence(project_sequence);
					AdhocTaskdto.setProject(d.get("project"));
					AdhocTaskdto.setUser(d.get("user"));
					AdhocTaskdtos.add(AdhocTaskdto);
				}

				dto = Repository.getBatchDetails(batch_id, project_sequence);
				message = Repository.addScheduleTable(AdhocTaskdtos, dto);
				if (message.equalsIgnoreCase("success")) {
					status = "Success";
					message = "Task Added Successfully";
				} else {
					status = "Failed";
				}
			}
		} catch (Exception e) {
			status = "Failed";
			message = e.getMessage();
			e.printStackTrace();

		}
		return ResponseUtil.createResponse(status, message);
	}

	/**
	 * @param requestDto
	 * @return String
	 * @throws SQLException
	 */
	@RequestMapping(value = "/editScheduleData", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String editScheduleData(@RequestBody RequestDto requestDto) throws SQLException {
		String status = "";
		String message = "";
		int file_count = 0;
		int project_sequence = 0;
		Map<String, String> d = requestDto.getBody().get("data");
		try {
			project_sequence = Repository.getProjectSeq(requestDto.getBody().get("data").get("project"));

			for (int x = 1; x <= Integer.parseInt(d.get("counter")); x++) {
				file_count++;
			}

			if (file_count != Integer.parseInt(d.get("counter"))) {
				status = "Failed";
				message = "Command passed for one of the task does not exists, Failed to add task";
			} else {

				AdhocTask AdhocTaskdto = new AdhocTask();
				AdhocTaskdto.setJOB_ID(d.get("project") + "_" + d.get("l2") + "_" + d.get("job_id1"));
				AdhocTaskdto.setJOB_NAME(d.get("job_name1"));
				AdhocTaskdto.setBATCH_ID(d.get("l2"));
				AdhocTaskdto.setCommand_type(d.get("command_type1"));
				AdhocTaskdto.setCommand(d.get("command1"));
				AdhocTaskdto.setArgument_1(d.get("argument_11"));
				AdhocTaskdto.setArgument_2(d.get("argument_21"));
				AdhocTaskdto.setArgument_3(d.get("argument_31"));
				AdhocTaskdto.setProject_sequence(project_sequence);
				AdhocTaskdto.setProject(d.get("project"));
				message = Repository.editAdhocScheduleData(AdhocTaskdto);
				if (message.equalsIgnoreCase("success")) {
					message = Repository.editScheduleTable(AdhocTaskdto);
					if (message.equalsIgnoreCase("success")) {
						status = "Success";
						message = "Task Updated Successfully";
					} else {
						status = "Failed";
					}
				} else {
					status = "Failed";
				}

			}
		} catch (Exception e) {
			status = "Failed";
			message = e.getMessage();
		}
		return ResponseUtil.createResponse(status, message);

	}

	/**
	 * @param requestDto
	 * @return String
	 * @throws SQLException
	 */
	@RequestMapping(value = "/editBatchDetails", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String editBatchDetails(@RequestBody RequestDto requestDto) throws SQLException {
		String status = "";
		String message = "";
		int project_sequence = 0;
		String cron = "";
		String schedule_type = requestDto.getBody().get("data").get("sch_type");
		BatchTableDetailsDTO batchTableDTO = new BatchTableDetailsDTO();

		String batch_unique_name = requestDto.getBody().get("data").get("batch_val2");
		try {
			batchTableDTO.setBATCH_NAME(requestDto.getBody().get("data").get("batch_val2"));
			batchTableDTO.setBATCH_DESC(requestDto.getBody().get("data").get("batch_desc"));
			batchTableDTO.setSCHEDULE_FLAG(requestDto.getBody().get("data").get("sch_flag"));
			batchTableDTO.setSCHEDULE_TYPE(requestDto.getBody().get("data").get("sch_type"));
			batchTableDTO.setProject(requestDto.getBody().get("data").get("project"));
			batchTableDTO.setUser(requestDto.getBody().get("data").get("user"));
			project_sequence = Repository.getProjectSeq(requestDto.getBody().get("data").get("project"));
			batchTableDTO.setProject_sequence(project_sequence);

			if (schedule_type.equalsIgnoreCase("adhoc")) {

			} else if (schedule_type.toLowerCase().equalsIgnoreCase("regular")) {
				cron = requestDto.getBody().get("data").get("cron");
			} else if (schedule_type.equalsIgnoreCase("event_based")) {
				String schedule_flag = requestDto.getBody().get("data").get("sch_flag");
				if (schedule_flag.toUpperCase().equalsIgnoreCase("F")) {
					batchTableDTO.setArgument_1(requestDto.getBody().get("data").get("batch_name"));
					batchTableDTO.setArgument_3("$RUN_ID$");
					batchTableDTO.setArgument_4(requestDto.getBody().get("data").get("Filepath") + "/"
							+ requestDto.getBody().get("data").get("Filepattern"));
				} else if (schedule_flag.toUpperCase().equalsIgnoreCase("K")) {
					batchTableDTO.setArgument_1(requestDto.getBody().get("data").get("batch_name"));
					batchTableDTO.setArgument_3("$RUN_ID$");
					batchTableDTO.setArgument_4(requestDto.getBody().get("data").get("kafka_topic"));
				} else if (schedule_flag.toUpperCase().equalsIgnoreCase("A")) {
					batchTableDTO.setArgument_1(requestDto.getBody().get("data").get("batch_name"));
					batchTableDTO.setArgument_3("$RUN_ID$");
					batchTableDTO.setArgument_4(requestDto.getBody().get("data").get("api_unique_key"));
				}

			}

			message = Repository.editBatchDetails(batch_unique_name, project_sequence);
			if (message.equalsIgnoreCase("success")) {
				message = Repository.saveBatchDetails(batchTableDTO, cron);
				if (message.equalsIgnoreCase("success")) {
					BatchTableDetailsDTO batchDTO = Repository.getBatchDetails(batch_unique_name, project_sequence);
					message = Repository.updateAdhocScheduleTable(batchDTO);
					if (message.equalsIgnoreCase("success")) {
						Repository.updateFinalScheduleTable(batchDTO);
						status = "Success";
						message = "Batch Updated Successfully";
					} else {
						status = "Failed";
						message = "Failed to edit the batch";
					}
				} else {
					status = "Failed";
					message = "Failed to edit the batch";
				}
			} else {
				status = "Failed";
				message = "Failed to edit the batch";
			}
		} catch (Exception e) {
			status = "Failed";
			message = e.getMessage();
		}
		return ResponseUtil.createResponse(status, message);
	}

	/**
	 * @param requestDto
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/sequenceSubmit", method = RequestMethod.POST, produces = "application/json")
	public String sequenceSubmit(@RequestBody RequestDto requestDto) throws Exception {
		String status = "";
		String message = "";
		String seqString = null;
		String job_name_old = "";
		String job_name = "";
		String predeccessor = "";
		int sequence = 1;
		String batch = "";
		ArrayList<AdhocTaskSequence> sequences = new ArrayList<AdhocTaskSequence>();

		Map<String, String> d = requestDto.getBody().get("data");
		String project = d.get("project");
		seqString = d.get("sequence");
		String[] value_split = seqString.split("\\|");
		if (!d.get("l2").equalsIgnoreCase("")) {
			batch = d.get("l2");
		} else if (!d.get("l1").equalsIgnoreCase("")) {
			batch = d.get("l1");
		}
		int project_sequence = 0;
		try {
			project_sequence = Repository.getProjectSeq(project);

			if (value_split.length == 1) {
				String[] value_split2 = seqString.split(",");
				for (int i = 0; i < value_split2.length; i++) {
					AdhocTaskSequence seq = new AdhocTaskSequence();
					job_name = value_split2[i];
					predeccessor = "";
					seq.setBatch(batch);
					seq.setJob_name(job_name);
					seq.setPredeccessor(predeccessor);
					seq.setSequence(sequence);
					seq.setProject(project);
					sequences.add(seq);
				}
			} else if (value_split.length > 1) {

				for (int i = 0; i < value_split.length; i++) {
					AdhocTaskSequence seq = new AdhocTaskSequence();
					if (value_split[i].contains(",")) {

						if (i == 0) {
							String[] value_split2 = value_split[i].split(",");
							if (value_split2.length > 1) {
								for (int j = 0; j < value_split2.length; j++) {
									job_name = value_split2[j];
									predeccessor = "";
									seq.setBatch(batch);
									seq.setJob_name(job_name);
									seq.setPredeccessor(predeccessor);
									seq.setSequence(sequence);
									seq.setProject(project);
									sequences.add(seq);
								}
							} else {
								job_name = value_split2[0];
								predeccessor = value_split2[1];
							}
						} else {
							String[] value_split2 = value_split[i].split(",");
							job_name = value_split2[0];
							predeccessor = value_split2[1];
						}
					} else {
						job_name = value_split[i];
						predeccessor = "";
					}
					if (job_name_old.equalsIgnoreCase(job_name) && predeccessor != "") {
						sequence++;
					} else {
						sequence = 1;
					}
					job_name_old = job_name;
					if (i == 0) {
						String[] value_split2 = value_split[i].split(",");
						if (value_split2.length > 1) {
							seq.setBatch(batch);
							seq.setJob_name(job_name);
							seq.setPredeccessor(predeccessor);
							seq.setSequence(sequence);
							seq.setProject(project);
							sequences.add(seq);
						}
					} else {
						seq.setBatch(batch);
						seq.setJob_name(job_name);
						seq.setPredeccessor(predeccessor);
						seq.setSequence(sequence);
						seq.setProject(project);
						sequences.add(seq);
					}
				}

			} else {
				status = "Failed";
				message = "Task were not selected";
			}
			if (!sequences.isEmpty()) {
				message = Repository.updateAdhocTaskPredeccessors(batch, project_sequence);
				if (message.equalsIgnoreCase("success")) {
					message = Repository.addAdhocTaskSequence(sequences);
					if (message.equalsIgnoreCase("success")) {
						message = Repository.deleteScheduleTable(batch, project_sequence);
						if (message.equalsIgnoreCase("success")) {
							message = Repository.updateScheduleTableFromAdhocTable(batch, project_sequence);
							if (message.equalsIgnoreCase("success")) {
								status = "Success";
								message = "Sequence Added Successfully";
							} else {
								status = "Failed";
								message = "Failed to add the sequence";
							}
						} else {
							status = "Failed";
							message = "Failed to add the sequence";
						}
					} else {
						status = "Failed";
						message = "Failed to add the sequence";
					}
				} else {
					status = "Failed";
					message = "Failed to add the sequence";
				}
			}
		} catch (Exception e) {
			status = "Failed";
			message = e.getMessage();
		}
		return ResponseUtil.createResponse(status, message);

	}

	/**
	 * @param requestDto
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/batchSequenceSubmit", method = RequestMethod.POST, produces = "application/json")
	public String batchSequenceSubmit(@RequestBody RequestDto requestDto) throws Exception {
		String status = "";
		String message = "";
		String seqString = null;
		String job_name_old = "";
		String job_name = "";
		String predeccessor = "";
		int sequence = 1;
		String batch = "";
		ArrayList<AdhocTaskSequence> sequences = new ArrayList<AdhocTaskSequence>();

		Map<String, String> d = requestDto.getBody().get("data");
		String project = d.get("project");
		seqString = d.get("sequence");
		String BATCH_SEQUENCE_ID = d.get("flow1");
		String flowname = d.get("flowname");
		String BATCH_TYPE = d.get("schtype");
		String[] value_split = seqString.split("\\|");
		batch = d.get("batchid");
		String button_type = d.get("button_type");
		int project_sequence = 0;
		try {
			project_sequence = Repository.getProjectSeq(project);
			if (value_split.length == 1) {
				String[] value_split2 = seqString.split(",");
				for (int i = 0; i < value_split2.length; i++) {
					AdhocTaskSequence seq = new AdhocTaskSequence();
					job_name = value_split2[i];
					predeccessor = "";
					seq.setBatch(batch);
					seq.setJob_name(job_name);
					seq.setPredeccessor(predeccessor);
					seq.setSequence(sequence);
					seq.setProject(project);
					sequences.add(seq);
				}
			} else if (value_split.length > 1) {

				for (int i = 0; i < value_split.length; i++) {
					AdhocTaskSequence seq = new AdhocTaskSequence();
					if (value_split[i].contains(",")) {

						if (i == 0) {
							String[] value_split2 = value_split[i].split(",");
							if (value_split2.length > 1) {
								for (int j = 0; j < value_split2.length; j++) {
									job_name = value_split2[j];
									predeccessor = "";
									seq.setBatch(batch);
									seq.setJob_name(job_name);
									seq.setPredeccessor(predeccessor);
									seq.setSequence(sequence);
									seq.setProject(project);
									sequences.add(seq);
								}
							} else {
								job_name = value_split2[0];
								predeccessor = value_split2[1];
							}
						} else {
							String[] value_split2 = value_split[i].split(",");
							job_name = value_split2[0];
							predeccessor = value_split2[1];
						}
					} else {
						job_name = value_split[i];
						predeccessor = "";
					}
					if (job_name_old.equalsIgnoreCase(job_name) && predeccessor != "") {
						sequence++;
					} else {
						sequence = 1;
					}
					job_name_old = job_name;
					if (i == 0) {
						String[] value_split2 = value_split[i].split(",");
						if (value_split2.length >= 1) {
							seq.setBatch(batch);
							seq.setJob_name(job_name);
							seq.setPredeccessor(predeccessor);
							seq.setSequence(sequence);
							seq.setProject(project);
							sequences.add(seq);
						}
					} else {
						seq.setBatch(batch);
						seq.setJob_name(job_name);
						seq.setPredeccessor(predeccessor);
						seq.setSequence(sequence);
						seq.setProject(project);
						sequences.add(seq);
					}
				}

			} else {
				status = "Failed";
				message = "Task were not selected";
			}
			if (!sequences.isEmpty()) {
				if (button_type.equalsIgnoreCase("create")) {
					message = Repository.addBatchFlowSequence(flowname, BATCH_TYPE, seqString, project_sequence);
					if (message.equalsIgnoreCase("success")) {
						message = Repository.addBatchTaskSequence(sequences);
						if (message.equalsIgnoreCase("success")) {
							status = "Success";
							message = "Sequence Added Successfully";
						} else {
							status = "Failed";
							message = "Failed to add the sequence";
						}
					} else {
						status = "Failed";
						message = "Failed to add the sequence";
					}
				} else if (button_type.equalsIgnoreCase("edit")) {
					message = Repository.updateBatchFlowSequence(BATCH_SEQUENCE_ID, BATCH_TYPE, seqString,
							project_sequence);
					if (message.equalsIgnoreCase("success")) {
						message = Repository.updateBatchPredecessors(BATCH_SEQUENCE_ID, BATCH_TYPE, seqString,
								project_sequence);
						if (message.equalsIgnoreCase("success")) {
							message = Repository.addBatchTaskSequence(sequences);
							if (message.equalsIgnoreCase("success")) {
								status = "Success";
								message = "Sequence Added Successfully";
							} else {
								status = "Failed";
								message = "Failed to add the sequence";
							}
						} else {
							status = "Failed";
							message = "Failed to add the sequence";
						}
					} else {
						status = "Failed";
						message = "Failed to add the sequence";
					}
				}
			}
		} catch (Exception e) {
			status = "Failed";
			message = e.getMessage();
			if (message.contains("ORA-00001")) {
				message = "Sequence already exists";
			}
		}
		return ResponseUtil.createResponse(status, message);

	}
}
