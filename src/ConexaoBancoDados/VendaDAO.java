/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Venda;
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
public class VendaDAO extends ConexaoSQLite {
    public static void criarTabela() throws Exception{
        // CRIANDO TABELA DA VENDA
        String sql = "CREATE TABLE IF NOT EXISTS venda"
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "quantidade INTEGER NOT NULL,"
                + "valorTotal INTEGER NOT NULL,"
                + "valorPago INTEGER NOT NULL,"
                + "dataVenda CHAR(40) NOT NULL,"
                + "dataQuitacao CHAR(40) NOT NULL,"
                + "idCliente INTEGER NOT NULL,"
                + "idProduto INTEGER NOT NULL)";
        ConexaoSQLite.executarSQL(sql);
    }

    public static int salvarVenda(Venda venda) {
        
        String sql = "INSERT INTO venda (idCliente,idProduto,quantidade,valorTotal,valorPago,dataVenda,dataQuitacao)"
                    + "values ("
                    + venda.getIdCliente()+","
                    + venda.getIdProduto()+","
                    + venda.getQuantidade()+","
                    + venda.getValorTotal()+","
                    + venda.getValorPago()+",'"
                    + venda.getDataVenda()+"','"
                    + venda.getDataQuitacao()+ "')";
        int key=-1;
        try {
            
            key=ConexaoSQLite.executarInsertSQL(sql);
            JOptionPane.showMessageDialog(null, "Venda registrada com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao registrar venda");
        }
        return key;
    }


    public static void atualizarVenda(Venda venda){
        String sql = "UPDATE venda SET "
                + "idCliente =" + venda.getIdCliente() 
                + ",idProduto ="+ venda.getIdProduto()
                + ",quantidade = "+venda.getQuantidade()
                +",valorTotal ="+venda.getValorTotal()
                +",valorPago ="+venda.getValorPago()
                +",dataVenda ='"+venda.getDataVenda()
                +"',dataQuitacao='"+venda.getDataQuitacao()
                +"' WHERE id = "+venda.getIdVenda();
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao atualizar venda");
        }
    }

    public static Venda carregarVendaID(int id) {
        String sql = "SELECT * FROM venda where id = " + id;
        return carregarVendas(sql).get(0);
    }
    
    public static List<Venda> carregarTodasVendas() {
        String sql = "SELECT * FROM venda";
        return carregarVendas(sql);
    }

    public static List<Venda> carregarVendas(String sql) {
        List<Venda> listaVendas = new ArrayList<>();
        //ConexaoSQLite con;
        Statement stament = null;
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while (rs.next()){
                Venda venda = new Venda();
                venda.setIdCliente(rs.getInt("idCliente"));
                venda.setIdProduto(rs.getInt("idProduto"));
                venda.setQuantidade(rs.getInt("quantidade"));
                venda.setValorTotal(rs.getInt("valorTotal"));
                venda.setValorPago(rs.getInt("valorPago"));
                venda.setDataVenda(rs.getString("dataVenda"));
                venda.setDataQuitacao(rs.getString("dataQuitacao"));
                venda.setIdVenda(rs.getInt("id"));
                listaVendas.add(venda);
            }
        }catch (SQLException ex) {
            }
        try {
            stament.close();
            //fecha a conexao com o banco de dados
            ConexaoSQLite.conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao fechar conexao" + ex);
        }
        return listaVendas;
        }
   
    public static void deletarVenda(int id){
        String sql = "DELETE FROM venda WHERE id ="+id;
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao deletar venda");
        }
    }
    
    
}
