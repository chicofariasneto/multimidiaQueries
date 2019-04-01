package aplicacao_bd;

import java.sql.*;
import java.util.Properties;

public class Conexao {
    
    public static void testeJDBC() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Carregou");
        }
        catch(ClassNotFoundException ex) {ex.printStackTrace();}
    }
    
    public static Connection setConnection(String host, String database, String user, String senha) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conexao = DriverManager.
                getConnection("jdbc:postgresql://" + host + "/" + database, user, senha);
        return conexao;
    }
    
}
