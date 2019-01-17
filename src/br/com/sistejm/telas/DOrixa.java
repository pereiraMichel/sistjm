/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Caboclos;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.Orixas;
import br.com.sistejm.classes.Entidade;
import br.com.sistejm.classes.Ere;
import br.com.sistejm.classes.Exu;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class DOrixa extends javax.swing.JDialog {

    Orixas ori;
    Entidade ent;
    Exu exu;
    Ere ere;
    Caboclos cab;
    Configuracoes config;
    private String tipo;
    private String nomeUsuario;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Creates new form DialogOrixa
     */
    public DOrixa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ativaBotoes(true);
        this.isCellEditable(tabelaOrixa.getRowCount(), tabelaOrixa.getColumnCount());
        txtId.setVisible(true);
        
    }
    
    public void ativaBotoes(boolean valor){
        btSalvar.setVisible(valor);
        btExcluir.setVisible(valor);
        btCancelar.setVisible(valor);
        txtUsuario.setText(nomeUsuario);
    }
    
    public void tipoDialog(String tipo){
        switch(tipo){
            case "orixas":
                ori = new Orixas();
                ori.preencheTabOrixas(tabelaOrixa);
                this.setTipo(tipo);
                this.setTipo("orixas");
                preencheLabel("ORIXÁS");
                boasVindasOrixas();
                boasVindasPadrão();
                txtNome.setText("");
                txtNome.requestFocus();
                break;
            case "entidade": 
                ent = new Entidade();
                ent.preencheTabEntidade(tabelaOrixa);
                this.setTipo(tipo);
                preencheLabel("ENTIDADE");
                this.setTipo("entidade");
                boasVindasEntidade();
                boasVindasPadrão();
                txtNome.setText("");
                txtNome.requestFocus();
                break;
            case "exu":
                exu = new Exu();
                exu.preencheTabExu(tabelaOrixa);
                boasVindasExu();
                boasVindasPadrão();
                preencheLabel("EXU");
                this.setTipo("exu");
                txtNome.setText("");
                txtNome.requestFocus();
                break;
            case "ere":
                ere = new Ere();
                ere.preencheTabEres(tabelaOrixa);
                boasVindasEre();
                boasVindasPadrão();
                preencheLabel("ERÊ");
                this.setTipo("ere");
                txtNome.setText("");
                txtNome.requestFocus();
                break;
            case "caboclo":
                cab = new Caboclos();
                cab.preencheTabCaboclo(tabelaOrixa);
                boasVindasCaboclo();
                boasVindasPadrão();
                preencheLabel("CABOCLO");
                this.setTipo("caboclo");
                txtNome.setText("");
                txtNome.requestFocus();
                break;
        }
    }
    
    public void boasVindasPadrão(){
        preencheTexto("=========================================");
//        preencheTexto("Preencha o campo e digite:");
//        preencheTexto("<ENTER>, para incluir:");
//        preencheTexto("<F2>, para limpar o campo.");
//        preencheTexto("<F3>, para pesquisar o Orixá.");
//        preencheTexto("<F4>, para alterar o Orixá.");
//        preencheTexto("<F5>, para excluir o Orixá.");
    }
    
    public void boasVindasOrixas(){
        preencheTexto("Bem-vindo ao registro de Orixás");
    }
    public void boasVindasEntidade(){
        preencheTexto("Bem-vindo ao registro de Entidades das almas");
    }
    public void boasVindasExu(){
        preencheTexto("Bem-vindo ao registro de Exus");
    }
    public void boasVindasEre(){
        preencheTexto("Bem-vindo ao registro de Erês");
    }
    public void boasVindasCaboclo(){
        preencheTexto("Bem-vindo ao registro de Caboclos");
    }
    
    public void preencheLabel(String titulo){
        labelTitulo.setText(titulo);
        labelNome.setText("Nome: ");
        txtNome.setText("");
        txtNome.requestFocus();
    }
    
    public void preencheTexto(String texto){
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

        panelTopo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btCancelar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        btExcluir = new javax.swing.JButton();
        panelDown = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        labelVersao = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaOrixa = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTopo.setBackground(new java.awt.Color(255, 255, 255));
        panelTopo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("jLabel1");

        btCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btCancelar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/cancelar.png"))); // NOI18N
        btCancelar.setText("Sair");
        btCancelar.setToolTipText("Cancelar");
        btCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        btSalvar.setBackground(new java.awt.Color(255, 255, 255));
        btSalvar.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/fileSave.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setToolTipText("Salvar");
        btSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        txtId.setEditable(false);
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdKeyReleased(evt);
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

        javax.swing.GroupLayout panelTopoLayout = new javax.swing.GroupLayout(panelTopo);
        panelTopo.setLayout(panelTopoLayout);
        panelTopoLayout.setHorizontalGroup(
            panelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopoLayout.setVerticalGroup(
            panelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopoLayout.createSequentialGroup()
                .addGroup(panelTopoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 8, Short.MAX_VALUE))
            .addGroup(panelTopoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDown.setBackground(new java.awt.Color(255, 255, 255));
        panelDown.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtUsuario.setEditable(false);
        txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(0, 0, 204));
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setBorder(null);

        labelVersao.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelVersao.setText("SISTEJM - Versão 1.0 - Interna");

        javax.swing.GroupLayout panelDownLayout = new javax.swing.GroupLayout(panelDown);
        panelDown.setLayout(panelDownLayout);
        panelDownLayout.setHorizontalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDownLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelVersao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelDownLayout.setVerticalGroup(
            panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addComponent(labelVersao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelNome.setText("jLabel1");

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNomeKeyReleased(evt);
            }
        });

        tabelaOrixa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabelaOrixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaOrixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaOrixaMouseClicked(evt);
            }
        });
        tabelaOrixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaOrixaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaOrixa);

        campoTexto.setColumns(20);
        campoTexto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoTexto.setForeground(new java.awt.Color(0, 0, 204));
        campoTexto.setRows(5);
        jScrollPane2.setViewportView(campoTexto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTopo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelNome)
                        .addGap(18, 18, 18)
                        .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTopo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void fechar(){
        this.dispose();
    }
    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        sairTecla(evt);
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(!txtNome.getText().equals("")){
                registro(evt);
            }else{
                preencheTexto("\nO campo não pode ficar vazio.");
            }
        }

    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyReleased

        if(txtId.getText().length() > 0){
            switch(this.tipo){
                case "orixas":
                    preencheTexto("\nOrixá selecionado. Digite e clique <F4> para alterar.");
                    break;
                case "entidade":
                    preencheTexto("\nEntidade das almas selecionada. Digite e clique <F4> para alterar.");
                    break;
                case "exu":
                    preencheTexto("\nExu selecionado. Digite e clique <F4> para alterar.");
                    break;
                case "ere":
                    preencheTexto("\nErê selecionado. Digite e clique <F4> para alterar.");
                    break;
                case "caboclo":
                    preencheTexto("\nCaboclo selecionado. Digite e clique <F4> para alterar.");
                    break;
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdKeyReleased

    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    private void tabelaOrixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaOrixaMouseClicked

        String selecao = String.valueOf(tabelaOrixa.getValueAt(tabelaOrixa.getSelectedRow(), 0));
//        String selecaoId = String.valueOf(tabelaOrixa.getValueAt(tabelaOrixa.getSelectedRow(), 0));
        txtNome.setText(selecao);
//        txtId.setText(selecaoId); 
            switch(this.tipo){
                case "orixas":
                    ori = new Orixas();
                    ori.setOrixa(txtNome.getText());
                    int idOri = ori.retornaIdOrixa();
                    txtId.setText(String.valueOf(idOri));
                    break;
                case "entidade":
                    ent = new Entidade();
                    ent.setNome(txtNome.getText());
                    int idEnt = ent.retornaIdEntidade();
                    txtId.setText(String.valueOf(idEnt));
                    break;
                case "exu":
                    exu = new Exu();
                    exu.setNome(txtNome.getText());
                    int idExu = exu.retornaIdExu();
                    txtId.setText(String.valueOf(idExu));
                    break;
                case "ere":
                    ere = new Ere();
                    ere.setNome(txtNome.getText());
                    int idEre = ere.retornaIdEre();
                    txtId.setText(String.valueOf(idEre));
                    break;
                case "caboclo":
                    cab = new Caboclos();
                    cab.setNome(txtNome.getText());
                    int id = cab.retornaIdCaboclo();
                    txtId.setText(String.valueOf(id));
                    break;
            }


    }//GEN-LAST:event_tabelaOrixaMouseClicked

    private void tabelaOrixaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaOrixaKeyPressed

        sairTecla(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaOrixaKeyPressed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        if(!txtId.getText().equals("")){
            if(JOptionPane.showConfirmDialog(null, "Confirma exclusão ?", "Exclusão", JOptionPane.YES_NO_OPTION) == 0){
//                JOptionPane.showMessageDialog(null, this.tipo);
                switch(this.tipo){
                    case "orixas":
                        ori = new Orixas();
                        ori.setIdOrixa(Integer.valueOf(txtId.getText()));
                        ori.excluirOrixa();
                        limpaCampos();
                        ori.preencheTabOrixas(tabelaOrixa);
                    break;
                    case "entidade":
                        ent = new Entidade();
                        ent.setIdEntidade(Integer.valueOf(txtId.getText()));
                        ent.excluirEntidade();
                        limpaCampos();
                        ent.preencheTabEntidade(tabelaOrixa);
                    break;
                    case "exu":
                        exu = new Exu();
                        exu.setIdExu(Integer.valueOf(txtId.getText()));
                        exu.excluirExu();
                        limpaCampos();
                        exu.preencheTabExu(tabelaOrixa);
                    break;
                    case "ere":
                        ere = new Ere();
                        ere.setIdEre(Integer.valueOf(txtId.getText()));
                        ere.excluirEre();
                        limpaCampos();
                        ere.preencheTabEres(tabelaOrixa);
                    break;
                    case "caboclo":
                        cab = new Caboclos();
                        cab.setIdCaboclo(Integer.valueOf(txtId.getText()));
                        cab.excluirCaboclo();
                        limpaCampos();
                        cab.preencheTabCaboclo(tabelaOrixa);
                    break;
                }
            }
        }else{
            preencheTexto("\nSelecione para a exclusão");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        switch(tipo){
            case "orixas":
                registraOrixa();
                tipoDialog("orixas");
            break;
            case "entidade":
                registraEntidade();
                tipoDialog("entidade");
            break;
            case "exu":
                registraExu();
                tipoDialog("exu");
            break;
            case "ere":
                registraEre();
                tipoDialog("ere");
            break;
            case "caboclo":
                registraCaboclo();
                tipoDialog("caboclo");
            break;
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btSalvarActionPerformed

    private void txtNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyReleased

        switch(this.tipo){
            case "orixas":
                ori = new Orixas();
                ori.buscaTabOrixas(tabelaOrixa, txtNome);
            break;
            case "entidade":
                ent = new Entidade();
                ent.buscaTabEntidade(tabelaOrixa, txtNome);
            break;
            case "ere":
                exu = new Exu();
                exu.buscaTabExus(tabelaOrixa, txtNome);
            break;
            case "exu":
                ere = new Ere();
                ere.buscaTabEres(tabelaOrixa, txtNome);
            break;
            case "caboclo":
                cab = new Caboclos();
                cab.buscaTabCaboclo(tabelaOrixa, txtNome);
            break;
        }

    }//GEN-LAST:event_txtNomeKeyReleased

    public void sairTecla(KeyEvent ev){
        if(ev.getKeyCode() == KeyEvent.VK_ESCAPE){
            fechar();
        }
    }
    
    public void registro(KeyEvent ev){
        int code = ev.getKeyCode();
        if (code == KeyEvent.VK_ENTER){
            switch(this.tipo){
                case "orixas":
                    registraOrixa();
                    break;
                case "entidade":
                    registraEntidade();
                    break;
                case "exu":
                    registraExu();
                    break;
                case "ere":
                    registraEre();
                    break;
                case "caboclo":
                    registraCaboclo();
                    break;
            }
        }
    }
    
    public void limpaCampos(){
        txtId.setText("");
        txtNome.setText("");
        txtNome.requestFocus();
    }
    
    public void registraOrixa(){
        ori = new Orixas();
        config = new Configuracoes();
        if(txtId.getText().length() > 0){
            ori.setIdOrixa(Integer.valueOf(txtId.getText()));
            ori.setOrixa(txtNome.getText());
            if (ori.alterarOrixa()){
                preencheTexto("\nAlterado com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
                config.gravaAtividades("Alteração do Orixá " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                ori.preencheTabOrixas(tabelaOrixa);
                limpaCampos();
            }
        }else{
            ori.setOrixa(txtNome.getText());
            if(ori.incluirOrixa()){
                preencheTexto("\nIncluso com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
                ori.preencheTabOrixas(tabelaOrixa);
                config.gravaAtividades("Inclusão do Orixá " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                limpaCampos();
            }
        }
        
    }

    public void registraEntidade(){
        ent = new Entidade();
        config = new Configuracoes();
        if(txtId.getText().length() > 0){
            ent.setIdEntidade(Integer.valueOf(txtId.getText()));
            ent.setNome(txtNome.getText());
            if (ent.alterarEntidade()){
                preencheTexto("\nAlterado com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
                config.gravaAtividades("Alteração do Entidade das almas " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                ent.preencheTabEntidade(tabelaOrixa);
                limpaCampos();
            }
        }else{
            ent.setNome(txtNome.getText());
            if(ent.incluirEntidade()){
                preencheTexto("\nIncluso com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
                ent.preencheTabEntidade(tabelaOrixa);
                config.gravaAtividades("Inclusão do Entidade das almas " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                limpaCampos();
            }
        }
        
    }
    public void registraExu(){
        exu = new Exu();
        config = new Configuracoes();
        if(txtId.getText().length() > 0){
            exu.setIdExu(Integer.valueOf(txtId.getText()));
            exu.setNome(txtNome.getText());
            if (exu.alterarExu()){
                preencheTexto("\nExu alterado com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
                config.gravaAtividades("Alteração do Exu " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                exu.preencheTabExu(tabelaOrixa);
                limpaCampos();
            }
        }else{
            exu.setNome(txtNome.getText());
            if(exu.incluirExu()){
                preencheTexto("\nExu incluso com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
                exu.preencheTabExu(tabelaOrixa);
                config.gravaAtividades("Inclusão do Exu " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                limpaCampos();
            }
        }
    }
    public void registraEre(){
        ere = new Ere();
        config = new Configuracoes();
        if(txtId.getText().length() > 0){
            ere.setIdEre(Integer.valueOf(txtId.getText()));
            ere.setNome(txtNome.getText());
            if (ere.alterarEre()){
                preencheTexto("\nErê alterado com sucesso!");
                config.gravaAtividades("Alteração do Erê " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                ere.preencheTabEres(tabelaOrixa);
                limpaCampos();
            }
        }else{
            //System.out.println("Estou na inclusão");
            ere.setNome(txtNome.getText());
            if(ere.incluirEre()){
                preencheTexto("\nErê incluso com sucesso!");
                ere.preencheTabEres(tabelaOrixa);
                config.gravaAtividades("Inclusão do Erê " + txtNome.getText(), this.nomeUsuario, txtNome.getText());
                limpaCampos();
            }
        }
        
    }

    public void registraCaboclo(){
        cab = new Caboclos();
        config = new Configuracoes();
        if(txtId.getText().length() > 0){
            cab.setIdCaboclo(Integer.valueOf(txtId.getText()));
            cab.setNome(txtNome.getText());
            if (cab.alterarCaboclo()){
                preencheTexto("\nCaboclo alterado com sucesso!");
                config.gravaAtividades("Alteração de Caboclo", this.nomeUsuario, txtNome.getText());
                cab.preencheTabCaboclo(tabelaOrixa);
                limpaCampos();
            }
        }else{
            cab.setNome(txtNome.getText());
            if(cab.incluirCaboclo()){
                preencheTexto("\nCaboclo incluso com sucesso!");
                cab.preencheTabCaboclo(tabelaOrixa);
                config.gravaAtividades("Inclusão do Cabloco", this.nomeUsuario, txtNome.getText());
                limpaCampos();
            }
        }
        
    }
//    public void alteraCaboclo(){
//        cab = new Caboclos();
//        config = new Configuracoes();
//        if(txtId.getText().length() > 0){
//            cab.setIdCaboclo(Integer.valueOf(txtId.getText()));
//            cab.setNome(txtNome.getText());
//            if (cab.alterarCaboclo()){
//                preencheTexto("\nAlterado com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
//                config.gravaAtividades("Alteração de Caboclo", this.nomeUsuario, txtNome.getText());
//                cab.preencheTabCaboclo(tabelaOrixa);
//                limpaCampos();
//            }
//        }else{
//            preencheTexto("\nSelecione o Caboclo para alteração...");
//        }
//        
//    }
//    public void excluiCaboclo(){
//        cab = new Caboclos();
//        config = new Configuracoes();
//        if(txtId.getText().length() > 0){
//            cab.setIdCaboclo(Integer.valueOf(txtId.getText()));
//            cab.setNome(txtNome.getText());
//            if (cab.excluirCaboclo()){
//                preencheTexto("\nExcluído com sucesso! Clique <F2> para limpar ou <ESC> para sair.");
//                config.gravaAtividades("Exclusão do Caboclo", this.nomeUsuario, txtNome.getText());
//                cab.preencheTabCaboclo(tabelaOrixa);
//                limpaCampos();
//            }
//        }else{
//            preencheTexto("\nSelecione o Caboclo para exclusão...");
//        }
//        
//    }
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
            java.util.logging.Logger.getLogger(DOrixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DOrixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DOrixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DOrixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DOrixa dialog = new DOrixa(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btSalvar;
    private javax.swing.JTextArea campoTexto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTopo;
    private javax.swing.JTable tabelaOrixa;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
