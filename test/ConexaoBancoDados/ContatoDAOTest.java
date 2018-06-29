/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexaoBancoDados;

import aplicativoagro.Contato;
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
public class ContatoDAOTest {
    
    public ContatoDAOTest() {
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
     * Test of criarTabela method, of class ContatoDAO.
     */
    @Test
    public static void testCriarTabela() throws Exception {
        System.out.println("criarTabela");
        ContatoDAO.criarTabela();
    }

    /**
     * Test of salvarContato method, of class ContatoDAO.
     */
    @Test
    public void testSalvarContato() throws ClassNotFoundException, SQLException, ConnectException {
        System.out.println("salvarContato");
        Contato contato = new Contato();
        contato.setCelular1("998121999");
        contato.setTelefone("");
        contato.setEmail("willianvaneli@gmail.com");
        ContatoDAO.salvarContato(contato);
    }

    /**
     * Test of carregarContatoID method, of class ContatoDAO.
     */
    @Test
    public void testCarregarContatoID() throws Exception {
        System.out.println("carregarContatoID");
        int id = 0;
        Contato result = ContatoDAO.carregarContatoID(id);
    }

    /**
     * Test of carregarTodosContatos method, of class ContatoDAO.
     */
    @Test
    public void testCarregarTodosContatos() throws Exception {
        System.out.println("carregarTodosContatos");
        List<Contato> result = ContatoDAO.carregarTodosContatos();
    }

    /**
     * Test of carregarContatos method, of class ContatoDAO.
     */
    @Test
    public void testCarregarContatos() throws Exception {
        System.out.println("carregarContatos");
        String sql = "SELECT * FROM contato";
        List<Contato> expResult = null;
        List<Contato> result = ContatoDAO.carregarContatos(sql);
    }
    
}
