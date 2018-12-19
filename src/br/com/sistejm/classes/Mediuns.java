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
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

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
        String novoFormato = config.retornaFormatoDataSQL(dataNascimento);
        
        this.dataNascimento = novoFormato;
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
                      "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium";
     
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
            medium.addColumn("Ativo");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(50);

            while(rs.next()){
                int id = rs.getInt("idmedium");
                String nome = rs.getString("nome");
                String matricula = rs.getString("matricula");
                String ativo = null;
                
                if(rs.getString("ativo").equals("1")){
                    ativo = "Sim";
                }else{
                    ativo = "Não";
                }
                medium.addRow(new Object[]{id, matricula, nome, ativo});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }        
    }

//    public Component prepareRenderer(TableCellRenderer renderer,
//            int rowIndex, int vColIndex) {
//         
//        DefaultTableModel m = (DefaultTableModel) getModel();
//        Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
//         
//        // altera a cor de background da linha para vermelho e foreground para branco
//        // quando o valor da coluna 3 for igual a fechado 
//        if (m.getValueAt(rowIndex, 2).toString().toLowerCase().equals("fechado")) {
//            c.setBackground(new Color(192, 0, 0));
//            c.setForeground(Color.white);
//        } else {
//            // mantem a cor padrão de foreground 
//            c.setForeground(getForeground());
//             
//            // determina a cor de background da linha selecionada 
//            if(isCellSelected(rowIndex, vColIndex)) {
//                c.setBackground(new Color(184, 207, 229));
//            } else {
//                // linhas não selecionadas, manter cor de background padrão 
//                c.setBackground(getBackground());
//            }
// 
//        }           
//        return c;
////    }
//};
    
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

        String sql = "SELECT * FROM mediuns WHERE " + tipoBusca + " LIKE '%" + texto.getText() + "%'";
        
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
    public void buscaTabMediumMatricula(JTable tabela, JTextField texto){

        String sql = "SELECT * FROM mediuns WHERE matricula LIKE '%" + texto.getText() + "%'";
        
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
                + "email = '" + this.observacoes + "', "
                + "sexo = '" + this.observacoes + "' "
                + " WHERE idmedium = " + this.idMedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch alteração de Médium ativado. Erro: " + ex.getMessage());
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
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de Médium ativado. Erro: " + ex.getMessage());
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
    public int retornaIdMedium(String texto){

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
            JRadioButton rMasc, JRadioButton rFemin, JTextField email){
        
        config = new Configuracoes();
        
        String sql = "SELECT m.idmedium, m.matricula, m.nome, m.isentoMensal, "
                + "DATE_FORMAT(m.dataNascimento, '%d/%m/%Y') AS dataNascimento, "
                + "DATE_FORMAT(m.dataEntrada, '%d/%m/%Y') AS dataEntrada, m.observacoes, "
                + "m.dataEntrada, m.ativo, m.email, m.sexo, l.* "
                + "FROM mediuns m "
                + "LEFT JOIN logradouro l ON l.cod_medium = m.idmedium "
                + "WHERE m.matricula = '" + this.matricula + "'";
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
//            if(rs.absolute(1)){
                int rIsentoMensal = rs.getInt("m.isentoMensal");
                String sex = rs.getString("m.sexo");
                id.setText(rs.getString("m.idmedium"));
                nome.setText(rs.getString("m.nome"));
                int ativo = rs.getInt("ativo");
                //
//                System.out.println(nome.getText());
    
                
                if(ativo == 1){
                    ativoSim.setSelected(true);
                }else{
                    ativoNao.setSelected(true);
                }
                
                if(sex.equals("m")){
                    rMasc.setSelected(true);
                }else if(sex.equals("f")){
                    rFemin.setSelected(true);
                }else{
                    rMasc.setSelected(false);
                    rFemin.setSelected(false);
                }
                
                if(rIsentoMensal == 1){
                    isento.setSelected(true);
                }else{
                    isento.setSelected(false);
                }
                matricula.setText(rs.getString("m.matricula"));
                String dtNasc = rs.getString("dataNascimento");
                if(dtNasc.equals("1901-01-01") || dtNasc.equals("")){
//                    System.out.println("o valor está aqui");
                    dataNascimento.setText("01/01/1901");
                }else{
                    dataNascimento.setText(dtNasc);
                }
//                System.out.println("Nascimento: " + dtNasc);
                if(rs.getString("dataEntrada").equals("1901-01-01") || rs.getString("dataEntrada").equals("")){
                    dtEntrada.setText("01/01/1901");
                }else{
                    dtEntrada.setText(rs.getString("dataEntrada"));
                }
//                System.out.println("Entrada: " + dtEntrada.getText());
    
                observacoes.setText(rs.getString("m.observacoes"));
                email.setText(rs.getString("m.email"));
                endereco.setText(rs.getString("l.endereco"));
                bairro.setText(rs.getString("l.bairro"));
                cidade.setText(rs.getString("l.cidade"));
                estado.setText(rs.getString("l.estado"));
                
//            }
            
        }catch(Exception ex){
            config.gravaErroLog("Houve um erro no campos do médium. Erro: " + ex.getMessage(), "Preenchimento dos campos do médium", "sistejm.campomedium");
        }
        
    }

    public void preencheTabNomeMedium(JTable tabela){

        String sql = "SELECT DISTINCT * FROM mediuns m ";
     
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

        String sql = "SELECT DISTINCT * FROM mediuns m WHERE nome LIKE '%" + texto + "'";
     
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
//    public int retornaIdMedium(JTable tabela, String texto){
//        config = new Configuracoes();
//
//        String sql = "SELECT DISTINCT * FROM mediuns m WHERE nome = '" + texto + "'";
//     
//        try{
//            con = new Conexao();
//            conn = con.getConnection();
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery(sql);
//            rs.next();
//
//            if(rs.absolute(1)){
//                return rs.getInt("idmedium");
//            }
//            
//        }catch(Exception ex){
//            config.gravaErroLog("Erro no retorno. Descrção: " + ex.getMessage(), "Retorno de Id do Médium", "sistejm.retornaidmedium");
//        }
//        return 0;        
//    }
    
}
