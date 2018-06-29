/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public abstract class ConexaoSQLite {
    public static Connection conexao;
    
    
    public static Connection conectar() {
        try{
            String url = "jdbc:sqlite:database.db";
            Class.forName("org.sqlite.JDBC");
            ConexaoSQLite.conexao = DriverManager.getConnection(url);
                    
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Falha na conexao" + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoSQLite.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha na conexao" + ex);
        }
        return conexao;
    }
    
    public static void desconectar(Connection conexao) {
        try{
            if(ConexaoSQLite.conexao.isClosed() == false){
                ConexaoSQLite.conexao.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Falha na desconexao" + e);
        }
    }
    
    public static void executarSQL(String sql) throws SQLException {
        ConexaoSQLite con = null;
        con.conexao= ConexaoSQLite.conectar();
        Statement stament = con.conexao.createStatement();
        stament.executeUpdate(sql);
        stament.close();
        ConexaoSQLite.desconectar(con.conexao);
    }
    

    
    
    
    public static int executarInsertSQL(String sql) throws SQLException {
        ConexaoSQLite con = null;
        con.conexao= ConexaoSQLite.conectar();
        PreparedStatement statement = con.conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        int key=-1;
         ResultSet rs = statement.getGeneratedKeys();
         if (rs != null && rs.next()) {
            key = rs.getInt(1);
         }
         
        statement.close();
        ConexaoSQLite.desconectar(con.conexao);
        return key;
    }
    
}
