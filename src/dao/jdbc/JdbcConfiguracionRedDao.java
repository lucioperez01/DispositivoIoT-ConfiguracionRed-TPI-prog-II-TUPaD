package dao.jdbc;

import dao.ConfiguracionRedDao;
import entities.ConfiguracionRed;

import java.sql.*;

public class JdbcConfiguracionRedDao implements ConfiguracionRedDao {

	@Override
	public void insertar(Connection conn, ConfiguracionRed c) throws Exception {

		String sql = """
			INSERT INTO configuracion_red 
			(ip, mascara, gateway, dns_primario, dhcp_habilitado, dispositivo_id, eliminado)
			VALUES (?, ?, ?, ?, ?, ?, FALSE)
			""";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, c.getIp());
			ps.setString(2, c.getMascara());
			ps.setString(3, c.getGateway());
			ps.setString(4, c.getDnsPrimario());
			ps.setBoolean(5, c.isDhcpHabilitado());
			ps.setLong(6, c.getId());

			ps.executeUpdate();
		}
	}

    @Override
    public ConfiguracionRed buscarPorDispositivo(long dispositivoId) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
