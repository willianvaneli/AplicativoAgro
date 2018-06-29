/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativoagro;

/**
 *
 * @author Willian
 */
public class Venda {

    private int idCliente;
    private int idProduto;
    private double quantidade;
    private double valorTotal;
    private double valorPago;
    private String dataVenda;
    private String dataQuitacao;
    private int idVenda;
    
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int cliente) {
        this.idCliente = cliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int produto) {
        this.idProduto = produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDataQuitacao() {
        return dataQuitacao;
    }

    public void setDataQuitacao(String dataQuitacao) {
        this.dataQuitacao = dataQuitacao;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
}
