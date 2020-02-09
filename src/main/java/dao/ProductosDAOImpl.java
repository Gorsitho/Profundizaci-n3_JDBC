package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import BD_Conexion.Conexion;
import model.Productos;

public class ProductosDAOImpl implements ProductosDAO{
	private static Conexion con = new Conexion();
	public List<Productos> listarTodos(Connection conexion) throws SQLException {
		Statement statement = conexion.createStatement();
		
		ResultSet set = statement.executeQuery("SELECT * FROM PRODUCTOS");
		
		System.out.println("******* TODAS LOS PRODUCTOS ******* ");
		
		while (set.next()) {
			System.out.println("----------------------");
			System.out.println("prod_id: "+ set.getInt("prod_id"));
			System.out.println("prod_nom: "+ set.getString("prod_nom"));
			System.out.println("prod_stock: "+ set.getInt("prod_stock"));
			System.out.println("prod_precio_bruto: "+ set.getInt("prod_precio_bruto"));
			System.out.println("prod_id_impuesto: "+ set.getInt("prod_id_impuesto"));
			System.out.println("prod_id_categoria: "+ set.getInt("prod_id_categoria"));
			System.out.println("prod_descripcion: "+ set.getString("prod_descripcion"));
		}
		
		System.out.println("************************* ");
		
		set.close();
		statement.close();
		return null;
	}

	public Productos leerPorId(Connection conexion) throws SQLException {
		
		System.out.println("******* Lectura por id ******* ");
		Scanner opciones = new Scanner(System.in); 
		System.out.println("Ingresa el id del elemento que deseas consultar: ");
		 int id = opciones.nextInt();
		 opciones.close();
		
		//Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `PRODUCTOS` WHERE prod_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		
		System.out.println("prod_id: "+ set.getInt("prod_id"));
		System.out.println("prod_nom: "+ set.getString("prod_nom"));
		System.out.println("prod_stock: "+ set.getInt("prod_stock"));
		System.out.println("prod_precio_bruto: "+ set.getInt("prod_precio_bruto"));
		System.out.println("prod_id_impuesto: "+ set.getInt("prod_id_impuesto"));
		System.out.println("prod_id_categoria: "+ set.getInt("prod_id_categoria"));
		System.out.println("prod_descripcion: "+ set.getString("prod_descripcion"));
	
		
		set.close();
	//	statement.close();
		return null;
	}
	
	public Productos leerPorIdConArrayList(Connection conexion, ArrayList<Integer> listadoProductos) throws SQLException {
		
		System.out.println("********** Lista de productos comprados ************** ");
		
		
		for(int i=0;i<listadoProductos.size();i++) {
			// Por productos comprados
			int idProducto = (int)listadoProductos.get(i); 
			//Statement statement = conexion.createStatement();
			String consulta = "SELECT * FROM `PRODUCTOS` WHERE prod_id=?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//System.out.println("Numero: "+idProducto);
			sentencia.setInt(1, idProducto);  //Se define la variable que le dara valor al campo (?)
			ResultSet set= sentencia.executeQuery();       // PROBLEMONNNN
			set.next();
			System.out.println("=========================================");
			System.out.println("Producto comprado n°"+(i+1));
			System.out.println("prod_id: "+ set.getInt("prod_id")); 
			System.out.println("prod_nom: "+ set.getString("prod_nom"));
			//System.out.println("prod_stock: "+ set.getInt("prod_stock"));
			System.out.println("prod_precio_bruto: "+ set.getInt("prod_precio_bruto"));
			//System.out.println("prod_id_impuesto: "+ set.getInt("prod_id_impuesto"));
			System.out.println("prod_id_categoria: "+ set.getInt("prod_id_categoria"));
			System.out.println("prod_descripcion: "+ set.getString("prod_descripcion"));
			System.out.println("=========================================");
			set.close();
			//statement.close();
			
		}
		
	
		
		
		
		return null;
	}
	
	
	public int obtenerPrecioProducto(Connection conexion, int idProducto) throws SQLException {
		
		
		//Statement statement = conexion.createStatement();
		String consulta = "SELECT * FROM `PRODUCTOS` WHERE prod_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, idProducto);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		set.next();
		int precio = set.getInt("prod_precio_bruto");
		set.close();
		return precio;
	}

	@SuppressWarnings("resource")
	public void crear(Connection conexion ) throws SQLException {
		
		System.out.println("---- Crear PRODUCTO --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		//System.out.println("Ingrese el prod_id: ");
		// int prod_id = opciones.nextInt();
		 
		 System.out.print("Ingrese el prod_nom: ");
		 String prod_nom = opciones.nextLine();
		 
		 System.out.println("Ingrese prod_stock: ");
		 int prod_stock = Integer.parseInt(opciones.nextLine());
		 
		System.out.println("Ingrese el prod_precio_bruto: ");
		 int prod_precio_bruto = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el prod_id_impuesto: ");
		 int prod_id_impuesto = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese prod_id_categoria: ");
		 int prod_id_categoria = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese prod_descripcion: ");
		 String prod_descripcion = opciones.nextLine();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "INSERT INTO `PRODUCTOS`(`prod_id`, `prod_nom`, `prod_stock`, `prod_precio_bruto`, `prod_id_impuesto`, `prod_id_categoria`, `prod_descripcion`) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setNull(1, Types.INTEGER);
			sentencia.setString(2, prod_nom);
			sentencia.setInt(3, prod_stock);
			sentencia.setInt(4, prod_precio_bruto);
			sentencia.setInt(5, prod_id_impuesto);
			sentencia.setInt(6, prod_id_categoria);
			sentencia.setString(7, prod_descripcion);
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
	}

	@SuppressWarnings("resource")
	public void actualizar(Connection conexion) throws SQLException {
		
		
		System.out.println("---- ACTUALIZAR PRODUCTOS --------");
		Scanner opciones = new Scanner(System.in); 
		
		
		System.out.println("Ingrese el prod_id: ");
		 int prod_id = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el prod_nom: ");
		 String prod_nom = opciones.next();
		 
		 System.out.println("Ingrese prod_stock: ");
		 int prod_stock = Integer.parseInt(opciones.nextLine());
		 
		System.out.println("Ingrese el prod_precio_bruto: ");
		 int prod_precio_bruto = Integer.parseInt(opciones.nextLine());
		 
		 System.out.print("Ingrese el prod_id_impuesto: ");
		 int prod_id_impuesto = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese prod_id_categoria: ");
		 int prod_id_categoria = Integer.parseInt(opciones.nextLine());
		 
		 System.out.println("Ingrese prod_descripcion: ");
		 String prod_descripcion = opciones.next();
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `PRODUCTOS` SET `prod_id`=?, `prod_nom`=?, `prod_stock`=?, `prod_precio_bruto`=?, `prod_id_impuesto`=?, `prod_id_categoria`=?, `prod_descripcion`=? WHERE prod_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, prod_id);
			sentencia.setString(2, prod_nom);
			sentencia.setInt(3, prod_stock);
			sentencia.setInt(4, prod_precio_bruto);
			sentencia.setInt(5, prod_id_impuesto);
			sentencia.setInt(6, prod_id_categoria);
			sentencia.setString(7, prod_descripcion);
			
			sentencia.setInt(8, prod_id);
			
			
			sentencia.executeUpdate();//Se ejecula la sentencia
			sentencia.close();//Se cierra la sentencia
			System.out.println("Operación exitosa");//Imprime que se realizo el query correctamente
			} catch (SQLException e) {
				e.printStackTrace();//Imprime el si error si lo hubo
			}
		
	}
	
public int obtenerStockProducto(Connection conexion2, int id) throws SQLException {
		Connection conexion = con.conectar();
		String consulta = "SELECT * FROM `PRODUCTOS` WHERE prod_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);   //Se define la variable que le dara valor al campo (?)
		ResultSet set= sentencia.executeQuery();
		set.next();
		int stock = set.getInt("prod_stock");
		set.close();
		return stock;
	}
	
	@SuppressWarnings("resource")
	public void actualizarAlComprar(Connection conexion, int idProductoComprado ,int cantidadComprada) throws SQLException {

		 int prod_id = idProductoComprado;

		 int prod_stock = obtenerStockProducto(conexion,idProductoComprado)-cantidadComprada;
		
		try {
			//Se usa una sentencia preparada
			String consulta = "UPDATE `PRODUCTOS` SET `prod_stock`=? WHERE prod_id = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			//Se definen las variables que se usan para llenar los campos (?) en orden de aparicion de izq a derecha
			sentencia.setInt(1, prod_stock);
			sentencia.setInt(2, prod_id);
			
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
		String consulta = "DELETE FROM `PRODUCTOS` WHERE catg_id = ?";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setInt(1, id);//Se define la variable que le dara valor al campo (?)
		sentencia.executeUpdate();//Se ejecuta el query
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		
	}

}
