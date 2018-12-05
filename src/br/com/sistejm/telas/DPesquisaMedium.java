/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Fotos;
import br.com.sistejm.classes.Logradouro;
import br.com.sistejm.classes.MediumCaboclo;
import br.com.sistejm.classes.MediumEntidade;
import br.com.sistejm.classes.MediumEre;
import br.com.sistejm.classes.MediumExu;
import br.com.sistejm.classes.MediumOrixa;
import br.com.sistejm.classes.Mediuns;
import br.com.sistejm.classes.Mensalidade;
import br.com.sistejm.classes.Telefones;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class DPesquisaMedium extends javax.swing.JDialog {

    Mediuns m;
    Fotos f;
    Telefones t;
    Logradouro l;
    Mensalidade mensal;
    MediumOrixa mo;
    MediumEntidade me;
    MediumEre ere;
    MediumExu exu;
    MediumCaboclo mc;
    DMediumGeral med;
    TelaInicial inicial;
    
    private int idMedium;
    private int idUser;
    private String selecao;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getSelecao() {
        return selecao;
    }

    public void setSelecao(String selecao) {
        this.selecao = selecao;
    }
    
    public int getIdMedium() {
        return idMedium;
    }

    public void setIdMedium(int idMedium) {
        this.idMedium = idMedium;
    }
    
    
    /**
     * Creates new form DPesquisaMedium
     */
    public DPesquisaMedium(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.selecao = "nome";
        bloqueiaButton(false);
        ocultaText(false);
        exibeTabMedium();
    }
    
    public void bloqueiaButton(boolean valor){
        btDesativar.setEnabled(valor);
    }
    
    public void ocultaText(boolean valor){
        txtMatricula.setVisible(valor);
        txtIdMedium.setVisible(valor);
    }
    
    public void exibeTabMedium(){
        m = new Mediuns();
        m.preencheTabMedium(tabelaMedium);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        labelPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMedium = new javax.swing.JTable();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        radioNome = new javax.swing.JRadioButton();
        radioMatricula = new javax.swing.JRadioButton();
        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btDesativar = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        txtDataEntrada = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtIdMedium = new javax.swing.JTextField();
        txtDataNascimento = new javax.swing.JTextField();
        txtIsento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        txtAtivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelPesquisa.setText("Digite sua pesquisa: ");

        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        tabelaMedium.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matrícula", "Nome"
            }
        ));
        tabelaMedium.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMediumMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMedium);

        panelDown.setBackground(new java.awt.Color(255, 255, 255));
        panelDown.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelVersao.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelVersao.setText("SISTEJM - Versão 1.0 - Interna");

        javax.swing.GroupLayout panelDownLayout = new javax.swing.GroupLayout(panelDown);
        panelDown.setLayout(panelDownLayout);
        panelDownLayout.setHorizontalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDownLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelVersao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDownLayout.setVerticalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelVersao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        buttonGroup.add(radioNome);
        radioNome.setSelected(true);
        radioNome.setText("Por nome");
        radioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomeActionPerformed(evt);
            }
        });

        buttonGroup.add(radioMatricula);
        radioMatricula.setText("por matrícula");
        radioMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMatriculaActionPerformed(evt);
            }
        });

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("PESQUISA DE MÉDIUNS");

        btDesativar.setBackground(new java.awt.Color(255, 255, 255));
        btDesativar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btDesativar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/sim.png"))); // NOI18N
        btDesativar.setText("Selecionar");
        btDesativar.setToolTipText("Selecionar");
        btDesativar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btDesativar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btDesativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDesativarActionPerformed(evt);
            }
        });

        btCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btCancelar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/cancelar.png"))); // NOI18N
        btCancelar.setText("Sair");
        btCancelar.setToolTipText("Cancelar");
        btCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCancelar.setMaximumSize(new java.awt.Dimension(51, 43));
        btCancelar.setMinimumSize(new java.awt.Dimension(51, 43));
        btCancelar.setPreferredSize(new java.awt.Dimension(51, 43));
        btCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btExcluir.setBackground(new java.awt.Color(255, 255, 255));
        btExcluir.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/botaoexcluir.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setToolTipText("Excluir");
        btExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDesativar)
                .addGap(10, 10, 10)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btDesativar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTopLayout.createSequentialGroup()
                        .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        txtDataEntrada.setEditable(false);

        txtMatricula.setEditable(false);

        txtIdMedium.setEditable(false);

        txtDataNascimento.setEditable(false);

        txtIsento.setEditable(false);

        txtObservacoes.setEditable(false);
        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane2.setViewportView(txtObservacoes);

        txtAtivo.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(radioMatricula)
                                    .addComponent(radioNome)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIsento, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtAtivo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 265, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPesquisa)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioMatricula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIsento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(txtAtivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btDesativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDesativarActionPerformed
        inicial = new TelaInicial();
        med = new DMediumGeral(inicial, false);
        med.recebeNomeMedium(txtPesquisa.getText(), txtMatricula.getText(), txtIdMedium.getText(), 
                String.valueOf(this.idUser));
//        med.setFocusableWindowState(true);
        med.setLocationRelativeTo(med);
        med.setVisible(true);
        fechar();
        
    }//GEN-LAST:event_btDesativarActionPerformed

    public void recebeIdUsuario(String coduser){
        this.idUser = Integer.valueOf(coduser);
    }
        
    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void tabelaMediumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMediumMouseClicked

//        m = new Mediuns();
        inicial = new TelaInicial();
        med = new DMediumGeral(inicial, false);
        String id = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 0));
        String matriculaMedium = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 1));
        String nomeMedium = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 2));
        
        txtPesquisa.setText(nomeMedium);
        txtIdMedium.setText(id);
        txtMatricula.setText(matriculaMedium);
        btDesativar.setEnabled(true);
        
//        this.idMedium = m.retornaIdMediumPorMatricula(matriculaMedium);
        
//        med.setLocationRelativeTo(inicial);
//        med.recebeNomeMedium(nomeMedium, matriculaMedium, id);
//        med.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaMediumMouseClicked

    private void radioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomeActionPerformed

        this.setSelecao("nome");
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNomeActionPerformed

    private void radioMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMatriculaActionPerformed

        this.setSelecao("matricula");
        // TODO add your handling code here:
    }//GEN-LAST:event_radioMatriculaActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased

        m = new Mediuns();
        
        m.buscaTabMedium(tabelaMedium, this.selecao, txtPesquisa);
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        m = new Mediuns();
        f = new Fotos();
        t = new Telefones();
        l = new Logradouro();
        mensal = new Mensalidade();
        mo = new MediumOrixa();
        me = new MediumEntidade();
        ere = new MediumEre();
        exu = new MediumExu();
        mc = new MediumCaboclo();        

        if(!txtIdMedium.getText().equals("")){
            if(JOptionPane.showConfirmDialog(null, "Deseja excluir? Toda as informações serão excluídas.", "EXCLUSÃO", JOptionPane.YES_NO_OPTION)== 0){
                m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
                f.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                t.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                l.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                mensal.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                mo.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                me.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                ere.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                exu.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                mc.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                
                f.excluirFoto();
                t.excluirTelefone();
                l.excluirEndereco();
                mensal.excluiMensalidade();
                mo.excluirOrixaMedium();
                me.excluirEntidadeMedium();
                m.excluirMedium();
                
                exibeTabMedium();
            }
            
        }
    }//GEN-LAST:event_btExcluirActionPerformed

    
    public void fechar(){
        this.dispose();
    }
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
            java.util.logging.Logger.getLogger(DPesquisaMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DPesquisaMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DPesquisaMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DPesquisaMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DPesquisaMedium dialog = new DPesquisaMedium(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btDesativar;
    private javax.swing.JButton btExcluir;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelPesquisa;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTop;
    private javax.swing.JRadioButton radioMatricula;
    private javax.swing.JRadioButton radioNome;
    private javax.swing.JTable tabelaMedium;
    private javax.swing.JTextField txtAtivo;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataNascimento;
    private javax.swing.JTextField txtIdMedium;
    private javax.swing.JTextField txtIsento;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
