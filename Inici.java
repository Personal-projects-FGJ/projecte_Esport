package p1.t7.vista.garciajimenezfrancisco;

import javax.swing.*;
import org.milaifontanals.esport.interficiePersistencia.IGestorBDEsport;
import org.milaifontanals.esport.capaOracle.GestorBDEsport;

public class Inici {
    static private String nomClassePersistencia = null;

    public static void main(String[] args) {
        System.out.println("Inici del programa...");
        if (args.length > 0) {
            System.out.println(args[0]);
        } else {
            System.out.println("No s'han passat arguments al programa.");
        }
        try {
            
//            nomClassePersistencia = "org.milaifontanals.esport.capaOracle.GestorBDEsport";
            nomClassePersistencia = args[0];
            Class<?> clazz = Class.forName(nomClassePersistencia);
            IGestorBDEsport dades = (IGestorBDEsport) clazz.getDeclaredConstructor().newInstance();

            if (dades != null) {
                SwingUtilities.invokeLater(() -> {
                    Login login = new Login(dades);
                    login.setVisible(true);
                });
            }
        } catch (Exception e) {
            System.out.println("Error carregant la classe o iniciant Login:");
            e.printStackTrace();
        }

    }
}
