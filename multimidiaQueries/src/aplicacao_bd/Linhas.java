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
        conexao = Conexao.setConnection("127.0.0.1", "postgres", "postgres", "senha");
    }
    
    public void linha (String comando) throws SQLException {
        Statement com = conexao.createStatement();
        com.executeUpdate(comando);
    }
    
    public void addGenero(String novo) throws SQLException, ClassNotFoundException {
        String consulta = "INSERT INTO multimidia.genero VALUES"
                + " ('" + novo  +"')";
        Statement comando = conexao.createStatement();
        comando.executeQuery(consulta);
    }
    
    public void addComentario(String novo) throws SQLException {
        String consulta = "INSERT INTO multimidia.comentario "
                        + "VALUES (" + novo + ")";
        System.out.println(consulta);
        Statement comando = conexao.createStatement();
        comando.executeQuery(consulta);
    }
    
    
}
