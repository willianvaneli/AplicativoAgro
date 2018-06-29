/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicativoagro;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willian
 */
public class TelaGerenciarUsuario extends javax.swing.JFrame {
    String login;
    /**
     * Creates new form TelaGerenciarUsuario
     */
    public TelaGerenciarUsuario() {
        initComponents();
        try {
            atualizarTabela();
        } catch (SQLException | ClassNotFoundException | ConnectException ex) {
            Logger.getLogger(TelaRegistroVendas.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,"Falha na atualização da tabela");
        }
    }

    private void atualizarTabela() throws SQLException, ClassNotFoundException, ConnectException{
    DefaultTableModel model = (DefaultTableModel)tabelaUsuarios.getModel();
    model.getDataVector().removeAllElements();
    model.fireTableDataChanged();
    List<Usuario> usuarios = ConexaoBancoDados.UsuarioDAO.carregarTodosUsuarios();
    for (Usuario usuario : usuarios){
        model.addRow(new Object[]{
            usuario.getNome(),
            usuario.getLogin(),
            usuario.getFuncao()
        });
    }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaUsuarios = new javax.swing.JTable();
        adicionar = new javax.swing.JButton();
        excluir = new javax.swing.JButton();
        trocarSenha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GERENCIADOR DE USUÁRIOS");

        tabelaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "USUARIO", "LOGIN", "FUNÇÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaUsuarios);

        adicionar.setText("Adicionar");
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });

        excluir.setText("Excluir");
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });

        trocarSenha.setText("Trocar Senha");
        trocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trocarSenhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(trocarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(adicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adicionar)
                        .addGap(18, 18, 18)
                        .addComponent(trocarSenha)
                        .addGap(18, 18, 18)
                        .addComponent(excluir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        TelaAdicionarUsuario telaUsuario = new TelaAdicionarUsuario();
        // Colocado listener para atualizar tabela sempre que a tela de adicionar usuário ser fechada
        WindowListener listener = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent we) {
            }

            @Override
            public void windowClosing(WindowEvent we) {
                try {
                    atualizarTabela();
                } catch (SQLException | ClassNotFoundException | ConnectException ex) {
                    JOptionPane.showMessageDialog(null,"Falha na atualização da tabela");
                    Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent we) {
                try {
                    atualizarTabela();
                } catch (SQLException | ClassNotFoundException | ConnectException ex) {
                    JOptionPane.showMessageDialog(null,"Falha na atualização da tabela");
                    Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void windowIconified(WindowEvent we) {
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
            }

            @Override
            public void windowActivated(WindowEvent we) {
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
            }
            };
            telaUsuario.addWindowListener(listener);
            telaUsuario.setVisible(true);
    }//GEN-LAST:event_adicionarActionPerformed

    private void trocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trocarSenhaActionPerformed
        TelaAlterarSenha telaSenha= new TelaAlterarSenha(this.login);
        telaSenha.setVisible(true);
    }//GEN-LAST:event_trocarSenhaActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        if(0==JOptionPane.showConfirmDialog(null, "Deseja realmente deletar o usuario selecionado?", "", JOptionPane.YES_NO_OPTION)){
            try {
                ConexaoBancoDados.UsuarioDAO.deletarUsuario(this.login);
                Log.salvarLogUsuario(this.login, "Deletou");
                atualizarTabela();
            } catch (SQLException | ClassNotFoundException | ConnectException ex) {
                Logger.getLogger(TelaGerenciarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Falha na atualização da tabela");
            }
        }
    }//GEN-LAST:event_excluirActionPerformed

    private void tabelaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaUsuariosMouseClicked
        int row = tabelaUsuarios.rowAtPoint(evt.getPoint());
        this.login=tabelaUsuarios.getModel().getValueAt(row,1).toString();
    }//GEN-LAST:event_tabelaUsuariosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JButton excluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaUsuarios;
    private javax.swing.JButton trocarSenha;
    // End of variables declaration//GEN-END:variables
}