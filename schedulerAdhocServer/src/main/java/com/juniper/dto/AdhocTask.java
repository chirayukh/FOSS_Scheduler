package com.juniper.dto;

import java.util.ArrayList;

public class AdhocTask {

	String JOB_ID;
	String JOB_NAME;
	String BATCH_ID;
	String command_type;
	String command;
	String argument_1;
	String argument_2;
	String argument_3;
	String project;
	String user;
	int project_sequence;
	ArrayList<AdhocTask> ScheduleTableArr;

	/**
	 * @return String
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

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
	 * @return ArrayList
	 */
	public ArrayList<AdhocTask> getScheduleTableArr() {
		return ScheduleTableArr;
	}

	/**
	 * @param scheduleTableArr
	 */
	public void setScheduleTableArr(ArrayList<AdhocTask> scheduleTableArr) {
		ScheduleTableArr = scheduleTableArr;
	}

}
