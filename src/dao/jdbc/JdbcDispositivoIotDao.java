package dao.jdbc;

import config.DatabaseConnection;
import dao.DispositivoIoTDao;
import entities.DispositivoIoT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación JDBC para Dispositivos IoT.
 */
public class JdbcDispositivoIoTDao implements DispositivoIoTDao {

	@Override
	public void insertar(DispositivoIoT d) throws Exception {
		String sql = """
				INSERT INTO dispositivo_iot (serial, modelo, ubicacion, firmware_version)
				VALUES (?, ?, ?, ?)
				""";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, d.getSerial());
			ps.setString(2, d.getModelo());
			ps.setString(3, d.getUbicacion());
			ps.setString(4, d.getFirmwareVersion());
			ps.executeUpdate();
		}
	}

	@Override
	public DispositivoIoT buscarPorId(long id) throws Exception {
		String sql = "SELECT * FROM dispositivo_iot WHERE id=? AND eliminado=FALSE";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) return null;

			DispositivoIoT d = new DispositivoIoT();
			d.setId(rs.getLong("id"));
			d.setEliminado(rs.getBoolean("eliminado"));
			d.setSerial(rs.getString("serial"));
			d.setModelo(rs.getString("modelo"));
			d.setUbicacion(rs.getString("ubicacion"));
			d.setFirmwareVersion(rs.getString("firmware_version"));
			return d;
		}
	}

	@Override
	public List<DispositivoIoT> listar() throws Exception {
		List<DispositivoIoT> lista = new ArrayList<>();
		String sql = "SELECT * FROM dispositivo_iot WHERE eliminado=FALSE";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				DispositivoIoT d = new DispositivoIoT();
				d.setId(rs.getLong("id"));
				d.setSerial(rs.getString("serial"));
				d.setModelo(rs.getString("modelo"));
				d.setUbicacion(rs.getString("ubicacion"));
				d.setFirmwareVersion(rs.getString("firmware_version"));
				lista.add(d);
			}
		}
		return lista;
	}

	@Override
	public void actualizar(DispositivoIoT d) throws Exception {
		String sql = """
				UPDATE dispositivo_iot SET 
				serial=?, modelo=?, ubicacion=?, firmware_version=?
				WHERE id=? AND eliminado=FALSE
				""";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, d.getSerial());
			ps.setString(2, d.getModelo());
			ps.setString(3, d.getUbicacion());
			ps.setString(4, d.getFirmwareVersion());
			ps.setLong(5, d.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void eliminarLogico(long id) throws Exception {
		String sql = "UPDATE dispositivo_iot SET eliminado=TRUE WHERE id=?";

		try (Connection conn = DatabaseConnection.getConnection();
		     PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		}
	}
}
