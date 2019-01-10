package br.com.sistejm.telas;

import br.com.sistejm.classes.Caboclos;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.Entidade;
import br.com.sistejm.classes.Ere;
import br.com.sistejm.classes.Exu;
import br.com.sistejm.classes.MediumCaboclo;
import br.com.sistejm.classes.MediumEntidade;
import br.com.sistejm.classes.MediumEre;
import br.com.sistejm.classes.MediumExu;
import br.com.sistejm.classes.MediumOrixa;
import br.com.sistejm.classes.Mediuns;
import br.com.sistejm.classes.Orixas;
import br.com.sistejm.classes.TipoOrixa;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class DOrixasMedium extends javax.swing.JDialog {

    private int codUser;
    private String nomeUser;
    private int codMedium;
    private String nomeMedium;
    private String selecao;

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }

    public String getNomeMedium() {
        return nomeMedium;
    }

    public void setNomeMedium(String nomeMedium) {
        this.nomeMedium = nomeMedium;
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

    public String getSelecao() {
        return selecao;
    }

    public void setSelecao(String selecao) {
        this.selecao = selecao;
    }

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

    /**
     * Creates new form DOrixasMedium
     */
    public DOrixasMedium(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ocultaText(true);
    }
    
    public void ocultaText(boolean valor){
        txtId.setVisible(valor);
        txtCodMedium.setVisible(valor);
        txtCodMediumSanto.setVisible(valor);
        txtIdTP.setVisible(valor);
    }
    
    public void recebeInfo(int idMedium, int codeUser, String nomeMedium){
        this.selecao = "orixas";
        this.codUser = codeUser;
        this.codMedium = idMedium;
        
        txtCodMedium.setText(String.valueOf(this.codMedium));
        labelNomeMedium.setText(nomeMedium);
        preparaTabelasMedium();
        tabInicial();
    }
    
    public void fechar(){
        this.setAutoRequestFocus(false);
        this.dispose();
    }
    
    public void tabInicial(){
        radioOrixas.setSelected(true);
        preparaTabelasMedium(); // Prepara a tabela do Médium com Orixás cadastrados.
        preparaCamposOrixas(); // Prepara os campos labels
        preparaTabelaOrixa(); // Prepara a tabela escolha do Orixá
        preencheTipoOrixa(); // Prepara a tabela do tipo de Orixa
        
        preparaCampoPesquisa();
        
    }

    
    public void preparaTabelasMedium(){
        
        if(!txtCodMedium.getText().equals("")){
            switch(this.selecao){
                case "orixas":
                    mo = new MediumOrixa();
                    mo.setCodMedium(this.codMedium);
                    mo.exibeTabMediumOriPorId(tabelaMediumOri);
                    break;
                case "caboclo":
                    mc = new MediumCaboclo();
                    mc.setCodMedium(this.codMedium);
                    mc.exibeTabMediumCabPorId(tabelaMediumOri);
                    break;
                case "entidade":
                    ment = new MediumEntidade();
                    ment.setCodMedium(this.codMedium);
                    ment.exibeTabMediumEntPorId(tabelaMediumOri);
                    break;
                case "ere":
                    mere = new MediumEre();
                    mere.setCodMedium(this.codMedium);
                    mere.exibeTabMediumErePorId(tabelaMediumOri);
                    break;
                case "exu":
                    mexu = new MediumExu();
                    mexu.setCodMedium(this.codMedium);
                    mexu.exibeTabMediumExuPorId(tabelaMediumOri);
                    break;
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Identificação do médium não localizada. Saia e tente novamente", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    public void preparaCampoPesquisa(){
        txtEntidade.setText("");
        txtEntidade.requestFocus();
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
        cab.preencheTabCaboclo(tabelaEscolha);
    }
    public void preparaTabelaEre(){
        ere = new Ere();
        ere.preencheTabEres(tabelaEscolha);
    }
    public void preparaTabelaExu(){
        exu = new Exu();
        exu.preencheTabExu(tabelaEscolha);
    }
    
    public void preparaCamposOrixas(){
        panelTipo.setVisible(true);
        labelSanto.setText("Orixá:");
    }
    public void preparaCamposCaboclo(){
        panelTipo.setVisible(false);
        this.setSelecao("caboclo");
        labelSanto.setText("Caboclo:");       
    }
    public void preparaCamposEntidade(){
        panelTipo.setVisible(false);
        this.setSelecao("entidade");
        labelSanto.setText("Entidade:");          
    }
    public void preencheTipoOrixa(){
        to = new TipoOrixa();
        to.preencheTabTipoOrixa(tabelaEscolhaTipo);
    }
    public void preparaCamposEre(){
        panelTipo.setVisible(false);
        this.setSelecao("ere");
        labelSanto.setText("Erê:");          
    }
    public void preparaCamposExu(){
        panelTipo.setVisible(false);
        this.setSelecao("exu");
        labelSanto.setText("Exu:");         
    }
    
    public void transformaLabelMaiuscula(JLabel label){
        label.getText().toUpperCase();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupEntidades = new javax.swing.ButtonGroup();
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
        scrollTabelaEscolha = new javax.swing.JScrollPane();
        tabelaEscolha = new javax.swing.JTable();
        panelTabela = new javax.swing.JPanel();
        labelMedium = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaMediumOri = new javax.swing.JTable();
        panelTipo = new javax.swing.JPanel();
        labelTipo = new javax.swing.JLabel();
        scrollTabelaEscolhaTipo = new javax.swing.JScrollPane();
        tabelaEscolhaTipo = new javax.swing.JTable();
        txtTipoOrixa = new javax.swing.JTextField();
        labelSanto = new javax.swing.JLabel();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCodMediumSanto = new javax.swing.JTextField();
        txtCodMedium = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        txtIdTP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        labelNomeMedium = new javax.swing.JLabel();
        labelMedium1 = new javax.swing.JLabel();
        txtEntidade = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        toolbar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(102, 102, 102)));
        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        groupEntidades.add(radioOrixas);
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

        groupEntidades.add(radioCaboclo);
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

        groupEntidades.add(radioEntidades);
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

        groupEntidades.add(radioEre);
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

        groupEntidades.add(radioExu);
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

        panelTabela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panelTabela.setForeground(new java.awt.Color(153, 153, 153));

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

        javax.swing.GroupLayout panelTabelaLayout = new javax.swing.GroupLayout(panelTabela);
        panelTabela.setLayout(panelTabelaLayout);
        panelTabelaLayout.setHorizontalGroup(
            panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelMedium, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelTabelaLayout.setVerticalGroup(
            panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelTipo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        labelTipo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTipo.setText("Tipo:");

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
                        .addGap(18, 18, 18)
                        .addComponent(txtTipoOrixa, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelTipoLayout.setVerticalGroup(
            panelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipo)
                    .addComponent(txtTipoOrixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTabelaEscolhaTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelSanto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelSanto.setText("Caboclo:");

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

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("MÉDIUNS - ORIXÁS E ENTIDADES");

        txtId.setEditable(false);
        txtId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtId.setToolTipText("txtId");

        txtCodMediumSanto.setEditable(false);
        txtCodMediumSanto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodMediumSanto.setToolTipText("codTabelaMaior");

        txtCodMedium.setEditable(false);
        txtCodMedium.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCodMedium.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodMedium.setToolTipText("codMedium");

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
        txtIdTP.setToolTipText("CodTipo");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtOrixas.png"))); // NOI18N

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCodMediumSanto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        labelNomeMedium.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelNomeMedium.setText("Aguardando nome...");

        labelMedium1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelMedium1.setText("MÉDIUM:");

        txtEntidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEntidadeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEntidadeKeyReleased(evt);
            }
        });

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelMedium1)
                                .addGap(18, 18, 18)
                                .addComponent(labelNomeMedium)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSanto)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(panelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addComponent(scrollTabelaEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMedium1)
                            .addComponent(labelNomeMedium))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelSanto)
                                    .addComponent(txtEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollTabelaEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(panelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioOrixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOrixasActionPerformed
        this.selecao = "orixas";
        preparaTabelasMedium(); // Prepara a tabela do Médium com Orixás cadastrados.
        preparaCamposOrixas(); // Prepara os campos labels
        preparaTabelaOrixa(); // Prepara a tabela escolha do Orixá
        preencheTipoOrixa(); // Prepara a tabela do tipo de Orixa
    }//GEN-LAST:event_radioOrixasActionPerformed

    private void radioCabocloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCabocloActionPerformed
        this.selecao = "caboclo";
        preparaTabelasMedium(); // Prepara a tabela dos caboclos do médium
        preparaCamposCaboclo(); // Prepara os campos labels
        preparaTabelaCaboclo(); // Prepara a tabela escolha  dos Caboclos
    }//GEN-LAST:event_radioCabocloActionPerformed

    private void radioEntidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEntidadesActionPerformed
        this.selecao = "entidade";
        preparaTabelasMedium();
        preparaCamposEntidade();
        preparaTabelaEntidade();
    }//GEN-LAST:event_radioEntidadesActionPerformed

    private void radioEreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEreActionPerformed
        this.selecao = "ere";
        preparaTabelasMedium();
        preparaCamposEre();
        preparaTabelaEre();
    }//GEN-LAST:event_radioEreActionPerformed

    private void radioExuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioExuActionPerformed
        this.selecao = "exu";
        preparaTabelasMedium();
        preparaCamposExu();
        preparaTabelaExu();
    }//GEN-LAST:event_radioExuActionPerformed

    private void tabelaEscolhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEscolhaMouseClicked

        ori = new Orixas();
        ent = new Entidade();
        cab = new Caboclos();
        ere = new Ere();
        exu = new Exu();
        
        String escolha = null;
        
        switch(this.selecao){
            case "orixas":
                    escolha = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
                    txtId.setText(String.valueOf(ori.retornaIdOrixa(escolha)));
                break;
            case "caboclo":
                    escolha = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
                    txtId.setText(String.valueOf(cab.retornaIdCaboclo(escolha)));
                break;
            case "entidade":
                    escolha = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
                    txtId.setText(String.valueOf(ent.retornaIdEntidade(escolha)));
                break;
            case "ere":
                    escolha = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
                    txtId.setText(String.valueOf(ere.retornaIdEre(escolha)));
                break;
            case "exu":
                    escolha = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
                    txtId.setText(String.valueOf(exu.retornaIdExu(escolha)));
                break;
        }
        txtEntidade.setText(escolha.toUpperCase());
//        tabelaEscolha.setVisible(false);
//        labelSantoSelect.setText(nomeOrixa);
    }//GEN-LAST:event_tabelaEscolhaMouseClicked

    private void tabelaMediumOriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMediumOriMouseClicked

        mo = new MediumOrixa();
        ori = new Orixas();
        to = new TipoOrixa();
        String codSanto = String.valueOf(tabelaMediumOri.getValueAt(tabelaMediumOri.getSelectedRow(), 0));
        String codTipoSanto = null;

        switch(this.selecao){
            case "orixas":
                codTipoSanto = String.valueOf(tabelaMediumOri.getValueAt(tabelaMediumOri.getSelectedRow(), 1));
                mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mo.setCodOrixa(ori.retornaIdOrixa(codSanto));
                mo.setCodTipo(to.retornaIdTipoOrixa(codTipoSanto));
                txtCodMediumSanto.setText(String.valueOf(mo.retornaIdMediumOrixa(codSanto)));
            break;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaMediumOriMouseClicked

    private void tabelaEscolhaTipoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEscolhaTipoMouseClicked
        to = new TipoOrixa();
        String nomeTipoOrixa = String.valueOf(tabelaEscolhaTipo.getValueAt(tabelaEscolhaTipo.getSelectedRow(), 0));

        txtIdTP.setText(String.valueOf(to.retornaIdTipoOrixa(nomeTipoOrixa)));
        txtTipoOrixa.setText(nomeTipoOrixa.toUpperCase());
//        tabelaEscolhaTipo.setVisible(false);

    }//GEN-LAST:event_tabelaEscolhaTipoMouseClicked

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed

        switch(this.selecao){
            case "orixas":
                mo = new MediumOrixa();
                if(txtCodMedium.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Selecione o Médium", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                if(txtId.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Selecione o Orixá", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                if(txtIdTP.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Selecione o tipo de Orixá", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
                mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mo.setCodOrixa(Integer.valueOf(txtId.getText()));
                mo.setCodTipo(Integer.valueOf(txtIdTP.getText()));
//                if(mo.verificaQuantidade() >= 4){

                    JOptionPane.showMessageDialog(null, mo.verificaQuantidade());
                    JOptionPane.showMessageDialog(null, "A PORRA DA QUANTIDADE É MAIOR QUE 4");
//                    if(!mo.verificaExistente()){
//                        mo.incluirMediumOrixa();
//                    }else{
//                        JOptionPane.showMessageDialog(null, "Dados já existente. Exclua para incluir outro.", "ERRO", JOptionPane.ERROR_MESSAGE);
//                    }
//                }
            mo.exibeTabMediumOriPorId(tabelaMediumOri);
            limpaCamposOrixa();
            break;
            
            case "caboclo":
            mc = new  MediumCaboclo();
            mc.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
            mc.setCodCaboclo(Integer.valueOf(txtId.getText()));
            if(!mc.verificaExistente()){
                mc.incluirMediumCaboclo();
            }else{
                JOptionPane.showMessageDialog(null, "O Médium já possui o caboclo selecionado. Exclua para incluir outro.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            mc.exibeTabMediumCabPorId(tabelaMediumOri);
            break;
            
            case "entidade":
            ment = new  MediumEntidade();
            ment.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
            ment.setCodEntidade(Integer.valueOf(txtId.getText()));
            if(!ment.verificaExistente()){
                ment.incluirMediumEntidade();
            }else{
                JOptionPane.showMessageDialog(null, "O Médium já possui a entidade selecionada. Exclua para incluir outro.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            ment.exibeTabMediumEntPorId(tabelaMediumOri);
            break;
            
            case "ere":
            mere = new  MediumEre();
            mere.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
            mere.setCodEre(Integer.valueOf(txtId.getText()));
            if(!mere.verificaExistente()){
                mere.incluirMediumEre();
            }else{
                JOptionPane.showMessageDialog(null, "O Médium já possui o Erê selecionado. Exclua para incluir outro.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            mere.exibeTabMediumErePorId(tabelaMediumOri);
            break;
            
            case "exu":
            mexu = new  MediumExu();
            mexu.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
            mexu.setCodExu(Integer.valueOf(txtId.getText()));
            if(!mexu.verificaExistente()){
                mexu.incluirMediumExu();
            }else{
                JOptionPane.showMessageDialog(null, "O Médium já possui o Exu selecionado. Exclua para incluir outro.", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
            mexu.exibeTabMediumExuPorId(tabelaMediumOri);
            break;
        }
    }//GEN-LAST:event_btSalvarActionPerformed

    public void limpaCamposOrixa(){
        txtEntidade.setText("");
        txtTipoOrixa.setText("");
        txtEntidade.requestFocus();
    }
    
    private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
        medium = new Mediuns();

        if(!txtCodMedium.getText().equals("")){
            if(JOptionPane.showConfirmDialog(null, "Confirma exclusão?", "Exclusão", JOptionPane.YES_NO_OPTION) == 0){
                switch(this.selecao){
                    case "orixas":
                        mo = new MediumOrixa();
                        mo.setIdMediumOrixa(Integer.valueOf(txtCodMediumSanto.getText()));
                        if(mo.excluirMediumOrixa()){
//                            mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                            mo.preencheTabMediumOri(tabelaMediumOri);
                        }
                    break;
                    case "entidade":
                        ment = new MediumEntidade();
                        ment.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                        ment.setCodEntidade(Integer.valueOf(txtCodMediumSanto.getText()));
                        if(ment.excluirMediumEntidade()){
                            ment.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                            ment.exibeTabMediumEntPorId(tabelaMediumOri);
                        }
                    break;
                    case "caboclo":
                        mc = new MediumCaboclo();
                        mc.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                        mc.setCodCaboclo(Integer.valueOf(txtCodMediumSanto.getText()));
                        if(mc.excluirMediumCaboclo()){
                            mc.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                            mc.exibeTabMediumCabPorId(tabelaMediumOri);
                        }
                    break;
                    case "ere":
                        mere = new MediumEre();
                        mere.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                        mere.setCodEre(Integer.valueOf(txtCodMediumSanto.getText()));
                        if(mere.excluirMediumEre()){
                            mere.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                            mere.exibeTabMediumErePorId(tabelaMediumOri);
                        }
                    break;
                    case "exu":
                        mexu = new MediumExu();
                        mexu.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                        mexu.setCodExu(Integer.valueOf(txtCodMediumSanto.getText()));
                        if(mexu.excluirMediumExu()){
                            mexu.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                            mexu.exibeTabMediumExuPorId(tabelaMediumOri);
                        }
                    break;
                }
                tabInicial();// fazer de acordo com a seleção
                // Preencher código para exclusão de todas as informações
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione o Médium para a exclusão.", "ERRO", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void txtEntidadeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntidadeKeyReleased

        switch(this.selecao){
            case "orixa":
                ori = new Orixas();
                ori.buscaTabOrixas(tabelaEscolha, txtEntidade);
                break;
            case "entidade":
                ent = new Entidade();
                ent.buscaTabEntidade(tabelaEscolha, txtEntidade);
                break;
            case "caboclo":
                cab = new Caboclos();
                cab.buscaTabCaboclo(tabelaEscolha, txtEntidade);
                break;
            case "ere":
                ere = new Ere();
                ere.buscaTabEres(tabelaEscolha, txtEntidade);
                break;
            case "exu":
                exu = new Exu();
                exu.buscaTabExus(tabelaEscolha, txtEntidade);
                break;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntidadeKeyReleased

    private void txtEntidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntidadeKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            switch(this.selecao){
                case "orixas":
                    ori = new Orixas();
                    ori.setOrixa(txtEntidade.getText());
                    if(!ori.verificaExistente()){
                        if(JOptionPane.showConfirmDialog(null, "Orixá não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                            ori.setOrixa(txtEntidade.getText());
                            ori.incluirOrixa();
                        }
                    }
                    ori.buscaTabOrixas(tabelaEscolha, txtEntidade);
                    break;
                case "entidade":
                    ent = new Entidade();
                    ent.setNome(txtEntidade.getText());
                    if(!ent.verificaExistente()){
                        if(JOptionPane.showConfirmDialog(null, "Entidade não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                            ent.setNome(txtEntidade.getText());
                            ent.incluirEntidade();
                        }
                    }
                    ent.buscaTabEntidade(tabelaEscolha, txtEntidade);
                    break;
                case "caboclo":
                    cab = new Caboclos();
                    cab.setNome(txtEntidade.getText());
                    if(!cab.verificaExistente()){
                        if(JOptionPane.showConfirmDialog(null, "Caboclo não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                            cab.setNome(txtEntidade.getText());
                            cab.incluirCaboclo();
                        }
                    }
                    cab.buscaTabCaboclo(tabelaEscolha, txtEntidade);
                    break;
                case "ere":
                    ere = new Ere();
                    ere.setNome(txtEntidade.getText());
                    if(!ere.verificaExistente()){
                        if(JOptionPane.showConfirmDialog(null, "Erê não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                            ere.setNome(txtEntidade.getText());
                            ere.incluirEre();
                        }
                    }
                    ere.buscaTabEres(tabelaEscolha, txtEntidade);
                    break;
                case "exu":
                    exu = new Exu();
                    exu.setNome(txtEntidade.getText());
                    if(!exu.verificaExistente()){
                        if(JOptionPane.showConfirmDialog(null, "Entidade não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                            ent.setNome(txtEntidade.getText());
                            ent.incluirEntidade();
                        }
                    }
                    exu.buscaTabExus(tabelaEscolha, txtEntidade);
                    break;
            }
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntidadeKeyPressed

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
            java.util.logging.Logger.getLogger(DOrixasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DOrixasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DOrixasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DOrixasMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DOrixasMedium dialog = new DOrixasMedium(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup groupEntidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JLabel labelMedium;
    private javax.swing.JLabel labelMedium1;
    private javax.swing.JLabel labelNomeMedium;
    private javax.swing.JLabel labelSanto;
    private javax.swing.JLabel labelTipo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTabela;
    private javax.swing.JPanel panelTipo;
    private javax.swing.JPanel panelTop;
    private javax.swing.JRadioButton radioCaboclo;
    private javax.swing.JRadioButton radioEntidades;
    private javax.swing.JRadioButton radioEre;
    private javax.swing.JRadioButton radioExu;
    private javax.swing.JRadioButton radioOrixas;
    private javax.swing.JScrollPane scrollTabelaEscolha;
    private javax.swing.JScrollPane scrollTabelaEscolhaTipo;
    private javax.swing.JTable tabelaEscolha;
    private javax.swing.JTable tabelaEscolhaTipo;
    private javax.swing.JTable tabelaMediumOri;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextField txtCodMedium;
    private javax.swing.JTextField txtCodMediumSanto;
    private javax.swing.JTextField txtEntidade;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdTP;
    private javax.swing.JTextField txtTipoOrixa;
    // End of variables declaration//GEN-END:variables
}
