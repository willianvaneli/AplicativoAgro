/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Contato;
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
public class ContatoDAO extends ConexaoSQLite{
    
    public static void criarTabela() {
    // CRIANDO TABELA DE CONTATO
        String sql = "CREATE TABLE IF NOT EXISTS contato"
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "telefone CHAR(40) NOT NULL,"
                + "celular CHAR(40) NOT NULL,"
                + "email CHAR(40) NOT NULL)";
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao criar tabela de contato");
        }
    }

    public static int salvarContato(Contato contato) {
        String sql = "INSERT INTO contato (telefone,celular,email)"
                + "values ('"
                + contato.getTelefone() +"','"
                + contato.getCelular1()+ "','"
                + contato.getEmail()+"')";
        int key=-1;
        try {
            key= ConexaoSQLite.executarInsertSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao salvar contato");
        }
        return key;
    }
    
    public static void atualizarContato(Contato contato) {
        String sql = "UPDATE contato SET "
                + "telefone ='" + contato.getTelefone()
                + "',celular ='"+ contato.getCelular1()
                + "',email = '"+contato.getEmail()
                + "' WHERE id = " +contato.getIdContato();
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao salvar contato");
        }
    }
    
    
    public static Contato carregarContatoID(int id) throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM contato where id = " + id;
        return carregarContatos(sql).get(0);
    }

    public static List<Contato> carregarTodosContatos() throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM contato";
        return carregarContatos(sql);
    }
    
    
    public static List<Contato> carregarContatos(String sql) throws SQLException, ClassNotFoundException, ConnectException {
        List<Contato> listaContatos = new ArrayList<>();
        Statement stament;
        ConexaoSQLite.conectar();
        stament = ConexaoSQLite.conexao.createStatement();
        //exeucta a query no meu banco de dados
        ResultSet rs = stament.executeQuery(sql);
        while (rs.next()){
            Contato contato = new Contato();
            contato.setTelefone(rs.getString("telefone"));
            contato.setCelular1(rs.getString("celular"));
            contato.setEmail(rs.getString("email"));
            contato.setIdContato(rs.getInt("id"));
            listaContatos.add(contato);
        }
        stament.close();
        //fecha a conexao com o banco de dados
        ConexaoSQLite.conexao.close();
        return listaContatos;
        }
    
    
    public static void deletarContato(int id){
        String sql = "DELETE FROM contato WHERE id ="+id;
         try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao deletar contato");
        }
        
    }
    
}
