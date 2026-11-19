
package es.izquierdofp.retrovault.datos;

import es.izquierdofp.retrovault.modelo.Juego;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoDAO {

    // OBTENER TODOS LOS JUEGOS
    public List<Juego> obtenerTodos() {

        List<Juego> lista = new ArrayList<>();

        String sql = "SELECT * FROM juegos ORDER BY titulo";

        try (Connection conexion = DatabaseManager.getConnection();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            while (resultado.next()) {
                lista.add(mapear(resultado));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // FILTRAR POR CONSOLA
    public List<Juego> obtenerPorConsola(String consola) {

        List<Juego> lista = new ArrayList<>();

        String sql = "SELECT * FROM juegos WHERE consola = ? ORDER BY titulo";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, consola);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {
                    lista.add(mapear(resultado));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Juego> obtenerPorEstado(String estado) {

        List<Juego> lista = new ArrayList<>();

        String sql = "SELECT * FROM juegos WHERE estado = ? ORDER BY titulo";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, estado);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {
                    lista.add(mapear(resultado));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void insertar(Juego juego) {

        String sql = "INSERT INTO juegos(titulo,consola,genero,anio,puntuacion,estado) "
                   + "VALUES(?,?,?,?,?,?)";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, juego.getTitulo());
            sentencia.setString(2, juego.getConsola());
            sentencia.setString(3, juego.getGenero());
            sentencia.setInt(4, juego.getAnio());
            sentencia.setInt(5, juego.getPuntuacion());
            sentencia.setString(6, juego.getEstado());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    juego.setId(resultado.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Juego juego) {

        String sql = "UPDATE juegos SET titulo=?,consola=?,genero=?,"
                   + "anio=?,puntuacion=?,estado=? WHERE id=?";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, juego.getTitulo());
            sentencia.setString(2, juego.getConsola());
            sentencia.setString(3, juego.getGenero());
            sentencia.setInt(4, juego.getAnio());
            sentencia.setInt(5, juego.getPuntuacion());
            sentencia.setString(6, juego.getEstado());
            sentencia.setInt(7, juego.getId());

            sentencia.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {

        String sql = "DELETE FROM juegos WHERE id = ?";

        try (Connection conexion = DatabaseManager.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);
            sentencia.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int contarTotal() {

        return contarSQL("SELECT COUNT(*) FROM juegos");
    }

    public int contarCompletados() {

        return contarSQL(
            "SELECT COUNT(*) FROM juegos WHERE estado='Completado'"
        );
    }

    public double puntuacionMedia() {

        String sql = "SELECT AVG(puntuacion) FROM juegos WHERE puntuacion > 0";

        try (Connection conexion = DatabaseManager.getConnection();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            if (resultado.next()) {
                return Math.round(resultado.getDouble(1) * 10.0) / 10.0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    private int contarSQL(String sql) {

        try (Connection conexion = DatabaseManager.getConnection();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            if (resultado.next()) {
                return resultado.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private Juego mapear(ResultSet resultado) throws SQLException {

        Juego juego = new Juego();

        juego.setId(resultado.getInt("id"));
        juego.setTitulo(resultado.getString("titulo"));
        juego.setConsola(resultado.getString("consola"));
        juego.setGenero(resultado.getString("genero"));
        juego.setAnio(resultado.getInt("anio"));
        juego.setPuntuacion(resultado.getInt("puntuacion"));
        juego.setEstado(resultado.getString("estado"));

        return juego;
    }
}
