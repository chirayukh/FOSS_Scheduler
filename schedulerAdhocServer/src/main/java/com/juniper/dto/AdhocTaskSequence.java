package com.juniper.dto;

import java.util.ArrayList;

public class AdhocTaskSequence {

	String batch;
	String job_name;
	String predeccessor;
	int sequence;
	String project;
	ArrayList<AdhocTaskSequence> ScheduleTableArr;

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
	public String getBatch() {
		return batch;
	}

	/**
	 * @param batch
	 */
	public void setBatch(String batch) {
		this.batch = batch;
	}

	/**
	 * @return String
	 */
	public String getJob_name() {
		return job_name;
	}

	/**
	 * @param job_name
	 */
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	/**
	 * @return String
	 */
	public String getPredeccessor() {
		return predeccessor;
	}

	/**
	 * @param predeccessor
	 */
	public void setPredeccessor(String predeccessor) {
		this.predeccessor = predeccessor;
	}

	/**
	 * @return String
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return ArrayList
	 */
	public ArrayList<AdhocTaskSequence> getScheduleTableArr() {
		return ScheduleTableArr;
	}

	/**
	 * @param scheduleTableArr
	 */
	public void setScheduleTableArr(ArrayList<AdhocTaskSequence> scheduleTableArr) {
		ScheduleTableArr = scheduleTableArr;
	}

}
