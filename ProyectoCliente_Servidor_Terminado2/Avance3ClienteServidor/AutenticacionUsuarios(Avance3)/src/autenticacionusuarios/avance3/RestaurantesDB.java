/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autenticacionusuarios.avance3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class RestaurantesDB extends javax.swing.JFrame {

    private JTable table;
    private DefaultTableModel tabla;

    Conexion conexion = new Conexion();

    /**
     * Creates new form JFrame
     */
    public RestaurantesDB() {
        initComponents();
        setTitle("Conexion JTable con MySQL");
        setSize(600, 400);
        setDefaultCloseOperation(RestaurantesDB.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabla = new DefaultTableModel();
        jTable1.setModel(tabla);
        tabla.addColumn("ID");
        tabla.addColumn("Nombre");
        tabla.addColumn("Precio plato");
        tabla.addColumn("Direccion");

        cargarDatos();
        add(new JScrollPane(table));
    }

    private void cargarDatos() {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = conexion.conectar();
            String sql = "SELECT * FROM Restaurantes";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            tabla.setRowCount(0);

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID_Restaurantes"),
                    rs.getString("Nombre_Restaurante"),
                    rs.getBigDecimal("Precio_Plato"),
                    rs.getString("Direccion")
                };
                tabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                conexion.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void agregarDatos() {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = conexion.conectar();

            String sql = "INSERT INTO Restaurantes (ID_Restaurantes, Nombre_Restaurante, Precio_Plato, Direccion) VALUES (?, ?, ?, ?)";
            pst = con.prepareStatement(sql);

            int id = Integer.parseInt(txtIDRest.getText());
            String nombre = txtNombreRest.getText();
            double precio = Double.parseDouble(txtPrecioPlatRest.getText());
            String direccion = txtDireccionRest.getText();

            pst.setInt(1, id);
            pst.setString(2, nombre);
            pst.setDouble(3, precio);
            pst.setString(4, direccion);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Datos agregados correctamente");
            cargarDatos();
            limpiarCampos();

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al agregar datos: " + e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    conexion.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void limpiarCampos() {
        txtIDRest.setText("");
        txtNombreRest.setText("");
        txtPrecioPlatRest.setText("");
        txtDireccionRest.setText("");
    }

    private void eliminarFila() {
        Connection con = null;
        PreparedStatement pst = null;

        try {
            int filaSeleccionada = jTable1.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
                return;
            }

            int id = Integer.parseInt(tabla.getValueAt(filaSeleccionada, 0).toString());

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de eliminar el registro con ID: " + id + "?",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            con = conexion.conectar();

            String sql = "DELETE FROM Restaurantes WHERE ID_Restaurantes = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.");

            cargarDatos();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar el registro: " + e.getMessage());
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    conexion.desconectar();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtIDRest = new javax.swing.JTextField();
        txtNombreRest = new javax.swing.JTextField();
        txtPrecioPlatRest = new javax.swing.JTextField();
        txtDireccionRest = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        txtIDRest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDRestActionPerformed(evt);
            }
        });

        txtPrecioPlatRest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioPlatRestActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        jLabel2.setText("Nombre");

        jLabel3.setText("Precio del plato");

        jLabel4.setText("Direccion");

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtIDRest, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombreRest, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecioPlatRest, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDireccionRest, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAgregar)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4))
                            .addComponent(btnEliminar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(txtIDRest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(txtNombreRest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11)
                        .addComponent(txtPrecioPlatRest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccionRest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(21, 21, 21)
                        .addComponent(btnAgregar)
                        .addGap(32, 32, 32)
                        .addComponent(btnEliminar)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDRestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDRestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDRestActionPerformed

    private void txtPrecioPlatRestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioPlatRestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioPlatRestActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregarDatos();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarFila();
    }//GEN-LAST:event_btnEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RestaurantesDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RestaurantesDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RestaurantesDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RestaurantesDB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestaurantesDB().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDireccionRest;
    private javax.swing.JTextField txtIDRest;
    private javax.swing.JTextField txtNombreRest;
    private javax.swing.JTextField txtPrecioPlatRest;
    // End of variables declaration//GEN-END:variables
}
