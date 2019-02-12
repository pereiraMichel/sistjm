/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Conexao;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.Coroa;
import br.com.sistejm.classes.Fotos;
import br.com.sistejm.classes.Logradouro;
import br.com.sistejm.classes.Mediuns;
import br.com.sistejm.classes.Mensalidade;
import br.com.sistejm.classes.Telefones;
import br.com.sistejm.classes.Constances;
import br.com.sistejm.classes.Relatorios;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.util.ImageUtils;
import org.apache.xmlbeans.impl.jam.internal.elements.ClassImpl;

/**
 *
 * @author Michel
 */
public class DMediumGeral extends javax.swing.JDialog {

    Configuracoes config;
    Mediuns m;
    Telefones t;
    Fotos f;
    Conexao con;
    TelaInicial inicial;
    Calendar cal;
    DMensalidade mensal;
    DCoroacao coroa;
    Logradouro l;
    Coroa c;
    Mensalidade men;
    Relatorios r;
    
    private int isento;
    private int ativo;
    private String sexo;

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getIsento() {
        return isento;
    }

    public void setIsento(int isento) {
        this.isento = isento;
    }
    
    

    /**
     * Creates new form DMediumGeral
     */
    public DMediumGeral(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpaCampos();
        atualizaDataHoje();
        preencheCamposCidadeEstado();
        ocultaText(false);
        preencheTitulo();
        btImprimir.setVisible(false);
//        this.ativo = 1;
    }
    
    public void preencheTitulo(){
        this.setTitle(Constances.TITULO_DMEDIUMGERAL);
    }
    
    public void preencheCamposCidadeEstado(){
        txtCidade.setText("Rio de Janeiro");
        txtEstado.setText("RJ");
    }
    
    public void ocultaText(boolean valor){
        txtData.setVisible(valor);
        txtDataCadastro.setVisible(valor);
        txtIdMedium.setVisible(valor);
        txtCodeUser.setVisible(valor);
        txtDataNascSQL.setVisible(valor);
    }
    
    public void recebeNomeMedium(String nome, String matricula, String idMedium, String iduser, String ativo){

        this.setAutoRequestFocus(true);
        
        m = new Mediuns();
        txtNome.setText(nome);
        txtMatricula.setText(matricula);
        txtIdMedium.setText(idMedium);
        txtCodeUser.setText(iduser);
        
        cal = new GregorianCalendar();
        men = new Mensalidade();
        men.setAno(cal.get(Calendar.YEAR));
        men.setCodMedium(Integer.valueOf(idMedium));
        men.setCodUsuario(Integer.valueOf(iduser));
        men.verificaAno();
        
//        System.out.println(nome);
//        System.out.println(matricula);
//        System.out.println(idMedium);
//        System.out.println(iduser);
//        System.out.println(ativo);
        m.setMatricula(txtMatricula.getText());
        m.setIdMedium(Integer.valueOf(idMedium));
        m.exibeCamposMedium(txtIdMedium, txtNome, txtMatricula, checkIsento, txtDataNascimento, txtDataEntrada, 
                txtObservacoes, txtEndereco, txtBairro, txtCidade, txtEstado, radioSim, radioNao, radioM, radioF, 
                txtEmail, txtFuncao);
        
        if(checkIsento.isSelected()){
            this.isento = 1;
            btFinanceiro.setEnabled(false);

        }else{
            this.isento = 0;
            btFinanceiro.setEnabled(true);
        }
        
        if(ativo.equals("Sim")){
            this.ativo = 1;
        }else if(ativo.equals("Não")){
            this.ativo = 0;
        }
 
        exibeTelefones();
        exibeFoto();
        exibeUltimaCoroa();
        exibeUltimoPagamento();
        
    }
    
    public void exibeUltimaCoroa(){
        c = new Coroa();
        
        String dataUltCoroa = c.ultimaCoroa(Integer.valueOf(txtIdMedium.getText()));
        labelDataSaida.setText(dataUltCoroa);
        
    }
    public void exibeUltimoPagamento(){
        men = new Mensalidade();
        
        String dataUltPagamento = men.ultimoPagamento(Integer.valueOf(txtIdMedium.getText()));
        labelMesUltMensal.setText(dataUltPagamento);
        
    }
    
    public void alteraCamposMedium(){
        m = new Mediuns();
        
        m = new Mediuns();
        m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
        m.setDataNascimento(txtDataNascimento.getText());
        m.setCodUsuario(Integer.valueOf(txtCodeUser.getText()));
        m.setDataEntrada(txtDataEntrada.getText());
        m.setMatricula(txtMatricula.getText());
        m.setNomeMedium(txtNome.getText());
        m.setObservacoes(txtObservacoes.getText());
        m.setAtivo(this.ativo);
        m.setIsentoMensal(this.isento);
        m.setEmail(txtEmail.getText());
        m.setFuncao(txtFuncao.getText());
        if(radioM.isSelected()){
            m.setSexo("m");
        }else{
            m.setSexo("f");
        }
        
        
        if (m.alterarMedium()){
            m.exibeCamposMedium(txtIdMedium, txtNome, txtMatricula, checkIsento, txtDataNascimento, 
                    txtDataEntrada, txtObservacoes, txtEndereco, txtBairro, txtCidade, txtEstado, 
                    radioSim, radioNao, radioM, radioF, txtEmail, txtFuncao);
            if(radioSim.isSelected()){
                Color corVerde = new Color(0,153,0);
                labelAtivoSel.setForeground(corVerde);
                labelAtivoSel.setText("ATIVO");
            }else{
                labelAtivoSel.setForeground(Color.RED);
                labelAtivoSel.setText("INATIVO");
            }
        }
        alteraEndereco();

        
    }
    
    public void alteraEndereco(){

        l = new Logradouro();
        
        l.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
        l.setEndereco(txtEndereco.getText());
        l.setComplemento("");
        l.setBairro(txtBairro.getText());
        l.setCidade(txtCidade.getText());
        l.setEstado(txtEstado.getText());
        
        if (l.verificaEndereco()){
            l.alteraEndereco();
        }else{
            l.incluirEndereco();
        }
    }
    
    public void exibeFoto(){
        f = new Fotos();
        f.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                    
        
        if(f.retornaFoto() != null){
            File file = new File(f.retornaFoto());
    //                System.out.println("Ocorreu um problema: " + file);
                BufferedImage bufferedImage = null;  
                try {
                    bufferedImage = ImageIO.read(new File(file.getPath()));
                } catch (IOException e) {  
    //                JOptionPane.showMessageDialog(null, "Arquivo não suportável");  
                    System.out.println("Ocorreu um problema: " + e.getMessage());
                }  
                ImageIcon img = new ImageIcon(bufferedImage.getScaledInstance(200, 200, 200));
                labelFoto.setIcon(img);
        }else{
            //icon-people.png
            File file = new File("C:\\sistejm\\images\\icon-people.png");
                BufferedImage bi = null;  
                try {
                    bi = ImageIO.read(new File(file.getPath()));
                } catch (IOException e) {
                    System.out.println("Ocorreu um problema: " + e.getMessage());
                }  
                ImageIcon img = new ImageIcon(bi);
                labelFoto.setIcon(img);
        }
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
        txtDataCadastro.setText(dataCompleta);        
//        String dataSQL = ano + "-" + mesLongo + "-" + dia;
//        txtDataCadastro.setText(dataSQL);        
    }
    
    public void limpaCampos(){
        config = new Configuracoes();
        txtMatricula.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
        config.limpaTabela(tabelaTelefone);
        labelFoto.setText("");
        txtObservacoes.setText("");
        txtIdMedium.setText("");
        txtNome.requestFocus();
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
        buttonSexo = new javax.swing.ButtonGroup();
        panelTop = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        txtData = new javax.swing.JTextField();
        txtIdMedium = new javax.swing.JTextField();
        txtDataNascSQL = new javax.swing.JTextField();
        txtCodeUser = new javax.swing.JTextField();
        txtDataCadastro = new javax.swing.JTextField();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        labelUltimaSaida = new javax.swing.JLabel();
        labelDataSaida = new javax.swing.JLabel();
        labelUltimaMensalidade = new javax.swing.JLabel();
        labelMesUltMensal = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btPesquisa = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btImprimir = new javax.swing.JButton();
        btCateira = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        panelMensalidade = new javax.swing.JPanel();
        btFinanceiro = new javax.swing.JButton();
        panelCoroacao = new javax.swing.JPanel();
        btCoroa = new javax.swing.JButton();
        panelTrabalhos = new javax.swing.JPanel();
        btTrabalhos = new javax.swing.JButton();
        panelOrixas = new javax.swing.JPanel();
        btOrixas = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txtBairro = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        txtEndereco = new javax.swing.JTextField();
        panelEntrada = new javax.swing.JPanel();
        txtDataEntrada = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        txtTelefone = new javax.swing.JTextField();
        btIncluiTel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        brExcluiTel = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        checkIsento = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        txtEstado = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txtCidade = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtMatricula = new javax.swing.JTextField();
        panelFuncao = new javax.swing.JPanel();
        txtFuncao = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        txtDataNascimento = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        radioNao = new javax.swing.JRadioButton();
        radioSim = new javax.swing.JRadioButton();
        jPanel13 = new javax.swing.JPanel();
        radioF = new javax.swing.JRadioButton();
        radioM = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        txtNome = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        labelFoto = new javax.swing.JLabel();
        btInserirFoto = new javax.swing.JButton();
        btExcluirFoto = new javax.swing.JButton();
        labelAtivoSel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("MÉDIUNS");

        txtData.setEditable(false);

        txtIdMedium.setEditable(false);

        txtDataNascSQL.setEditable(false);

        txtCodeUser.setEditable(false);
        txtCodeUser.setToolTipText("CodUser");

        txtDataCadastro.setEditable(false);

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitulo)
                .addGap(113, 113, 113)
                .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtIdMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDataNascSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtCodeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(663, Short.MAX_VALUE))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataNascSQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        labelUltimaSaida.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelUltimaSaida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUltimaSaida.setText("ÚLTIMA SAÍDA");

        labelDataSaida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataSaida.setText("Aguardando...");

        labelUltimaMensalidade.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelUltimaMensalidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUltimaMensalidade.setText("ÚLTIMA MENSALIDADE");

        labelMesUltMensal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMesUltMensal.setText("Aguardando...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelUltimaSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelDataSaida, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelUltimaMensalidade, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
            .addComponent(labelMesUltMensal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelUltimaSaida)
                .addGap(4, 4, 4)
                .addComponent(labelDataSaida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelUltimaMensalidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(labelMesUltMensal)
                .addContainerGap())
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        btPesquisa.setBackground(new java.awt.Color(255, 255, 255));
        btPesquisa.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/search.png"))); // NOI18N
        btPesquisa.setText("Pesquisar");
        btPesquisa.setToolTipText("Pesquisar");
        btPesquisa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPesquisa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisaActionPerformed(evt);
            }
        });

        btNovo.setBackground(new java.awt.Color(255, 255, 255));
        btNovo.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/novo16x16.png"))); // NOI18N
        btNovo.setText("Novo");
        btNovo.setToolTipText("Novo");
        btNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
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

        btImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btImprimir.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/print.png"))); // NOI18N
        btImprimir.setText("Imprimir");
        btImprimir.setToolTipText("Imprimir");
        btImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirActionPerformed(evt);
            }
        });

        btCateira.setBackground(new java.awt.Color(255, 255, 255));
        btCateira.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btCateira.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Grupo2.png"))); // NOI18N
        btCateira.setText("Carteira");
        btCateira.setToolTipText("Excluir");
        btCateira.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCateira.setMaximumSize(new java.awt.Dimension(71, 43));
        btCateira.setMinimumSize(new java.awt.Dimension(71, 43));
        btCateira.setPreferredSize(new java.awt.Dimension(71, 43));
        btCateira.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCateira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCateiraActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCateira, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCateira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelMensalidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mensalidades", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        panelMensalidade.setPreferredSize(new java.awt.Dimension(200, 105));

        btFinanceiro.setBackground(new java.awt.Color(255, 255, 255));
        btFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtFinaceiro1.png"))); // NOI18N
        btFinanceiro.setToolTipText("Mensalidades");
        btFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFinanceiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMensalidadeLayout = new javax.swing.GroupLayout(panelMensalidade);
        panelMensalidade.setLayout(panelMensalidadeLayout);
        panelMensalidadeLayout.setHorizontalGroup(
            panelMensalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMensalidadeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btFinanceiro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMensalidadeLayout.setVerticalGroup(
            panelMensalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btFinanceiro)
        );

        panelCoroacao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Coroação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        btCoroa.setBackground(new java.awt.Color(255, 255, 255));
        btCoroa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtCoroa.png"))); // NOI18N
        btCoroa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCoroaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCoroacaoLayout = new javax.swing.GroupLayout(panelCoroacao);
        panelCoroacao.setLayout(panelCoroacaoLayout);
        panelCoroacaoLayout.setHorizontalGroup(
            panelCoroacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCoroacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btCoroa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCoroacaoLayout.setVerticalGroup(
            panelCoroacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btCoroa)
        );

        panelTrabalhos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trabalhos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        panelTrabalhos.setPreferredSize(new java.awt.Dimension(200, 105));

        btTrabalhos.setBackground(new java.awt.Color(255, 255, 255));
        btTrabalhos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtTrabalhos.png"))); // NOI18N
        btTrabalhos.setToolTipText("Trabalhos");
        btTrabalhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrabalhosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTrabalhosLayout = new javax.swing.GroupLayout(panelTrabalhos);
        panelTrabalhos.setLayout(panelTrabalhosLayout);
        panelTrabalhosLayout.setHorizontalGroup(
            panelTrabalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrabalhosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btTrabalhos, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        panelTrabalhosLayout.setVerticalGroup(
            panelTrabalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btTrabalhos)
        );

        panelOrixas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orixás", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        panelOrixas.setPreferredSize(new java.awt.Dimension(200, 105));

        btOrixas.setBackground(new java.awt.Color(255, 255, 255));
        btOrixas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtOrixas.png"))); // NOI18N
        btOrixas.setToolTipText("Trabalhos");
        btOrixas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOrixasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOrixasLayout = new javax.swing.GroupLayout(panelOrixas);
        panelOrixas.setLayout(panelOrixasLayout);
        panelOrixasLayout.setHorizontalGroup(
            panelOrixasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrixasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btOrixas, javax.swing.GroupLayout.PREFERRED_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelOrixasLayout.setVerticalGroup(
            panelOrixasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btOrixas)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   BAIRRO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBairroKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   ENDEREÇO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEnderecoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelEntrada.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   DATA DE ENTRADA   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtDataEntrada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDataEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataEntradaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelEntradaLayout = new javax.swing.GroupLayout(panelEntrada);
        panelEntrada.setLayout(panelEntradaLayout);
        panelEntradaLayout.setHorizontalGroup(
            panelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntradaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEntradaLayout.setVerticalGroup(
            panelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEntradaLayout.createSequentialGroup()
                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   TELEFONES   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefoneKeyReleased(evt);
            }
        });

        btIncluiTel.setBackground(new java.awt.Color(255, 255, 255));
        btIncluiTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/sinalMais16x16.png"))); // NOI18N
        btIncluiTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluiTelActionPerformed(evt);
            }
        });

        tabelaTelefone.setForeground(new java.awt.Color(0, 0, 102));
        tabelaTelefone.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tabelaTelefone);

        brExcluiTel.setBackground(new java.awt.Color(255, 255, 255));
        brExcluiTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/sinalMenos16x16.png"))); // NOI18N
        brExcluiTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brExcluiTelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btIncluiTel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brExcluiTel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btIncluiTel)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brExcluiTel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   ISENTO MENSALIDADE   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        checkIsento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIsentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(checkIsento)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(checkIsento))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   ESTADO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEstadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   CIDADE   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCidadeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   OBSERVAÇÕES   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane2.setViewportView(txtObservacoes);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   MATRÍCULA   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtMatricula.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panelFuncao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   FUNÇÃO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtFuncao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtFuncao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFuncao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFuncaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout panelFuncaoLayout = new javax.swing.GroupLayout(panelFuncao);
        panelFuncao.setLayout(panelFuncaoLayout);
        panelFuncaoLayout.setHorizontalGroup(
            panelFuncaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFuncaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFuncao, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFuncaoLayout.setVerticalGroup(
            panelFuncaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   DATA DE NASCIMENTO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtDataNascimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataNascimentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataNascimentoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   E-MAIL   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEmailKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   ATIVO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        buttonGroup.add(radioNao);
        radioNao.setText("Não");
        radioNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNaoActionPerformed(evt);
            }
        });

        buttonGroup.add(radioSim);
        radioSim.setSelected(true);
        radioSim.setText("Sim");
        radioSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(radioSim)
                .addGap(40, 40, 40)
                .addComponent(radioNao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioSim)
                    .addComponent(radioNao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   SEXO   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        buttonSexo.add(radioF);
        radioF.setText(" Feminino");
        radioF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioFActionPerformed(evt);
            }
        });

        buttonSexo.add(radioM);
        radioM.setText(" Masculino");
        radioM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioM)
                .addGap(18, 18, 18)
                .addComponent(radioF)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radioM)
            .addComponent(radioF)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   NOME   ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10), new java.awt.Color(0, 0, 153))); // NOI18N

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon-people.png"))); // NOI18N

        btInserirFoto.setBackground(new java.awt.Color(255, 255, 255));
        btInserirFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/sinalMais16x16.png"))); // NOI18N
        btInserirFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInserirFotoActionPerformed(evt);
            }
        });

        btExcluirFoto.setBackground(new java.awt.Color(255, 255, 255));
        btExcluirFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/sinalMenos16x16.png"))); // NOI18N
        btExcluirFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirFotoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFoto, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btInserirFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluirFoto)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btInserirFoto)
                    .addComponent(btExcluirFoto))
                .addContainerGap())
        );

        labelAtivoSel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelAtivoSel.setForeground(new java.awt.Color(0, 153, 0));
        labelAtivoSel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAtivoSel.setText("ATIVO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelAtivoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(panelMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(panelCoroacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(panelTrabalhos, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(panelOrixas, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(211, 211, 211))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addContainerGap(17, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(labelAtivoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelCoroacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelTrabalhos, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelOrixas, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        alteraCamposMedium();
        alteraEndereco();
    }//GEN-LAST:event_btSalvarActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        fechar();
    }//GEN-LAST:event_btCancelarActionPerformed

    private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed

        limpaCampos();
        // TODO add your handling code here:
    }//GEN-LAST:event_btNovoActionPerformed

    public void exibeTelefones(){

        if(!txtIdMedium.getText().equals("")){
            t = new Telefones();
            t.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
            t.preencheTabTelefones(tabelaTelefone);
        }
        
    }
    
    public void incluiTel(){
        t = new Telefones();
        
        t.setNumTelefone(txtTelefone.getText());
        t.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
        if(!t.verificaTelefone()){
            t.incluirTelefone();
            t.preencheTabTelefones(tabelaTelefone);
            txtTelefone.setText("");
            txtTelefone.requestFocus();
        }
        
    }
    
    private void brExcluiTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brExcluiTelActionPerformed

        t = new Telefones();
        
        t.setNumTelefone(txtTelefone.getText());
        t.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
        if(t.excluirTelefone()){
            t.preencheTabTelefones(tabelaTelefone);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_brExcluiTelActionPerformed

    private void btInserirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInserirFotoActionPerformed

        if(txtIdMedium.getText().equals("")){
            
            JOptionPane.showMessageDialog(null, "Por favor, selecione ou digite o nome do médium.");
            
        }else{
        
            JFileChooser jfilechooser = new JFileChooser();
            File file = new File("C:\\sistejm\\fotos\\");

            jfilechooser.setSelectedFile(file);
            jfilechooser.setFileFilter(new FileNameExtensionFilter("Arquivos de Imagem", "png", "jpeg", "jpg"));

            int option = jfilechooser.showOpenDialog(null);

            if (option == JFileChooser.APPROVE_OPTION) {

                Fotos f = new Fotos();
                file = jfilechooser.getSelectedFile();
                String caminho = file.getAbsolutePath().replace('\\', '/');

    //            JOptionPane.showMessageDialog(null, file.getPath());

                BufferedImage bufferedImage = null;  
                try {
                    bufferedImage = ImageIO.read(new File(file.getPath()));

                    //Grava o link da foto
                    f.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
                    f.setFoto(caminho);
    //                f.setFoto(file.getPath());
                    f.incluirFoto();

    //                Path origem = Paths.get(file.toString());
    //                Path destino = Paths.get("C:\\sistejm\\fotos");
    //                Files.copy(origem, destino);                   
    //                JOptionPane.showMessageDialog(null, file.getPath());
                } catch (IOException e) {  
                    JOptionPane.showMessageDialog(null, "Arquivo não suportável");
    //                System.out.println("Erro ao abrir a imagem...");  
                    e.fillInStackTrace();  
                }  

                ImageIcon img = new ImageIcon(bufferedImage.getScaledInstance(200, 200, 200));
    //            ImageIcon img = new ImageIcon(bufferedImage.getScaledInstance(1000, 1000, 1000));

                labelFoto.setIcon(img);

    //            Image i = img.getImage();

    //            f = new Fotos();

    /*

                ImageIcon image = new ImageIcon(file.getPath());

                int height = image.getIconHeight();
                int width = image.getIconWidth();

                labelFoto.setSize(width, height);
                labelFoto.setIcon(image);
                labelFoto.repaint();*/

            }        // TODO add your handling code here:
        }
    }//GEN-LAST:event_btInserirFotoActionPerformed

    
   
    private void btExcluirFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirFotoActionPerformed

        ImageIcon img = new ImageIcon("");
        labelFoto.setIcon(img);
        
        if(txtIdMedium.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Por favor, selecione o médium.");
        }else if(!txtIdMedium.getText().equals("")){
            f = new Fotos();
            f.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
            if(!f.excluirFoto()){
                JOptionPane.showMessageDialog(null, "Não há foto para este médium. Inclua uma nova foto.");
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btExcluirFotoActionPerformed

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
        con = new Conexao();
        m = new Mediuns();
        String dataVazia = "01/01/1901";
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(evt.getKeyChar() > 5){
                    m = new Mediuns();
                    int codIdMedium = con.ultimoId("mediuns", "idmedium");
                    txtMatricula.setText("TEJM-" + String.valueOf(codIdMedium));
                    txtIdMedium.setText(String.valueOf(codIdMedium));
                    //verificar se o usuário existe

//                    m.setCodUsuario(Integer.valueOf(txtCodeUser.getText()));
//                    m.setIdMedium(codIdMedium);
//                    m.setMatricula(txtMatricula.getText());
//                    m.setNomeMedium(txtNome.getText());
//                    m.setDataCadastro(txtDataCadastro.getText());
                    
//                    if(txtDataNascimento.getText().isEmpty()){
//                        m.setDataNascimento(dataVazia);
//                    }
//                    if(txtDataEntrada.getText().isEmpty()){
//                        m.setDataEntrada(dataVazia);
//                    }
                    
//                    if(m.incluirMedium()){
//                        JOptionPane.showMessageDialog(null, "Cadastrado");
//
//                    }else{
//                        limpaCampos();
//                    }
            }
            txtDataNascimento.requestFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtDataNascimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataNascimentoKeyReleased

        config = new Configuracoes();
        m = new Mediuns();
        if(txtDataNascimento.getText().length() == 2){
            txtDataNascimento.setText(txtDataNascimento.getText() + "/");
        }
        if(txtDataNascimento.getText().length() == 5){
            txtDataNascimento.setText(txtDataNascimento.getText() + "/");
        }
        if(txtDataNascimento.getText().length() == 10){
            m.setDataNascimento(txtDataNascimento.getText());
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            m.alterarDataNascimentoMedium();
//            alteraCamposMedium();
            txtEndereco.requestFocus();
//            config = new Configuracoes();
//            updateMedium();
//            txtDataNascSQL.setText(config.retornaFormatoDataSQL(txtDataNascimento.getText()));
        }
    }//GEN-LAST:event_txtDataNascimentoKeyReleased

    private void txtDataEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataEntradaKeyReleased
        config = new Configuracoes();
        if(txtDataEntrada.getText().length() == 2){
            txtDataEntrada.setText(txtDataEntrada.getText() + "/");
        }
        if(txtDataEntrada.getText().length() == 5){
            txtDataEntrada.setText(txtDataEntrada.getText() + "/");
        }
        if(txtDataEntrada.getText().length() >= 10){
            config = new Configuracoes();
            
            txtData.setText(config.retornaFormatoDataSQL(txtDataEntrada.getText()));
            if(!txtIdMedium.getText().equals("")){
                m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
                m.setDataEntrada(txtDataEntrada.getText());
                m.alterarMedium();
            }else{
                txtNome.requestFocus();
            }
            
            
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txtDataEntradaKeyReleased

    private void btPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisaActionPerformed

        inicial = new TelaInicial();
        
        DPesquisaMedium pm = new DPesquisaMedium(inicial, false);
        pm.setLocationRelativeTo(pm);
        pm.setVisible(true);
        pm.recebeIdUsuario(txtCodeUser.getText());
        fechar();
    }//GEN-LAST:event_btPesquisaActionPerformed

    private void txtDataNascimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataNascimentoKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(txtDataNascimento.getText().length() < 11){
                txtNome.requestFocus();
            }else if (txtDataNascimento.getText().isEmpty()){
                txtNome.requestFocus();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoKeyPressed
    
    private void checkIsentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIsentoActionPerformed

        m = new Mediuns();
        if(checkIsento.isSelected()){
            this.isento = 1;
            m.setIsentoMensal(this.isento);
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            btFinanceiro.setEnabled(false);
            m.alterarIsentoMedium();
        }else{
            this.isento = 0;
            m.setIsentoMensal(this.isento);
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            btFinanceiro.setEnabled(true);
            m.alterarIsentoMedium();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_checkIsentoActionPerformed

    public void updateMedium(){
        
//        if(!txtIdMedium.getText().equals("")){
//            
//        }
            m.setAtivo(this.ativo);
            m.setCodUsuario(Integer.valueOf(txtCodeUser.getText()));
            m.setDataEntrada(txtDataEntrada.getText());
            m.setDataNascimento(txtDataNascimento.getText());
            m.setEmail(txtEmail.getText());
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            m.setIsentoMensal(this.isento);
            m.setMatricula(txtMatricula.getText());
            m.setNomeMedium(txtNome.getText());
            m.setObservacoes(txtObservacoes.getText());
            m.setSexo(this.sexo);
            if (m.alterarMedium()){
                m.exibeCamposMedium(txtIdMedium, txtNome, txtMatricula, checkIsento, txtDataNascimento, txtDataEntrada, 
                    txtObservacoes, txtEndereco, txtBairro, txtCidade, txtEstado, radioSim, radioNao, radioM, radioF, txtEmail, txtFuncao);
                if(radioSim.isSelected()){
                    Color corVerde = new Color(0,153,0);
                    labelAtivoSel.setForeground(corVerde);
                    labelAtivoSel.setText("ATIVO");
                }else{
                    labelAtivoSel.setForeground(Color.RED);
                    labelAtivoSel.setText("INATIVO");
                }
            }       
    }
    
    private void btFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFinanceiroActionPerformed

        inicial = new TelaInicial();
        
        if(!txtIdMedium.getText().equals("")){
            mensal = new DMensalidade(inicial, false);
            mensal.setLocationRelativeTo(mensal);
            mensal.recebeInfo(Integer.valueOf(txtIdMedium.getText()), Integer.valueOf(txtCodeUser.getText()), txtNome.getText());
            mensal.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione ou cadastre o médium.");
        }
    }//GEN-LAST:event_btFinanceiroActionPerformed

    private void radioSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSimActionPerformed

        this.ativo = 1;
        updateMedium();
        Color corVerde = new Color(0,153,0);
        labelAtivoSel.setForeground(corVerde);
        labelAtivoSel.setText("ATIVO");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_radioSimActionPerformed

    private void radioNaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNaoActionPerformed

        this.ativo = 0;
        updateMedium();
        labelAtivoSel.setForeground(Color.RED);
        labelAtivoSel.setText("INATIVO");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_radioNaoActionPerformed

    private void btCoroaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCoroaActionPerformed

        inicial = new TelaInicial();
        
        if(!txtIdMedium.getText().equals("")){
            coroa = new DCoroacao(inicial, false);
            coroa.setLocationRelativeTo(coroa);
            coroa.setTitle("COROAÇÃO");
            coroa.recebeInfo(Integer.valueOf(txtIdMedium.getText()), 
                    Integer.valueOf(txtCodeUser.getText()), txtNome.getText(), txtDataEntrada.getText());
            coroa.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione ou cadastre o médium.");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btCoroaActionPerformed

    private void btTrabalhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrabalhosActionPerformed

        inicial = new TelaInicial();
        
        DTrabalhoSaidas saida = new DTrabalhoSaidas(inicial, false);
        saida.setTitle("TRABALHOS - SAÍDAS");
        saida.setLocationRelativeTo(saida);
        saida.recebeInfo(txtNome.getText(), txtIdMedium.getText());
        saida.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_btTrabalhosActionPerformed

    private void txtFuncaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFuncaoKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            m = new Mediuns();
            m.setFuncao(txtFuncao.getText());
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            m.alterarFuncaoMedium();
            txtTelefone.requestFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtFuncaoKeyPressed

    private void radioMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioMActionPerformed

        this.sexo = "m";
        if(!txtIdMedium.getText().equals("")){
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            m.setSexo(this.sexo);
            m.alterarSexoMedium();
        }else{
            txtNome.requestFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_radioMActionPerformed

    private void radioFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioFActionPerformed
        this.sexo = "f";
        if(!txtIdMedium.getText().equals("")){
            m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
            m.setSexo(this.sexo);
            m.alterarSexoMedium();
        }else{
            txtNome.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_radioFActionPerformed

    private void txtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB){
            if(!txtIdMedium.getText().equals("")){
                m.setIdMedium(Integer.valueOf(txtIdMedium.getText()));
                m.setEmail(txtEmail.getText());
                m.alterarEmailMedium();
                txtEndereco.requestFocus();
            }else{
                txtNome.requestFocus();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyPressed

    private void btOrixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOrixasActionPerformed

        inicial = new TelaInicial();
        
        if(!txtIdMedium.getText().equals("")){
            DOrixasMedium om = new DOrixasMedium(inicial, false);
            om.setLocationRelativeTo(om);
            om.setTitle("COROAÇÃO");
            om.recebeInfo(Integer.valueOf(txtIdMedium.getText()), 
                    Integer.valueOf(txtCodeUser.getText()), txtNome.getText());
            om.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Selecione ou cadastre o médium.");
        }
        
    }//GEN-LAST:event_btOrixasActionPerformed

    private void btImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btImprimirActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEnderecoKeyPressed

        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            camposLogradouro();
            txtBairro.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoKeyPressed

    private void txtBairroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBairroKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            camposLogradouro();
            txtCidade.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBairroKeyReleased

    private void txtCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCidadeKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            camposLogradouro();
            txtEstado.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCidadeKeyPressed

    private void txtEstadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            camposLogradouro();
            txtTelefone.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEstadoKeyReleased

    private void txtTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefoneKeyReleased

        String tel = txtTelefone.getText();
        if(txtTelefone.getText().length()  == 1){
            if(tel.substring(0).equals("9")){
                if(tel.length() == 5){
                    txtTelefone.setText(tel + "-");
                }
                if(tel.length() == 10){
                    if(JOptionPane.showConfirmDialog(null, "Confirma o celular ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0){
                        incluiTel();
                    }
                }
            }else{
                if(tel.length() == 4){
                    txtTelefone.setText(tel + "-");
                }
                if(tel.length() == 9){
                    if(JOptionPane.showConfirmDialog(null, "Confirma o telefone ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0){
                        incluiTel();
                    }
                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneKeyReleased

    private void btIncluiTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluiTelActionPerformed
        incluiTel();
        // TODO add your handling code here:
    }//GEN-LAST:event_btIncluiTelActionPerformed

    private void btCateiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCateiraActionPerformed

        String funcao = null;
        r = new Relatorios();
        
        if(txtFuncao.getText().equals("")){
            funcao = "MÉDIUM";
        }else{
            funcao = txtFuncao.getText();
        }
        r.geraCarteira(Integer.valueOf(txtIdMedium.getText()), funcao.toUpperCase());

        // TODO add your handling code here:
    }//GEN-LAST:event_btCateiraActionPerformed

    public void camposLogradouro(){
            l = new Logradouro();
            
            l.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
            l.setEndereco(txtEndereco.getText());
            l.setCidade(txtCidade.getText());
            l.setBairro(txtBairro.getText());
            l.setEstado(txtEstado.getText());
            l.verificaEndereco();

    }
    
    public void recebeUsuario(int iduser, String user){
        txtCodeUser.setText(String.valueOf(iduser));
    }
    public void fechar(){
        this.setAutoRequestFocus(false);
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
            java.util.logging.Logger.getLogger(DMediumGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DMediumGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DMediumGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DMediumGeral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DMediumGeral dialog = new DMediumGeral(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton brExcluiTel;
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btCateira;
    private javax.swing.JButton btCoroa;
    private javax.swing.JButton btExcluirFoto;
    private javax.swing.JButton btFinanceiro;
    private javax.swing.JButton btImprimir;
    private javax.swing.JButton btIncluiTel;
    private javax.swing.JButton btInserirFoto;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btOrixas;
    private javax.swing.JButton btPesquisa;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btTrabalhos;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.ButtonGroup buttonSexo;
    private javax.swing.JCheckBox checkIsento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAtivoSel;
    private javax.swing.JLabel labelDataSaida;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelMesUltMensal;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUltimaMensalidade;
    private javax.swing.JLabel labelUltimaSaida;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JPanel panelCoroacao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelEntrada;
    private javax.swing.JPanel panelFuncao;
    private javax.swing.JPanel panelMensalidade;
    private javax.swing.JPanel panelOrixas;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPanel panelTrabalhos;
    private javax.swing.JRadioButton radioF;
    private javax.swing.JRadioButton radioM;
    private javax.swing.JRadioButton radioNao;
    private javax.swing.JRadioButton radioSim;
    private javax.swing.JTable tabelaTelefone;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtCodeUser;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDataCadastro;
    private javax.swing.JTextField txtDataEntrada;
    private javax.swing.JTextField txtDataNascSQL;
    private javax.swing.JTextField txtDataNascimento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtFuncao;
    private javax.swing.JTextField txtIdMedium;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
