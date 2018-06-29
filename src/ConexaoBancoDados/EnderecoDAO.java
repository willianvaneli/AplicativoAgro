/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Endereco;
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
public abstract class EnderecoDAO extends ConexaoSQLite{
    
    public static void criarTabela() throws Exception{
    // CRIANDO TABELA DE ENDERECO
        String sql = "CREATE TABLE IF NOT EXISTS endereco"
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "rua CHAR(40) NOT NULL,"
                + "bairro CHAR(40) NOT NULL,"
                + "cidade CHAR(40) NOT NULL,"
                + "estado CHAR(40) NOT NULL,"
                + "cep CHAR(40) NOT NULL,"
                + "numero CHAR(40) NOT NULL)";
        ConexaoSQLite.executarSQL(sql);
    }
    
    public static int salvarEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco (rua,bairro,cidade,estado,cep,numero)"
                + "values ('"
                + endereco.getRua() +"','"
                + endereco.getBairro()+ "','"
                + endereco.getCidade()+ "','"
                + endereco.getEstado()+ "','"
                + endereco.getCep()+ "','"
                + endereco.getNumero()+"')";
        int key=-1;
        try {
            key= ConexaoSQLite.executarInsertSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao salvar Endereco");
        }
        return key;
    }
    
    public static void atualizarEndereco(Endereco endereco) {
        String sql = "UPDATE endereco SET "
                + "rua ='" + endereco.getRua()
                + "',bairro ='"+ endereco.getBairro()
                + "',cidade = '"+endereco.getCidade()
                + "',estado ='"+endereco.getEstado()
                + "',cep ='"+endereco.getCep()
                + "',numero ='"+endereco.getNumero()
                + "' WHERE id = " +endereco.getIdEndereco();
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao salvar Endereco");
        }
    }
    
    public static Endereco carregarEnderecoID(int id) throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM endereco where id = " + id;
        return carregarEnderecos(sql).get(0);
    }

    public static List<Endereco> carregarTodosEndereco() throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM endereco";
        return carregarEnderecos(sql);
    }
    
    public static List<Endereco> carregarEnderecos(String sql) {
        List<Endereco> listaEnderecos = new ArrayList<>();
        Statement stament = null;
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while (rs.next()){
                Endereco endereco = new Endereco();
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCep(rs.getString("cep"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setIdEndereco(rs.getInt("id"));
                listaEnderecos.add(endereco);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha no carregamento de endereco" + ex);
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
    
    
     public static void deletarEndereco (int id){
         String sql = "DELETE FROM endereco WHERE id ="+id;
         try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao deletar endereco");
        }
         
     }
    
    
    
    
    
}
