/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.Mensalidade;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;

/**
 *
 * @author Michel
 */
public class DMensalidade extends javax.swing.JDialog {

    Calendar cal;
    Mensalidade m;
    Configuracoes config;
    
    private String jan;
    private String fev;
    private String mar;
    private String abr;
    private String mai;
    private String jun;
    private String jul;
    private String ago;
    private String set;
    private String out;
    private String nov;
    private String dez;

    public String getJan() {
        return jan;
    }

    public void setJan(String jan) {
        this.jan = jan;
    }

    public String getFev() {
        return fev;
    }

    public void setFev(String fev) {
        this.fev = fev;
    }

    public String getMar() {
        return mar;
    }

    public void setMar(String mar) {
        this.mar = mar;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getMai() {
        return mai;
    }

    public void setMai(String mai) {
        this.mai = mai;
    }

    public String getJun() {
        return jun;
    }

    public void setJun(String jun) {
        this.jun = jun;
    }

    public String getJul() {
        return jul;
    }

    public void setJul(String jul) {
        this.jul = jul;
    }

    public String getAgo() {
        return ago;
    }

    public void setAgo(String ago) {
        this.ago = ago;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getNov() {
        return nov;
    }

    public void setNov(String nov) {
        this.nov = nov;
    }

    public String getDez() {
        return dez;
    }

    public void setDez(String dez) {
        this.dez = dez;
    }
    
    
    /**
     * Creates new form DMensalidade
     */
    public DMensalidade(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ocultaTex(true);
        preencheCampos();
    }
    
    public void ocultaTex(boolean valor){
        txtData.setVisible(valor);
        txtCodMedium.setVisible(valor);
        txtCodUser.setVisible(valor);
    }
    
    
    public void recebeInfo(int idMedium, int codUsuario, String medium){
        m = new Mensalidade();
        
        cal = new GregorianCalendar();
        int ano = cal.get(Calendar.YEAR);
        
        txtCodMedium.setText(String.valueOf(idMedium));
        txtCodUser.setText(String.valueOf(codUsuario));
        labelNomeMedium.setText(medium);
        
        m.setCodMedium(idMedium);
        m.setCodUsuario(codUsuario);
        m.setAno(ano);
        m.verificaAno();
        m.consultaMesesPagos(cJan, cFev, cMar, cAbr, 
            cMai, cJun, cJul, cAgo, cSet, cOut, 
            cNov, cDez);
    }
    
    public void preencheCampos(){
        cal = new GregorianCalendar();
        config = new Configuracoes();
        txtAno.setText(String.valueOf(cal.get(Calendar.YEAR)));

        txtDataPagamento.setText(config.retornaData());
        
        txtValor.setText("20,00");
    }
    
    public void atualizaDataHoje(){
        cal = new GregorianCalendar();
        String mesLongo = null;
        
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        
        if(mes < 10){
            mesLongo = "0" + mes;
        }else{
            mesLongo = String.valueOf(mes);
        }
        
        String dataCompleta = dia + "/" + mesLongo + "/" + ano;
        txtData.setText(dataCompleta);        
//        String dataSQL = ano + "-" + mesLongo + "-" + dia;
//        txtDataCadastro.setText(dataSQL);        
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
        jLabel1 = new javax.swing.JLabel();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        panelAno = new javax.swing.JPanel();
        txtAno = new javax.swing.JTextField();
        panelValor = new javax.swing.JPanel();
        txtValor = new javax.swing.JTextField();
        labelMedium = new javax.swing.JLabel();
        labelNomeMedium = new javax.swing.JLabel();
        cJan = new javax.swing.JCheckBox();
        cMai = new javax.swing.JCheckBox();
        cSet = new javax.swing.JCheckBox();
        cFev = new javax.swing.JCheckBox();
        cJun = new javax.swing.JCheckBox();
        cOut = new javax.swing.JCheckBox();
        cMar = new javax.swing.JCheckBox();
        cJul = new javax.swing.JCheckBox();
        cNov = new javax.swing.JCheckBox();
        cAbr = new javax.swing.JCheckBox();
        cAgo = new javax.swing.JCheckBox();
        cDez = new javax.swing.JCheckBox();
        labelDataPagamento = new javax.swing.JLabel();
        txtDataPagamento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("MENSALIDADE");

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtFinaceiro1.png"))); // NOI18N

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(labelTitulo)
                .addGap(106, 106, 106)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCodMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        panelAno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ANO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        txtAno.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtAno.setForeground(new java.awt.Color(0, 0, 153));
        txtAno.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelAnoLayout = new javax.swing.GroupLayout(panelAno);
        panelAno.setLayout(panelAnoLayout);
        panelAnoLayout.setHorizontalGroup(
            panelAnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAno, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelAnoLayout.setVerticalGroup(
            panelAnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        panelValor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "VALOR", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        txtValor.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtValor.setForeground(new java.awt.Color(0, 0, 153));
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelValorLayout = new javax.swing.GroupLayout(panelValor);
        panelValor.setLayout(panelValorLayout);
        panelValorLayout.setHorizontalGroup(
            panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelValorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtValor, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelValorLayout.setVerticalGroup(
            panelValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelValorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        labelMedium.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelMedium.setText("MÉDIUM:");

        labelNomeMedium.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelNomeMedium.setText("Aguardando nome...");

        cJan.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cJan.setForeground(new java.awt.Color(51, 51, 51));
        cJan.setText("JANEIRO");
        cJan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cJan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cJan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cJan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cJanActionPerformed(evt);
            }
        });

        cMai.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cMai.setForeground(new java.awt.Color(51, 51, 51));
        cMai.setText("MAIO");
        cMai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cMai.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cMai.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cMaiActionPerformed(evt);
            }
        });

        cSet.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cSet.setForeground(new java.awt.Color(51, 51, 51));
        cSet.setText("SETEMBRO");
        cSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cSet.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cSet.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cSetActionPerformed(evt);
            }
        });

        cFev.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cFev.setForeground(new java.awt.Color(51, 51, 51));
        cFev.setText("FEVEREIRO");
        cFev.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cFev.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cFev.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cFev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cFevActionPerformed(evt);
            }
        });

        cJun.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cJun.setForeground(new java.awt.Color(51, 51, 51));
        cJun.setText("JUNHO");
        cJun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cJun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cJun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cJun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cJunActionPerformed(evt);
            }
        });

        cOut.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cOut.setForeground(new java.awt.Color(51, 51, 51));
        cOut.setText("OUTUBRO");
        cOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cOut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cOutActionPerformed(evt);
            }
        });

        cMar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cMar.setForeground(new java.awt.Color(51, 51, 51));
        cMar.setText("MARÇO");
        cMar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cMar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cMar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cMar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cMarActionPerformed(evt);
            }
        });

        cJul.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cJul.setForeground(new java.awt.Color(51, 51, 51));
        cJul.setText("JULHO");
        cJul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cJul.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cJul.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cJul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cJulActionPerformed(evt);
            }
        });

        cNov.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cNov.setForeground(new java.awt.Color(51, 51, 51));
        cNov.setText("NOVEMBRO");
        cNov.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cNov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cNov.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cNov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cNovActionPerformed(evt);
            }
        });

        cAbr.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cAbr.setForeground(new java.awt.Color(51, 51, 51));
        cAbr.setText("ABRIL");
        cAbr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cAbr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cAbr.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cAbr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cAbrActionPerformed(evt);
            }
        });

        cAgo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cAgo.setForeground(new java.awt.Color(51, 51, 51));
        cAgo.setText("AGOSTO");
        cAgo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cAgo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cAgo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cAgo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cAgoActionPerformed(evt);
            }
        });

        cDez.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cDez.setForeground(new java.awt.Color(51, 51, 51));
        cDez.setText("DEZEMBRO");
        cDez.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cDez.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cDez.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cDez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDezActionPerformed(evt);
            }
        });

        labelDataPagamento.setText("Data de Pagamento: ");

        txtDataPagamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataPagamentoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDown, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cJan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cMai, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cFev)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cJun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cJul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cNov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cMar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cDez, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cAgo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cAbr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelMedium)
                                .addGap(18, 18, 18)
                                .addComponent(labelNomeMedium))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelDataPagamento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMedium)
                    .addComponent(labelNomeMedium))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataPagamento)
                    .addComponent(txtDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(panelAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cJan)
                                .addGap(18, 18, 18)
                                .addComponent(cMai)
                                .addGap(18, 18, 18)
                                .addComponent(cSet))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cAbr)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(cFev)
                                        .addGap(18, 18, 18)
                                        .addComponent(cJun)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cOut))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(cMar)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cJul)
                                                    .addComponent(cAgo))
                                                .addGap(18, 18, 18)
                                                .addComponent(cNov))
                                            .addComponent(cDez, javax.swing.GroupLayout.Alignment.TRAILING))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void cFevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cFevActionPerformed
        marcaPagto(cFev, 2);
        // TODO add your handling code here:
    }//GEN-LAST:event_cFevActionPerformed

    private void cNovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cNovActionPerformed
        marcaPagto(cNov, 11);
        // TODO add your handling code here:
    }//GEN-LAST:event_cNovActionPerformed

    private void cJanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cJanActionPerformed

        marcaPagto(cJan, 1);
        // TODO add your handling code here:
    }//GEN-LAST:event_cJanActionPerformed

    public void marcaPagto(JCheckBox mes, int codMes){
        m = new Mensalidade();
        
        if(mes.isSelected()){
            m.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
            m.setMes(codMes);
            m.setAno(Integer.valueOf(txtAno.getText()));
            m.setDataPagamento(txtDataPagamento.getText());
            m.setValor(txtValor.getText());
            m.marcaPagamento();
            
            m.consultaMesesPagos(cJan, cFev, cMar, cAbr, 
                cMai, cJun, cJul, cAgo, cSet, cOut, 
                cNov, cDez);            
            //insere o mes
        }else{
            m.setCodMedium(Integer.valueOf(txtCodMedium.getText()));
            m.setMes(codMes);
            m.setAno(Integer.valueOf(txtAno.getText()));
            m.setDataPagamento(txtDataPagamento.getText());
            m.setValor(txtValor.getText());
            m.desmarcaPagamento();
            m.consultaMesesPagos(cJan, cFev, cMar, cAbr, 
                cMai, cJun, cJul, cAgo, cSet, cOut, 
                cNov, cDez);   
            //exclui o mes
        }        
    }
    
    
    private void txtDataPagamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataPagamentoKeyReleased

        if(txtDataPagamento.getText().length() == 2){
            txtDataPagamento.setText(txtDataPagamento.getText() + "/");
        }
        if(txtDataPagamento.getText().length() == 5){
            txtDataPagamento.setText(txtDataPagamento.getText() + "/");
        }
        if(txtDataPagamento.getText().length() >= 10){
            cJan.requestFocus();
        }
    }//GEN-LAST:event_txtDataPagamentoKeyReleased

    private void cMarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cMarActionPerformed
        marcaPagto(cMar, 3);
        // TODO add your handling code here:
    }//GEN-LAST:event_cMarActionPerformed

    private void cAbrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cAbrActionPerformed
        marcaPagto(cAbr, 4);
        // TODO add your handling code here:
    }//GEN-LAST:event_cAbrActionPerformed

    private void cMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cMaiActionPerformed
        marcaPagto(cMai, 5);
        // TODO add your handling code here:
    }//GEN-LAST:event_cMaiActionPerformed

    private void cJunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cJunActionPerformed
        marcaPagto(cJun, 6);
        // TODO add your handling code here:
    }//GEN-LAST:event_cJunActionPerformed

    private void cJulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cJulActionPerformed
        marcaPagto(cJul, 7);
        // TODO add your handling code here:
    }//GEN-LAST:event_cJulActionPerformed

    private void cAgoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cAgoActionPerformed
        marcaPagto(cAgo, 8);
        // TODO add your handling code here:
    }//GEN-LAST:event_cAgoActionPerformed

    private void cSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cSetActionPerformed
        marcaPagto(cSet, 9);
        // TODO add your handling code here:
    }//GEN-LAST:event_cSetActionPerformed

    private void cOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cOutActionPerformed
        marcaPagto(cOut, 10);
        // TODO add your handling code here:
    }//GEN-LAST:event_cOutActionPerformed

    private void cDezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDezActionPerformed
        marcaPagto(cDez, 12);
        // TODO add your handling code here:
    }//GEN-LAST:event_cDezActionPerformed

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
            java.util.logging.Logger.getLogger(DMensalidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DMensalidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DMensalidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DMensalidade.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DMensalidade dialog = new DMensalidade(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JCheckBox cAbr;
    private javax.swing.JCheckBox cAgo;
    private javax.swing.JCheckBox cDez;
    private javax.swing.JCheckBox cFev;
    private javax.swing.JCheckBox cJan;
    private javax.swing.JCheckBox cJul;
    private javax.swing.JCheckBox cJun;
    private javax.swing.JCheckBox cMai;
    private javax.swing.JCheckBox cMar;
    private javax.swing.JCheckBox cNov;
    private javax.swing.JCheckBox cOut;
    private javax.swing.JCheckBox cSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelDataPagamento;
    private javax.swing.JLabel labelMedium;
    private javax.swing.JLabel labelNomeMedium;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelAno;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPanel panelValor;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtCodMedium;
    private javax.swing.JTextField txtCodUser;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDataPagamento;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
