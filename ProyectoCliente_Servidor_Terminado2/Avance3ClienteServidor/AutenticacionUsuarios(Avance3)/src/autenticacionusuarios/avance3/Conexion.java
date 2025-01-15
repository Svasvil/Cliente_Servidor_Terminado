package autenticacionusuarios.avance3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    String bd = "RestaurantesDB";
    String user = "root";
    String password = "andy1234";
    Connection cx;
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Conexion() {
    }

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cx = DriverManager.getConnection(cadena, user, password);
            System.out.println("Si se logro conectar a BD " + bd);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("No se conecto a BD " + bd);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);

        }
        return cx;
    }

    public void desconectar() {
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
