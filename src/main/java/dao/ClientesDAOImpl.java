package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Clientes;

public class ClientesDAOImpl implements ClientesDAO {
	
	private int clie_id;
	private String clie_cedula;
	private String clie_nom;
	private String clie_telefono;
	private String clie_direccion;
	private int clie_id_ciudad;

	public List<Clientes> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM CLIENTES");
		
		System.out.println("******* TODAS LOS CLIENTES ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("clie_id: "+ set.getInt("clie_id"));
			System.out.println("clie_cedula: "+ set.getString("clie_cedula"));
			System.out.println("clie_nom: "+ set.getString("clie_nom"));
			System.out.println("clie_telefono: "+ set.getString("clie_telefono"));
			System.out.println("clie_direccion: "+ set.getString("clie_direccion"));
			System.out.println("clie_id_ciudad: "+ set.getInt("clie_id_ciudad"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Clientes leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `CLIENTES` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		
		System.out.println("clie_id: "+ set.getInt("clie_id"));
		System.out.println("clie_cedula: "+ set.getString("clie_cedula"));
		System.out.println("clie_nom: "+ set.getString("clie_nom"));
		System.out.println("clie_telefono: "+ set.getString("clie_telefono"));
		System.out.println("clie_direccion: "+ set.getString("clie_direccion"));
		System.out.println("clie_id_ciudad: "+ set.getInt("clie_id_ciudad"));
	
		
		set.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear CLIENTE --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el id: ");
		// int clie_id = opciones.nextInt();
		 
		 System.out.print("Ingrese la C.C: ");
		  clie_cedula = opciones.nextLine();
		 
		 System.out.println("Ingrese el nombre completo: ");
		  clie_nom = opciones.nextLine() ;
		 
		 System.out.println("Ingrese el telefono: ");
		  clie_telefono = opciones.nextLine();
		 
		 System.out.print("Ingrese la dirección: ");
		  clie_direccion = opciones.nextLine();
		 
		 System.out.println("Ingrese el id de la ciudad: ");
		  clie_id_ciudad = Integer.parseInt(opciones.nextLine());
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `CLIENTES`(`clie_id`, `clie_cedula`, `clie_nom`, `clie_telefono`, `clie_direccion`, `clie_id_ciudad`) VALUES (?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setString(2, clie_cedula);
			sentencia.setString(3, clie_nom);
			sentencia.setString(4, clie_telefono);
			sentencia.setString(5, clie_direccion);
			sentencia.setInt(6, clie_id_ciudad);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar CLIENTE --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el id: ");
		  clie_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese la C.C: ");
		  clie_cedula = opciones.nextLine();
		 
		 System.out.println("Ingrese el nombre completo: ");
		  clie_nom = opciones.nextLine() ;
		 
		 System.out.println("Ingrese el telefono: ");
		  clie_telefono = opciones.nextLine();
		 
		 System.out.print("Ingrese la dirección: ");
		  clie_direccion = opciones.nextLine();
		 
		 System.out.println("Ingrese el id de la ciudad: ");
		  clie_id_ciudad = Integer.parseInt(opciones.nextLine());
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `CLIENTES` SET `clie_id`=?, `clie_cedula`=?, `clie_nom`=?, `clie_telefono`=?, `clie_direccion`=?, `clie_id_ciudad`=? WHERE clie_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, clie_id);
			sentencia.setString(2, clie_cedula);
			sentencia.setString(3, clie_nom);
			sentencia.setString(4, clie_telefono);
			sentencia.setString(5, clie_direccion);
			sentencia.setInt(6, clie_id_ciudad);
			
			sentencia.setInt(7, clie_id);
			
			
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
		String consulta = "DELETE FROM `CLIENTES` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}


}
