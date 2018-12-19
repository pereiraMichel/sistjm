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
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
//        this.ativo = 1;
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
        m = new Mediuns();
        txtNome.setText(nome);
        txtMatricula.setText(matricula);
        txtIdMedium.setText(idMedium);
        txtCodeUser.setText(iduser);
        m.setMatricula(txtMatricula.getText());
        m.exibeCamposMedium(txtIdMedium, txtNome, txtMatricula, checkIsento, txtDataNascimento, txtDataEntrada, 
                txtObservacoes, txtEndereco, txtBairro, txtCidade, txtEstado, radioSim, radioNao, radioM, radioF, 
                txtEmail);
        
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
        
        alteraEndereco();
        
        if (m.alterarMedium()){
            m.exibeCamposMedium(txtIdMedium, txtNome, txtMatricula, checkIsento, txtDataNascimento, txtDataEntrada, 
                txtObservacoes, txtEndereco, txtBairro, txtCidade, txtEstado, radioSim, radioNao, radioM, radioF, txtEmail);
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
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        txtData = new javax.swing.JTextField();
        txtIdMedium = new javax.swing.JTextField();
        btImprimir = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        txtDataNascSQL = new javax.swing.JTextField();
        txtCodeUser = new javax.swing.JTextField();
        txtDataCadastro = new javax.swing.JTextField();
        btPesquisa = new javax.swing.JButton();
        panelDown = new javax.swing.JPanel();
        labelVersao = new javax.swing.JLabel();
        labelMatricula = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        labelEndereco = new javax.swing.JLabel();
        labelBairro = new javax.swing.JLabel();
        panelFoto = new javax.swing.JPanel();
        labelFoto = new javax.swing.JLabel();
        btInserirFoto = new javax.swing.JButton();
        btExcluirFoto = new javax.swing.JButton();
        labeNascimento = new javax.swing.JLabel();
        labelTelefone = new javax.swing.JLabel();
        panelMensalidade = new javax.swing.JPanel();
        btFinanceiro = new javax.swing.JButton();
        labelObservacoes = new javax.swing.JLabel();
        panelCoroacao = new javax.swing.JPanel();
        btCoroa = new javax.swing.JButton();
        panelTrabalhos = new javax.swing.JPanel();
        btTrabalhos = new javax.swing.JButton();
        txtMatricula = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtDataNascimento = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        btIncluiTel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaTelefone = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacoes = new javax.swing.JTextArea();
        checkIsento = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        labelUltimaSaida = new javax.swing.JLabel();
        labelDataSaida = new javax.swing.JLabel();
        labelUltimaMensalidade = new javax.swing.JLabel();
        labelMesUltMensal = new javax.swing.JLabel();
        brExcluiTel = new javax.swing.JButton();
        panelOrixas = new javax.swing.JPanel();
        btOrixas = new javax.swing.JButton();
        labelCidade = new javax.swing.JLabel();
        labelEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        txtDataEntrada = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        labeltivo = new javax.swing.JLabel();
        radioSim = new javax.swing.JRadioButton();
        radioNao = new javax.swing.JRadioButton();
        labelAtivoSel = new javax.swing.JLabel();
        radioM = new javax.swing.JRadioButton();
        radioF = new javax.swing.JRadioButton();
        labelEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelTitulo.setText("MÉDIUNS");

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

        txtIdMedium.setEditable(false);

        btImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btImprimir.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        btImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/print.png"))); // NOI18N
        btImprimir.setText("Imprimir");
        btImprimir.setToolTipText("Imprimir");
        btImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

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

        txtDataNascSQL.setEditable(false);

        txtCodeUser.setEditable(false);
        txtCodeUser.setToolTipText("CodUser");

        txtDataCadastro.setEditable(false);

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
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDataNascSQL, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdMedium, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(btCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataNascSQL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btImprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btNovo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        labelMatricula.setText("Matrícula:");

        labelNome.setText("Nome:");

        labelEndereco.setText("Endereço:");

        labelBairro.setText("Bairro:");

        panelFoto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        javax.swing.GroupLayout panelFotoLayout = new javax.swing.GroupLayout(panelFoto);
        panelFoto.setLayout(panelFotoLayout);
        panelFotoLayout.setHorizontalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFotoLayout.createSequentialGroup()
                        .addComponent(btInserirFoto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluirFoto))
                    .addGroup(panelFotoLayout.createSequentialGroup()
                        .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelFotoLayout.setVerticalGroup(
            panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelFotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(panelFotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btInserirFoto)
                    .addComponent(btExcluirFoto))
                .addContainerGap())
        );

        labeNascimento.setText("Data de Nascimento:");

        labelTelefone.setText("Telefones:");

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
                .addComponent(btFinanceiro, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMensalidadeLayout.setVerticalGroup(
            panelMensalidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMensalidadeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btFinanceiro)
                .addContainerGap())
        );

        labelObservacoes.setText("Observações:");

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
                .addComponent(btCoroa, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCoroacaoLayout.setVerticalGroup(
            panelCoroacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCoroacaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCoroa)
                .addContainerGap())
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
            .addGroup(panelTrabalhosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btTrabalhos, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTrabalhosLayout.setVerticalGroup(
            panelTrabalhosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTrabalhosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btTrabalhos)
                .addContainerGap())
        );

        txtMatricula.setEditable(false);

        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        txtDataNascimento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDataNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDataNascimentoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataNascimentoKeyReleased(evt);
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

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        jScrollPane2.setViewportView(txtObservacoes);

        checkIsento.setText("   Isento da mensalidade");
        checkIsento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkIsentoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RESUMO", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 0, 153)));

        labelUltimaSaida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUltimaSaida.setText("ÚLTIMA SAÍDA");

        labelDataSaida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataSaida.setText("Aguardando...");

        labelUltimaMensalidade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUltimaMensalidade.setText("ÚLTIMA MENSALIDADE");

        labelMesUltMensal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMesUltMensal.setText("Aguardando...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelUltimaSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelDataSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelUltimaMensalidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelMesUltMensal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelUltimaSaida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDataSaida)
                .addGap(18, 18, 18)
                .addComponent(labelUltimaMensalidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelMesUltMensal)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        brExcluiTel.setBackground(new java.awt.Color(255, 255, 255));
        brExcluiTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/sinalMenos16x16.png"))); // NOI18N
        brExcluiTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brExcluiTelActionPerformed(evt);
            }
        });

        panelOrixas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Orixás", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N
        panelOrixas.setPreferredSize(new java.awt.Dimension(200, 105));

        btOrixas.setBackground(new java.awt.Color(255, 255, 255));
        btOrixas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/BtOrixas.png"))); // NOI18N
        btOrixas.setToolTipText("Trabalhos");

        javax.swing.GroupLayout panelOrixasLayout = new javax.swing.GroupLayout(panelOrixas);
        panelOrixas.setLayout(panelOrixasLayout);
        panelOrixasLayout.setHorizontalGroup(
            panelOrixasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrixasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btOrixas, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOrixasLayout.setVerticalGroup(
            panelOrixasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOrixasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btOrixas)
                .addContainerGap())
        );

        labelCidade.setText("Cidade:");

        labelEstado.setText("Estado:");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtDataEntrada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDataEntrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDataEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataEntradaKeyReleased(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA ENTRADA");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        labeltivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeltivo.setText("Ativo ?");

        buttonGroup.add(radioSim);
        radioSim.setSelected(true);
        radioSim.setText("Sim");
        radioSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSimActionPerformed(evt);
            }
        });

        buttonGroup.add(radioNao);
        radioNao.setText("Não");
        radioNao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioNaoActionPerformed(evt);
            }
        });

        labelAtivoSel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        labelAtivoSel.setForeground(new java.awt.Color(0, 153, 0));
        labelAtivoSel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAtivoSel.setText("ATIVO");

        buttonSexo.add(radioM);
        radioM.setText(" Masculino");

        buttonSexo.add(radioF);
        radioF.setText(" Feminino");

        labelEmail.setText("E-mail:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelMatricula)
                                            .addComponent(labelNome))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(radioM)
                                                .addGap(18, 18, 18)
                                                .addComponent(radioF))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(checkIsento)
                                                .addGap(42, 42, 42)
                                                .addComponent(labeNascimento)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelEndereco)
                                            .addComponent(labelBairro)
                                            .addComponent(labelEmail))
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelCidade)
                                                .addGap(20, 20, 20)
                                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(labelEstado)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelTelefone)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btIncluiTel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(brExcluiTel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(labelObservacoes))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelMensalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelCoroacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTrabalhos, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelOrixas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labeltivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAtivoSel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(radioSim)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(radioNao)
                        .addGap(23, 23, 23)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelNome)
                                    .addComponent(radioM)
                                    .addComponent(radioF)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelMatricula)
                                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(checkIsento)
                                    .addComponent(txtDataNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labeNascimento))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(labelEndereco)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(labelBairro))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelCidade)
                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelEstado)
                                .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelTelefone))
                            .addComponent(labelObservacoes))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btIncluiTel)
                                .addGap(15, 15, 15)
                                .addComponent(brExcluiTel))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelMensalidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelCoroacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTrabalhos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelOrixas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labeltivo)
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(radioSim)
                                    .addComponent(radioNao))
                                .addGap(13, 13, 13)
                                .addComponent(labelAtivoSel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(159, 159, 159)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    
    private void btIncluiTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluiTelActionPerformed

        t = new Telefones();
        
        t.setNumTelefone(txtTelefone.getText());
        t.setCodMedium(Integer.valueOf(txtIdMedium.getText()));
        if(!t.verificaTelefone()){
            t.incluirTelefone();
            t.preencheTabTelefones(tabelaTelefone);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btIncluiTelActionPerformed

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

                    m.setCodUsuario(Integer.valueOf(txtCodeUser.getText()));
                    m.setIdMedium(codIdMedium);
                    m.setMatricula(txtMatricula.getText());
                    m.setNomeMedium(txtNome.getText());
                    m.setDataCadastro(txtDataCadastro.getText());
                    
                    if(txtDataNascimento.getText().isEmpty()){
                        m.setDataNascimento(dataVazia);
                    }
                    if(txtDataEntrada.getText().isEmpty()){
                        m.setDataEntrada(dataVazia);
                    }
                    
                    if(m.incluirMedium()){
                        JOptionPane.showMessageDialog(null, "Cadastrado");

                    }else{
                        limpaCampos();
                    }
            }
            txtDataNascimento.requestFocus();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtDataNascimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataNascimentoKeyReleased

        config = new Configuracoes();
        if(txtDataNascimento.getText().length() == 2){
            txtDataNascimento.setText(txtDataNascimento.getText() + "/");
        }
        if(txtDataNascimento.getText().length() == 5){
            txtDataNascimento.setText(txtDataNascimento.getText() + "/");
        }
        if(txtDataNascimento.getText().length() >= 10){
            config = new Configuracoes();
            
            txtDataNascSQL.setText(config.retornaFormatoDataSQL(txtDataNascimento.getText()));
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
                txtDataNascimento.requestFocus();
            }else if (txtDataNascimento.getText().isEmpty()){
                txtEndereco.requestFocus();
            }else{
                alteraCamposMedium();
                txtEndereco.requestFocus();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoKeyPressed
    
    private void checkIsentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkIsentoActionPerformed

        m = new Mediuns();
        if(checkIsento.isSelected()){
            this.isento = 1;
            btFinanceiro.setEnabled(false);
            updateMedium();
        }else{
            this.isento = 0;
            btFinanceiro.setEnabled(true);
            updateMedium();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_checkIsentoActionPerformed

    public void updateMedium(){
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
                    txtObservacoes, txtEndereco, txtBairro, txtCidade, txtEstado, radioSim, radioNao, radioM, radioF, txtEmail);
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
    private javax.swing.JButton btCoroa;
    private javax.swing.JButton btExcluir;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labeNascimento;
    private javax.swing.JLabel labelAtivoSel;
    private javax.swing.JLabel labelBairro;
    private javax.swing.JLabel labelCidade;
    private javax.swing.JLabel labelDataSaida;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEndereco;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelFoto;
    private javax.swing.JLabel labelMatricula;
    private javax.swing.JLabel labelMesUltMensal;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelObservacoes;
    private javax.swing.JLabel labelTelefone;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelUltimaMensalidade;
    private javax.swing.JLabel labelUltimaSaida;
    private javax.swing.JLabel labelVersao;
    private javax.swing.JLabel labeltivo;
    private javax.swing.JPanel panelCoroacao;
    private javax.swing.JPanel panelDown;
    private javax.swing.JPanel panelFoto;
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
    private javax.swing.JTextField txtIdMedium;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObservacoes;
    private javax.swing.JTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
