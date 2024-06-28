package com.x_o_client;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Alert;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Form extends javax.swing.JFrame {
    private final Client client;
    private final List<JPanel> fields;
    
    private final ImageIcon cross = new ImageIcon("pics/cross.png");
    private final ImageIcon circle = new ImageIcon("pics/circle.png");

    public Form() {
        initComponents();
        ImageIcon icon = new ImageIcon("pics/icon.png");
        this.setIconImage(icon.getImage());
        reconnectLabel.setVisible(false);
        reconnectButton.setVisible(false);
        
        fields = new LinkedList<>();
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);
        fields.add(field9);
        
        client = new Client(this);
        client.run();
    }
    
    public void SetField(int num, String figure) {
        SwingUtilities.invokeLater(() -> {
            JLabel label;
            if (figure.equals("x")) {
                label = new JLabel(cross);
                label.setBounds(0,0,cross.getIconWidth(),cross.getIconHeight());
            } else {
                label = new JLabel(circle);
                label.setBounds(0,0,circle.getIconWidth(),circle.getIconHeight());
            }
            fields.get(num).add(label);
            fields.get(num).updateUI();
        });
    }
    
    public void SetVictory(String pos) { // "h2" "v3" "c1" "c2"
        ImageIcon icon;
        Integer x = 0, y = 0;
        if (pos.startsWith("h")) {
            icon = new ImageIcon("pics/line_h.png");
            y = 114 * (Integer.parseInt(pos.substring(1)) - 1);
        } else if (pos.startsWith("v")) {
            icon = new ImageIcon("pics/line_v.png");
            x = 114 * (Integer.parseInt(pos.substring(1)) - 1);
        } else if (pos.equals("c1")) {
            icon = new ImageIcon("pics/line_c1.png");
            x = 9;
        } else {
            icon = new ImageIcon("pics/line_c2.png");
            y = 9;
        }
        JLabel label = new JLabel(icon);
        label = new JLabel(icon);
        
        label.setBounds(x, y,icon.getIconWidth(),icon.getIconHeight());
        
        LayeredPanel.add(label, 1, 0);
        LayeredPanel.updateUI();
    }
    
    public void SetConnection(boolean value) {
        SwingUtilities.invokeLater(() -> {
            reconnectLabel.setVisible(!value);
            reconnectButton.setVisible(!value);
        });
    }
    
    public void MessageBox(String title, String message) {
        SwingUtilities.invokeLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setTitle(title);
            alert.setHeaderText(message);
            alert.showAndWait();
        });
    }
    
    public static void main(String[] args) {
        Form form = new Form();
        form.setLocationRelativeTo(null);
        form.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        reconnectLabel = new javax.swing.JLabel();
        reconnectButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        LayeredPanel = new javax.swing.JLayeredPane();
        fieldPanel = new javax.swing.JPanel();
        field2 = new javax.swing.JPanel();
        field3 = new javax.swing.JPanel();
        field1 = new javax.swing.JPanel();
        field6 = new javax.swing.JPanel();
        field4 = new javax.swing.JPanel();
        field5 = new javax.swing.JPanel();
        field7 = new javax.swing.JPanel();
        field9 = new javax.swing.JPanel();
        field8 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Крестики - нолики");
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(null);
        setMinimumSize(null);
        setResizable(false);
        setSize(new java.awt.Dimension(447, 450));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(447, 446));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        reconnectLabel.setForeground(new java.awt.Color(204, 102, 0));
        reconnectLabel.setText("Соединение с сервером не установлено");

        reconnectButton.setBackground(new java.awt.Color(204, 102, 0));
        reconnectButton.setForeground(new java.awt.Color(255, 255, 255));
        reconnectButton.setText("Подключиться");
        reconnectButton.setMargin(new java.awt.Insets(2, 4, 3, 4));
        reconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reconnectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(reconnectLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reconnectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reconnectLabel)
                    .addComponent(reconnectButton)))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ожидаем второго игрока...");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LayeredPanel.setPreferredSize(new java.awt.Dimension(358, 358));

        fieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        fieldPanel.setPreferredSize(new java.awt.Dimension(346, 346));

        field2.setPreferredSize(new java.awt.Dimension(100, 100));
        field2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field2Layout = new javax.swing.GroupLayout(field2);
        field2.setLayout(field2Layout);
        field2Layout.setHorizontalGroup(
            field2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field2Layout.setVerticalGroup(
            field2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field3Layout = new javax.swing.GroupLayout(field3);
        field3.setLayout(field3Layout);
        field3Layout.setHorizontalGroup(
            field3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field3Layout.setVerticalGroup(
            field3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        field1.setPreferredSize(new java.awt.Dimension(100, 100));
        field1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field1Layout = new javax.swing.GroupLayout(field1);
        field1.setLayout(field1Layout);
        field1Layout.setHorizontalGroup(
            field1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field1Layout.setVerticalGroup(
            field1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        field6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field6MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field6Layout = new javax.swing.GroupLayout(field6);
        field6.setLayout(field6Layout);
        field6Layout.setHorizontalGroup(
            field6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field6Layout.setVerticalGroup(
            field6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field4.setPreferredSize(new java.awt.Dimension(100, 100));
        field4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field4MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field4Layout = new javax.swing.GroupLayout(field4);
        field4.setLayout(field4Layout);
        field4Layout.setHorizontalGroup(
            field4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field4Layout.setVerticalGroup(
            field4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field5.setPreferredSize(new java.awt.Dimension(100, 100));
        field5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field5Layout = new javax.swing.GroupLayout(field5);
        field5.setLayout(field5Layout);
        field5Layout.setHorizontalGroup(
            field5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field5Layout.setVerticalGroup(
            field5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field7.setPreferredSize(new java.awt.Dimension(100, 100));
        field7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field7MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field7Layout = new javax.swing.GroupLayout(field7);
        field7.setLayout(field7Layout);
        field7Layout.setHorizontalGroup(
            field7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field7Layout.setVerticalGroup(
            field7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field9MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field9Layout = new javax.swing.GroupLayout(field9);
        field9.setLayout(field9Layout);
        field9Layout.setHorizontalGroup(
            field9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field9Layout.setVerticalGroup(
            field9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field8.setPreferredSize(new java.awt.Dimension(100, 100));
        field8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field8MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field8Layout = new javax.swing.GroupLayout(field8);
        field8.setLayout(field8Layout);
        field8Layout.setHorizontalGroup(
            field8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field8Layout.setVerticalGroup(
            field8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator4.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator6.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator7.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator8.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout fieldPanelLayout = new javax.swing.GroupLayout(fieldPanel);
        fieldPanel.setLayout(fieldPanelLayout);
        fieldPanelLayout.setHorizontalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(fieldPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fieldPanelLayout.createSequentialGroup()
                                .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(fieldPanelLayout.createSequentialGroup()
                                .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2)
                    .addGroup(fieldPanelLayout.createSequentialGroup()
                        .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        fieldPanelLayout.setVerticalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(field2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(field3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(field1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        LayeredPanel.setLayer(fieldPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout LayeredPanelLayout = new javax.swing.GroupLayout(LayeredPanel);
        LayeredPanel.setLayout(LayeredPanelLayout);
        LayeredPanelLayout.setHorizontalGroup(
            LayeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LayeredPanelLayout.setVerticalGroup(
            LayeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LayeredPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(fieldPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LayeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LayeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        client.CloseConnection();
    }//GEN-LAST:event_formWindowClosing

    private void reconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reconnectButtonActionPerformed
        client.run();
    }//GEN-LAST:event_reconnectButtonActionPerformed

    private void field1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1MousePressed
        SetField(0,"x");
        SetField(1,"x");
        SetField(2,"x");
        SetField(3,"x");
        SetField(4,"x");
        SetField(5,"x");
        SetField(6,"x");
        SetField(7,"x");
        SetField(8,"x");
    }//GEN-LAST:event_field1MousePressed

    private void field2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2MousePressed
        SetVictory("v2");
    }//GEN-LAST:event_field2MousePressed

    private void field3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3MousePressed
        SetVictory("v3");
    }//GEN-LAST:event_field3MousePressed

    private void field4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field4MousePressed
        SetVictory("h2");
    }//GEN-LAST:event_field4MousePressed

    private void field5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field5MousePressed
        SetVictory("c2");
    }//GEN-LAST:event_field5MousePressed

    private void field6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field6MousePressed
        SetVictory("c1");
    }//GEN-LAST:event_field6MousePressed

    private void field7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_field7MousePressed

    private void field8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field8MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_field8MousePressed

    private void field9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field9MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_field9MousePressed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPanel;
    private javax.swing.JPanel field1;
    private javax.swing.JPanel field2;
    private javax.swing.JPanel field3;
    private javax.swing.JPanel field4;
    private javax.swing.JPanel field5;
    private javax.swing.JPanel field6;
    private javax.swing.JPanel field7;
    private javax.swing.JPanel field8;
    private javax.swing.JPanel field9;
    private javax.swing.JPanel fieldPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JButton reconnectButton;
    private javax.swing.JLabel reconnectLabel;
    // End of variables declaration//GEN-END:variables
}
