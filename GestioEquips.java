/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package p1.t7.vista.garciajimenezfrancisco;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.esport.interficiePersistencia.GestorBDEsportException;
import org.milaifontanals.esport.interficiePersistencia.IGestorBDEsport;
import org.milaifontanals.esport.model.*;

/**
 *
 * @author fran_
 */
public class GestioEquips extends javax.swing.JFrame {
    IGestorBDEsport bd = null;
    List<Temporada> lTemp = new ArrayList<>();
    List<Equip> lEquips = new ArrayList<>();
    Boolean temporadaSeleccionada = false;

    public GestioEquips(IGestorBDEsport dades, String temporada) {
        this.bd = dades;
        initComponents();
        
        gdEquips.getColumnModel().getColumn(0).setMinWidth(0);  
        gdEquips.getColumnModel().getColumn(0).setMaxWidth(0);  
        gdEquips.getColumnModel().getColumn(0).setWidth(0);    
        gdEquips.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);

        
        carregarTemporades();
        cbTemporada.setSelectedItem(temporada);
        carregarEquipsFiltrats(temporada);

    }
    
    private void carregarEquipsFiltrats(String temporada) {
        try {
            if ((temporada == null || temporada.isEmpty()) && !temporadaSeleccionada) {
                temporada = (String) cbTemporada.getSelectedItem();
            }

            if (temporada == null || temporada.isEmpty()) {
                lbInfo.setForeground(Color.RED);
                lbInfo.setText("Selecciona una temporada abans de carregar els equips.");
                return;
            }

            lEquips = bd.getEquips();
            List<Equip> equipsFiltrats = new ArrayList<>();

            for (Equip e : lEquips) {
                if (e.getAny_temporada() == Integer.parseInt(temporada)) {
                    equipsFiltrats.add(e);
                }
            }

            DefaultTableModel model = (DefaultTableModel) gdEquips.getModel();
            model.setRowCount(0);

            for (Equip e : equipsFiltrats) {
                Integer idCat = e.getId_categoria();
                Categoria categoria = bd.getCategoriaById(idCat);
                Object[] row = {e.getId(), e.getNom(), e.getTipus(), e.getAny_temporada(), categoria.getNom()};
                model.addRow(row);
            }

        } catch (NumberFormatException e) {
            lbInfo.setForeground(Color.RED);
            lbInfo.setText("Format de temporada no vàlid.");
        } catch (GestorBDEsportException e) {
            lbInfo.setForeground(Color.RED);
            lbInfo.setText("Error en carregar els equips: " + e.getMessage());
        }
    }


    
//    private void carregarEquips() {
//        try {
//            String temporadaSeleccionada = (String) cbTemporada.getSelectedItem();
//            
//            lEquips = bd.getEquips(); 
//            
//            DefaultTableModel model = (DefaultTableModel) gdEquips.getModel();
//            
//            model.setRowCount(0);
//            
//            
//            for (Equip e : lEquips) {
//                Integer idCat = e.getId_categoria();
//                Categoria categoria = bd.getCategoriaById(idCat); 
//                
//                Object[] row = {e.getId(), e.getNom(), e.getTipus(), e.getAny_temporada(),categoria.getNom()};
//                model.addRow(row);
//            }
//        } catch (GestorBDEsportException e) {
//            System.out.println("Error al cargar los equipos: " + e.getMessage());
//        }
//    }
   
    private void carregarTemporades() {
        try {
            List<Temporada> lTemp = bd.getTemporades(); 

            cbTemporada.removeAllItems();

            for (Temporada t : lTemp) {
                cbTemporada.addItem(String.valueOf(t.getAny()));
            }
        } catch (GestorBDEsportException e) {
            System.out.println("Error al cargar las temporadas: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbTemporada = new javax.swing.JComboBox<>();
        bFilte = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        gdEquips = new javax.swing.JTable();
        btAddEquip = new javax.swing.JToggleButton();
        btDeleteEquip = new javax.swing.JToggleButton();
        btEditarEquip = new javax.swing.JToggleButton();
        btExportarCsv = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        lbInfo = new javax.swing.JLabel();
        btFitxarEquip = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel1.setText("Gestor de equips");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Filtrar");

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Temporada");

        cbTemporada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTemporada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTemporadaActionPerformed(evt);
            }
        });

        bFilte.setBackground(new java.awt.Color(153, 153, 153));
        bFilte.setForeground(new java.awt.Color(255, 255, 255));
        bFilte.setText("Mostra");
        bFilte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFilteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cbTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(bFilte)
                .addGap(28, 28, 28))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bFilte))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        gdEquips.setBackground(new java.awt.Color(204, 204, 255));
        gdEquips.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nom", "Tipus", "Any temporada", "Categoria"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(gdEquips);

        btAddEquip.setBackground(new java.awt.Color(255, 204, 153));
        btAddEquip.setText("Afegir equip");
        btAddEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddEquipActionPerformed(evt);
            }
        });

        btDeleteEquip.setBackground(new java.awt.Color(255, 153, 153));
        btDeleteEquip.setText("Esborrar equip");
        btDeleteEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteEquipActionPerformed(evt);
            }
        });

        btEditarEquip.setBackground(new java.awt.Color(204, 255, 204));
        btEditarEquip.setText("Editar equip");
        btEditarEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarEquipActionPerformed(evt);
            }
        });

        btExportarCsv.setBackground(new java.awt.Color(204, 204, 255));
        btExportarCsv.setText("Exportar dades del equip");
        btExportarCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportarCsvActionPerformed(evt);
            }
        });

        jToggleButton5.setBackground(new java.awt.Color(204, 204, 204));
        jToggleButton5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jToggleButton5.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton5.setText("<<");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });

        btFitxarEquip.setBackground(new java.awt.Color(153, 204, 255));
        btFitxarEquip.setText("Fitxar jugadors");
        btFitxarEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFitxarEquipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jToggleButton5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(264, 264, 264)
                                    .addComponent(jLabel1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(169, 169, 169)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(143, 143, 143)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btAddEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(btEditarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(btFitxarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(28, 28, 28)
                                    .addComponent(btDeleteEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btExportarCsv, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jToggleButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAddEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeleteEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEditarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btFitxarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExportarCsv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bFilteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFilteActionPerformed
        String temporada = (String) cbTemporada.getSelectedItem();
        carregarEquipsFiltrats(temporada);
    }//GEN-LAST:event_bFilteActionPerformed

    private void btAddEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddEquipActionPerformed
        afegirEquip addEq = new afegirEquip(this.bd, null);
        addEq.setVisible(true);
        this.dispose();       
    }//GEN-LAST:event_btAddEquipActionPerformed

    private void cbTemporadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTemporadaActionPerformed
        temporadaSeleccionada = true;
    }//GEN-LAST:event_cbTemporadaActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        App app = new App(this.bd);
        app.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void btEditarEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarEquipActionPerformed
        int filaSeleccionada = gdEquips.getSelectedRow();
        if (filaSeleccionada != -1) {
            String categoriaNom = (String) gdEquips.getValueAt(filaSeleccionada, 4); 
            try {
                int cat = bd.getIdCategoriaByNom(categoriaNom);
                Equip equip = new Equip(
                    (int) gdEquips.getValueAt(filaSeleccionada, 0), 
                    (String) gdEquips.getValueAt(filaSeleccionada, 1), 
                    (char) gdEquips.getValueAt(filaSeleccionada, 2),  
                    (int) gdEquips.getValueAt(filaSeleccionada, 3), 
                    cat  
                );
                System.out.println(equip);

                try {
                    afegirEquip addEq = new afegirEquip(this.bd, equip);
                    addEq.setVisible(true);
                    this.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    lbInfo.setForeground(Color.RED);
                    lbInfo.setText("Error al carregar la finestra d'edició d'equips.");
                }

            } catch (GestorBDEsportException e) {
                System.out.println(e.getMessage());
                lbInfo.setForeground(Color.RED);
                lbInfo.setText(e.getMessage());
            }
        } else {
            lbInfo.setForeground(Color.RED);
            lbInfo.setText("Selecciona un equip per editar.");
        }
    }//GEN-LAST:event_btEditarEquipActionPerformed

    private void btDeleteEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteEquipActionPerformed
        int filaSeleccionada = gdEquips.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEquip = (int) gdEquips.getValueAt(filaSeleccionada, 0);
            confirmaDelEquip cde = new confirmaDelEquip(this.bd, idEquip);
            cde.setVisible(true);
            this.dispose();         
        } else {
            lbInfo.setForeground(Color.RED);
            lbInfo.setText("Selecciona un equip per editar.");
        }

        
        
    }//GEN-LAST:event_btDeleteEquipActionPerformed

    private void btExportarCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportarCsvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btExportarCsvActionPerformed

    private void btFitxarEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFitxarEquipActionPerformed
        int filaSeleccionada = gdEquips.getSelectedRow();
        if (filaSeleccionada != -1) {
            try {
                Integer idEquip = (int) gdEquips.getValueAt(filaSeleccionada, 0);
                fitxarJugadorsEquip fje = new fitxarJugadorsEquip(this.bd, idEquip);
                fje.setVisible(true);
                this.dispose();         
            } catch (GestorBDEsportException e) {
                lbInfo.setForeground(Color.RED);
                lbInfo.setText("Error en carregar l'equip: " + e.getMessage());
            }
        } else {
            lbInfo.setForeground(Color.RED);
            lbInfo.setText("Selecciona un equip per fitxar jugadors.");
        }
    }//GEN-LAST:event_btFitxarEquipActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GestioEquips.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GestioEquips.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GestioEquips.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GestioEquips.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GestioEquips().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bFilte;
    private javax.swing.JToggleButton btAddEquip;
    private javax.swing.JToggleButton btDeleteEquip;
    private javax.swing.JToggleButton btEditarEquip;
    private javax.swing.JToggleButton btExportarCsv;
    private javax.swing.JToggleButton btFitxarEquip;
    private javax.swing.JComboBox<String> cbTemporada;
    private javax.swing.JTable gdEquips;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JLabel lbInfo;
    // End of variables declaration//GEN-END:variables
}
