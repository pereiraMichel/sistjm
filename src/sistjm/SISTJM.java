/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistjm;
import br.com.sistjm.telas.TelaInicial;
/**
 *
 * @author Michel
 */
public class SISTJM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TelaInicial tela = new TelaInicial();
        tela.setTitle("SISTJM - Sistema TJM");
        tela.setLocationRelativeTo(tela);
        tela.setExtendedState(tela.MAXIMIZED_BOTH);
        tela.setDefaultCloseOperation(tela.EXIT_ON_CLOSE);
        tela.setVisible(true);
        
        // TODO code application logic here
    }
    
}
