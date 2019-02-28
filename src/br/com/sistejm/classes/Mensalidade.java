/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Michel
 */
public class Mensalidade {
    
    private int idMensalidade;
    private int ano;
    private int mes;
    private String dataPagamento;
    private String pago;
    private String valor;
    private int codMedium;
    private int codUsuario;

    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }
    
    
    public int getIdMensalidade() {
        return idMensalidade;
    }

    public void setIdMensalidade(int idMensalidade) {
        this.idMensalidade = idMensalidade;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(String dataPagamento) {
        config = new Configuracoes();
        String formatoSQL = config.retornaFormatoDataSQL(dataPagamento);

        this.dataPagamento = formatoSQL;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public void verificaAno(){
        
        String sql = "SELECT ano FROM mensalidade WHERE ano = " + this.ano + " "
                + "AND cod_medium = " + this.codMedium;
        
//        System.out.println(sql);
        
        try{
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(!rs.absolute(1)){
                this.valor = "20,00";
                incluiMeses();
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de verificação de ano. Erro: " + ex.getMessage(), "Mensalidade", "sistejm.anomensal");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.anomensal para mais informações");
            
        }
    }
    
    public void incluiMeses(){
        con = new Conexao();
        config = new Configuracoes();
        
        for(int i = 1; i <= 12; i++){
            this.idMensalidade = con.ultimoId("mensalidade", "idmensalidade");

            String sql = "INSERT INTO mensalidade (idmensalidade, ano, mes, data_pagamento, pago, valor, cod_medium, codUsuario) "
                    + "VALUES "
                    + "("
                    + this.idMensalidade + ", " + this.ano + ", " + i + "," + "'1901-01-01', 'n', '" + this.valor + "' "
                    + ", " + this.codMedium + ", " + this.codUsuario
                    + ")"
                    ;
//            System.out.println(sql);
            try{

                con = new Conexao();
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
//                rs = stmt.executeQuery(sql);
//
//                rs.next();
//
//                if(!rs.absolute(1)){
//                    incluiMeses();
//                }

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão dos meses de ano. Erro: " + ex.getMessage(), "Iclusão de meses - Mensalidade", "sistejm.mesmensal");
                JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.mesmensal para mais informações");

            }        
        }
    }
    
    public void consultaMesesPagos(JCheckBox jan, JCheckBox fev, JCheckBox mar, JCheckBox abr, 
            JCheckBox mai, JCheckBox jun, JCheckBox jul, JCheckBox ago, JCheckBox set, JCheckBox out, 
            JCheckBox nov, JCheckBox dez){
        
        String sql = "SELECT * FROM mensalidade "
                + "WHERE ano = " + this.ano + " "
                + "AND cod_medium = " + this.codMedium;
        
//                System.out.println(sql);
        
        try{
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                if(rs.getString("pago").equals("s")){
                    if(rs.getInt("mes") == 1){
                        jan.setSelected(true);
                    }
                    if(rs.getInt("mes") == 2){
                        fev.setSelected(true);
                    }
                    if(rs.getInt("mes") == 3){
                        mar.setSelected(true);
                    }
                    if(rs.getInt("mes") == 4){
                        abr.setSelected(true);
                    }
                    if(rs.getInt("mes") == 5){
                        mai.setSelected(true);
                    }
                    if(rs.getInt("mes") == 6){
                        jun.setSelected(true);
                    }
                    if(rs.getInt("mes") == 7){
                        jul.setSelected(true);
                    }
                    if(rs.getInt("mes") == 8){
                        ago.setSelected(true);
                    }
                    if(rs.getInt("mes") == 9){
                        set.setSelected(true);
                    }
                    if(rs.getInt("mes") == 10){
                        out.setSelected(true);
                    }
                    if(rs.getInt("mes") == 11){
                        nov.setSelected(true);
                    }
                    if(rs.getInt("mes") == 12){
                        dez.setSelected(true);
                    }
                }else if(rs.getString("pago").equals("n")){
                    if(rs.getInt("mes") == 1){
                        jan.setSelected(false);
                    }
                    if(rs.getInt("mes") == 2){
                        fev.setSelected(false);
                    }
                    if(rs.getInt("mes") == 3){
                        mar.setSelected(false);
                    }
                    if(rs.getInt("mes") == 4){
                        abr.setSelected(false);
                    }
                    if(rs.getInt("mes") == 5){
                        mai.setSelected(false);
                    }
                    if(rs.getInt("mes") == 6){
                        jun.setSelected(false);
                    }
                    if(rs.getInt("mes") == 7){
                        jul.setSelected(false);
                    }
                    if(rs.getInt("mes") == 8){
                        ago.setSelected(false);
                    }
                    if(rs.getInt("mes") == 9){
                        set.setSelected(false);
                    }
                    if(rs.getInt("mes") == 10){
                        out.setSelected(false);
                    }
                    if(rs.getInt("mes") == 11){
                        nov.setSelected(false);
                    }
                    if(rs.getInt("mes") == 12){
                        dez.setSelected(false);
                    }
                }


            }
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de inclusão dos meses de ano. Erro: " + ex.getMessage(), "Iclusão de meses - Mensalidade", "sistejm.mesmensal");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.mesmensal para mais informações");
            
        }      
    }
    
    public boolean excluiMensalidade(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM mensalidade WHERE cod_medium = " + this.codMedium;
        
        try{
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão de mensalidade. Erro: " + ex.getMessage(), "Exclusão | Mensalidade", "sistejm.excluimensal");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.excluimensal para mais informações");
        }
        return false;
    }
    
    public void marcaPagamento(){
        config = new Configuracoes();
        
        String sql = "UPDATE mensalidade SET pago = 's', "
                + "data_pagamento = '" + this.dataPagamento + "', valor = '" + this.valor + "'  "
                + "WHERE "
                + "cod_medium = " + this.codMedium +" "
                + "AND mes = " + this.mes + " "
                + "AND ano = " + this.ano;
        
//        System.out.println(sql);
        
        try{
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de alteração de mensalidade. Erro: " + ex.getMessage(), "UPDATE | Mensalidade", "sistejm.updatemensal");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.updatemensal para mais informações");
        }
    }
    
    public void desmarcaPagamento(){
        config = new Configuracoes();
        
        String sql = "UPDATE mensalidade SET pago = 'n', "
                + "data_pagamento = '" + this.dataPagamento + "', valor = '" + this.valor + "' "
                + "WHERE "
                + "cod_medium = " + this.codMedium + " "
                + "AND mes = " + this.mes + " "
                + "AND ano = " + this.ano;
        
        try{
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de alteração de mensalidade. Erro: " + ex.getMessage(), "UPDATE | Mensalidade", "sistejm.updatemensal");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.updatemensal para mais informações");
        }
        
    }
    public String ultimoPagamento(int idMedium){

        String sql = "SELECT DATE_FORMAT(me.data_pagamento, '%d/%m/%Y') AS ultimoPagamento " +
                    "FROM mediuns m " +
                    "LEFT JOIN mensalidade me ON me.cod_medium = m.idmedium " +
                    "WHERE m.ativo = 1 " +
                    "AND me.pago = 's' " +
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
                return rs.getString("ultimoPagamento");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public String quantMeses(String pago, int idMedium, int codperiodo, int mes1, int ano1, int mes2, int ano2){

        String compPeriodo = "";
        String compMedium = "";
        
        if(idMedium != 0){
            compMedium = "AND m.idmedium = " + idMedium + " ";
        }

        switch(codperiodo){
            case 1: //por ano

                compPeriodo = " AND me.ano = " + ano1 + " ";
                break;
            case 2: // pelo mes e ano
                compPeriodo = " AND me.mes = " + mes1 +
                        " AND me.ano = " + ano1 + " ";
                break;
            case 3: // pelo período de mes e ano a mes ano
                compPeriodo = " AND me.mes BETWEEN " + mes1 + " AND " + mes2 +
                        " AND me.ano BETWEEN " + ano1 + " AND " + ano2;
                break;
        }
        
        String sql = "SELECT COUNT(me.idmensalidade) AS quantidade FROM mensalidade me "
                + "LEFT JOIN mediuns m ON m.idmedium = me.cod_medium "
                + "WHERE me.pago = '" + pago + "' "
                + compMedium
//                + "AND m.idmedium = " + idMedium + " "
                + compPeriodo;
        
//        System.out.println(sql);
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("quantidade");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public String retornaQuantNPagosSintetico(){
        
        String compMedium = null;
        
        if(this.codMedium == 0){
            compMedium = "";
        }else{
            compMedium = "AND me.cod_medium = " + this.codMedium;
        }
        String sql = "SELECT COUNT(me.idmensalidade) AS quantidadeNPagos " +
                        "FROM mensalidade me " +
                        "WHERE me.pago = 'n' " +
                        "AND me.ano = " + this.ano + " " +
                        compMedium;
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("quantidadeNPagos");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public String retornaValoresNPagosSintetico(){
        String compMedium = null;
        
        if(this.codMedium == 0){
            compMedium = "";
        }else{
            compMedium = "AND me.cod_medium = " + this.codMedium;
        }
        
        String sql = "SELECT REPLACE(CAST(SUM(me.valor) AS DECIMAL(15, 2)), '.', ',') AS valoresPendentes " +
                        "FROM mensalidade me " +
                        "WHERE me.pago = 'n' " +
                        "AND me.ano = " + this.ano + " " +
                        compMedium;
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("valoresPendentes");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public String retornaQuantPagosSintetico(){
        String compMedium = null;
        
        if(this.codMedium == 0){
            compMedium = "";
        }else{
            compMedium = "AND me.cod_medium = " + this.codMedium;
        }
        
        String sql = "SELECT COUNT(me.idmensalidade) AS quantidadePagos " +
                    "FROM mensalidade me " +
                    "WHERE me.pago = 's' " +
                    "AND me.ano = " + this.ano + " " +
                    compMedium;
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("quantidadePagos");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public String retornaValoresPagosSintetico(){
        String compMedium = null;
        
        if(this.codMedium == 0){
            compMedium = "";
        }else{
            compMedium = "AND me.cod_medium = " + this.codMedium;
        }
        
        String sql = "SELECT REPLACE(CAST(SUM(me.valor) AS DECIMAL(15, 2)), '.', ',') AS valoresPagos " +
                        "FROM mensalidade me " +
                        "WHERE me.pago = 's'" +
                        "AND me.ano = " + this.ano + " " +
                        compMedium;
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getString("valoresPagos");
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    
}

