/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Acesso;
import br.com.sistejm.classes.Configuracoes;
import java.awt.event.KeyEvent;

/**
 *
 * @author Michel
 */
public class DAcesso extends javax.swing.JDialog {

    Acesso acesso;
    Configuracoes config;
    private String nomeUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    /**
     * Creates new form DAcesso
     */
    public DAcesso(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ativaTexto(false);
        boasVindas();
    }
    
    public void ativaTexto(boolean valor){
        txtNomeAcesso.setVisible(valor);
        txtCodeUser.setVisible(valor);
        txtIdAcesso.setVisible(valor);
    }
    
    public void boasVindas(){
        acesso = new Acesso();
        campoTexto.setText("");
        acesso.listaAcessos(table);
        preencheCampo("Bem-vindo ao acesso.");
        preencheCampo("\n--------------------------------------");
        preencheCampo("\nEssa tela é apenas um informativo quanto à restrição de cada acesso.");
        preencheCampo("\nBasta apenas selecionar o acesso ao lado.");
    }
    
    public void mensagemPadrao(){
        campoTexto.setText("");
        preencheCampo("ACESSO - CONSULTAS");
        preencheCampo("--------------------------------------");        
    }
    
    public void preencheAcessoSelecionado(String texto){
        
        switch(texto){
            case "Administrador":
                mensagemPadrao();
                preencheCampo("Administrador.");
                preencheCampo("Acesso completo, a todas as funções do sistema.");
                break;
            case "Administração":
                mensagemPadrao();
                preencheCampo("Acesso administrativo, como:");
                preencheCampo("- Menu Administração (todos);");
                preencheCampo("- Menu Configurações:");
                preencheCampo("------ Usuários (próprio)");
                preencheCampo("------ Acesso");
                preencheCampo("------ Orixás");
                preencheCampo("------ Entidades");
                preencheCampo("------ Exu");
                preencheCampo("- Menu Relatórios:");
                preencheCampo("------ Médiuns");
                preencheCampo("------ Eventos");
                preencheCampo("------ Agenda");
                preencheCampo("------ Administração");
                preencheCampo("------ Agendamentos (Consultas)");
                break;
            case "Tesouraria":
                mensagemPadrao();
                preencheCampo("Acesso financeiros, como:");
                preencheCampo("- Menu Tesouraria (todos);");
                preencheCampo("- Menu Configurações:");
                preencheCampo("------ Usuários (próprio)");
                preencheCampo("------ Acesso");
                preencheCampo("------ Orixás");
                preencheCampo("------ Entidades");
                preencheCampo("------ Exu");
                preencheCampo("- Menu Relatórios:");
                preencheCampo("------ Médiuns");
                preencheCampo("------ Tesouraria");
                break;
            case "Biblioteca":
                mensagemPadrao();
                preencheCampo("Acesso de biblioteca, como:");
                preencheCampo("- Menu Biblioteca (todos);");
                preencheCampo("- Menu Configurações:");
                preencheCampo("------ Usuários (próprio)");
                preencheCampo("------ Acesso");
                preencheCampo("------ Orixás");
                preencheCampo("------ Entidades");
                preencheCampo("------ Exu");
                preencheCampo("- Menu Relatórios:");
                preencheCampo("------ Médiuns");
                preencheCampo("------ Livros");
                break;
            case "Usuário":
                mensagemPadrao();
                preencheCampo("Acesso básico, como:");
                preencheCampo("- Menu Administração:");
                preencheCampo("------ Médiuns (próprio)");
                preencheCampo("- Menu Configurações:");
                preencheCampo("------ Usuários (próprio)");
                preencheCampo("------ Acesso");
                preencheCampo("------ Orixás (consultas)");
                preencheCampo("------ Entidades (consultas)");
                preencheCampo("------ Exu (consultas)");
                preencheCampo("- Menu Relatórios:");
                preencheCampo("------ Médiuns (próprio)");
                preencheCampo("------ Tesouraria (própria mensalidade)");
                preencheCampo("------ Livros (próprio)");
                break;
            default:
                mensagemPadrao();
                preencheCampo("\nSelecione o acesso para saber suas limitações");
                break;
            
        }
        
    }
    
    public void preencheCampo(String texto){
        campoTexto.setLineWrap(true);
        campoTexto.append("\n" + texto);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        txtNomeAcesso = new javax.swing.JTextField();
        txtCodeUser = new javax.swing.JTextField();
        txtIdAcesso = new javax.swing.JTextField();
        panelDown = new javax.swing.JPanel();
        labelNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtAcesso = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("Acessos");

        txtNomeAcesso.setEditable(false);

        txtCodeUser.setEditable(false);

        txtIdAcesso.setEditable(false);

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(txtNomeAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIdAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtCodeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtIdAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addComponent(txtNomeAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelDown.setBackground(new java.awt.Color(255, 255, 255));
        panelDown.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        javax.swing.GroupLayout panelDownLayout = new javax.swing.GroupLayout(panelDown);
        panelDown.setLayout(panelDownLayout);
        panelDownLayout.setHorizontalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDownLayout.setVerticalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        labelNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelNome.setText("Acesso:");

        table.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        txtAcesso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtAcesso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcessoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAcessoKeyReleased(evt);
            }
        });

        campoTexto.setEditable(false);
        campoTexto.setColumns(20);
        campoTexto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoTexto.setRows(5);
        jScrollPane2.setViewportView(campoTexto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNome)
                        .addGap(18, 18, 18)
                        .addComponent(txtAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 55, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(txtAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        String selecao = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
        String selecaoId = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        txtAcesso.setText(selecao);
        txtIdAcesso.setText(selecaoId);
        preencheAcessoSelecionado(selecao);
        //        txtEvento.setText(table.getValueAt(1, 1).toString());
        // TODO add your handling code here:
    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        sairTecla(evt);
        String selecao = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
        txtAcesso.setText(selecao);
        // TODO add your handling code here:
    }//GEN-LAST:event_tableKeyPressed

    public void sairTecla(KeyEvent ev){
        if(ev.getKeyCode() == KeyEvent.VK_ESCAPE){
            sair();
        }
    }
    
    public void sair(){
        this.dispose();
    }
    
    
    
    private void txtAcessoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcessoKeyPressed

        sairTecla(evt);
        acesso = new Acesso();
        config = new Configuracoes();
        config.gravaAtividades("Acesso", this.nomeUsuario, "Consulta");

        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcessoKeyPressed

    private void txtAcessoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcessoKeyReleased
        acesso = new Acesso();
        if(txtAcesso.getText().length() > 0){
            acesso.buscaAcessos(table, txtAcesso);
        }else if(txtAcesso.getText().length() == 0){
            boasVindas();
        }else if(txtAcesso.getText().equals("")){
            //acesso.listaAcessos(table);
            boasVindas();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAcessoKeyReleased

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DAcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DAcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DAcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DAcesso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DAcesso dialog = new DAcesso(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea campoTexto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTop;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAcesso;
    private javax.swing.JTextField txtCodeUser;
    private javax.swing.JTextField txtIdAcesso;
    private javax.swing.JTextField txtNomeAcesso;
    // End of variables declaration//GEN-END:variables
}
