package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Connection conexion;

    static {

        Properties conf = new Properties();

        try {
            conf.load(new FileReader("configuracion.properties"));

            try {
                conexion = DriverManager.getConnection(conf.getProperty("url"), conf.getProperty("user"), conf.getProperty("password"));
                Logger.getLogger(UsuarioDAO.class.getName()).info("Conexión establecida con exito");
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Connection getConexion() {
        return conexion;
    }

}
