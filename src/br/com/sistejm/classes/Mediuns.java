/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


/**
 *
 * @author Michel
 */
public class Mediuns {
    
    private int idMedium;
    private String nomeMedium;
    private int codUsuario;
    private String dataCadastro;
    private String dataNascimento;
    private String matricula;
    private int isentoMensal;
    private String observacoes;
    private String dataEntrada;
    private String email;
    private int ativo;
    private String sexo;
    private String funcao;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    Calendar cal;
    Mensalidade m;

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        config = new Configuracoes();
        String novoFormato = config.retornaFormatoDataSQL(dataEntrada);

        this.dataEntrada = novoFormato;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public int getIsentoMensal() {
        return isentoMensal;
    }

    public void setIsentoMensal(int isentoMensal) {
        this.isentoMensal = isentoMensal;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        config = new Configuracoes();
        String novoFormatoNascimento = config.retornaFormatoDataSQL(dataNascimento);
        
        this.dataNascimento = novoFormatoNascimento;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        config = new Configuracoes();
        String novoFormato = config.retornaFormatoDataSQL(dataCadastro);
        
        this.dataCadastro = novoFormato;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdMedium() {
        return idMedium;
    }

    public void setIdMedium(int idMedium) {
        this.idMedium = idMedium;
    }

    public String getNomeMedium() {
        return nomeMedium;
    }

    public void setNomeMedium(String nomeMedium) {
        this.nomeMedium = nomeMedium;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public void preencheTabMedium(JTable tabela){

        String sql = "SELECT DISTINCT m.*, mo.codMedium AS codeMedium FROM mediuns m " +
                      "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium "
                + "    ORDER BY m.nome ASC";
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

//            medium.addColumn("ID");
            medium.addColumn("Matrícula");
            medium.addColumn("Médium");
            medium.addColumn("Ativo");

//            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(30);

            while(rs.next()){
//                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                String ativo = null;
                
                if(rs.getString("ativo").equals("1")){
                    ativo = "Sim";
                }else{
                    ativo = "Não";
                }
//                medium.addRow(new Object[]{matricula, nome});
//                medium.addRow(new Object[]{id, matricula, nome, ativo});
                medium.addRow(new Object[]{matricula, nome, ativo});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }        
    }
    
    public class Renderer extends DefaultTableCellRenderer {
       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
          if (row%2 != 0) {
             setBackground(Color.RED);
          } else {
             setBackground(Color.WHITE);
          }
          return this; 
       }
    }
    
    public void buscaTabMedium(JTable tabela, String tipoBusca, JTextField texto){

        String sql = "SELECT * FROM mediuns WHERE " + tipoBusca + " LIKE '%" + texto.getText() + "%' "
                + "ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nome = rs.getString("nome");
                medium.addRow(new Object[]{nome});
            }
        }catch(Exception ex){
            System.out.println("Erro na busca de tabela de Médiuns. Mensagem: " + ex.getMessage());
        }
    }
    public void buscaTabMediumMatricula(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM mediuns WHERE matricula LIKE '%" + texto.getText() + "%' "
                + " ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Matrícula");
            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(110);

            while(rs.next()){
                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                medium.addRow(new Object[]{id, matricula, nome});
            }
        }catch(Exception ex){
            System.out.println("Erro na busca de tabela de Médiuns. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT nome FROM mediuns WHERE nome = '" + this.nomeMedium + "'";
//        System.out.println(sql);
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
           
        }catch(Exception ex){
            System.out.println("Catch verificação de duplicidade de Médium ativado. Erro: " + ex.getMessage());
        }
//        JOptionPane.showMessageDialog(null, "Médium existente.");
        return false;
    }
    
    public boolean incluirMedium(){
        con = new Conexao();
        
        if(!this.verificaExistente()){
//            this.idMedium = con.ultimoId("mediuns", "idmedium");
            String sql = "INSERT INTO mediuns (idmedium, nome, dataCadastro, dataNascimento, "
                    + "dataEntrada, matricula, isentoMensal, ativo, observacoes, email, sexo, cod_usuario) "
                    + "VALUES (" + this.idMedium + ", '" + this.nomeMedium + "', '" + this.dataCadastro + "', '"
                    + this.dataNascimento + "', '" + this.dataEntrada + "', '" + this.matricula + "', "
                    + " " + this.isentoMensal + ", " + this.ativo + ", '" + this.observacoes + "', "
                    + "'" + this.email + "', '" + this.sexo + "', " + this.codUsuario + ")";
            
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                cal = new GregorianCalendar();
                m = new Mensalidade();
                m.setAno(cal.get(Calendar.YEAR));
                m.setCodMedium(this.idMedium);
                m.verificaAno();

                config.gravaBDBackup(sql);
                return true;

            }catch(Exception ex){
                System.out.println("Catch inclusão de Médium ativado. Erro: " + ex.getMessage());
            }
        }
        JOptionPane.showMessageDialog(null, "Médium existente. Utilize o botão'Pesquisar'");
        return false;
    }
    
    public boolean alterarMedium(){
        String sql = "UPDATE mediuns SET "
                + "nome = '" + this.nomeMedium + "', "
                + "dataNascimento = '" + this.dataNascimento + "',  "
                + "dataEntrada = '" + this.dataEntrada + "', "
                + "matricula = '" + this.matricula + "', "
                + "isentoMensal = " + this.isentoMensal + ", "
                + "ativo = " + this.ativo + ", "
                + "observacoes = '" + this.observacoes + "', "
                + "email = '" + this.email + "', "
                + "sexo = '" + this.sexo + "', "
                + "funcao = '" + this.funcao + "' "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na alteração. Descrição: " + ex.getMessage(), "Alteração dos campos do médium", "sistejm.alteramedium");
        }
        return false;
    }
    
    public boolean alterarFuncaoMedium(){
        config = new Configuracoes();
        
        String sql = "UPDATE mediuns SET "
                + "funcao = '" + this.funcao + "' "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro ao alterara a função. Descrição: " + ex.getMessage(), "Alteração da função do médium", "sistejm.alterfunmedium");
        }
        return false;
    }
    public boolean alterarIsentoMedium(){
        config = new Configuracoes();
        
        String sql = "UPDATE mediuns SET "
                + "isentoMensal = " + this.isentoMensal + " "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro ao alterar a isenção de mensalidade. Descrição: " + ex.getMessage(), "Alteração da isenção de mensalidade do médium", "sistejm.altisenmedium");
        }
        return false;
    }
    public boolean alterarEmailMedium(){
        config = new Configuracoes();
        
        String sql = "UPDATE mediuns SET "
                + "email = '" + this.email + "' "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            config.gravaBDBackup(sql);
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro ao alterar o e-mail. Descrição: " + ex.getMessage(), "Alteração e-mail do médium", "sistejm.emailmedium");
        }
        return false;
    }
    public boolean alterarSexoMedium(){
        config = new Configuracoes();
        
        String sql = "UPDATE mediuns SET "
                + "sexo = '" + this.sexo + "' "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            config.gravaBDBackup(sql);
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro o sexo do médium. Descrição: " + ex.getMessage(), "Alteração do sexo do médium", "sistejm.sexomedium");
        }
        return false;
    }
    public boolean alterarDataNascimentoMedium(){
        config = new Configuracoes();
        if(this.dataNascimento.equals("")){
            this.setDataNascimento("01/01/1901");
        }
        
        String sql = "UPDATE mediuns SET "
                + "dataNascimento = '" + this.dataNascimento + "' "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            config.gravaBDBackup(sql);
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro ao alterar a data de nascimento. Descrição: " + ex.getMessage(), "Alteração da Data de Nascimento do médium", "sistejm.datanascmedium");
        }
        return false;
    }
    public boolean excluirMedium(){
        String sql = "DELETE FROM mediuns WHERE idmedium = " + this.idMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            config.gravaBDBackup(sql);
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro ao excluir o médium. Descrição: " + ex.getMessage(), "Exclusão do médium", "sistejm.excluimedium");
        }
        return false;
    }
    
    public void preencheComboMedium(JComboBox combo){

        String nome = null;
        try{
            String sql = "SELECT nome FROM mediuns";
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
//            rs.first();

            while(rs.next()){
                nome = rs.getString("nome");
                combo.addItem(nome);
            }

        }catch(IOException | SQLException ex){
            System.out.println(" Erro: " + ex.getMessage());
        }
    }
    public void preencheComboMediumNIsentos(JComboBox combo){

        String nome = null;
        try{
            String sql = "SELECT nome FROM mediuns WHERE isentoMensal = 0";
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
//            rs.first();

            while(rs.next()){
                nome = rs.getString("nome");
                combo.addItem(nome);
            }

        }catch(IOException | SQLException ex){
            System.out.println(" Erro: " + ex.getMessage());
        }
    }
    public int retornaIdMedium(String texto){// colocar a variável OO

        String sql = "SELECT * FROM mediuns WHERE nome =  '" + texto + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            rs.next();
            
            return rs.getInt("idmedium");
        }catch(Exception ex){
            System.out.println("Erro na busca de tabela de Médiuns. Mensagem: " + ex.getMessage());
        }
        return 0;
    }
    public int retornaIdMediumPorMatricula(String texto){

        String sql = "SELECT * FROM mediuns WHERE matricula =  '" + texto + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            rs.next();
            
            return rs.getInt("matricula");
        }catch(Exception ex){
            System.out.println("Erro na busca de tabela de Médiuns. Mensagem: " + ex.getMessage());
        }
        return 0;
    }
    
    public void exibeCamposMedium(JTextField id, JTextField nome, JTextField matricula, JCheckBox isento, 
            JTextField dataNascimento, JTextField dtEntrada, JTextArea observacoes, JTextField endereco, 
            JTextField bairro, JTextField cidade, JTextField estado, JRadioButton ativoSim, JRadioButton ativoNao, 
            JRadioButton rMasc, JRadioButton rFemin, JTextField email, JTextField funcao){
        
        this.idMedium = Integer.valueOf(id.getText());
        
        config = new Configuracoes();
        
        String sql = "SELECT m.idmedium, m.matricula, m.nome, m.isentoMensal, "
                + "DATE_FORMAT(m.dataNascimento, '%d/%m/%Y') AS dtNasc, "
                + "DATE_FORMAT(m.dataEntrada, '%d/%m/%Y') AS dtEnt, m.observacoes, "
                + "m.ativo, m.email, m.sexo, m.funcao, l.endereco, l.bairro, l.cidade, l.estado "
                + "FROM mediuns m "
                + "LEFT JOIN logradouro l ON l.cod_medium = m.idmedium "
                + "WHERE m.idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                String idMedium = rs.getString("m.idmedium");
                String n = rs.getString("m.nome");
                String matr = rs.getString("m.matricula");
                
                int rIsentoMensal = rs.getInt("m.isentoMensal");
                String sex = rs.getString("m.sexo");
                int mediumAtivo = rs.getInt("ativo");
                String dtNasc = rs.getString("dtNasc");
                String dtEnt = rs.getString("dtEnt");
                String obs = rs.getString("m.observacoes");
                String em = rs.getString("m.email");
                String end = rs.getString("l.endereco");
                String bair = rs.getString("l.bairro");
                String cid = rs.getString("l.cidade");
                String est = rs.getString("l.estado");
                String fun = rs.getString("m.funcao");
                
                id.setText(idMedium);
                nome.setText(n);
                matricula.setText(matr);

                if(mediumAtivo == 1){
                    ativoSim.setSelected(true);
                }else{
                    ativoNao.setSelected(true);
                }
                switch(sex){
                    case "m":
                        rMasc.setSelected(true);
                        rFemin.setSelected(false);
                        break;
                    case "f":
                        rMasc.setSelected(false);
                        rFemin.setSelected(true);
                        break;
                    case " ":
                        rMasc.setSelected(false);
                        rFemin.setSelected(false);
                        break;
                    default:
                        rMasc.setSelected(false);
                        rFemin.setSelected(false);
                        break;
                        
                }
                if(rIsentoMensal == 1){
                    isento.setSelected(true);
                }else{
                    isento.setSelected(false);
                }
//                matricula.setText(rs.getString("m.matricula"));
                if(dtNasc.equals("01/01/1901") || dtNasc.equals("")){
                    dataNascimento.setText("");
                }else{
                    dataNascimento.setText(dtNasc);
                }
                
                if(dtEnt.equals("01/01/1901") || dtEnt.equals("")){
                    dtEntrada.setText("");
                }else{
                    dtEntrada.setText(dtEnt);
                }
                if(!obs.equals("")){
                    observacoes.setText(obs);
                }else{
                    observacoes.setText("");
                }
                if(!em.equals(" ")){
                    email.setText(rs.getString("email"));
                }else{
                    em = " ";
                    email.setText(em);
                }
                if(!end.equals("")){
                    endereco.setText(end);
                }else{
                    endereco.setText("");
                }
                if(!bair.equals("")){
                    bairro.setText(bair);
                }else{
                    bairro.setText("");
                }
                if(!cid.equals("")){
                    cidade.setText(cid);
                }else{
                    cidade.setText("");
                }
                if(!est.equals("")){
                    estado.setText(est);
                }else{
                    estado.setText("");
                }
                if(!fun.equals("")){
                    funcao.setText(fun);
                }else{
                    funcao.setText("");
                }
            }
            
        }catch(NullPointerException e){
            System.out.println("Há campos null");
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro no campos do médium. Erro: " + ex.getMessage(), "Preenchimento dos campos do médium", "sistejm.campomedium");
        }// + " SQL: " + sql
        
    }

    public void preencheTabNomeMedium(JTable tabela){

        String compAtivo = null;
        
        if(this.ativo == 9){
            compAtivo = "";
        }else{
            compAtivo = "WHERE m.ativo = " + this.ativo;
        }
        
        String sql = "SELECT nome FROM mediuns m "
                + compAtivo + " "
                + "ORDER BY nome ASC";
        
//        System.out.println(sql);
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nome = rs.getString("nome");
                medium.addRow(new Object[]{nome});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }        
    }
    
    public void preencheTabNomeMediumRestrito(JTable tabela, int codmedium){

        String sql = "SELECT DISTINCT * FROM mediuns m WHERE m.idmedium <> " + codmedium;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nome = rs.getString("nome");
                medium.addRow(new Object[]{nome});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }        
    }    
    public void buscaTabNomeMedium(JTable tabela, String texto){

        String compAtivo = null;
        
        if(this.ativo == 9){
            compAtivo = "";
        }else{
            compAtivo = "AND m.ativo = " + this.ativo;
        }
        
        String sql = "SELECT m.nome FROM mediuns m "
                + "WHERE m.nome LIKE '%" + texto + "%' "
                + compAtivo + " "
                + "ORDER BY nome ASC";
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nome = rs.getString("nome");
                medium.addRow(new Object[]{nome});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }        
    }

    public void preencheTaMediumNIsentos(JTable tabela){
        config = new Configuracoes();

        String sql = "SELECT nome FROM mediuns WHERE isentoMensal = 0";
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nome = rs.getString("nome");

                medium.addRow(new Object[]{nome});
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na execução da tabela de médiuns na mensalidade. Erro: " + ex.getMessage(), "Tabela de Médiuns | Mensalidade", "sistejm.tabmensal");
        }        
    }
    public String retornaFuncao(String texto){
        config = new Configuracoes();

        String sql = "SELECT funcao, nome FROM mediuns WHERE nome = '" + texto + "'";
     
        String funcao = null;
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            rs.next();

            if(rs.absolute(1)){
                funcao = rs.getString("funcao");

            }else{
                funcao = "MÉDIUM";
            }
            return funcao;
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na pesquisa de função do médium. Erro: " + ex.getMessage(), "Função do Médiuns", "sistejm.funcaomedium");
        }
        return null;
    }
    
    public void confereAniversariantesNoMes(JTable tabela, int mes){
        
        String dataNascimento = null;
        
        String sql = "SELECT nome, DATE_FORMAT(dataNascimento, '%d/%m/%Y') AS nascimento FROM mediuns "
                + "   ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");
            medium.addColumn("Data de Aniversário");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(80);

            while(rs.next()){
                dataNascimento = rs.getString("nascimento");
                String dataNascDia = dataNascimento.substring(0, 2);
                String dataNascMes = dataNascimento.substring(3, 5);
                
                if(dataNascMes.equals(mes)){
                    String dataNasc = dataNascDia + "/" + dataNascMes;
                    String nome = rs.getString("nome");
                    medium.addRow(new Object[]{nome, dataNasc});
                }
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na aniversário dos médiuns. Erro: " + ex.getMessage(), "Aniversário dos Médiuns", "sistejm.nivermediuns");
        }        
        
    }
    
    public void confereAniversariantesNoDia(JTable tabela){
        cal = new GregorianCalendar();
        
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        
        String sql = "SELECT nome, DATE_FORMAT(dataNascimento, '%d/%m/%Y') AS nascimento FROM mediuns "
                + "   ORDER BY nome ASC";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");
            medium.addColumn("Data de Aniversário");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                dataNascimento = rs.getString("nascimento");
                String dataNascDia = dataNascimento.substring(0, 2);
                String dataNascMes = dataNascimento.substring(3, 5);
                
                if(dataNascMes.equals(mes)){
                    if(dataNascDia.equals(dia)){
                        String dataNasc = dataNascDia + "/" + dataNascMes;
                        String nome = rs.getString("nome");
                        medium.addRow(new Object[]{nome, dataNasc});
                    }
                }
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na aniversário dos médiuns. Erro: " + ex.getMessage(), "Aniversário dos Médiuns", "sistejm.nivermediuns");
        }        
        
        
    }
    public int confereQuantAniversariantes(){
        cal = new GregorianCalendar();
        
//        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int quant = 0;
        
        String sql = "SELECT COUNT(idmedium) AS quant, DATE_FORMAT(dataNascimento, '%d/%m/%Y') AS nascimento "
                + "FROM mediuns "
                + "WHERE DATE_FORMAT(dataNascimento, '%m') = " + mes
                ;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                quant = rs.getInt("quant");
            }
            return quant;
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro na aniversário dos médiuns. Erro: " + ex.getMessage(), "Aniversário dos Médiuns", "sistejm.nivermediuns");
        }
        return 0;
    }
    
    
}
