/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao_bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Francisco Rodrigues
 */
public class Linhas {
    private Connection conexao;
    
    public void chamaConexao () throws SQLException, ClassNotFoundException{
        conexao = Conexao.setConnection("127.0.0.1", "postgres", "postgres", "senha");
    }
    
    public void deleteGenero (String pk) throws SQLException, ClassNotFoundException {
        chamaConexao();
        String comando = "DELETE FROM multimidia.genero WHERE nome = '" + pk + "'";
        Statement st = conexao.createStatement();
        st.executeUpdate(comando);
    }
    
    public void deleteConteudo (String pk) throws SQLException, ClassNotFoundException {
        chamaConexao();
        String comando = "DELETE FROM multimidia.conteudo WHERE idconteudo = " + pk;
        Statement st = conexao.createStatement();
        st.executeUpdate(comando);
    }
    
    public void deleteComentario (String pk) throws SQLException, ClassNotFoundException {
        chamaConexao();
        String comando = "delete from multimidia.comentario where num_comentario = " + pk;
        Statement st = conexao.createStatement();
        st.executeUpdate(comando);
    }
    
    public void updateGenero (String pk, String value) throws SQLException, ClassNotFoundException {
        chamaConexao();
        String comando = "update multimidia.genero set nome = '"+ value +"' where nome = " + pk;
        Statement st = conexao.createStatement();
        st.executeUpdate(comando);
    }
    
    public void updateConteudo (String pk, String atributo, String value) throws SQLException, ClassNotFoundException {
        chamaConexao();
        String comando = "update multimidia.conteudo set " + atributo + " = '"+ value +"' where idconteudo = " + pk;
        Statement st = conexao.createStatement();
        st.executeUpdate(comando);
    }
    
    public void updateComentario (String pk, String atributo, String value) throws SQLException, ClassNotFoundException {
        chamaConexao();
        String comando = "update multimidia.comentario set " + atributo + " = '" + value + "' where num_comentario = " + pk;
        System.out.println(comando);
        Statement st = conexao.createStatement();
        st.executeUpdate(comando);
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
