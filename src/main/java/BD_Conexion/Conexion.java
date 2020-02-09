package BD_Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private Connection conexion;
		public  Connection  conectar() throws SQLException {
			String jdbc = "jdbc:mariadb://remotemysql.com:3306/RYpzJ5WQ5W";

			try {

				 conexion = DriverManager.getConnection(jdbc, "RYpzJ5WQ5W", "X9myItdabl");
				
			

				System.out.println("Estoy conectado!!");

			} catch (SQLException sql) {

				sql.printStackTrace();
			}
			return conexion; 
			
		}
		
		public void cerrarConexion() {
			if (conexion != null) {

				try {

					conexion.close();
				} catch (SQLException e) {

				}
			}
		}
}
