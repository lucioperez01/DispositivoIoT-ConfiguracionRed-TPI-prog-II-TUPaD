package dao.jdbc;

import config.DatabaseConnection;
import dao.DispositivoIoTDao;
import entities.DispositivoIoT;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDispositivoIoTDao implements DispositivoIoTDao {

	@Override
	public long insertar(Connection conn, DispositivoIoT d) throws Exception {

		String sql = """
			INSERT INTO dispositivo_iot (serial, modelo, ubicacion, firmware_version, eliminado)
			VALUES (?, ?, ?, ?, FALSE)
			""";

		try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, d.getSerial());
			ps.setString(2, d.getModelo());
			ps.setString(3, d.getUbicacion());
			ps.setString(4, d.getFirmwareVersion());

			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) return rs.getLong(1);

			throw new Exception("No se pudo obtener ID generado.");
		}
	}

	@Override
	public DispositivoIoT buscarPorSerial(Connection conn, String serial) throws Exception {
		String sql = "SELECT * FROM dispositivo_iot WHERE serial = ? LIMIT 1";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, serial);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) return null;

			return new DispositivoIoT(
					rs.getLong("id"),
					rs.getBoolean("eliminado"),
					rs.getString("serial"),
					rs.getString("modelo"),
					rs.getString("ubicacion"),
					rs.getString("firmware_version"),
					null
			);
		}
	}

	@Override
	public void actualizar(Connection conn, DispositivoIoT d) throws Exception {
		String sql = """
			UPDATE dispositivo_iot 
			SET serial=?, modelo=?, ubicacion=?, firmware_version=?
			WHERE id=? AND eliminado=FALSE
			""";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, d.getSerial());
			ps.setString(2, d.getModelo());
			ps.setString(3, d.getUbicacion());
			ps.setString(4, d.getFirmwareVersion());
			ps.setLong(5, d.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void eliminarLogico(Connection conn, long id) throws Exception {
		String sql = "UPDATE dispositivo_iot SET eliminado=TRUE WHERE id=?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		}
	}

	@Override
	public DispositivoIoT buscarPorId(Connection conn, long id) throws Exception {

		String sql = "SELECT * FROM dispositivo_iot WHERE id = ? LIMIT 1";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) return null;

			return new DispositivoIoT(
					rs.getLong("id"),
					rs.getBoolean("eliminado"),
					rs.getString("serial"),
					rs.getString("modelo"),
					rs.getString("ubicacion"),
					rs.getString("firmware_version"),
					null
			);
		}
	}

    @Override
    public List<DispositivoIoT> listar(Connection conn) throws Exception {

	String sql = "SELECT * FROM dispositivo_iot WHERE eliminado = FALSE";

	List<DispositivoIoT> lista = new ArrayList<>();

	try (PreparedStatement ps = conn.prepareStatement(sql);
	     ResultSet rs = ps.executeQuery()) {

		while (rs.next()) {
			DispositivoIoT d = new DispositivoIoT(
					rs.getLong("id"),
					rs.getBoolean("eliminado"),
					rs.getString("serial"),
					rs.getString("modelo"),
					rs.getString("ubicacion"),
					rs.getString("firmware_version"),
					null
			);
			lista.add(d);
		}
	}

	return lista;
    }
}
