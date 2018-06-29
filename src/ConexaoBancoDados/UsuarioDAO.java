/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Usuario;
import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class UsuarioDAO extends ConexaoSQLite{
    //public static Usuario usuario;
    
    public static void criarTabela() throws Exception{
        // CRIANDO TABELA DA VENDA
        String sql = "CREATE TABLE IF NOT EXISTS usuario"
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome CHAR(40) NOT NULL,"
                + "funcao CHAR(40) NOT NULL,"
                + "login CHAR(40) NOT NULL,"
                + "senha CHAR(40) NOT NULL)";
        ConexaoSQLite.executarSQL(sql);
    }
    
    public static void salvarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome,funcao,login,senha)"
                    + "values ('"
                    + usuario.getNome()+"','"
                    + usuario.getFuncao()+"','"
                    + usuario.getLogin()+"','"
                    + usuario.getSenha()+"')";
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao registrar usuario");
        }
    }
    
    public static void atualizarUsuario(Usuario usuario){
        String sql = "UPDATE usuario SET "
                + "nome ='" + usuario.getNome()
                + "',funcao ='"+ usuario.getFuncao()
                +"',senha ='"+usuario.getSenha()
                +"' WHERE login = '"+usuario.getLogin()+"'";
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao atualizar usuario");
        }
    }
    
    public static void atualizarSenha(String login, String senha){
        String sql = "UPDATE usuario SET "
                +"senha ='"+senha
                +"' WHERE login = '"+login+"'";
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao atualizar senha");
        }
    }
    
    public static Usuario carregaPorLogin(String login){
        Usuario usuario = new Usuario();
        Statement stament;
        String sql = "SELECT * FROM usuario WHERE login ='"+login+"'";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while(rs.next()){
                usuario.setNome(rs.getString("nome"));
                usuario.setFuncao(rs.getString("funcao"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public static void deletarUsuario(String login){
        String sql = "DELETE FROM usuario WHERE login ='"+login+"'";
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao deletar usuario");
        }
    }
    
    
    public static boolean verificaExisteLogin(String login){
        boolean verificador=false;
        Statement stament;
        String sql = "SELECT * FROM usuario WHERE login ='"+login+"'";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while(rs.next()){
                if (login.equals(rs.getString("login"))){
                    verificador=true;
                }
            }
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verificador;
    }
    
    
        public static List<Usuario> carregarTodosUsuarios() throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM usuario";
        return carregarUsuarios(sql);
    }
    
    
    public static List<Usuario> carregarUsuarios(String sql) {
        List<Usuario> listaEnderecos = new ArrayList<>();
        Statement stament = null;
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setFuncao(rs.getString("funcao"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                listaEnderecos.add(usuario);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha no carregamento de usuario" + ex);
        }
        try {
            stament.close();
            //fecha a conexao com o banco de dados
            ConexaoSQLite.conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao fechar conexao" + ex);
        }
        return listaEnderecos;
        }

    
}
