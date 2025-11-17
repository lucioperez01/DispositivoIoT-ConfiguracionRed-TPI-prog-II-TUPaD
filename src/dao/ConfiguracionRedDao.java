package dao;

import entities.ConfiguracionRed;

public interface ConfiguracionRedDao {

	void insertar(ConfiguracionRed c) throws Exception;

	ConfiguracionRed buscarPorDispositivo(long dispositivoId) throws Exception;
}
