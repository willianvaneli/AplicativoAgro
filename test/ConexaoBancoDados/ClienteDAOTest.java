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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Willian
 */
public class ClienteDAOTest {
    
    public ClienteDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of criarTabela method, of class ClienteDAO.
     */
    @Test
    public void testCriarTabela() {
        System.out.println("criarTabela");
        try {
            ClienteDAO.criarTabela();
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of salvarCliente method, of class ClienteDAO.
     */
    @Test
    public void testSalvarCliente() throws ClassNotFoundException, SQLException, ConnectException {
        System.out.println("salvarCliente");
        Cliente cliente = new Cliente();
        cliente.setCpf("12287787798");
        cliente.setIdentidade("19633276");
        cliente.setDataNascimento("08/02/1988");
        cliente.setNome("willian");
        cliente.setSexo("Masculino");
        Contato contato = new Contato();
        contato.setCelular1("998121999");
        contato.setTelefone("");
        contato.setEmail("willianvaneli@gmail.com");
        cliente.setContato(contato);
        Endereco endereco = new Endereco();
        endereco.setBairro("Balneario de carapebus");
        endereco.setCep("29164878");
        endereco.setCidade("Serra");
        endereco.setEstado("ES");
        endereco.setNumero("1273");
        endereco.setRua("Rua da casuarina");
        cliente.setEndereco(endereco);
        ClienteDAO.salvarCliente(cliente);
        
    }

    /**
     * Test of carregarTodosClientes method, of class ClienteDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCarregarTodosClientes() throws Exception {
        System.out.println("carregarTodosClientes");
        List expResult = null;
        List result = ClienteDAO.carregarTodosClientes();
    }

    /**
     * Test of carregarClientes method, of class ClienteDAO.
     */
 

    /**
     * Test of carregarClienteID method, of class ClienteDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCarregarClienteID() throws Exception {
        System.out.println("carregarClienteID");
        int id = 1;
        Cliente result = ClienteDAO.carregarClienteID(id);
    }

    /**
     * Test of carregarClientes method, of class ClienteDAO.
     * @throws java.lang.Exception
     */
    @Test
    public void testCarregarClientes() throws Exception {
        System.out.println("carregarClientes");
        String sql = "SELECT * FROM cliente";
        List<Cliente> result = ClienteDAO.carregarClientes(sql);
    }
    
}
