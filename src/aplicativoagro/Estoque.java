/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativoagro;

import java.util.List;

/**
 *
 * @author Willian
 */
public class Estoque {

    private List<Produto> estoque;
    
    public List<Produto> getEstoque() {
        return estoque;
    }

    public void setEstoque(List<Produto> estoque) {
        this.estoque = estoque;
    }
}
