package dao;

import entities.DispositivoIoT;
import java.util.List;

public interface DispositivoIoTDao {

	void insertar(DispositivoIoT d) throws Exception;

	DispositivoIoT buscarPorId(long id) throws Exception;

	List<DispositivoIoT> listar() throws Exception;

	void actualizar(DispositivoIoT d) throws Exception;

	void eliminarLogico(long id) throws Exception;
}
