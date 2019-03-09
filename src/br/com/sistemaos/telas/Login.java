/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemaos.telas;

import br.com.sistemaos.dao.ModuloConexao;
import static br.com.sistemaos.telas.TelaPrincipal.desktop;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    // gera os formulários
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar() {
        String sql = "select * from tbusuarios where login = ? and senha = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_usuario.getText());
            pst.setString(2, txt_senha.getText());

            // executa a query - consulta
            rs = pst.executeQuery();

            if (rs.next()) {
                String perfil = rs.getString(6);
                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();// instancia objeto para poder usar
                    principal.setVisible(true);
                    TelaPrincipal.menuCadUsu.setEnabled(true);
                    TelaMenu menu = new TelaMenu();
                    desktop.add(menu);
                    menu.setVisible(true);
                    TelaMenu.btnUsuarios.setEnabled(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.lblUsuario.setForeground(Color.BLUE);
                    this.dispose(); // fecha a tela de login ao conecatar
                    conexao.close();
                } else {
                    TelaPrincipal principal = new TelaPrincipal();// instancia objeto para poder usar
                    principal.setVisible(true);
                    TelaMenu menu = new TelaMenu();
                    desktop.add(menu);
                    menu.setVisible(true);
                    TelaPrincipal.lblUsuario.setText(rs.getString(2));
                    TelaPrincipal.lblUsuario.setForeground(Color.red);
                    this.dispose(); // fecha a tela de login ao conecatar
                    conexao.close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos");
                txt_usuario.setText(null); // limpa a caixa de texto
                txt_senha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //inicia a tela
    public Login() {
        initComponents(); // inicia o formulário
        conexao = ModuloConexao.conector(); // concta ao bando de dados
        // System.out.println(conexao);
        if (conexao != null) {
            lbl_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistemaos/icones/dbok.png"))); // busca dinâminca de img
        } else {
            lbl_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistemaos/icones/dberro.png")));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        txt_senha = new javax.swing.JPasswordField();
        btn_login = new javax.swing.JButton();
        lbl_status = new javax.swing.JLabel();
        lb_fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login de Usuário");
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuário");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 80, 60, 30);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Senha");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 130, 50, 30);
        getContentPane().add(txt_usuario);
        txt_usuario.setBounds(150, 80, 210, 30);
        getContentPane().add(txt_senha);
        txt_senha.setBounds(150, 130, 210, 30);

        btn_login.setText("Login");
        btn_login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });
        getContentPane().add(btn_login);
        btn_login.setBounds(270, 170, 90, 31);

        lbl_status.setForeground(new java.awt.Color(255, 255, 255));
        lbl_status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistemaos/icones/dberro.png"))); // NOI18N
        getContentPane().add(lbl_status);
        lbl_status.setBounds(320, 250, 40, 40);

        lb_fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fundo-cinza-fundos.jpg"))); // NOI18N
        lb_fundo.setMaximumSize(new java.awt.Dimension(450, 300));
        lb_fundo.setMinimumSize(new java.awt.Dimension(450, 300));
        lb_fundo.setPreferredSize(new java.awt.Dimension(450, 300));
        getContentPane().add(lb_fundo);
        lb_fundo.setBounds(0, 0, 450, 320);

        setSize(new java.awt.Dimension(450, 348));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // ação ao clicar no botão
    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        logar();
    }//GEN-LAST:event_btn_loginActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_login;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lb_fundo;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JPasswordField txt_senha;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
