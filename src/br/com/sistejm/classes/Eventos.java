/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class Eventos {
    private int idEventos;
    private String evento;
    private int codUsuario;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public int getIdEvewntos() {
        return idEventos;
    }

    public void setIdEventos(int idEventos) {
        this.idEventos = idEventos;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }
    
    public boolean incluirEvento(){
        con = new Conexao();
        config = new Configuracoes();
        
        this.idEventos = con.ultimoId("eventos", "idevento");
        
        String sql = "INSERT INTO eventos (idevento, evento, codUsuario) "
                + "VALUES (" + this.idEventos + ", " + "'" + this.evento + "', " + this.codUsuario + ")";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

            config.gravaBDBackup(sql);
            return true;

        }catch(Exception ex){
            config.gravaErroLog(Constances.ERRORINC+ ex.getMessage(), "Inclusão de Evento", "sistejm.incevento");
            return false;
        }
        //return false;
    }
    
    public boolean alterarEvento(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "UPDATE eventos "
                + "SET evento = '" + this.evento + "', codUsuario = " + this.codUsuario + " "
                + "WHERE idevento = " + this.idEventos;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return true;

        }catch(Exception ex){
            config.gravaErroLog(Constances.ERRORALT+ ex.getMessage(), "Alteração de Evento", "sistejm.altevento");
            return false;
        }        
    }
    
    public boolean excluirEvento(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "DELETE FROM eventos "
                + "WHERE idevento = " + this.idEventos;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            config.gravaBDBackup(sql);
            return true;

        }catch(Exception ex){
            config.gravaErroLog(Constances.ERROREXC+ ex.getMessage(), "Exclusão de Evento", "sistejm.excevento");
//            System.out.println("Problemas na exclusão do evento. Erro: " + ex.getMessage());
            return false;
        }    
    }
    
    public void preencheTabelaEvento(JTable eventos){
        String sql = "SELECT * FROM eventos";
        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
//            if(rs.absolute(1)){
                
                DefaultTableModel tabela = new DefaultTableModel();
                eventos.setModel(tabela);
                
                tabela.addColumn("ID");
                tabela.addColumn("Eventos");
                
                eventos.getColumnModel().getColumn(0).setPreferredWidth(30);
                eventos.getColumnModel().getColumn(1).setPreferredWidth(100);
                
                while(rs.next()){
                    int idEvento = rs.getInt("idevento");
                    String nomeEvento = rs.getString("evento");
                    tabela.addRow(new Object[]{idEvento, nomeEvento});
                }
                
//            }
            
        }catch(Exception ex){
            config.gravaErroLog("Problemas no preenchimento de tabela. Erro: " + ex.getMessage(), "Eventos", "sistejm.evento");
        }
    }
    
    public void pesquisaCampoTabela(JTable eventos, JTextField campo){
        String sql = "SELECT * FROM eventos WHERE evento LIKE '%" + campo.getText() + "%'";
        config =  new Configuracoes();
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
                DefaultTableModel tabela = new DefaultTableModel();
                eventos.setModel(tabela);
                
                tabela.addColumn("ID");
                tabela.addColumn("Eventos");
                
                eventos.getColumnModel().getColumn(0).setPreferredWidth(30);
                eventos.getColumnModel().getColumn(1).setPreferredWidth(100);
                
                while(rs.next()){
                    int idEvento = rs.getInt("idevento");
                    String nomeEvento = rs.getString("evento");
                    tabela.addRow(new Object[]{idEvento, nomeEvento});
                }
                
//            }
            
        }catch(Exception ex){
            config.gravaErroLog("Problemas na pesquisa de campo dos eventos. Erro: " + ex.getMessage(), "Eventos", "sistejm.evento");
        }        
    }
    
    public boolean verificaDuplicidade(){
        
        config = new Configuracoes();
        
        String sql = "SELECT * FROM eventos WHERE evento = '" + this.evento + "'";
        
        con = new Conexao();
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                alterarEvento();
                return false;
            }
            incluirEvento();

        }catch(Exception ex){
            config.gravaErroLog("Problemas na verificação de duplicidade do eventos. Erro: " + ex.getMessage(), "Eventos", "sistejm.evento");
        }        
            return true;
    }    
}
