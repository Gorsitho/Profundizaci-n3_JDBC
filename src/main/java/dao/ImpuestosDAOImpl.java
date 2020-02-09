package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;


import model.Impuestos;

public class ImpuestosDAOImpl implements ImpuestosDAO{

	
	private int impu_id;
	private String impu_descripcion; 
	private int impu_porcentaje;
	
	public List<Impuestos> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM IMPUESTOS");
		
		System.out.println("******* TODAS LOS IMPUESTOS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("impu_id: "+ set.getInt("impu_id"));
			System.out.println("impu_porcentaje: "+ set.getInt("impu_porcentaje"));
			System.out.println("impu_descripcion: "+ set.getString("impu_descripcion"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Impuestos leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `IMPUESTOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet rs= sentencia.executeQuery();
		
		System.out.println("id: "+rs.getInt("impu_id"));
		System.out.println("Categoria: "+ rs.getInt("impu_porcentaje"));
		System.out.println("Descripción de la categoria: "+  rs.getString("impu_descripcion"));
	
		
		rs.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear IMPUESTO --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el impu_id: ");
		// int impu_id = opciones.nextInt();
		 
		 System.out.println("Ingrese el impu_porcentaje: ");
		  impu_porcentaje = Integer.parseInt(opciones.nextLine());
		 
		 
		 System.out.println("Ingrese una impu_descripcion: ");
		  impu_descripcion = opciones.nextLine();
		  
		  opciones.close();
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `IMPUESTOS`(`impu_id`, `impu_porcentaje`, `impu_descripcion`) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setInt(2, impu_porcentaje);
			sentencia.setString(3, impu_descripcion);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar IMPUESTO --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el impu_id: ");
		  impu_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el impu_porcentaje: ");
		  impu_porcentaje = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese una impu_descripcion: ");
		  impu_descripcion = opciones.nextLine() ;
		  opciones.close();
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `IMPUESTOS` SET `impu_id`=?, `impu_porcentaje`=?, `impu_descripcion`=? WHERE impu_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, impu_id);
			sentencia.setInt(2, impu_porcentaje);
			sentencia.setString(3, impu_descripcion);
			sentencia.setInt(4, impu_id);
			
			
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
		String consulta = "DELETE FROM `IMPUESTOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
