/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawpkg;

import bdd.dao.ProjectsDAO;
import bdd.entities.Project;
import shapespkg.*;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;
import javax.swing.JColorChooser;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author otmangx
 */
public class MainFrame extends javax.swing.JFrame {

    DefaultTableModel jTableModel;
    String filename;
    Socket con;
    ObjectInputStream in;
    ObjectOutputStream out;
    Project pr;
    ProjectsDAO cp;
    boolean dragged=false;
    boolean terminate = false;
    Vector checkPoint;
    
    @SuppressWarnings("empty-statement")
    public MainFrame(Socket con, Project pr, ProjectsDAO cp) throws Exception {
        this.con = con;
        this.pr = pr;
        this.cp = cp;
        
        initComponents();
        setLocationRelativeTo(null);
        
        setColor(drawPanel1.drawcolor);
        drawPanel1.setParent(this);
        PropPanel.setVisible(false);
        
        //Table
        jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent event) {
            try {
                drawPanel1.selectShape(jTable.getSelectedRow());
            } catch (Exception e) { }
            repaint();
            }
        });
        jTableModel = ((javax.swing.table.DefaultTableModel)jTable.getModel());
        drawPanel1.setTable(jTable, jTableModel);
        
        try {
            checkPoint = (Vector) openProject().clone();
        } catch(Exception ex) {}
        
       
        Runnable code = new Runnable() {
            public void run() {
                while (!terminate) {
                    try {
                        Thread.sleep(300);
                        if (!dragged) openProject();
                        
                    } catch (Exception ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }}};
        //SwingUtilities.invokeLater(code);
        new Thread(code).start();
    }
    
    
    public void setColor(Color cl) {
        dragged = true;
        if (drawPanel1.indexshapeSelected!=-1) {
            drawPanel1.shapesList.get(drawPanel1.indexshapeSelected).setColor(cl);
            saveProject();
            repaint();
        }
        drawPanel1.drawcolor = cl;
        ButtonColor.setBackground(drawPanel1.drawcolor);
        dragged = false;
    }
    
    
    
    public void changeMode(String mode) {
        if (drawPanel1.indexshapeSelected==-1) drawPanel1.deselectShape();
        drawPanel1.mode = mode;
        switch(mode) {
            case "pencil":
                drawPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                PropPanel.setVisible(false);
                break;
            
            case "line":
                drawPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
                PropPanel.setVisible(false);
                break;
            case "rect":
            case "ellipse":
                drawPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
                PropPanel.setVisible(true);
                SizePanel.setVisible(false);
               
                break;
            case "move" :
                drawPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
                jToggleButton4.setSelected(true);
                PropPanel.setVisible(false);
        }
        
    }
    
    public void updateProperties(){
        if (drawPanel1.indexshapeSelected!=-1) {
            
            Color c = ((Bordered)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).getBorderColor();
            PropPanel.setVisible(true);
            SizePanel.setVisible(true);
            BorderColorButton.setBackground(c);
            jSpinner1.getModel().setValue((int)((Bordered)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).getBorderSize());
            jComboBox1.setSelectedItem(((Bordered)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).getBorderType());
            SliderWidth.setValue((int)
                    ((RectangularShape)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).getWidth()
            );
            SliderHeight.setValue((int)
                    ((RectangularShape)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).getHeight()
            );
            //if (jTable.getSelectedRow()!=drawPanel1.indexshapeSelected)
                //jTable.getSelectionModel().setSelectionInterval(drawPanel1.indexshapeSelected, 
                                                                //drawPanel1.indexshapeSelected); 
            if (jTable.getSelectedRow()!=drawPanel1.indexshapeSelected)
                jTable.getSelectionModel().setSelectionInterval(drawPanel1.indexshapeSelected, 
                                                                drawPanel1.indexshapeSelected);

        }
        else {
            jSpinner1.setValue((int)drawPanel1.shapeBorderSize);
            BorderColorButton.setBackground(drawPanel1.shapeBorderColor);
            jComboBox1.setSelectedItem(drawPanel1.shapeBorderType);
            PropPanel.setVisible(false);
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
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jFileSave = new javax.swing.JFileChooser();
        SaveDialog = new javax.swing.JDialog(this);
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        drawPanel1 = new drawpkg.DrawPanel();
        jPanel3 = new javax.swing.JPanel();
        statusBarText = new javax.swing.JLabel();
        LeftPanel = new javax.swing.JPanel();
        ToolsPanel = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        ColorsPanel = new javax.swing.JPanel();
        jButtonRed = new javax.swing.JButton();
        jButtonGreen = new javax.swing.JButton();
        jButtonBlue = new javax.swing.JButton();
        ButtonColor = new javax.swing.JButton();
        jButtonOrange = new javax.swing.JButton();
        jButtonBlack = new javax.swing.JButton();
        jButtonWhite = new javax.swing.JButton();
        RightPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        ClearButton = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        PropPanel = new javax.swing.JPanel();
        SizePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SliderHeight = new javax.swing.JSlider();
        SliderWidth = new javax.swing.JSlider();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        BorderColorButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        SaveMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        AboutItem = new javax.swing.JMenuItem();

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel8.setText("You really want to close without saving ?!");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 60, 0));

        jButton1.setText("Save");
        jPanel1.add(jButton1);

        jButton2.setText("No");
        jPanel1.add(jButton2);

        jButton3.setText("Cancel");
        jPanel1.add(jButton3);

        javax.swing.GroupLayout SaveDialogLayout = new javax.swing.GroupLayout(SaveDialog.getContentPane());
        SaveDialog.getContentPane().setLayout(SaveDialogLayout);
        SaveDialogLayout.setHorizontalGroup(
            SaveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SaveDialogLayout.createSequentialGroup()
                .addGroup(SaveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SaveDialogLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SaveDialogLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jLabel8)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        SaveDialogLayout.setVerticalGroup(
            SaveDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SaveDialogLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GxPaint");
        setBackground(new java.awt.Color(254, 254, 254));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        drawPanel1.setAutoscrolls(true);
        drawPanel1.setMinimumSize(new java.awt.Dimension(200, 200));
        drawPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        drawPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                drawPanel1MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout drawPanel1Layout = new javax.swing.GroupLayout(drawPanel1);
        drawPanel1.setLayout(drawPanel1Layout);
        drawPanel1Layout.setHorizontalGroup(
            drawPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        drawPanel1Layout.setVerticalGroup(
            drawPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(statusBarText, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusBarText, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        LeftPanel.setAutoscrolls(true);

        ToolsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tools"));
        ToolsPanel.setLayout(new java.awt.GridLayout(5, 1));

        buttonGroup1.add(jToggleButton1);
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawpkg/imgs/pencil.png"))); // NOI18N
        jToggleButton1.setSelected(true);
        jToggleButton1.setToolTipText("Pencil");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        ToolsPanel.add(jToggleButton1);

        buttonGroup1.add(jToggleButton4);
        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawpkg/imgs/move.png"))); // NOI18N
        jToggleButton4.setToolTipText("Move");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        ToolsPanel.add(jToggleButton4);

        buttonGroup1.add(jToggleButton2);
        jToggleButton2.setFont(new java.awt.Font("Ubuntu", 0, 14)); // NOI18N
        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawpkg/imgs/rect.png"))); // NOI18N
        jToggleButton2.setToolTipText("Rectangle");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        ToolsPanel.add(jToggleButton2);

        buttonGroup1.add(jToggleButton3);
        jToggleButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawpkg/imgs/line.png"))); // NOI18N
        jToggleButton3.setToolTipText("Line");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        ToolsPanel.add(jToggleButton3);

        buttonGroup1.add(jToggleButton5);
        jToggleButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/drawpkg/imgs/oval.png"))); // NOI18N
        jToggleButton5.setToolTipText("Ellipse");
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        ToolsPanel.add(jToggleButton5);

        ColorsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Fill Color"));
        ColorsPanel.setLayout(new java.awt.GridBagLayout());

        jButtonRed.setBackground(java.awt.Color.red);
        jButtonRed.setToolTipText("Red");
        jButtonRed.setFocusable(false);
        jButtonRed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRed.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 20, 0, 0);
        ColorsPanel.add(jButtonRed, gridBagConstraints);

        jButtonGreen.setBackground(java.awt.Color.green);
        jButtonGreen.setToolTipText("Green");
        jButtonGreen.setFocusable(false);
        jButtonGreen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGreen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 0);
        ColorsPanel.add(jButtonGreen, gridBagConstraints);

        jButtonBlue.setBackground(java.awt.Color.blue);
        jButtonBlue.setToolTipText("Blue");
        jButtonBlue.setFocusable(false);
        jButtonBlue.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBlue.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(27, 6, 0, 18);
        ColorsPanel.add(jButtonBlue, gridBagConstraints);

        ButtonColor.setToolTipText("Current Color");
        ButtonColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonColorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.ipady = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 20, 14, 18);
        ColorsPanel.add(ButtonColor, gridBagConstraints);

        jButtonOrange.setBackground(java.awt.Color.orange);
        jButtonOrange.setToolTipText("Orange");
        jButtonOrange.setFocusable(false);
        jButtonOrange.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonOrange.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonOrange.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonOrangeMouseClicked(evt);
            }
        });
        jButtonOrange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 18);
        ColorsPanel.add(jButtonOrange, gridBagConstraints);

        jButtonBlack.setBackground(java.awt.Color.black);
        jButtonBlack.setToolTipText("Black");
        jButtonBlack.setFocusable(false);
        jButtonBlack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonBlack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 20, 0, 0);
        ColorsPanel.add(jButtonBlack, gridBagConstraints);

        jButtonWhite.setBackground(java.awt.Color.white);
        jButtonWhite.setToolTipText("White");
        jButtonWhite.setFocusable(false);
        jButtonWhite.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonWhite.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 18);
        ColorsPanel.add(jButtonWhite, gridBagConstraints);

        javax.swing.GroupLayout LeftPanelLayout = new javax.swing.GroupLayout(LeftPanel);
        LeftPanel.setLayout(LeftPanelLayout);
        LeftPanelLayout.setHorizontalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addGroup(LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ColorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(ToolsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        LeftPanelLayout.setVerticalGroup(
            LeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ToolsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ColorsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        RightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null));
        RightPanel.setAutoscrolls(true);
        RightPanel.setMinimumSize(new java.awt.Dimension(250, 600));
        RightPanel.setPreferredSize(new java.awt.Dimension(293, 500));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Shapes Table", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Shape", "Visible"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public void setValueAt(Object value, int row, int col) {
                super.setValueAt(value, row, col);
                if (col == 2) {
                    if ((Boolean) this.getValueAt(row, col) == true) {
                        drawPanel1.shapesList.elementAt(row).setVisible(true);

                    }
                    else if ((Boolean) this.getValueAt(row, col) == false) {
                        drawPanel1.shapesList.elementAt(row).setVisible(false);
                    }
                    repaint();
                }
            }
        });
        jScrollPane1.setViewportView(jTable);

        jToolBar1.setRollover(true);
        jToolBar1.setFont(new java.awt.Font("Liberation Sans", 0, 12)); // NOI18N

        ClearButton.setText("Clear");
        ClearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ClearButtonMouseClicked(evt);
            }
        });
        jToolBar1.add(ClearButton);

        jButtonRemove.setText("remove");
        jButtonRemove.setFocusable(false);
        jButtonRemove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRemove.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonRemoveMouseClicked(evt);
            }
        });
        jToolBar1.add(jButtonRemove);

        PropPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Properties"));

        jLabel1.setText("Width");

        jLabel5.setText("Height");

        SliderHeight.setMaximum(600);
        SliderHeight.setValue(0);
        SliderHeight.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderHeightStateChanged(evt);
            }
        });

        SliderWidth.setMaximum(600);
        SliderWidth.setValue(0);
        SliderWidth.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SliderStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        jLabel7.setText("0");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 10)); // NOI18N
        jLabel6.setText("0");

        javax.swing.GroupLayout SizePanelLayout = new javax.swing.GroupLayout(SizePanel);
        SizePanel.setLayout(SizePanelLayout);
        SizePanelLayout.setHorizontalGroup(
            SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SizePanelLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(47, 47, 47)
                .addGroup(SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SizePanelLayout.createSequentialGroup()
                        .addComponent(SliderWidth, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(SizePanelLayout.createSequentialGroup()
                        .addComponent(SliderHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))))
        );
        SizePanelLayout.setVerticalGroup(
            SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SizePanelLayout.createSequentialGroup()
                .addGroup(SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SizePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel7))
                    .addGroup(SizePanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SliderWidth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(9, 9, 9)
                .addGroup(SizePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SizePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5))
                    .addComponent(SliderHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SizePanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel2.setText("Border");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none", "solid", "dashed" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Border Size");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 0, 20, 1));
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel3.setText("BorderColor");

        BorderColorButton.setBackground(java.awt.Color.blue);
        BorderColorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorderColorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(BorderColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(BorderColorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PropPanelLayout = new javax.swing.GroupLayout(PropPanel);
        PropPanel.setLayout(PropPanelLayout);
        PropPanelLayout.setHorizontalGroup(
            PropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PropPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        PropPanelLayout.setVerticalGroup(
            PropPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PropPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SizePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout RightPanelLayout = new javax.swing.GroupLayout(RightPanel);
        RightPanel.setLayout(RightPanelLayout);
        RightPanelLayout.setHorizontalGroup(
            RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PropPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RightPanelLayout.setVerticalGroup(
            RightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(PropPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(604, 604, 604))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(drawPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addComponent(RightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 532, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FileMenu.setText("File");

        SaveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        SaveMenuItem.setText("Save");
        SaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveMenuItemActionPerformed(evt);
            }
        });
        FileMenu.add(SaveMenuItem);
        FileMenu.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        FileMenu.add(jMenuItem4);

        jMenuBar1.add(FileMenu);

        jMenu1.setText("Help");

        AboutItem.setText("About");
        AboutItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutItemActionPerformed(evt);
            }
        });
        jMenu1.add(AboutItem);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    public synchronized void saveProject() {
        try {
            Project pr2 =new Project(pr.getId());
            pr2.setData((Vector)drawPanel1.shapesList.clone());
            cp.setData(pr2);
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized Vector openProject() throws Exception {
        Vector shapeList = (Vector)cp.getData(pr.getId());
        if(shapeList!=null && shapeList.size()>0) {
            drawPanel1.shapesList = (Vector)shapeList.clone();
            repaint();
        }
        return shapeList;
        //return null;
    }
    
    
    private void SaveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveMenuItemActionPerformed
        saveProject();
    }//GEN-LAST:event_SaveMenuItemActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //SaveDialog.setVisible(true);
        int result = JOptionPane.showConfirmDialog(this,
            "Do you want to Save ?", "Exit Confirmation : ",
            JOptionPane.YES_NO_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.YES_OPTION:
                terminate = true;
                this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
                SaveMenuItem.doClick();
                this.dispose();
                break;
            case JOptionPane.NO_OPTION:
                terminate = true;
                this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
                drawPanel1.shapesList = checkPoint;
                saveProject();
                this.dispose();
                break;
            case JOptionPane.CANCEL_OPTION:
                this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
                break;
            default:
                break;
        }
      
    }//GEN-LAST:event_formWindowClosing

    private void BorderColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorderColorButtonActionPerformed
        dragged = true;
        Color color = JColorChooser.showDialog
        (null, "couleur du fond", Color.WHITE);
        BorderColorButton.setBackground(color);
        if (drawPanel1.indexshapeSelected!=-1)
        {
            
            ((Bordered)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).setBorderColor(color);
            saveProject();
            repaint();
            
        }
        else drawPanel1.shapeBorderColor = color;
        dragged = false;
    }//GEN-LAST:event_BorderColorButtonActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        
        dragged = true;
        int value = (int)jSpinner1.getValue();
        if (drawPanel1.indexshapeSelected!=-1) {
            ((Bordered)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).setBorderSize(value);
            saveProject();
            repaint();
            
        }
        else {
            drawPanel1.shapeBorderSize = value ;
        }
        dragged = false;
        
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        dragged = true;
        if (drawPanel1.indexshapeSelected!=-1) {
            
            ((Bordered)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).setBorderType((String)jComboBox1.getSelectedItem());
            saveProject();
            repaint();
            
        }
        else {
            drawPanel1.shapeBorderType = (String)jComboBox1.getSelectedItem();
        }
        dragged = false;
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void SliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderStateChanged
        //String text = String.valueOf(((JSlider)evt.getSource()).getValue());
        dragged = true;
        if (drawPanel1.indexshapeSelected!=-1) {
            
            int width =((JSlider)evt.getSource()).getValue();
            if (drawPanel1.shapesList.get(drawPanel1.indexshapeSelected) instanceof Rrectangle)
            ((Rectangle)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).width = width ;
            else if (drawPanel1.shapesList.get(drawPanel1.indexshapeSelected) instanceof Ellipse)
            ((Ellipse)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).width = width ;
            jLabel7.setText(String.valueOf(width));
            saveProject();
            repaint();
            
        }
        dragged = false;
    }//GEN-LAST:event_SliderStateChanged

    private void SliderHeightStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SliderHeightStateChanged
        dragged = true;
        if (drawPanel1.indexshapeSelected!=-1) {
            
            int height =((JSlider)evt.getSource()).getValue();
            if (drawPanel1.shapesList.get(drawPanel1.indexshapeSelected) instanceof Rrectangle)
            ((Rectangle)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).height = height ;
            else if (drawPanel1.shapesList.get(drawPanel1.indexshapeSelected) instanceof Ellipse)
            ((Ellipse)drawPanel1.shapesList.get(drawPanel1.indexshapeSelected)).height = height ;
            jLabel6.setText(String.valueOf(height));
            saveProject();
            repaint();
            
        }
        dragged = false;
    }//GEN-LAST:event_SliderHeightStateChanged

    private void jButtonRemoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonRemoveMouseClicked
        int indexrow = jTable.getSelectedRow();
        try {
            jTableModel.removeRow(indexrow);
            drawPanel1.shapesList.removeElementAt(indexrow);
            drawPanel1.indexshapeSelected = -1 ;
            repaint();
            saveProject();
        } catch(Exception e) {
            if(drawPanel1.indexshapeSelected!=-1) {
                drawPanel1.shapesList.removeElementAt(drawPanel1.indexshapeSelected);
                repaint();
                saveProject();
            }
            else 
                JOptionPane.showMessageDialog(rootPane, e, "Error", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoveMouseClicked

    private void ClearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ClearButtonMouseClicked
        drawPanel1.shapesList.clear();
        jTableModel.setRowCount(0);
        saveProject();
        repaint();
    }//GEN-LAST:event_ClearButtonMouseClicked

    private void ColorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorButtonActionPerformed
        Color color = ((JButton)evt.getSource()).getBackground();
        setColor(color);
    }//GEN-LAST:event_ColorButtonActionPerformed

    private void jButtonOrangeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonOrangeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonOrangeMouseClicked

    private void ButtonColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonColorActionPerformed
        Color color = JColorChooser.showDialog
        (null, "couleur du fond", Color.WHITE);
        setColor(color);
    }//GEN-LAST:event_ButtonColorActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        changeMode("ellipse");
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        changeMode("line");
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        changeMode("rect");
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        changeMode("move");
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        changeMode("pencil");
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void drawPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanel1MouseMoved
        statusBarText.setText("X= "+evt.getX()+", Y= "+evt.getY());
    }//GEN-LAST:event_drawPanel1MouseMoved

    private void AboutItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutItemActionPerformed
        JOptionPane.showMessageDialog(rootPane, "GxPaint 0.1\nThis program allows you to draw different shapes\n"
                + " (Ellipse, Circle, Line Segment, Rectangle)\n "
                + "and manipulate them", "About", INFORMATION_MESSAGE);
    }//GEN-LAST:event_AboutItemActionPerformed
    
    
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainFrame().setVisible(true);
            }
        });
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutItem;
    private javax.swing.JButton BorderColorButton;
    private javax.swing.JButton ButtonColor;
    private javax.swing.JButton ClearButton;
    private javax.swing.JPanel ColorsPanel;
    private javax.swing.JMenu FileMenu;
    private javax.swing.JPanel LeftPanel;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel PropPanel;
    private javax.swing.JPanel RightPanel;
    private javax.swing.JDialog SaveDialog;
    private javax.swing.JMenuItem SaveMenuItem;
    private javax.swing.JPanel SizePanel;
    private javax.swing.JSlider SliderHeight;
    private javax.swing.JSlider SliderWidth;
    private javax.swing.JPanel ToolsPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private drawpkg.DrawPanel drawPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBlack;
    private javax.swing.JButton jButtonBlue;
    private javax.swing.JButton jButtonGreen;
    private javax.swing.JButton jButtonOrange;
    private javax.swing.JButton jButtonRed;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonWhite;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel statusBarText;
    // End of variables declaration//GEN-END:variables
}
