/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Conexao;
import br.com.sistejm.classes.Mediuns;
import br.com.sistejm.classes.Orixas;
import br.com.sistejm.classes.Entidade;
import br.com.sistejm.classes.Ere;
import br.com.sistejm.classes.Exu;
import br.com.sistejm.classes.Caboclos;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.MediumCaboclo;
import br.com.sistejm.classes.MediumEntidade;
import br.com.sistejm.classes.MediumEre;
import br.com.sistejm.classes.MediumExu;
import br.com.sistejm.classes.MediumOrixa;
import br.com.sistejm.classes.TipoOrixa;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Michel
 */
public class DMediumEspiritos extends javax.swing.JDialog {

    private int codUser;
    private String nomeUser;
    private String selecao;
    private String pesquisa;
    
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    
    Mediuns medium;
    Orixas ori;
    Entidade ent;
    Caboclos cab;
    Ere ere;
    Exu exu;
    TipoOrixa to;
    MediumOrixa mo;
    MediumCaboclo mc;
    MediumEntidade ment;
    MediumEre mere;
    MediumExu mexu;
    Mediuns med;

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getSelecao() {
        return selecao;
    }

    public void setSelecao(String Selecao) {
        this.selecao = selecao;
    }
    
    public int getCodUser() {
        return codUser;
    }

    public void setCodUser(int codUser) {
        this.codUser = codUser;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }
    
    
    /**
     * Creates new form DMediumEspiritos
     */
    public DMediumEspiritos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        transformaLabelMaiuscula(labelMedium);
        boasVindas();
        tabInicial();
        ocultaText(true);
        ocultaTabelas(false);
        verificaRadioMarcado();
    }

    
    public void preencheTabTipoOrixa(){
        to = new TipoOrixa();
        to.preencheTabTipoOrixa(tabelaEscolhaTipo);
    }
    
    public void tabInicial(){
        radioOrixas.setSelected(true);
        preparaTabelaMediuns();
        preparaPreenchimentoOrixas();
        preparaCampoPesquisa();
        acertoTabelas(false);
        preencheTabTipoOrixa();
        
    }
    
    public void ocultaTabelas(boolean valor){
        tabelaEscolha.setVisible(valor);
        tabelaEscolhaTipo.setVisible(valor);
    }
    
    public void ocultaText(boolean valor){
        txtCodMedium.setVisible(valor);
        txtCodMediumSanto.setVisible(valor);
        txtId.setVisible(valor);
        txtIdTP.setVisible(valor);
    }
    
    public void preparaCampoPesquisa(){
        txtPesquisa.setText("");
        txtPesquisa.requestFocus();
        this.setPesquisa("nome");
    }
    public void acertoTabelas(boolean valor){
        tabelaMedium.setFocusable(valor);
        tabelaEscolha.setFocusable(valor);
        tabelaEscolhaTipo.setFocusable(valor);
        
        tabelaMedium.getTableHeader().setReorderingAllowed(valor);
        tabelaEscolha.getTableHeader().setReorderingAllowed(valor);
        tabelaEscolhaTipo.getTableHeader().setReorderingAllowed(valor);
        
    }
    
    public void recebeUsuario(int codUser){
        txtCodMediumSanto.setText(String.valueOf(codUser));
        this.setCodUser(codUser);
    }
    
    public void preparaTabelaMediuns(){
        medium = new Mediuns();
        medium.preencheTabMedium(tabelaMedium);
    }
    public void preparaTabelaOrixa(){
        ori = new Orixas();
        ori.preencheTabOrixas(tabelaEscolha);
    }
    public void preparaTabelaEntidade(){
        ent = new Entidade();
        ent.preencheTabEntidade(tabelaEscolha);
    }
    public void preparaTabelaCaboclo(){
        cab = new Caboclos();
        config = new Configuracoes();
        
        cab.preencheTabCaboclo(tabelaEscolha);
        if(txtPesquisa.getText().equals("")){
            config.limpaTabela(tabelaMediumOri);
        }else{
            cab.buscaTabCabocloPorNome(tabelaMediumOri, txtPesquisa.getText());
        }
    }
    public void preparaTabelaEre(){
        ere = new Ere();
        ere.preencheTabEres(tabelaEscolha);
    }
    public void preparaTabelaExu(){
        exu = new Exu();
        exu.preencheTabExu(tabelaEscolha);
    }
    
    public void preparaPreenchimentoOrixas(){
        panelTipo.setVisible(true);
        preparaTabelaOrixa();
//        this.setSelecao("orixas");
        labelSanto.setText("Orixá:");
    }
    public void preparaPreenchimentoCaboclo(){
        panelTipo.setVisible(false);
        preparaTabelaCaboclo();
        this.setSelecao("caboclo");
        labelSanto.setText("Caboclo:");       
    }
    public void preparaPreenchimentoEntidade(){
        panelTipo.setVisible(false);
        preparaTabelaEntidade();
        this.setSelecao("entidade");
        labelSanto.setText("Entidade:");          
    }
    
    public void preencheTipoOrixa(){
        to = new TipoOrixa();
        to.preencheTabTipoOrixa(tabelaEscolha);
        
        //
    }
    public void preparaPreenchimentoEre(){
        panelTipo.setVisible(false);
        preparaTabelaEre();
        this.setSelecao("ere");
        labelSanto.setText("Erê:");          
    }
    public void preparaPreenchimentoExu(){
        panelTipo.setVisible(false);
        preparaTabelaExu();
        this.setSelecao("exu");
        labelSanto.setText("Exu:");         
    }
    
    public void transformaLabelMaiuscula(JLabel label){
        label.getText().toUpperCase();
    }
    
    public void fechar(){
        this.dispose();
    }
    
    public void verificaRadioMarcado(){
        if(radioOrixas.isSelected()){
            this.selecao = "orixas";
            System.out.println(this.selecao + " marcado");
        }
    }
    
    public void boasVindas(){
        preencheCampo("Bem-vindo à inclusão de Orixás, Entidades, Caboclos, Erês e Exu");
        preencheCampo("===================================");
        preencheCampo("O objetivo deste é identificar melhor a incorporação, ou não, do médium.");
        preencheCampo("Fica melhor a organização de saídas e para outras informações.");
    }
    
    public void preencheCampo(String texto){
        campoTexto.setLineWrap(true);
        campoTexto.append("\n" + texto);
    }
    
    public void limpaCampos(){
//        txtNomeSanto.setText("");
        txtPesquisa.setText("");
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
        buttonPesquisa = new javax.swing.ButtonGroup();
        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCodMediumSanto = new javax.swing.JTextField();
        txtCodMedium = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        txtIdTP = new javax.swing.JTextField();
        txtPesquisa = new javax.swing.JTextField();
        labelPesquisa = new javax.swing.JLabel();
        toolbar = new javax.swing.JToolBar();
        radioOrixas = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        radioCaboclo = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        radioEntidades = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        radioEre = new javax.swing.JRadioButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        radioExu = new javax.swing.JRadioButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaMedium = new javax.swing.JTable();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        radioNome = new javax.swing.JRadioButton();
        radioMatricula = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        labelMedium = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMediumOri = new javax.swing.JTable();
        labelSanto = new javax.swing.JLabel();
        panelTipo = new javax.swing.JPanel();
        labelTipo = new javax.swing.JLabel();
        labelTipoSelect = new javax.swing.JLabel();
        scrollTabelaEscolhaTipo = new javax.swing.JScrollPane();
        tabelaEscolhaTipo = new javax.swing.JTable();
        btSeeTabTipoOrixa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();
        scrollTabelaEscolha = new javax.swing.JScrollPane();
        tabelaEscolha = new javax.swing.JTable();
        btSeeTabOrixas = new javax.swing.JButton();
        labelSantoSelect = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("MÉDIUNS - ESPIRITUAL");

        txtId.setEditable(false);
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setText("idSanto");
        txtId.setToolTipText("idSanto");

        txtCodMediumSanto.setEditable(false);
        txtCodMediumSanto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodMediumSanto.setText("mediumSanto");

        txtCodMedium.setEditable(false);
        txtCodMedium.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodMedium.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodMedium.setText("idMedium");
        txtCodMedium.setToolTipText("idMedium");

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

        txtIdTP.setEditable(false);
        txtIdTP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdTP.setText("idTPSanto");
        txtIdTP.setToolTipText("idTPSanto");

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodMediumSanto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCodMediumSanto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtPesquisa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPesquisa.setMinimumSize(new java.awt.Dimension(6, 25));
        txtPesquisa.setPreferredSize(new java.awt.Dimension(6, 25));
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        labelPesquisa.setText("Pesquisar:");

        toolbar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        buttonGroup.add(radioOrixas);
        radioOrixas.setSelected(true);
        radioOrixas.setFocusable(false);
        radioOrixas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radioOrixas.setLabel("   Orixás   ");
        radioOrixas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radioOrixas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOrixasActionPerformed(evt);
            }
        });
        toolbar.add(radioOrixas);
        toolbar.add(jSeparator1);

        buttonGroup.add(radioCaboclo);
        radioCaboclo.setFocusable(false);
        radioCaboclo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radioCaboclo.setLabel("   Caboclo   ");
        radioCaboclo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radioCaboclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCabocloActionPerformed(evt);
            }
        });
        toolbar.add(radioCaboclo);
        toolbar.add(jSeparator2);

        buttonGroup.add(radioEntidades);
        radioEntidades.setFocusable(false);
        radioEntidades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radioEntidades.setLabel("   Entidades   ");
        radioEntidades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radioEntidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEntidadesActionPerformed(evt);
            }
        });
        toolbar.add(radioEntidades);
        toolbar.add(jSeparator3);

        buttonGroup.add(radioEre);
        radioEre.setFocusable(false);
        radioEre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radioEre.setLabel("      Erê      ");
        radioEre.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radioEre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEreActionPerformed(evt);
            }
        });
        toolbar.add(radioEre);
        toolbar.add(jSeparator4);

        buttonGroup.add(radioExu);
        radioExu.setFocusable(false);
        radioExu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        radioExu.setLabel("     Exu     ");
        radioExu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        radioExu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioExuActionPerformed(evt);
            }
        });
        toolbar.add(radioExu);
        toolbar.add(jSeparator5);

        tabelaMedium.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabelaMedium.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaMedium.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabelaMedium.setRequestFocusEnabled(false);
        tabelaMedium.getTableHeader().setReorderingAllowed(false);
        tabelaMedium.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMediumMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaMedium);
        tabelaMedium.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

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

        buttonPesquisa.add(radioNome);
        radioNome.setSelected(true);
        radioNome.setText("Por Nome");
        radioNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNomeActionPerformed(evt);
            }
        });

        buttonPesquisa.add(radioMatricula);
        radioMatricula.setText("Por Matricula");
        radioMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMatriculaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jPanel1.setForeground(new java.awt.Color(153, 153, 153));

        labelMedium.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMedium.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMedium.setText("Aguardando seleção...");

        tabelaMediumOri.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        tabelaMediumOri.setForeground(new java.awt.Color(0, 0, 255));
        tabelaMediumOri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabelaMediumOri.setRequestFocusEnabled(false);
        tabelaMediumOri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMediumOriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaMediumOri);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMedium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelSanto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelSanto.setText("Caboclo:");

        panelTipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        labelTipo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTipo.setText("Tipo:");

        labelTipoSelect.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelTipoSelect.setText("Aguardando...");

        tabelaEscolhaTipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabelaEscolhaTipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaEscolhaTipo.setRequestFocusEnabled(false);
        tabelaEscolhaTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEscolhaTipoMouseClicked(evt);
            }
        });
        scrollTabelaEscolhaTipo.setViewportView(tabelaEscolhaTipo);

        btSeeTabTipoOrixa.setBackground(new java.awt.Color(255, 255, 255));
        btSeeTabTipoOrixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/search.png"))); // NOI18N
        btSeeTabTipoOrixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSeeTabTipoOrixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTipoLayout = new javax.swing.GroupLayout(panelTipo);
        panelTipo.setLayout(panelTipoLayout);
        panelTipoLayout.setHorizontalGroup(
            panelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTabelaEscolhaTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                    .addGroup(panelTipoLayout.createSequentialGroup()
                        .addComponent(labelTipo)
                        .addGap(29, 29, 29)
                        .addComponent(labelTipoSelect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(68, 68, 68)
                        .addComponent(btSeeTabTipoOrixa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTipoLayout.setVerticalGroup(
            panelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(labelTipoSelect)
                    .addComponent(btSeeTabTipoOrixa))
                .addGap(18, 18, 18)
                .addComponent(scrollTabelaEscolhaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        campoTexto.setEditable(false);
        campoTexto.setColumns(20);
        campoTexto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoTexto.setForeground(new java.awt.Color(0, 0, 255));
        campoTexto.setRows(5);
        jScrollPane2.setViewportView(campoTexto);

        tabelaEscolha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabelaEscolha.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaEscolha.setRequestFocusEnabled(false);
        tabelaEscolha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaEscolhaMouseClicked(evt);
            }
        });
        scrollTabelaEscolha.setViewportView(tabelaEscolha);

        btSeeTabOrixas.setBackground(new java.awt.Color(255, 255, 255));
        btSeeTabOrixas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/search.png"))); // NOI18N
        btSeeTabOrixas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSeeTabOrixasActionPerformed(evt);
            }
        });

        labelSantoSelect.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        labelSantoSelect.setText("Aguardando...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelPesquisa)
                                .addGap(18, 18, 18)
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(radioMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(radioNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(8, 8, 8))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(panelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSanto)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSantoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btSeeTabOrixas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)))
                                .addComponent(scrollTabelaEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPesquisa)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radioNome))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(radioMatricula)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSanto)
                            .addComponent(btSeeTabOrixas)
                            .addComponent(labelSantoSelect))
                        .addGap(18, 18, 18)
                        .addComponent(panelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(scrollTabelaEscolha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioOrixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOrixasActionPerformed
        preparaPreenchimentoOrixas();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioOrixasActionPerformed

    private void radioCabocloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCabocloActionPerformed
        this.selecao = "caboclo";
        preparaPreenchimentoCaboclo();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCabocloActionPerformed

    private void radioEntidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEntidadesActionPerformed
        this.selecao = "entidade";
        preparaPreenchimentoEntidade();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioEntidadesActionPerformed

    private void radioEreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEreActionPerformed
        this.selecao = "ere";
        preparaPreenchimentoEre();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioEreActionPerformed

    private void radioExuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioExuActionPerformed
        this.selecao = "exu";
        preparaPreenchimentoExu();
        // TODO add your handling code here:
    }//GEN-LAST:event_radioExuActionPerformed

    private void tabelaMediumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMediumMouseClicked

        mo = new MediumOrixa();
        cab = new Caboclos();
        ent = new Entidade();
        ere = new Ere();
        exu = new Exu();
        
//        String selecaoId = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 0));
        String selecaoMedium = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 0));
        String nomeMedium = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 2));

        med = new Mediuns();
        
        
        labelMedium.setText(nomeMedium.toUpperCase());
        txtPesquisa.setText(nomeMedium);
        txtCodMedium.setText(selecaoMedium);
        switch(this.selecao){
            case "orixas":
                mo.preencheTabMediumOriPorNome(tabelaMediumOri, txtPesquisa.getText());
                break;
            case "caboclo":
                cab.buscaTabCabocloPorNome(tabelaMediumOri, txtPesquisa.getText());
                break;
            case "entidade":
                ent.buscaTabEntidade(tabelaMediumOri, txtPesquisa);
                break;
            case "ere":
                ere.buscaTabEres(tabelaMediumOri, txtPesquisa);
                break;
            case "exu":
                exu.buscaTabExus(tabelaMediumOri, txtPesquisa);
                break;
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaMediumMouseClicked

    public boolean isCellEditable(int row, int col){
        return false;
    }
    
    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        mo = new MediumOrixa();
        cab = new Caboclos();
        ent = new Entidade();
        ere = new Ere();
        exu = new Exu();
        JOptionPane.showMessageDialog(null, this.selecao);
        
        switch(this.selecao){
            case "orixas":
                mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mo.setCodOrixa(Integer.valueOf(txtId.getText()));
                mo.setCodTipo(Integer.valueOf(txtIdTP.getText()));
                if(mo.incluirMediumOrixa()){
                    preencheCampo("Salvo com sucesso");
                    mo.preencheTabMediumOriPorNome(tabelaMediumOri, txtPesquisa.getText());
                }
                break;
            case "caboclo":
                mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mo.setCodOrixa(Integer.valueOf(txtId.getText()));
                if(cab.incluirCaboclo()){
                    preencheCampo("Salvo com sucesso");
                    cab.buscaTabCabocloPorNome(tabelaMediumOri, txtPesquisa.getText());
                }
                break;
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        medium = new Mediuns();
        mo = new MediumOrixa();
        
        
        if(!txtId.getText().equals("")){
            if(JOptionPane.showConfirmDialog(null, "Confirma exclusão?", "Exclusão", JOptionPane.YES_NO_OPTION) == 0){
                switch(this.selecao){
                    case "orixas":
                        mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                        mo.setCodOrixa(Integer.valueOf(txtCodMediumSanto.getText()));
                        if(mo.excluirMediumOrixa()){
                            preencheCampo("Excluído com sucesso!");
                            mo.preencheTabMediumOriPorNome(tabelaMediumOri, txtPesquisa.getText());                            
                        }
                        break;
                }
                tabInicial();
                // Preencher código para exclusão de todas as informações
            }
        }else{
            preencheCampo("\nSelecione o Médium para a exclusão.");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            medium = new Mediuns();
            medium.buscaTabMedium(tabelaMedium, this.pesquisa, txtPesquisa);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaKeyPressed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased
            medium = new Mediuns();
            
                if(txtPesquisa.getText().length() > 3){
                    if(this.getPesquisa().equals("nome")){
                        medium.buscaTabMedium(tabelaMedium, this.pesquisa, txtPesquisa);
                    }else if(this.getPesquisa().equals("matricula")){
                        medium.buscaTabMediumMatricula(tabelaMedium, txtPesquisa);
                    }
                }else if(txtPesquisa.getText().equals("")){
                    medium.preencheTabMedium(tabelaMedium);
                }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaKeyReleased

    private void radioMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMatriculaActionPerformed

        txtPesquisa.setText("TEJM-");
        txtPesquisa.requestFocus();
        this.setPesquisa("matricula");
        // TODO add your handling code here:
    }//GEN-LAST:event_radioMatriculaActionPerformed

    private void radioNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNomeActionPerformed
//        String selecaoOrixa = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
//        String nomeOrixa = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 2));
        txtPesquisa.setText("");
        txtPesquisa.requestFocus();
        this.setPesquisa("nome");
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNomeActionPerformed

    private void tabelaEscolhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEscolhaMouseClicked

        ori = new Orixas();
        String nomeOrixa = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
        
        txtId.setText(String.valueOf(ori.retornaIdOrixa(nomeOrixa)));
        labelSantoSelect.setText(nomeOrixa.toUpperCase());
        tabelaEscolha.setVisible(false);
//        labelSantoSelect.setText(nomeOrixa);

    }//GEN-LAST:event_tabelaEscolhaMouseClicked

    private void tabelaEscolhaTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEscolhaTipoMouseClicked
        to = new TipoOrixa();
        String nomeOrixa = String.valueOf(tabelaEscolhaTipo.getValueAt(tabelaEscolhaTipo.getSelectedRow(), 0));
        
        txtIdTP.setText(String.valueOf(to.retornaIdTipoOrixa(nomeOrixa)));
        labelTipoSelect.setText(nomeOrixa.toUpperCase());
        tabelaEscolhaTipo.setVisible(false);
        
    }//GEN-LAST:event_tabelaEscolhaTipoMouseClicked

    private void btSeeTabOrixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSeeTabOrixasActionPerformed

//        scrollTabelaEscolha.setVisible(true);
        tabelaEscolha.setVisible(true);
//        scrollTabelaEscolhaTipo.setVisible(false);
        tabelaEscolhaTipo.setVisible(false);
       
    }//GEN-LAST:event_btSeeTabOrixasActionPerformed

    private void btSeeTabTipoOrixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSeeTabTipoOrixaActionPerformed
//        tabelaEscolha.setVisible(false);
//        scrollTabelaEscolha.setVisible(false);
        scrollTabelaEscolhaTipo.setVisible(true);
        tabelaEscolhaTipo.setVisible(true);
    }//GEN-LAST:event_btSeeTabTipoOrixaActionPerformed

    private void tabelaMediumOriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMediumOriMouseClicked

        mo = new MediumOrixa();
        ori = new Orixas();
        String codSanto = String.valueOf(tabelaMediumOri.getValueAt(tabelaMediumOri.getSelectedRow(), 1));
        
        switch(this.selecao){
            case "orixas":
                txtCodMediumSanto.setText(String.valueOf(ori.retornaIdOrixa(codSanto)));
                break;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaMediumOriMouseClicked

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
            java.util.logging.Logger.getLogger(DMediumEspiritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DMediumEspiritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DMediumEspiritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DMediumEspiritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DMediumEspiritos dialog = new DMediumEspiritos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btSeeTabOrixas;
    private javax.swing.JButton btSeeTabTipoOrixa;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonPesquisa;
    private javax.swing.JTextArea campoTexto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JLabel labelMedium;
    private javax.swing.JLabel labelPesquisa;
    private javax.swing.JLabel labelSanto;
    private javax.swing.JLabel labelSantoSelect;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTipoSelect;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTipo;
    private javax.swing.JPanel panelTop;
    private javax.swing.JRadioButton radioCaboclo;
    private javax.swing.JRadioButton radioEntidades;
    private javax.swing.JRadioButton radioEre;
    private javax.swing.JRadioButton radioExu;
    private javax.swing.JRadioButton radioMatricula;
    private javax.swing.JRadioButton radioNome;
    private javax.swing.JRadioButton radioOrixas;
    private javax.swing.JScrollPane scrollTabelaEscolha;
    private javax.swing.JScrollPane scrollTabelaEscolhaTipo;
    private javax.swing.JTable tabelaEscolha;
    private javax.swing.JTable tabelaEscolhaTipo;
    private javax.swing.JTable tabelaMedium;
    private javax.swing.JTable tabelaMediumOri;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextField txtCodMedium;
    private javax.swing.JTextField txtCodMediumSanto;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdTP;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables
}
