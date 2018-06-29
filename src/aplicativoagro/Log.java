/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativoagro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class Log {

    
    public static void salvarLogVendas(int idVenda,String alteracao){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date();
        String data= dateFormat.format(date);
        try {
            FileWriter writer;
            boolean existe = (new File(".\\LogVendas.csv")).exists();
            writer = new FileWriter(".\\LogVendas.csv", true);
            writer.append(Inicio.usuarioAtual.getLogin());
            writer.append(",");
            writer.append(alteracao);
            writer.append(",");
            writer.append(Integer.toString(idVenda));
            writer.append(",");
            writer.append(data);
            writer.append("\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
     public static void salvarLogUsuario(String login,String alteracao){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date();
        String data= dateFormat.format(date);
        try {
            FileWriter writer;
            boolean existe = (new File(".\\LogUsuario.csv")).exists();
            writer = new FileWriter(".\\LogUsuario.csv", true);
            writer.append(Inicio.usuarioAtual.getLogin());
            writer.append(",");
            writer.append(alteracao);
            writer.append(",");
            writer.append(login);
            writer.append(",");
            writer.append(data);
            writer.append("\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 /*   
    public static List carregarClientes(){
        List listaClientes = new ArrayList();
        try {
            FileReader arquivo = new FileReader(".\\Clientes.csv");
            BufferedReader lerArquivo = new BufferedReader(arquivo);
            String linha = lerArquivo.readLine();
            while(linha !=null){
                listaClientes.add(carregarCliente(linha));
                linha = lerArquivo.readLine();
            }
            arquivo.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel carregar o arquivo");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possivel carregar a linha");
        }
        return listaClientes;
    }
    
    public static Cliente carregarCliente(String linha){
        String lista[] =  linha.split(",");
        Cliente cliente = new Cliente();
        cliente.setNome(lista[0]);
        cliente.setDataNascimento(lista[1]);
        cliente.setCpf(lista[2]);
        cliente.setIdentidade(lista[3]);
        cliente.setSexo(lista[4]);
        Contato contato = new Contato();
        contato.setTelefone(lista[5]);
        contato.setCelular1(lista[6]);
        contato.setEmail(lista[7]);
        cliente.setContato(contato);
        Endereco endereco = new Endereco();
        endereco.setRua(lista[8]);
        endereco.setBairro(lista[9]);
        endereco.setCidade(lista[10]);
        endereco.setEstado(lista[11]);
        endereco.setCep(lista[12]);
        cliente.setEndereco(endereco);
        return cliente;
    }
    
    public static void salvarProduto(Produto produto){
        // produto unidade quantidade
        FileWriter writer;
        boolean existe = (new File(".\\produtos.csv")).exists();
        try{
            if (existe){
                writer = new FileWriter(".\\produtos.csv",false);
            }else{
                writer = new FileWriter(".\\produtos.csv",true);
            }
            writer.append(produto.getNome());
            writer.append(",");
            writer.append(produto.getUnidade());
            writer.append("\n");
            writer.close();
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Arquivo de Produtos não pode ser criado ou aberto");
        }
    }
     */
}
