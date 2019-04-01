package aplicacao_bd;

import java.sql.*;

public class Conexao {
    
    public static Connection setConnection(String host, String database, String user, String senha) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conexao = DriverManager.
                getConnection("jdbc:postgresql://" + host + "/" + database, user, senha);
        return conexao;
    }
    
}
