package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Tiendas;

public class TiendasDAOImpl implements TiendasDAO{

	public List<Tiendas> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM TIENDAS");
		
		System.out.println("******* TODAS LAS TIENDAS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("tien_id: "+ set.getInt("tien_id"));
			System.out.println("tien_nom: "+ set.getString("tien_nom"));
			System.out.println("tien_telefono: "+ set.getString("tien_telefono"));
			System.out.println("tien_direccion: "+ set.getString("tien_direccion"));
			System.out.println("tien_id_ciudad: "+ set.getInt("tien_id_ciudad"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Tiendas leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		 
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `TIENDAS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		
		System.out.println("tien_id: "+ set.getInt("tien_id"));
		System.out.println("tien_nom: "+ set.getString("tien_nom"));
		System.out.println("tien_telefono: "+ set.getString("tien_telefono"));
		System.out.println("tien_direccion: "+ set.getString("tien_direccion"));
		System.out.println("tien_id_ciudad: "+ set.getInt("tien_id_ciudad"));
	
		
		set.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear TIENDA --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese tien_id: ");
		// int tien_id = opciones.nextInt();
		 
		 System.out.print("Ingrese tien_nom: ");
		 String tien_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese tien_telefono: ");
		 String tien_telefono = opciones.nextLine();
		 
		 System.out.print("Ingrese tien_direccion: ");
		 String tien_direccion = opciones.nextLine();
		 
		 System.out.println("Ingrese tien_id_ciudad: ");
		 int tien_id_ciudad = Integer.parseInt(opciones.nextLine());
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `TIENDAS`(`tien_id`, `tien_nom`, `tien_telefono`, `tien_direccion`, `tien_id_ciudad`) VALUES (?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setString(2, tien_nom);
			sentencia.setString(3, tien_telefono);
			sentencia.setString(4, tien_direccion);
			sentencia.setInt(5, tien_id_ciudad);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar TIENDAS --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese tien_id: ");
		 int tien_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese tien_nom: ");
		 String tien_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese tien_telefono: ");
		 String tien_telefono = opciones.nextLine();
		 
		 System.out.print("Ingrese tien_direccion: ");
		 String tien_direccion = opciones.nextLine();
		 
		 System.out.println("Ingrese tien_id_ciudad: ");
		 int tien_id_ciudad = Integer.parseInt(opciones.nextLine());
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `TIENDAS` SET `tien_id`=?, `tien_nom`=?, `tien_telefono`=?, `tien_direccion`=?, `tien_id_ciudad`=? WHERE tien_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, tien_id);
			sentencia.setString(2, tien_nom);
			sentencia.setString(3, tien_telefono);
			sentencia.setString(4, tien_direccion);
			sentencia.setInt(5, tien_id_ciudad);
			
			sentencia.setInt(6, tien_id);
			
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
		
	}

	public void eliminar(Connection conexion) throws SQLException {
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas eliminar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		try {
		String consulta = "DELETE FROM `TIENDAS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
