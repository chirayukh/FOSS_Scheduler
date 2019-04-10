package com.juniper.constant;

public class MetadataDBConstants {

	public final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String SYSTEMTABLE = "JUNIPER_SYSTEM_MASTER";
	public static final String PROJECTTABLE = "JUNIPER_PROJECT_MASTER";
	public static final String KEYTABLE = "JUNIPER_EXT_KEY_MASTER";
	public static final String CONNECTIONTABLE = "JUNIPER_EXT_SRC_CONN_MASTER";
	public static final String GETSEQUENCEID = "Select  DATA_DEFAULT from USER_TAB_COLUMNS where TABLE_NAME = '${tableName}' and COLUMN_NAME='${columnName}'";
	public static final String CONNECTIONTABLEKEY = "SRC_CONN_SEQUENCE";
	public static final String INSERTQUERY = "insert into {$table}({$columns}) values({$data})";
	public static final String GETLASTROWID = "SELECT ${id}.currval from dual";
	public static final String COMMA = ",";
	public static final String TEMPTABLEDETAILSTABLE = "JUNIPER_EXT_TABLE_MASTER_TEMP";
	public static final String QUOTE = "'";
	public static final String FEEDSRCTGTLINKTABLE = "JUNIPER_EXT_FEED_SRC_TGT_LINK";
	public static final String TABLEDETAILSTABLE = "JUNIPER_EXT_TABLE_MASTER";
	public static final String BATCHTABLE = "JUNIPER_SCH_BATCH_DETAILS";
	public static final String ADHOCSCHEDULETABLE = "JUNIPER_SCH_ADHOC_JOB_DETAIL";
	public static final String SCHEDULETABLE = "JUNIPER_SCH_MASTER_JOB_DETAIL";
}
