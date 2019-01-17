/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistejm.classes;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.sistejm.telas.TelaInicial;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Michel
 */
public class Configuracoes {
    private String nome;
    private String opcao;
    private String mensagem;
    private String s; //SubMáscara Rede
    private String ip; //IP Rede
    private byte[] mac;
    private Boolean pastaExiste;
    private String ipLocal;
    private String macFisico;    
    
    Properties prop;
    TelaInicial inicial;    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public byte[] getMac() {
        return mac;
    }

    public void setMac(byte[] mac) {
        this.mac = mac;
    }

    public Boolean getPastaExiste() {
        return pastaExiste;
    }

    public void setPastaExiste(Boolean pastaExiste) {
        this.pastaExiste = pastaExiste;
    }

    public String getIpLocal() {
        return ipLocal;
    }

    public void setIpLocal(String ipLocal) {
        this.ipLocal = ipLocal;
    }

    public String getMacFisico() {
        return macFisico;
    }

    public void setMacFisico(String macFisico) {
        this.macFisico = macFisico;
    }

    public Properties getProp() {
        return prop;
    }

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    
    public void ultimoId(String idTabela, String tabela){
        
    }
    
    public void geraPdf (){
        
    }


    
    public String mac(){

           String macAddress = "";
        try {         
           InetAddress address = InetAddress.getLocalHost();  
           NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
           mac = ni.getHardwareAddress();
           for (int i = 0; i < mac.length; i++) {             
               macAddress += (String.format("%02X-", mac[i]));  
           }
//           return macAddress.substring(0, macAddress.length()-1);
        } catch (UnknownHostException e) {  
           e.printStackTrace();
        } catch (SocketException e) {  
           e.printStackTrace();  
        }
           return macAddress.substring(0, macAddress.length()-1);
    }
    
    
    public void inforRede(){
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
// Objeto para as interfaces de rede
                NetworkInterface iface = interfaces.nextElement();
// Não mostra endereços de loopback
                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
// Pega IP
                    this.ip = addr.getHostAddress();
// Pega MAC
                    this.mac = iface.getHardwareAddress();
                    
// Mostra MAC formatado
                    for (int i = 0; i < mac.length; i++) {
                        this.s += String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                    }
// Mostra tudo
//                    System.out.println(iface.getDisplayName() + "\n"
//                            + this.s + " - "
//                            + this.ip);
                }
            }
                    InetAddress localHost = Inet4Address.getLocalHost();
                    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
//                    mensagens.append("\n\nPlaca de Rede: " + networkInterface.getDisplayName());
                    java.util.List<InterfaceAddress> lista = networkInterface.getInterfaceAddresses();
                    this.ipLocal=lista.get(0).getAddress().getHostAddress();
        
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

    public void inforMac(){

        try {         
           InetAddress address = InetAddress.getLocalHost();  
           NetworkInterface ni = NetworkInterface.getByInetAddress(address);  
           mac = ni.getHardwareAddress();
           String macAddress = "";
           for (int i = 0; i < mac.length; i++) {             
               macAddress += (String.format("%02X-", mac[i]));  
           }
           this.macFisico = macAddress.substring(0, macAddress.length()-1);
//           System.out.println(macAddress.substring(0, macAddress.length()-1));
        } catch (UnknownHostException e) {  
           e.printStackTrace();
        } catch (SocketException e) {  
           e.printStackTrace();  
        }        
    }
    
    public void gravaAcessoUsuario(int acesso, int autoriza){
        String path = "C:\\sistejm\\config";
        //String arquivo = "datauser.properties";
        
        File caminho = new File(path);
        
        if (!caminho.exists()){
            caminho.mkdir();
        }
        
        prop = new Properties();
        
        try {

            prop.setProperty("prop.user.acesso", String.valueOf(acesso));
            prop.setProperty("prop.user.autorizacao", String.valueOf(autoriza));

            FileOutputStream d = new FileOutputStream("C:\\sistejm\\config\\datauser.properties");
            prop.store(d, "prop.user");

            //this.pastaExiste = true;
            
/*            tela = new Principal();
            tela.bancoAtivado();
  */                  
        } catch (FileNotFoundException ex) {
            System.out.println("Erro File Not Found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro IOException: " + ex.getMessage());
        }
    }
    
    public void gravaValorMensalidade(String valor){
        String path = "C:\\sistejm\\config";
        File caminho = new File(path);
        if (!caminho.exists()){
            caminho.mkdir();
        }
        prop = new Properties();
        try {
            prop.setProperty("prop.config.vmensal", valor);
            FileOutputStream d = new FileOutputStream("C:\\sistejm\\config\\vmensal.properties");
            prop.store(d, "prop.config");
        } catch (FileNotFoundException ex) {
            System.out.println("Erro File Not Found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro IOException: " + ex.getMessage());
        }
    }
    
    public void gravaProperties(){
        inforRede(); // Busca informações da rede
        inforMac();
        String nomeArquivo = "dados.properties";
        String path = "C:\\sistejm\\config\\";
        prop = new Properties();
        
        try {
            String pastaSistema = "C:\\sistejm";

            prop.setProperty("prop.server.usuarioLogado", System.getProperty("user.name"));
            prop.setProperty("prop.server.nomeUsuario", this.nome);
            prop.setProperty("prop.server.ipv4", this.ipLocal);
            prop.setProperty("prop.server.ipv6", this.ip);
            prop.setProperty("prop.server.path", path);
            prop.setProperty("prop.server.mac", this.macFisico);
            prop.setProperty("prop.server.so", System.getProperty("os.name"));
            prop.setProperty("prop.server.pathSistema", pastaSistema);

            prop.store(new FileOutputStream(path + nomeArquivo), "prop.server");
                 
        } catch (FileNotFoundException ex) {
            System.out.println("Erro File Not Found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro IOException: " + ex.getMessage());
        }
        
    }
    public void gravaDatabase(String tabela, String usuario, String senha, String servidor){
//        String nomeArquivo = "banco.properties";
//        String path = "C:\\sistem\\config\\";
        Properties prop = new Properties();
        
        try {
            String pastaSistema = "C:\\sistejm";

            prop.setProperty("prop.database.banco", tabela);
            prop.setProperty("prop.database.usuario", usuario);
            prop.setProperty("prop.database.senha", senha);
            prop.setProperty("prop.database.servidor", servidor);
            prop.setProperty("prop.database.pathSistema", pastaSistema);

            FileOutputStream dados = new FileOutputStream("C:\\sistejm\\config\\banco.properties");
            prop.store(dados, "prop.database");

                
        } catch (FileNotFoundException ex) {
            System.out.println("Erro File Not Found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro IOException: " + ex.getMessage());
        }
        
    }
    
    public void extendNew(String local){

        File arquivo = new File(local);
        arquivo.renameTo(new File(local.replace(".properties",".dat")));
        
    }    
    public void extendOriginal(String local){
        
        File arquivo = new File(local);
        arquivo.renameTo(new File(local.replace(".dat",".properties")));
        
    }
    
    public String retornaDataArquivo(){
        Calendar cal = new GregorianCalendar();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
//        int semana = cal.get(Calendar.DAY_OF_WEEK);
        return String.valueOf(dia + "." + mes + "."+ ano);
    }
    public String retornaData(){
        Calendar cal = new GregorianCalendar();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1;
        int ano = cal.get(Calendar.YEAR);
        
        return String.valueOf(dia + "/" + mes + "/"+ ano);
    }
    public String retornaDataMesAnterior(){
        Calendar cal = new GregorianCalendar();
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH);
        int ano = cal.get(Calendar.YEAR);
        
        String diaLongo = null;
        String mesLongo = null;

       if(dia < 10){
            diaLongo = String.valueOf("0" + dia);
        }
        if(mes < 10){
            mesLongo = String.valueOf("0" + mes);
        }

        
        return String.valueOf(diaLongo + "/" + mesLongo + "/"+ ano);
    }
    
    public String retornaHora(){
        Calendar cal = new GregorianCalendar();
        int h = cal.get(Calendar.HOUR_OF_DAY);
        int m = cal.get(Calendar.MINUTE);
        int s = cal.get(Calendar.SECOND);

        String horaCompleta = String.valueOf(h + ":" + m + ":" + s);
        
        return horaCompleta;
    }
    
    public String retornaFormatoDataSQL(String data){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat f = DateFormat.getDateInstance(); 
        java.util.Date dataConvert;
        
        try {
            dataConvert = f.parse(data);
            String dataFormatada = formato.format(dataConvert);
            return dataFormatada;
            
        } catch (ParseException ex) {
            System.out.println("Erro no na data SQL em 'Configurações'. Verifique sob o erro: " + ex.getMessage());
            return "";
        }   

//        return novoFormato;
    }
    
    public String retornaValorDecimal(String valor){
        String f = new DecimalFormat("#0.##").format(valor);
        
        return f;
    }
    
    public String retornaFormatoDataNormal(String data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = null;

        dataFormatada = sdf.format(data);
        JOptionPane.showMessageDialog(null, "Data formatada: " + dataFormatada);

        return dataFormatada;

    } 
    public void gravaTituloAtividade(String usuario){
        
        //Date data = new Date();
        String hora = retornaHora();
        String dataFormatada = retornaDataArquivo();
        String dataTradicional = retornaData();
        

        try{
            File pastaPrincipal = new File("C:\\sistejm");
            File pastaGrava = new File("C:\\sistejm\\dados");
            File path = new File("C:\\sistejm\\dados\\" + usuario + "." + dataFormatada + ".txt");

            if(!pastaPrincipal.exists()){
                pastaPrincipal.mkdir();
            }
            if(!pastaGrava.exists()){
                pastaGrava.mkdir();
            }
            if(!path.exists()){
                FileWriter arq = new FileWriter("C:\\sistejm\\dados\\" + usuario + "." + dataFormatada + ".txt");
                PrintWriter gravarArq = new PrintWriter(arq);
                gravarArq.append("=========== HISTÓRICO DE ATIVIDADES ===========\r\n");
                gravarArq.append("\r\n");
                gravarArq.append("\nEntrada no Sistema: " + dataTradicional + " | " + hora);

                arq.close();            
            }

        }catch(IOException ex){
            System.out.println("Erro. Messagem: " + ex.getMessage());
        }

    }
    
    public void gravaAtividades(String dados, String usuario, String atividade){

        String data = retornaDataArquivo();
        String hora = retornaHora();
        String dataTradicional = retornaData();

        File arqPrincipal = new File("C:\\sistejm\\dados\\" + usuario + "." + data + ".txt");
        String path = "C:\\sistejm\\dados\\" + usuario + "." + data + ".txt";
        
        if(arqPrincipal.exists()){
        try{
                FileWriter arq = new FileWriter(new File(path), true);
 //               BufferedWriter b = new BufferedWriter(arq);
                PrintWriter gravarArq = new PrintWriter(arq);
                gravarArq.append("\r\n");
                gravarArq.append("Data | Hora " + dataTradicional + " | " + hora);
                gravarArq.append("\r\n");
                gravarArq.append(dados);
                gravarArq.append("\r\n");
                gravarArq.append(atividade);
                gravarArq.append("\r\n");
                gravarArq.append("=========================================\r\n");
                gravarArq.append("\r\n");
                arq.close();
                //extendNew(path);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "\nArquivo não foi gravado. Erro: " + ex.getMessage());
            }
        }else{
            gravaTituloAtividade(usuario);
        }
    }
    
    public void verificaPastas(){
        String dirPrincipal = "C:\\sistejm";
        String dirConfig = "C:\\sistejm\\config";
        String dirDados = "C:\\sistejm\\dados";
        String dirErro = "C:\\sistejm\\erro";
        String dirFotos = "C:\\sistejm\\fotos";
        String dirImg = "C:\\sistejm\\images";
        
        File fp = new File(dirPrincipal);
        File fc = new File(dirConfig);
        File fd = new File(dirDados);
        File fe = new File(dirErro);
        File ff = new File(dirFotos);
        File fi = new File(dirImg);
        
        if(!fp.exists()){
            fp.mkdir();
        }
        if(!fc.exists()){
            fc.mkdir();
        }
        if(!fd.exists()){
            fd.mkdir();
        }
        if(!fe.exists()){
            fe.mkdir();
        }
        if(!ff.exists()){
            ff.mkdir();
        }
        if(!fi.exists()){
            fi.mkdir();
        }
        
        
    }

    public void insereIconePrincipal(JFrame tela){
        
        URL url = this.getClass().getResource("/br/com/sistejm/images/icontejm.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        tela.setIconImage(imagemTitulo);        
    }
    
    public void insereIconeDialog(JDialog tela){
        
        URL url = this.getClass().getResource("/br/com/sistejm/images/icontejm.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        tela.setIconImage(imagemTitulo);        
    }    
    
    public void retornaSemana(int dia, int mes, int ano, JLabel label){
        
        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date data = sdf.parse(dia + "/" + mes + "/" + ano);
            Calendar cal = new GregorianCalendar();

            cal.setTime(data);

            int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
//            System.out.println(diaSemana);
//            System.out.println("Dia: " + dia);
//            System.out.println("Mês: " + mes);
//            System.out.println("Ano: " + ano);
            switch(diaSemana){
                case 1:
                    label.setText("Domingo");
                    break;
                case 2:
                    label.setText("Segunda-feira");
                    break;
                case 3:
                    label.setText("Terça-feira");
                    break;
                case 4:
                    label.setText("Quarta-feira");
                    break;
                case 5:
                    label.setText("Quinta-feira");
                    break;
                case 6:
                    label.setText("Sexta-feira");
                    break;
                case 7:
                    label.setText("Sábado");
                    break;
            }        
        }catch(Exception ex){
            System.out.println("Catch ativado. Erro: " + ex.getMessage());
        }

    }

    public void gravaErroLog(String msgErro, String menu, String nomeArq){
//        Date data = new Date();
//        Timer hora = new Timer();
        
        Calendar c = new GregorianCalendar();
        
        String diaLongo = null;
        String mesLongo = null;
        String horaLonga = null;
        String minLongo = null;
        String secLongo = null;
        
        String dataCompleta = null;
        String horaCompleta = null;
        
        int dia = c.get(Calendar.DAY_OF_MONTH);
        int mes = c.get(Calendar.MONTH) + 1;
        int ano = c.get(Calendar.YEAR);
        
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);

        if(dia < 10){
            diaLongo = "0" + dia;
        }
        
        if(mes < 10){
            mesLongo = "0" + mes;
        }
        
        if(hora < 10){
            horaLonga = "0" + hora;
        }
        
        if(min < 10){
            minLongo = "0" + min;
        }
        
        if(sec < 10){
            secLongo = "0" + sec;
        }
        
        dataCompleta = diaLongo + "/" + mesLongo + "/" + ano;
        horaCompleta = horaLonga + ":" + minLongo + ":" + secLongo;
        
        String pathPrinc = "C:\\sistejm";
        String pathErro = "C:\\sistejm\\erro";

        File pp = new File(pathPrinc);
        File pe = new File(pathErro);
        
        if(!pp.exists()){
            pp.mkdir();
        }
        if(!pe.exists()){
            pe.mkdir();
        }
        
        try{
            
            FileOutputStream out = new FileOutputStream(pathErro + "\\" + nomeArq + ".log");  
//            FileOutputStream out = new FileOutputStream("c:\\Erro.txt");  
            PrintStream p = new PrintStream(out);
            p.append("=========== HISTÓRICO DE ERRO ===========");
            p.append(System.getProperty("line.separator"));
            p.append("Sistema: SISTEJM"); 
//            p.append("Sistema: " + menu); 
            p.append(System.getProperty("line.separator"));
            p.append("Menu do erro: " + menu); 
            p.append(System.getProperty("line.separator"));
            p.append("Data: " + dataCompleta + "  |  Hora: " + horaCompleta); 
            p.append(System.getProperty("line.separator"));
            p.append(msgErro); 
            p.append(System.getProperty("line.separator"));
            p.append("=========== FIM DO HISTÓRICO ===========");  
            p.close();            
            
            
//            JOptionPane.showMessageDialog(null, "\nArquivo gravado com sucesso em \"" + nomeArq + ".log\".\n"); 
            JOptionPane.showMessageDialog(null, "\nHouve um erro. Consulte o arquivo \"" + nomeArq + ".log\" para mais detalhes.\n"); 
//            JOptionPane.showMessageDialog(null, "\nArquivo gravado com sucesso em \"" + nomeArq + ".log\".\n"); 
//            JOptionPane.showMessageDialog(null, "\nArquivo gravada com sucesso em \"c:\\erro.txt\".\n"); 
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "\nArquivo não foi gravado. Erro: " + ex.getMessage()); 
        }
//            FileWriter arq = new FileWriter("c:\\erro.txt"); 
//            PrintWriter gravarArq = new PrintWriter(arq);
//            gravarArq.append("=========== HISTÓRICO DE ERRO ===========");
//            gravarArq.append('\n');
//            gravarArq.append(dados);
//            gravarArq.append('\n');
//            gravarArq.append("=========================================");
//            gravarArq.append('\n');
//            arq.close(); 
    }
    
    public void limpaTabela(JTable tabela){
            DefaultTableModel e = new DefaultTableModel();
            tabela.setModel(e);
            
            e.addColumn("Coluna");
            tabela.getColumnModel().getColumn(0).setPreferredWidth(80);

            String limpeza = "Sem informações";

            e.addRow(new Object[]{limpeza});            
            
    }
    
    public String retornaMesExtenso(int mes){
        String mesExtenso = null;
        
        switch(mes){
            case 1:
                mesExtenso = "JAN";
                break;
            case 2:
                mesExtenso = "FEV";
                break;
            case 3:
                mesExtenso = "MAR";
                break;
            case 4:
                mesExtenso = "ABR";
                break;
            case 5:
                mesExtenso = "MAI";
                break;
            case 6:
                mesExtenso = "JUN";
                break;
            case 7:
                mesExtenso = "JUL";
                break;
            case 8:
                mesExtenso = "AGO";
                break;
            case 9:
                mesExtenso = "SET";
                break;
            case 10:
                mesExtenso = "OUT";
                break;
            case 11:
                mesExtenso = "NOV";
                break;
            case 12:
                mesExtenso = "DEZ";
                break;
        }
        return mesExtenso;
        
    }
    
}
