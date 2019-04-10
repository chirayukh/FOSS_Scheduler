package com.juniper.repository;

import java.sql.Connection;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.juniper.dao.JuniperOnPremAdhocTaskDao;
import com.juniper.dto.*;
import com.juniper.util.MetadataDBConnectionUtils;

@Component
public class JuniperOnPremAdhocTaskRepositoryImpl implements JuniperOnPremAdhocTaskRepository {

	@Autowired
	JuniperOnPremAdhocTaskDao Dao;
		
	//----------------------------------Adhoc Task Sequence--------------------------------------------------//
	/**
	 * @param project_id
	 * @return int
	 */
	@Override
	public int getProjectSeq(String project_id) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
		} catch (Exception e) {
			throw e;
		}
		return Dao.getProjectSeq(conn, project_id);
	}

	/**
	 * @param dto
	 * @param cron
	 * @return String
	 */
	@Override
	public String saveBatchDetails(BatchTableDetailsDTO dto, String cron) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
		} catch (Exception e) {
			throw e;
		}
		return Dao.saveBatchDetails(conn, dto, cron);
	}
	
	/**
	 * @param dto
	 * @return String
	 */
	@Override
	public String editAdhocScheduleData(AdhocTask dto) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
		} catch (Exception e) {
			throw e;
		}
		return Dao.updateAdhocScheduleTable(conn, dto);
	}
	
	/**
	 * @param dto
	 * @return String
	 */
	@Override
	public String editScheduleTable(AdhocTask dto) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
		} catch (Exception e) {
			throw e;
		}
		return Dao.updateScheduleTable(conn, dto);
	}
	
	/**
	 * @param dtos
	 * @param batchDto
	 * @return String
	 */
	@Override
	public String addScheduleTable(ArrayList<AdhocTask> dtos, BatchTableDetailsDTO batchDto) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.insertScheduleTable(conn, dtos, batchDto);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param dto
	 * @return String
	 */
	@Override
	public String addAdhocTaskSequence(ArrayList<AdhocTaskSequence> dto) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.insertAdhocTaskSequence(conn, dto);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String editBatchDetails(String batch_id, int project_id) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.editBatchDetails(conn, batch_id, project_id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param batch_id
	 * @param project_id
	 * @return BatchTableDetailsDTO
	 */
	@Override
	public BatchTableDetailsDTO getBatchDetails(String batch_id, int project_id) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
		} catch (Exception e) {
			throw e;

		}
		return Dao.getBatchDetails(conn, batch_id, project_id);
	}

	/**
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String updateAdhocTaskPredeccessors(String batch_id, int project_id) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateAdhocTaskPredeccessors(conn, batch_id, project_id);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param dto
	 * @return String
	 */
	@Override
	public String addBatchTaskSequence(ArrayList<AdhocTaskSequence> dto) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateScheduleBatchPredeccessors(conn, dto);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String updateScheduleTableFromAdhocTable(String batch_id, int project_id) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateScheduleTableFromAdhocTable(conn, batch_id, project_id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String deleteScheduleTable(String batch_id, int project_id) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.deleteScheduleTable(conn, batch_id, project_id);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @param batchDTO
	 * @return String
	 */
	@Override
	public String updateAdhocScheduleTable(BatchTableDetailsDTO batchDTO) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateAdhocScheduleTable(conn, batchDTO);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param batchDTO
	 * @return String
	 */
	@Override
	public String updateFinalScheduleTable(BatchTableDetailsDTO batchDTO) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateFinalScheduleTable(conn, batchDTO);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param BATCH_SEQUENCE_ID
	 * @param BATCH_TYPE
	 * @param BATCH_SEQUENCE
	 * @return String
	 */
	@Override
	public String addBatchFlowSequence(String BATCH_SEQUENCE_ID, String BATCH_TYPE, String BATCH_SEQUENCE,
			int PROJECT_SEQUENCE) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.addBatchFlowSequence(conn, BATCH_SEQUENCE_ID, BATCH_TYPE, BATCH_SEQUENCE, PROJECT_SEQUENCE);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param BATCH_SEQUENCE_ID
	 * @param BATCH_TYPE
	 * @param BATCH_SEQUENCE
	 * @return String
	 */
	@Override
	public String updateBatchFlowSequence(String BATCH_SEQUENCE_ID, String BATCH_TYPE, String BATCH_SEQUENCE,
			int PROJECT_SEQUENCE) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateBatchFlowSequence(conn, BATCH_SEQUENCE_ID, BATCH_TYPE, BATCH_SEQUENCE, PROJECT_SEQUENCE);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * @param BATCH_SEQUENCE_ID
	 * @param BATCH_TYPE
	 * @param BATCH_SEQUENCE
	 * @return String
	 */
	@Override
	public String updateBatchPredecessors(String BATCH_SEQUENCE_ID, String BATCH_TYPE, String BATCH_SEQUENCE,
			int PROJECT_SEQUENCE) throws Exception {
		Connection conn = null;
		try {
			conn = MetadataDBConnectionUtils.getOracleConnection();
			return Dao.updateBatchPredecessors(conn, BATCH_SEQUENCE_ID, BATCH_TYPE, BATCH_SEQUENCE, PROJECT_SEQUENCE);
		} catch (Exception e) {
			throw e;
		}

	}

}
