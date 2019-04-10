package com.juniper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import com.juniper.constant.MetadataDBConstants;
import com.juniper.dto.AdhocTask;
import com.juniper.dto.AdhocTaskSequence;
import com.juniper.dto.BatchTableDetailsDTO;

@Component
public class JuniperOnPremAdhocTaskDaoImpl implements JuniperOnPremAdhocTaskDao {

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#saveBatchDetails(java.sql.Connection, com.juniper.dto.BatchTableDetailsDTO, java.lang.String)
	 */
	/**
	 * @param conn
	 * @param dto
	 * @param cron
	 * @return String
	 */
	@Override
	public String saveBatchDetails(Connection conn, BatchTableDetailsDTO dto, String cron) throws Exception {
		String insertConnDetails = "";
		PreparedStatement pstm = null;
		String status = "";
		String schedule_flag = "";
		String schedule_type = "";

		schedule_flag = dto.getSCHEDULE_FLAG();
		schedule_type = dto.getSCHEDULE_TYPE();

		try {
			if (schedule_flag.toLowerCase().contains("f") || schedule_flag.toLowerCase().contains("k")
					|| schedule_flag.toLowerCase().contains("a") || schedule_type.toLowerCase().contains("adhoc")) {
				insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
						+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,schedule_type,job_schedule_time,daily_flag,project_id,BATCH_USER,CREATED_BY,argument_1,argument_3)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?)";
				pstm = conn.prepareStatement(insertConnDetails);
				pstm.setString(1, dto.getBATCH_NAME());
				pstm.setString(2, dto.getBATCH_DESC());
				pstm.setString(3, dto.getArgument_4());
				if (schedule_type.toLowerCase().contains("adhoc")) {
					pstm.setString(4, "O");
				} else {
					pstm.setString(4, schedule_flag);
				}
				pstm.setString(5, "00:00:00");
				pstm.setString(6, "Y");
				pstm.setInt(7, dto.getProject_sequence());
				pstm.setString(8, dto.getUser());
				pstm.setString(9, dto.getUser());
				pstm.setString(10, dto.getArgument_1());
				pstm.setString(11, dto.getArgument_3());
				pstm.executeUpdate();
				pstm.close();
				status = "success";
			}
		} catch (SQLException e) {
			return e.getMessage();
		}

		if (cron != "") {
			String[] temp = cron.split(" ");
			String minutes = temp[0];
			String hours = temp[1];
			String dates = temp[2];
			String months = temp[3];
			String daysOfWeek = temp[4];
			String hourlyFlag = "";
			String weeklyFlag = "";
			String dailyFlag = "";
			String monthlyFlag = "";
			String yearlyFlag = "";

			if (hours.equals("*") && dates.equals("*") && months.equals("*") && (daysOfWeek.equals("*"))) {
				dto.setYEARLY_FLAG("Y");
				yearlyFlag = "Y";
			}
			if (dates.equals("*") && months.equals("*") && daysOfWeek.equals("*") && !hours.equals("*")
					&& !minutes.equals("*")) {
				dto.setDAILY_FLAG("Y");
				dailyFlag = "Y";
			}
			if (months.equals("*") && daysOfWeek.equals("*") && !dates.equals("*") && !hours.equals("*")
					&& !minutes.equals("*")) {
				dto.setMONTHLY_FLAG("Y");
				monthlyFlag = "Y";
			}
			if (dates.equals("*") && months.equals("*") && !minutes.equals("*") && !hours.equals("*")
					&& !daysOfWeek.equals("*")) {
				dto.setWEEKLY_FLAG("Y");
				weeklyFlag = "Y";
			}

			if (dates.equals("*") && months.equals("*") && !minutes.equals("*") && hours.equals("*")
					&& daysOfWeek.equals("*")) {

				dto.setHOURLY_FLAG("Y");
				hourlyFlag = "Y";
			}

			try {
				if (dailyFlag.equalsIgnoreCase("Y")) {
					if (hours.contains(",")) {
						for (String hour : hours.split(",")) {
							if (minutes.contains(",")) {
								for (String minute : minutes.split(",")) {
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,DAILY_FLAG,JOB_SCHEDULE_TIME,schedule_type,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, dailyFlag);
									pstm.setString(5, hour + ":" + minute + ":00");
									pstm.setString(6, "R");
									pstm.setInt(7, dto.getProject_sequence());
									pstm.setString(8, dto.getUser());
									pstm.setString(9, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							} else {
								insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
										+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,DAILY_FLAG,JOB_SCHEDULE_TIME,schedule_type,project_id,BATCH_USER,CREATED_BY)"
										+ "values(?,?,?,?,?,?,?,?,?)";
								pstm = conn.prepareStatement(insertConnDetails);
								pstm.setString(1, dto.getBATCH_NAME());
								pstm.setString(2, dto.getBATCH_DESC());
								pstm.setString(3, dto.getArgument_4());
								pstm.setString(4, dailyFlag);
								pstm.setString(5, hour + ":" + minutes + ":00");
								pstm.setString(6, "R");
								pstm.setInt(7, dto.getProject_sequence());

								pstm.executeUpdate();
								pstm.close();
								status = "success";
							}
						}
					} else {
						if (minutes.contains(",")) {
							for (String minute : minutes.split(",")) {
								insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
										+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,DAILY_FLAG,JOB_SCHEDULE_TIME,schedule_type,project_id,BATCH_USER,CREATED_BY)"
										+ "values(?,?,?,?,?,?,?,?,?)";
								pstm = conn.prepareStatement(insertConnDetails);
								pstm.setString(1, dto.getBATCH_NAME());
								pstm.setString(2, dto.getBATCH_DESC());
								pstm.setString(3, dto.getArgument_4());
								pstm.setString(4, dailyFlag);
								pstm.setString(5, hours + ":" + minute + ":00");
								pstm.setString(6, "R");
								pstm.setInt(7, dto.getProject_sequence());
								pstm.setString(8, dto.getUser());
								pstm.setString(9, dto.getUser());
								pstm.executeUpdate();
								pstm.close();
								status = "success";
							}
						} else {
							insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
									+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,DAILY_FLAG,JOB_SCHEDULE_TIME,schedule_type,project_id,BATCH_USER,CREATED_BY)"
									+ "values(?,?,?,?,?,?,?,?,?)";
							pstm = conn.prepareStatement(insertConnDetails);
							pstm.setString(1, dto.getBATCH_NAME());
							pstm.setString(2, dto.getBATCH_DESC());
							pstm.setString(3, dto.getArgument_4());
							pstm.setString(4, dailyFlag);
							pstm.setString(5, hours + ":" + minutes + ":00");
							pstm.setString(6, "R");
							pstm.setInt(7, dto.getProject_sequence());
							pstm.setString(8, dto.getUser());
							pstm.setString(9, dto.getUser());
							pstm.executeUpdate();
							pstm.close();
							status = "success";
						}

					}
				}
				if (monthlyFlag.equalsIgnoreCase("Y")) {
					if (dates.contains(",")) {
						for (String date : dates.split(",")) {
							if (hours.contains(",")) {
								for (String hour : hours.split(",")) {
									if (minutes.contains(",")) {
										for (String minute : minutes.split(",")) {
											insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
													+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
													+ "values(?,?,?,?,?,?,?,?,?,?)";
											pstm = conn.prepareStatement(insertConnDetails);
											pstm.setString(1, dto.getBATCH_NAME());
											pstm.setString(2, dto.getBATCH_DESC());
											pstm.setString(3, dto.getArgument_4());
											pstm.setString(4, monthlyFlag);
											pstm.setString(5, hour + ":" + minute + ":00");
											pstm.setString(6, "R");
											pstm.setString(7, date);
											pstm.setInt(8, dto.getProject_sequence());
											pstm.setString(9, dto.getUser());
											pstm.setString(10, dto.getUser());
											pstm.executeUpdate();
											pstm.close();
											status = "success";
										}
									} else {
										insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
												+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
												+ "values(?,?,?,?,?,?,?,?,?,?)";
										pstm = conn.prepareStatement(insertConnDetails);
										pstm.setString(1, dto.getBATCH_NAME());
										pstm.setString(2, dto.getBATCH_DESC());
										pstm.setString(3, dto.getArgument_4());
										pstm.setString(4, monthlyFlag);
										pstm.setString(5, hour + ":" + minutes + ":00");
										pstm.setString(6, "R");
										pstm.setString(7, date);
										pstm.setInt(8, dto.getProject_sequence());
										pstm.setString(9, dto.getUser());
										pstm.setString(10, dto.getUser());
										pstm.executeUpdate();
										pstm.close();
										status = "success";
									}
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, monthlyFlag);
									pstm.setString(5, hour + ":" + minutes + ":00");
									pstm.setString(6, "R");
									pstm.setString(7, date);
									pstm.setInt(8, dto.getProject_sequence());
									pstm.setString(9, dto.getUser());
									pstm.setString(10, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							} else {
								insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
										+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
										+ "values(?,?,?,?,?,?,?,?,?,?)";
								pstm = conn.prepareStatement(insertConnDetails);
								pstm.setString(1, dto.getBATCH_NAME());
								pstm.setString(2, dto.getBATCH_DESC());
								pstm.setString(3, dto.getArgument_4());
								pstm.setString(4, monthlyFlag);
								pstm.setString(5, hours + ":" + minutes + ":00");
								pstm.setString(6, "R");
								pstm.setString(7, date);
								pstm.setInt(8, dto.getProject_sequence());
								pstm.setString(9, dto.getUser());
								pstm.setString(10, dto.getUser());
								pstm.executeUpdate();
								pstm.close();
								status = "success";
							}
							insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
									+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
									+ "values(?,?,?,?,?,?,?,?,?,?)";
							pstm = conn.prepareStatement(insertConnDetails);
							pstm.setString(1, dto.getBATCH_NAME());
							pstm.setString(2, dto.getBATCH_DESC());
							pstm.setString(3, dto.getArgument_4());
							pstm.setString(4, monthlyFlag);
							pstm.setString(5, hours + ":" + minutes + ":00");
							pstm.setString(6, "R");
							pstm.setString(7, date);
							pstm.setInt(8, dto.getProject_sequence());
							pstm.setString(9, dto.getUser());
							pstm.setString(10, dto.getUser());
							pstm.executeUpdate();
							pstm.close();
							status = "success";
						}
					} else {
						if (hours.contains(",")) {
							for (String hour : hours.split(",")) {
								if (minutes.contains(",")) {
									for (String minute : minutes.split(",")) {
										insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
												+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
												+ "values(?,?,?,?,?,?,?,?,?,?)";
										pstm = conn.prepareStatement(insertConnDetails);
										pstm.setString(1, dto.getBATCH_NAME());
										pstm.setString(2, dto.getBATCH_DESC());
										pstm.setString(3, dto.getArgument_4());
										pstm.setString(4, monthlyFlag);
										pstm.setString(5, hour + ":" + minutes + ":00");
										pstm.setString(6, "R");
										pstm.setString(7, dates);
										pstm.setInt(8, dto.getProject_sequence());
										pstm.setString(9, dto.getUser());
										pstm.setString(10, dto.getUser());
										pstm.executeUpdate();
										pstm.close();
										status = "success";
									}
								} else {
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, monthlyFlag);
									pstm.setString(5, hour + ":" + minutes + ":00");
									pstm.setString(6, "R");
									pstm.setString(7, dates);
									pstm.setInt(8, dto.getProject_sequence());
									pstm.setString(9, dto.getUser());
									pstm.setString(10, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							}
						} else {
							if (minutes.contains(",")) {
								for (String minute : minutes.split(",")) {
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, monthlyFlag);
									pstm.setString(5, hours + ":" + minute + ":00");
									pstm.setString(6, "R");
									pstm.setString(7, dates);
									pstm.setInt(8, dto.getProject_sequence());
									pstm.setString(9, dto.getUser());
									pstm.setString(10, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							} else {
								insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
										+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,MONTHLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,MONTH_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
										+ "values(?,?,?,?,?,?,?,?,?,?)";
								pstm = conn.prepareStatement(insertConnDetails);
								pstm.setString(1, dto.getBATCH_NAME());
								pstm.setString(2, dto.getBATCH_DESC());
								pstm.setString(3, dto.getArgument_4());
								pstm.setString(4, monthlyFlag);
								pstm.setString(5, hours + ":" + minutes + ":00");
								pstm.setString(6, "R");
								pstm.setString(7, dates);
								pstm.setInt(8, dto.getProject_sequence());
								pstm.setString(9, dto.getUser());
								pstm.setString(10, dto.getUser());
								pstm.executeUpdate();
								pstm.close();
								status = "success";
							}

						}
					}
				}

				if (weeklyFlag.equalsIgnoreCase("Y")) {
					if (daysOfWeek.contains(",")) {
						for (String day : daysOfWeek.split(",")) {
							if (hours.contains(",")) {
								for (String hour : hours.split(",")) {
									if (minutes.contains(",")) {
										for (String minute : minutes.split(",")) {
											insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
													+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
													+ "values(?,?,?,?,?,?,?,?,?,?)";
											pstm = conn.prepareStatement(insertConnDetails);
											pstm.setString(1, dto.getBATCH_NAME());
											pstm.setString(2, dto.getBATCH_DESC());
											pstm.setString(3, dto.getArgument_4());
											pstm.setString(4, weeklyFlag);
											pstm.setString(5, hour + ":" + minute + ":00");
											pstm.setString(6, "R");
											pstm.setString(7, day);
											pstm.setInt(8, dto.getProject_sequence());
											pstm.setString(9, dto.getUser());
											pstm.setString(10, dto.getUser());
											pstm.executeUpdate();
											pstm.close();
											status = "success";
										}
									} else {
										insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
												+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
												+ "values(?,?,?,?,?,?,?,?,?,?)";
										pstm = conn.prepareStatement(insertConnDetails);
										pstm.setString(1, dto.getBATCH_NAME());
										pstm.setString(2, dto.getBATCH_DESC());
										pstm.setString(3, dto.getArgument_4());
										pstm.setString(4, weeklyFlag);
										pstm.setString(5, hour + ":" + minutes + ":00");
										pstm.setString(6, "R");
										pstm.setString(7, day);
										pstm.setInt(8, dto.getProject_sequence());
										pstm.setString(9, dto.getUser());
										pstm.setString(10, dto.getUser());
										pstm.executeUpdate();
										pstm.close();
										status = "success";

									}
								}
							} else {
								if (minutes.contains(",")) {
									for (String minute : minutes.split(",")) {
										insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
												+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
												+ "values(?,?,?,?,?,?,?,?,?,?)";
										pstm = conn.prepareStatement(insertConnDetails);
										pstm.setString(1, dto.getBATCH_NAME());
										pstm.setString(2, dto.getBATCH_DESC());
										pstm.setString(3, dto.getArgument_4());
										pstm.setString(4, weeklyFlag);
										pstm.setString(5, hours + ":" + minute + ":00");
										pstm.setString(6, "R");
										pstm.setString(7, day);
										pstm.setInt(8, dto.getProject_sequence());
										pstm.setString(9, dto.getUser());
										pstm.setString(10, dto.getUser());
										pstm.executeUpdate();
										pstm.close();
										status = "success";
									}
								} else {
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, weeklyFlag);
									pstm.setString(5, hours + ":" + minutes + ":00");
									pstm.setString(6, "R");
									pstm.setString(7, day);
									pstm.setInt(8, dto.getProject_sequence());
									pstm.setString(9, dto.getUser());
									pstm.setString(10, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							}
						}
					} else {
						if (hours.contains(",")) {
							for (String hour : hours.split(",")) {
								if (minutes.contains(",")) {
									for (String minute : minutes.split(",")) {
										insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
												+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
												+ "values(?,?,?,?,?,?,?,?,?,?)";
										pstm = conn.prepareStatement(insertConnDetails);
										pstm.setString(1, dto.getBATCH_NAME());
										pstm.setString(2, dto.getBATCH_DESC());
										pstm.setString(3, dto.getArgument_4());
										pstm.setString(4, weeklyFlag);
										pstm.setString(5, hour + ":" + minute + ":00");
										pstm.setString(6, "R");
										pstm.setString(7, daysOfWeek);
										pstm.setInt(8, dto.getProject_sequence());
										pstm.setString(9, dto.getUser());
										pstm.setString(10, dto.getUser());
										pstm.close();
										status = "success";
									}
								} else {
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, weeklyFlag);
									pstm.setString(5, hour + ":" + minutes + ":00");
									pstm.setString(6, "R");
									pstm.setString(7, daysOfWeek);
									pstm.setInt(8, dto.getProject_sequence());
									pstm.setString(9, dto.getUser());
									pstm.setString(10, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							}
						} else {
							if (minutes.contains(",")) {
								for (String minute : minutes.split(",")) {
									insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
											+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
											+ "values(?,?,?,?,?,?,?,?,?,?)";
									pstm = conn.prepareStatement(insertConnDetails);
									pstm.setString(1, dto.getBATCH_NAME());
									pstm.setString(2, dto.getBATCH_DESC());
									pstm.setString(3, dto.getArgument_4());
									pstm.setString(4, weeklyFlag);
									pstm.setString(5, hours + ":" + minute + ":00");
									pstm.setString(6, "R");
									pstm.setString(7, daysOfWeek);
									pstm.setInt(8, dto.getProject_sequence());
									pstm.setString(9, dto.getUser());
									pstm.setString(10, dto.getUser());
									pstm.executeUpdate();
									pstm.close();
									status = "success";
								}
							} else {
								insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
										+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,WEEKLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,WEEK_RUN_DAY,project_id,BATCH_USER,CREATED_BY)"
										+ "values(?,?,?,?,?,?,?,?,?,?)";
								pstm = conn.prepareStatement(insertConnDetails);
								pstm.setString(1, dto.getBATCH_NAME());
								pstm.setString(2, dto.getBATCH_DESC());
								pstm.setString(3, dto.getArgument_4());
								pstm.setString(4, weeklyFlag);
								pstm.setString(5, hours + ":" + minutes + ":00");
								pstm.setString(6, "R");
								pstm.setString(7, daysOfWeek);
								pstm.setInt(8, dto.getProject_sequence());
								pstm.setString(9, dto.getUser());
								pstm.setString(10, dto.getUser());
								pstm.executeUpdate();
								pstm.close();
								status = "success";
							}
						}

					}
				}
				if (hourlyFlag.equalsIgnoreCase("Y")) {
					insertConnDetails = "insert into " + MetadataDBConstants.BATCHTABLE
							+ "(BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,argument_4,HOURLY_FLAG,JOB_SCHEDULE_TIME,schedule_type,project_id,BATCH_USER,CREATED_BY)"
							+ "values(?,?,?,?,?,?,?,?,?)";
					pstm = conn.prepareStatement(insertConnDetails);
					pstm.setString(1, dto.getBATCH_NAME());
					pstm.setString(2, dto.getBATCH_DESC());
					pstm.setString(3, dto.getArgument_4());
					pstm.setString(4, hourlyFlag);
					pstm.setString(5, "00:00:00");
					pstm.setString(6, "R");
					pstm.setInt(7, dto.getProject_sequence());
					pstm.setString(8, dto.getUser());
					pstm.setString(9, dto.getUser());
					pstm.executeUpdate();
					pstm.close();
					status = "success";
				}
			} catch (SQLException e) {
				throw e;
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					throw e;
				}
			}

		}
		return status;

	}

	/*
	 * @see
	 * com.juniper.dao.JuniperOnPremAdhocTaskDao#getProjectSeq(java.sql.Connection,java.lang.String)
	 */
	/**
	 * @param conn
	 * @param project_id
	 * @return integer
	 */
	@Override
	public int getProjectSeq(Connection conn, String project_id) throws Exception {

		String insertConnDetails = "";
		PreparedStatement pstm = null;
		int project_seq = 0;

		insertConnDetails = "select PROJECT_SEQUENCE from JUNIPER_PROJECT_MASTER where PROJECT_ID='" + project_id + "'";
		try {
			pstm = conn.prepareStatement(insertConnDetails);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(insertConnDetails);
			if (rs.isBeforeFirst()) {
				rs.next();
				project_seq = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return project_seq;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#getBatchDetails(java.sql.Connection, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param batch_id
	 * @param project_id
	 * @return BatchTableDetailsDTO
	 */
	@Override
	public BatchTableDetailsDTO getBatchDetails(Connection conn, String batch_id, int project_id) throws Exception {
		BatchTableDetailsDTO arr = new BatchTableDetailsDTO();
		String insertConnDetails = "";
		PreparedStatement pstm = null;
		insertConnDetails = "select BATCH_UNIQUE_NAME,BATCH_DESCRIPTION,DAILY_FLAG,WEEKLY_FLAG,MONTHLY_FLAG,ARGUMENT_4,"
				+ "YEARLY_FLAG,JOB_SCHEDULE_TIME,WEEK_RUN_DAY,MONTH_RUN_VAL,MONTH_RUN_DAY,PROJECT_ID,WEEK_NUM_MONTH,SCHEDULE_TYPE,HOURLY_FLAG,ARGUMENT_1,ARGUMENT_3"
				+ " from JUNIPER_SCH_BATCH_DETAILS " + "where BATCH_UNIQUE_NAME='" + batch_id + "'"
				+ " and PROJECT_ID='" + project_id + "'";
		try {
			pstm = conn.prepareStatement(insertConnDetails);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(insertConnDetails);
			if (rs.next()) {
				arr.setBATCH_NAME(rs.getString("BATCH_UNIQUE_NAME"));
				arr.setBATCH_DESC(rs.getString("BATCH_DESCRIPTION"));
				arr.setDAILY_FLAG(rs.getString("DAILY_FLAG"));
				arr.setWEEKLY_FLAG(rs.getString("WEEKLY_FLAG"));
				arr.setMONTHLY_FLAG(rs.getString("MONTHLY_FLAG"));
				arr.setYEARLY_FLAG(rs.getString("YEARLY_FLAG"));
				arr.setJOB_SCHEDULE_TIME(rs.getString("JOB_SCHEDULE_TIME"));
				arr.setWEEK_RUN_DAY(rs.getString("WEEK_RUN_DAY"));
				arr.setMONTH_RUN_DAY(rs.getString("MONTH_RUN_DAY"));
				arr.setMONTH_RUN_VAL(rs.getString("MONTH_RUN_VAL"));
				arr.setWEEK_NUM_MONTH(rs.getString("WEEK_NUM_MONTH"));
				arr.setProject_sequence(rs.getInt("PROJECT_ID"));
				arr.setSCHEDULE_TYPE(rs.getString("SCHEDULE_TYPE"));
				arr.setArgument_4(rs.getString("ARGUMENT_4"));
				arr.setHOURLY_FLAG(rs.getString("HOURLY_FLAG"));
				arr.setArgument_1(rs.getString("ARGUMENT_1"));
				arr.setArgument_3(rs.getString("ARGUMENT_3"));

			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return arr;
	}

	/*
	 * @see
	 * com.juniper.dao.JuniperOnPremAdhocTaskDao#updateAdhocTaskPredeccessors(java.sql.Connection, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String updateAdhocTaskPredeccessors(Connection conn, String batch_id, int project_id) throws Exception {
		String status = "Failed";
		Statement statement;
		try {
			statement = conn.createStatement();
			String updateScheduleDetails = "update " + MetadataDBConstants.ADHOCSCHEDULETABLE
					+ " set PREDESSOR_JOB_ID_1='',PREDESSOR_JOB_ID_2='',PREDESSOR_JOB_ID_3='',PREDESSOR_JOB_ID_4='',PREDESSOR_JOB_ID_5='',"
					+ "PREDESSOR_JOB_ID_6='',PREDESSOR_JOB_ID_7='',PREDESSOR_JOB_ID_8='',PREDESSOR_JOB_ID_9='',PREDESSOR_JOB_ID_10=''"
					+ " where BATCH_ID='" + batch_id + "' and project_id=" + project_id;
			statement.executeQuery(updateScheduleDetails);
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return status;
	}

	/*
	 * @see
	 * com.juniper.dao.JuniperOnPremAdhocTaskDao#updateScheduleTableFromAdhocTable(java.sql.Connection, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String updateScheduleTableFromAdhocTable(Connection conn, String batch_id, int project_id) throws Exception {
		String status = "Failed";
		try {
			String updateScheduleDetails = "insert into " + MetadataDBConstants.SCHEDULETABLE
					+ "(JOB_ID,JOB_NAME,BATCH_ID,PRE_PROCESSING,POST_PROCESSING,COMMAND,ARGUMENT_1,ARGUMENT_2,ARGUMENT_3,ARGUMENT_4,ARGUMENT_5,DAILY_FLAG,WEEKLY_FLAG,MONTHLY_FLAG,YEARLY_FLAG,HOURLY_FLAG,JOB_SCHEDULE_TIME,PREDESSOR_JOB_ID_1,PREDESSOR_JOB_ID_2,PREDESSOR_JOB_ID_3,PREDESSOR_JOB_ID_4,PREDESSOR_JOB_ID_5,PREDESSOR_JOB_ID_6,PREDESSOR_JOB_ID_7,PREDESSOR_JOB_ID_8,PREDESSOR_JOB_ID_9,PREDESSOR_JOB_ID_10,IS_DEPENDENT_JOB,WEEK_RUN_DAY,MONTH_RUN_VAL,MONTH_RUN_DAY,COMMAND_TYPE,PROJECT_ID,FEED_ID,WEEK_NUM_MONTH,IS_SUSPENDED,CREATED_DATE,UPDATED_DATE,SCHEDULE_TYPE,CUSTOM_SCHEDULAR_FLAG,USER_NAME)"
					+ " select JOB_ID,JOB_NAME,BATCH_ID,PRE_PROCESSING,POST_PROCESSING,COMMAND,ARGUMENT_1,ARGUMENT_2,ARGUMENT_3,ARGUMENT_4,ARGUMENT_5,DAILY_FLAG,WEEKLY_FLAG,MONTHLY_FLAG,YEARLY_FLAG,HOURLY_FLAG,JOB_SCHEDULE_TIME,PREDESSOR_JOB_ID_1,PREDESSOR_JOB_ID_2,PREDESSOR_JOB_ID_3,PREDESSOR_JOB_ID_4,PREDESSOR_JOB_ID_5,PREDESSOR_JOB_ID_6,PREDESSOR_JOB_ID_7,PREDESSOR_JOB_ID_8,PREDESSOR_JOB_ID_9,PREDESSOR_JOB_ID_10,IS_DEPENDENT_JOB,WEEK_RUN_DAY,MONTH_RUN_VAL,MONTH_RUN_DAY,COMMAND_TYPE,PROJECT_ID,FEED_ID,WEEK_NUM_MONTH,IS_SUSPENDED,CREATED_DATE,UPDATED_DATE,SCHEDULE_TYPE,'Y',CREATED_BY from "
					+ MetadataDBConstants.ADHOCSCHEDULETABLE + " where BATCH_ID='" + batch_id + "' and project_id="
					+ project_id;
			Statement statement_update = conn.createStatement();
			statement_update.executeQuery(updateScheduleDetails);
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#deleteScheduleTable(java.sql.Connection, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String deleteScheduleTable(Connection conn, String batch_id, int project_id) throws Exception {
		String status = "Failed";
		String deleteScheduleDetails = "delete from " + MetadataDBConstants.SCHEDULETABLE + " where BATCH_ID='"
				+ batch_id + "' and project_id=" + project_id;
		Statement statement_update;
		try {
			statement_update = conn.createStatement();
			statement_update.executeUpdate(deleteScheduleDetails);
			status = "Success";

		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#editBatchDetails(java.sql.Connection, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param batch_id
	 * @param project_id
	 * @return String
	 */
	@Override
	public String editBatchDetails(Connection conn, String batch_id, int project_id) throws Exception {
		String deleteScheduleDetails = "";
		Statement statement = null;
		String status = "Failed";
		try {
			statement = conn.createStatement();
			deleteScheduleDetails = "delete from " + MetadataDBConstants.BATCHTABLE + " where BATCH_UNIQUE_NAME='"
					+ batch_id + "' and project_id=" + project_id;
			statement.executeUpdate(deleteScheduleDetails);
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateAdhocScheduleTable(java.sql.Connection, com.juniper.dto.BatchTableDetailsDTO)
	 */
	/**
	 * @param conn
	 * @param batchDTO
	 * @return String
	 */
	@Override
	public String updateAdhocScheduleTable(Connection conn, BatchTableDetailsDTO batchDTO) throws Exception {
		String updateScheduleDetails = "";
		String status = "Failed";
		PreparedStatement pstm = null;
		try {
			if (batchDTO.getSCHEDULE_TYPE() == "K" || batchDTO.getSCHEDULE_TYPE() == "A"
					|| batchDTO.getSCHEDULE_TYPE() == "F") {
				updateScheduleDetails = "update " + MetadataDBConstants.ADHOCSCHEDULETABLE
						+ " set DAILY_FLAG=?,WEEKLY_FLAG=?,MONTHLY_FLAG=?,YEARLY_FLAG=?,ARGUMENT_4=?,WEEK_RUN_DAY=?"
						+ ",MONTH_RUN_VAL=?,MONTH_RUN_DAY=?,WEEK_NUM_MONTH=?,SCHEDULE_TYPE=?,JOB_SCHEDULE_TIME=?,HOURLY_FLAG=?,ARGUMENT_1=?,ARGUMENT_2=? where batch_id=? and project_id=?";
				pstm = conn.prepareStatement(updateScheduleDetails);
				pstm.setString(1, batchDTO.getDAILY_FLAG());
				pstm.setString(2, batchDTO.getWEEKLY_FLAG());
				pstm.setString(3, batchDTO.getMONTHLY_FLAG());
				pstm.setString(4, batchDTO.getYEARLY_FLAG());
				pstm.setString(5, batchDTO.getArgument_4());
				pstm.setString(6, batchDTO.getWEEK_RUN_DAY());
				pstm.setString(7, batchDTO.getMONTH_RUN_VAL());
				pstm.setString(8, batchDTO.getMONTH_RUN_DAY());
				pstm.setString(9, batchDTO.getWEEK_NUM_MONTH());
				pstm.setString(10, batchDTO.getSCHEDULE_TYPE());
				pstm.setString(11, batchDTO.getJOB_SCHEDULE_TIME());
				pstm.setString(12, batchDTO.getHOURLY_FLAG());
				pstm.setString(13, batchDTO.getArgument_1());
				pstm.setString(14, batchDTO.getArgument_2());
				pstm.setString(15, batchDTO.getBATCH_NAME());
				pstm.setInt(16, batchDTO.getProject_sequence());
			} else {
				updateScheduleDetails = "update " + MetadataDBConstants.ADHOCSCHEDULETABLE
						+ " set DAILY_FLAG=?,WEEKLY_FLAG=?,MONTHLY_FLAG=?,YEARLY_FLAG=?,ARGUMENT_4=?,WEEK_RUN_DAY=?"
						+ ",MONTH_RUN_VAL=?,MONTH_RUN_DAY=?,WEEK_NUM_MONTH=?,SCHEDULE_TYPE=?,JOB_SCHEDULE_TIME=?,HOURLY_FLAG=? where batch_id=? and project_id=?";
				pstm = conn.prepareStatement(updateScheduleDetails);
				pstm.setString(1, batchDTO.getDAILY_FLAG());
				pstm.setString(2, batchDTO.getWEEKLY_FLAG());
				pstm.setString(3, batchDTO.getMONTHLY_FLAG());
				pstm.setString(4, batchDTO.getYEARLY_FLAG());
				pstm.setString(5, batchDTO.getArgument_4());
				pstm.setString(6, batchDTO.getWEEK_RUN_DAY());
				pstm.setString(7, batchDTO.getMONTH_RUN_VAL());
				pstm.setString(8, batchDTO.getMONTH_RUN_DAY());
				pstm.setString(9, batchDTO.getWEEK_NUM_MONTH());
				pstm.setString(10, batchDTO.getSCHEDULE_TYPE());
				pstm.setString(11, batchDTO.getJOB_SCHEDULE_TIME());
				pstm.setString(12, batchDTO.getHOURLY_FLAG());
				pstm.setString(13, batchDTO.getBATCH_NAME());
				pstm.setInt(14, batchDTO.getProject_sequence());
			}

			pstm.executeUpdate();
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateFinalScheduleTable(java.sql.Connection, com.juniper.dto.BatchTableDetailsDTO) 
	 */
	/**
	 * @param conn
	 * @param batchDTO
	 * @return String
	 */
	@Override
	public String updateFinalScheduleTable(Connection conn, BatchTableDetailsDTO batchDTO) throws Exception {
		String updateScheduleDetails = "";
		String status = "Failed";
		PreparedStatement pstm = null;
		try {
			if (batchDTO.getSCHEDULE_TYPE() == "K" || batchDTO.getSCHEDULE_TYPE() == "A"
					|| batchDTO.getSCHEDULE_TYPE() == "F") {
				updateScheduleDetails = "update " + MetadataDBConstants.SCHEDULETABLE
						+ " set DAILY_FLAG=?,WEEKLY_FLAG=?,MONTHLY_FLAG=?,YEARLY_FLAG=?,ARGUMENT_4=?,WEEK_RUN_DAY=?"
						+ ",MONTH_RUN_VAL=?,MONTH_RUN_DAY=?,WEEK_NUM_MONTH=?,SCHEDULE_TYPE=?,JOB_SCHEDULE_TIME=?,HOURLY_FLAG=?,ARGUMENT_1=?,ARGUMENT_2=? where batch_id=? and project_id=?";
				pstm = conn.prepareStatement(updateScheduleDetails);
				pstm.setString(1, batchDTO.getDAILY_FLAG());
				pstm.setString(2, batchDTO.getWEEKLY_FLAG());
				pstm.setString(3, batchDTO.getMONTHLY_FLAG());
				pstm.setString(4, batchDTO.getYEARLY_FLAG());
				pstm.setString(5, batchDTO.getArgument_4());
				pstm.setString(6, batchDTO.getWEEK_RUN_DAY());
				pstm.setString(7, batchDTO.getMONTH_RUN_VAL());
				pstm.setString(8, batchDTO.getMONTH_RUN_DAY());
				pstm.setString(9, batchDTO.getWEEK_NUM_MONTH());
				pstm.setString(10, batchDTO.getSCHEDULE_TYPE());
				pstm.setString(11, batchDTO.getJOB_SCHEDULE_TIME());
				pstm.setString(12, batchDTO.getHOURLY_FLAG());
				pstm.setString(13, batchDTO.getArgument_1());
				pstm.setString(14, batchDTO.getArgument_2());
				pstm.setString(15, batchDTO.getBATCH_NAME());
				pstm.setInt(16, batchDTO.getProject_sequence());
			} else {
				updateScheduleDetails = "update " + MetadataDBConstants.SCHEDULETABLE
						+ " set DAILY_FLAG=?,WEEKLY_FLAG=?,MONTHLY_FLAG=?,YEARLY_FLAG=?,ARGUMENT_4=?,WEEK_RUN_DAY=?"
						+ ",MONTH_RUN_VAL=?,MONTH_RUN_DAY=?,WEEK_NUM_MONTH=?,SCHEDULE_TYPE=?,JOB_SCHEDULE_TIME=?,HOURLY_FLAG=? where batch_id=? and project_id=?";
				pstm = conn.prepareStatement(updateScheduleDetails);
				pstm.setString(1, batchDTO.getDAILY_FLAG());
				pstm.setString(2, batchDTO.getWEEKLY_FLAG());
				pstm.setString(3, batchDTO.getMONTHLY_FLAG());
				pstm.setString(4, batchDTO.getYEARLY_FLAG());
				pstm.setString(5, batchDTO.getArgument_4());
				pstm.setString(6, batchDTO.getWEEK_RUN_DAY());
				pstm.setString(7, batchDTO.getMONTH_RUN_VAL());
				pstm.setString(8, batchDTO.getMONTH_RUN_DAY());
				pstm.setString(9, batchDTO.getWEEK_NUM_MONTH());
				pstm.setString(10, batchDTO.getSCHEDULE_TYPE());
				pstm.setString(11, batchDTO.getJOB_SCHEDULE_TIME());
				pstm.setString(12, batchDTO.getHOURLY_FLAG());
				pstm.setString(13, batchDTO.getBATCH_NAME());
				pstm.setInt(14, batchDTO.getProject_sequence());
			}

			pstm.executeUpdate();
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateAdhocScheduleTable(java.sql.Connection, com.juniper.dto.AdhocTask) 
	 */
	/**
	 * @param conn
	 * @param dto
	 * @return String
	 */
	@Override
	public String updateAdhocScheduleTable(Connection conn, AdhocTask dto) throws Exception {
		String updateScheduleDetails = "";
		String status = "Failed";
		PreparedStatement pstm = null;

		updateScheduleDetails = "update " + MetadataDBConstants.ADHOCSCHEDULETABLE
				+ " set COMMAND= ?,COMMAND_TYPE= ?,ARGUMENT_1= ?,ARGUMENT_2= ?,ARGUMENT_3= ? where batch_id= ? and project_id= ? and job_name= ?";
		try {
			pstm = conn.prepareStatement(updateScheduleDetails);
			pstm.setString(1, dto.getCommand());
			pstm.setString(2, dto.getCommand_type());
			pstm.setString(3, dto.getArgument_1());
			pstm.setString(4, dto.getArgument_2());
			pstm.setString(5, dto.getArgument_3());
			pstm.setString(6, dto.getBATCH_ID());
			pstm.setInt(7, dto.getProject_sequence());
			pstm.setString(8, dto.getJOB_NAME());
			pstm.executeUpdate();
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateScheduleTable(java.sql.Connection, com.juniper.dto.AdhocTask)
	 */
	/**
	 * @param conn
	 * @param dto
	 * @return String
	 */
	@Override
	public String updateScheduleTable(Connection conn, AdhocTask dto) throws Exception {
		String updateScheduleDetails = "";
		String status = "Failed";
		PreparedStatement pstm = null;

		updateScheduleDetails = "update " + MetadataDBConstants.SCHEDULETABLE
				+ " set COMMAND= ?,COMMAND_TYPE= ?,ARGUMENT_1= ?,ARGUMENT_2= ?,ARGUMENT_3= ? where batch_id= ? and project_id= ? and job_name= ?";
		try {
			pstm = conn.prepareStatement(updateScheduleDetails);
			pstm.setString(1, dto.getCommand());
			pstm.setString(2, dto.getCommand_type());
			pstm.setString(3, dto.getArgument_1());
			pstm.setString(4, dto.getArgument_2());
			pstm.setString(5, dto.getArgument_3());
			pstm.setString(6, dto.getBATCH_ID());
			pstm.setInt(7, dto.getProject_sequence());
			pstm.setString(8, dto.getJOB_NAME());
			pstm.executeUpdate();
			status = "Success";
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				pstm.close();
				conn.close();
			} catch (SQLException e) {
				throw e;
			}
		}

		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#insertAdhocTaskSequence(java.sql.Connection, java.util.ArrayList) 
	 */
	/**
	 * @param conn
	 * @param dtos
	 * @return String
	 */
	@Override
	public String insertAdhocTaskSequence(Connection conn, ArrayList<AdhocTaskSequence> dtos) throws Exception {
		String insertAdhocTaskDetails = "";
		PreparedStatement pstm = null;
		String status = "Failed";
		String predeccessor_id = "";
		String job_id = "";
		try {
			for (AdhocTaskSequence dto : dtos) {
				String predeccessor = dto.getPredeccessor();
				if (predeccessor.isEmpty()) {
					predeccessor_id = dto.getPredeccessor();
				} else {
					predeccessor_id = dto.getProject() + "_" + dto.getBatch() + "_" + dto.getPredeccessor();
				}
				job_id = dto.getProject() + "_" + dto.getBatch() + "_" + dto.getJob_name();

				if (dto.getSequence() < 11) {
					insertAdhocTaskDetails = "update JUNIPER_SCH_ADHOC_JOB_DETAIL set PREDESSOR_JOB_ID_"
							+ dto.getSequence() + "='" + predeccessor_id + "' where JOB_id='" + job_id
							+ "' and BATCH_ID='" + dto.getBatch() + "'"
							+ " and PROJECT_ID=(select PROJECT_SEQUENCE from JUNIPER_PROJECT_MASTER where PROJECT_ID='"
							+ dto.getProject() + "')";
					pstm = conn.prepareStatement(insertAdhocTaskDetails);
					pstm.executeQuery();
					pstm.close();
					status = "Success";
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}

		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateScheduleBatchPredeccessors(java.sql.Connection, java.util.ArrayList)
	 */
	/**
	 * @param conn
	 * @param dtos
	 * @return String
	 */
	@Override
	public String updateScheduleBatchPredeccessors(Connection conn, ArrayList<AdhocTaskSequence> dtos)
			throws Exception {
		String insertAdhocTaskDetails = "";
		PreparedStatement pstm = null;
		String status = "Failed";
		try {
			for (AdhocTaskSequence dto : dtos) {
				String predeccessor = dto.getPredeccessor();
				if (dto.getSequence() == 1) {
					insertAdhocTaskDetails = "update JUNIPER_SCH_master_JOB_DETAIL set PREDECESSOR_BATCH_ID_"
							+ dto.getSequence() + "='" + predeccessor + "' where BATCH_ID='" + dto.getJob_name() + "'"
							+ " and PROJECT_ID=(select PROJECT_SEQUENCE from JUNIPER_PROJECT_MASTER where PROJECT_ID='"
							+ dto.getProject() + "')";
					pstm = conn.prepareStatement(insertAdhocTaskDetails);
					pstm.executeQuery();
					pstm.close();
					status = "Success";
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}

		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#insertScheduleTable(java.sql.Connection, java.util.ArrayList, com.juniper.dto.BatchTableDetailsDTO) 
	 */
	/**
	 * @param conn
	 * @param dtos
	 * @param batchDto
	 * @return String
	 */
	@Override
	public String insertScheduleTable(Connection conn, ArrayList<AdhocTask> dtos, BatchTableDetailsDTO batchDto)
			throws Exception {

		String insertConnDetails = "";
		PreparedStatement pstm = null;
		String status = "Failed";
		try {
			for (AdhocTask dto : dtos) {
				insertConnDetails = "insert into " + MetadataDBConstants.ADHOCSCHEDULETABLE
						+ "(JOB_ID,JOB_NAME,BATCH_ID,command,argument_1,argument_2,argument_3,argument_4,schedule_type,job_schedule_time,"
						+ "daily_flag,WEEKLY_FLAG,MONTHLY_FLAG,YEARLY_FLAG,"
						+ "WEEK_RUN_DAY,MONTH_RUN_VAL,MONTH_RUN_DAY,WEEK_NUM_MONTH,project_id,COMMAND_TYPE,CREATED_BY,HOURLY_FLAG)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstm = conn.prepareStatement(insertConnDetails);
				pstm.setString(1, dto.getJOB_ID());
				pstm.setString(2, dto.getJOB_NAME());
				pstm.setString(3, dto.getBATCH_ID());
				pstm.setString(4, dto.getCommand());
				if (batchDto.getSCHEDULE_TYPE().equalsIgnoreCase("K")
						|| batchDto.getSCHEDULE_TYPE().equalsIgnoreCase("F")
						|| batchDto.getSCHEDULE_TYPE().equalsIgnoreCase("A")) {
					pstm.setString(5, batchDto.getArgument_1());
					pstm.setString(6, batchDto.getArgument_2());
					pstm.setString(7, batchDto.getArgument_3());
				} else {
					pstm.setString(5, dto.getArgument_1());
					pstm.setString(6, dto.getArgument_2());
					pstm.setString(7, dto.getArgument_3());
				}

				pstm.setString(8, batchDto.getArgument_4());
				pstm.setString(9, batchDto.getSCHEDULE_TYPE());
				pstm.setString(10, batchDto.getJOB_SCHEDULE_TIME());
				pstm.setString(11, batchDto.getDAILY_FLAG());
				pstm.setString(12, batchDto.getWEEKLY_FLAG());
				pstm.setString(13, batchDto.getMONTHLY_FLAG());
				pstm.setString(14, batchDto.getYEARLY_FLAG());
				pstm.setString(15, batchDto.getWEEK_RUN_DAY());
				pstm.setString(16, batchDto.getMONTH_RUN_VAL());
				pstm.setString(17, batchDto.getMONTH_RUN_DAY());
				pstm.setString(18, batchDto.getWEEK_NUM_MONTH());
				pstm.setInt(19, batchDto.getProject_sequence());
				pstm.setString(20, dto.getCommand_type());
				pstm.setString(21, dto.getUser());
				pstm.setString(22, batchDto.getHOURLY_FLAG());
				pstm.executeUpdate();
				pstm.close();
				status = "Success";
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw e;
			}

		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#addBatchFlowSequence(java.sql.Connection, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param BATCH_SEQUENCE_ID
	 * @param BATCH_TYPE
	 * @param BATCH_SEQUENCE
	 * @param PROJECT_SEQUENCE
	 * @return String
	 */
	public String addBatchFlowSequence(Connection conn, String BATCH_SEQUENCE_ID, String BATCH_TYPE,
			String BATCH_SEQUENCE, int PROJECT_SEQUENCE) throws Exception {
		String insertConnDetails = "";
		PreparedStatement pstm = null;
		String status = "Failed";
		try {
			insertConnDetails = "insert into JUNIPER_BATCH_SEQUENCE_DETAILS (BATCH_SEQUENCE_ID,BATCH_TYPE,BATCH_SEQUENCE,PROJECT_SEQUENCE) values(?,?,?,?)";
			pstm = conn.prepareStatement(insertConnDetails);
			pstm.setString(1, BATCH_SEQUENCE_ID);
			pstm.setString(2, BATCH_TYPE);
			pstm.setString(3, BATCH_SEQUENCE);
			pstm.setInt(4, PROJECT_SEQUENCE);
			pstm.executeUpdate();

			status = "Success";
		} catch (Exception e) {
			throw e;
		} finally {
			pstm.close();
			conn.close();
		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateBatchFlowSequence(java.sql.Connection, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param BATCH_SEQUENCE_ID
	 * @param BATCH_TYPE
	 * @param BATCH_SEQUENCE
	 * @param PROJECT_SEQUENCE
	 * @return String
	 */
	public String updateBatchFlowSequence(Connection conn, String BATCH_SEQUENCE_ID, String BATCH_TYPE,
			String BATCH_SEQUENCE, int PROJECT_SEQUENCE) throws Exception {
		String insertConnDetails = "";
		PreparedStatement pstm = null;
		String status = "Failed";
		try {
			insertConnDetails = "update JUNIPER_BATCH_SEQUENCE_DETAILS set BATCH_SEQUENCE=? where BATCH_SEQUENCE_ID=? and PROJECT_SEQUENCE=?";
			pstm = conn.prepareStatement(insertConnDetails);
			pstm.setString(1, BATCH_SEQUENCE);
			pstm.setString(2, BATCH_SEQUENCE_ID);
			pstm.setInt(3, PROJECT_SEQUENCE);
			pstm.executeUpdate();

			status = "Success";
		} catch (Exception e) {
			throw e;
		} finally {
			pstm.close();
			conn.close();
		}
		return status;
	}

	/*
	 * @see com.juniper.dao.JuniperOnPremAdhocTaskDao#updateBatchPredecessors(java.sql.Connection, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	/**
	 * @param conn
	 * @param BATCH_SEQUENCE_ID
	 * @param BATCH_TYPE
	 * @param BATCH_SEQUENCE
	 * @param PROJECT_SEQUENCE
	 * @return String
	 */
	public String updateBatchPredecessors(Connection conn, String BATCH_SEQUENCE_ID, String BATCH_TYPE,
			String BATCH_SEQUENCE, int PROJECT_SEQUENCE) throws Exception {
		String insertConnDetails = "";
		PreparedStatement pstm = null;
		String status = "Failed";
		String batch_id = "";
		try {
			String[] pattern = BATCH_SEQUENCE.split("\\|");
			for (int i = 0; i < pattern.length; i++) {
				batch_id = pattern[i].split("\\,")[0];
				insertConnDetails = "update " + MetadataDBConstants.SCHEDULETABLE
						+ " set PREDECESSOR_BATCH_ID_1='' where BATCH_ID=? and PROJECT_ID=?";
				pstm = conn.prepareStatement(insertConnDetails);
				pstm.setString(1, batch_id);
				pstm.setInt(2, PROJECT_SEQUENCE);
				pstm.executeUpdate();
			}
			status = "Success";
		} catch (Exception e) {
			throw e;
		} finally {
			pstm.close();
			conn.close();
		}
		return status;
	}

}
