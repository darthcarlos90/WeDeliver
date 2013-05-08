package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class connectionDB {
	private Connection connectionDB = null;
	Statement statement;

	public connectionDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String host = "localhost";
			String dbName = "ferreteria";
			int mySQLport = 3306;
			String mySqlURL = "jdbc:mysql://" + host + ":"
					+ mySQLport + "/" + dbName;

			String username = "root";
			String password = "paty";
			connectionDB = DriverManager.getConnection(mySqlURL, username,
					password);

			statement=connectionDB.createStatement();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public connectionDB(String driver, String url, String user, String password) {
		try {
			Class.forName(driver);
			connectionDB = DriverManager.getConnection(url, user, password);
			statement=connectionDB.createStatement();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnectionDB() {
		return connectionDB;
	}

	public void setConnectionDB(Connection connectionDB) {
		this.connectionDB = connectionDB;
	}

	public void closeConnectionDB() {
		if (connectionDB != null) {
			try {
				connectionDB.close();
			}
			// process SQLException on close operation
			catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}
}
