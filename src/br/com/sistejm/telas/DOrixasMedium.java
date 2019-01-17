package br.com.sistejm.telas;

import br.com.sistejm.classes.Caboclos;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.Constances;
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
        ocultaText(false);
        tabelaTeste.setVisible(false);
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
        labelNomeMedium.setText(nomeMedium.toUpperCase());
        preparaTabelasMedium();
        tabInicial();
        this.setTitle(Constances.TITULO_DORIXASMEDIUM);
    }
    
    public void fechar(){
        this.setAutoRequestFocus(false);
        this.dispose();
    }
    
    public void tabInicial(){
        
        preparaTabelasMedium();

        
//        radioOrixas.setSelected(true);
//        preparaTabelasMedium(); // Prepara a tabela do Médium com Orixás cadastrados.
//        preparaCamposOrixas(); // Prepara os campos labels
//        preparaTabelaOrixa(); // Prepara a tabela escolha do Orixá
//        preencheTipoOrixa(); // Prepara a tabela do tipo de Orixa
        
        preparaCampoPesquisa();
        
    }

    
    public void preparaTabelasMedium(){
        config = new Configuracoes();
        if(!txtCodMedium.getText().equals("")){
            switch(this.selecao){
                case Constances.SORI:
                    config.limpaTabela(tabelaEscolha);
                    mo = new MediumOrixa();
                    mo.setCodMedium(this.codMedium);
                    mo.exibeTabMediumOriPorId(tabelaMediumOri);
                    preparaCamposOrixas(); // Prepara os campos labels
                    preparaTabelaOrixa(); // Prepara a tabela escolha do Orixá
                    preencheTipoOrixa(); // Prepara a tabela do tipo de Orixa 
                    break;
                case Constances.SCAB:
                    config.limpaTabela(tabelaEscolha);
                    mc = new MediumCaboclo();
                    mc.setCodMedium(this.codMedium);
                    mc.exibeTabMediumCabPorId(tabelaMediumOri);
                    preparaCamposCaboclo(); // Prepara os campos labels
                    preparaTabelaCaboclo(); // Prepara tabela escolha
                    break;
                case Constances.SENT:
                    config.limpaTabela(tabelaEscolha);
                    ment = new MediumEntidade();
                    ment.setCodMedium(this.codMedium);
                    ment.exibeTabMediumEntPorId(tabelaMediumOri);
                    preparaCamposEntidade();
                    preparaTabelaEntidade();
                    break;
                case Constances.SERE:
                    config.limpaTabela(tabelaEscolha);
                    mere = new MediumEre();
                    mere.setCodMedium(this.codMedium);
                    mere.exibeTabMediumErePorId(tabelaMediumOri);
                    preparaCamposEre();
                    preparaTabelaEre();
                    break;
                case Constances.SEXU:
                    config.limpaTabela(tabelaEscolha);
                    mexu = new MediumExu();
                    mexu.setCodMedium(this.codMedium);
                    mexu.exibeTabMediumExuPorId(tabelaMediumOri);
                    preparaCamposExu();
                    preparaTabelaExu();
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
        cab.preencheTabCaboclo(tabelaTeste);
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
        scrollTabelaEscolha1 = new javax.swing.JScrollPane();
        tabelaEscolha = new javax.swing.JTable();
        scrollTabelaEscolha2 = new javax.swing.JScrollPane();
        tabelaTeste = new javax.swing.JTable();

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
                    .addComponent(scrollTabelaEscolhaTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelTipoLayout.createSequentialGroup()
                        .addComponent(labelTipo)
                        .addGap(18, 18, 18)
                        .addComponent(txtTipoOrixa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 62, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addComponent(txtCodMediumSanto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(82, 82, 82)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodMediumSanto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
        scrollTabelaEscolha1.setViewportView(tabelaEscolha);

        tabelaTeste.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabelaTeste.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabelaTeste.setRequestFocusEnabled(false);
        tabelaTeste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaTesteMouseClicked(evt);
            }
        });
        scrollTabelaEscolha2.setViewportView(tabelaTeste);

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
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelMedium1)
                                .addGap(18, 18, 18)
                                .addComponent(labelNomeMedium)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(scrollTabelaEscolha1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelSanto)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEntidade)))
                                .addGap(35, 35, 35)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollTabelaEscolha2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelMedium1)
                            .addComponent(labelNomeMedium))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelSanto)
                                    .addComponent(txtEntidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(scrollTabelaEscolha1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scrollTabelaEscolha2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioOrixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOrixasActionPerformed
        this.selecao = Constances.SORI;
        preparaTabelasMedium(); // Prepara a tabela do Médium com Orixás cadastrados.

    }//GEN-LAST:event_radioOrixasActionPerformed

    private void radioCabocloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCabocloActionPerformed
        this.selecao = Constances.SCAB;
        preparaTabelasMedium(); // Prepara a tabela dos caboclos do médium
    }//GEN-LAST:event_radioCabocloActionPerformed

    private void radioEntidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEntidadesActionPerformed
        this.selecao = Constances.SENT;
        preparaTabelasMedium();
    }//GEN-LAST:event_radioEntidadesActionPerformed

    private void radioEreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEreActionPerformed
        this.selecao = Constances.SERE;
        preparaTabelasMedium();
    }//GEN-LAST:event_radioEreActionPerformed

    private void radioExuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioExuActionPerformed
        this.selecao = Constances.SEXU;
        preparaTabelasMedium();
    }//GEN-LAST:event_radioExuActionPerformed

    private void tabelaMediumOriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMediumOriMouseClicked

        String codSanto = String.valueOf(tabelaMediumOri.getValueAt(tabelaMediumOri.getSelectedRow(), 0));
        String codTipoSanto = null;

        switch(this.selecao){
            case Constances.SORI:
                mo = new MediumOrixa();
                ori = new Orixas();
                to = new TipoOrixa();
                codTipoSanto = String.valueOf(tabelaMediumOri.getValueAt(tabelaMediumOri.getSelectedRow(), 1));
                mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                ori.setOrixa(codSanto);
                mo.setCodOrixa(ori.retornaIdOrixa());
                mo.setCodTipo(to.retornaIdTipoOrixa(codTipoSanto));
                txtCodMediumSanto.setText(String.valueOf(mo.retornaIdMediumOrixa()));
            break;
            case Constances.SCAB:
                mc = new MediumCaboclo();
                cab = new Caboclos();
                cab.setNome(codSanto);
                mc.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mc.setCodCaboclo(cab.retornaIdCaboclo());
                txtCodMediumSanto.setText(String.valueOf(mc.retornaIdMediumCaboclo(codSanto)));
            break;
            case Constances.SENT:
                ment = new MediumEntidade();
                ent = new Entidade();
                ent.setNome(codSanto);
                ment.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                ment.setCodEntidade(ent.retornaIdEntidade());
                txtCodMediumSanto.setText(String.valueOf(ment.retornaIdMediumEntidade()));
            break;
            case Constances.SERE:
                mere = new MediumEre();
                ere = new Ere();
                ere.setNome(codSanto);
                mere.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mere.setCodEre(ere.retornaIdEre());
                txtCodMediumSanto.setText(String.valueOf(mere.retornaIdMediumEre()));
            break;
            case Constances.SEXU:
                mexu = new MediumExu();
                exu = new Exu();
                exu.setNome(codSanto);
                mexu.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mexu.setCodExu(exu.retornaIdExu());
                txtCodMediumSanto.setText(String.valueOf(mexu.retornaIdMediumExu()));
            break;
        }
        
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
            case Constances.SORI:
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
                if(mo.verificaQuantidade() >= 4){
                    if(!mo.verificaExistente()){
                        mo.incluirMediumOrixa();
                    }else{
                        JOptionPane.showMessageDialog(null, "Dados já existente. Exclua para incluir outro.", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
                mo.exibeTabMediumOriPorId(tabelaMediumOri);
                limpaCamposOrixa();
                
            break;
            
            case Constances.SCAB:
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
            
            case Constances.SENT:
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
            
            case Constances.SERE:
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
            
            case Constances.SEXU:
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
                        mo.excluirMediumOrixa();

                        preparaTabelasMedium();
                    break;
                    case "entidade":
                        ment = new MediumEntidade();
                        ment.setIdMediumEntidade(Integer.valueOf(txtCodMediumSanto.getText()));
                        ment.excluirMediumEntidade();

                        preparaTabelasMedium();
                    break;
                    case "caboclo":
                        mc = new MediumCaboclo();
                        mc.setIdMediumCaboclo(Integer.valueOf(txtCodMediumSanto.getText()));
                        mc.excluirMediumCaboclo();

                        preparaTabelasMedium();
                    break;
                    case "ere":
                        mere = new MediumEre();
                        mere.setIdMediumEre(Integer.valueOf(txtCodMediumSanto.getText()));
                        mere.excluirMediumEre();

                        preparaTabelasMedium();
                    break;
                    case "exu":
                        mexu = new MediumExu();
                        mexu.setIdMediumExu(Integer.valueOf(txtCodMediumSanto.getText()));
                        mexu.excluirMediumExu();

                        preparaTabelasMedium();
                    break;
                }
                preparaCampoPesquisa();
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
                
                ori.setOrixa(txtEntidade.getText());
                if(!ori.verificaExistente()){
                    if(JOptionPane.showConfirmDialog(null, "Orixá não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                        ori.setOrixa(txtEntidade.getText());
                        ori.incluirOrixa();
                    }
                }
                break;
            case "entidade":
                ent = new Entidade();
                ent.buscaTabEntidade(tabelaEscolha, txtEntidade);
                
                ent.setNome(txtEntidade.getText());
                if(!ent.verificaExistente()){
                    if(JOptionPane.showConfirmDialog(null, "Entidade não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                        ent.setNome(txtEntidade.getText());
                        ent.incluirEntidade();
                    }
                }
                break;
            case "caboclo":
                cab = new Caboclos();
                cab.buscaTabCaboclo(tabelaEscolha, txtEntidade);
                
                cab.setNome(txtEntidade.getText());
                if(!cab.verificaExistente()){
                    if(JOptionPane.showConfirmDialog(null, "Caboclo(a) não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                        cab.setNome(txtEntidade.getText());
                        cab.incluirCaboclo();
                    }
                }
                break;
            case "ere":
                ere = new Ere();
                ere.buscaTabEres(tabelaEscolha, txtEntidade);
                
                ere.setNome(txtEntidade.getText());
                if(!ere.verificaExistente()){
                    if(JOptionPane.showConfirmDialog(null, "Erê não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                        ere.setNome(txtEntidade.getText());
                        ere.incluirEre();
                    }
                }
                break;
            case "exu":
                exu = new Exu();
                exu.buscaTabExus(tabelaEscolha, txtEntidade);
                
                exu.setNome(txtEntidade.getText());
                if(!exu.verificaExistente()){
                    if(JOptionPane.showConfirmDialog(null, "Exu/ Pomba(o)-gira não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                        exu.setNome(txtEntidade.getText());
                        exu.incluirExu();
                    }
                }
                break;
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntidadeKeyReleased

    private void txtEntidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEntidadeKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            switch(this.selecao){
                case Constances.SORI:
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
                case Constances.SENT:
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
                case Constances.SCAB:
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
                case Constances.SERE:
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
                case Constances.SEXU:
                    exu = new Exu();
                    exu.setNome(txtEntidade.getText());
                    if(!exu.verificaExistente()){
                        if(JOptionPane.showConfirmDialog(null, "Exu não existente. Deseja cadastrar ?", "ERRO", JOptionPane.YES_NO_OPTION) == 0){
                            ent.setNome(txtEntidade.getText());
                            ent.incluirEntidade();
                        }
                    }
                    exu.buscaTabExus(tabelaEscolha, txtEntidade);
                    break;
            }// fecha switch
        } //fecha if
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntidadeKeyPressed

    private void tabelaEscolhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaEscolhaMouseClicked

        String campo = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
        txtEntidade.setText(campo.toUpperCase());

        switch(this.selecao){
            case Constances.SORI:
                mo = new MediumOrixa();
                
                ori = new Orixas();
                ori.setOrixa(campo);
                txtId.setText(String.valueOf(ori.retornaIdOrixa())); // Pego o id do orixa

                mo.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mo.setCodOrixa(Integer.valueOf(txtId.getText()));
                mo.setCodTipo(Integer.valueOf(txtIdTP.getText()));
                
                if(JOptionPane.showConfirmDialog(null, "Confirma transação ?", "INCLUSÃO", JOptionPane.YES_NO_OPTION) == 0){
                    if(!mo.verificaExistente()){
                        mo.incluirMediumOrixa();
                        limpaCamposOrixa();
                    }else{
                        JOptionPane.showMessageDialog(null, "Já existente.");
                    }
                }
                preparaTabelasMedium();
//                ori.buscaTabOrixas(tabelaEscolha, txtEntidade);
                break;
                
            case Constances.SENT:
                ment = new MediumEntidade();
                
                ent = new Entidade();
                ent.setNome(campo);
                txtId.setText(String.valueOf(ent.retornaIdEntidade())); // Pego o id da entidade
                
                ment.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                ment.setCodEntidade(Integer.valueOf(txtId.getText()));
                
                if(JOptionPane.showConfirmDialog(null, "Confirma transação ?", "INCLUSÃO", JOptionPane.YES_NO_OPTION) == 0){
                    if(!ment.verificaExistente()){
                        ment.incluirMediumEntidade();
                        limpaCamposOrixa();
                    }else{
                        JOptionPane.showMessageDialog(null, "Já existente.");
                    }
                }
                preparaTabelasMedium();
        //        ent.buscaTabEntidade(tabelaEscolha, txtEntidade);
                break;
                
            case Constances.SCAB:
                mc = new MediumCaboclo();
                
                cab = new Caboclos();
                cab.setNome(campo);
                txtId.setText(String.valueOf(cab.retornaIdCaboclo())); // Pego o id do caboclo
                
                mc.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mc.setCodCaboclo(Integer.valueOf(txtId.getText()));
                
                if(JOptionPane.showConfirmDialog(null, "Confirma transação ?", "INCLUSÃO", JOptionPane.YES_NO_OPTION) == 0){
                    if(!mc.verificaExistente()){
                        mc.incluirMediumCaboclo();
                        limpaCamposOrixa();
                    }else{
                        JOptionPane.showMessageDialog(null, "Já existente.");
                    }
                }
                preparaTabelasMedium();
        //        cab.buscaTabCaboclo(tabelaEscolha, txtEntidade);
                break;
                
            case Constances.SERE:
                mere = new MediumEre();
                
                ere = new Ere();
                ere.setNome(campo);
                txtId.setText(String.valueOf(ere.retornaIdEre())); // Pego o id do erê
                
                mere.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mere.setCodEre(Integer.valueOf(txtId.getText()));

                if(JOptionPane.showConfirmDialog(null, "Confirma transação ?", "INCLUSÃO", JOptionPane.YES_NO_OPTION) == 0){
                    if(!mere.verificaExistente()){
                        mere.incluirMediumEre();
                        limpaCamposOrixa();
                    }else{
                        JOptionPane.showMessageDialog(null, "Já existente.");
                    }
                }
                preparaTabelasMedium();
        //        ere.buscaTabEres(tabelaEscolha, txtEntidade);
                break;
            case Constances.SEXU:
                mexu = new MediumExu();
                exu = new Exu();
                exu.setNome(campo);
                txtId.setText(String.valueOf(exu.retornaIdExu())); // Pego o id do erê
                
                mexu.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
                mexu.setCodExu(Integer.valueOf(txtId.getText()));
                
                if(JOptionPane.showConfirmDialog(null, "Confirma transação ?", "INCLUSÃO", JOptionPane.YES_NO_OPTION) == 0){
                    if(!mexu.verificaExistente()){
                        mexu.incluirMediumExu();
                        limpaCamposOrixa();
                    }else{
                        JOptionPane.showMessageDialog(null, "Já existente.");
                    }
                }
                preparaTabelasMedium();
        //        exu.buscaTabExus(tabelaEscolha, txtEntidade);
                break;
        }
        
    }//GEN-LAST:event_tabelaEscolhaMouseClicked

    public void incluiEntidadesMedium(){
        
    }
    
    private void tabelaTesteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaTesteMouseClicked
        String campo = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
        
        txtEntidade.setText(campo.toUpperCase());
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaTesteMouseClicked

    public void methodVazio(){
        String campo = String.valueOf(tabelaEscolha.getValueAt(tabelaEscolha.getSelectedRow(), 0));
        
        txtEntidade.setText(campo.toUpperCase());
        
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
    private javax.swing.JScrollPane scrollTabelaEscolha1;
    private javax.swing.JScrollPane scrollTabelaEscolha2;
    private javax.swing.JScrollPane scrollTabelaEscolhaTipo;
    private javax.swing.JTable tabelaEscolha;
    private javax.swing.JTable tabelaEscolhaTipo;
    private javax.swing.JTable tabelaMediumOri;
    private javax.swing.JTable tabelaTeste;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextField txtCodMedium;
    private javax.swing.JTextField txtCodMediumSanto;
    private javax.swing.JTextField txtEntidade;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdTP;
    private javax.swing.JTextField txtTipoOrixa;
    // End of variables declaration//GEN-END:variables
}
