/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistejm;
import br.com.sistejm.telas.TelaInicial;
import br.com.sistejm.telas.TelaInicialNew;
/**
 *
 * @author Michel
 */
public class SISTEJM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        TelaInicialNew tela = new TelaInicialNew();
        TelaInicial tela = new TelaInicial();
        tela.setTitle("SISTEJM - Sistema TEJM");
        tela.setLocationRelativeTo(tela);
        tela.setExtendedState(tela.MAXIMIZED_BOTH);
        tela.setDefaultCloseOperation(tela.EXIT_ON_CLOSE);
        tela.setVisible(true);
        
        // TODO code application logic here
    }
    
}
