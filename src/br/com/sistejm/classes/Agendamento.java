/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Agendamento {
    private String data;
    private String consultor;
    private double valor;
    private String consultado;
    private String TConsultado;
    private int idagendamento;
    public int codusuario;
    public String codigo;
    public String pago;
    private String semana;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCodusuario() {
        return codusuario;
    }

    public void setCodusuario(int codusuario) {
        this.codusuario = codusuario;
    }

    public int getIdagendamento() {
        return idagendamento;
    }

    public void setIdagendamento(int idagendamento) {
        this.idagendamento = idagendamento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        config = new Configuracoes();
        
        this.data = config.retornaFormatoDataSQL(data);
    }

    public String getConsultor() {
        return consultor;
    }

    public void setConsultor(String consultor) {
        this.consultor = consultor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getConsultado() {
        return consultado;
    }

    public void setConsultado(String consultado) {
        this.consultado = consultado;
    }

    public String getTConsultado() {
        return TConsultado;
    }

    public void setTConsultado(String TConsultado) {
        this.TConsultado = TConsultado;
    }
    
    public void consultaAgendamento(JTextField id, JTextField date, JTextField valor, 
            JRadioButton radioCab, JRadioButton radioExu, JRadioButton radioNMedium, 
            JRadioButton radioMedium, JTextField nome, JTextField codigo, JRadioButton radioSPago, 
            JRadioButton radioNPago, JLabel semana, JLabel status){
        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dtf FROM agendamento "
                + "WHERE idagendamento = " + id.getText();
        
//        System.out.println("SQL: " + sql);OK
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                date.setText(rs.getString("dtf"));
                valor.setText(rs.getString("valor"));
                if(rs.getString("consultor").equals("Caboclo")){
                    radioCab.setSelected(true);
                    radioExu.setSelected(false);
                }else{
                    radioExu.setSelected(true);
                    radioCab.setSelected(false);
                }
                
                if(rs.getString("tconsultado").equals("N")){
                    radioNMedium.setSelected(true);
                    radioMedium.setSelected(false);
                }else{
                    radioNMedium.setSelected(false);
                    radioMedium.setSelected(true);
                }
                if(rs.getString("pago").equals("N")){
                    radioNPago.setSelected(true);
                    radioSPago.setSelected(false);
                }else{
                    radioNPago.setSelected(false);
                    radioSPago.setSelected(true);
                }
                if(rs.getString("baixa").equals("S")){
                    status.setForeground(Color.GREEN);
                    status.setText("Atendido");
                }else{
                    status.setForeground(Color.RED);
                    status.setText("Não atendido");
                }
                semana.setText(rs.getString("semana"));
                nome.setText(rs.getString("consultado"));
                codigo.setText(rs.getString("codigo"));
            }
            
            
        }catch(Exception ex){
            System.out.println("Erro na consulta de agendamento. Descrição: " + ex.getMessage());
        }
    }
    
    public void preencheTabAgendamento(JTable tabela){

        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dataFormatada FROM agendamento";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            config = new Configuracoes();
                
            DefaultTableModel agendamento = new DefaultTableModel();
            tabela.setModel(agendamento);

            agendamento.addColumn("ID");
            agendamento.addColumn("Código");
            agendamento.addColumn("Data");
            agendamento.addColumn("Consultor");
            agendamento.addColumn("Consultado");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(100);

            while(rs.next()){
                int idAgendamento = rs.getInt("idagendamento");
                String codigo = rs.getString("codigo");
                String data = rs.getString("dataFormatada");
                String consultor = rs.getString("consultor");
                String consultado = rs.getString("consultado");
                String medium = rs.getString("tconsultado");

                agendamento.addRow(new Object[]{idAgendamento, codigo, data, consultor, consultado, medium});

            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agendamento. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabAgendamento(JTable tabela, String coluna, String valor, String data1, String data2){

        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dataFormatada "
                + "FROM agendamento WHERE " + coluna + " LIKE '%" + valor + "' "
                + "AND data BETWEEN '" + data1 + "' AND '" + data2 + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel agendamento = new DefaultTableModel();
            tabela.setModel(agendamento);

            agendamento.addColumn("ID");
            agendamento.addColumn("Código");
            agendamento.addColumn("Consultado");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);

            while(rs.next()){
                int idAgendamento = rs.getInt("idagendamento");
                String codigo = rs.getString("codigo");
                String consultado = rs.getString("consultado");

                agendamento.addRow(new Object[]{idAgendamento, codigo, consultado});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agendamento. Mensagem: " + ex.getMessage());
        } 
    }
    public void retornaQuantidade(JLabel labelQuantidade, String data1, String data2){

        String sql = "SELECT COUNT(idagendamento) AS quantidade "
                + "FROM agendamento WHERE "
                + "data BETWEEN '" + data1 + "' AND '" + data2 + "'";
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            rs.next();
            if(rs.absolute(1)){
                labelQuantidade.setText(rs.getString("quantidade"));
            }else{
                labelQuantidade.setText("Vazio");
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agendamento. Mensagem: " + ex.getMessage());
        }
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT * FROM agendamento "
                + "WHERE "
//                + "consultor = '" + this.consultor + "' "
//                + "AND consultado = '" + this.consultado +"' "
                + "codigo = '" + this.codigo + "' ";
//                + "AND data = '" + this.data + "' ";
        
        System.out.println("SQL Verif Existente: " + sql);
        
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
            System.out.println("Catch verificação de duplicidade de agenda ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean incluirAgendamento(){
        con = new Conexao();
        if(!this.verificaExistente()){
            
            this.idagendamento = con.ultimoId("agendamento", "idagendamento");

            String sql = "INSERT INTO agendamento (idagendamento, data, consultor, valor, consultado, tconsultado, codigo, codusuario) "
                    + "VALUES "
                    + "(" + this.idagendamento + ", '" + this.data + "', '" + this.consultor + "', " + this.valor + ", "
                    + "'" + this.consultado + "', '" + this.TConsultado + "', '" + this.codigo + "', " + this.codusuario + ")";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                System.out.println("Catch inclusão de Agendamento ativado. Erro: " + ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nome já existente neste dia e com o mesmo consultor.");
        }
        return false;
    }
    
    public String codigoAgendamento(JTextField txtData, String tConsultado, String consultor){

        con = new Conexao();
        int selectConultor = 0;
        
        String ano = txtData.getText().substring(6, 10);
        String mes = txtData.getText().substring(3, 5);
        String dia = txtData.getText().substring(0, 2);
        
        String codData = String.valueOf(dia + mes + ano);
        
        
        if(consultor.equals("Caboclo")){
            selectConultor = 1;
        }else if(consultor.equals("Exu")){
            selectConultor = 2;
        }
        int uId = con.ultimoId("agendamento", "idagendamento");
        String code = "TEJM." + uId + "|" + selectConultor + "|" + codData + "-" + tConsultado;
        
        this.codigo = code;

        return code;
    }    
    
    public boolean alterarAgendamento(){
        String sql = "UPDATE agendamento SET data = '" + this.data + "', codigo = '" + this.codigo + "', "
                + "consultor = '" + this.consultor + "', valor = " + this.valor + ", consultado = '" + this.consultado + "',"
                + "tconsultado = '" + this.TConsultado + "', pago = '" + this.pago + "', semana = '" + this.semana + "', codusuario = " + this.codusuario
                + " WHERE idagendamento = " + this.idagendamento;

//                System.out.println("SQL altera: " + sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch alteração de Agendamento ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    public boolean excluirAgendamento(){
        String sql = "DELETE FROM agendamento WHERE idagendamento = " + this.idagendamento;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            System.out.println("Catch exclusão de Agendamento ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public void preencheComboNome(JComboBox combo){
        String sql = "SELECT consultado, baixa FROM agendamento WHERE baixa = 'N'";
        String nome;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                nome = rs.getString("consultado");
                combo.addItem(nome);
            }
            
            rs.close();
            stmt.close();
        }catch(Exception ex){
            System.out.println("Catch ativado no combo do agendamento (consultado). Erro: " + ex.getMessage());
        }
    }
    public void preencheComboCodigo(JComboBox combo){
        String sql = "SELECT codigo, baixa FROM agendamento WHERE baixa = 'N'";
        String nome;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                nome = rs.getString("codigo");
                combo.addItem(nome);
            }
        }catch(Exception ex){
            System.out.println("Catch ativado no combo do agendamento (código). Erro: " + ex.getMessage());
        }
    }
    
    public String contAgendamento(int dia, int mes, int ano){
        
        int mesAntererior = mes - 1;
        
        String dataAtual = ano + "-" + mes + "-" + dia;
        String dataAnterior = ano + "-" + mesAntererior + "-" + dia;
        
        String sql = "SELECT COUNT(idagendamento) AS qtde "
                + "FROM agendamento WHERE data BETWEEN '" + dataAnterior + "' AND '" + dataAtual + "'";

        System.out.println(sql);
        
        try{
        
        con = new Conexao();
        conn = con.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        
        rs.next();
        
        if(rs.absolute(1)){
            return rs.getString("qtde");
        }else{
            return "0";
        }
            
        }catch(Exception ex){
            System.out.println("Exception contagem ativado. Erro: " + ex.getMessage());
        }
        
        return "0";
    }
    
    public void preencheCampos(JTable tabela){
        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dataFormatada "
                + "FROM agendamento "
                + "WHERE baixa = 'N'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            config = new Configuracoes();
                
            DefaultTableModel agendamento = new DefaultTableModel();
            tabela.setModel(agendamento);

            agendamento.addColumn("Código");
            agendamento.addColumn("Consultado");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(50);

            while(rs.next()){
                String codigo = rs.getString("codigo");
                String consultado = rs.getString("consultado");

                agendamento.addRow(new Object[]{codigo, consultado});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de agendamento. Mensagem: " + ex.getMessage());
        }        
    }
    
    public void retornaPesquisaTabela(String comprovante, JLabel nome, JLabel codigo, JLabel consultor, JLabel data){
        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dataFormatada "
                + "FROM agendamento WHERE baixa = 'N' "
                + "AND codigo = '" + comprovante + "'";
                
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                nome.setText(rs.getString("consultado"));
                codigo.setText(rs.getString("codigo"));
                consultor.setText(rs.getString("consultor"));
                data.setText(rs.getString("dataFormatada"));
            }
        }catch(Exception ex){
            System.out.println("Catch ativado no combo do agendamento (código). Erro: " + ex.getMessage());
        }
        
    }
    
    public boolean alteraBaixaAgendamento(){
        String sql = "UPDATE agendamento SET baixa = 'S' WHERE codigo = '" + this.codigo + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
//            if(rs.absolute(1)){
                return true;
//            }
                        
        }catch(Exception ex){
            System.out.println("Erro ao atualizar a baixa do comprovante. Descrição: " + ex.getMessage());
        }
        return false;
    }
    public boolean alteraNBaixaAgendamento(){
        String sql = "UPDATE agendamento SET baixa = 'N' WHERE codigo = '" + this.codigo + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
//            if(rs.absolute(1)){
                return true;
//            }
                        
        }catch(Exception ex){
            System.out.println("Erro ao atualizar a inclusão do comprovante. Descrição: " + ex.getMessage());
        }
        return false;
    }

    
}
