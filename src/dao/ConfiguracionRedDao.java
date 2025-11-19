package dao;

import entities.ConfiguracionRed;
import java.sql.Connection;

public interface ConfiguracionRedDao {

	void insertar(Connection conn, ConfiguracionRed c) throws Exception;

	ConfiguracionRed buscarPorDispositivo(long dispositivoId) throws Exception;
}
