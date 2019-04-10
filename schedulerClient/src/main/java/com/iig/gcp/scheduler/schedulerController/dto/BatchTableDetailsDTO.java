package com.iig.gcp.scheduler.schedulerController.dto;

import java.util.ArrayList;

public class BatchTableDetailsDTO {

	String BATCH_NAME;
	String BATCH_DESC;
	String DAILY_FLAG;
	String WEEKLY_FLAG;
	String HOURLY_FLAG;
	String MONTHLY_FLAG;
	String YEARLY_FLAG;
	String JOB_SCHEDULE_TIME;
	String WEEK_RUN_DAY;
	String WEEK_NUM_MONTH;
	String MONTH_RUN_VAL;
	String MONTH_RUN_DAY;
	String SCHEDULE_TYPE;
	String SCHEDULE_FLAG;
	String project;
	int project_sequence;
	String Argument_4;
	ArrayList<BatchTableDetailsDTO> ScheduleTableArr;

	/**
	 * @return String
	 */
	public String getHOURLY_FLAG() {
		return HOURLY_FLAG;
	}

	/**
	 * @param hOURLY_FLAG
	 */
	public void setHOURLY_FLAG(String hOURLY_FLAG) {
		HOURLY_FLAG = hOURLY_FLAG;
	}

	/**
	 * @return String
	 */
	public String getArgument_4() {
		return Argument_4;
	}

	/**
	 * @param argument_4
	 */
	public void setArgument_4(String argument_4) {
		Argument_4 = argument_4;
	}

	/**
	 * @return String
	 */
	public String getBATCH_NAME() {
		return BATCH_NAME;
	}

	/**
	 * @param bATCH_NAME
	 */
	public void setBATCH_NAME(String bATCH_NAME) {
		BATCH_NAME = bATCH_NAME;
	}

	/**
	 * @return String
	 */
	public String getBATCH_DESC() {
		return BATCH_DESC;
	}

	/**
	 * @param bATCH_DESC
	 */
	public void setBATCH_DESC(String bATCH_DESC) {
		BATCH_DESC = bATCH_DESC;
	}

	/**
	 * @return String
	 */
	public int getProject_sequence() {
		return project_sequence;
	}

	/**
	 * @param project_sequence
	 */
	public void setProject_sequence(int project_sequence) {
		this.project_sequence = project_sequence;
	}

	/**
	 * @return String
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @param project
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return String
	 */
	public String getSCHEDULE_FLAG() {
		return SCHEDULE_FLAG;
	}

	/**
	 * @param sCHEDULE_FLAG
	 */
	public void setSCHEDULE_FLAG(String sCHEDULE_FLAG) {
		SCHEDULE_FLAG = sCHEDULE_FLAG;
	}

	/**
	 * @return String
	 */
	public String getDAILY_FLAG() {
		return DAILY_FLAG;
	}

	/**
	 * @param dAILY_FLAG
	 */
	public void setDAILY_FLAG(String dAILY_FLAG) {
		DAILY_FLAG = dAILY_FLAG;
	}

	/**
	 * @return String
	 */
	public String getWEEKLY_FLAG() {
		return WEEKLY_FLAG;
	}

	/**
	 * @param wEEKLY_FLAG
	 */
	public void setWEEKLY_FLAG(String wEEKLY_FLAG) {
		WEEKLY_FLAG = wEEKLY_FLAG;
	}

	/**
	 * @return String
	 */
	public String getMONTHLY_FLAG() {
		return MONTHLY_FLAG;
	}

	/**
	 * @param mONTHLY_FLAG
	 */
	public void setMONTHLY_FLAG(String mONTHLY_FLAG) {
		MONTHLY_FLAG = mONTHLY_FLAG;
	}

	/**
	 * @return String
	 */
	public String getYEARLY_FLAG() {
		return YEARLY_FLAG;
	}

	/**
	 * @param yEARLY_FLAG
	 */
	public void setYEARLY_FLAG(String yEARLY_FLAG) {
		YEARLY_FLAG = yEARLY_FLAG;
	}

	/**
	 * @return String
	 */
	public String getJOB_SCHEDULE_TIME() {
		return JOB_SCHEDULE_TIME;
	}

	/**
	 * @param jOB_SCHEDULE_TIME
	 */
	public void setJOB_SCHEDULE_TIME(String jOB_SCHEDULE_TIME) {
		JOB_SCHEDULE_TIME = jOB_SCHEDULE_TIME;
	}

	/**
	 * @return String
	 */
	public String getWEEK_RUN_DAY() {
		return WEEK_RUN_DAY;
	}

	/**
	 * @param wEEK_RUN_DAY
	 */
	public void setWEEK_RUN_DAY(String wEEK_RUN_DAY) {
		WEEK_RUN_DAY = wEEK_RUN_DAY;
	}

	/**
	 * @return String
	 */
	public String getWEEK_NUM_MONTH() {
		return WEEK_NUM_MONTH;
	}

	/**
	 * @param wEEK_NUM_MONTH
	 */
	public void setWEEK_NUM_MONTH(String wEEK_NUM_MONTH) {
		WEEK_NUM_MONTH = wEEK_NUM_MONTH;
	}

	/**
	 * @return String
	 */
	public String getMONTH_RUN_VAL() {
		return MONTH_RUN_VAL;
	}

	/**
	 * @param mONTH_RUN_VAL
	 */
	public void setMONTH_RUN_VAL(String mONTH_RUN_VAL) {
		MONTH_RUN_VAL = mONTH_RUN_VAL;
	}

	/**
	 * @return String
	 */
	public String getMONTH_RUN_DAY() {
		return MONTH_RUN_DAY;
	}

	/**
	 * @param mONTH_RUN_DAY
	 */
	public void setMONTH_RUN_DAY(String mONTH_RUN_DAY) {
		MONTH_RUN_DAY = mONTH_RUN_DAY;
	}

	/**
	 * @return String
	 */
	public String getSCHEDULE_TYPE() {
		return SCHEDULE_TYPE;
	}

	/**
	 * @param sCHEDULE_TYPE
	 */
	public void setSCHEDULE_TYPE(String sCHEDULE_TYPE) {
		SCHEDULE_TYPE = sCHEDULE_TYPE;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList<BatchTableDetailsDTO> getScheduleTableArr() {
		return ScheduleTableArr;
	}

	/**
	 * @param scheduleTableArr
	 */
	public void setScheduleTableArr(ArrayList<BatchTableDetailsDTO> scheduleTableArr) {
		ScheduleTableArr = scheduleTableArr;
	}

}
