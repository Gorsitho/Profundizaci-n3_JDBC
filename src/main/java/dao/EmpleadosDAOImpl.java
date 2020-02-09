package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Empleados;

public class EmpleadosDAOImpl implements EmpleadosDAO {

	public List<Empleados> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet rs = statement.executeQuery("SELECT * FROM EMPLEADOS");
		
		System.out.println("******* TODAS LOS EMPLEADOS ******* ");
		
		while (rs.next()) {
			System.out.println("empl_id: "+rs.getInt("empl_id"));
			System.out.println("empl_nom: "+ rs.getString("empl_nom"));
			System.out.println("empl_cedula: "+  rs.getString("empl_cedula"));
			System.out.println("tipo de empleado: "+  rs.getString("empl_tipo_empleado"));
			System.out.println("empl_id_tienda: "+  rs.getString("empl_id_tienda"));
			System.out.println("empl_telefono: "+  rs.getString("empl_telefono"));
			System.out.println("empl_direccion: "+  rs.getString("empl_direccion"));
			System.out.println("empl_id_ciudad: "+  rs.getString("empl_id_ciudad"));
			System.out.println("empl_fecha_nacimiento: "+  rs.getString("empl_fecha_nacimiento"));
		}
		
		System.out.println("************************* ");
		
		rs.close();
		statement.close();
		return null;
	}

	public Empleados leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `EMPLEADOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet rs= sentencia.executeQuery();
		
		System.out.println("empl_id: "+rs.getInt("empl_id"));
		System.out.println("empl_nom: "+ rs.getString("empl_nom"));
		System.out.println("empl_cedula: "+  rs.getString("empl_cedula"));
		System.out.println("tipo de empleado: "+  rs.getString("empl_tipo_empleado"));
		System.out.println("empl_id_tienda: "+  rs.getString("empl_id_tienda"));
		System.out.println("empl_telefono: "+  rs.getString("empl_telefono"));
		System.out.println("empl_direccion: "+  rs.getString("empl_direccion"));
		System.out.println("empl_id_ciudad: "+  rs.getString("empl_id_ciudad"));
		System.out.println("empl_fecha_nacimiento: "+  rs.getString("empl_fecha_nacimiento"));
		
		rs.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear Empleado --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el id: ");
		// int empl_id = opciones.nextInt();
		 
		 System.out.print("Ingrese el nombre: ");
		 String empl_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese la C.C: ");
		 String empl_cedula = opciones.nextLine() ;

		 System.out.println("Ingrese el tipo empleado: ");
		 int empl_tipo_empleado = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el id de la tienda: ");
		 int empl_id_tienda = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el telefono: ");
		 String empl_telefono = opciones.nextLine() ;
		 
		 System.out.println("Ingrese la direccion: ");
		 String empl_direccion = opciones.nextLine();
		 
		 System.out.print("Ingrese la ciudad: ");
		 int empl_id_ciudad = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese la fecha de nacimiento: ");
		 String empl_fecha_nacimiento = opciones.nextLine();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `EMPLEADOS`(`empl_id`, `empl_nom`, `empl_cedula`, `empl_tipo_empleado`, `empl_id_tienda`, `empl_telefono`, `empl_direccion`, `empl_id_ciudad`, `empl_fecha_nacimiento`) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setString(2, empl_nom);
			sentencia.setString(3, empl_cedula);
			sentencia.setInt(4, empl_tipo_empleado);
			sentencia.setInt(5, empl_id_tienda);
			sentencia.setString(6, empl_telefono);
			sentencia.setString(7, empl_direccion);
			sentencia.setInt(8, empl_id_ciudad);
			sentencia.setString(9, empl_fecha_nacimiento);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar EMPLEADO--------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el id: ");
		 int empl_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el nombre: ");
		 String empl_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese la C.C: ");
		 String empl_cedula = opciones.nextLine() ;

		 System.out.println("Ingrese el tipo empleado: ");
		 int empl_tipo_empleado = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el id de la tienda: ");
		 int empl_id_tienda = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el telefono: ");
		 String empl_telefono = opciones.nextLine() ;
		 
		 System.out.println("Ingrese la direccion: ");
		 String empl_direccion = opciones.nextLine();
		 
		 System.out.print("Ingrese la ciudad: ");
		 int empl_id_ciudad = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese la fecha de nacimiento: ");
		 String empl_fecha_nacimiento = opciones.nextLine();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `EMPLEADOS` SET `empl_id`=?, `empl_nom`=?, `empl_cedula`=?, `empl_tipo_empleado`=?, `empl_id_tienda`=?, `empl_telefono`=?, `empl_direccion`=?, `empl_id_ciudad`=?, `empl_fecha_nacimiento`=? WHERE empl_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, empl_id);
			sentencia.setString(2, empl_nom);
			sentencia.setString(3, empl_cedula);
			sentencia.setInt(4, empl_tipo_empleado);
			sentencia.setInt(5, empl_id_tienda);
			sentencia.setString(6, empl_telefono);
			sentencia.setString(7, empl_direccion);
			sentencia.setInt(8, empl_id_ciudad);
			sentencia.setString(9, empl_fecha_nacimiento);
			sentencia.setInt(10, empl_id);
			
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
			
		String consulta = "DELETE FROM `EMPLEADOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
