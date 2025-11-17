package dao.jdbc;

import config.DatabaseConnection;
import dao.ConfiguracionRedDao;
import entities.ConfiguracionRed;

import java.sql.*;

public class JdbcConfiguracionRedDao implements ConfiguracionRedDao {

	@Override
	public void insertar(ConfiguracionRed c) throws Exception {
		String sql = """
				INSERT INTO configuracion_red
				(ip, mascara, gateway, dns_primario, dhcp_habilitado, dispositivo_id)
				VALUES (?, ?, ?, ?, ?, ?)
				""";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {

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
	public ConfiguracionRed buscarPorDispositivo(long id) throws Exception {
		String sql = "SELECT * FROM configuracion_red WHERE dispositivo_id=? AND eliminado=FALSE";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) return null;

			ConfiguracionRed c = new ConfiguracionRed();
			c.setId(rs.getLong("id"));
			c.setIp(rs.getString("ip"));
			c.setMascara(rs.getString("mascara"));
			c.setGateway(rs.getString("gateway"));
			c.setDnsPrimario(rs.getString("dns_primario"));
			c.setDhcpHabilitado(rs.getBoolean("dhcp_habilitado"));
			c.setId(id);
			return c;
		}
	}
}
