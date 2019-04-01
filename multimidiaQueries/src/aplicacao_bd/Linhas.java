/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao_bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Francisco Rodrigues
 */
public class Linhas {
    private Connection conexao;
    
    public void chamaConexao () throws SQLException, ClassNotFoundException{
        conexao = Conexao.setConnection("127.0.0.1:1234", "postgres", "postgres", "JtkMPma0");
    }
    
    public void linha (String comando) throws SQLException {
        Statement com = conexao.createStatement();
        com.executeUpdate(comando);
    }
}
