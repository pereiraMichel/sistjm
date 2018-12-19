/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.telas;

import br.com.sistejm.classes.Conexao;
import br.com.sistejm.classes.Configuracoes;
import br.com.sistejm.classes.Usuario;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class TelaInicial extends javax.swing.JFrame {

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    Usuario usuario;
    Configuracoes config;
    Calendar cal = new GregorianCalendar();
    
    private String user;
    private int iduser;
    private String acesso;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }
    
    /**
     * Creates new form TelaInicial
     */
    public TelaInicial() {
        initComponents();
        fechaPanelLogin();
        bloqueiaMenus(false);
        bloqueiaLogoff(false);
        fechaTestes();
        txtAcesso.setText("Não está logado.");
        inicioAdm();
        ocultaMenus(false);
    }
    
    public void inicioAdm(){
        panelLogin.setVisible(true);
        txtUsuario.setText("michel");
        txtSenha.setText("m1ch3l4p");
        txtSenha.requestFocus();
    }
    
    public void preencheCampo(String texto){
        campoTexto.setLineWrap(true);
        campoTexto.append(texto + "\n");
    }
    
    public void fechaTestes(){
        labelEsqueciSenha.setVisible(false);
        panelEsqueciSenha.setVisible(false);
    }
    
    public void bloqueiaLogoff(boolean valor){
        subMenuLogoff.setEnabled(valor);
    }
    public void ocultaMenus(boolean valor){
        subMenuBaixa.setVisible(valor);
    }

    
    public String dataHoje(){
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        int semana = cal.get(Calendar.DAY_OF_WEEK);
        
        String mesLongo = null;
        String semanaExtensa = null;
        
        switch(semana){
            case 1:
                semanaExtensa = "Domingo";
                break;
            case 2:
                semanaExtensa = "Segunda-feira";
                break;
            case 3:
                semanaExtensa = "Terça-feira";
                break;
            case 4:
                semanaExtensa = "Quarta-feira";
                break;
            case 5:
                semanaExtensa = "Quinta-feira";
                break;
            case 6:
                semanaExtensa = "Sexta-feira";
                break;
            case 7:
                semanaExtensa = "Sábado";
                break;
        }
        
        if(mes < 10){
            mesLongo = "0" + mes;
        }else{
            mesLongo = String.valueOf(mes);
        }
        
        String data = semanaExtensa + ", dia " + dia + "/" + mesLongo + "/" + ano;
        
        return data;        
    }
    
    public void sair(){
        
        if(JOptionPane.showConfirmDialog(null, "Deseja sair ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0){
            String pathBanco = "C:\\sistejm\\config\\banco.properties";
            String pathUser = "C:\\sistejm\\config\\datauser.properties";

            config = new Configuracoes();
            //String dataHoje = config.retornaData();
           // String pathAtividades = "C:\\sistejm\\dados\\" + this.user + "." + dataHoje + ".txt";

            config.gravaAtividades("Saída do", this.user, "Sistema");
            
            config.extendNew(pathBanco);
            config.extendNew(pathUser);
            //config.extendNew(pathAtividades);

            //preencheCampo("\nFechando...");

            this.dispose();
            System.exit(0);

        }
    }
    
    public void bloqueiaMenus(boolean valor){
        menuBiblioteca.setEnabled(valor);
        menuRelatorios.setEnabled(valor);
        menuTesouraria.setEnabled(valor);
        menuAdm.setEnabled(valor);
        
        subMenuExu.setEnabled(valor);
        subMenuAcesso.setEnabled(valor);
        subMenuConfigUsuarios.setEnabled(valor);
        subMenuOrixas.setEnabled(valor);
        subMenuEntidades.setEnabled(valor);
        subMenuNovoUsuario.setEnabled(valor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMeio = new javax.swing.JPanel();
        labelImage = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        campoTexto = new javax.swing.JTextArea();
        labelImagemOrixas = new javax.swing.JLabel();
        panelLogin = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        labelUsuario = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        labelEsqueciSenha = new javax.swing.JLabel();
        panelEsqueciSenha = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtConfirmaUsuario = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        labelCodigo = new javax.swing.JLabel();
        txtCode = new javax.swing.JTextField();
        toolbar = new javax.swing.JToolBar();
        btLogin = new javax.swing.JButton();
        panelBaixo = new javax.swing.JPanel();
        labelAcesso = new javax.swing.JLabel();
        txtAcesso = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        menu = new javax.swing.JMenuBar();
        menuArquivo = new javax.swing.JMenu();
        subMenuLogin = new javax.swing.JMenuItem();
        subMenuLogoff = new javax.swing.JMenuItem();
        subMenuNovoUsuario = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        subMenuSair = new javax.swing.JMenuItem();
        menuAdm = new javax.swing.JMenu();
        subMenuMediuns = new javax.swing.JMenuItem();
        aubMenuEventos = new javax.swing.JMenuItem();
        subMenuProdutos = new javax.swing.JMenuItem();
        subMenuColabora = new javax.swing.JMenuItem();
        subMenuAgendaAnual = new javax.swing.JMenuItem();
        subMenuAgendamentos = new javax.swing.JMenuItem();
        subMenuBaixa = new javax.swing.JMenuItem();
        subMenuTrabalho = new javax.swing.JMenuItem();
        subMenuCoroa = new javax.swing.JMenuItem();
        menuTesouraria = new javax.swing.JMenu();
        subMenuPagamentos = new javax.swing.JMenuItem();
        subMenuRecebimentos = new javax.swing.JMenuItem();
        subMenuMensalidades = new javax.swing.JMenuItem();
        subMenuDepositos = new javax.swing.JMenuItem();
        menuBiblioteca = new javax.swing.JMenu();
        subMenuLivros = new javax.swing.JMenuItem();
        subMenuPatente = new javax.swing.JMenuItem();
        subMenuReservas = new javax.swing.JMenuItem();
        menuRelatorios = new javax.swing.JMenu();
        subMenuRelUsuarios = new javax.swing.JMenuItem();
        subMenuRelMediuns = new javax.swing.JMenuItem();
        subMenuRelEventos = new javax.swing.JMenuItem();
        subMenuRelAgenda = new javax.swing.JMenuItem();
        subMenuRelLivros = new javax.swing.JMenuItem();
        subMenuRelTesouraria = new javax.swing.JMenuItem();
        subMenuRelSaidas = new javax.swing.JMenuItem();
        subMenuConsultas = new javax.swing.JMenuItem();
        menuConfiguracoes = new javax.swing.JMenu();
        subMenuBancoDados = new javax.swing.JMenuItem();
        subMenuConfigUsuarios = new javax.swing.JMenuItem();
        subMenuAcesso = new javax.swing.JMenuItem();
        subMenuOrixas = new javax.swing.JMenuItem();
        subMenuEntidades = new javax.swing.JMenuItem();
        subMenuExu = new javax.swing.JMenuItem();
        subMenuEres = new javax.swing.JMenuItem();
        subMenuCaboclos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                formWindowDeactivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelMeio.setBackground(new java.awt.Color(255, 255, 255));
        panelMeio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        labelImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/logoTJM.jpg"))); // NOI18N

        scroll.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        scroll.setForeground(new java.awt.Color(204, 204, 204));

        campoTexto.setEditable(false);
        campoTexto.setColumns(20);
        campoTexto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campoTexto.setForeground(new java.awt.Color(0, 0, 153));
        campoTexto.setRows(5);
        scroll.setViewportView(campoTexto);

        labelImagemOrixas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelImagemOrixas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/image.jpg"))); // NOI18N

        panelLogin.setBackground(new java.awt.Color(255, 255, 255));
        panelLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUsuario.setMinimumSize(new java.awt.Dimension(6, 25));
        txtUsuario.setPreferredSize(new java.awt.Dimension(6, 25));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        txtSenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSenha.setMinimumSize(new java.awt.Dimension(6, 25));
        txtSenha.setPreferredSize(new java.awt.Dimension(6, 25));
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelUsuario.setText("Usuário:");

        labelSenha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelSenha.setText("Senha:");

        labelEsqueciSenha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEsqueciSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelEsqueciSenha.setText("Esqueci minha senha");

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUsuario)
                    .addComponent(labelSenha))
                .addGap(18, 18, 18)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addComponent(labelEsqueciSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 21, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addGap(18, 18, 18)
                .addComponent(labelEsqueciSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelEsqueciSenha.setBackground(new java.awt.Color(255, 255, 255));
        panelEsqueciSenha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Usuário:");

        labelEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelEmail.setText("E-mail:");

        labelCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelCodigo.setText("Código:");

        txtCode.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelEsqueciSenhaLayout = new javax.swing.GroupLayout(panelEsqueciSenha);
        panelEsqueciSenha.setLayout(panelEsqueciSenhaLayout);
        panelEsqueciSenhaLayout.setHorizontalGroup(
            panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsqueciSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(labelEmail)
                    .addComponent(labelCodigo))
                .addGap(18, 18, 18)
                .addGroup(panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtConfirmaUsuario)
                    .addComponent(txtEmail)
                    .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEsqueciSenhaLayout.setVerticalGroup(
            panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEsqueciSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtConfirmaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelEsqueciSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCodigo)
                    .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelMeioLayout = new javax.swing.GroupLayout(panelMeio);
        panelMeio.setLayout(panelMeioLayout);
        panelMeioLayout.setHorizontalGroup(
            panelMeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMeioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelImagemOrixas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMeioLayout.createSequentialGroup()
                        .addComponent(panelEsqueciSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(136, 136, 136))
                    .addGroup(panelMeioLayout.createSequentialGroup()
                        .addGap(0, 92, Short.MAX_VALUE)
                        .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelMeioLayout.setVerticalGroup(
            panelMeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMeioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMeioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMeioLayout.createSequentialGroup()
                        .addComponent(scroll)
                        .addContainerGap())
                    .addGroup(panelMeioLayout.createSequentialGroup()
                        .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelEsqueciSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(304, 304, 304))
                    .addGroup(panelMeioLayout.createSequentialGroup()
                        .addComponent(labelImage)
                        .addGap(119, 119, 119)
                        .addComponent(labelImagemOrixas)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        toolbar.setBackground(new java.awt.Color(153, 204, 255));
        toolbar.setFloatable(false);

        btLogin.setBackground(new java.awt.Color(255, 255, 255));
        btLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Cadeado.png"))); // NOI18N
        btLogin.setText("Login");
        btLogin.setToolTipText("Login (F2)");
        btLogin.setFocusable(false);
        btLogin.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btLogin.setMaximumSize(new java.awt.Dimension(80, 40));
        btLogin.setMinimumSize(new java.awt.Dimension(80, 40));
        btLogin.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });
        toolbar.add(btLogin);

        panelBaixo.setBackground(new java.awt.Color(255, 255, 255));
        panelBaixo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        labelAcesso.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        labelAcesso.setForeground(new java.awt.Color(0, 0, 255));
        labelAcesso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtAcesso.setEditable(false);
        txtAcesso.setBackground(new java.awt.Color(255, 255, 255));
        txtAcesso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtAcesso.setForeground(new java.awt.Color(0, 0, 255));
        txtAcesso.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAcesso.setBorder(null);

        txtData.setEditable(false);
        txtData.setBackground(new java.awt.Color(255, 255, 255));
        txtData.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtData.setForeground(new java.awt.Color(0, 0, 255));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setBorder(null);

        javax.swing.GroupLayout panelBaixoLayout = new javax.swing.GroupLayout(panelBaixo);
        panelBaixo.setLayout(panelBaixoLayout);
        panelBaixoLayout.setHorizontalGroup(
            panelBaixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBaixoLayout.createSequentialGroup()
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBaixoLayout.setVerticalGroup(
            panelBaixoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtAcesso)
            .addGroup(panelBaixoLayout.createSequentialGroup()
                .addComponent(labelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
            .addComponent(txtData)
        );

        menuArquivo.setText("Arquivo");

        subMenuLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        subMenuLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Cadeado.png"))); // NOI18N
        subMenuLogin.setText("Login");
        subMenuLogin.setPreferredSize(new java.awt.Dimension(150, 22));
        subMenuLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuLoginActionPerformed(evt);
            }
        });
        menuArquivo.add(subMenuLogin);

        subMenuLogoff.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        subMenuLogoff.setText("Logoff");
        subMenuLogoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuLogoffActionPerformed(evt);
            }
        });
        menuArquivo.add(subMenuLogoff);

        subMenuNovoUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        subMenuNovoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Grupo2.png"))); // NOI18N
        subMenuNovoUsuario.setText("Novo Usuário");
        subMenuNovoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuNovoUsuarioActionPerformed(evt);
            }
        });
        menuArquivo.add(subMenuNovoUsuario);
        menuArquivo.add(jSeparator1);

        subMenuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        subMenuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/site_icon-16x16.png"))); // NOI18N
        subMenuSair.setText("Sair");
        subMenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuSairActionPerformed(evt);
            }
        });
        menuArquivo.add(subMenuSair);

        menu.add(menuArquivo);

        menuAdm.setText("Administração");

        subMenuMediuns.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        subMenuMediuns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16HM.png"))); // NOI18N
        subMenuMediuns.setText("Médiuns");
        subMenuMediuns.setPreferredSize(new java.awt.Dimension(150, 22));
        subMenuMediuns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuMediunsActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuMediuns);

        aubMenuEventos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        aubMenuEventos.setText("Eventos");
        aubMenuEventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aubMenuEventosActionPerformed(evt);
            }
        });
        menuAdm.add(aubMenuEventos);

        subMenuProdutos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        subMenuProdutos.setText("Produtos");
        subMenuProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuProdutosActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuProdutos);

        subMenuColabora.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        subMenuColabora.setText("Colaborações");
        subMenuColabora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuColaboraActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuColabora);

        subMenuAgendaAnual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        subMenuAgendaAnual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Agendamento.png"))); // NOI18N
        subMenuAgendaAnual.setText("Agenda Anual");
        subMenuAgendaAnual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuAgendaAnualActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuAgendaAnual);

        subMenuAgendamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        subMenuAgendamentos.setText("Agendamento (Consultas)");
        subMenuAgendamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuAgendamentosActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuAgendamentos);

        subMenuBaixa.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuBaixa.setText("Baixa Agendamento");
        subMenuBaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuBaixaActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuBaixa);

        subMenuTrabalho.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        subMenuTrabalho.setText("Trabalhos");
        subMenuTrabalho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuTrabalhoActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuTrabalho);

        subMenuCoroa.setText("Coroa");
        subMenuCoroa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuCoroaActionPerformed(evt);
            }
        });
        menuAdm.add(subMenuCoroa);

        menu.add(menuAdm);

        menuTesouraria.setText("Tesouraria");

        subMenuPagamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        subMenuPagamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/calculadoraIcon.png"))); // NOI18N
        subMenuPagamentos.setText("Pagamentos");
        subMenuPagamentos.setPreferredSize(new java.awt.Dimension(150, 22));
        menuTesouraria.add(subMenuPagamentos);

        subMenuRecebimentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        subMenuRecebimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Fechamento.png"))); // NOI18N
        subMenuRecebimentos.setText("Recebimentos");
        menuTesouraria.add(subMenuRecebimentos);

        subMenuMensalidades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        subMenuMensalidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Relatorio.png"))); // NOI18N
        subMenuMensalidades.setText("Mensalidades");
        menuTesouraria.add(subMenuMensalidades);

        subMenuDepositos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        subMenuDepositos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/iconCalendar.png"))); // NOI18N
        subMenuDepositos.setText("Depósitos");
        menuTesouraria.add(subMenuDepositos);

        menu.add(menuTesouraria);

        menuBiblioteca.setText("Biblioteca");

        subMenuLivros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        subMenuLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Tarefas.png"))); // NOI18N
        subMenuLivros.setText("Livros");
        subMenuLivros.setPreferredSize(new java.awt.Dimension(150, 22));
        menuBiblioteca.add(subMenuLivros);

        subMenuPatente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuPatente.setText("Patentear Livros");
        menuBiblioteca.add(subMenuPatente);

        subMenuReservas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuReservas.setText("Reservas");
        menuBiblioteca.add(subMenuReservas);

        menu.add(menuBiblioteca);

        menuRelatorios.setText("Relatórios");

        subMenuRelUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16RelatorioGrupo.png"))); // NOI18N
        subMenuRelUsuarios.setText("Usuários");
        subMenuRelUsuarios.setPreferredSize(new java.awt.Dimension(150, 22));
        menuRelatorios.add(subMenuRelUsuarios);

        subMenuRelMediuns.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelMediuns.setText("Médiuns");
        menuRelatorios.add(subMenuRelMediuns);

        subMenuRelEventos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelEventos.setText("Eventos");
        menuRelatorios.add(subMenuRelEventos);

        subMenuRelAgenda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelAgenda.setText("Agenda");
        menuRelatorios.add(subMenuRelAgenda);

        subMenuRelLivros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelLivros.setText("Livros");
        menuRelatorios.add(subMenuRelLivros);

        subMenuRelTesouraria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelTesouraria.setText("Tesouraria");
        menuRelatorios.add(subMenuRelTesouraria);

        subMenuRelSaidas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuRelSaidas.setText("Corôas - Saídas");
        subMenuRelSaidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuRelSaidasActionPerformed(evt);
            }
        });
        menuRelatorios.add(subMenuRelSaidas);

        subMenuConsultas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        subMenuConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16RelatorioGrupo2.png"))); // NOI18N
        subMenuConsultas.setText("Agendamentos (Consultas)");
        subMenuConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuConsultasActionPerformed(evt);
            }
        });
        menuRelatorios.add(subMenuConsultas);

        menu.add(menuRelatorios);

        menuConfiguracoes.setText("Configurações");

        subMenuBancoDados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        subMenuBancoDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/config.jpg"))); // NOI18N
        subMenuBancoDados.setText("Banco de dados");
        subMenuBancoDados.setMinimumSize(new java.awt.Dimension(150, 22));
        subMenuBancoDados.setPreferredSize(new java.awt.Dimension(150, 22));
        subMenuBancoDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuBancoDadosActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuBancoDados);

        subMenuConfigUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        subMenuConfigUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/sistejm/images/icon16x16Grupo2.png"))); // NOI18N
        subMenuConfigUsuarios.setText("Usuários");
        subMenuConfigUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuConfigUsuariosActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuConfigUsuarios);

        subMenuAcesso.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        subMenuAcesso.setText("Acesso");
        subMenuAcesso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuAcessoActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuAcesso);

        subMenuOrixas.setText("Orixás");
        subMenuOrixas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuOrixasActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuOrixas);

        subMenuEntidades.setText("Entidades");
        subMenuEntidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuEntidadesActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuEntidades);

        subMenuExu.setText("Exu");
        subMenuExu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuExuActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuExu);

        subMenuEres.setText("Erês");
        subMenuEres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuEresActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuEres);

        subMenuCaboclos.setText("Caboclos");
        subMenuCaboclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuCaboclosActionPerformed(evt);
            }
        });
        menuConfiguracoes.add(subMenuCaboclos);

        menu.add(menuConfiguracoes);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMeio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBaixo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelMeio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelBaixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subMenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuSairActionPerformed

        this.sair();
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuSairActionPerformed

    public void abreBanco(){
        con = new Conexao();
        
        try {
            con.getConnection();
            
        } catch (IOException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        config = new Configuracoes();
        String pathBanco = "C:\\sistejm\\config\\banco.dat";
        String pathUser = "C:\\sistejm\\config\\datauser.dat";
        
        config.extendOriginal(pathBanco);
        config.extendOriginal(pathUser);
        
        boasVindas();

    }//GEN-LAST:event_formWindowOpened

    
    public void boasVindasNormal(){
        preencheCampo("Bem-vindo ao SISTEJM !");
        preencheCampo("============================== \n");
        preencheCampo("Hoje é " + dataHoje() + " \n");
    }
    
    public void boasVindas(){
        campoTexto.setText("");
        usuario = new Usuario();
        config = new Configuracoes();
        
        con = new Conexao();
        preencheCampo("Bem-vindo ao SISTEJM !");
        preencheCampo("============================== \n");
        preencheCampo("Hoje é " + dataHoje() + " \n");
        preencheCampo("iniciando o servidor...\n");
        txtData.setText(dataHoje());
        config.verificaPastas();
        try {
//            if(con.iniciaServer()){
                con.getConnection();
                usuario.verificaPrimeiroUsuario();
                preencheCampo("Banco conectado.");
//            }else{
//                preencheCampo("Banco não conectado");
//            }
//            if (!con.getConnection().equals(null)){
//                usuario.verificaPrimeiroUsuario();
//            }
//Houve um erro na tentativa de conexão. Descrição: null (usuários)

        }catch (IOException ex) {
            config.gravaErroLog("Descrição do erro: " + ex.getMessage(), "Conexão Banco de dados", "sistejm.conexao");
            preencheCampo("Banco desconectado.");
        }        
        // TODO add your handling code here:
        //preencheCampo("\nVerificação concluída");        
    }
    
    public void chamaBD(){
        
        JOptionPane.showConfirmDialog(null, "Banco de dados desconectado. Deseja reconectar ?");
        BancoDados banco = new BancoDados(this, false);
        banco.setLocationRelativeTo(banco);
        banco.setTitle("Banco de Dados");
        banco.setVisible(true);
        preencheCampo("\nAbrindo banco de dados...");        
    }
    
    private void subMenuBancoDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuBancoDadosActionPerformed
        chamaBD();

        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuBancoDadosActionPerformed

    private void subMenuMediunsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuMediunsActionPerformed

        DPesquisaMedium mediuns = new DPesquisaMedium(this, false);
//        DSelecionaOpMedium mediuns = new DSelecionaOpMedium(this, false);
        mediuns.setLocationRelativeTo(mediuns);
        mediuns.setTitle("SELECIONE O MÉDIUM");
//        mediuns.recebeUsuario(iduser, user);
        mediuns.setVisible(true);
        mediuns.setAutoRequestFocus(true);
        preencheCampo("\nAbrindo seleção de operação dos Médiuns...");
        config = new Configuracoes();
        config.gravaAtividades("Médiuns", this.user, "Seleção de operação de médiuns");
//        DMediumGeral mediuns = new DMediumGeral(this, false);
////        DSelecionaOpMedium mediuns = new DSelecionaOpMedium(this, false);
//        mediuns.setLocationRelativeTo(mediuns);
//        mediuns.setTitle("MÉDIUNS");
//        mediuns.recebeUsuario(iduser, user);
//        mediuns.setVisible(true);
//        preencheCampo("\nAbrindo seleção de operação dos Médiuns...");
//        config = new Configuracoes();
//        config.gravaAtividades("Médiuns", this.user, "Seleção de operação de médiuns");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuMediunsActionPerformed

    private void subMenuOrixasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuOrixasActionPerformed

        DOrixa orixa = new DOrixa(this, false);
        orixa.setLocationRelativeTo(orixa);
        orixa.tipoDialog("orixas");
        orixa.setTitle("Orixás");
        orixa.setNomeUsuario(this.user);
        orixa.setVisible(true);
        preencheCampo("\nAbrindo Orixás...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuOrixasActionPerformed

    private void subMenuEntidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuEntidadesActionPerformed
        DOrixa entidade = new DOrixa(this, false);
        entidade.setLocationRelativeTo(entidade);
        entidade.tipoDialog("entidade");
        entidade.setTitle("Entidade das almas");
        entidade.setNomeUsuario(user);
        entidade.setVisible(true);
        preencheCampo("\nAbrindo Entidades...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuEntidadesActionPerformed

    private void subMenuExuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuExuActionPerformed
        DOrixa exu = new DOrixa(this, false);
        exu.setLocationRelativeTo(exu);
        exu.tipoDialog("exu");
        exu.setTitle("Exu");
        exu.setNomeUsuario(user);
        exu.setVisible(true);
        preencheCampo("\nAbrindo Exu...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuExuActionPerformed

    private void subMenuLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuLoginActionPerformed

        abrePanelLogin();
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuLoginActionPerformed

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed

        abrePanelLogin();
        // TODO add your handling code here:
    }//GEN-LAST:event_btLoginActionPerformed

    private void subMenuAgendamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuAgendamentosActionPerformed

        DAgendamento agendamento = new DAgendamento(this, false);
        agendamento.setLocationRelativeTo(agendamento);
        agendamento.setTitle("Atendimento (Consultas)");
        agendamento.recebeUsuario(iduser, user);
        agendamento.setVisible(true);
        preencheCampo("\nAbrindo Atendimento - Consultas...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuAgendamentosActionPerformed

    private void aubMenuEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aubMenuEventosActionPerformed

        DEventos evento = new DEventos(this, false);
        evento.setLocationRelativeTo(evento);
        evento.identificaOperacao("evento");
        evento.setTitle("Evento");
        evento.setVisible(true);
        evento.recebeCodUser(this.iduser, this.user);
        preencheCampo("\nAbrindo Eventos...");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_aubMenuEventosActionPerformed

    private void subMenuProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuProdutosActionPerformed

        DEventos produto = new DEventos(this, false);
        produto.setTitle("Produtos");
        produto.setLocationRelativeTo(produto);
        produto.identificaOperacao("produto");
        produto.setVisible(true);
        produto.recebeCodUser(this.iduser, this.user);
        preencheCampo("\nAbrindo Produtos...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuProdutosActionPerformed

    private void subMenuAgendaAnualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuAgendaAnualActionPerformed

        DAgenda agenda = new DAgenda(this, false);
        agenda.setLocationRelativeTo(agenda);
        agenda.setVisible(true);
        preencheCampo("\nAbrindo Agenda Anual...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuAgendaAnualActionPerformed

    private void subMenuColaboraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuColaboraActionPerformed

        DColaboracoes colab = new DColaboracoes(this, false);
        colab.setLocationRelativeTo(colab);
        colab.setTitle("Colaborações");
        colab.setVisible(true);
        preencheCampo("\nAbrindo Colaborações...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuColaboraActionPerformed

    private void subMenuConfigUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuConfigUsuariosActionPerformed

        DUsuario user = new DUsuario(this, false);
        
        user.setLocationRelativeTo(user);
        user.setTitle("Usuários");
        user.setVisible(true);
        preencheCampo("\nAbrindo Usuários...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuConfigUsuariosActionPerformed

    private void subMenuNovoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuNovoUsuarioActionPerformed
        usuario = new Usuario();
        config = new Configuracoes();
        //String pathUser = "C:\\sistejm\\config\\datauser.properties";
        try {
            if(usuario.verificaAcessoAtivo()){
                DPrimeiroUsuario primeiro = new DPrimeiroUsuario(this, false);
                primeiro.setLocationRelativeTo(primeiro);
                primeiro.setTitle("Primeiro Usuário");
                preencheCampo("\nAbrindo primeiro usuário...");
                primeiro.setVisible(true);
            }else{
                DNovoUsuario novo = new DNovoUsuario(this, false);
                novo.setLocationRelativeTo(novo);
                novo.setTitle("Novo Usuário");
                preencheCampo("\nAbrindo novos usuários...");
                novo.setVisible(true);
            }
            
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_subMenuNovoUsuarioActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        config = new Configuracoes();
        String pathUser = "C:\\sistejm\\config\\datauser.properties";
        
        config.extendNew(pathUser);
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        config = new Configuracoes();
        String pathUser = "C:\\sistejm\\config\\datauser.properties";
        
        config.extendNew(pathUser);
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowDeactivated
        config = new Configuracoes();
        String pathUser = "C:\\sistejm\\config\\datauser.properties";
        
        config.extendNew(pathUser);
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowDeactivated

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        sairTelaLogin(evt);
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(txtUsuario.getText().length() > 0){
                txtSenha.setText("");
                txtSenha.requestFocus();
            }else{
                preencheCampo("\nO campo não pode ficar vazio.");
            }
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        sairTelaLogin(evt);
        usuario = new Usuario();
        config = new Configuracoes();
        
            if(evt.getKeyCode() == KeyEvent.VK_ENTER){
                if(txtSenha.getText().length() > 0){
                
                    usuario.setNome(txtUsuario.getText());
                    usuario.setSenha(txtSenha.getText());
                
                    this.setIduser(usuario.consultaLoginUsuario());
                    if(this.iduser > 0){
                        //System.out.println(this.getIduser());
//                        preencheCampo("\nUsuário localizado.");
                        autorizaUsuario();
                        limpaPanelLogin();
                        fechaPanelLogin();

                    }else{
                        preencheCampo("\nUsuário inexistente");
                    }
                }else{
                    preencheCampo("\nO campo não pode ficar vazio.");
                }
            }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void subMenuLogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuLogoffActionPerformed
        config = new Configuracoes();
        if(JOptionPane.showConfirmDialog(null, "Deseja efetuar o logoff?", "ATENÇÃO", JOptionPane.YES_NO_OPTION) == 0){
            this.acesso = "";
            this.user = "";
            this.iduser = 0;
            txtAcesso.setText("Não está logado.");
            bloqueiaLogoff(false);
            bloqueiaLogin(true);
            bloqueiaMenus(false);
            btLogin.setEnabled(true);
            config.gravaAtividades("Logoff do", this.user, "Sistema");

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuLogoffActionPerformed

    private void subMenuAcessoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuAcessoActionPerformed

        DAcesso acesso = new DAcesso(this, false);
        acesso.setLocationRelativeTo(acesso);
        acesso.setTitle("ACESSO - CONSULTAS");
        acesso.setVisible(true);
        preencheCampo("\nAbrindo acessos...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuAcessoActionPerformed

    private void subMenuEresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuEresActionPerformed

        DOrixa ere = new DOrixa(this, false);
        ere.setLocationRelativeTo(ere);
        ere.tipoDialog("ere");
        ere.setTipo("ere");
        ere.setTitle("ERÊS");
        ere.setNomeUsuario(user);
        ere.setVisible(true);
        preencheCampo("\nAbrindo Erês...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuEresActionPerformed

    private void subMenuCaboclosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuCaboclosActionPerformed

        DOrixa caboclo = new DOrixa(this, false);
        caboclo.setLocationRelativeTo(caboclo);
        caboclo.tipoDialog("caboclo");
        caboclo.setTipo("caboclo");
        caboclo.setTitle("CABOCLOS");
        caboclo.setNomeUsuario(user);
        caboclo.setVisible(true);
        preencheCampo("\nAbrindo Caboclos...");
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuCaboclosActionPerformed

    private void subMenuConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuConsultasActionPerformed

        DRelAgendamento rel = new DRelAgendamento(this, false);
        rel.setLocationRelativeTo(rel);
        rel.setTitle("Relatório de Agendamento | Atendimento");
        rel.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuConsultasActionPerformed

    private void subMenuBaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuBaixaActionPerformed

        DBaixaAg baixa = new DBaixaAg(this, false);
        baixa.setLocationRelativeTo(baixa);
        baixa.setTitle("Baixa de Agendamento");
        baixa.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuBaixaActionPerformed

    private void subMenuTrabalhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuTrabalhoActionPerformed

        DTrabalhos trab = new DTrabalhos(this, false);
        trab.setTitle("Selecione o tipo de trabalho");
        trab.setLocationRelativeTo(trab);
        trab.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuTrabalhoActionPerformed

    private void subMenuCoroaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuCoroaActionPerformed

        config = new Configuracoes();
        DCoroa coroa = new DCoroa(this, false);
        coroa.setTitle("Coroa - Agendamentos");
        coroa.recebeInfo(iduser, user);
        coroa.setAutoRequestFocus(true);
        coroa.setLocationRelativeTo(coroa);
        config.gravaAtividades("Corôa", this.user, "Operação de corôas de médiuns em geral");
        coroa.setVisible(true);

    }//GEN-LAST:event_subMenuCoroaActionPerformed

    private void subMenuRelSaidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuRelSaidasActionPerformed

        config = new Configuracoes();
        DRelatorioCoroa relcoroa = new DRelatorioCoroa(this, false);
        relcoroa.setTitle("RELATÓRIO COROAÇÃO | SAÍDAS");
        relcoroa.recebeInfo(this.iduser, user);
        relcoroa.setAutoRequestFocus(true);
        relcoroa.setLocationRelativeTo(relcoroa);
        config.gravaAtividades("Corôa", this.user, "Abertura de relatório de Corôas");
        relcoroa.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_subMenuRelSaidasActionPerformed

    public void autorizaUsuario(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT t.id_usuario, t.nome, a.* FROM tblusuario t "
                + "INNER JOIN acesso a ON t.codAcesso = a.idacesso "
                + "WHERE t.id_usuario = " + this.iduser;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                this.setUser(rs.getString("nome"));
                this.setIduser(rs.getInt("id_usuario"));
                this.setAcesso(rs.getString("acesso"));
                
                txtAcesso.setText(this.user + " | " + this.acesso);
                preencheCampo("\nBem-vindo(a), " + user);
//                preencheCampo("\nO seu nível de acesso é " + acesso);
                verificaAcesso();
                bloqueiaLogoff(true);
                bloqueiaLogin(false);
                btLogin.setEnabled(false);

                
            }else{
                config.gravaErroLog("Usuário não localizado.", "Usuário", "sistejm.usuario");
                System.out.println("Problemas.");
            }
            rs.close();
            stmt.close();
        }catch(Exception ex){
            System.out.println("Catch usuário ativado. Erro: " + ex.getMessage());
        }
        
        
    }
    
    public void bloqueiaLogin(boolean valor){
        subMenuLogin.setEnabled(valor);
    }
    
    public void verificaAcesso(){
        config = new Configuracoes();
        
        preencheCampo("\nCriando o registro de atividades...");
        config.gravaTituloAtividade(user);
        preencheCampo("\nCriado com êxito.");
//                        config.gravaAtividades("Entrada no ", txtUsuario.getText(), "sistema");
        
        switch(this.acesso){
            case "Administrador":
                acessoAministrador();
                break;
            case "Administração":
                acessoAministracao();
                break;
            case "Tesouraria":
                acessoTesouraria();
                break;
            case "Biblioteca":
                acessoBiblioteca();
                break;
            case "Usuário":
                acessoUsuario();
                break;
        }
    }

    
    public void acessoAministrador(){
        bloqueiaMenus(true);
    }
    public void acessoAministracao(){
        bloqueiaMenus(true);
    }
    public void acessoTesouraria(){
        bloqueiaMenus(true);
    }
    public void acessoBiblioteca(){
        bloqueiaMenus(true);
    }
    public void acessoUsuario(){
        bloqueiaMenus(true);
    }

    
    public void recebeDados(String userLogado, String acessoLogado, int idUsuario){
        this.acesso = acessoLogado;
        this.user = userLogado;
        this.iduser = idUsuario;
        
        System.out.println("Usuário: " + this.user);
        System.out.println("Acesso: " + this.acesso);
        System.out.println("Id do Usuário: " + this.iduser);
        txtAcesso.setText(this.acesso);
        System.out.println("Identifica JTextField:: " + txtAcesso.getText());        
//        labelAcesso.setText("Logado");
//        labelAcesso.setText(user);
        //fechaPanelLogin();
        
        //JOptionPane.showMessageDialog(null, "Logado");
//
//        System.out.println("Usuário: " + this.user);
//        System.out.println("Acesso: " + this.acesso);
//        System.out.println("Id do Usuário: " + this.iduser);
//        labelAcesso.setVisible(true);
//        labelAcesso.setText("Os acessos foram dados.");
        //labelAcesso.setText(this.acesso);
    }
    
    public void sairTelaLogin(KeyEvent ev){
        if(ev.getKeyCode() == KeyEvent.VK_ESCAPE){
            limpaPanelLogin();
            fechaPanelLogin();        
        }
    }
    public void limpaPanelLogin(){
        txtUsuario.setText("");
        txtSenha.setText("");
    }
    
    public void abrePanelLogin(){
        panelLogin.setVisible(true);
        limpaPanelLogin();
        txtUsuario.requestFocus();
        preencheCampo("\nInforme o usuário e a senha nos campos ao lado e dê <enter>...");
    }
    public void fechaPanelLogin(){
        limpaPanelLogin();
        panelLogin.setVisible(false);
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
//                if ("Windows".equals(info.getName())) {
                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aubMenuEventos;
    private javax.swing.JButton btLogin;
    private javax.swing.JTextArea campoTexto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel labelAcesso;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelEsqueciSenha;
    private javax.swing.JLabel labelImage;
    private javax.swing.JLabel labelImagemOrixas;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuAdm;
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenu menuBiblioteca;
    private javax.swing.JMenu menuConfiguracoes;
    private javax.swing.JMenu menuRelatorios;
    private javax.swing.JMenu menuTesouraria;
    private javax.swing.JPanel panelBaixo;
    private javax.swing.JPanel panelEsqueciSenha;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPanel panelMeio;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JMenuItem subMenuAcesso;
    private javax.swing.JMenuItem subMenuAgendaAnual;
    private javax.swing.JMenuItem subMenuAgendamentos;
    private javax.swing.JMenuItem subMenuBaixa;
    private javax.swing.JMenuItem subMenuBancoDados;
    private javax.swing.JMenuItem subMenuCaboclos;
    private javax.swing.JMenuItem subMenuColabora;
    private javax.swing.JMenuItem subMenuConfigUsuarios;
    private javax.swing.JMenuItem subMenuConsultas;
    private javax.swing.JMenuItem subMenuCoroa;
    private javax.swing.JMenuItem subMenuDepositos;
    private javax.swing.JMenuItem subMenuEntidades;
    private javax.swing.JMenuItem subMenuEres;
    private javax.swing.JMenuItem subMenuExu;
    private javax.swing.JMenuItem subMenuLivros;
    private javax.swing.JMenuItem subMenuLogin;
    private javax.swing.JMenuItem subMenuLogoff;
    private javax.swing.JMenuItem subMenuMediuns;
    private javax.swing.JMenuItem subMenuMensalidades;
    private javax.swing.JMenuItem subMenuNovoUsuario;
    private javax.swing.JMenuItem subMenuOrixas;
    private javax.swing.JMenuItem subMenuPagamentos;
    private javax.swing.JMenuItem subMenuPatente;
    private javax.swing.JMenuItem subMenuProdutos;
    private javax.swing.JMenuItem subMenuRecebimentos;
    private javax.swing.JMenuItem subMenuRelAgenda;
    private javax.swing.JMenuItem subMenuRelEventos;
    private javax.swing.JMenuItem subMenuRelLivros;
    private javax.swing.JMenuItem subMenuRelMediuns;
    private javax.swing.JMenuItem subMenuRelSaidas;
    private javax.swing.JMenuItem subMenuRelTesouraria;
    private javax.swing.JMenuItem subMenuRelUsuarios;
    private javax.swing.JMenuItem subMenuReservas;
    private javax.swing.JMenuItem subMenuSair;
    private javax.swing.JMenuItem subMenuTrabalho;
    private javax.swing.JToolBar toolbar;
    private javax.swing.JTextField txtAcesso;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtConfirmaUsuario;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
