package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CRUD <T> {

	
	List<T> listarTodos(Connection conexion) throws SQLException;
	T leerPorId(Connection conexion)throws SQLException;
	void eliminar(Connection conexion)throws SQLException;
	void crear(Connection conexion)throws SQLException;
	void actualizar(Connection conexion)throws SQLException;
	
}
