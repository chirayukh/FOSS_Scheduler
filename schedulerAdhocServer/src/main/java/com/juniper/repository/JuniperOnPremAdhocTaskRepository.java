package com.juniper.repository;


import java.util.ArrayList;
import com.juniper.dto.AdhocTask;
import com.juniper.dto.AdhocTaskSequence;
import com.juniper.dto.BatchTableDetailsDTO;


public interface JuniperOnPremAdhocTaskRepository {
	
	public String saveBatchDetails(BatchTableDetailsDTO dto,String cron) throws Exception;
	public int getProjectSeq(String project_id) throws Exception;
	public String addScheduleTable(ArrayList<AdhocTask> dtos,BatchTableDetailsDTO batchDto) throws Exception;
	public String editAdhocScheduleData(AdhocTask dto) throws Exception;
	public String editScheduleTable(AdhocTask dto) throws Exception;
	public String addAdhocTaskSequence(ArrayList<AdhocTaskSequence> dto) throws Exception;
	public String editBatchDetails(String batch_id,int project_id) throws Exception;
	public BatchTableDetailsDTO getBatchDetails(String batch_id,int project_id) throws Exception;
	
	//Task Sequencing
	public String updateAdhocTaskPredeccessors(String batch_id,int project_id) throws Exception;
	public String updateScheduleTableFromAdhocTable(String batch_id,int project_id) throws Exception;
	public String deleteScheduleTable(String batch_id,int project_id) throws Exception;
	public String updateAdhocScheduleTable(BatchTableDetailsDTO batchDTO) throws Exception; 
	public String updateFinalScheduleTable(BatchTableDetailsDTO batchDTO) throws Exception;
	
	
	//Batch Sequencing
	public String addBatchTaskSequence(ArrayList<AdhocTaskSequence> dto) throws Exception;
	public String addBatchFlowSequence(String BATCH_SEQUENCE_ID,String BATCH_TYPE,String BATCH_SEQUENCE,int PROJECT_SEQUENCE) throws Exception;
	public String updateBatchFlowSequence(String BATCH_SEQUENCE_ID,String BATCH_TYPE,String BATCH_SEQUENCE,int PROJECT_SEQUENCE) throws Exception;
	public String updateBatchPredecessors(String BATCH_SEQUENCE_ID,String BATCH_TYPE,String BATCH_SEQUENCE,int PROJECT_SEQUENCE) throws Exception;
}
