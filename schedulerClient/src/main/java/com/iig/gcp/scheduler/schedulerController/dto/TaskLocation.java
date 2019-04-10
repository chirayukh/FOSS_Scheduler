package com.iig.gcp.scheduler.schedulerController.dto;

public class TaskLocation {
	private String script_location;
	private String files;

	/**
	 * @return String
	 */
	public String getScript_location() {
		return script_location;
	}

	/**
	 * @param script_location
	 */
	public void setScript_location(String script_location) {
		this.script_location = script_location;
	}

	/**
	 * @return String
	 */
	public String getFiles() {
		return files;
	}

	/**
	 * @param files
	 */
	public void setFiles(String files) {
		this.files = files;
	}

}
