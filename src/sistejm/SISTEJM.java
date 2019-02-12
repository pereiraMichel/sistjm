/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistejm;
import br.com.sistejm.telas.DFLogin;
import br.com.sistejm.telas.TelaInicial;
import br.com.sistejm.telas.TelaInicialNew;
import java.io.IOException;
/**
 *
 * @author Michel
 */
public class SISTEJM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//////        TelaInicialNew tela = new TelaInicialNew();
//        DFLogin login = new DFLogin();
//        login.setTitle("SISTEJM - Sistema TEJM");
//        login.setLocationRelativeTo(login);
////        tela.setExtendedState(tela.MAXIMIZED_BOTH);
//        login.setDefaultCloseOperation(login.EXIT_ON_CLOSE);
//        login.setVisible(true);
        
        
        TelaInicial tela = new TelaInicial();
        tela.setTitle("SISTEJM - Sistema TEJM");
        tela.setLocationRelativeTo(tela);
        tela.setExtendedState(tela.MAXIMIZED_BOTH);
        tela.setDefaultCloseOperation(tela.EXIT_ON_CLOSE);
        tela.setVisible(true);
        // TESTE
        /*
        System.out.println(" ******** TESTE DE CÁLCULOS ******** ");
        
        String vlr1 = "20.57";
        String vlr2 = "59.43";
        
        float vl1 = Float.valueOf(vlr1);
        float vl2 = Float.valueOf(vlr2);
        
        double tt = vl1 + vl2;
        
        System.out.println("Valor total (80,00): " + tt);
        
        */
        
    }
    
}
