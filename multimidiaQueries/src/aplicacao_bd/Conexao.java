package aplicacao_bd;

import java.sql.*;

public class Conexao {
    
    public static Connection setConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conexao = DriverManager.
                getConnection("jdbc:postgresql://" + "127.0.0.1" + "/" + "postgres", "postgres", "postgres");
        return conexao;
    }
    
}
