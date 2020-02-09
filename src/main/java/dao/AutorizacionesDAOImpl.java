package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Autorizaciones;

public class AutorizacionesDAOImpl implements AutorizacionesDAO {

	public List<Autorizaciones> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM AUTORIZACIONES");
		
		System.out.println("******* TODAS LAS CATEGORIAS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("auto_id: "+ set.getInt("auto_id"));
			System.out.println("auto_id_autorizador: "+ set.getInt("auto_id_autorizador"));
			System.out.println("auto_fecha_autorizacion: "+ set.getString("auto_fecha_autorizacion"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Autorizaciones leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `AUTORIZACIONES` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet rs= sentencia.executeQuery();
		
		System.out.println("auto_id: "+ rs.getInt("auto_id"));
		System.out.println("auto_id_autorizador: "+ rs.getInt("auto_id_autorizador"));
		System.out.println("auto_fecha_autorizacion: "+ rs.getString("auto_fecha_autorizacion"));
	
		
		rs.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear Autorización --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el auto_id: ");
		// int auto_id = opciones.nextInt();
		 
		 System.out.print("Ingrese el auto_id_autorizador: ");
		 int auto_id_autorizador = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese una auto_fecha_autorizacion: ");
		 String auto_fecha_autorizacion = opciones.nextLine() ;
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `AUTORIZACIONES`(`auto_id`, `auto_id_autorizador`, `auto_fecha_autorizacion`) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setInt(2, auto_id_autorizador);
			sentencia.setString(3, auto_fecha_autorizacion);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar Autorización --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el auto_id: ");
		 int auto_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el auto_id_autorizador: ");
		 int auto_id_autorizador = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese una auto_fecha_autorizacion: ");
		 String auto_fecha_autorizacion = opciones.nextLine() ;
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `AUTORIZACIONES` SET `auto_id`=?, `auto_id_autorizador`=?, `auto_fecha_autorizacion`=? WHERE auto_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, auto_id);
			sentencia.setInt(2, auto_id_autorizador);
			sentencia.setString(3, auto_fecha_autorizacion);
			
			sentencia.setInt(4, auto_id);
			
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
		
	}

	public void eliminar(Connection conexion) throws SQLException {
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas eliminar : ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		try {
		String consulta = "DELETE FROM `AUTORIZACIONES` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
