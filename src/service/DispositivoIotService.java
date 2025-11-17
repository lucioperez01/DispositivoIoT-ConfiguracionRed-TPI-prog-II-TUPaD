package service;

import dao.jdbc.JdbcDispositivoIoTDao;
import dao.jdbc.JdbcConfiguracionRedDao;
import entities.ConfiguracionRed;
import entities.DispositivoIoT;

/**
 * Servicio principal para la gestión de Dispositivos IoT
 * y su configuración de red asociada.
 */
public class DispositivoIoTService {

	private final JdbcDispositivoIoTDao dispositivoDao = new JdbcDispositivoIoTDao();
	private final JdbcConfiguracionRedDao configDao = new JdbcConfiguracionRedDao();

	public long crearDispositivoConConfig(DispositivoIoT d, ConfiguracionRed c) throws Exception {

		dispositivoDao.insertar(d);

		long id = dispositivoDao.listar().getLast().getId();

		c.setId(id);
		configDao.insertar(c);

		return id;
	}
}
