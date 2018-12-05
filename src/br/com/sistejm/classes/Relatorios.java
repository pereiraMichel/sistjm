/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    
}
