package didproject;

import java.sql.*;
import java.util.*;
//import DBaccess;

//******************************************************************************
class DBManager {

	private String host, port, user, password, dbname;
	int n;

	public ArrayList<String> getIds(String tblName, String tblID) {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet rs;
		try {
			DBaccess.connect(host, port, user, password, dbname);
			rs = DBaccess.retrieve("SELECT `"+tblID+"` FROM `" + tblName + "`");
			while (rs.next()) {
				result.add(rs.getString(tblID));
			}
		} catch (Exception e) {
		} finally {
			DBaccess.disconnect();
			return result;
		}

	}

	public boolean exists(String tblName, String field, String value) {
		ArrayList<String> result = new ArrayList<String>();
		int id = 0;
		Boolean is = false;
		ResultSet rs;
		try {
			DBaccess.connect(host, port, user, password, dbname);
			rs = DBaccess.retrieve("SELECT `"+tblName+"ID` FROM `" + tblName
					+ "` WHERE "+field+"='" + value + "'");
			if (!rs.first()) {
				System.err.println("TS_ERROR: No results.");
				is = false;
			} else
				is = true;
		} catch (Exception e) {
		} finally {
			DBaccess.disconnect();
			return is;
		}
	}
    
	public void addLanguage(String languageName) {
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update("INSERT INTO Lang (name,active) VALUES ('"
				+ languageName + "',1)");
		DBaccess.disconnect();
	}

	public void updateLang(int id, String name, int active) {
		String q = "UPDATE Lang SET name = '" + name + "', active = '" + active
				+ "'  WHERE ID = '" + id + "'";
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update(q);
		System.err.println("UPDATE: " + q);
		DBaccess.disconnect();
	}
	
	public void createTable(String tblName) {
		int textWidth1 = 50;
		String[] fields = new String[3];
		fields[0] = "ID";
		fields[1] = "name";
		fields[2] = "active";
		String q = "CREATE TABLE \"" + tblName + "\"" + "(" + fields[0]
				+ " int(11) NOT NULL AUTO_INCREMENT, " + fields[1]
				+ " VARCHAR(" + textWidth1 + ") NOT NULL, " + fields[2]
				+ " int(1) NOT NULL, " + "PRIMARY KEY (\"" + fields[0] + "\")"
				+ ")";
		try {
			DBaccess.connect(host, port, user, password, dbname);
			DBaccess.create("SET sql_mode='ANSI_QUOTES'");
			// Create table proper
			DBaccess.createTable(q);
			System.err.println(tblName + " table created");
		} catch (Exception e) {
		} finally {
			DBaccess.disconnect();
			return;
		}
	}


	public ArrayList<String> getRowById(String tblName,String field,String id) {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet rs;
		
		try {
			DBaccess.connect(host, port, user, password, dbname);
			rs = DBaccess.retrieve("SELECT * FROM `" + tblName
					+ "` WHERE `"+field+"` = '" + id + "'");

			if (!rs.first()) {
				System.err.println("TS_ERROR: No results.");
				return null;
			}
			result.add(rs.getString("ID"));
			result.add(rs.getString("name"));
			result.add(rs.getString("active"));
		} catch (Exception e) {
			System.err
					.println("ERROR: Get Languages e.getm: " + e.getMessage());
		} finally {
			DBaccess.disconnect();
			return result;
		}

	}

	public int size(String tblName) {
		try {
			DBaccess.connect(host, port, user, password, dbname);
			ResultSet rs = DBaccess.retrieve("SELECT COUNT(*) FROM `" + tblName
					+ "`");
			rs.first();
			n = Integer.parseInt(rs.getString("COUNT(*)"));
			System.err.println("TS____ N= " + n);
		} catch (SQLException ex) {
			System.err.println("TS_SQL_ERR_INS = " + ex.getMessage());
			System.err.println("SELECT COUNT(*) FROM \"" + tblName + "\"");
		} catch (NumberFormatException ex) {
			System.err.println("TS_SQL_NUMFORMAT_INS = " + ex.getMessage());
		} finally {
			DBaccess.disconnect();
			return n;
		}

	}

}
