package com.iig.gcp.scheduler.schedulerController.dto;

import java.util.Date;

public class DailyJobsDTO {
	private int job_sequence;
	private String job_id;
	private String job_name;
	private String batch_id;
	private String batch_date;
	private String pre_processing;
	private String post_processing;
	private String command;
	private String argument_1;
	private String argument_2;
	private String argument_3;
	private String argument_4;
	private String argument_5;
	private char daily_flag;
	private char weekly_flag;
	private char monthly_flag;
	private char yearly_flag;
	private String job_schedule_time;
	private String predessor_job_id_1;
	private String predessor_job_id_2;
	private String predessor_job_id_3;
	private String predessor_job_id_4;
	private String predessor_job_id_5;
	private String predessor_job_id_6;
	private String predessor_job_id_7;
	private String predessor_job_id_8;
	private String predessor_job_id_9;
	private String predessor_job_id_10;
	private String is_dependent_job;
	private String week_run_day;
	private String month_run_val;
	private String month_run_day;
	private String command_type;
	private String status;
	private Date start_time;
	private Date end_time;
	private Date last_update_ts;
	private int script_pointer;
	private String worker_id;
	private String schedule;
	private String consolidatedSchedule;

	/**
	 * @return String
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return String
	 */
	public String getConsolidatedSchedule() {
		return consolidatedSchedule;
	}

	/**
	 * @param consolidatedSchedule
	 */
	public void setConsolidatedSchedule(String consolidatedSchedule) {
		this.consolidatedSchedule = consolidatedSchedule;
	}

	/**
	 * @return String
	 */
	public int getJob_sequence() {
		return job_sequence;
	}

	/**
	 * @param job_sequence
	 */
	public void setJob_sequence(int job_sequence) {
		this.job_sequence = job_sequence;
	}

	/**
	 * @return String
	 */
	public String getJob_id() {
		return job_id;
	}

	/**
	 * @param job_id
	 */
	public void setJob_id(String job_id) {
		this.job_id = job_id;
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
	public String getBatch_id() {
		return batch_id;
	}

	/**
	 * @param batch_id
	 */
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}

	/**
	 * @return String
	 */
	public String getBatch_date() {
		return batch_date;
	}

	/**
	 * @param batch_date
	 */
	public void setBatch_date(String batch_date) {
		this.batch_date = batch_date;
	}

	/**
	 * @return String
	 */
	public String getPre_processing() {
		return pre_processing;
	}

	/**
	 * @param pre_processing
	 */
	public void setPre_processing(String pre_processing) {
		this.pre_processing = pre_processing;
	}

	/**
	 * @return String
	 */
	public String getPost_processing() {
		return post_processing;
	}

	/**
	 * @param post_processing
	 */
	public void setPost_processing(String post_processing) {
		this.post_processing = post_processing;
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
	public char getDaily_flag() {
		return daily_flag;
	}

	/**
	 * @param daily_flag
	 */
	public void setDaily_flag(char daily_flag) {
		this.daily_flag = daily_flag;
	}

	/**
	 * @return String
	 */
	public char getWeekly_flag() {
		return weekly_flag;
	}

	/**
	 * @param weekly_flag
	 */
	public void setWeekly_flag(char weekly_flag) {
		this.weekly_flag = weekly_flag;
	}

	/**
	 * @return String
	 */
	public char getMonthly_flag() {
		return monthly_flag;
	}

	/**
	 * @param monthly_flag
	 */
	public void setMonthly_flag(char monthly_flag) {
		this.monthly_flag = monthly_flag;
	}

	/**
	 * @return String
	 */
	public char getYearly_flag() {
		return yearly_flag;
	}

	/**
	 * @param yearly_flag
	 */
	public void setYearly_flag(char yearly_flag) {
		this.yearly_flag = yearly_flag;
	}

	/**
	 * @return String
	 */
	public String getJob_schedule_time() {
		return job_schedule_time;
	}

	/**
	 * @param job_schedule_time
	 */
	public void setJob_schedule_time(String job_schedule_time) {
		this.job_schedule_time = job_schedule_time;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_1() {
		return predessor_job_id_1;
	}

	/**
	 * @param predessor_job_id_1
	 */
	public void setPredessor_job_id_1(String predessor_job_id_1) {
		this.predessor_job_id_1 = predessor_job_id_1;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_2() {
		return predessor_job_id_2;
	}

	/**
	 * @param predessor_job_id_2
	 */
	public void setPredessor_job_id_2(String predessor_job_id_2) {
		this.predessor_job_id_2 = predessor_job_id_2;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_3() {
		return predessor_job_id_3;
	}

	/**
	 * @param predessor_job_id_3
	 */
	public void setPredessor_job_id_3(String predessor_job_id_3) {
		this.predessor_job_id_3 = predessor_job_id_3;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_4() {
		return predessor_job_id_4;
	}

	/**
	 * @param predessor_job_id_4
	 */
	public void setPredessor_job_id_4(String predessor_job_id_4) {
		this.predessor_job_id_4 = predessor_job_id_4;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_5() {
		return predessor_job_id_5;
	}

	/**
	 * @param predessor_job_id_5
	 */
	public void setPredessor_job_id_5(String predessor_job_id_5) {
		this.predessor_job_id_5 = predessor_job_id_5;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_6() {
		return predessor_job_id_6;
	}

	/**
	 * @param predessor_job_id_6
	 */
	public void setPredessor_job_id_6(String predessor_job_id_6) {
		this.predessor_job_id_6 = predessor_job_id_6;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_7() {
		return predessor_job_id_7;
	}

	/**
	 * @param predessor_job_id_7
	 */
	public void setPredessor_job_id_7(String predessor_job_id_7) {
		this.predessor_job_id_7 = predessor_job_id_7;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_8() {
		return predessor_job_id_8;
	}

	/**
	 * @param predessor_job_id_8
	 */
	public void setPredessor_job_id_8(String predessor_job_id_8) {
		this.predessor_job_id_8 = predessor_job_id_8;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_9() {
		return predessor_job_id_9;
	}

	/**
	 * @param predessor_job_id_9
	 */
	public void setPredessor_job_id_9(String predessor_job_id_9) {
		this.predessor_job_id_9 = predessor_job_id_9;
	}

	/**
	 * @return String
	 */
	public String getPredessor_job_id_10() {
		return predessor_job_id_10;
	}

	/**
	 * @param predessor_job_id_10
	 */
	public void setPredessor_job_id_10(String predessor_job_id_10) {
		this.predessor_job_id_10 = predessor_job_id_10;
	}

	/**
	 * @return String
	 */
	public String getIs_dependent_job() {
		return is_dependent_job;
	}

	/**
	 * @param is_dependent_job
	 */
	public void setIs_dependent_job(String is_dependent_job) {
		this.is_dependent_job = is_dependent_job;
	}

	/**
	 * @return String
	 */
	public String getWeek_run_day() {
		return week_run_day;
	}

	/**
	 * @param week_run_day
	 */
	public void setWeek_run_day(String week_run_day) {
		this.week_run_day = week_run_day;
	}

	/**
	 * @return String
	 */
	public String getMonth_run_val() {
		return month_run_val;
	}

	/**
	 * @param month_run_val
	 */
	public void setMonth_run_val(String month_run_val) {
		this.month_run_val = month_run_val;
	}

	/**
	 * @return String
	 */
	public String getMonth_run_day() {
		return month_run_day;
	}

	/**
	 * @param month_run_day
	 */
	public void setMonth_run_day(String month_run_day) {
		this.month_run_day = month_run_day;
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
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Date
	 */
	public Date getStart_time() {
		return start_time;
	}

	/**
	 * @param start_time
	 */
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	/**
	 * @return Date
	 */
	public Date getEnd_time() {
		return end_time;
	}

	/**
	 * @param end_time
	 */
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	/**
	 * @return Date
	 */
	public Date getLast_update_ts() {
		return last_update_ts;
	}

	/**
	 * @param last_update_ts
	 */
	public void setLast_update_ts(Date last_update_ts) {
		this.last_update_ts = last_update_ts;
	}

	/**
	 * @return Date
	 */
	public int getScript_pointer() {
		return script_pointer;
	}

	/**
	 * @param script_pointer
	 */
	public void setScript_pointer(int script_pointer) {
		this.script_pointer = script_pointer;
	}

	/**
	 * @return String
	 */
	public String getWorker_id() {
		return worker_id;
	}

	/**
	 * @param worker_id
	 */
	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}
}
