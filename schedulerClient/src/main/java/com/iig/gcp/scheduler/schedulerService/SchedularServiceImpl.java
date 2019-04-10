package com.iig.gcp.scheduler.schedulerService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.iig.gcp.scheduler.schedulerController.dao.SchedularDAO;
import com.iig.gcp.scheduler.schedulerController.dto.AdhocJobDTO;
import com.iig.gcp.scheduler.schedulerController.dto.ArchiveJobsDTO;
import com.iig.gcp.scheduler.schedulerController.dto.BatchDetailsDTO;
import com.iig.gcp.scheduler.schedulerController.dto.BatchFlowDTO;
import com.iig.gcp.scheduler.schedulerController.dto.BatchSequenceDTO;
import com.iig.gcp.scheduler.schedulerController.dto.BatchTableDetailsDTO;
import com.iig.gcp.scheduler.schedulerController.dto.DailyJobsDTO;
import com.iig.gcp.scheduler.schedulerController.dto.MasterJobsDTO;
import com.iig.gcp.scheduler.schedulerController.dto.TaskLocation;
import com.iig.gcp.scheduler.schedulerController.dto.TaskSequenceDTO;

@Service
public class SchedularServiceImpl implements SchedularService {

	@Autowired
	SchedularDAO schedularDAO;

	// Master Table
	@Override
	public ArrayList<String> getFeedFromMaster(String project) throws Exception {
		return schedularDAO.getFeedFromMaster(project);
	}

	@Value("${adhoc.task.compute.url}")
	private String ADHOC_TASK_COMPUTE_URL;

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#allLoadJobs(java.lang.String)
	 * @return List
	 * @param project
	 * @throws Exception
	 */
	@Override
	public List<MasterJobsDTO> allLoadJobs(String project) throws Exception {
		return schedularDAO.allLoadJobs(project);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#typeLoadJobs(java.lang.String, java.lang.String)
	 * @return List
	 * @param frequency
	 * @param batchId
	 * @throws Exception
	 */
	@Override
	public List<MasterJobsDTO> typeLoadJobs(String frequency, String batchId) throws Exception {
		return schedularDAO.typAndBatchLoadJobs(frequency, batchId);
	}

	// Archive Table;
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getFeedIdList()
	 * @return ArrayList
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getFeedIdList() throws Exception {
		return schedularDAO.getFeedIdList();
	}

	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getListOfArchievJobs(java.lang.String)
	 * @return ArrayList
	 * @param feed_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<ArchiveJobsDTO> getListOfArchievJobs(@Valid String feed_id) throws Exception {
		return schedularDAO.getListOfArchievJobs(feed_id);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getChartDetails(java.lang.String)
	 * @return ArrayList
	 * @param job_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<ArchiveJobsDTO> getChartDetails(@Valid String job_id) throws Exception {
		return schedularDAO.getChartDetails(job_id);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getRunStats(java.lang.String, java.lang.String)
	 * @return List
	 * @param job_id
	 * @param feed_id
	 * @throws Exception
	 */
	@Override
	public List<ArchiveJobsDTO> getRunStats(@Valid String job_id, @Valid String feed_id) throws Exception {
		return schedularDAO.getRunStats(job_id, feed_id);
	}

	// Current Table;
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#allCurrentJobs(java.lang.String)
	 * @return List
	 * @param project
	 * @throws Exception
	 */
	@Override
	public List<DailyJobsDTO> allCurrentJobs(String project) throws Exception {
		return schedularDAO.allCurrentJobs(project);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getFeedFromCurrent(java.lang.String)
	 * @return ArrayList
	 * @param project
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getFeedFromCurrent(String project) throws Exception {
		return schedularDAO.getFeedFromCurrent(project);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#filterCurrentJobs(java.lang.String, java.lang.String)
	 * @return List
	 * @param status
	 * @param feedId
	 * @throws Exception
	 */
	@Override
	public List<DailyJobsDTO> filterCurrentJobs(String status, String feedId) throws Exception {
		return schedularDAO.filterCurrentJobs(status, feedId);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#allCurrentJobsGroupByFeedId()
	 * @return HashMap
	 * @throws Exception 
	 */
	@Override
	public HashMap<String, ArrayList<String>> allCurrentJobsGroupByFeedId() throws Exception {
		return schedularDAO.allCurrentJobsGroupByFeedId();
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#moveJobFromMasterToCurrentJob(java.lang.String)
	 * @return String
	 * @param feedId
	 * @throws Exception
	 */
	@Override
	public String moveJobFromMasterToCurrentJob(String feedId) throws Exception {
		return schedularDAO.moveJobFromMasterToCurrentJob(feedId);

	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#deleteJobFromMaster(java.lang.String)
	 * @return String
	 * @param feedId
	 * @throws Exception
	 */
	@Override
	public String deleteJobFromMaster(String feedId) throws Exception {
		return schedularDAO.deleteJobFromMaster(feedId);

	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#runScheduleJob(java.lang.String, java.lang.String, java.lang.String)
	 * @return String
	 * @param feedId
	 * @param jobId
	 * @param batchDate
	 * @throws Exception
	 */
	@Override
	public String runScheduleJob(@Valid String feedId, String jobId, String batchDate) throws Exception {
		return schedularDAO.runScheduleJob(feedId, jobId, batchDate);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#killCurrentJob(java.lang.String, java.lang.String, java.lang.String)
	 * @return String
	 * @param feedId
	 * @param jobId
	 * @param batchDate
	 * @throws Exception
	 */
	@Override
	public String killCurrentJob(@Valid String feedId, String jobId, String batchDate) throws Exception {
		return schedularDAO.killCurrentJob(feedId, jobId, batchDate);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#suspendJobFromMaster(java.lang.String)
	 * @return String
	 * @param feedId
	 * @throws Exception
	 */
	@Override
	public String suspendJobFromMaster(String feedId) throws Exception {
		return schedularDAO.suspendJobFromMaster(feedId);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#unSuspendJobFromMaster(java.lang.String)
	 * @return String
	 * @param feedId
	 * @throws Exception
	 */
	@Override
	public String unSuspendJobFromMaster(@Valid String feedId) throws Exception {
		return schedularDAO.unSuspendJobFromMaster(feedId);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchDetails(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<BatchDetailsDTO> getBatchDetails(String project_id) throws Exception {
		return schedularDAO.getBatchDetails(project_id);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getAdhocBatchCreateDetails(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<BatchDetailsDTO> getAdhocBatchCreateDetails(String project_id) throws Exception {
		return schedularDAO.getAdhocBatchCreateDetails(project_id);
	}

	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getAdhocBatchEditDetails(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<BatchDetailsDTO> getAdhocBatchEditDetails(String project_id) throws Exception {
		return schedularDAO.getAdhocBatchEditDetails(project_id);
	}


	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getCreateBatchDetails(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<BatchDetailsDTO> getCreateBatchDetails(String project_id) throws Exception {
		return schedularDAO.getCreateBatchDetails(project_id);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getEditBatchDetails(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<BatchDetailsDTO> getEditBatchDetails(String project_id) throws Exception {
		return schedularDAO.getEditBatchDetails(project_id);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getJobDetails(java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @param batch_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<TaskSequenceDTO> getJobDetails(String batch_id, String project_id) throws Exception {
		return schedularDAO.getJobDetails(batch_id, project_id);
	}

	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchDetails1(java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @param schtype
	 * @throws Exception
	 */
	@Override
	public ArrayList<TaskSequenceDTO> getBatchDetails1(String project_id,String schtype) throws Exception {
		return schedularDAO.getBatchDetails1(project_id, schtype);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getAdhocJobDetails(java.lang.String, java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @param batch_id
	 * @param job_name
	 * @throws Exception
	 */
	@Override
	public ArrayList<DailyJobsDTO> getAdhocJobDetails(String batch_id, String project_id,String job_name) throws Exception {
		return schedularDAO.getAdhocJobDetails(batch_id, project_id,job_name);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getKafkaTopic()
	 * @return ArrayList
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getKafkaTopic() throws Exception {
		try {
			return schedularDAO.getKafkaTopic();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchJobs(java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param batch_id
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getBatchJobs(String batch_id, String project_id) throws Exception {
		try {
			return schedularDAO.getBatchJobs(batch_id, project_id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#extractBatchDetails(java.lang.String, java.lang.String)
	 * @return BatchTableDetailsDTO
	 * @param batch_id
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public BatchTableDetailsDTO extractBatchDetails(String batch_id, String project_id) throws Exception {
		return schedularDAO.extractBatchDetails(batch_id, project_id);
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#extractBatchJobDetails(java.lang.String, java.lang.String, java.lang.String)
	 * @return AdhocJobDTO
	 * @param batch_id
	 * @param project_id
	 * @param job_id
	 * @throws Exception
	 */
	@Override
	public AdhocJobDTO extractBatchJobDetails(String batch_id, String project_id, String job_id) throws Exception {
		try {
			return schedularDAO.extractBatchJobDetails(batch_id, project_id, job_id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#invokeRest(java.lang.String, java.lang.String)
	 * @return String
	 * @param json
	 * @param url
	 * @throws UnsupportedOperationException
	 * @throws Exception
	 */
	@Override
	public String invokeRest(String json, String url) throws UnsupportedOperationException, Exception {
		String resp = null;
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost postRequest = new HttpPost(ADHOC_TASK_COMPUTE_URL + url);
		postRequest.setHeader("Content-Type", "application/json");
		StringEntity input = new StringEntity(json);
		postRequest.setEntity(input);
		HttpResponse response = httpClient.execute(postRequest);
		String response_string = EntityUtils.toString(response.getEntity(), "UTF-8");
		if (response.getStatusLine().getStatusCode() != 200) {
			resp = "Error" + response_string;
			throw new Exception("Error" + response_string);
		} else {
			resp = response_string;
		}
		return resp;
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchSequence(java.lang.String, java.lang.String, java.lang.String, int)
	 * @return String
	 * @param batch_id
	 * @param project_id
	 * @param i
	 * @throws Exception 
	 */
	@Override
	public String getBatchSequence(String batch_id,String project_id,String job_name,int i) throws Exception {
		try {
			return schedularDAO.getBatchSequence(batch_id, project_id,job_name,i);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getGoogleProject(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getGoogleProject(String project_id)  throws Exception {
		try {
			return schedularDAO.getGoogleProject(project_id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getServiceAccount(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getServiceAccount(String project_id)  throws Exception {
		try {
			return schedularDAO.getServiceAccount(project_id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchDetails2(java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param batch_id
	 * @param project_id
	 * @throws Exception
	 */
	@Override
	public ArrayList<BatchSequenceDTO> getBatchDetails2(@Valid String batch_id, @Valid String project_id) throws Exception {
		return schedularDAO.getBatchDetails2(batch_id,project_id);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchFlow(java.lang.String)
	 * @return ArrayList
	 * @param project_id
	 * @throws Exception  
	 */
	@Override
	public ArrayList<BatchFlowDTO> getBatchFlow(String project_id) throws Exception {
		return schedularDAO.getBatchFlow(project_id);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchFlowDetails(java.lang.String, java.lang.String)
	 * @return BatchFlowDTO
	 * @param project_id
	 * @param flowname
	 * @throws Exception
	 */
	@Override
	public BatchFlowDTO getBatchFlowDetails(String project_id,String flowname) throws Exception{
		return schedularDAO.getBatchFlowDetails(project_id,flowname);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBatchSequence(java.lang.String)
	 * @return ArrayList
	 * @param sequence
	 * @throws Exception 
	 */
	@Override
	public ArrayList<BatchSequenceDTO> getBatchSequence(String sequence) throws Exception{
		return schedularDAO.getBatchSequence(sequence);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#runUnixCommand(java.lang.String)
	 * @return ArrayList
	 * @param script_location
	 * @throws Exception 
	 */
	@Override
	public ArrayList<TaskLocation> runUnixCommand(String script_location) throws Exception{
		return schedularDAO.runUnixCommand(script_location);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBuckets(java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param google_project
	 * @param service_account
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getBuckets(String google_project,String service_account) throws Exception{
		return schedularDAO.getBuckets(google_project,service_account);
	}
	
	/**
	 * @see com.iig.gcp.scheduler.schedulerService.SchedularService#getBucketFiles(java.lang.String, java.lang.String, java.lang.String)
	 * @return ArrayList
	 * @param google_project
	 * @param service_account
	 * @param bucket
	 * @throws Exception
	 */
	@Override
	public ArrayList<String> getBucketFiles(String google_project,String service_account,String bucket) throws Exception{
		return schedularDAO.getBucketFiles(google_project,service_account,bucket);
	}


}
