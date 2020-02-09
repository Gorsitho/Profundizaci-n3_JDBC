package principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import servicios_negocio.ServicioAdmin;
import servicios_negocio.Servicios_Cliente;

public class App {
	
	
	public static void main(String[] args) throws SQLException {
		
	
		
		int continuar = 1;
		
		do {
			
			
			menuApp();  // 
			@SuppressWarnings("resource")
			Scanner opcionContinuar = new Scanner(System.in); 
			System.out.println("¿ Deseas continuar en la aplicación?");
			System.out.println("1. Si");
			System.out.println("2. No");
			continuar=opcionContinuar.nextInt();
			
			
		}while(continuar==1);
		
		System.out.println("********************************** ");
		System.out.println("Gracias por usar nuestros servicios...");
		System.out.println("********************************** ");
		
	}
	
	// ======================================================================
	// Método para imprimir el listado.
	// ======================================================================
	
	private static void listadoAdmin(int TipolistadoImprimir) {
		
		switch (TipolistadoImprimir) {
		
		case 1:
			System.out.println("¿Qué deseas realizar?");
			System.out.println("1. Consultar todos los registros de una tabla");
			System.out.println("2. Consultar registro por id.");
			System.out.println("3. Crear un registro.");
			System.out.println("4. Actualizar registro por id.");
			System.out.println("5. Eliminar registro por id.");
			
			break;
			
		case 2:
			System.out.println("1. Empleados.");
			System.out.println("2. Productos.");
			System.out.println("3. Proveedores.");
			System.out.println("4. Impuestos.");
			System.out.println("5. Categorias.");
			System.out.println("6. Tiendas.");
			System.out.println("7. Clientes.");
			
			
			break;
			
			default: System.out.println("Tipo de listado invalido...");
			break;
		
		
		}
		
		
	}
	
	// ======================================================================
	// Método 
	// ======================================================================
	
	@SuppressWarnings("resource")
	private static void menuApp()  throws SQLException{
		
		ServicioAdmin admin = new ServicioAdmin();
		Servicios_Cliente cliente = new Servicios_Cliente();
		ArrayList<Integer> listaDeCompras = new ArrayList<Integer>();
		ArrayList<Integer> listaDeCantidad = new ArrayList<Integer>();
		
		
		Scanner opcionApp = new Scanner(System.in); 

		
		System.out.println(" ¡Bienvenidos!");
		System.out.println(" ¡Este es el sistema Punto de Venta Nueva Era!");
		
		
		System.out.println(" Ingresa tu C.C");
		
		 String cedula = opcionApp.nextLine();
		 System.out.println("La cedula ingresa es:"+cedula+".");
		 
		//*************************** Usuario ADMIN ***********************
		 
		if(cedula.equals("1075306358")) { 
			System.out.println("------------ ADMIN ------------");

			listadoAdmin(1);
			
			int adminOpcionInicial = opcionApp.nextInt();
			
			switch(adminOpcionInicial) {
			
			case 1:    // Consultar todos los registros de una tabla
				
				listadoAdmin(2);
				int adminConsultaTodosOpcion = opcionApp.nextInt();
				admin.ConsultarTodosLosElementos(adminConsultaTodosOpcion);
				
				
				break;
			case 2:  // Consultar registro por id.
				
				listadoAdmin(2);
				int adminConsultaIdOpcion = opcionApp.nextInt();
				admin.ConsultarIdElementos(adminConsultaIdOpcion);
				
				
				break;
			case 3:  // Crear un registro.
				
				listadoAdmin(2);
				int adminCrearOpcion = opcionApp.nextInt();
				admin.CrearElementos(adminCrearOpcion);
				
				break;
			case 4: // Actualizar registro por id.
				
				listadoAdmin(2);
				int adminActualizarOpcion = opcionApp.nextInt();
				admin.ActualizarElementos(adminActualizarOpcion);
				
				break;
			case 5: // Eliminar registro por id.
				
				listadoAdmin(2);
				int adminEliminarOpcion = opcionApp.nextInt();
				admin.BorrarElementos(adminEliminarOpcion);
				
				break;
			
				default:
					
					System.out.println("Opción invalida !!!! ");
					break;
			
			
			}
		
			//*************************** Usuario CLIENTE ***********************
			// C.C: 1075312791
			// Nombre: Maria Paula
			
		}else if(cliente.consultar(cedula).getClie_cedula() !=null){  
			
			
			System.out.println("------------ CLIENTE ------------");
			
			cliente.TodosLosProductos();
			
			System.out.println("¿ Deseas comprar algún producto ? ");
			System.out.println("1. Si");
			System.out.println("2. No");
			int opcionComprar = opcionApp.nextInt();
			
			do {
				
			

				if(opcionComprar == 1) {
					System.out.println("Ingresa el id del producto: ");
					int  idElegido = opcionApp.nextInt();  // Agrega el id del producto.
					listaDeCompras.add(idElegido);
					System.out.println("Ingresa la cantidad del producto: ");
					int  cantidadElegida = opcionApp.nextInt();  // Agrega el id del producto.
					listaDeCantidad.add(cantidadElegida);
					
					
					System.out.println("¿ Deseas comprar algún otro producto ? ");
					System.out.println("1. Si");
					System.out.println("2. No");
					 opcionComprar = opcionApp.nextInt();
				
				
				}else {
				
					System.out.println("¡Muchas gracias por utilizar nuestros servicios al cliente!");
				break;
				}

			}while(opcionComprar==1);
			
		
				//System.out.println("listado de id_productos para comprar: "+listaDeCompras.toString());
				
				cliente.Facturar(listaDeCompras, listaDeCantidad);  // REVISAR <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
				
			
			
		
			
			
		}else {  // Registro de clientes sin registrar.
			
			System.out.println("------------ NO ESTAS REGISTRADO, POR FAVOR REALIZA EL SIGUIENTE REGISTRO. ------------");
			
			System.out.println("¿ Deseas registrarte ? ");
			System.out.println("1. Si");
			System.out.println("2. No");
			int opcionRegistro = opcionApp.nextInt();
			
			if(opcionRegistro == 1) {
				
				cliente.Registro();
				
			}else {
				
				System.out.println("¡Muchas gracias por utilizar nuestros servicios!");
			}
			
			
		}
		
	
		
		
	}
	
	
	// ======================================================================
	
	
}
