/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
        
//        System.out.println(sql);

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
    
}
