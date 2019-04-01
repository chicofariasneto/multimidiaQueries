/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao_bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Francisco Rodrigues
 */
public class consultasFunction {
    private Connection conexao;
    
    public void chamaConexao () throws SQLException, ClassNotFoundException{
        conexao = Conexao.setConnection("127.0.0.1", "postgres", "postgres", "postgres");
    }
    
    public String[] consulta1(String nome) throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT * " +
                    "FROM multimidia.usuario " +
                    "WHERE username = '" + nome + "';";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String email = resultado.getString(1);
            String username = resultado.getString(2);
            String data = resultado.getString(3);
            String senha = resultado.getString(4);
            String name = resultado.getString(5);
            String sobrenome = resultado.getString(6);
            
            list[count++] = email + "\\" + username + "\\" + data + "\\" 
                    + senha + "\\" + name + "\\" + sobrenome;
            //System.out.println(email + "\\" + username + "\\" + data + "\\" 
                    //+ senha + "\\" + name + "\\" + sobrenome);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta2(String nome) throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT * " +
                    "FROM multimidia.conteudo " +
                    "WHERE nome = '"+nome+"';";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String id = resultado.getString(1);
            String titulo = resultado.getString(2);
            String descr = resultado.getString(3);
            String local = resultado.getString(4);
            String tags = resultado.getString(5);
            String idioma = resultado.getString(6);
            String name = resultado.getString(7);
            
            list[count++] = id + "\\" + titulo + "\\" + descr + "\\" 
                    + local + "\\" + tags + "\\" + idioma + "\\" + name;
            //System.out.println(id + "\\" + titulo + "\\" + descr + "\\" 
                    //+ local + "\\" + tags + "\\" + idioma + "\\" + name);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta3() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT * " +
                    "FROM multimidia.usuario " +
                    "WHERE data_de_nascimento < '1996-01-01'";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
           
        while (resultado.next()) {
            String email = resultado.getString(1);
            String username = resultado.getString(2);
            String data = resultado.getString(3);
            String senha = resultado.getString(4);
            String name = resultado.getString(5);
            String sobrenome = resultado.getString(6);
            
            list[count++] = email + "\\" + username + "\\" + data + "\\" 
                    + senha + "\\" + name + "\\" + sobrenome;
            //System.out.println(email + "\\" + username + "\\" + data + "\\" 
                    //+ senha + "\\" + name + "\\" + sobrenome);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    // Quais usuários são adms
    public String[] consulta4() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT primeiro_nome, sobrenome, usu.email " +
                    "FROM multimidia.administrador adm " +
                    "JOIN multimidia.usuario usu USING (email, username)";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String primeiro_nome = resultado.getString(1);
            String sobrenome = resultado.getString(2);
            String email = resultado.getString(3);
            list[count++] = primeiro_nome + "\\" + sobrenome + "\\" + email;
            //System.out.println(primeiro_nome + "\\" + sobrenome + "\\" + email);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    // Quais os conteúdos que não foram visualizados por ninguém ?
    public String[] consulta5() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT * " +
                    "FROM multimidia.conteudo " +
                    "   NATURAL LEFT JOIN multimidia.visualiza " +
                    "WHERE idvisualiza IS NULL;";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        String header = "";
        for (int i = 1; i <= 7; i++) {
            header += meta.getColumnLabel(i);
            if (i < 7)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String id = resultado.getString(1);
            String titulo = resultado.getString(2);
            String descr = resultado.getString(3);
            String local = resultado.getString(4);
            String tags = resultado.getString(5);
            String idioma = resultado.getString(6);
            String name = resultado.getString(7);
            
            list[count++] = id + "\\" + titulo + "\\" + descr + "\\" 
                    + local + "\\" + tags + "\\" + idioma + "\\" + name;
            //System.out.println(id + "\\" + titulo + "\\" + descr + "\\" 
                    //+ local + "\\" + tags + "\\" + idioma + "\\" + name);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    // Qual artista que produz mais conteúdo
    public String[] consulta6() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT username, primeiro_nome, sobrenome, usuario.email, count (idconteudo) as qnt " +
                    "FROM multimidia.conteudo " +
                    "	JOIN multimidia.produz USING (idconteudo) " +
                    "	JOIN multimidia.artista USING (cpf) " +
                    "	JOIN multimidia.usuario USING (username) " +
                    "GROUP BY username, primeiro_nome, sobrenome, usuario.email " +
                    "ORDER BY count (idconteudo) DESC;";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String user = resultado.getString(1);
            String nome = resultado.getString(2);
            String sobrenome = resultado.getString(3);
            String email = resultado.getString(4);
            String countId = resultado.getString(5);
            
            list[count++] = user + "\\" + nome + "\\" + sobrenome + "\\" 
                    + email + "\\" + countId;
            //System.out.println(user + "\\" + nome + "\\" + sobrenome + "\\" 
                    //+ email + "\\" + countId);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta7() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT nome, count (idconteudo) " +
                    "FROM multimidia.conteudo  " +
                    "GROUP BY nome";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String nome = resultado.getString(1);
            String countId = resultado.getString(2);
            
            list[count++] = nome + "\\" + countId;
            //System.out.println(nome + "\\" + countId);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta8() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT idconteudo, titulo, descricao, localidade, " +
                "lista_de_tags, idioma, nome as genero " +
                "FROM multimidia.conteudo " +
                "	JOIN multimidia.comentario USING (idconteudo);";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String idConteudo = resultado.getString(1);
            String titulo = resultado.getString(2);
            String descr = resultado.getString(3);
            String local = resultado.getString(4);
            String tags = resultado.getString(5);
            String idioma = resultado.getString(6);
            list[count++] = idConteudo + "\\" + titulo + "\\" + descr + "\\" + 
                    local + "\\" + tags + "\\" + idioma;
            //System.out.println(idConteudo + "\\" + titulo + "\\" + descr + "\\" + 
                    //local + "\\" + tags + "\\" + idioma);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta9() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "WITH contComment as " +
                    "( " +
                    "	SELECT idconteudo, count (num_comentario) as qntComment " +
                    "	FROM multimidia.conteudo " +
                    "		JOIN multimidia.comentario USING (idconteudo) " +
                    "	GROUP BY idconteudo " +
                    ") " +
                    " " +
                    "SELECT username, primeiro_nome, sobrenome, sum (qntComment) as qntCommentArt " +
                    "FROM contComment  " +
                    "	JOIN multimidia.produz USING (idconteudo) " +
                    "	JOIN multimidia.artista USING (cpf) " +
                    "	JOIN multimidia.usuario USING (username) " +
                    "GROUP BY username, primeiro_nome, sobrenome " +
                    "ORDER BY sum (qntComment) DESC";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String user = resultado.getString(1);
            String nome = resultado.getString(2);
            String sobrenome = resultado.getString(3);
            String countComen = resultado.getString(4);
            list[count++] = user + "\\" + nome + "\\" + sobrenome + "\\" + 
                    countComen;
            //System.out.println(user + "\\" + nome + "\\" + sobrenome + "\\" + countComen);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta10() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT nome, count(nome) as Numero_de_Comentarios " +
                "FROM multimidia.conteudo cont " +
                "JOIN multimidia.comentario coment " +
                "USING (idConteudo) " +
                "GROUP BY cont.nome";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String nome = resultado.getString(1);
            String countNome = resultado.getString(2);
            list[count++] = nome + "\\" + countNome;
            //System.out.println(nome + "\\" + countNome);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta11() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "WITH comMod as " +
                    "( " +
                    "	SELECT num_comentario, multimidia.moderador.username as userMod " +
                    "	FROM multimidia.comentario " +
                    "		JOIN multimidia.modera USING (num_comentario) " +
                    "		JOIN multimidia.moderador USING (cpf) " +
                    "), " +
                    "	comAdm as " +
                    "( " +
                    "	SELECT multimidia.comentario.num_comentario, multimidia.administrador.username " +
                    "as userAdm " +
                    "	FROM multimidia.comentario " +
                    "		JOIN multimidia.modera USING (num_comentario) " +
                    "		JOIN multimidia.julga USING (idmodera) " +
                    "		JOIN multimidia.administrador ON (multimidia.administrador.cpf = multimidia.julga.cpf) " +
                    ") " +
                    " " +
                    "SELECT num_comentario, comentario, userMod as moderador, userAdm as administrador " +
                    "FROM multimidia.comentario " +
                    "	LEFT JOIN comMod USING (num_comentario) " +
                    "	LEFT JOIN comAdm USING (num_comentario)";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String numComent = resultado.getString(1);
            String coment = resultado.getString(2);
            String mod = resultado.getString(3);
            String adm = resultado.getString(4);
            list[count++] = numComent + "\\" + coment + "\\" + mod + "\\" + 
                    adm;
            //System.out.println(numComent + "\\" + coment + "\\" + mod + "\\" + adm);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta12() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT usu.primeiro_nome, usu.sobrenome, " +
                "count(cont.idConteudo) AS quantidade_de_conteudos " +
                "FROM multimidia.artista art " +
                "JOIN multimidia.produz prod USING (cpf) " +
                "JOIN multimidia.conteudo cont USING (idconteudo) " +
                "JOIN multimidia.usuario usu USING (email, username) " +
                "GROUP BY art.cpf, usu.primeiro_nome, usu.sobrenome " +
                "ORDER BY count(cont.idConteudo) DESC";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String primeiro_nome = resultado.getString(1);
            String sobrenome = resultado.getString(2);
            String counts = resultado.getString(3);
            list[count++] = primeiro_nome + "\\" + sobrenome + "\\" + counts;
            //System.out.println(primeiro_nome + "\\" + sobrenome + "\\" + counts);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta13() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT DISTINCT usu.primeiro_nome, usu.sobrenome " + 
                "FROM multimidia.artista art " +
                "JOIN multimidia.usuario usu " +
                "USING (username, email) " +
                "JOIN multimidia.produz " +
                "USING (cpf) " +
                "WHERE idconteudo IN " +
                "( " +
                " SELECT idConteudo " +
                " FROM multimidia.conteudo cont " +
                " JOIN multimidia.produz prod " +
                " USING (idConteudo) " +
                " JOIN multimidia.comentario coment " +
                " USING (idConteudo) " +
                " JOIN multimidia.modera mod  " +
                " USING (num_comentario) " +
                " )";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String primeiro_nome = resultado.getString("primeiro_nome");
            String sobrenome = resultado.getString("sobrenome");
            list[count++] = primeiro_nome + "\\" + sobrenome;
            //System.out.println(primeiro_nome + "\\" + sobrenome);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    // Listar usuários que mais comentam para um artista específico (de acordo com o username do artista).
    public String[] consulta14(String nome) throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "WITH commentWithArt as " +
                    "( " +
                    "	SELECT comentario.username as coment, artista.username as art, " +
                    "count (num_comentario) as qnt " +
                    "	FROM multimidia.comentario " +
                    "		JOIN multimidia.produz USING (idconteudo) " +
                    "		JOIN multimidia.artista USING (cpf) " +
                    "	GROUP BY comentario.username, artista.username " +
                    ") " +
                    " " +
                    "SELECT coment as comentarista, art as artista, qnt " +
                    "FROM commentWithArt " +
                    "WHERE art = '"+nome+"'" +
                    "ORDER BY qnt DESC";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String coment = resultado.getString(1);
            String artista = resultado.getString(2);
            String qnt = resultado.getString(3);
            //System.out.println(coment + "\\" + artista + "\\" + qnt);
            list[count++] = coment + "\\" + artista + "\\" + qnt;
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    // Listar os artistas que possuem pelo menos x conteúdos produzidos
    public String[] consulta15(String X) throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "WITH contPerCpf as " +
                    "( " +
                    "	SELECT cpf, count (idconteudo) as qntPerCpf " +
                    "	FROM multimidia.produz " +
                    "	GROUP BY cpf " +
                    ") " +
                    " " +
                    "SELECT multimidia.usuario.username, primeiro_nome, sobrenome, qntPerCpf " +
                    "FROM multimidia.usuario " +
                    "	JOIN multimidia.artista ON (multimidia.usuario.username = multimidia.artista.username) " +
                    "	JOIN contPerCpf USING (cpf) " +
                    "WHERE qntPerCpf >= "+X;
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String user = resultado.getString(1);
            String nome = resultado.getString(2);
            String sobrenome = resultado.getString(3);
            String qntpercpf = resultado.getString(4);
            //System.out.println(user + "\\" + nome + "\\" + sobrenome + "\\" + qntpercpf);
            list[count++] = user + "\\" + nome + "\\" + sobrenome + "\\" + qntpercpf;
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta16(String nome) throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = "SELECT * " +
                    "FROM multimidia.conteudo " +
                    "WHERE lower (titulo) LIKE '%"+nome+"%'";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String id = resultado.getString(1);
            String titulo = resultado.getString(2);
            String descr = resultado.getString(3);
            String local = resultado.getString(4);
            String tags = resultado.getString(5);
            String idioma = resultado.getString(6);
            String name = resultado.getString(7);
            //System.out.println(id + "\\" + titulo + "\\" + descr + "\\" + local + "\\" +
                    //tags + "\\" + idioma + "\\" + name);
            list[count++] = id + "\\" + titulo + "\\" + descr + "\\" + local + "\\" +
                    tags + "\\" + idioma + "\\" + name;
        }
        comando.close();
        conexao.close();
        return list;
    }
    
    public String[] consulta17() throws SQLException, ClassNotFoundException {
        String[] list = new String[100];
        int count = 0;
        chamaConexao();
        String consulta = 
            "WITH qntPos as\n" +
            "(\n" +
            "	SELECT idConteudo, titulo, count (num_comentario) qntPositivo\n" +
            "	FROM multimidia.conteudo\n" +
            "		LEFT JOIN\n" +
            "		(\n" +
            "			SELECT idConteudo, num_comentario\n" +
            "			FROM multimidia.comentario\n" +
            "			WHERE recomenda = TRUE\n" +
            "		) comPos USING (idConteudo)\n" +
            "	GROUP BY idConteudo\n" +
            "),\n" +
            "	qntTotal as\n" +
            "(\n" +
            "	SELECT idConteudo, titulo, count (num_comentario) qntTotal\n" +
            "	FROM multimidia.conteudo\n" +
            "		LEFT JOIN multimidia.comentario USING (idConteudo)\n" +
            "	GROUP BY idConteudo\n" +
            ")\n" +
            "\n" +
            "\n" +
            "\n" +
            "SELECT titulo, porcentagem_de_comentarios_positivos\n" +
            "FROM multimidia.conteudo\n" +
            "	LEFT JOIN\n" +
            "	(\n" +
            "		SELECT idConteudo, titulo, qntPositivo/qntTotal*100 AS porcentagem_de_comentarios_positivos\n" +
            "		FROM qntTotal\n" +
            "			JOIN qntPos USING (idConteudo, titulo)\n" +
            "			WHERE qntTotal > 0\n" +
            "	) perc USING (idConteudo, titulo)\n" +
            "ORDER BY porcentagem_de_comentarios_positivos";
        Statement comando = conexao.createStatement();
        ResultSet resultado = comando.executeQuery(consulta);
        
        // ------------------ Get Headers ------------------ 
        ResultSetMetaData meta = resultado.getMetaData();
        
        int colCount = meta.getColumnCount();
        String header = "";
        for (int i = 1; i <= colCount; i++) {
            header += meta.getColumnLabel(i);
            if (i < colCount)
                header += "\\";
        }
        list[count++] = header;
        // -------------------------------------------------
        
        while (resultado.next()) {
            String titulo = resultado.getString(1);
            String porcentagem = resultado.getString(2);
            list[count++] = titulo + "\\" + porcentagem;
            //System.out.println(titulo + "\\" + porcentagem);
        }
        comando.close();
        conexao.close();
        return list;
    }
    
}
