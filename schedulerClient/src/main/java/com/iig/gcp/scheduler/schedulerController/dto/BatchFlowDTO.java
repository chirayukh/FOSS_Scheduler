package com.iig.gcp.scheduler.schedulerController.dto;

public class BatchFlowDTO {
	private String BATCH_SEQUENCE_ID;
	private String BATCH_TYPE;
	private String BATCH_SEQUENCE;
	private int PROJECT_SEQUENCE;

	/**
	 * @return String
	 */
	public String getBATCH_SEQUENCE_ID() {
		return BATCH_SEQUENCE_ID;
	}

	/**
	 * @param bATCH_SEQUENCE_ID
	 */
	public void setBATCH_SEQUENCE_ID(String bATCH_SEQUENCE_ID) {
		BATCH_SEQUENCE_ID = bATCH_SEQUENCE_ID;
	}

	/**
	 * @return String
	 */
	public String getBATCH_TYPE() {
		return BATCH_TYPE;
	}

	/**
	 * @param bATCH_TYPE
	 */
	public void setBATCH_TYPE(String bATCH_TYPE) {
		BATCH_TYPE = bATCH_TYPE;
	}

	/**
	 * @return String
	 */
	public String getBATCH_SEQUENCE() {
		return BATCH_SEQUENCE;
	}

	/**
	 * @param bATCH_SEQUENCE
	 */
	public void setBATCH_SEQUENCE(String bATCH_SEQUENCE) {
		BATCH_SEQUENCE = bATCH_SEQUENCE;
	}

	/**
	 * @return String
	 */
	public int getPROJECT_SEQUENCE() {
		return PROJECT_SEQUENCE;
	}

	/**
	 * @param pROJECT_SEQUENCE
	 */
	public void setPROJECT_SEQUENCE(int pROJECT_SEQUENCE) {
		PROJECT_SEQUENCE = pROJECT_SEQUENCE;
	}

}
