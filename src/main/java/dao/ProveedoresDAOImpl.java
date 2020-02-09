package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Proveedores;

public class ProveedoresDAOImpl implements ProveedoresDAO{

	public List<Proveedores> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM PROVEEDORES");
		
		System.out.println("******* TODAS LOS PROVEEDORES ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("prov_id: "+ set.getInt("prov_id"));
			System.out.println("prov_nom: "+ set.getString("prov_nom"));
			System.out.println("prov_nit: "+ set.getString("prov_nit"));
			System.out.println("prov_telefono: "+ set.getString("prov_telefono"));
			System.out.println("prov_direccion: "+ set.getString("prov_direccion"));
			System.out.println("prov_id_ciudad: "+ set.getInt("prov_id_ciudad"));
			System.out.println("prov_num_cta_banco: "+ set.getString("prov_num_cta_banco"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Proveedores leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `PROVEEDORES` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		
		System.out.println("prov_id: "+ set.getInt("prov_id"));
		System.out.println("prov_nom: "+ set.getString("prov_nom"));
		System.out.println("prov_nit: "+ set.getString("prov_nit"));
		System.out.println("prov_telefono: "+ set.getString("prov_telefono"));
		System.out.println("prov_direccion: "+ set.getString("prov_direccion"));
		System.out.println("prov_id_ciudad: "+ set.getInt("prov_id_ciudad"));
		System.out.println("prov_num_cta_banco: "+ set.getString("prov_num_cta_banco"));
	
		
		set.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear un PROVEEDOR --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el prov_id: ");
		// int prov_id = opciones.nextInt();
		 
		 System.out.print("Ingrese el prov_nom: ");
		 String prov_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese prov_nit: ");
		 String prov_nit = opciones.nextLine();
		 
		 System.out.println("Ingrese el prov_telefono: ");
		 String prov_telefono = opciones.nextLine();
		 
		 System.out.print("Ingrese prov_direccion: ");
		 String prov_direccion = opciones.nextLine();
		 
		 System.out.println("Ingrese prov_id_ciudad: ");
		 int prov_id_ciudad = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese prov_num_cta_banco: ");
		 String prov_num_cta_banco = opciones.nextLine();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `PROVEEDORES`(`prov_id`, `prov_nom`, `prov_nit`, `prov_telefono`, `prov_direccion`, `prov_id_ciudad`, `prov_num_cta_banco`) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setString(2, prov_nom);
			sentencia.setString(3, prov_nit);
			sentencia.setString(4, prov_telefono);
			sentencia.setString(5, prov_direccion);
			sentencia.setInt(6, prov_id_ciudad);
			sentencia.setString(7, prov_num_cta_banco);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar PROVEEDOR --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el prov_id: ");
		 int prov_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el prov_nom: ");
		 String prov_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese prov_nit: ");
		 String prov_nit = opciones.nextLine();
		 
		 System.out.println("Ingrese el prov_telefono: ");
		 String prov_telefono = opciones.nextLine();
		 
		 System.out.print("Ingrese prov_direccion: ");
		 String prov_direccion = opciones.nextLine();
		 
		 System.out.println("Ingrese prov_id_ciudad: ");
		 int prov_id_ciudad = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese prov_num_cta_banco: ");
		 String prov_num_cta_banco = opciones.nextLine();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `PROVEEDORES` SET `prov_id`=?, `prov_nom`=?, `prov_nit`=?, `prov_telefono`=?, `prov_direccion`=?, `prov_id_ciudad`=?, `prov_num_cta_banco`=? WHERE prov_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, prov_id);
			sentencia.setString(2, prov_nom);
			sentencia.setString(3, prov_nit);
			sentencia.setString(4, prov_telefono);
			sentencia.setString(5, prov_direccion);
			sentencia.setInt(6, prov_id_ciudad);
			sentencia.setString(7, prov_num_cta_banco);
			
			sentencia.setInt(8, prov_id);
			
			
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
		String consulta = "DELETE FROM `PROVEEDORES` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
