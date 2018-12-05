/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import br.com.sistejm.telas.BancoDados;
import br.com.sistejm.telas.TelaInicial;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;
//import org.jcp.xml.dsig.internal.dom.Utils;

/**
 *
 * @author Michel
 */
public class Conexao {
    
    private String servidor;
    private String usuario;
    private String senha;
    private String database;
//    private String driver;
    private String caminho;
    
    Configuracoes config;
  

    public static Properties getProp() throws IOException {//br\com\satoru\bundles
        String caminho = "C:\\sistejm\\config\\banco.properties";
        
        Properties props = new Properties();
        FileInputStream file = new FileInputStream(caminho);
//        FileInputStream file = new FileInputStream("banco.properties");
        
            props.load(file);
            //JOptionPane.showMessageDialog(null, props);
            return props;
    }    
    
    
    public Connection getConnection() throws IOException{
        String caminho = "C:\\sistejm\\config\\banco.properties";
        TelaInicial inicial = new TelaInicial();
        config = new Configuracoes();
//        iniciaServer();
        File arquivoExistente = new File(caminho);
        
        if(arquivoExistente.exists()){
            Properties prop = new Properties();
        
            FileInputStream file = new FileInputStream(arquivoExistente);
            prop.load(file);
            file.close();
            
        this.servidor = prop.getProperty("prop.database.servidor");
        this.usuario = prop.getProperty("prop.database.usuario");
        this.senha = prop.getProperty("prop.database.senha");
        this.database = prop.getProperty("prop.database.banco");
        this.caminho = "jdbc:mysql://";
//        this.driver = "com.mysql.jdbc.Driver";

//    try{
//        String path = Utils.getPath() + "C:\\wamp\\wampmanager.exe";
//        Process iniciaServer = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL cmd /c \""+path+"\"");
//        Process iniciaServer = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL cmd /c soffice.exe -headless -norestore -accept=socket,host=localhost,port=8100;urp");
//       
//    }catch(Exception ex){
//        System.out.println("Problemas. Erro: " + ex.getMessage());
//    }
        
        }else{//arquivo não existe
            JOptionPane.showMessageDialog(null, "Houve um erro. Consulte o arquivo sistekm.conexao.arq.log, na pasta c:/sistejm > erro.");
            config.gravaErroLog("Arquivo inexistente.", "Conexão Banco de dados", "sistejm.conexao.arq");
            inicial.chamaBD();
        }
        
        try {
            Connection con = DriverManager.getConnection(this.caminho + this.servidor + "/" + this.database, this.usuario, this.senha);

            if (con == null) {
                config.gravaErroLog("Tentativa de conexão ao banco.", "Conexão Banco de dados", "sistejm.conexaobd");
                JOptionPane.showMessageDialog(null, "Houve um erro. Consulte o arquivo sistekm.conexaobd.log, na pasta c:/sistejm > erro.");
                return null;
            }else{
                return con;
            }

        }catch (SQLException e) {
            if (!iniciaServer()){
                return null;
            }
            
           
//            inicial.preencheCampo("\nProblemas de comunicação ao Banco de dados...");
//            inicial.preencheCampo("\nVerifique as informações no Banco de dados...");
//            inicial.chamaBD();
//            banco.preencheCampos(this.servidor, this.database, this.usuario, this.senha);
//            banco.setLocationRelativeTo(banco);
//            banco.setTitle("Configurações de Banco de Dados");
//            banco.setVisible(true);
            return null;
        }
    }
    
    public boolean iniciaServer(){
        config = new Configuracoes();
        try{
            String path = "C:\\wamp\\wampmanager.exe";
            
            File f = new File(path);
            
            if(f.exists()){
                Process iniciaServer = Runtime.getRuntime().exec(path);
                iniciaServer.waitFor();
                
//                Process iniciaServer = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL cmd /c \""+path+"\"");
//                Process fechaCmd = Runtime.getRuntime().exec("taskkill /im cmd.exe -f");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Aplicativo WAMP SERVER não localizado. Por favor, instale o aplicativo.");
            }
        }catch(IOException | InterruptedException ex){
            config.gravaErroLog("Tentativa de conexão ao banco.", "Conexão Banco de dados", "sistejm.conexaobd");
        }
        return false;
    }
    
    public void fechaConexao() throws IOException, SQLException{
        this.getConnection().close();
    }
    
    public int ultimoId(String tabela, String id){
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try{
            String sql = "SELECT MAX(" + id + ") as " + id + " FROM " + tabela;
            
//            JOptionPane.showMessageDialog(null, sql);
            
            
            conn = this.getConnection();
            stmt = conn.createStatement();
            
            rs = stmt.executeQuery(sql);
            rs.next();
            
            int resultado = rs.getInt(id);
            int soma = resultado + 1;
            
            return soma;
            
        }catch(IOException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Houve um erro na consulta. Erro: " + ex.getMessage(), "ERRO", 0);
        }
        return 0;
    }

    
}
