package edu.eci.escuelaing.arep.networking;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLExamples {
    public static void main(String[] args){
        try {
            URL firstSite = new URL("https://campusvirtual.escuelaing.edu.co/moodle/mod/assign/view.php?id=31610");
            System.out.println("La URL es: " + firstSite.toString());
            System.out.println("El HOST es: " + firstSite.getHost());
            System.out.println("El puerto es: " + firstSite.getPort());
            System.out.println("El PATH es: " + firstSite.getPath());
            System.out.println("El protocolo es: " + firstSite.getProtocol());
            System.out.println("El query es: " + firstSite.getQuery());
            System.out.println("El archivo es: " + firstSite.getFile());
            System.out.println("La referencia es: " + firstSite.getRef());
            System.out.println("El authority es: " + firstSite.getAuthority());
        }
        catch (MalformedURLException ex){
            Logger.getLogger(URLExamples.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
