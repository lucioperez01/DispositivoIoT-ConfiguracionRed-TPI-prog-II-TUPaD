package dao;

import entities.DispositivoIoT;
import java.sql.Connection;
import java.util.List;

public interface DispositivoIoTDao {

	long insertar(Connection conn, DispositivoIoT d) throws Exception;

	DispositivoIoT buscarPorId(Connection conn, long id) throws Exception;

	DispositivoIoT buscarPorSerial(Connection conn, String serial) throws Exception;

	List<DispositivoIoT> listar(Connection conn) throws Exception;

	void actualizar(Connection conn, DispositivoIoT d) throws Exception;

	void eliminarLogico(Connection conn, long id) throws Exception;
}
