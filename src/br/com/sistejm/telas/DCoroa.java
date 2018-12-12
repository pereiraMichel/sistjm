/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Coroa;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jxl.write.DateTime;
import org.hsqldb.Library;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Michel
 */
public class DCoroa extends javax.swing.JDialog {

    Calendar cal;
    Coroa c;
    /**
     * Creates new form DCoroa
     */
    public DCoroa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        preencheCampos();
        campoPeriodo(false);
    }
    
    public void preencheCampos(){
        cal = new GregorianCalendar();
        
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        
        txtMes.setText(String.valueOf(mes));
        txtAno.setText(String.valueOf(ano));
        
    }
    
    public void campoPeriodo(boolean valor){
        txtMesAno1.setEditable(valor);
        txtMesAno2.setEditable(valor);
    }
    
    public void exibeTabTodos(){
        c = new Coroa();
        c.preencheTabSaidasNConfirmadasGeral(tabMedSaida);
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
        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        txtData = new javax.swing.JTextField();
        txtCodUser = new javax.swing.JTextField();
        txtCodMedium = new javax.swing.JTextField();
        labelFotoCoroa = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        dataSelect = new com.toedter.calendar.JDateChooser();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabMedSaida = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabMedSaidaOrixa = new javax.swing.JTable();
        btColocar = new javax.swing.JButton();
        btTirar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        radioCorrente = new javax.swing.JRadioButton();
        radioTodos = new javax.swing.JRadioButton();
        calendario = new com.toedter.calendar.JCalendar();
        labelDataSaida = new javax.swing.JLabel();
        txtDataSaida = new javax.swing.JTextField();
        radioPeriodo = new javax.swing.JRadioButton();
        txtMesAno1 = new javax.swing.JTextField();
        txtMesAno2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("CORÔA");

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

        txtData.setEditable(false);

        txtCodUser.setEditable(false);
        txtCodUser.setText("codUser");

        txtCodMedium.setEditable(false);
        txtCodMedium.setText("codMedium");

        labelFotoCoroa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtCoroa.png"))); // NOI18N

        txtMes.setEditable(false);

        txtAno.setEditable(false);

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFotoCoroa)
                .addGap(18, 18, 18)
                .addComponent(labelTitulo)
                .addGap(66, 66, 66)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dataSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelFotoCoroa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dataSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

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

        tabMedSaida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Médium", "Mês Confirmação", "Tipo"
            }
        ));
        jScrollPane1.setViewportView(tabMedSaida);

        tabMedSaidaOrixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Médium", "Data"
            }
        ));
        jScrollPane2.setViewportView(tabMedSaidaOrixa);

        btColocar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/setaInsere16x16.png"))); // NOI18N
        btColocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btColocarActionPerformed(evt);
            }
        });

        btTirar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/setaTira16x16.png"))); // NOI18N

        jLabel1.setText("Médium");

        buttonGroup.add(radioCorrente);
        radioCorrente.setText("Do mês e ano corrente");
        radioCorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCorrenteActionPerformed(evt);
            }
        });

        buttonGroup.add(radioTodos);
        radioTodos.setText("Todos");
        radioTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTodosActionPerformed(evt);
            }
        });

        calendario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Calendário", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 14), new java.awt.Color(0, 0, 204))); // NOI18N
        calendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarioMouseClicked(evt);
            }
        });
        calendario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarioPropertyChange(evt);
            }
        });

        labelDataSaida.setText("Data de saída:");

        buttonGroup.add(radioPeriodo);
        radioPeriodo.setText("Por período (MM/YYYY)");
        radioPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPeriodoActionPerformed(evt);
            }
        });

        txtMesAno1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesAno1KeyReleased(evt);
            }
        });

        txtMesAno2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMesAno2KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(radioPeriodo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMesAno1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMesAno2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(radioTodos))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btColocar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btTirar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(radioCorrente))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelDataSaida)
                                .addGap(18, 18, 18)
                                .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(calendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(radioTodos)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelDataSaida)
                                    .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioCorrente)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(radioPeriodo)
                            .addComponent(txtMesAno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMesAno2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btColocar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btTirar)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btColocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btColocarActionPerformed

        java.util.Date g = dataSelect.getDate();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fsql = new SimpleDateFormat("yyyy-MM-dd");

        txtData.setText(f.format(g));
//        System.out.println("Primeiro: " + f.format(g));

//        JOptionPane.showMessageDialog(null, f.format(g));
        // TODO add your handling code here:
    }//GEN-LAST:event_btColocarActionPerformed

    private void calendarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarioMouseClicked

//        java.util.Date g = calendario.getDate();
//        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat fsql = new SimpleDateFormat("yyyy-MM-dd"); 
//        txtData.setText(f.format(g));
        // TODO add your handling code here:
    }//GEN-LAST:event_calendarioMouseClicked

    private void calendarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarioPropertyChange
        java.util.Date g = calendario.getDate();
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fsql = new SimpleDateFormat("yyyy-MM-dd"); 
        txtData.setText(f.format(g));
        txtDataSaida.setText(f.format(g));
// TODO add your handling code here:
    }//GEN-LAST:event_calendarioPropertyChange

    private void txtMesAno1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesAno1KeyReleased

        preencheBarra(txtMesAno1);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesAno1KeyReleased

    private void txtMesAno2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMesAno2KeyReleased

        preencheBarra(txtMesAno2);
        if(txtMesAno2.getText().length() >= 7){
            c = new Coroa();
            int mes1 = Integer.valueOf(txtMesAno1.getText().substring(0, 1));
            int ano1 = Integer.valueOf(txtMesAno1.getText().substring(3, 6));
            int mes2 = Integer.valueOf(txtMesAno2.getText().substring(0, 1));
            int ano2 = Integer.valueOf(txtMesAno2.getText().substring(3, 6));

            c.preencheTabSaidasPeriodo(tabMedSaida, mes1, ano1, mes2, ano2);
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesAno2KeyReleased

    private void radioPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPeriodoActionPerformed
        campoPeriodo(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_radioPeriodoActionPerformed

    private void radioCorrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCorrenteActionPerformed

        campoPeriodo(false);
        
        c = new Coroa();
        c.setMes(Integer.valueOf(txtMes.getText()));
        c.setAno(Integer.valueOf(txtAno.getText()));
        c.preencheTabSaidasMesAnoCorrente(tabMedSaida);
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCorrenteActionPerformed

    private void radioTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTodosActionPerformed

        campoPeriodo(false);
        exibeTabTodos();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioTodosActionPerformed

    public void preencheBarra(JTextField campo){
        if(campo.getText().length() > 2){
            campo.setText(campo.getText() + "/");
        }        
    }
    
    public void exibeMesAno(){
        cal = new GregorianCalendar();
        
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        
        c = new Coroa();
        
        c.setAno(ano);
        c.setMes(mes);
        
        
        //Preencherá tabela de datas a confirmar a saída, levando em consideração ao mês corrente
        
    }

    public void dataHoje(){
        cal = new GregorianCalendar();
        
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        
        txtData.setText(dia + "/" + mes + "/" + ano);
    }
    
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
            java.util.logging.Logger.getLogger(DCoroa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DCoroa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DCoroa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DCoroa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DCoroa dialog = new DCoroa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btColocar;
    private javax.swing.JButton btTirar;
    private javax.swing.ButtonGroup buttonGroup;
    private com.toedter.calendar.JCalendar calendario;
    private com.toedter.calendar.JDateChooser dataSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelDataSaida;
    private javax.swing.JLabel labelFotoCoroa;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTop;
    private javax.swing.JRadioButton radioCorrente;
    private javax.swing.JRadioButton radioPeriodo;
    private javax.swing.JRadioButton radioTodos;
    private javax.swing.JTable tabMedSaida;
    private javax.swing.JTable tabMedSaidaOrixa;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtCodMedium;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDataSaida;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtMesAno1;
    private javax.swing.JTextField txtMesAno2;
    // End of variables declaration//GEN-END:variables
}
