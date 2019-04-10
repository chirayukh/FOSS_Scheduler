package com.juniper.dto;

import java.util.ArrayList;

public class ScheduleTableDTO {

	String JOB_ID;
	String JOB_NAME;
	String BATCH_ID;
	String command_type;
	String PRE_PROCESSING;
	String command;
	String argument_1;
	String argument_2;
	String argument_3;
	String argument_4;
	String argument_5;
	String POST_PROCESSING;
	String DAILY_FLAG;
	String WEEKLY_FLAG;
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
	ArrayList<ScheduleTableDTO> ScheduleTableArr;

	/**
	 * @return int
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
	 * @return String
	 */
	public String getJOB_ID() {
		return JOB_ID;
	}

	/**
	 * @param jOB_ID
	 */
	public void setJOB_ID(String jOB_ID) {
		JOB_ID = jOB_ID;
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

	/**
	 * @return String
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
	public String getCommand_type() {
		return command_type;
	}

	/**
	 * @param command_type
	 */
	public void setCommand_type(String command_type) {
		this.command_type = command_type;
	}

	/**
	 * @return String
	 */
	public String getPRE_PROCESSING() {
		return PRE_PROCESSING;
	}

	/**
	 * @param pRE_PROCESSING
	 */
	public void setPRE_PROCESSING(String pRE_PROCESSING) {
		PRE_PROCESSING = pRE_PROCESSING;
	}

	/**
	 * @return String
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @return String
	 */
	public String getArgument_1() {
		return argument_1;
	}

	/**
	 * @param argument_1
	 */
	public void setArgument_1(String argument_1) {
		this.argument_1 = argument_1;
	}

	/**
	 * @return String
	 */
	public String getArgument_2() {
		return argument_2;
	}

	/**
	 * @param argument_2
	 */
	public void setArgument_2(String argument_2) {
		this.argument_2 = argument_2;
	}

	/**
	 * @return String
	 */
	public String getArgument_3() {
		return argument_3;
	}

	/**
	 * @param argument_3
	 */
	public void setArgument_3(String argument_3) {
		this.argument_3 = argument_3;
	}

	/**
	 * @return String
	 */
	public String getArgument_4() {
		return argument_4;
	}

	/**
	 * @param argument_4
	 */
	public void setArgument_4(String argument_4) {
		this.argument_4 = argument_4;
	}

	/**
	 * @return String
	 */
	public String getArgument_5() {
		return argument_5;
	}

	/**
	 * @param argument_5
	 */
	public void setArgument_5(String argument_5) {
		this.argument_5 = argument_5;
	}

	/**
	 * @return String
	 */
	public String getPOST_PROCESSING() {
		return POST_PROCESSING;
	}

	/**
	 * @param pOST_PROCESSING
	 */
	public void setPOST_PROCESSING(String pOST_PROCESSING) {
		POST_PROCESSING = pOST_PROCESSING;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList<ScheduleTableDTO> getScheduleTableArr() {
		return ScheduleTableArr;
	}

	/**
	 * @param scheduleTableArr
	 */
	public void setScheduleTableArr(ArrayList<ScheduleTableDTO> scheduleTableArr) {
		ScheduleTableArr = scheduleTableArr;
	}

}
