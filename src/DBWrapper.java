import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* SINGLETON */
public class DBWrapper {

	// Macht es atomisch mit VOLATILE!
	private volatile static DBWrapper instance = null;

	public static DBWrapper getInstance() {

		if (instance == null) {
			synchronized (DBWrapper.class) {
				if (instance == null) {
					instance = new DBWrapper();
				}
			}
		}

		return instance;
	}

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.exit(-1);
		}
	}

	// for TIMESTAMP
	private static SimpleDateFormat tsf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Connection conn = null;
	private Statement stmt = null;

	public DBWrapper() {
		openDB();
	}

	private void openDB() {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
			stmt = conn.createStatement();
			stmt.setQueryTimeout(5); // maxima timeout is 5 sec;

		} catch (SQLException e) {
			e.printStackTrace();

			try {
				conn.close();
			} catch (SQLException e1) {
				System.err.println(e1);
			}
		}
	}

	public void closeDB() {

		try {
			conn.close();
		} catch (SQLException e1) {
			System.err.println(e1);
		}
	}

	public ArrayList<String> querySeminarRoomList() {

		String query = "select (room_name) from room";

		try {
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<String> roomNames = new ArrayList<String>();

			while (rs.next()) {
				roomNames.add(rs.getString("room_name"));
			}

			return roomNames;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void insertReservation(Date theDay, String rentalTime,
			String roomName, String userName, String userPhoneNumber) throws Exception {
		
		if(userName.length() > 10){
			Exception e = new Exception("User name is too long");
			throw e;
		}
		
		if(userPhoneNumber.matches("[0-9]{3}-[0-9]{4}-[0-9]{4}") == false){
			Exception e = new Exception("wrong phone number format");
			throw e;
		}

		String query = "insert into reservations VALUES ("
				+ "'" + rentalTime + "'" + ","
				+ "'" + roomName + "'" + ","
				+ "'" + tsf.format(theDay) + "'" + ","
				+ "'" + userName + "'" + ","
				+ "'" + userPhoneNumber + "'" + ")";
		
		System.out.println(query);
		
		stmt.execute(query);
	}

}
