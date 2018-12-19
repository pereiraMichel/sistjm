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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

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
    Calendar cal;

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
    
   public boolean verificaCoroaAnterior(){
        config = new Configuracoes();
        con = new Conexao();
        
        int tipoCoroAnterior = this.codtipocoroa - 1;
        String sql = "SELECT * FROM coroa "
                + "WHERE codmedium = " + this.codmedium + " "
                + "AND codtipocoroa = " + tipoCoroAnterior;
        
//        System.out.println(sql);
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
             if(rs.absolute(1)){
                if(rs.getString("confirma").equals("n")){
                    return true;
                }else{
                    return false;
                }
             }
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Verificação da Corôa", "sistejm.verifmarcacoroa");
        }
        return false;
   }
    public boolean marcaCoroa(){
        config = new Configuracoes();
        con = new Conexao();

        if(verificaCoroaAnterior()){
            JOptionPane.showMessageDialog(null, "Existe saídas anteriores pendentes");
            return false;
        }else{
            String sql = "UPDATE coroa SET "
                    + "mes = " + this.mes + ", "
                    + "ano = " + this.ano + ", "
                    + "dataRealizacao = '" + this.dataRealizacao + "', "
                    + "confirma = '" + this.confirma + "' "
                    + "WHERE codmedium = " + this.codmedium + " "
                    + "AND codtipocoroa = " + this.codtipocoroa;

    //        System.out.println(sql);

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
        
    }
    public boolean desmarcaCoroa(){
        config = new Configuracoes();
        con = new Conexao();
        
        String sql = "UPDATE coroa SET "
                + "mes = " + this.mes + ", "
                + "ano = " + this.ano + ", "
                + "dataRealizacao = '" + this.dataRealizacao + "', "
                + "confirma = '" + this.confirma + "' "
                + "WHERE codmedium = " + this.codmedium + " "
                + "AND codtipocoroa = " + this.codtipocoroa;

//        System.out.println(sql);
        
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
    
    public void preencheTabSaidasNConfirmadasGeral(JTable tabela){
        config = new Configuracoes();
        cal = new GregorianCalendar();
        
        int anoAnterior = cal.get(Calendar.YEAR) - 1;
        int anoPosterior = cal.get(Calendar.YEAR) + 1;
        String sql = "SELECT m.nome, m.ativo, tp.nome AS tipo, c.confirma, c.mes, c.ano "
                + "FROM mediuns m  "
                + "LEFT JOIN coroa c ON c.codmedium = m.idmedium "
                + "LEFT JOIN tipocoroa tp ON tp.idtipocoroa = c.codtipocoroa "
                + "WHERE m.ativo = 1 "
                + "AND c.confirma = 'n' "
                + "AND c.ano  BETWEEN " + anoAnterior + " AND " + anoPosterior;
        
//        System.out.println(sql);
        
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
            medium.addColumn("Tipo");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(20);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                String mes = rs.getString("c.mes");
                String ano = rs.getString("c.ano");
                String tipo = rs.getString("tipo");

                medium.addRow(new Object[]{nome, mes, ano, tipo});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de saídas a confirmar do Médium. Erro: " + ex.getMessage(), "Tabela de Saídas não confirmadas do Médium", "sistejm.saidasmedium");
        }            
    }    
    public void preencheTabSaidasMesAnoCorrente(JTable tabela){
        config = new Configuracoes();
        
        String sql = "SELECT m.nome, m.ativo, tp.nome AS tipo, c.confirma, c.mes, c.ano "
                + "FROM mediuns m  "
                + "LEFT JOIN coroa c ON c.codmedium = m.idmedium "
                + "LEFT JOIN tipocoroa tp ON tp.idtipocoroa = c.codtipocoroa "
                + "WHERE m.ativo = 1 "
                + "AND c.confirma = 'n' "
                + "AND c.mes = " + this.mes + " "
                + "AND c.ano = " + this.ano;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Nome");
            medium.addColumn("Mês");
            medium.addColumn("Ano");
            medium.addColumn("Tipo");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(20);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                String mes = rs.getString("c.mes");
                String ano = rs.getString("c.ano");
                String tipo = rs.getString("tipo");

                medium.addRow(new Object[]{nome, mes, ano, tipo});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de saídas do Médium (por mes/ano corrente). Erro: " + ex.getMessage(), "Saída do Médium - por mes/ano corrente", "sistejm.saidacorrente");
        }            
    }
    
    public void preencheTabSaidasPeriodo(JTable tabela, int mes1, int mes2, int ano1, int ano2){
        config = new Configuracoes();
        String complemento = null;
        
        if((mes1 == 0 && ano1 == 0) && (mes2 == 0 && ano2 == 0)){
            complemento = "";
        }else{
            complemento = "  AND c.mes BETWEEN " + mes1 +" AND " + mes2 +
                            " AND c.ano BETWEEN " + ano1 + " AND " + ano2;
        }
        
        String sql = "SELECT m.nome, m.ativo, tp.nome AS tipo, c.confirma, c.mes, c.ano "
                + "FROM mediuns m  "
                + "LEFT JOIN coroa c ON c.codmedium = m.idmedium "
                + "LEFT JOIN tipocoroa tp ON tp.idtipocoroa = c.codtipocoroa "
                + "WHERE m.ativo = 1 "
                + "AND c.confirma = 'n' "
                + complemento;
        
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("Nome");
            medium.addColumn("Mês");
            medium.addColumn("Ano");
            medium.addColumn("Tipo");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(20);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                String mes = rs.getString("c.mes");
                String ano = rs.getString("c.ano");
                String tipo = rs.getString("tipo");

                medium.addRow(new Object[]{nome, mes, ano, tipo});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de saídas do Médium | Orixá. Erro: " + ex.getMessage(), "Corôa do Médium - Pesquisas", "sistejm.coroaPesquisa");
        }            
    }
    
    public void preencheTabSaidasConfirmadasGeral(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT m.nome, m.ativo, tp.nome AS tipo, c.confirma, c.mes, c.ano, "
                + "DATE_FORMAT(c.dataRealizacao, '%d/%m/%y') AS dataSaida "
                + "FROM mediuns m  "
                + "LEFT JOIN coroa c ON c.codmedium = m.idmedium "
                + "LEFT JOIN tipocoroa tp ON tp.idtipocoroa = c.codtipocoroa "
                + "WHERE m.ativo = 1 "
                + "AND c.confirma = 's' "
                + "AND c.mes = " + this.mes + " "
                + "AND c.ano = " + this.ano;
        
//        System.out.println(sql);
        
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
            medium.addColumn("Tipo");
            medium.addColumn("Data de Saída");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(50);

            while(rs.next()){
                String nome = rs.getString("m.nome");
                String mes = rs.getString("c.mes");
                String ano = rs.getString("c.ano");
                String tipo = rs.getString("tipo");
                String dataSaida = rs.getString("dataSaida");

                medium.addRow(new Object[]{nome, mes, ano, tipo, dataSaida});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de saídas confirmadas do Médium. Erro: " + ex.getMessage(), "Tabela de confirmação de saídas", "sistejm.tabconfirmsaidas");
        }            
    }
    
    public int quantRelatorioSaidas(String confirmado, int idmedium, int periodo, int mes1, int ano1, int mes2, int ano2){

        cal = new GregorianCalendar();
        
        String compMedium = null;
        String compPeriodo = null;
        
        if(idmedium == 0){
            compMedium = "";
        }else{
            compMedium = " AND m.idmedium = " + idmedium;
        }

        switch(periodo){
            case 1: //pelo 3 meses do ano
                    if(mes2 > 12){
                        mes2 = 12;
                        compPeriodo = " AND c.mes BETWEEN " + mes1 + " AND " + mes2 +
                            " AND c.ano = " + ano1;
                    }
                    compPeriodo = " AND c.mes BETWEEN " + mes1 + " AND " + mes2 +
                        " AND c.ano = " + ano1;
                break;
            case 2: // pelo mes e ano
                compPeriodo = " AND c.mes = " + mes1 +
                        " AND ano = " + ano1;
                break;
            case 3: // pelo período de mes e ano a mes ano
                compPeriodo = " AND c.mes BETWEEN " + mes1 + " AND " + mes2 +
                        " AND c.ano BETWEEN " + ano1 + " AND " + ano2;
                break;
        }

        String sql = "SELECT COUNT(m.idmedium) AS quantidade, c.mes, c.ano, m.ativo, m.idmedium, mo.codTipo, "
                + "c.confirma "
                + "FROM mediuns m " +
                "LEFT JOIN coroa c ON c.codmedium = m.idmedium " +
                "LEFT JOIN medium_ori mo ON mo.codMedium = m.idmedium " +
                "LEFT JOIN orixas o ON mo.cod_orixa = o.idorixa " +
                "LEFT JOIN tipocoroa tp ON c.codtipocoroa = tp.idtipocoroa " +
                "WHERE m.ativo = 1 " +
                "AND mo.codTipo = 1 " +
                "AND c.confirma = '" + confirmado + "' " +
                compMedium +
                compPeriodo;
        
//        System.out.println(sql);
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("quantidade");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public String ultimaCoroa(int idMedium){

        String sql = "SELECT DATE_FORMAT(c.dataRealizacao, '%d/%m/%Y') AS ultimadata " +
                    "FROM mediuns m " +
                    "LEFT JOIN coroa c ON c.codmedium = m.idmedium " +
                    "WHERE m.ativo = 1 " +
                    "AND c.confirma = 's' " +
                    "AND m.idmedium = " + idMedium + " " + 
                    "ORDER BY m.idmedium DESC " +
                    "LIMIT 1 ";
        
//        System.out.println(sql);
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("ultimadata");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    
}
