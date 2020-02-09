package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.Ventas;

public class VentasDAOImpl implements VentasDAO{

	public List<Ventas> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM VENTAS");
		
		System.out.println("******* TODAS LAS VENTAS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("vent_id: "+ set.getInt("vent_id"));
			System.out.println("vent_id_detalle_venta: "+ set.getInt("vent_id_detalle_venta"));
			System.out.println("vent_fecha_hora: "+ set.getString("vent_fecha_hora"));
			System.out.println("vent_id_empleado: "+ set.getInt("vent_id_empleado"));
			System.out.println("vent_id_tienda: "+ set.getInt("vent_id_tienda"));
			System.out.println("vent_id_cliente: "+ set.getInt("vent_id_cliente"));
			System.out.println("vent_precio_bruto: "+ set.getInt("vent_precio_bruto"));
			System.out.println("vent_dscto_tipo: "+ set.getString("vent_dscto_tipo"));
			System.out.println("vent_dscto_valor: "+ set.getInt("vent_dscto_valor"));
			System.out.println("vent_precio_final: "+ set.getInt("vent_precio_final"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Ventas leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		 
		Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `VENTAS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		
		System.out.println("vent_id: "+ set.getInt("vent_id"));
		System.out.println("vent_id_detalle_venta: "+ set.getInt("vent_id_detalle_venta"));
		System.out.println("vent_fecha_hora: "+ set.getString("vent_fecha_hora"));
		System.out.println("vent_id_empleado: "+ set.getInt("vent_id_empleado"));
		System.out.println("vent_id_tienda: "+ set.getInt("vent_id_tienda"));
		System.out.println("vent_id_cliente: "+ set.getInt("vent_id_cliente"));
		System.out.println("vent_precio_bruto: "+ set.getInt("vent_precio_bruto"));
		System.out.println("vent_dscto_tipo: "+ set.getString("vent_dscto_tipo"));
		System.out.println("vent_dscto_valor: "+ set.getInt("vent_dscto_valor"));
		System.out.println("vent_precio_final: "+ set.getInt("vent_precio_final"));
	
		
		set.close();
		statement.close();
		return null;
	}

	@SuppressWarnings("resource")
	public void crearDesdeCliente(Connection conexion , int precioTotalBruto) throws SQLException {
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		System.out.println("---- Crear VENTA --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese vent_id: ");
		// int vent_id = opciones.nextInt();
		 
		 //System.out.print("Ingrese vent_id_detalle_venta: ");
		// int vent_id_detalle_venta = Integer.parseInt(opciones.nextLine());
		 
		 
		// String vent_fecha_hora = "HORA  "+c1.get(Calendar.HOUR_OF_DAY)+":"+c1.get(Calendar.MINUTE)+" | FECHA "+c1.get(Calendar.DAY_OF_MONTH)+"/"+(c1.get(Calendar.MONTH)+1)+"/"+c1.get(Calendar.YEAR);
		Timestamp vent_fecha_hora = timestamp;
		
		System.out.println(vent_fecha_hora);
		 
		System.out.println("Ingrese vent_id_empleado: ");
		 int vent_id_empleado = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese vent_id_tienda: ");
		 int vent_id_tienda = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese vent_id_cliente: ");
		 int vent_id_cliente = Integer.parseInt(opciones.nextLine());
		 
		 int vent_precio_bruto = precioTotalBruto;
		 System.out.println("El precio total en bruto es de : $ "+vent_precio_bruto);
		 
		 //System.out.print("Ingrese vent_dscto_tipo: ");
		 String vent_dscto_tipo = "1";  // No se que agregar.
		 
		 
		 int vent_dscto_valor = (int) (Math.random()*20); 
		 System.out.println("Descuento de: "+ vent_dscto_valor+"%");
		 
		 
		 int vent_precio_final = vent_precio_bruto-((vent_precio_bruto*vent_dscto_valor)/100);
		 System.out.println("El precio final comprado es de : $" +vent_precio_final);
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `VENTAS`(`vent_id`, `vent_fecha_hora`, `vent_id_empleado`, `vent_id_tienda`, `vent_id_cliente`, `vent_precio_bruto`, `vent_dscto_tipo`, `vent_dscto_valor`, `vent_precio_final`) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			//sentencia.setNull(2, Types.INTEGER);
			sentencia.setTimestamp(2, vent_fecha_hora);
			sentencia.setInt(3, vent_id_empleado);
			sentencia.setInt(4, vent_id_tienda);
			sentencia.setInt(5, vent_id_cliente);
			sentencia.setInt(6, vent_precio_bruto);
			sentencia.setString(7, vent_dscto_tipo);
			sentencia.setInt(8, vent_dscto_valor);
			sentencia.setInt(9, vent_precio_final);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- Actualizar VENTA --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese vent_id: ");
		 int vent_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese vent_id_detalle_venta: ");
		 int vent_id_detalle_venta = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese vent_fecha_hora: ");
		 String vent_fecha_hora = opciones.nextLine();
		 
		System.out.println("Ingrese vent_id_empleado: ");
		 int vent_id_empleado = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese vent_id_tienda: ");
		 int vent_id_tienda = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese vent_id_cliente: ");
		 int vent_id_cliente = Integer.parseInt(opciones.nextLine());
		 
		System.out.println("Ingrese vent_precio_bruto: ");
		 int vent_precio_bruto = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese vent_dscto_tipo: ");
		 String vent_dscto_tipo = opciones.nextLine();
		 
		 System.out.println("Ingrese vent_dscto_valor: ");
		 int vent_dscto_valor = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese vent_precio_final: ");
		 int vent_precio_final = Integer.parseInt(opciones.nextLine());
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `VENTAS` SET `vent_id`=?, `vent_id_detalle_venta`=?, `vent_fecha_hora`=?, `vent_id_empleado`=?, `vent_id_tienda`=?, `vent_id_cliente`=?, `vent_precio_bruto`=?, `vent_dscto_tipo`=?, `vent_dscto_valor`=?, `vent_precio_final`=? WHERE vent_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, vent_id);
			sentencia.setInt(2, vent_id_detalle_venta);
			sentencia.setString(3, vent_fecha_hora);
			sentencia.setInt(4, vent_id_empleado);
			sentencia.setInt(5, vent_id_tienda);
			sentencia.setInt(6, vent_id_cliente);
			sentencia.setInt(7, vent_precio_bruto);
			sentencia.setString(8, vent_dscto_tipo);
			sentencia.setInt(9, vent_dscto_valor);
			sentencia.setInt(10, vent_precio_final);
			
			sentencia.setInt(11, vent_id);
			
			
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
		String consulta = "DELETE FROM `VENTAS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}
	
	public static String getFechaActual() {
	    Date ahora = new Date(0);
	    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	    return formateador.format(ahora);
	}
	
	public void crear(Connection conexion) throws SQLException {
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		System.out.println("---- Crear VENTA --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese vent_id: ");
		// int vent_id = opciones.nextInt();
		 
		 //System.out.print("Ingrese vent_id_detalle_venta: ");
		// int vent_id_detalle_venta = Integer.parseInt(opciones.nextLine());
		 
		 
		// String vent_fecha_hora = "HORA  "+c1.get(Calendar.HOUR_OF_DAY)+":"+c1.get(Calendar.MINUTE)+" | FECHA "+c1.get(Calendar.DAY_OF_MONTH)+"/"+(c1.get(Calendar.MONTH)+1)+"/"+c1.get(Calendar.YEAR);
		Timestamp vent_fecha_hora = timestamp;
		
		System.out.println(vent_fecha_hora);
		 
		System.out.println("Ingrese vent_id_empleado: ");
		 int vent_id_empleado = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese vent_id_tienda: ");
		 int vent_id_tienda = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese vent_id_cliente: ");
		 int vent_id_cliente = Integer.parseInt(opciones.nextLine());
		 
		System.out.println("Ingrese vent_precio_bruto: ");
		 int vent_precio_bruto = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese vent_dscto_tipo: ");
		 String vent_dscto_tipo = opciones.nextLine();
		 
		 System.out.println("Ingrese vent_dscto_valor:");
		 int vent_dscto_valor = Integer.parseInt(opciones.nextLine());
		 
		 
		 System.out.println("Ingrese vent_precio_final: ");
		 int vent_precio_final = Integer.parseInt(opciones.nextLine());
		 
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `VENTAS`(`vent_id`, `vent_fecha_hora`, `vent_id_empleado`, `vent_id_tienda`, `vent_id_cliente`, `vent_precio_bruto`, `vent_dscto_tipo`, `vent_dscto_valor`, `vent_precio_final`) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			//sentencia.setNull(2, Types.INTEGER);
			sentencia.setTimestamp(2, vent_fecha_hora);
			sentencia.setInt(3, vent_id_empleado);
			sentencia.setInt(4, vent_id_tienda);
			sentencia.setInt(5, vent_id_cliente);
			sentencia.setInt(6, vent_precio_bruto);
			sentencia.setString(7, vent_dscto_tipo);
			sentencia.setInt(8, vent_dscto_valor);
			sentencia.setInt(9, vent_precio_final);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			opciones.close();
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

}
