/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.awt.Color;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michel
 */
public class MediumExu {
    private int idMediumExu;
    private int codExu;
    private int codMedium;
    
    Conexao con;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    Configuracoes config;
    Exu exu;
    
    public int getIdMediumExu() {
        return idMediumExu;
    }

    public void setIdMediumExu(int idMediumExu) {
        this.idMediumExu = idMediumExu;
    }

    public int getCodExu() {
        return codExu;
    }

    public void setCodExu(int codExu) {
        this.codExu = codExu;
    }

    public int getCodMedium() {
        return codMedium;
    }

    public void setCodMedium(int codMedium) {
        this.codMedium = codMedium;
    }
    
    public void tabMediumExu(JTable tabela){
        config = new Configuracoes();

        String sql = "SELECT me.idmedium_exu, m.nome, e.nome AS nomeExu "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_exu me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN exu e ON e.idexu = me.cod_exu ";
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Médium");
            medium.addColumn("Exu");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);

            while(rs.next()){
                int id = rs.getInt("idmedium_exu");
                String nome = rs.getString("nome");
                String matricula = rs.getString("nomeExu");
                medium.addRow(new Object[]{id, matricula, nome});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exibir a tabela de Exu. Erro: " + ex.getMessage(), "Tabela de Exu", "sistejm.mexu");
        }        
    }
    public void buscaTabMediumMatricula(JTable tabela, JTextField texto){

        String sql = "SELECT me.idmedium_exu, m.nome, m.matricula, e.nome AS nomeExu "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_exu me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN exu e ON e.idexu = me.cod_exu "
                    + "WHERE matricula LIKE '%" + texto.getText() + "%'";

        config = new Configuracoes();
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Médium");
            medium.addColumn("Exu");

            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);

            while(rs.next()){
                int id = rs.getInt("me.idmedium_exu");
                String nome = rs.getString("m.nome");
                String exu = rs.getString("nomeExu");
                medium.addRow(new Object[]{id, nome, exu});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exibir a tabela de caboclo. Erro: " + ex.getMessage(), "Tabela de Caboclo", "sistejm.mcaboclo");
        }        
    }
    
    public boolean verificaExistente(){
        con = new Conexao();
        config = new Configuracoes();
        
        String sql = "SELECT codMedium FROM medium_exu "
                + "WHERE codMedium = " + this.codMedium + " "
                + "AND cod_exu = " + this.codExu;
        
        try{
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(rs.absolute(1)){
                return true;
            }else{
                return false;
            }
           
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de verificação de duplicidade da Exu do Médium. Erro: " + ex.getMessage(), "Duplicidade de Exu", "sistejm.mexu");
        }
//        JOptionPane.showMessageDialog(null, "Caboclo existente.");
        return false;
    }
    
    public boolean incluirMediumExu(){
        con = new Conexao();
        config = new Configuracoes();
        
        if(!this.verificaExistente()){
            
            this.idMediumExu = con.ultimoId("medium_exu", "idmedium_exu");

            String sql = "INSERT INTO medium_exu (idmedium_exu, cod_exu, codMedium) "
                    + "VALUES (" + this.idMediumExu + ", " + this.codExu + ", " + this.codMedium + ")";
            
            try{
                conn = con.getConnection();
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                return true;

            }catch(Exception ex){
                config.gravaErroLog("Tentativa de inclusão de Exu do Médium. Erro: " + ex.getMessage(), "Inclusão de Exu", "sistejm.mexu");
//                System.out.println("Catch inclusão de Entidade do Médium ativado. Erro: " + ex.getMessage());
            }
        }
        return false;
    }
    
    public boolean alterarMediumExu(){
        config = new Configuracoes();
        
        String sql = "UPDATE medium_exu SET cod_exu = " + this.codExu + ""
                + " WHERE idmedium_exu = " + this.idMediumExu + " AND codMedium = " + this.codMedium;
        
        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            return true;
            
        }catch(Exception ex){
                config.gravaErroLog("Tentativa de alteração do Exu do Médium. Erro: " + ex.getMessage(), "Alteração do Exu", "sistejm.mexu");
        }
        return false;
    }
    public boolean excluirMediumExu(){
        config = new Configuracoes();
        
        String sql = "DELETE FROM medium_exu WHERE idmedium_exu = " + this.idMediumExu;

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //Colocar direcionamento de outras tabelas
            return true;
            
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de exclusão do Exu do Médium. Erro: " + ex.getMessage(), "Exclusão do Exu", "sistejm.mexu");
        }
        return false;
    }
    
    public void preencheComboMediumExu(JComboBox combo){

        String nome = null;
        try{
            String sql = "SELECT e.nome FROM mediuns m "
                    + " INNER JOIN medium_exu me ON me.codMedium = m.idmedium "
                    + " INNER JOIN exu e ON e.idcaboclo = me.cod_exu";
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                nome = rs.getString("e.nome");
                combo.addItem(nome);
            }

        }catch(IOException | SQLException ex){
            System.out.println(" Erro: " + ex.getMessage());
        }
    }
    
    public void preencheTabMediumCaboclo(JTable tabela){
        config = new Configuracoes();
        String sql = "SELECT DISTINCT m.nome FROM mediuns m "
                + "LEFT JOIN medium_exu me ON m.idmedium = mec.codMedium  "
                + "LEFT JOIN exu e ON c.idcaboclo = me.cod_exu";
        
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
            config.gravaErroLog("Tentativa de preenchimento da tabela geral do Médium | Exu. Erro: " + ex.getMessage(), "Exu", "sistejm.mexu");
        }            
    }
    public void preencheTabMediumExuPorNome(JTable tabela, String nome){
        config = new Configuracoes();
        String sql = "SELECT m.nome AS nomeMedium, e.nome AS nomeExu, me.idmedium_exu "
                + "FROM mediuns m "
                + "LEFT JOIN medium_exu me ON m.idmedium = me.codMedium  "
                + "LEFT JOIN exu e ON e.idexu = me.cod_exu "
                + "WHERE m.nome = '" + nome + "' "
                + "ORDER BY e.idexu";

        try{
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
         
                
            DefaultTableModel medium = new DefaultTableModel();
            tabela.setModel(medium);

            medium.addColumn("ID");
            medium.addColumn("Exu");
 
            tabela.getColumnModel().getColumn(0).setPreferredWidth(5);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(80);



            while(rs.next()){
                int id = rs.getInt("me.idmedium_exu");
                String exu = rs.getString("nomeExu");
               
                medium.addRow(new Object[]{id, exu});
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da busca do Médium | Exu. Erro: " + ex.getMessage(), "Exu", "sistejm.mexu");
        }  
        
    }

    public int retornaIdMediumExu(){
        config = new Configuracoes();
        try{
            String sql = "SELECT * FROM medium_exu "
                    + "WHERE codMedium = " + this.codMedium + " "
                    + "AND cod_exu = " + this.codExu;
            
            con = new Conexao();
            conn = con.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            
            if(rs.absolute(1)){
                return rs.getInt("idmedium_exu");
            }
            

        }catch(IOException | SQLException ex){
            config.gravaErroLog("Tentativa de retorno do id do Médium Exu. Erro: " + ex.getMessage(), "Médium Exu", "sistejm.mexu");
            JOptionPane.showMessageDialog(null, "Houve um erro. consulte o arquivo C:/sistejm > erro > sistejm.mexu para mais informações");
//            System.out.println(" Erro: " + ex.getMessage());
        }        
        return 0;
    }
    
    public void exibeTabMediumExuPorId(JTable tabela){
        config = new Configuracoes();
         String sql = "SELECT e.nome AS nomeExu "
                    + "FROM mediuns m "
                    + "INNER JOIN medium_exu me ON me.codMedium = m.idmedium "
                    + "LEFT JOIN exu e ON e.idexu = me.cod_exu "
                    + "WHERE m.idmedium = " + this.codMedium;
        
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
                String nome = rs.getString("nomeExu");
                if(nome.equals("")){
                    medium.addRow(new Object[]{"Sem informações"});
                }else{
                    medium.addRow(new Object[]{nome});
                }
            }
        }catch(Exception ex){
            config.gravaErroLog("Tentativa de preenchimento da tabela de Exu do Médium. Erro: " + ex.getMessage(), "Tabela de exu", "sistejm.exumedium");
        }            
    }
    
}
