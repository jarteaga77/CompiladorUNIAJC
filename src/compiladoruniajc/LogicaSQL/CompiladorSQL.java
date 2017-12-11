/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruniajc.LogicaSQL;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author George
 */
public class CompiladorSQL {

    /**
     * @param args the command line arguments
     */
    
       //Tipo de token que puede haber en la cadena
   public final int     FIN_DE_ARCHIVO  =  -1;
   public final int     NUMERO_ENTERO   =   2;
   public final int     DELIMITADOR     =   3;
   public final int     SALTO_DE_LINEA  =   4;
   public final int     NINGUNO         =   5;
   public final int     IDENTIFICADOR   =   6;
   public final int     PALABRA_CLAVE   =   7;
   public final int     CADENA          =   8;
   public final int     PARENTESIS      =  9;
   public final int     NUMERO_DECIMAL   =   10;
   public final int     ASTERISCO   =       11;
   public final int     ERROR   =       12;
    
   //El constructor espera recibir una cadena sql de la cual se quieren extraer sus tokens
   private void analizarCadenaSQL(String cadSQL)
   {
      Tokens tokens = new Tokens(cadSQL);
 
//      System.out.println("\n\n");
      //Este bucle imprime los tokens que se van leyendo y el tipo de token que se leyo de la cadena SQL
      tokens.obtenerToken();        //Obtener el primer token de la cadena SQL
      while( ! tokens.token.equals(";") )
      {
         switch(tokens.intTipoToken)
         {
            case NUMERO_ENTERO:
               System.out.println(tokens.token+" \t\t\tNUMERO_ENTERO");
               break;
            case NUMERO_DECIMAL:
               System.out.println(tokens.token+" \t\t\tNUMERO_DECIMAL");
               break;
            case PALABRA_CLAVE:
               System.out.println(tokens.token+" \t\t\tPALABRA_CLAVE");
               break;
            case IDENTIFICADOR:
               System.out.println(tokens.token+" \t\t\tIDENTIFICADOR");
               break;
            case CADENA:
               System.out.println(tokens.token+" \t\t\tCADENA");
               break;
            case PARENTESIS:
               System.out.println(tokens.token+" \t\t\tPARENTESIS");
               break;
            case DELIMITADOR:
               System.out.println(tokens.token+" \t\t\tDELIMITADOR");
               break;
            case ASTERISCO:
               System.out.println(tokens.token+" \t\t\tASTERISCO");
               break;
            case ERROR:
               System.out.println(tokens.token + " \t\t\t<-- ERROR !");
               break;
         }
            tokens.obtenerToken();
        }
   }
   

   
   
   /*
    public static void main(String[] args) {
        // TODO code application logic here
        
        CompiladorSQL prueba1 = new CompiladorSQL();
      prueba1.analizarCadenaSQL("insert into s values('s1','ford','mty', 23,58.42)");
    }
   */
    
}
