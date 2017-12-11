
/*
 * Splash.java
 *
 * 
 */
package compiladoruniajc;

import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;


/**
 *
 * @author jarteaga
 */
public class Splash extends javax.swing.JFrame implements Runnable{

   Thread hilo;
   
   int num = 0;
  
    public Splash() 
    {
        initComponents();
        Color c1 = new Color(66,111,166);
        Color c2 = new Color(102,174,118);
        JPColor jpmicolor = new JPColor(c1,c2);
        jpmicolor.setSize(this.getSize());        
        jPanel1.add(jpmicolor); 
        
        
    }
    
     @SuppressWarnings("unchecked")
    @Override
    public Image getIconImage() 
      {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("compiladoruniajc/recursos/icono2.png"));
        return retValue;
    }
 
     public void progresoBarra() {
    while (num < 1000) {
      jPB_barra.setValue(num);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
      }
      num += 100;
    }
    
  }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPB_barra = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compilador UNIAJC (1.0)");
        setIconImage(getIconImage());
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPB_barra.setMaximum(1000);

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("COMPILADOR UNIAJC");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("V.1");

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("  Esta cargando,espere por favor.........");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPB_barra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel3))
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPB_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void run()
{
    this.setLocationRelativeTo(null);
    this.setVisible(true);
        try {
            progresoBarra();
            hilo.sleep(700);
            this.dispose();
            
            MDIPrincipal principal = new MDIPrincipal(); // Inicializa la ventana con los menús de la aplicación
            principal.setLocationRelativeTo(null);
            principal.setVisible(false);//Frame Principal No arranca visible
            principal.setExtendedState(JFrame.MAXIMIZED_BOTH);//Frame toma el tramaño de la resolución de la pantalla
            principal.iniciarSistema();
        }  catch (InterruptedException ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        } 
       
          catch (Exception ex) {
            ex.printStackTrace(); // Registra en la salida de linea de comandos, si hay un error en la inicialización del sistema
        }
}



    public static void main(String args[]) throws ParseException {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;");
                    break;
                }
            }
            UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MDIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Splash().setVisible(true);
            }
        });
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JProgressBar jPB_barra;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
    
}
