/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativoagro;

import ConexaoBancoDados.ConexaoSQLite;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class Inicio extends javax.swing.JFrame {
    static Usuario usuarioAtual;
    /**
     * Creates new form Inicio
     */
    public int irrigacao=0;                                                                     //DECLARADO VALOR DE IRRIGAÇÃO SETO EM 0 COMO DESLIGADO
    public Inicio() throws ClassNotFoundException, ConnectException {
        initComponents();
        
        ConexaoSQLite.conexao = ConexaoSQLite.conectar();
        try {
            ConexaoBancoDados.UsuarioDAO.criarTabela();
        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao criar tabela de usuários");
        }
        if(!(ConexaoBancoDados.UsuarioDAO.verificaExisteLogin("admin"))){
            Usuario adm= new Usuario();
            adm.setFuncao("administrador");
            adm.setLogin("admin");
            adm.setNome("admin");
            adm.setSenha("123");
            ConexaoBancoDados.UsuarioDAO.salvarUsuario(adm);
        }
        TelaEntradaLogin telaEntrada = new TelaEntradaLogin(new javax.swing.JDialog(),true);
        telaEntrada.setVisible(true);
        
        ConexaoSQLite.desconectar(ConexaoSQLite.conexao);
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        Inicio = new javax.swing.JMenu();
        contaMenu = new javax.swing.JMenuItem();
        IrrigarM = new javax.swing.JMenu();
        AdubarM = new javax.swing.JMenu();
        EstoqueM = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        Estoque = new javax.swing.JMenuItem();
        VENDAS = new javax.swing.JMenu();
        adicionarVendaMenu = new javax.swing.JMenuItem();
        exibirHistoricoMenu = new javax.swing.JMenuItem();
        adicionarCompradorMenu = new javax.swing.JMenuItem();
        COTACAO = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("BEM VINDO AO APP AGRO");

        Inicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/InicioIcone.jpg"))); // NOI18N
        Inicio.setToolTipText("Inicio");

        contaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/user_edit.png"))); // NOI18N
        contaMenu.setText("Gerenciar contas");
        contaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contaMenuActionPerformed(evt);
            }
        });
        Inicio.add(contaMenu);

        jMenuBar1.add(Inicio);

        IrrigarM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/irrigaçãoIcone.jpg"))); // NOI18N
        IrrigarM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IrrigarMActionPerformed(evt);
            }
        });
        jMenuBar1.add(IrrigarM);

        AdubarM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/adubarIcone.jpg"))); // NOI18N
        AdubarM.setToolTipText("Adubar");
        jMenuBar1.add(AdubarM);
        AdubarM.getAccessibleContext().setAccessibleName("Adubar");

        EstoqueM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/EstoqueIcone.jpg"))); // NOI18N
        EstoqueM.setToolTipText("Estoque");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/add.png"))); // NOI18N
        jMenuItem1.setText("Produtos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        EstoqueM.add(jMenuItem1);

        Estoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/EstoqueSF10.png"))); // NOI18N
        Estoque.setText("Ver estoque");
        Estoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstoqueActionPerformed(evt);
            }
        });
        EstoqueM.add(Estoque);

        jMenuBar1.add(EstoqueM);

        VENDAS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/vendasIcone.png"))); // NOI18N
        VENDAS.setToolTipText("Vendas");

        adicionarVendaMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/cart_add.png"))); // NOI18N
        adicionarVendaMenu.setText("Vendas");
        adicionarVendaMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarVendaMenuActionPerformed(evt);
            }
        });
        VENDAS.add(adicionarVendaMenu);

        exibirHistoricoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/cart_go.png"))); // NOI18N
        exibirHistoricoMenu.setText("Exibir Histórico de vendas");
        exibirHistoricoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exibirHistoricoMenuActionPerformed(evt);
            }
        });
        VENDAS.add(exibirHistoricoMenu);

        adicionarCompradorMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/user_add.png"))); // NOI18N
        adicionarCompradorMenu.setText("Compradores");
        adicionarCompradorMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarCompradorMenuActionPerformed(evt);
            }
        });
        VENDAS.add(adicionarCompradorMenu);

        jMenuBar1.add(VENDAS);

        COTACAO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aplicativoagro/imagens/cotaçãoIcone.jpg"))); // NOI18N
        COTACAO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                COTACAOMouseClicked(evt);
            }
        });
        COTACAO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COTACAOActionPerformed(evt);
            }
        });
        jMenuBar1.add(COTACAO);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addContainerGap(491, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        TelaAdicionarProduto telaProduto = new TelaAdicionarProduto();
        telaProduto.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void IrrigarMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IrrigarMActionPerformed
        //Digitar irrigação aqui
        JOptionPane.showMessageDialog(null, irrigacao);
        if(this.irrigacao==0){
            if(JOptionPane.showConfirmDialog(null,"Deseja iniciar irrigação?","",JOptionPane.YES_NO_OPTION)==0){
                irrigacao=1;
                JOptionPane.showMessageDialog(null, irrigacao);
                //TODO PROCESSO PARA INICIAR A IRRIGAÇÃO DEVE SER CODIFICADO ABAIXO
            }
        }else{
            irrigacao=0;
            JOptionPane.showMessageDialog(null, irrigacao);
            //TODO CODIGO PARA ENCERRAR A IRRIGAÇÃO DEVE SER CODIFICADO ABAIXO
        }
    }//GEN-LAST:event_IrrigarMActionPerformed

    private void adicionarVendaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarVendaMenuActionPerformed
        TelaAdicionarVenda adicionarVenda = new TelaAdicionarVenda();
        adicionarVenda.setVisible(true);
    }//GEN-LAST:event_adicionarVendaMenuActionPerformed

    private void adicionarCompradorMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarCompradorMenuActionPerformed
        try{
            TelaComprador  tComprador = new TelaComprador();
            tComprador.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_adicionarCompradorMenuActionPerformed

    private void EstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstoqueActionPerformed
        TelaEstoque estoque = new TelaEstoque();
        estoque.setVisible(true);
    }//GEN-LAST:event_EstoqueActionPerformed

    private void exibirHistoricoMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exibirHistoricoMenuActionPerformed
        TelaRegistroVendas registro = new TelaRegistroVendas();
        registro.setVisible(true);
    }//GEN-LAST:event_exibirHistoricoMenuActionPerformed

    private void contaMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contaMenuActionPerformed
        //Controla qual tela de controle de usuários será aberta
        if (usuarioAtual.getFuncao().equalsIgnoreCase("Administrador")){
            TelaGerenciarUsuario telaAdm = new TelaGerenciarUsuario();
            telaAdm.setVisible(true);
        }else{
            TelaGerUsuarioComum telaComum = new TelaGerUsuarioComum();
            telaComum.setVisible(true);
        }
    }//GEN-LAST:event_contaMenuActionPerformed

    private void COTACAOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COTACAOActionPerformed
        
    }//GEN-LAST:event_COTACAOActionPerformed

    private void COTACAOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COTACAOMouseClicked
        // TODO add your handling code here:
        String Link= "http://200.198.51.71/detec/filtro_boletim_es/filtro_boletim_es.php"; 
        try {
            java.awt.Desktop.getDesktop().browse( new java.net.URI(Link));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao abrir URL");
        }
    }//GEN-LAST:event_COTACAOMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Inicio().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ConnectException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AdubarM;
    private javax.swing.JMenu COTACAO;
    private javax.swing.JMenuItem Estoque;
    private javax.swing.JMenu EstoqueM;
    private javax.swing.JMenu Inicio;
    private javax.swing.JMenu IrrigarM;
    private javax.swing.JMenu VENDAS;
    private javax.swing.JMenuItem adicionarCompradorMenu;
    private javax.swing.JMenuItem adicionarVendaMenu;
    private javax.swing.JMenuItem contaMenu;
    private javax.swing.JMenuItem exibirHistoricoMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}