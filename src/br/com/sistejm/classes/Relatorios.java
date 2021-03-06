/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Michel
 */
public class Relatorios {
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Calendar cal;
    Configuracoes config;

//Agendamento | Atendimento    
    public void geraRelatorioAgendamento(String modelo, String valor, String data1, String data2, String ordem){
        String sql = null;
        switch(modelo){
            case "padrao":
                sql = "SELECT * FROM agendamento WHERE data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            case "data":
                sql = "SELECT * FROM agendamento WHERE data = '" + valor + "' ORDER BY " + ordem;
                break;
            case "consultor":
                sql = "SELECT * FROM agendamento WHERE consultor = '" + valor + "' AND data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            case "consultado":
                sql = "SELECT * FROM agendamento WHERE consultado = '" + valor + "' AND data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            case "tconsultado":
                sql = "SELECT * FROM agendamento WHERE tconsultado = '" + valor + "' AND data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            case "pago":
                sql = "SELECT * FROM agendamento WHERE pago = '" + valor + "' AND data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            case "codigo":
                sql = "SELECT * FROM agendamento WHERE codigo = '" + valor + "' AND data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            default:
                sql = "SELECT * FROM agendamento WHERE data BETWEEN '" + data1 + "' AND '" + data2 +"' ORDER BY " + ordem;
                break;
            
        }
//        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//report_agendamentogeral.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            
            Map param = new HashMap(); // Abre o parâmetro

            
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }

// ==============================================================================
    public void relTabAgend(String data1, String data2, int pago, int baixa, int consultor){

        config = new Configuracoes();
        
        String compPago = null;
        String compBaixa = null;
        String compConsultor = null;
        String compData = null;
        
        switch(pago){
            case 0:
                compPago = "AND pago = 'N' ";
                break;
            case 1:
                compPago = "AND pago = 'S' ";
                break;
            case 9:
                compPago = "";
                break;
        }
        
        switch(baixa){
            case 0:
                compBaixa = "AND baixa = 'N' ";
                break;
            case 1:
                compBaixa = "AND baixa = 'S' ";
                break;
            case 9:
                compBaixa = "";
                break;
        }
        
        switch(consultor){
            case 1:
                compConsultor = "AND consultor = 'Caboclo' ";
                break;
            case 2:
                compConsultor = "AND consultor = 'Exu' ";
                break;
            case 9:
                compConsultor = "";
                break;
        }

        if(data1.equals("sem data") || data2.equals("sem data")){
            compData = "";
        }else{
            compData = "AND data BETWEEN '" + config.retornaFormatoDataSQL(data1) + "' "
                    + "AND '" + config.retornaFormatoDataSQL(data2) + "' ";
        }
        
        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dataFormat "
                + "FROM agendamento "
                + "WHERE "
                + "idagendamento IS NOT NULL "
                + compData
                + compPago
                + compBaixa
                + compConsultor + " "
                + "ORDER BY consultor"
                ;
        
                System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//report_agendamentogeral.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            
            Map param = new HashMap(); // Abre o parâmetro

            
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


// ==============================================================================    
    
// Comprovante de agendamento
    public void geraComprovanteAtendimento(String comprovante){
        String sql = "SELECT *, DATE_FORMAT(data, '%d/%m/%Y') AS dataFormatada FROM agendamento WHERE codigo = '" + comprovante + "'";
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//report_comprovanteagendamento.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            
            Map param = new HashMap(); // Abre o parâmetro

            
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }

// Saídas    
    public void relatorioSaidas(String confirmado, int idmedium, int periodo, int mes1, int ano1, int mes2, int ano2){

        cal = new GregorianCalendar();
        
        String compMedium = null;
        String compPeriodo = null;
        String compConfirm = null;
        
        if(idmedium == 0){
            compMedium = "";
        }else{
            compMedium = " AND m.idmedium = " + idmedium;
        }
        
        if(confirmado.equals("n")){
            compConfirm = "@dataS := '' AS dataSaida, ";
        }else if(confirmado.equals("s")){
            compConfirm = " DATE_FORMAT(c.dataRealizacao, '%d/%m/%Y') AS dataSaida, ";
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

        String sql = "SELECT m.nome, m.ativo, " + compConfirm + " c.mes, c.ano, c.confirma, mo.codtipo, o.nome AS nomeOrixa, "
                + "tp.nome AS tipocoroa FROM mediuns m " +
                "LEFT JOIN coroa c ON c.codmedium = m.idmedium " +
                "LEFT JOIN medium_ori mo ON mo.codMedium = m.idmedium " +
                "LEFT JOIN orixas o ON mo.cod_orixa = o.idorixa " +
                "LEFT JOIN tipocoroa tp ON c.codtipocoroa = tp.idtipocoroa " +
                "WHERE m.ativo = 1 " +
                "AND mo.codTipo = 1 " +
                "AND c.confirma = '" + confirmado + "'" +
                compMedium +
                compPeriodo;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//reportcoroa.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            
            Map param = new HashMap(); // Abre o parâmetro

            
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
// Mensalidade    
    public void relatorioMensalidade(String tipo, int idmedium, int periodo, int mes1, int ano1, int mes2, int ano2){

        cal = new GregorianCalendar();
        String path = null;
        String sql = null;
        
        String compMedium = null;
        String codMedium = null;
        String compPeriodo = null;
        String msgParametro = null;
        
        if(idmedium == 0){
            compMedium = "";
            codMedium = "";
        }else{
            compMedium = " AND m.idmedium = " + idmedium + " ";
            codMedium = " AND me.cod_medium = " + idmedium + " ";
        }
        
        switch(periodo){
            case 1: //por ano

                compPeriodo = " AND me.ano = " + ano1;
                msgParametro = "Somente do ano";
                break;
            case 2: // pelo mes e ano
                compPeriodo = " AND me.mes = " + mes1 +
                        " AND me.ano = " + ano1;
                msgParametro = "Somente do mês " + mes1 + " e do ano " + ano1;
                break;
            case 3: // pelo período de mes e ano a mes ano
                compPeriodo = " AND me.mes BETWEEN " + mes1 + " AND " + mes2 +
                        " AND me.ano BETWEEN " + ano1 + " AND " + ano2;
                msgParametro = "Período de " + mes1 + "/" + ano1 + " a " + mes2 + "/" +ano2;
                break;
        }
        
        
        if(tipo.equals("sintetico")){
            path = "relatorios//reportmensalsintetico.jasper";
            
            sql = "SELECT " +
                "(SELECT COUNT(me.idmensalidade) " +
                    "FROM mensalidade me " +
                    "WHERE me.pago = 'n' " +
                    codMedium + 
                    compPeriodo + " ) AS quantidadeNPagos, " +
                "(SELECT COUNT(me.idmensalidade) " +
                    "FROM mensalidade me " +
                    "WHERE me.pago = 's' " +
                    codMedium +
                    compPeriodo + ") AS quantidadePagos, " +
                "(SELECT REPLACE(CAST(SUM(me.valor) AS DECIMAL(15, 2)), '.', ',') " +
                    "FROM mensalidade me " +
                    "WHERE me.pago = 'n' " +
                    codMedium +
                    compPeriodo + ") AS valoresPendentes, " +
                "(SELECT REPLACE(CAST(SUM(me.valor) AS DECIMAL(15, 2)), '.', ',') " +
                    "FROM mensalidade me " +
                    "WHERE me.pago = 's' " +
                    codMedium + 
                    compPeriodo + ") AS valoresPagos, " +
                "(SELECT COUNT(m.isentoMensal) FROM mediuns m WHERE m.isentoMensal = 1) AS quantIsentos, " +
                "me.ano " +
                "FROM mensalidade me";
            
        }else if(tipo.equals("analitico")){
            path = "relatorios//reportmensalidade.jasper";
            
            sql = "SELECT m.nome, m.matricula, me.mes, me.ano, me.pago, me.valor, "
                    + "DATE_FORMAT(me.data_pagamento, '%d/%m/%Y') AS dataPago, "
                    + "(SELECT COUNT(me.idmensalidade) "
                    + "FROM mensalidade me "
                    + "LEFT JOIN mediuns m ON m.idmedium = me.cod_medium "
                    + "WHERE me.pago = 'n' "
                    + compMedium
                    + compPeriodo + ") AS quantidadePago " +
                "FROM mensalidade me " +
                "LEFT JOIN mediuns m ON m.idmedium = me.cod_medium " +
                "WHERE me.pago = 's' " +
                compPeriodo + 
                compMedium;
        }
//        System.out.println(sql);

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro
            
            param.put("periodo", msgParametro);

            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
// Gera carteirinha do médium
    public void geraCarteira(int idMedium, String funcao){
        
        String sql = "SELECT m.nome, m.funcao, DATE_FORMAT(m.dataEntrada, '%d/%m%Y') AS dataEntrada, " +
                    "m.email, m.matricula, l.endereco, l.bairro, f.foto " +
                    "FROM mediuns m " +
                    "LEFT JOIN logradouro l ON l.cod_medium = m.idmedium " +
                    "LEFT JOIN foto f ON f.cod_medium = m.idmedium " +
                    "WHERE m.idmedium = " + idMedium;
//                    "AND m.ativo = 1";

//                System.out.println(sql);

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//rcarteira.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados

            
            Map param = new HashMap(); // Abre o parâmetro

            param.put("funcao", funcao);
            
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }    
// Gera relatório de usuários
    public void relUsuarios(){
        
        String sql = "SELECT u.nome, u.email, DATE_FORMAT(u.dataCadastro, '%d/%m/%Y') AS dtCad, a.acesso " +
                     "FROM tblusuario u " +
                     "LEFT JOIN acesso a ON u.codAcesso = a.idacesso";

//                System.out.println(sql);

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//rUsuarios.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados

            
            Map param = new HashMap(); // Abre o parâmetro

            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public void geraAnosCarteira(int ano1, int ano2){
        
        try{
            String path = "relatorios//ranos.jasper";
            
//            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro

            param.put("ano1", ano1);
            param.put("ano2", ano2);
            
            JasperPrint print = JasperFillManager.fillReport(path, param); // Somente os parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void relatorioFichaMedium(int codMedium, int foto){

        String compFoto = null;
        
        if(foto == 0){
            compFoto = "(f.foto = NULL) AS foto, ";
        }else{
            compFoto = "f.foto, ";
        }
        
        String sql = "SELECT "
                + "m.nome, "
                + "m.ativo, "
                + "m.matricula, "
                + "m.funcao, "
                + "DATE_FORMAT(m.dataEntrada, '%d/%m/%Y') AS dataEntFormat, "
                + "m.isentoMensal, "
                + "m.sexo, "
                + "DATE_FORMAT(m.dataNascimento, '%d/%m/%Y') AS dataNascFormat, "
                + "m.observacoes, "
                + "m.email, "
                + "l.endereco, "
                + "l.bairro, "
                + "l.cidade, "
                + "l.estado, "
                + "t.numTelefone, "
                + compFoto + " "
                + "o.nome AS orixa, "
                + "tp.tipo AS tipo, "
                + "ent.nome AS entidade, "
                + "cab.nome AS caboclo, "
                + "er.nome AS ere, "
                + "ex.nome AS exu"
                + "   FROM mediuns m "
                + "   LEFT JOIN logradouro l ON l.cod_medium = m.idmedium "
                + "   LEFT JOIN foto f ON f.cod_medium = m.idmedium "
                + "   LEFT JOIN telefones t ON t.codMedium = m.idmedium "
                +    "LEFT JOIN medium_ori mor ON mor.codMedium = m.idmedium "
                +    "LEFT JOIN orixas o ON mor.cod_orixa = o.idorixa "
                +    "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mor.codTipo "
                +    "LEFT JOIN medium_ent ment ON ment.codMedium = m.idmedium "
                +    "LEFT JOIN entidade ent ON ment.cod_entidade = ent.identidade "
                +    "LEFT JOIN medium_caboclo mcab ON mcab.codMedium = m.idmedium "
                +    "LEFT JOIN caboclo cab ON mcab.cod_caboclo = cab.idcaboclo "
                +    "LEFT JOIN medium_ere mere ON mere.codMedium = m.idmedium "
                +    "LEFT JOIN ere er ON mere.cod_ere = er.idere "
                +    "LEFT JOIN medium_exu mex ON mex.codMedium = m.idmedium "
                +    "LEFT JOIN exu ex ON mex.cod_exu = ex.idexu "
                +    "WHERE m.idmedium =  " + codMedium + " "
                +    "ORDER BY tp.idtipo_orixa "
                ;
        
//        System.out.println(sql);
        

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//reportFichaMediuns.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro
            
            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void relatorioSaidasMedium(int idmedium){
        
        String sql = "  SELECT " +
                    " m.nome," +
                    " m.matricula," +
                    " m.ativo," +
                    " c.mes," +
                    " c.ano," +
                    " c.confirma, (" +
                    " CASE WHEN c.confirma = 's'" +
                    " THEN" +
                    " DATE_FORMAT(c.dataRealizacao, '%d/%m/%Y')" +
                    " END" +
                    ") AS dtReal," +
                    " tp.nome AS tipo " +
                    "FROM mediuns m " +
                    "LEFT JOIN coroa c ON c.codmedium = m.idmedium " +
                    "LEFT JOIN tipocoroa tp ON c.codtipocoroa = tp.idtipocoroa " +
                    "WHERE m.idmedium = " + idmedium
                ;

//        System.out.println(sql);
        

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            //Gera o relatório                
            String path = "relatorios//rSaidasMedium.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro

            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    public void relatorioEntidadesMedium(int idmedium){
        
        String sqlOrixas = "SELECT DISTINCT " +
                "m.nome, " +
                "m.matricula, " +
                "o.nome AS orixa, " +
                "tp.tipo AS tipo, " +
                "ent.nome AS entidade, " +
                "cab.nome AS caboclo, " +
                "er.nome AS ere, " +
                "ex.nome AS exu " +
                "FROM mediuns m " +
                "LEFT JOIN medium_ori mor ON mor.codMedium = m.idmedium " +
                "LEFT JOIN orixas o ON mor.cod_orixa = o.idorixa " +
                "LEFT JOIN tipo_orixa tp ON tp.idtipo_orixa = mor.codTipo " +
                "LEFT JOIN medium_ent ment ON ment.codMedium = m.idmedium " +
                "LEFT JOIN entidade ent ON ment.cod_entidade = ent.identidade " +
                "LEFT JOIN medium_caboclo mcab ON mcab.codMedium = m.idmedium " +
                "LEFT JOIN caboclo cab ON mcab.cod_caboclo = cab.idcaboclo " +
                "LEFT JOIN medium_ere mere ON mere.codMedium = m.idmedium " +
                "LEFT JOIN ere er ON mere.cod_ere = er.idere " +
                "LEFT JOIN medium_exu mex ON mex.codMedium = m.idmedium " +
                "LEFT JOIN exu ex ON mex.cod_exu = ex.idexu " +
                "WHERE m.idmedium = 1 " + idmedium +
                "ORDER BY tp.idtipo_orixa "
                ;

//        System.out.println(sql);
        

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlOrixas);
            
            //Gera o relatório                
            String path = "relatorios//rOrixas.jasper";
            
            JRResultSetDataSource jr = new JRResultSetDataSource(rs); // Cria um resultset do banco de dados
            Map param = new HashMap(); // Abre o parâmetro

            JasperPrint print = JasperFillManager.fillReport(path, param, jr); // Junta as informações do banco e parâmetros
            JasperViewer view = new JasperViewer(print, false);//Prepara a visualização do relatório - true, fecha aplicação | false, mantém aplicação aberto
            view.setVisible(true); //Visualiza o relatório
            view.toFront();//Puxa o relatório para frente do frame.

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
}
