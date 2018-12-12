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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Coroa {
    
    private int idcoroa;
    private int mes;
    private int ano;
    private String confirma;
    private int codmedium;
    private int codtipocoroa;
    private String dataRealizacao;
    
    Configuracoes config;
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;

    public String getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(String dataRealizacao) {
        config = new Configuracoes();
        String novoFormato = config.retornaFormatoDataSQL(dataRealizacao);
     
        this.dataRealizacao = novoFormato;
    }
    
    public int getIdcoroa() {
        return idcoroa;
    }

    public void setIdcoroa(int idcoroa) {
        this.idcoroa = idcoroa;
    }

    public int getMes() {
        return mes;
        
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getConfirma() {
        return confirma;
    }

    public void setConfirma(String confirma) {
        this.confirma = confirma;
    }

    public int getCodmedium() {
        return codmedium;
    }

    public void setCodmedium(int codmedium) {
        this.codmedium = codmedium;
    }

    public int getCodtipocoroa() {
        return codtipocoroa;
    }

    public void setCodtipocoroa(int codtipocoroa) {
        this.codtipocoroa = codtipocoroa;
    }
    
    public boolean verificaCamposPadrao(){
        config = new Configuracoes();
        con = new Conexao();

        String sql = "SELECT * FROM coroa WHERE codmedium = " + this.codmedium;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(!rs.absolute(1)){
                incluiCoroas();
                return true;
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ". SQL: " + sql, "Inclusão de Padrão de Corôa", "sistejm.incluicoroa");
            JOptionPane.showMessageDialog(null, "Erro na coroa. Verifique o arquivo sistejm > erro > sistejm.incluicoroa.log no C");
        }
        return false;
    }
    
    public boolean verificaCoroa(){
        config = new Configuracoes();
        con = new Conexao();
        
        String sql = "SELECT * FROM coroa WHERE codmedium = " + this.codmedium;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                alteraCoroa();
            }else{
                insereCoroa();
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ". SQL: " + sql, "Inclusão da Corôa", "sistejm.incluicoroa");
            JOptionPane.showMessageDialog(null, "Erro na coroa. Verifique o arquivo sistejm > erro > sistejm.incluicoroa.log no C");
        }
        return false;
    }
    public boolean insereCoroa(){
        config = new Configuracoes();
        con = new Conexao();
        
        this.idcoroa = con.ultimoId("coroa", "idcoroa");
        
        String sql = "INSERT INTO coroa (idcoroa, mes, ano, confirma, codmedium, codtipocoroa, dataRealizacao) "
                + "VALUES(" + this.idcoroa + ", " + this.mes + ", " + this.ano + ", "
                + "'" + this.confirma + "', " + this.codmedium + ", " + this.codtipocoroa + ", "
                + "'" + this.dataRealizacao + "'" + ")";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                return true;
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ". SQL: " + sql, "Inclusão da Corôa", "sistejm.incluicoroa");
            JOptionPane.showMessageDialog(null, "Erro na coroa. Verifique o arquivo sistejm > erro > sistejm.incluicoroa.log no C");
        }
        return false;
    }
    public boolean alteraCoroa(){
        config = new Configuracoes();
        con = new Conexao();
        
        String sql = "UPDATE coroa SET "
                + "mes = " + this.mes + ", "
                + "ano = " + this.ano + ", "
                + "confirma = '" + this.confirma + "', "
                + "dataRealizacao = '" + this.dataRealizacao + "', "
                + "codtipocoroa = " + this.codtipocoroa + ", "
                + "codmedium = " + this.codmedium
                + "WHERE idcoroa = " + this.idcoroa;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                return true;
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ". SQL: " + sql, "Inclusão da Corôa", "sistejm.incluicoroa");
            JOptionPane.showMessageDialog(null, "Erro na coroa. Verifique o arquivo sistejm > erro > sistejm.incluicoroa.log no C");
        }
        return false;
    }
    
    public boolean marcaCoroa(){
        config = new Configuracoes();
        con = new Conexao();
        
        String sql = "UPDATE coroa SET mes = " + this.mes + ", ano = " + this.ano + ", dataRealizacao = '" + this.dataRealizacao + "', confirma = 's' "
                + "WHERE codmedium = " + this.codmedium + " AND codtipocoroa = " + this.codtipocoroa;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            return true;
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ". SQL: " + sql, "Marcação da Corôa", "sistejm.marcacoroa");
        }
        return false;
    }
    public boolean desmarcaCoroa(){
        config = new Configuracoes();
        con = new Conexao();
        
        String sql = "UPDATE coroa SET mes = " + this.mes + ", ano = " + this.ano + ", dataRealizacao = '" + this.dataRealizacao + "', confirma = 'n' "
                + "WHERE codmedium = " + this.codmedium + " AND codtipocoroa = " + this.codtipocoroa;

        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ". SQL: " + sql, "Alteração da Corôa", "sistejm.alteracoroa");
            JOptionPane.showMessageDialog(null, "Erro na coroa. Verifique o arquivo sistejm > erro > sistejm.alteracoroa.log no C");
        }
        return false;
    }
    
    public void incluiCoroas(){
        con = new Conexao();
        config = new Configuracoes();
        
        for(int i = 2; i <= 7; i++){
            this.idcoroa = con.ultimoId("coroa", "idcoroa");

            String sql = "INSERT INTO coroa (idcoroa, mes, ano, confirma, dataRealizacao, codmedium, codtipocoroa) "
                    + "VALUES "
                    + "("
                    + this.idcoroa + ", 0, 0, " + "'n', " + "'1901-01-01', " + this.codmedium
                    + ", " + i
                    + ")"
                    ;
//            System.out.println(sql);
            try{

                con = new Conexao();
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão dos coroas do médium. Erro: " + ex.getMessage(), "Inclusão de coroas - Médiuns", "sistejm.incluicoroas");
                JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.incluicoroas para mais informações");

            }        
        }
    }
    
    public void consultaCoroaMedium(JTextField coroacao, JTextField umAno, 
            JTextField tresAnos, JTextField seteAnos, JTextField quatorzeAnos, JTextField vinteUmAnos){
        
        config = new Configuracoes();

        String sql = "SELECT *, DATE_FORMAT(dataRealizacao, '%d/%m/%Y') AS dataFRealizacao "
                + "FROM coroa WHERE codmedium = " + this.codmedium;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                if(rs.getInt("codtipocoroa") == 2){ // coroa
                    if(rs.getString("confirma").equals("s")){
                        coroacao.setText(rs.getString("dataFRealizacao"));
                    }else{
                        coroacao.setText(rs.getString("mes") + "/" + rs.getString("ano") + " | A confirmar");
                    }
                }
                if(rs.getInt("codtipocoroa") == 3){// 1 ano
                    if(rs.getString("confirma").equals("s")){
                        umAno.setText(rs.getString("dataFRealizacao"));
                    }else{
                        umAno.setText(rs.getString("mes") + "/" + rs.getString("ano") + " | A confirmar");
                    }
                }
                if(rs.getInt("codtipocoroa") == 4){// 3 anos
                    if(rs.getString("confirma").equals("s")){
                        tresAnos.setText(rs.getString("dataFRealizacao"));
                    }else{
                        tresAnos.setText(rs.getString("mes") + "/" + rs.getString("ano") + " | A confirmar");
                    }
                }
                if(rs.getInt("codtipocoroa") == 5){// 7 anos
                    if(rs.getString("confirma").equals("s")){
                        seteAnos.setText(rs.getString("dataFRealizacao"));
                    }else{
                        seteAnos.setText(rs.getString("mes") + "/" + rs.getString("ano") + " | A confirmar");
                    }
                }
                if(rs.getInt("codtipocoroa") == 6){// 14 anos
                    if(rs.getString("confirma").equals("s")){
                        quatorzeAnos.setText(rs.getString("dataFRealizacao"));
                    }else{
                        quatorzeAnos.setText(rs.getString("mes") + "/" + rs.getString("ano") + " | A confirmar");
                    }
                }
                if(rs.getInt("codtipocoroa") == 7){// 21 anos
                    if(rs.getString("confirma").equals("s")){
                        vinteUmAnos.setText(rs.getString("dataFRealizacao"));
                    }else{
                        vinteUmAnos.setText(rs.getString("mes") + "/" + rs.getString("ano") + " | A confirmar");
                    }
                }
            }
            
        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROR_VBATISMO + ex.getMessage() + ". SQL: " + sql, "Verificação de Batismo", "sistejm.excluibatismo");
        }        
    }
    
    public void exibeAConfirmar(JTable tabela){
        String sql = "SELECT m.nome, c.mes, c.ano, t.nome FROM mediuns m "
                   + "LEFT JOIN coroa c ON c.codmedium = m.idmedium " 
                   + "LEFT JOIN tipocoroa t ON t.idtipocoroa = c.codtipocoroa "
                   + "WHERE c.confirma = 'n'"
                   + "AND mes = " + this.mes
                   + "AND ano = " + this.ano;
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");
            medium.addColumn("Mês");
            medium.addColumn("Ano");
            medium.addColumn("Corôa");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(80);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                int mes = rs.getInt("c.mes");
                int ano = rs.getInt("c.ano");
                String coroa = rs.getString("t.nome");
                medium.addRow(new Object[]{nome, mes, ano, coroa});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }       
    }
    
    public void exibeConfirmados(JTable tabela){
        String sql = "SELECT m.nome, c.mes, c.ano, t.nome FROM mediuns m "
                   + "LEFT JOIN coroa c ON c.codmedium = m.idmedium " 
                   + "LEFT JOIN tipocoroa t ON t.idtipocoroa = c.codtipocoroa "
                   + "WHERE c.confirma = 's'"
                   + "AND mes = " + this.mes
                   + "AND ano = " + this.ano;
     
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Médium");
            medium.addColumn("Mês");
            medium.addColumn("Ano");
            medium.addColumn("Corôa");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(80);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                int mes = rs.getInt("c.mes");
                int ano = rs.getInt("c.ano");
                String coroa = rs.getString("t.nome");
                medium.addRow(new Object[]{nome, mes, ano, coroa});
            }
            
        }catch(Exception ex){
            System.out.println("Erro em tabela de mediuns. Mensagem: " + ex.getMessage());
        }          
    }
    
    public void preencheTabSaidasConfirmadas(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT DISTINCT m.nome FROM mediuns m "
                + "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium  "
                + "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mo.codTipo "
                + "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa";
//        String sql = "SELECT m.nome, o.nome, tp.tipo, tp.idtipo_orixa FROM mediuns m "
//                + "LEFT JOIN medium_ori mo ON m.idmedium = mo.codMedium  "
//                + "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mo.codTipo "
//                + "LEFT JOIN orixas o ON o.idorixa = mo.cod_orixa";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Nome");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                if(tabela.getRowCount() % 2 == 0){
                    tabela.setSelectionBackground(Color.WHITE);
                }
                medium.addRow(new Object[]{nome});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela geral do Médium | Orixá. Erro: " + ex.getMessage(), "Tipo de Orixá", "sistejm.tipoorixa");
        }            
    }    
}
