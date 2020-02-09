package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Scanner;

import model.Pagos;

public class PagosDAOImpl implements PagosDAO  {

	public List<Pagos> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM PAGOS");
		
		System.out.println("******* TODAS LOS PAGOS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("pago_id: "+ set.getInt("pago_id"));
			System.out.println("pago_id_venta: "+ set.getInt("pago_id_venta"));
			System.out.println("pago_cant_tipo_pago: "+ set.getInt("pago_cant_tipo_pago"));
			System.out.println("pago_id_tipo_pago: "+ set.getInt("pago_id_tipo_pago"));
			System.out.println("pago_requiere_auto: "+ set.getString("pago_requiere_auto"));
			System.out.println("auto_id_autorizacion: "+ set.getInt("auto_id_autorizacion"));
			System.out.println("pago_fecha_hora: "+ set.getString("pago_fecha_hora"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Pagos leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `PAGOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		
		System.out.println("pago_id: "+ set.getInt("pago_id"));
		System.out.println("pago_id_venta: "+ set.getInt("pago_id_venta"));
		System.out.println("pago_cant_tipo_pago: "+ set.getInt("pago_cant_tipo_pago"));
		System.out.println("pago_id_tipo_pago: "+ set.getInt("pago_id_tipo_pago"));
		System.out.println("pago_requiere_auto: "+ set.getString("pago_requiere_auto"));
		System.out.println("auto_id_autorizacion: "+ set.getInt("auto_id_autorizacion"));
		System.out.println("pago_fecha_hora: "+ set.getString("pago_fecha_hora"));
	
		
		set.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear un PAGO --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el pago_id: ");
		// int pago_id = opciones.nextInt();
		 
		 System.out.println("Ingrese el pago_id_venta: ");
		 int pago_id_venta = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el pago_cant_tipo_pago: ");
		 int pago_cant_tipo_pago = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el pago_id_tipo_pago: ");
		 int pago_id_tipo_pago = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese pago_requiere_auto: ");
		 String pago_requiere_auto = opciones.nextLine() ;
		 
		 System.out.println("Ingrese el auto_id_autorizacion: ");
		 int auto_id_autorizacion = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el pago_fecha_hora: ");
		 String pago_fecha_hora = opciones.nextLine();
		 
		 
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `PAGOS`(`pago_id`, `pago_id_venta`, `pago_cant_tipo_pago`, `pago_id_tipo_pago`, `pago_requiere_auto`, `auto_id_autorizacion`, `pago_fecha_hora`) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setInt(2, pago_id_venta);
			sentencia.setInt(3, pago_cant_tipo_pago);
			sentencia.setInt(4, pago_id_tipo_pago);
			sentencia.setString(5, pago_requiere_auto);
			sentencia.setInt(6, auto_id_autorizacion);
			sentencia.setString(7, pago_fecha_hora);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar PAGOS --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el pago_id: ");
		 int pago_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el pago_id_venta: ");
		 int pago_id_venta = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el pago_cant_tipo_pago: ");
		 int pago_cant_tipo_pago = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el pago_id_tipo_pago: ");
		 int pago_id_tipo_pago = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese pago_requiere_auto: ");
		 String pago_requiere_auto = opciones.nextLine() ;
		 
		 System.out.println("Ingrese el auto_id_autorizacion: ");
		 int auto_id_autorizacion = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese el pago_fecha_hora: ");
		 String pago_fecha_hora = opciones.nextLine();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `PAGOS` SET `pago_id`=?, `pago_id_venta`=?, `pago_cant_tipo_pago`=?, `pago_id_tipo_pago`=?, `pago_requiere_auto`=?, `auto_id_autorizacion`=?, `pago_fecha_hora`=? WHERE pago_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, pago_id);
			sentencia.setInt(2, pago_id_venta);
			sentencia.setInt(3, pago_cant_tipo_pago);
			sentencia.setInt(4, pago_id_tipo_pago);
			sentencia.setString(5, pago_requiere_auto);
			sentencia.setInt(6, auto_id_autorizacion);
			sentencia.setString(7, pago_fecha_hora);
			
			sentencia.setInt(8, pago_id);
			
			
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
		String consulta = "DELETE FROM `PAGOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}


}
