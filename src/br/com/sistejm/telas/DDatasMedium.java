/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Mediuns;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class DDatasMedium extends javax.swing.JDialog {

    Mediuns med;
    
    private String tipoBusca;
    private int coduser;

    public int getCoduser() {
        return coduser;
    }

    public void setCoduser(int coduser) {
        this.coduser = coduser;
    }
    
    public String getTipoBusca() {
        return tipoBusca;
    }

    public void setTipoBusca(String tipoBusca) {
        this.tipoBusca = tipoBusca;
    }
    
    
    
    /**
     * Creates new form DDatasMedium
     */
    public DDatasMedium(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpaCampos();
        preencheTabMedium();
        this.setTipoBusca("nome");
    }
    
    public void recebeUsuario(int user){
        this.setCoduser(user);
        txtCodUser.setText(String.valueOf(user));
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
        txtId = new javax.swing.JTextField();
        txtCodUser = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        labelPesquisa = new javax.swing.JLabel();
        radioMatricula = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaMedium = new javax.swing.JTable();
        radioNpome = new javax.swing.JRadioButton();
        txtPesquisa = new javax.swing.JTextField();
        panelAdm = new javax.swing.JPanel();
        txtFuncao = new javax.swing.JTextField();
        txtEntrada = new javax.swing.JFormattedTextField();
        labelFuncao = new javax.swing.JLabel();
        txtRecolhimento = new javax.swing.JFormattedTextField();
        labelEntrada = new javax.swing.JLabel();
        txtConfirmacao = new javax.swing.JFormattedTextField();
        labelRecolhimento = new javax.swing.JLabel();
        labelonfirmacao = new javax.swing.JLabel();
        label14Anos = new javax.swing.JLabel();
        txt14Anos = new javax.swing.JFormattedTextField();
        txt21Anos = new javax.swing.JFormattedTextField();
        label3Anos = new javax.swing.JLabel();
        label21Anos = new javax.swing.JLabel();
        label7Anos = new javax.swing.JLabel();
        txt3Anos = new javax.swing.JFormattedTextField();
        txt7Anos = new javax.swing.JFormattedTextField();
        txtMadrinha = new javax.swing.JTextField();
        txtPadrinho = new javax.swing.JTextField();
        txtBatismo = new javax.swing.JFormattedTextField();
        labelMadrinha = new javax.swing.JLabel();
        labelBatismo = new javax.swing.JLabel();
        labelPadrinho = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("jLabel1");

        txtId.setEditable(false);

        txtCodUser.setEditable(false);

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
        btSalvar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btSalvarKeyPressed(evt);
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
        btCancelar.setToolTipText("Sair");
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

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addGap(133, 133, 133)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTitulo)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelPesquisa.setText("Pesquisar:");

        radioMatricula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioMatricula.setText("Por Matrícula");
        radioMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMatriculaActionPerformed(evt);
            }
        });

        tabelaMedium.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaMedium.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMediumMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaMedium);

        radioNpome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        radioNpome.setSelected(true);
        radioNpome.setText("Por Nome");
        radioNpome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNpomeActionPerformed(evt);
            }
        });

        txtPesquisa.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtPesquisa.setMinimumSize(new java.awt.Dimension(6, 25));
        txtPesquisa.setPreferredSize(new java.awt.Dimension(6, 25));
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyReleased(evt);
            }
        });

        panelAdm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        txtFuncao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEntrada.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelFuncao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelFuncao.setText("Função:");

        txtRecolhimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtRecolhimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRecolhimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelEntrada.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEntrada.setText("Entrada:");

        txtConfirmacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtConfirmacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtConfirmacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelRecolhimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelRecolhimento.setText("Corôa:");

        labelonfirmacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelonfirmacao.setText("Confirmação:");

        label14Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label14Anos.setText("14 anos:");

        txt14Anos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txt14Anos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt14Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txt21Anos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txt21Anos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt21Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        label3Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label3Anos.setText("3 anos:");

        label21Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label21Anos.setText("21 anos:");

        label7Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label7Anos.setText("7 anos:");

        txt3Anos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txt3Anos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt3Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txt7Anos.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txt7Anos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt7Anos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtMadrinha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtPadrinho.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        txtBatismo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtBatismo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBatismo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelMadrinha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMadrinha.setText("Madrinha:");

        labelBatismo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelBatismo.setText("Batismo:");

        labelPadrinho.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelPadrinho.setText("Padrinho:");

        javax.swing.GroupLayout panelAdmLayout = new javax.swing.GroupLayout(panelAdm);
        panelAdm.setLayout(panelAdmLayout);
        panelAdmLayout.setHorizontalGroup(
            panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAdmLayout.createSequentialGroup()
                        .addComponent(labelEntrada)
                        .addGap(35, 35, 35)
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRecolhimento, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAdmLayout.createSequentialGroup()
                                .addComponent(labelFuncao)
                                .addGap(43, 43, 43)
                                .addComponent(txtFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAdmLayout.createSequentialGroup()
                                .addComponent(labelonfirmacao)
                                .addGap(18, 18, 18)
                                .addComponent(txtConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelRecolhimento)
                    .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label3Anos, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAdmLayout.createSequentialGroup()
                                .addComponent(label14Anos)
                                .addGap(111, 111, 111)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAdmLayout.createSequentialGroup()
                            .addComponent(txt3Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(201, 201, 201))
                        .addGroup(panelAdmLayout.createSequentialGroup()
                            .addGap(191, 191, 191)
                            .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(label21Anos)
                                .addComponent(label7Anos))
                            .addGap(40, 40, 40)
                            .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt21Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelAdmLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(txt7Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt14Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelAdmLayout.createSequentialGroup()
                            .addComponent(labelBatismo)
                            .addGap(29, 29, 29)
                            .addComponent(txtBatismo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAdmLayout.createSequentialGroup()
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMadrinha)
                            .addComponent(labelPadrinho))
                        .addGap(30, 30, 30)
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMadrinha, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelAdmLayout.setVerticalGroup(
            panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAdmLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEntrada)
                    .addComponent(labelFuncao)
                    .addComponent(txtEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRecolhimento)
                    .addComponent(txtRecolhimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelonfirmacao))
                .addGap(18, 18, 18)
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAdmLayout.createSequentialGroup()
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label3Anos)
                            .addComponent(txt3Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt7Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label7Anos))
                        .addGap(18, 18, 18)
                        .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label14Anos)
                            .addComponent(txt14Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt21Anos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(label21Anos))
                .addGap(18, 18, 18)
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBatismo)
                    .addComponent(txtBatismo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMadrinha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMadrinha, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(panelAdmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPadrinho)
                    .addComponent(txtPadrinho, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        campoTexto.setEditable(false);
        campoTexto.setColumns(20);
        campoTexto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campoTexto.setForeground(new java.awt.Color(0, 0, 255));
        campoTexto.setRows(5);
        jScrollPane5.setViewportView(campoTexto);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(radioMatricula)
                        .addGap(18, 18, 18)
                        .addComponent(radioNpome))
                    .addComponent(jScrollPane3)
                    .addComponent(panelAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelDown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelPesquisa)
                            .addComponent(radioMatricula)
                            .addComponent(radioNpome)
                            .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addGap(18, 18, 18)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        //        incluirMedium();
        // TODO add your handling code here:
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btSalvarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btSalvarKeyPressed

        //        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            //            incluirMedium();
            //        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btSalvarKeyPressed

    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        med = new Mediuns();
        if(!txtId.getText().equals("")){
            if(JOptionPane.showConfirmDialog(null, "Confirma exclusão? Tudo do médium será deletado.", "Exclusão", JOptionPane.YES_NO_OPTION) == 0){
                med.setIdMedium(Integer.valueOf(txtId.getText()));
                med.excluirMedium();
                limpaCampos();
                preencheTabMedium();
                // Preencher código para exclusão de todas as informações
            }
        }else{
            preencheCampo("Selecione o Médium para a exclusão.");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirActionPerformed

    public void fechar(){
        this.dispose();
    }
    
    public void limpaCampos(){
        txtPesquisa.setText("");
        txtEntrada.setText("");
        txtFuncao.setText("");
        txtRecolhimento.setText("");
        txtConfirmacao.setText("");
        this.setTipoBusca("nome");
        txtPesquisa.requestFocus();
    }
    
    public void preencheCampo(String texto){
        campoTexto.setLineWrap(true);
        campoTexto.setForeground(Color.BLUE);
        campoTexto.append("\n" + texto);
    }
    
    public void preencheTabMedium(){
        med = new Mediuns();
        med.preencheTabMedium(tabelaMedium);
    }
    
    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void radioMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMatriculaActionPerformed

        txtPesquisa.setText("TEJM-");
        txtPesquisa.requestFocus();
        this.setTipoBusca("matricula");

        // TODO add your handling code here:
    }//GEN-LAST:event_radioMatriculaActionPerformed

    private void tabelaMediumMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMediumMouseClicked
        String selecaoId = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 0));
        //        String selecaoMatrMedium = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 1));
        String selecaoMedium = String.valueOf(tabelaMedium.getValueAt(tabelaMedium.getSelectedRow(), 2));
        preencheCampo("\nMédium " + selecaoMedium + " selecionado.");
        txtId.setText(selecaoId);
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaMediumMouseClicked

    private void radioNpomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNpomeActionPerformed

        txtPesquisa.setText("");
        txtPesquisa.requestFocus();
        this.setTipoBusca("nome");

        // TODO add your handling code here:
    }//GEN-LAST:event_radioNpomeActionPerformed

    private void txtPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyReleased

        med = new Mediuns();
        med.buscaTabMedium(tabelaMedium, this.tipoBusca, txtPesquisa);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesquisaKeyReleased

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
            java.util.logging.Logger.getLogger(DDatasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DDatasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DDatasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DDatasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DDatasMedium dialog = new DDatasMedium(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel label14Anos;
    private javax.swing.JLabel label21Anos;
    private javax.swing.JLabel label3Anos;
    private javax.swing.JLabel label7Anos;
    private javax.swing.JLabel labelBatismo;
    private javax.swing.JLabel labelEntrada;
    private javax.swing.JLabel labelFuncao;
    private javax.swing.JLabel labelMadrinha;
    private javax.swing.JLabel labelPadrinho;
    private javax.swing.JLabel labelPesquisa;
    private javax.swing.JLabel labelRecolhimento;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JLabel labelonfirmacao;
    private javax.swing.JPanel panelAdm;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTop;
    private javax.swing.JRadioButton radioMatricula;
    private javax.swing.JRadioButton radioNpome;
    private javax.swing.JTable tabelaMedium;
    private javax.swing.JFormattedTextField txt14Anos;
    private javax.swing.JFormattedTextField txt21Anos;
    private javax.swing.JFormattedTextField txt3Anos;
    private javax.swing.JFormattedTextField txt7Anos;
    private javax.swing.JFormattedTextField txtBatismo;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JFormattedTextField txtConfirmacao;
    private javax.swing.JFormattedTextField txtEntrada;
    private javax.swing.JTextField txtFuncao;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMadrinha;
    private javax.swing.JTextField txtPadrinho;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JFormattedTextField txtRecolhimento;
    // End of variables declaration//GEN-END:variables
}
