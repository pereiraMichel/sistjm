/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class TipoOrixa {
    
    private int idTipo;
    private String tipo;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void preencheComboTipoOrixa(JComboBox combo){

        config = new Configuracoes();
        String nome = null;
        try{
            String sql = "SELECT tipo FROM tipo_orixa ";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
//            rs.first();

            while(rs.next()){
                nome = rs.getString("tipo");
                combo.addItem(nome);
            }

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de preenchimento combo do tipo de Orixá. Erro: " + ex.getMessage(), "Tipo de Orixá", "sistejm.tipoorixa");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.tipoorixa para mais informações");
//            System.out.println(" Erro: " + ex.getMessage());
        }
    }
    
    public int retornaIdTipoOrixa(String nome){
        config = new Configuracoes();
        try{
            String sql = "SELECT * FROM tipo_orixa WHERE tipo = '" + nome + "'";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idtipo_orixa");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do tipo de Orixá. Erro: " + ex.getMessage(), "Tipo de Orixá", "sistejm.tipoorixa");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.tipoorixa para mais informações");
//            System.out.println(" Erro: " + ex.getMessage());
        }        
        return 0;
    }
    
    public void preencheTabTipoOrixa(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT * FROM tipo_orixa";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

//            medium.addColumn("ID");
            medium.addColumn("tipo");
 
//            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(0).setPreferredWidth(100);

            while(rs.next()){
//                int id = rs.getInt("idtipo_orixa");
                String tipo = rs.getString("tipo");
                medium.addRow(new Object[]{ tipo });
//                medium.addRow(new Object[]{id, tipo });
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela do tipo de Orixá. Erro: " + ex.getMessage(), "Tipo de Orixá", "sistejm.tipoorixa");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.tipoorixa para mais informações");
//            System.out.println("Erro em tabela de tipo de Orixás. Mensagem: " + ex.getMessage());
        }            
    }
            
}
