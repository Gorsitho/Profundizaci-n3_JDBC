package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Categorias;

public class CategoriasDAOImpl implements CategoriasDAO {

	public List<Categorias> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM CATEGORIAS");
		
		System.out.println("******* TODAS LAS CATEGORIAS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("id: "+ set.getInt("catg_id"));
			System.out.println("Categoria: "+ set.getString("catg_nom"));
			System.out.println("Descripción de la categoria: "+ set.getString("catg_descripcion"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Categorias leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `CATEGORIAS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet rs= sentencia.executeQuery();
		
		System.out.println("id: "+rs.getInt("catg_id"));
		System.out.println("Categoria: "+ rs.getString("catg_nom"));
		System.out.println("Descripción de la categoria: "+  rs.getString("catg_descripcion"));
	
		
		rs.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear CATEGORIA --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el id: ");
		 //int catg_id = opciones.nextInt();
		 
		 System.out.print("Ingrese el nombre: ");
		 String catg_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese una descripción: ");
		 String catg_descripcion = opciones.nextLine() ;
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `CATEGORIAS`(`catg_id`, `catg_nom`, `catg_descripcion`) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setString(2, catg_nom);
			sentencia.setString(3, catg_descripcion);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar Categoria --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el id: ");
		 int catg_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el nombre: ");
		 String catg_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese una descripción: ");
		 String catg_descripcion = opciones.nextLine() ;
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `CATEGORIAS` SET `catg_id`=?, `catg_nom`=?, `catg_descripcion`=? WHERE catg_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, catg_id);
			sentencia.setString(2, catg_nom);
			sentencia.setString(3, catg_descripcion);
			
			sentencia.setInt(4, catg_id);
			
			
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
		String consulta = "DELETE FROM `CATEGORIAS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
