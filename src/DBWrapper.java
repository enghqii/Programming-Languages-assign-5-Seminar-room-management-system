import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/* SINGLETON */
public class DBWrapper {
	
	private volatile static DBWrapper instance = null;
	
	public static DBWrapper getInstance(){
		
		if(instance == null){
			synchronized(DBWrapper.class){
				if(instance == null){
					instance = new DBWrapper();
				}
			}
		}
		
		return instance;
	}
	
	static{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.exit(-1);
		}
	}
	
	private Connection 	conn = null;
	private Statement 	stmt = null;

	public DBWrapper() {
		openDB();
	}
	
	private void openDB(){

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
	
	public void closeDB(){
		
		try {
			conn.close();
		} catch (SQLException e1) {
			System.err.println(e1);
		}
	}
	
	public ArrayList<String> getSeminarRoomList(){
		
		String query = "select (room_name) from room";
		
		try {
			ResultSet rs = stmt.executeQuery(query);
			ArrayList<String> roomNames = new ArrayList<String>();
			
			while(rs.next()){
				roomNames.add(rs.getString("room_name"));
			}

			return roomNames;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
