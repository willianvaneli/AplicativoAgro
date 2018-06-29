/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Endereco;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Willian
 */
public class EnderecoDAOTest {
    
    public EnderecoDAOTest() {
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
     * Test of criarTabela method, of class EnderecoDAO.
     */
    @Test
    public void testCriarTabela() throws Exception {
        System.out.println("criarTabela");
        EnderecoDAO.criarTabela();
    }

    /**
     * Test of salvarEndereco method, of class EnderecoDAO.
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.net.ConnectException
     */
    @Test
    public void testSalvarEndereco() throws ClassNotFoundException, SQLException, ConnectException {
        System.out.println("salvarEndereco");
        Endereco endereco = new Endereco();
        endereco.setBairro("Balneario de carapebus");
        endereco.setCep("29164878");
        endereco.setCidade("Serra");
        endereco.setEstado("ES");
        endereco.setNumero("1273");
        endereco.setRua("Rua da casuarina");
        EnderecoDAO.salvarEndereco(endereco);
    }

    /**
     * Test of carregarEnderecoID method, of class EnderecoDAO.
     */
    @Test
    public void testCarregarEnderecoID() throws Exception {
        System.out.println("carregarEnderecoID");
        int id = 1;
        Endereco expResult = null;
        Endereco result = EnderecoDAO.carregarEnderecoID(id);
    }

    /**
     * Test of carregarTodosEndereco method, of class EnderecoDAO.
     */
    @Test
    public void testCarregarTodosEndereco() throws Exception {
        System.out.println("carregarTodosEndereco");
        List<Endereco> expResult = null;
        List<Endereco> result = EnderecoDAO.carregarTodosEndereco();
    }

    /**
     * Test of carregarEnderecos method, of class EnderecoDAO.
     */
    @Test
    public void testCarregarEnderecos() throws Exception {
        System.out.println("carregarEnderecos");
        String sql = "SELECT * FROM endereco";
        List<Endereco> expResult = null;
        List<Endereco> result = EnderecoDAO.carregarEnderecos(sql);
    }
    
}
