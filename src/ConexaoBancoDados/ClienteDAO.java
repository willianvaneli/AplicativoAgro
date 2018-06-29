/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Cliente;
import aplicativoagro.Contato;
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
public abstract class ClienteDAO extends ConexaoSQLite {
    
    
    
    public static void criarTabela() throws Exception{
        // CRIANDO TABELA DE CLIENTE
        String sql = "CREATE TABLE IF NOT EXISTS cliente"
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome CHAR(40) NOT NULL,"
                + "dataNascimento CHAR(40) NOT NULL,"
                + "cpf CHAR(40) NOT NULL,"
                + "identidade CHAR(40) NOT NULL,"
                + "sexo CHAR(40) NOT NULL,"
                + "idEndereco INTEGER NOT NULL,"
                + "idContato INTEGER NOT NULL)";
        ConexaoSQLite.executarSQL(sql);
    }
    
    
    public static void salvarCliente(Cliente cliente) throws ClassNotFoundException, SQLException, ConnectException{
        // PRIMEIRO SALVAR ENDEREÇO E CONTATO RETORNANDO OS IDS DOS MESMOS
        int idEndereco= EnderecoDAO.salvarEndereco(cliente.getEndereco());
        int idContato= ContatoDAO.salvarContato(cliente.getContato());
        // DEPOIS SALVAR AS INFORMAÇÕES NA TABELA
        String sql = "INSERT INTO cliente (nome,dataNascimento,cpf,identidade,sexo,idEndereco,idContato)"
                + "values ('"
                + cliente.getNome() +"','"
                + cliente.getDataNascimento() + "','"
                + cliente.getCpf() +"','"
                + cliente.getIdentidade() + "','"
                + cliente.getSexo() +"',"
                + idEndereco + ","
                + idContato + ")";
        ConexaoSQLite.executarSQL(sql);
        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
    }
    
    
    public static void atualizarCliente(Cliente cliente){
        EnderecoDAO.atualizarEndereco(cliente.getEndereco());
        ContatoDAO.atualizarContato(cliente.getContato());
        String sql = "UPDATE cliente SET "
                + "nome ='" + cliente.getNome()
                + "',dataNascimento ='"+ cliente.getDataNascimento()
                + "',cpf = '"+cliente.getCpf()
                + "',identidade ='"+cliente.getIdentidade()
                + "',sexo ='"+cliente.getSexo()
                + "',idEndereco ="+cliente.getEndereco().getIdEndereco()
                + ",idContato ="+cliente.getContato().getIdContato()
                + " WHERE id = "+cliente.getIdCliente();
        try {
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao atualizar cliente");
        }
    }
    
    public static Cliente carregarClienteID(int id) throws SQLException, ClassNotFoundException, ConnectException{
        String sql = "SELECT * FROM cliente where id = " + id;
        return carregarClientes(sql).get(0);
    }
    
    public static List<Cliente> carregarTodosClientes()throws SQLException, ClassNotFoundException, ConnectException{
        String sql ="SELECT * FROM cliente";
        return carregarClientes(sql);
    }
    

    
    
    public static List<Cliente> carregarClientes(String sql) throws SQLException, ClassNotFoundException, ConnectException {
        List<Cliente> listaClientes = new ArrayList<>();
        Statement stament = null;
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);         
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setIdentidade(rs.getString("Identidade"));
                cliente.setSexo(rs.getString("sexo"));
                // CHAMAR FUNCAO PARA CARREGAR O ENDEREÇO
                Endereco endereco = EnderecoDAO.carregarEnderecoID(rs.getInt("id"));
                cliente.setEndereco(endereco);
                // CHAMAR FUNCAO PARA CHAMAR CONTATO
                Contato contato = ContatoDAO.carregarContatoID(rs.getInt("id"));
                cliente.setContato(contato);
                cliente.setIdCliente(rs.getInt("id"));
                listaClientes.add(cliente);
            }
        }catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao carregar clientes" + ex);
        }
        stament.close();
        //fecha a conexao com o banco de dados
        ConexaoSQLite.conexao.close();
        return listaClientes;
    }
    
    public static List<String> carregarNomeClientes() throws SQLException, ClassNotFoundException, ConnectException {
        List<String> listaNomesClientes = new ArrayList<>();
        Statement stament = null;
        String sql ="SELECT * FROM cliente";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);         
            while(rs.next()){
                listaNomesClientes.add(rs.getString("nome"));
            }
        }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha ao carregar clientes" + ex);
        }
        stament.close();
        //fecha a conexao com o banco de dados
        ConexaoSQLite.conexao.close();
        return listaNomesClientes;
    }
    
    public static List<Cliente> pesquisaPorNomeCliente(String str){
        List<Cliente> listaClientes = new ArrayList<>();
        Statement stament;
        String sql = "SELECT * FROM cliente WHERE cli_nome ilike '%"+str+"%'";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            //exeucta a query no meu banco de dados
            ResultSet rs = stament.executeQuery(sql);
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setIdentidade(rs.getString("Identidade"));
                cliente.setSexo(rs.getString("sexo"));
                // CHAMAR FUNCAO PARA CARREGAR O ENDEREÇO
                Endereco endereco = EnderecoDAO.carregarEnderecoID(rs.getInt("id"));
                cliente.setEndereco(endereco);
                // CHAMAR FUNCAO PARA CHAMAR CONTATO
                Contato contato = ContatoDAO.carregarContatoID(rs.getInt("id"));
                cliente.setContato(contato);
                cliente.setIdCliente(rs.getInt("id"));
                listaClientes.add(cliente);
            }
        }catch (SQLException | ClassNotFoundException | ConnectException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;
    }
    
    public static String pesquisaPorId(int id){
        String nome = null;
        Statement stament;
        String sql = "SELECT * FROM cliente WHERE id ="+id;
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
        int id=-1;
        Statement stament;
        String sql = "SELECT * FROM cliente WHERE nome ='"+nome+"'";
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
    
    public static int pesquisaPorCPF(String cpf){
        int id=-1;
        Statement stament;
        String sql = "SELECT * FROM cliente WHERE cpf ='"+cpf+"'";
        try {
            ConexaoSQLite.conectar();
            stament = ConexaoSQLite.conexao.createStatement();
            ResultSet rs = stament.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("id");
            }
        }catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
 
    public static void deletarCliente(int id){
        try {
            Cliente cliente = carregarClienteID(id);
            ConexaoBancoDados.ContatoDAO.deletarContato(cliente.getContato().getIdContato());
            ConexaoBancoDados.EnderecoDAO.deletarEndereco(cliente.getEndereco().getIdEndereco());
            String sql = "DELETE FROM cliente WHERE id ="+id;
            ConexaoSQLite.executarSQL(sql);
        } catch (SQLException | ClassNotFoundException | ConnectException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}