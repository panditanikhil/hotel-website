package DBUtil;
import java.sql.*;
public class DB_util {

		private static final String driverName = "org.sqlite.JDBC";
		private static final String url="jdbc:sqlite:C:\\Users\\1454792\\MySQLiteDB";
		
		private static final String userName ="";	
		private static final String pwd="";
		
		public static Connection getConnection(){
			Connection con = null;
			
			try{
				Class.forName(driverName);
				
				con = DriverManager.getConnection(url);
				System.out.println("Connected");
			}
			catch(ClassNotFoundException e){
					
					e.printStackTrace();
			}
			catch(SQLException e){
				
				e.printStackTrace();
			}
			
			return con;		
		}
		
		public static void closeConnection(Connection con){
			
			if(con!=null){
				try{
					con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}			
			}		
		}
		
		public static void closeStatement(PreparedStatement pst){
			
			if(pst!=null){
				try{
					pst.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}			
			}		
		}
	

	
}
