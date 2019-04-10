package com.juniper.dao;

import java.sql.Connection;
import java.util.ArrayList;
import com.juniper.dto.AdhocTask;
import com.juniper.dto.AdhocTaskSequence;
import com.juniper.dto.BatchTableDetailsDTO;

public interface JuniperOnPremAdhocTaskDao {

	public String insertScheduleTable(Connection conn, ArrayList<AdhocTask> dtos, BatchTableDetailsDTO batchDto)
			throws Exception;

	public String insertAdhocTaskSequence(Connection conn, ArrayList<AdhocTaskSequence> dto) throws Exception;

	public String saveBatchDetails(Connection conn, BatchTableDetailsDTO dto, String cron) throws Exception;

	public String editBatchDetails(Connection conn, String batch_id, int project_id) throws Exception;

	public int getProjectSeq(Connection conn, String project_id) throws Exception;

	public BatchTableDetailsDTO getBatchDetails(Connection conn, String batch_id, int project_id) throws Exception;

	public String updateAdhocTaskPredeccessors(Connection conn, String batch_id, int project_id) throws Exception;

	public String updateScheduleTableFromAdhocTable(Connection conn, String batch_id, int project_id) throws Exception;

	public String deleteScheduleTable(Connection conn, String batch_id, int project_id) throws Exception;

	public String updateAdhocScheduleTable(Connection conn, BatchTableDetailsDTO batchDTO) throws Exception;

	public String updateFinalScheduleTable(Connection conn, BatchTableDetailsDTO batchDTO) throws Exception;

	public String updateAdhocScheduleTable(Connection conn, AdhocTask dto) throws Exception;

	public String updateScheduleTable(Connection conn, AdhocTask dto) throws Exception;

	// Batch Sequence

	public String updateScheduleBatchPredeccessors(Connection conn, ArrayList<AdhocTaskSequence> dto) throws Exception;

	public String addBatchFlowSequence(Connection conn, String BATCH_SEQUENCE_ID, String BATCH_TYPE,
			String BATCH_SEQUENCE, int PROJECT_SEQUENCE) throws Exception;

	public String updateBatchFlowSequence(Connection conn, String BATCH_SEQUENCE_ID, String BATCH_TYPE,
			String BATCH_SEQUENCE, int PROJECT_SEQUENCE) throws Exception;

	public String updateBatchPredecessors(Connection conn, String BATCH_SEQUENCE_ID, String BATCH_TYPE,
			String BATCH_SEQUENCE, int PROJECT_SEQUENCE) throws Exception;

}
