package com.iig.gcp.scheduler.schedulerController.dto;

public class BatchSequenceDTO {
	private String BATCH_ID;
	private String JOB_NAME;

	/**
	 * @return  String
	 */
	public String getBATCH_ID() {
		return BATCH_ID;
	}

	/**
	 * @param bATCH_ID
	 */
	public void setBATCH_ID(String bATCH_ID) {
		BATCH_ID = bATCH_ID;
	}

	/**
	 * @return String
	 */
	public String getJOB_NAME() {
		return JOB_NAME;
	}

	/**
	 * @param jOB_NAME
	 */
	public void setJOB_NAME(String jOB_NAME) {
		JOB_NAME = jOB_NAME;
	}

}
