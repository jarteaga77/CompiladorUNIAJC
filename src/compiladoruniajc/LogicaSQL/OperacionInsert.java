/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruniajc.LogicaSQL;

import compiladoruniajc.JICompiladorSQL;

/**
 *
 * @author George
 */
public class OperacionInsert {

    String strInstruccionSQL = "";
    Tokens tokens;
    int i, j, c;

    public OperacionInsert(String CadSQL) {
        tokens = new Tokens(CadSQL);
    }

    public void analizar() {
        // Tokens tokens = new Tokens(app);

        tokens.obtenerToken();      //Lee la primera palabra de la cadena, o sea la palabra insert
        tokens.obtenerToken();      //Lee la segunda palabra de la cadena, o sea la palabra into

        if (tokens.intTipoToken == tokens.PALABRA_CLAVE && tokens.token.toString().toLowerCase().equals("into")) {
            tokens.obtenerToken();            //leer el token  del nombre de la tabla
        } else {
            System.out.println(tokens);
            mensaje_parser("ERROR! se esperaba la palabra clave INTO, no la palabra '" + tokens.token.toString() + "'");
            return;
        }

        if (tokens.intTipoToken != tokens.IDENTIFICADOR) {
            mensaje_parser("ERROR! se esperaba el nombre de la tabla");
            return;
        }

        tokens.obtenerToken();      //leer el token values

        if (tokens.intTipoToken == tokens.PALABRA_CLAVE && tokens.token.toString().toLowerCase().equals("values")) {
            tokens.obtenerToken();                                       //leer el token del parentesis
        } //fin de comprobar si es la keyword values
        else {
            if (tokens.token.length() > 0) {
                mensaje_parser("Se esperaba la palabra clave VALUES y no  la palabra '" + tokens.token.toString() + "'.");
            } else {
                mensaje_parser("Se esperaba la palabra clave VALUES.");
            }

            return;
        }

        if (!tokens.token.toString().equals("(")) {
            if (tokens.token.length() > 0) {
                mensaje_parser("Se esperaba el parentesis de apertura ( y no '" + tokens.token.toString() + "'.");
            } else {
                mensaje_parser("Se esperaba el parentesis de apertura (.");
            }

            return;
        }

        tokens.obtenerToken();              //leer el token del primer dato a insertar
        StringBuffer strDatosAInsertar = new StringBuffer("");
        StringBuffer strTiposDatosAInsertar = new StringBuffer("");
        //En el siguiente bucle se van a guardar los datos que el usuario desea insertar y sus tipos de datos
        do {   // Guardamos los tokens
            strDatosAInsertar.append(tokens.token.toString());

            if (tokens.intTipoToken == tokens.ERROR) {
                mensaje_parser("\n\nERROR, Detalles en la ficha de msgs del parser ");
                return;
            }

            if (tokens.intTipoToken == tokens.DELIMITADOR) {
                strTiposDatosAInsertar.append(",");
            } else // Guardar el string del tipo de token  */
            {
                strTiposDatosAInsertar.append(tokens.intTipoToken);
            }

            tokens.obtenerToken();              //leer siguiente token */
        } while (!tokens.token.toString().equals(")"));
        //Cambiarnos a la primera ficha
        JICompiladorSQL.jTabbedPane.setSelectedIndex(0);
        mensaje_SBDD("La sintaxis es correcta:\nLos datos a insertar son: " + strDatosAInsertar.toString() + " (" + strTiposDatosAInsertar.toString() + ")");

    }        //Fin del metodo que analiza la sintaxis del INSERT

    // Muestra un mensaje de error que llega
    public void mensaje_SBDD(String elMsg) {
        JICompiladorSQL.txt_area_eje.append("\n" + elMsg);
    }

    public void mensaje_parser(String msg) {
        JICompiladorSQL.txt_area_compi.append("\n" + msg);
    }

}
