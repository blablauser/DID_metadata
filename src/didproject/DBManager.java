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
			rs = DBaccess.retrieve("SELECT `" + tblID + "` FROM `" + tblName
					+ "`");
			while (rs.next()) {
				result.add(rs.getString(tblID));
			}
		} catch (Exception e) {
		} finally {
			DBaccess.disconnect();
			return result;
		}

	}

	public int exists(String tblName, String field, String value) {
		int id = -1;

		ResultSet rs;
		try {
			DBaccess.connect(host, port, user, password, dbname);
			rs = DBaccess.retrieve("SELECT `" + tblName + "ID` FROM `"
					+ tblName + "` WHERE " + field + "='" + value + "'");
			if (!rs.first()) {
				//System.err.println("<exists> ERROR: No results.");
				id = -1;
			} else {
				id = Integer.parseInt(rs.getString(tblName + "ID"));
			}
		} catch (Exception e) {
			System.err.println("Error parsing ID from <exists>");
		} finally {
			DBaccess.disconnect();
			return id;
		}
	}

	public void addClassifiedIn(int castawayID, int categoryID) {
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update("INSERT INTO classifiedIn (castawayID,categoryID) VALUES ("+castawayID+","+categoryID+")");
		DBaccess.disconnect();
	}
	
	public void addWorksAs(int castawayID, int occupationID) {
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update("INSERT INTO worksAs (castawayID,occupationID) VALUES ("+castawayID+","+occupationID+")");
		DBaccess.disconnect();
	}
	
	public void addCategory(String name) {
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update("INSERT INTO category (name) VALUES ('"+name+"')");
		DBaccess.disconnect();
	}
	
	public void addOccupation(String name) {
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update("INSERT INTO occupation (name) VALUES ('"+name+"')");
		DBaccess.disconnect();
	}
	
	
	public void flagCastaway(int id, String field, int value) {
		String q = "UPDATE castaway SET "+field+" = " + value + " WHERE castawayID = '" + id + "'";
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update(q);
		System.err.println("UPDATE: " + q);
		DBaccess.disconnect();
	}
	
	public void addDoBCastaway(int id, String field, int value) {
		String q = "UPDATE castaway SET "+field+" = " + value + " WHERE castawayID = '" + id + "'";
		DBaccess.connect(host, port, user, password, dbname);
		DBaccess.update(q);
		//System.err.println("UPDATE: " + q);
		DBaccess.disconnect();
	}
//
//	public void createTable(String tblName) {
//		int textWidth1 = 50;
//		String[] fields = new String[3];
//		fields[0] = "ID";
//		fields[1] = "name";
//		fields[2] = "active";
//		String q = "CREATE TABLE \"" + tblName + "\"" + "(" + fields[0]
//				+ " int(11) NOT NULL AUTO_INCREMENT, " + fields[1]
//				+ " VARCHAR(" + textWidth1 + ") NOT NULL, " + fields[2]
//				+ " int(1) NOT NULL, " + "PRIMARY KEY (\"" + fields[0] + "\")"
//				+ ")";
//		try {
//			DBaccess.connect(host, port, user, password, dbname);
//			DBaccess.create("SET sql_mode='ANSI_QUOTES'");
//			// Create table proper
//			DBaccess.createTable(q);
//			System.err.println(tblName + " table created");
//		} catch (Exception e) {
//		} finally {
//			DBaccess.disconnect();
//			return;
//		}
//	}

	public ArrayList<String> getCastaway(String tblName, String id) {
		ArrayList<String> result = new ArrayList<String>();
		ResultSet rs;

		try {
			DBaccess.connect(host, port, user, password, dbname);
			rs = DBaccess.retrieve("SELECT * FROM `" + tblName + "` WHERE `"
					+ tblName + "ID` = '" + id + "'");

			if (!rs.first()) {
				System.err.println("TS_ERROR: Castaway does not exist.");
				return null;
			}

			result.add(rs.getString("castawayID"));
			result.add(rs.getString("name"));
			result.add(rs.getString("link"));
			result.add(rs.getString("gender"));
			result.add(rs.getString("occupation"));

		} catch (Exception e) {
			System.err.println("ERROR: Get Castaway e.getm: " + e.getMessage());
		} finally {
			DBaccess.disconnect();
			return result;
		}

	}
////
////	public int size(String tblName) {
//		try {
//			DBaccess.connect(host, port, user, password, dbname);
//			ResultSet rs = DBaccess.retrieve("SELECT COUNT(*) FROM `" + tblName
//					+ "`");
//			rs.first();
//			n = Integer.parseInt(rs.getString("COUNT(*)"));
//			System.err.println("TS____ N= " + n);
//		} catch (SQLException ex) {
//			System.err.println("TS_SQL_ERR_INS = " + ex.getMessage());
//			System.err.println("SELECT COUNT(*) FROM \"" + tblName + "\"");
//		} catch (NumberFormatException ex) {
//			System.err.println("TS_SQL_NUMFORMAT_INS = " + ex.getMessage());
//		} finally {
//			DBaccess.disconnect();
//			return n;
//		}
//
//	}

}
