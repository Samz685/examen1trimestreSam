package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

public class UsuarioDAO {

    private Connection connection;

    /* Completar consultas */
    static final String INSERT_QUERY = "INSERT INTO usuario (id, nombre, apellidos, dni) VALUES (NULL,?, ?, ?);";
    static final String LIST_QUERY = "SELECT * FROM usuario";
    static final String GET_BY_DNI = "SELECT * FROM usuario WHERE dni = ?";

    public void connect() {
        try {

            String url = "jdbc:mysql://localhost:3306/examen";
            String user = "root";
            String password = "RePuBlIc0FG4MeS*";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexión exitosa!");
            System.out.println(connection);

            System.out.println("Base de datos conectada");
        } catch (Exception ex) {
            System.out.println("Error al conectar a la base de datos");
            System.out.println("ex");
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");
        }
    }

    public void save(Usuario user) {

        try ( var pst = connection.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());

            if (pst.executeUpdate() > 0) {

                var keys = pst.getGeneratedKeys();
                keys.next();

                user.setId(keys.getLong(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Método completado");

    }

    public ArrayList<Usuario> list() {

        var salida = new ArrayList<Usuario>(0);

        try ( var pst = connection.prepareStatement(LIST_QUERY)) {

            ResultSet resultado = pst.executeQuery();

            while (resultado.next()) {
                var usuario = new Usuario();
                usuario.setId(resultado.getLong("id"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidos(resultado.getString("apellidos"));
                usuario.setDni(resultado.getString("dni"));
                salida.add(usuario);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Método completado");
        return salida;
        

    }

    public Usuario getByDNI(String dni) {

        Usuario salida = new Usuario();

        try ( var pst = connection.prepareStatement(GET_BY_DNI)) {

            pst.setString(1, dni);

            ResultSet resultado = pst.executeQuery();

            if (resultado.next()) {
                var usuario = new Usuario();
                usuario.setId(resultado.getLong("id"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellidos(resultado.getString("apellidos"));
                usuario.setDni(resultado.getString("dni"));

                salida = usuario;
                return salida;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Método getByDNI no completado");
        return null;
    }
}
