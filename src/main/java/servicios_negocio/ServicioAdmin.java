package servicios_negocio;

import java.sql.Connection;
import java.sql.SQLException;

import BD_Conexion.Conexion;
import dao.CategoriasDAOImpl;
import dao.ClientesDAOImpl;
import dao.EmpleadosDAOImpl;
import dao.ImpuestosDAOImpl;
import dao.ProductosDAOImpl;
import dao.ProveedoresDAOImpl;
import dao.TiendasDAOImpl;

public class ServicioAdmin {
	
	

	private static Conexion con = new Conexion();
	
	private EmpleadosDAOImpl empleados= new EmpleadosDAOImpl();
	private ProductosDAOImpl productos= new ProductosDAOImpl();
	private ProveedoresDAOImpl proveedores= new ProveedoresDAOImpl();
	private ImpuestosDAOImpl impuestos= new ImpuestosDAOImpl();
	private TiendasDAOImpl tiendas= new TiendasDAOImpl();
	private ClientesDAOImpl clientes= new ClientesDAOImpl();
	private CategoriasDAOImpl categorias= new CategoriasDAOImpl();
	
	public void ConsultarTodosLosElementos (int opcionAdmin) throws SQLException {
		
		Connection conexion = con.conectar();//Se establece la conexión
		
		
		
		switch(opcionAdmin) {
		
		case 1: // Empleados
			empleados.listarTodos(conexion);
			
			break;
		case 2: // Productos
			productos.listarTodos(conexion);
			
			break;
		case 3: // Proveedores
			
			proveedores.listarTodos(conexion);
			break;
		case 4: // Impuestos
			
			impuestos.listarTodos(conexion);
			break;
		case 5: // Categorias
			
			categorias.listarTodos(conexion);
			break;
		case 6: // Tiendas
			
			tiendas.listarTodos(conexion);
			break;
		case 7: // Clientes
			
			clientes.listarTodos(conexion);
			break;
			
		default:
			
			break;
		
		}
		
		conexion.close();
		
		
	}
	public void ConsultarIdElementos (int opcionAdmin) throws SQLException {
		
		Connection conexion = con.conectar();//Se establece la conexión
		
		
		switch(opcionAdmin) {
		
		case 1: // Empleados
			
			empleados.leerPorId(conexion);
			
			break;
		case 2: // Productos
			
			productos.leerPorId(conexion);
			break;
		case 3: // Proveedores
			
			proveedores.leerPorId(conexion);
			break;
		case 4: // Impuestos
			
			impuestos.leerPorId(conexion);
			break;
		case 5: // Categorias
			categorias.leerPorId(conexion);
			
			break;
		case 6: // Tiendas
			
			tiendas.leerPorId(conexion);
			break;
		case 7: // Clientes
			clientes.leerPorId(conexion);
			
			break;
			
		default:
			
			break;
		
		}
		conexion.close();
	}
	
	public void CrearElementos (int opcionAdmin) throws SQLException {
		
		Connection conexion = con.conectar();//Se establece la conexión
		
	switch(opcionAdmin) {
		
		case 1: // Empleados
			
			empleados.crear(conexion);
			break;
		case 2: // Productos
			productos.crear(conexion);
			
			break;
		case 3: // Proveedores
			
			proveedores.crear(conexion);
			break;
		case 4: // Impuestos
			impuestos.crear(conexion);
			
			break;
		case 5: // Categorias
			
			categorias.crear(conexion);
			break;
		case 6: // Tiendas
			
			tiendas.crear(conexion);
			break;
		case 7: // Clientes
			
			clientes.crear(conexion);
			break;
			
		default:
			
			break;
		
		}
	conexion.close();
		
	}
	
	public void BorrarElementos (int opcionAdmin) throws SQLException {
		
		
		Connection conexion = con.conectar();//Se establece la conexión
		
	switch(opcionAdmin) {
		
		case 1: // Empleados
			
			empleados.eliminar(conexion);
			break;
		case 2: // Productos
			productos.eliminar(conexion);
			
			break;
		case 3: // Proveedores
			proveedores.eliminar(conexion);
			
			break;
		case 4: // Impuestos
			
			impuestos.eliminar(conexion);
			break;
		case 5: // Categorias
			
			categorias.eliminar(conexion);
			break;
		case 6: // Tiendas
			
			tiendas.eliminar(conexion);
			break;
		case 7: // Clientes
			
			clientes.eliminar(conexion);
			break;
			
		default:
			
			break;
		
		}
	conexion.close();
	}
	
	public void ActualizarElementos (int opcionAdmin) throws SQLException {
		
		Connection conexion = con.conectar();//Se establece la conexión
		
	switch(opcionAdmin) {
		
		case 1: // Empleados
			
			empleados.actualizar(conexion);
			break;
		case 2: // Productos
			productos.actualizar(conexion);
			
			break;
		case 3: // Proveedores
			proveedores.actualizar(conexion);
			
			break;
		case 4: // Impuestos
			
			impuestos.actualizar(conexion);
			break;
		case 5: // Categorias
			
			categorias.actualizar(conexion);
			break;
		case 6: // Tiendas
			
			tiendas.actualizar(conexion);
			break;
		case 7: // Clientes
			clientes.actualizar(conexion);
			
			break;
			
		default:
			
			break;
		
		}
		
	conexion.close();
	}
	
	
	

}
