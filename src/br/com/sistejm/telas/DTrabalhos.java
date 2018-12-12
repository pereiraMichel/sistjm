/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

/**
 *
 * @author Michel
 */
public class DTrabalhos extends javax.swing.JDialog {

    TelaInicial inicial;
//    DTrabalhoAtendimento tag;
//    DTrabalhoSaidas tsa;
    /**
     * Creates new form DTrabalhos
     */
    public DTrabalhos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        labelTipoTrab = new javax.swing.JLabel();
        radioAtendimento = new javax.swing.JRadioButton();
        radioSaida = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabdatasaida = new javax.swing.JTable();
        labelPeriodo = new javax.swing.JLabel();
        txtData1 = new javax.swing.JTextField();
        txtData2 = new javax.swing.JTextField();
        labelA = new javax.swing.JLabel();
        labelProtocolo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaTrabalhos = new javax.swing.JTable();
        labelData = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                .addComponent(labelVersao, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDownLayout.setVerticalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelVersao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTitulo.setText("TRABALHOS");

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

        btImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btImprimir.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/print.png"))); // NOI18N
        btImprimir.setText("Imprimir");
        btImprimir.setToolTipText("Imprimir");
        btImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btImprimir.setMaximumSize(new java.awt.Dimension(51, 43));
        btImprimir.setMinimumSize(new java.awt.Dimension(51, 43));
        btImprimir.setPreferredSize(new java.awt.Dimension(51, 43));
        btImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        labelTipoTrab.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTipoTrab.setText("Tipo de trabalho:");

        buttonGroup.add(radioAtendimento);
        radioAtendimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioAtendimento.setText("Atendimento | Agendamento");
        radioAtendimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAtendimentoActionPerformed(evt);
            }
        });

        buttonGroup.add(radioSaida);
        radioSaida.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioSaida.setText("Recolhimento | Confirmação | Saídas");
        radioSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSaidaActionPerformed(evt);
            }
        });

        tabdatasaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Datas", "Nome"
            }
        ));
        jScrollPane3.setViewportView(tabdatasaida);

        labelPeriodo.setText("Período:");

        labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelA.setText("a");

        labelProtocolo.setText("Protocolo");

        labelNome.setText("Médium | Nome");

        tabelaTrabalhos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Produto", "Quantidade"
            }
        ));
        jScrollPane4.setViewportView(tabelaTrabalhos);

        labelData.setText("Data selecionada");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelDown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTipoTrab)
                                .addGap(18, 18, 18)
                                .addComponent(radioAtendimento))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelPeriodo)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtData1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelData))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelA, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtData2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(labelProtocolo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelNome)))))
                        .addGap(18, 18, 18)
                        .addComponent(radioSaida)
                        .addGap(92, 92, 92))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labelTipoTrab))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioAtendimento)
                        .addComponent(radioSaida)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelPeriodo)
                        .addComponent(txtData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelA)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelData)
                    .addComponent(labelProtocolo)
                    .addComponent(labelNome))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioAtendimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAtendimentoActionPerformed

//        inicial = new TelaInicial();
//        
//        tag = new DTrabalhoAtendimento(inicial, false);
//        tag.setTitle("Trabalhos para Agendamento | Atendimento");
//        tag.setLocationRelativeTo(tag);
//        tag.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_radioAtendimentoActionPerformed

    private void radioSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSaidaActionPerformed
//        inicial = new TelaInicial();
//        
//        tsa = new DTrabalhoSaidas(inicial, false);
//        tsa.setTitle("Trabalhos para Recolhimentos | Confirmações | Saídas");
//        tsa.setLocationRelativeTo(tsa);
//        tsa.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_radioSaidaActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btImprimirActionPerformed

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
            java.util.logging.Logger.getLogger(DTrabalhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DTrabalhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DTrabalhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DTrabalhos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DTrabalhos dialog = new DTrabalhos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btImprimir;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelA;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPeriodo;
    private javax.swing.JLabel labelProtocolo;
    private javax.swing.JLabel labelTipoTrab;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTop;
    private javax.swing.JRadioButton radioAtendimento;
    private javax.swing.JRadioButton radioSaida;
    private javax.swing.JTable tabdatasaida;
    private javax.swing.JTable tabelaTrabalhos;
    private javax.swing.JTextField txtData1;
    private javax.swing.JTextField txtData2;
    // End of variables declaration//GEN-END:variables
}
