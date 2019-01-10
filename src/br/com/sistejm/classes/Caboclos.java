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
public class Caboclos {
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    
    private int idCaboclo;
    private String nome;

    public int getIdCaboclo() {
        return idCaboclo;
    }

    public void setIdCaboclo(int idCaboclo) {
        this.idCaboclo = idCaboclo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void preencheTabCaboclo(JTable tabela){

        String sql = "SELECT * FROM caboclo";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel caboclo = new DefaultTableModel();
            tabela.setModel(caboclo);
            
            caboclo.addColumn("Caboclo");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
//                int idEvento = rs.getInt("idcaboclo");
                String nomeEvento = rs.getString("nome");
                caboclo.addRow(new Object[]{nomeEvento});
//                dtm.addRow(new Object[]{idEvento, nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de Caboclo. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabCaboclo(JTable tabela, JTextField texto){
        
        String sql = "SELECT * FROM caboclo WHERE nome LIKE '%" + texto.getText() + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel orixa = new DefaultTableModel();
            tabela.setModel(orixa);

            orixa.addColumn("Caboclo");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nome");
                orixa.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de caboclo. Mensagem: " + ex.getMessage());
        }        
    }
    public void buscaTabCabocloPorNome(JTable tabela, String texto){

        String sql = "SELECT m.nome AS nomeMedium, c.nome AS nomeCaboclo FROM mediuns m "
                + "LEFT JOIN medium_caboclo mc ON m.idmedium = mc.codMedium "
                + "LEFT JOIN caboclo c ON c.idcaboclo = mc.cod_caboclo "
                + "WHERE m.nome = '" + texto + "' "
                + "ORDER BY c.idcaboclo";        
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel caboclo = new DefaultTableModel();
            tabela.setModel(caboclo);

            caboclo.addColumn("Caboclo");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(110);

            while(rs.next()){
                String nomeEvento = rs.getString("nomeCaboclo");
                caboclo.addRow(new Object[]{nomeEvento});
            }
        }catch(Exception ex){
            System.out.println("Erro em tabela de caboclo. Mensagem: " + ex.getMessage());
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        
        String sql = "SELECT nome FROM caboclo WHERE nome = '" + this.nome + "'";
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            //System.out.println("SQL: " + sql);
            
            //rs.next();
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
           
        }catch(Exception ex){
            System.out.println("Catch verificação de duplicidade de Caboclo ativado. Erro: " + ex.getMessage());
        }
        return false;
    }
    
    public boolean incluirCaboclo(){
        con = new Conexao();
        config = new Configuracoes();
        if(!this.verificaExistente()){
            
            this.idCaboclo = con.ultimoId("caboclo", "idcaboclo");

            String sql = "INSERT INTO caboclo (idcaboclo, nome) "
                    + "VALUES (" + this.idCaboclo + ", '" + this.nome + "')";

            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);

                return true;

            }catch(Exception ex){
                config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Inclusão do caboclo", "sistejm.incluicaboclo");
            }
        }
        return false;
    }
    
    public boolean alterarCaboclo(){
        String sql = "UPDATE caboclo SET nome = '" + this.nome + "' WHERE idcaboclo = " + this.idCaboclo;
        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Alteração do caboclo", "sistejm.alteracaboclo");
        }
        return false;
    }
    public boolean excluirCaboclo(){
        String sql = "DELETE FROM caboclo WHERE idcaboclo = " + this.idCaboclo;
        config = new Configuracoes();

        System.out.println(sql);
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Exclusão do caboclo", "sistejm.excluicaboclo");
        }
        return false;
    }
    
    public int retornaIdCaboclo(String texto){
        config = new Configuracoes();
        String sql = "SELECT * FROM caboclo WHERE nome = '" + texto + "'";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idcaboclo");
            }
            
        }catch(Exception ex){
            config.gravaErroLog("Erro: " + ex.getMessage() + ".", "Retorna ID caboclo", "sistejm.idcaboclo");
        }
        return 0;
    }
}
