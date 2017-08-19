/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntryPackage;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import MLTools.Ont2Java;
import com.hp.hpl.jena.rdf.model.Model;
import com.ks.data_provenance.voidgenerator;
import static java.awt.EventQueue.invokeLater;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static java.lang.Short.MAX_VALUE;
import static java.lang.System.out;
import static java.lang.System.setOut;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextPane;
import static javax.swing.UIManager.getInstalledLookAndFeels;
import static javax.swing.UIManager.setLookAndFeel;
import static org.jdesktop.layout.GroupLayout.BASELINE;
import static org.jdesktop.layout.GroupLayout.DEFAULT_SIZE;
import static org.jdesktop.layout.GroupLayout.LEADING;
import static org.jdesktop.layout.GroupLayout.PREFERRED_SIZE;
import static org.jdesktop.layout.GroupLayout.TRAILING;
import static org.jdesktop.layout.LayoutStyle.RELATED;
import static org.jdesktop.layout.LayoutStyle.UNRELATED;

/**
 *
 * @author home
 */
public class MainWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainWindow
     */
    
    File mar21File;
    Model biboModel;
    
    public MainWindow() {
        
        this.mar21File = null;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textPaneMarc = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        textPaneVoid = new javax.swing.JTextPane();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblFileSize = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotalMarcRecords = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalLDRecord = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        textPaneLD = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDsTitle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDsPublisher = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDsDesc = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        txtDsSource = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDsLicense = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDsSparql = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtDsUri = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        addRDFLinkCheckBox = new java.awt.Checkbox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Marc to Linked Data (Building Linked Library Data)");

        jLabel1.setText("Source File");

        txtFileName.setEditable(false);
        txtFileName.setText("Click to Choose File (Marc Format)");
        txtFileName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFileNameMouseClicked(evt);
            }
        });
        txtFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFileNameActionPerformed(evt);
            }
        });

        textPaneMarc.setEditable(false);
        jScrollPane1.setViewportView(textPaneMarc);

        jLabel2.setText("Marc Records");

        textPaneVoid.setEditable(false);
        jScrollPane2.setViewportView(textPaneVoid);

        jLabel3.setText("Linked Data");

        jLabel4.setText("File Size:");

        lblFileSize.setBackground(new java.awt.Color(51, 51, 255));
        lblFileSize.setText("0");

        jLabel5.setText("Total Marc Records:");

        lblTotalMarcRecords.setBackground(new java.awt.Color(51, 51, 255));
        lblTotalMarcRecords.setText("0");

        jLabel6.setText("Total Linked Data Records:");

        lblTotalLDRecord.setBackground(new java.awt.Color(51, 51, 255));
        lblTotalLDRecord.setText("0");

        jLabel7.setText("Total Conversion Time:");

        jLabel8.setText("0:0");

        jButton1.setText("To RDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Clear All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        textPaneLD.setEditable(false);
        jScrollPane3.setViewportView(textPaneLD);

        jLabel9.setText("Provenance (VoID)");

        jLabel10.setText("Title");

        txtDsTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDsTitleActionPerformed(evt);
            }
        });

        jLabel11.setText("Description");

        jLabel12.setText("Publisher");

        txtDsPublisher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDsPublisherActionPerformed(evt);
            }
        });

        jLabel13.setText("Provenance Information");

        txtDsDesc.setColumns(20);
        txtDsDesc.setRows(5);
        jScrollPane4.setViewportView(txtDsDesc);

        jLabel14.setText("Source");

        txtDsSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDsSourceActionPerformed(evt);
            }
        });

        jLabel15.setText("License");

        txtDsLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDsLicenseActionPerformed(evt);
            }
        });

        jLabel16.setText("SPARQL end-point");

        txtDsSparql.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDsSparqlActionPerformed(evt);
            }
        });

        jButton3.setText("Generate VoID");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel17.setText("URI");

        txtDsUri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDsUriActionPerformed(evt);
            }
        });

        jButton4.setText("To RDF Using Spark");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("To Text");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        addRDFLinkCheckBox.setLabel("Add RDF Links?");

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jSeparator1)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(txtFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 243, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(layout.createSequentialGroup()
                                .add(jButton1)
                                .add(18, 18, 18)
                                .add(jButton5)
                                .add(18, 18, 18)
                                .add(jButton4)))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(jLabel4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblFileSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(51, 51, 51)
                                .add(jLabel5)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(lblTotalMarcRecords, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(40, 40, 40)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel6)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(lblTotalLDRecord, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel8)))
                                    .add(jLabel7)))
                            .add(layout.createSequentialGroup()
                                .add(10, 10, 10)
                                .add(addRDFLinkCheckBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(158, 158, 158)
                                .add(jButton2)
                                .add(213, 213, 213)
                                .add(jButton3))))
                    .add(layout.createSequentialGroup()
                        .add(139, 139, 139)
                        .add(jLabel2)
                        .add(273, 273, 273)
                        .add(jLabel3))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 332, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 339, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(layout.createSequentialGroup()
                                        .add(122, 122, 122)
                                        .add(jLabel13))
                                    .add(layout.createSequentialGroup()
                                        .add(51, 51, 51)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jLabel12)
                                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                .add(jLabel15)
                                                .add(jLabel14))
                                            .add(jLabel17))
                                        .add(18, 18, 18)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                            .add(txtDsPublisher)
                                            .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                                    .add(txtDsSource, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                    .add(txtDsLicense, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                                .add(0, 0, Short.MAX_VALUE))
                                            .add(txtDsUri)))
                                    .add(layout.createSequentialGroup()
                                        .add(29, 29, 29)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                            .add(jLabel11)
                                            .add(jLabel10))
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(layout.createSequentialGroup()
                                                .add(25, 25, 25)
                                                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 323, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                            .add(layout.createSequentialGroup()
                                                .add(16, 16, 16)
                                                .add(txtDsTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 332, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                    .add(layout.createSequentialGroup()
                                        .add(jLabel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(txtDsSparql, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 328, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                                        .add(12, 12, 12)
                                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 442, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(filler2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(546, 546, 546))))
                            .add(layout.createSequentialGroup()
                                .add(226, 226, 226)
                                .add(jLabel9)))))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(txtFileName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4)
                    .add(lblFileSize)
                    .add(jLabel5)
                    .add(lblTotalMarcRecords)
                    .add(jLabel6)
                    .add(lblTotalLDRecord))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel8)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButton5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton3))
                    .add(addRDFLinkCheckBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jLabel13))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                            .add(jScrollPane1))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(59, 59, 59)
                                .add(jLabel11))
                            .add(layout.createSequentialGroup()
                                .add(22, 22, 22)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(txtDsTitle, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(jLabel10))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(8, 8, 8)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtDsUri, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel17))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtDsPublisher, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel12))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtDsSource, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel14))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(txtDsLicense, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel15))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(txtDsSparql, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(56, 56, Short.MAX_VALUE)
                                .add(filler2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(306, 306, 306))
                            .add(layout.createSequentialGroup()
                                .add(18, 18, 18)
                                .add(jLabel9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 259, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFileNameActionPerformed
        
        
    }//GEN-LAST:event_txtFileNameActionPerformed

    private void txtFileNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFileNameMouseClicked

        JFileChooser _fileChooser = new JFileChooser();
        int retval = _fileChooser.showOpenDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) 
        {
            //... The user selected a file, get it, use it.
            mar21File = _fileChooser.getSelectedFile();
            //... Update user interface.
            txtFileName.setText(mar21File.getPath());
        }
    }//GEN-LAST:event_txtFileNameMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        if(null == this.mar21File)
        {
           JOptionPane.showMessageDialog(null, "Please select file first!");
           return;
        }
        
        MarcConverter converter = new MarcConverter(this.mar21File, textPaneMarc, textPaneLD, 100, addRDFLinkCheckBox.getState());
        biboModel = converter.ConverMarcWithFile();
//        converter.ConvertMarc21ToRDFUsingSpark();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        this.mar21File = null;
        txtFileName.setText("Click to Choose File (Marc Format)");
        textPaneMarc.setText("");
        textPaneLD.setText("");
        textPaneVoid.setText("");
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtDsTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsTitleActionPerformed

    private void txtDsPublisherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsPublisherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsPublisherActionPerformed

    private void txtDsSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsSourceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsSourceActionPerformed

    private void txtDsLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsLicenseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsLicenseActionPerformed

    private void txtDsSparqlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsSparqlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsSparqlActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        
        voidgenerator voidGen = new voidgenerator();
        voidGen.dsDescription = txtDsDesc.getText();
        voidGen.dsLicense = txtDsLicense.getText();
        voidGen.dsPublisher = txtDsPublisher.getText();
        voidGen.dsSource = txtDsSource.getText();
        voidGen.dsTitle = txtDsTitle.getText();
        voidGen.dsURI = txtDsUri.getText();
        voidGen.dsSparqlEndPoint = txtDsSparql.getText();
        
        Model voidModel = voidGen.generateProvenanceUsingPROVForModel(biboModel);
        
        if(null != voidModel)
            this.writeModelToTextArea(voidModel, "RDF/XML", textPaneVoid);
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtDsUriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDsUriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDsUriActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        if(null == this.mar21File)
        {
           JOptionPane.showMessageDialog(null, "Please select file first!");
           return;
        }
        MarcConverter converter = new MarcConverter(this.mar21File, textPaneMarc, textPaneLD, 100, addRDFLinkCheckBox.getState());
        converter.ConvertMarc21ToRDFUsingSpark();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        if(null == this.mar21File)
        {
           JOptionPane.showMessageDialog(null, "Please select file first!");
           return;
        }
        MarcConverter converter = new MarcConverter(this.mar21File, textPaneMarc, textPaneLD, 100, addRDFLinkCheckBox.getState());
        converter.converMarcToText();
    }//GEN-LAST:event_jButton5ActionPerformed

    
     private void writeModelToTextArea(Model m, String format, JTextPane pane)
    {
        pane.setText("");
         //redirect output to textarea
        ByteArrayOutputStream pipeOut = new ByteArrayOutputStream();

         // Store the current System.out
        PrintStream old_out = System.out;
       
         // Replace redirect output to our stream
        System.setOut(new PrintStream(pipeOut));


        // Revert back to the old System.out
        System.setOut(old_out);
        
//         m.write(pipeOut, "N3");
        m.write(pipeOut, format);
         
         Long noOfStmts = m.size();
         String no_ofStmts = noOfStmts.toString();
         
         String stmts = new String(pipeOut.toByteArray());

        pane.setText("Number of statements:" + no_ofStmts + "\n" + stmts);
        
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Checkbox addRDFLinkCheckBox;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblFileSize;
    private javax.swing.JLabel lblTotalLDRecord;
    private javax.swing.JLabel lblTotalMarcRecords;
    private javax.swing.JTextPane textPaneLD;
    private javax.swing.JTextPane textPaneMarc;
    private javax.swing.JTextPane textPaneVoid;
    private javax.swing.JTextArea txtDsDesc;
    private javax.swing.JTextField txtDsLicense;
    private javax.swing.JTextField txtDsPublisher;
    private javax.swing.JTextField txtDsSource;
    private javax.swing.JTextField txtDsSparql;
    private javax.swing.JTextField txtDsTitle;
    private javax.swing.JTextField txtDsUri;
    private javax.swing.JTextField txtFileName;
    // End of variables declaration//GEN-END:variables
}
