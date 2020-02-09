package servicios_negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BD_Conexion.Conexion;
import dao.ClientesDAOImpl;
import dao.ProductosDAOImpl;
import dao.VentasDAOImpl;
import model.Clientes;

public class Servicios_Cliente {
	
	
	public static Conexion conexion = null;
	private static Conexion con = new Conexion();
	
	//private EmpleadosDAOImpl empleados= new EmpleadosDAOImpl();
	private VentasDAOImpl ventas= new VentasDAOImpl();
	private ProductosDAOImpl productos= new ProductosDAOImpl();
	private ClientesDAOImpl clientes= new ClientesDAOImpl();
	/*private ImpuestosDAOImpl impuestos= new ImpuestosDAOImpl();
	private TiendasDAOImpl tiendas= new TiendasDAOImpl();
	private CategoriasDAOImpl categorias= new CategoriasDAOImpl();*/
	
	public Clientes consultar(String cedula) {
		Clientes cliente = new Clientes();
		try {
		Connection conexion = con.conectar();//Se establece la conexión
		
		String consulta ="SELECT * FROM `CLIENTES` WHERE clie_cedula = ?";
		
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		sentencia.setString(1, cedula); //Se define la variable que le dara valor al campo (?)
		
		ResultSet rs= sentencia.executeQuery();//Se guarda en resultado en un ResultSet
		if(rs.next()){
			//Se cargan los resultados en el objeto 'Venta' 
			cliente.setClie_cedula(rs.getString("clie_cedula"));
			cliente.setClie_nom(rs.getString("clie_nom"));
	    }
		sentencia.close();//Se cierra la sentencia
		} catch (SQLException e) {
			e.printStackTrace();//Imprime el si error si lo hubo
		}
		return cliente;
	}
	
	public void TodosLosProductos () throws SQLException {
		
		Connection conexion = con.conectar();//Se establece la conexión
		System.out.println(" ********************* ");
		System.out.println(" ¡Bienvenido AL PANEL DE PRODUCTOS!");
		System.out.println(" ¡ESTOS SON LOS PRODUCTOS QUE TENEMOS EN NUESTRO SERVICIO!");
		
		productos.listarTodos(conexion);
		
	}
	
	
	public void Facturar(ArrayList<Integer> listadoProductosComprar, ArrayList<Integer> llistadoProductosCantidadComprar) throws SQLException {
		Connection conexion = con.conectar();
		int sumaTotal=0;
		productos.leerPorIdConArrayList(conexion, listadoProductosComprar);
		
		
		for(int i=0; i< listadoProductosComprar.size();i++) {
			
			sumaTotal+=(productos.obtenerPrecioProducto(conexion, (int)listadoProductosComprar.get(i)*llistadoProductosCantidadComprar.get(i)));
			
			productos.actualizarAlComprar(conexion, listadoProductosComprar.get(i), llistadoProductosCantidadComprar.get(i));
			
		}
		
		System.out.println(" ********************* ");
		System.out.println(" ¡Bienvenido AL PANEL DE PRODUCTOS!");
		
		ventas.crearDesdeCliente(conexion,sumaTotal);
		
		
	}
	
	
	public void Registro() throws SQLException {
		Connection conexion = con.conectar();//Se establece la conexión
		
		
		
	System.out.println("¡ Bienvenidos al panel de registro de clientes!");
	System.out.println("Recuerda, si no posees una cuenta ingresa la siguientes información para tener una.");
		
		clientes.crear(conexion);
		
		System.out.println("Tu registro ha sido exitoso, por favor ingresa de nuevo con tu c.c para utilizar nuestros servicios.");
		
	}
	
	
	
	

}
