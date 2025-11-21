package service;

import config.DatabaseConnection;
import dao.jdbc.JdbcDispositivoIoTDao;
import dao.jdbc.JdbcConfiguracionRedDao;
import entities.ConfiguracionRed;
import entities.DispositivoIoT;

import java.sql.Connection;

public class DispositivoIoTService {

	private final JdbcDispositivoIoTDao dispositivoDao = new JdbcDispositivoIoTDao();
	private final JdbcConfiguracionRedDao configDao = new JdbcConfiguracionRedDao();

	public long crearDispositivoConConfig(DispositivoIoT d, ConfiguracionRed c) throws Exception {

		try (Connection conn = DatabaseConnection.getConnection()) {

			conn.setAutoCommit(false);

			// VALIDACION DE SERIAL DUPLICADO
			DispositivoIoT existente = dispositivoDao.buscarPorSerial(conn, d.getSerial());
			if (existente != null)
				throw new Exception("El serial ya existe: " + d.getSerial());

			// INSERTAR DISPOSITIVO
			long id = dispositivoDao.insertar(conn, d);

			// INSERTAR CONFIGURACION
			c.setId(id);
			configDao.insertar(conn, c);

			conn.commit();
			return id;

		} catch (Exception ex) {
			throw new Exception("Error transaccional al crear dispositivo", ex);
		}
	}
}
