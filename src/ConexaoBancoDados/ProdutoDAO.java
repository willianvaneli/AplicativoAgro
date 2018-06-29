/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Produto;
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
public abstract class ProdutoDAO extends ConexaoSQLite{

    public static void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS produto"
            + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nome CHAR(40) NOT NULL,"
            + "unidade CHAR(40) NOT NULL,"
            + "quantidade INTEGER NOT NULL)";
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao criar tabela de produto");
        }
    }

    public static void salvarProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome,unidade,quantidade)"
                   + "values ('"
                   + produto.getNome() +"','"
                   + produto.getUnidade() + "',"
                   + produto.getQuantidade() + ")";
        try {
            ConexaoSQLite.executarSQL(sql);
            JOptionPane.showMessageDialog(null, "Produto salvo com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao salvar produto");
        }
    }
    
    public static void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome ='" + produto.getNome() + "',unidade ='"+ produto.getUnidade()+ "',quantidade = "+produto.getQuantidade()+" WHERE id = "+produto.getIdProduto();
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao atualizar produto");
        }
    }
    
    public static void deletarProduto(int id){
        String sql = "DELETE FROM produto WHERE id ="+id;
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao deletar produto");
        }
    }
    
    
    public static Produto carregarProdutoID(int id) throws ConnectException, SQLException{
        String sql = "SELECT * FROM produto where id = " + id;
        return carregarProdutos(sql).get(0);
    }
    
    public static List<Produto> carregarTodosProdutos() throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM produto";
        return carregarProdutos(sql);
    }

    public static List<Produto> carregarProdutos(String sql) {
        List<Produto> listaProdutos = new ArrayList<>();
        //ConexaoSQLite con;
        Statement stament = null;
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while (rs.next()){
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setUnidade(rs.getString("unidade"));
                produto.setIdProduto(rs.getInt("id"));
                produto.setQuantidade(rs.getInt("quantidade"));
                listaProdutos.add(produto);
            }
        }catch (SQLException ex) {
            }
        try {
            stament.close();
            //fecha a conexao com o banco de dados
            ConexaoSQLite.conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao fechar conexao" + ex);
        }
        return listaProdutos;
        }
    
    public static List<String> carregarNomeProdutos() {
        List<String> listaNomeProdutos = new ArrayList<>();
        Statement stament = null;
        String sql = "SELECT * FROM produto";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while (rs.next()){
                listaNomeProdutos.add(rs.getString("nome"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao carregar produtos" + ex);
            }
        try {
            stament.close();
            //fecha a conexao com o banco de dados
            ConexaoSQLite.conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao fechar conexao" + ex);
        }
        return listaNomeProdutos;
        }
    
    public static String pesquisaPorId(int id){
        String nome = null;
        Statement stament;
        String sql = "SELECT * FROM produto WHERE id ="+id;
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while(rs.next()){
                nome = rs.getString("nome");
            }
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome;
    }
    
    public static int pesquisaPorNome(String nome){
        int id=0;
        Statement stament;
        String sql = "SELECT * FROM produto WHERE nome ='"+nome+"'";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("id");
            }
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
