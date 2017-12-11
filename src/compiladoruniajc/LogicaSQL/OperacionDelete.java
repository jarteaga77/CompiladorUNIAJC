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
public class OperacionDelete {
    
    String strInstruccionSQL = "";
    String strNombreTabla,strC1;
    Tokens tokens;
    int i,j,c;
 
   public  OperacionDelete(String cadSQL)
    {
    tokens = new Tokens(cadSQL);
    }
 
    public void analizar()
    {
     tokens.obtenerToken();      //Lee  la palabra delete
     tokens.obtenerToken();      //Lee la palabra from
     if( tokens.intTipoToken == tokens.PALABRA_CLAVE && tokens.token.toString().toLowerCase().equals("from"))
     {
       tokens.obtenerToken();            //leer el token  del nombre de la tabla
     }
     else
     {
       mensaje_parser("\n ERROR! Se esperaba la palabra clave FROM. ");
       return;
     }
 
     if( tokens.intTipoToken != tokens.IDENTIFICADOR  )
     {
        mensaje_parser("\n Error! Se esperaba el nombre de la tabla. ");
        return;
     }
        strNombreTabla=tokens.token.toString(); //Guardar el nombre de la tabla
 
        //Para mostrar los campos eliminados
       int cont_eliminados=0;
 
        //Leeer el siguiente token deespues del nombre de la tabla
        tokens.obtenerToken();
     if( tokens.intTipoToken == tokens.DELIMITADOR && tokens.token.toString().toLowerCase().equals(";") )
     {
       //Cambiarnos a la primera ficha
         JICompiladorSQL.jTabbedPane.setSelectedIndex(0);
       mensaje_SBDD("\n\n Se eliminaran TODOS los registros de la tabla " + strNombreTabla + ". En total " +cont_eliminados);
       //aqui se haria el delete la tabla que indico el usuario
       mensaje_SBDD("\n Operacion DELETE realizada con exito.");
       return;
     }   //Fin de checkar si es ;
 
       String campo1,campo2,campo3, valor1,valor2,valor3, operador1,operador2,operador3, logico1,logico2,logico3;
       String tipo_valor1,tipo_valor2,tipo_valor3,tipo_valor4;
 
       // ######################## COMPROBAR QUE EXISTE WHERE  ########################
     if( tokens.intTipoToken == tokens.PALABRA_CLAVE && tokens.token.toString().toLowerCase().equals("where"))
     {
        tokens.obtenerToken();            //leer el nombre del primer campo de asignacion
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba la palabra clave WHERE. ");
        return;
     }
 
      // ######################## LEER LA CONDICION 1   ########################
     if( tokens.intTipoToken == tokens.IDENTIFICADOR  )
     {
        campo1 = tokens.token.toString();
        tokens.obtenerToken();            //leer el operador de asignacion
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba el nombre de un identificador de campo de la tabla " + strNombreTabla);
        return;
     }
 
     if( tokens.intTipoToken == tokens.OPERADOR  )
     {
        operador1 = tokens.token.toString();
       if( ! operador1.equals("=") )
        {
           mensaje_parser("\n ERROR! Se esperaba el operador = , no el operador  " + operador1  );
           return;
        }
        tokens.obtenerToken();            //leer el operador
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba un operador " );
        return;
     }
 
     if( tokens.intTipoToken == tokens.NUMERO_ENTERO || tokens.intTipoToken==tokens.NUMERO_DECIMAL || tokens.intTipoToken==tokens.CADENA  )
     {
        tipo_valor1=(String) tokens.strTipoToken;      //Guardar el tipo de valor de campo; en forma de cadena
      if(tipo_valor1.equals("char"))
         valor1 = "'"+tokens.token.toString()+"'";
      else
         valor1 = tokens.token.toString();
         tokens.obtenerToken();            //leer el siguiente token la asignacion  ; ó  or ó and
     }
     else
     {
          mensaje_parser("\n ERROR! Se esperaba un valor integer, decimal o char para el campo "+ campo1 );
          return;
     }
 
      //Checkar si solo hay una condicion, si se logra pasar este tope (return), hay otra condicion
     if( tokens.intTipoToken == tokens.DELIMITADOR && tokens.token.toString().toLowerCase().equals(";") )
     {
        strC1 = new String("DELETE FROM " +  strNombreTabla + " WHERE "+ campo1+operador1+valor1);
        //Cambiarnos a la primera ficha
         JICompiladorSQL.jTabbedPane.setSelectedIndex(0);
        mensaje_SBDD("\n\n Instruccion DELETE con una condicion: "+strC1);
        //Aqui se intentaria ejecutar la instruccion...
        //Mostramos un msg al usuario para avisar del exito o fracaso de la ejecucion
        mensaje_SBDD("\n Operacion DELETE realizada con exito.");
        return;
 
     }
 
        // ######################## LEER LA CONDICION 2   ########################
     if( ! (tokens.intTipoToken == tokens.PALABRA_CLAVE) )
     {
        mensaje_parser("\n ERROR! se esperaba la palabra clave and  u or, no la palabra '" +tokens.token.toString() +"'" );
        return;
     }
 
        logico1 = tokens.token.toString();      //Recoger el operador logico ando u or
 
     if( logico1.toLowerCase().equals("and") || logico1.toLowerCase().equals("or") )
     {
        tokens.obtenerToken();
     }
 
     if( tokens.intTipoToken == tokens.IDENTIFICADOR  )
     {
        campo2 = tokens.token.toString();
        tokens.obtenerToken();            //leer el operador de asignacion
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba el nombre de un identificador de campo de la tabla " + strNombreTabla);
        return;
     }
 
     if( tokens.intTipoToken == tokens.OPERADOR  )
     {
        operador2 = tokens.token.toString();
        if( ! operador2.equals("=") )
        {
            mensaje_parser("\n ERROR! Se esperaba el operador = , no el operador  " + operador2  );
            return;
        }
        tokens.obtenerToken();            //leer el operador
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba un operador " );
        return;
     }
 
     if( tokens.intTipoToken == tokens.NUMERO_ENTERO || tokens.intTipoToken==tokens.NUMERO_DECIMAL || tokens.intTipoToken==tokens.CADENA  )
     {
        tipo_valor2=(String) tokens.strTipoToken;      //Guardar el tipo de valor de campo; en forma de cadena
        if(tipo_valor2.equals("char"))
        valor2 =  "'"+tokens.token.toString()+"'";
        else
        valor2 =  tokens.token.toString();
        tokens.obtenerToken();            //leer el siguiente token la asignacion  ; ó  ,
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba un valor integer, decimal o char para el campo "+ campo2 );
        return;
     }
 
        //Checkar si solo hay una condicion, si se logra pasar este tope (return), hay otra tercera condicion
     if( tokens.intTipoToken == tokens.DELIMITADOR && tokens.token.toString().toLowerCase().equals(";") )
     {
        strC1 = new String("DELETE FROM " +  strNombreTabla + " WHERE "+ campo1+operador1+valor1+ " " + logico1 + " " +campo2+operador2+valor2 );
        //Cambiarnos a la primera ficha
        JICompiladorSQL.jTabbedPane.setSelectedIndex(0);
        mensaje_SBDD("\n\n Instruccion DELETE con 2 condiciones: "+strC1);
        //Aqui se intentaria ejecutar la instruccion...
        //Mostramos un msg al usuario para avisar del exito o fracaso de la ejecucion
        mensaje_SBDD("\n Operacion DELETE realizada con exito.");
        return;
     }
 
        // ######################## LEER LA CONDICION 3   ########################
     if( ! (tokens.intTipoToken == tokens.PALABRA_CLAVE) )
     {
        mensaje_parser("\n ERROR! se esperaba la palabra clave and  u or, no la palabra '" +tokens.token.toString() +"'" );
        return;
     }
 
        logico2 = tokens.token.toString();      //Recoger el operador logico ando u or
 
     if( logico2.toLowerCase().equals("and") || logico2.toLowerCase().equals("or") )
     {
        tokens.obtenerToken();
     }
 
     if( tokens.intTipoToken == tokens.IDENTIFICADOR  )
     {
        campo3 = tokens.token.toString();
        tokens.obtenerToken();            //leer el operador de asignacion
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba el nombre de un identificador de campo de la tabla " + strNombreTabla);
        return;
     }
 
     if( tokens.intTipoToken == tokens.OPERADOR  )
     {
        operador3 = tokens.token.toString();
      if( ! operador3.equals("=") )
      {
            mensaje_parser("\n ERROR! Se esperaba el operador = , no el operador  " + operador3  );
            return;
      }
            tokens.obtenerToken();            //leer el operador
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba un operador " );
        return;
     }
 
     if( tokens.intTipoToken == tokens.NUMERO_ENTERO || tokens.intTipoToken==tokens.NUMERO_DECIMAL || tokens.intTipoToken==tokens.CADENA  )
     {
        tipo_valor3= tokens.strTipoToken;      //Guardar el tipo de valor de campo; en forma de cadena
      if(tipo_valor3.equals("char"))
            valor3 =  "'"+tokens.token.toString()+"'";
      else
        valor3 =  tokens.token.toString();
        tokens.obtenerToken();            //leer el siguiente token la asignacion  ; ó  ,
     }
     else
     {
        mensaje_parser("\n ERROR! Se esperaba un valor integer, decimal o char para el campo "+ campo3 );
        return;
     }
 
        //Checkar si solo hay una ultima condicion; este analizados sintactico como maximo soporta e condiciones unidas con and u or
     if( tokens.intTipoToken == tokens.DELIMITADOR && tokens.token.toString().toLowerCase().equals(";") )
      {
        strC1 = new String("DELETE FROM " +  strNombreTabla + " WHERE "+ campo1+operador1+valor1+ " " + logico1 + " " +campo2+operador2+valor2 + " " + logico2 + " " +campo3+operador3+valor3);
        //Cambiarnos a la primera ficha
        JICompiladorSQL.jTabbedPane.setSelectedIndex(0);
        mensaje_SBDD("\n\n Instruccion DELETE con 3 condiciones: "+strC1);
        //Aqui se intentaria ejecutar la instruccion...
        //Mostramos un msg al usuario para avisar del exito o fracaso de la ejecucion
        mensaje_SBDD("\n Operacion DELETE realizada con exito.");
        return;
      }
 
    }
 
    public void mensaje_parser(String msg)
    {
      JICompiladorSQL.txt_area_compi.append("\n" + msg );
    }
 
    public void mensaje_SBDD(String msg)
    {
      JICompiladorSQL.txt_area_eje.append( msg );
    }
    
}
