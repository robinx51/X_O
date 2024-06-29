package com.x_o_client;

import java.awt.Color;
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
    public int[] settedFields;
    
    private final ImageIcon cross = new ImageIcon("pics/cross.png");
    private final ImageIcon circle = new ImageIcon("pics/circle.png");
    private final ImageIcon crossT = new ImageIcon("pics/crossTransparent.png");
    private final ImageIcon circleT = new ImageIcon("pics/circleTransparent.png");
    
    private boolean isYourStep;
    public String figure;
    public String figureOpp;
    
    public Form() {
        isYourStep = false;
        figure = "";
        
        initComponents();
        ImageIcon icon = new ImageIcon("pics/icon.png");
        this.setIconImage(icon.getImage());
        reconnectLabel.setVisible(false);
        reconnectButton.setVisible(false);
        
        fields = new LinkedList<>();
        fields.add(field0);
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);
            
        client = new Client(this);
        client.run();
    }
    
    public void InitFields() {
        settedFields = new int[] {0,0,0,0,0,0,0,0,0};
    }
    
    public void SetIsYourStep(boolean value) { 
        isYourStep = value;
        if (value) 
            SetLabel("Ваш ход", "green");
        else 
            SetLabel("Ход противника", "black");
    }
    public void SetFigure(String value) { 
        figure = value; 
        if (figure.equals("x")) figureOpp = "o";
        else figureOpp = "x";
    }
    
    public void SetLabel(String value, String strColor) {
        SwingUtilities.invokeLater(() -> {
            Color color;
            switch (strColor) {
                case "red": {
                    color = Color.RED;
                    break;                         
                } case "green": {
                    color = Color.GREEN;
                    break; 
                } case "white": {
                    color = Color.WHITE;
                    break; 
                } default: {
                    color = Color.BLACK;
                    break; 
                }
            }
            upperLabel.setText(value);
            upperLabel.setForeground(color);
            upperLabel.updateUI();
        });
    }
    
    public void SetField(int num, String figure) {
        SwingUtilities.invokeLater(() -> {
            JLabel label;
            switch (figure) {
                case "x":
                    label = new JLabel(cross);
                    label.setBounds(0,0,cross.getIconWidth(),cross.getIconHeight());
                    settedFields[num] = 1;
                    break;
                case "o":
                    label = new JLabel(circle); 
                    label.setBounds(0,0,circle.getIconWidth(),circle.getIconHeight());
                    settedFields[num] = 2;
                    break;
                case "xT":
                    label = new JLabel(crossT);
                    label.setBounds(0,0,crossT.getIconWidth(),crossT.getIconHeight());
                    break;
                default:
                    label = new JLabel(circleT);
                    label.setBounds(0,0,circleT.getIconWidth(),circleT.getIconHeight());
                    break;
            }
            fields.get(num).add(label);
            fields.get(num).updateUI();
        });
    }
    public void SetVictory(String pos, int win) { // "h2" "v3" "c1" "c2"
        if (win != 2) {    
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

            label.setBounds(x, y,icon.getIconWidth(),icon.getIconHeight());

            LayeredPanel.add(label, 1, 0);
            LayeredPanel.updateUI();
        }
        switch (win) {
            case 1:
                SetLabel("Вы победили!", "green");
                break;
            case 0:
                SetLabel("Вы проиграли!", "red");
                break;
            default:
                SetLabel("Ничья!", "green");
                break;
        }
    }
    
    private void FieldClick(int num) {
        if (isYourStep) {
            client.sendMessage("step", num);
        }
    }
    private void SetTransparent(int num) {
        if (isYourStep && settedFields[num] == 0) {
            if (figure.equals("x"))
                SetField(num, "xT");
            else 
                SetField(num, "oT");
        }
    }
    private void RemoveTransparent(int num) {
        if (isYourStep && (fields.get(num).getComponentCount() != 0) && settedFields[num] == 0) {
            if ("x".equals(figure))
                fields.get(num).remove(0);
            else 
                fields.get(num).remove(0);
            fields.get(num).updateUI();
        }
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
        upperLabel = new javax.swing.JLabel();
        LayeredPanel = new javax.swing.JLayeredPane();
        fieldPanel = new javax.swing.JPanel();
        field0 = new javax.swing.JPanel();
        field1 = new javax.swing.JPanel();
        field2 = new javax.swing.JPanel();
        field3 = new javax.swing.JPanel();
        field4 = new javax.swing.JPanel();
        field5 = new javax.swing.JPanel();
        field6 = new javax.swing.JPanel();
        field7 = new javax.swing.JPanel();
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
        setSize(new java.awt.Dimension(458, 454));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setPreferredSize(new java.awt.Dimension(458, 454));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        reconnectLabel.setForeground(new java.awt.Color(255, 255, 255));
        reconnectLabel.setText("Соединение с сервером не установлено");

        reconnectButton.setBackground(new java.awt.Color(102, 102, 102));
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

        upperLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        upperLabel.setForeground(new java.awt.Color(0, 0, 0));
        upperLabel.setText("Ожидаем второго игрока...");
        upperLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        LayeredPanel.setPreferredSize(new java.awt.Dimension(358, 358));

        fieldPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 2));
        fieldPanel.setPreferredSize(new java.awt.Dimension(346, 346));

        field0.setPreferredSize(new java.awt.Dimension(100, 100));
        field0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field0MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field0MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                field0MousePressed(evt);
            }
        });

        javax.swing.GroupLayout field0Layout = new javax.swing.GroupLayout(field0);
        field0.setLayout(field0Layout);
        field0Layout.setHorizontalGroup(
            field0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        field0Layout.setVerticalGroup(
            field0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        field1.setPreferredSize(new java.awt.Dimension(100, 100));
        field1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field1MouseExited(evt);
            }
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
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field2MouseExited(evt);
            }
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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        field3.setPreferredSize(new java.awt.Dimension(100, 100));
        field3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field3MouseExited(evt);
            }
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
            .addGap(0, 100, Short.MAX_VALUE)
        );

        field4.setPreferredSize(new java.awt.Dimension(100, 100));
        field4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field4MouseExited(evt);
            }
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

        field5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field5MouseExited(evt);
            }
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

        field6.setPreferredSize(new java.awt.Dimension(100, 100));
        field6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field6MouseExited(evt);
            }
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

        field7.setPreferredSize(new java.awt.Dimension(100, 100));
        field7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field7MouseExited(evt);
            }
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

        field8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                field8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                field8MouseExited(evt);
            }
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
                                .addComponent(field0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(fieldPanelLayout.createSequentialGroup()
                                .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator2)
                    .addGroup(fieldPanelLayout.createSequentialGroup()
                        .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        fieldPanelLayout.setVerticalGroup(
            fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fieldPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(field1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(field2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(field0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(field3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(field4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(field5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fieldPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(upperLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(upperLabel)
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        client.CloseConnection();
    }//GEN-LAST:event_formWindowClosing

    private void field0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field0MousePressed
        FieldClick(0);
    }//GEN-LAST:event_field0MousePressed

    private void field1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1MousePressed
        FieldClick(1);
    }//GEN-LAST:event_field1MousePressed

    private void field2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2MousePressed
        FieldClick(2);
    }//GEN-LAST:event_field2MousePressed

    private void field3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3MousePressed
        FieldClick(3);
    }//GEN-LAST:event_field3MousePressed

    private void field4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field4MousePressed
        FieldClick(4);
    }//GEN-LAST:event_field4MousePressed

    private void field5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field5MousePressed
        FieldClick(5);
    }//GEN-LAST:event_field5MousePressed

    private void field6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field6MousePressed
        FieldClick(6);
    }//GEN-LAST:event_field6MousePressed

    private void field7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field7MousePressed
        FieldClick(7);
    }//GEN-LAST:event_field7MousePressed

    private void field8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field8MousePressed
        FieldClick(8);
    }//GEN-LAST:event_field8MousePressed

    private void field0MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field0MouseEntered
        SetTransparent(0);
    }//GEN-LAST:event_field0MouseEntered

    private void field0MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field0MouseExited
        RemoveTransparent(0);
    }//GEN-LAST:event_field0MouseExited

    private void field1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1MouseEntered
        SetTransparent(1);
    }//GEN-LAST:event_field1MouseEntered

    private void field1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field1MouseExited
        RemoveTransparent(1);
    }//GEN-LAST:event_field1MouseExited

    private void field2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2MouseEntered
        SetTransparent(2);
    }//GEN-LAST:event_field2MouseEntered

    private void field2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field2MouseExited
        RemoveTransparent(2);
    }//GEN-LAST:event_field2MouseExited

    private void field3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3MouseEntered
        SetTransparent(3);
    }//GEN-LAST:event_field3MouseEntered

    private void field3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field3MouseExited
        RemoveTransparent(3);
    }//GEN-LAST:event_field3MouseExited

    private void field4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field4MouseEntered
        SetTransparent(4);
    }//GEN-LAST:event_field4MouseEntered

    private void field4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field4MouseExited
        RemoveTransparent(4);
    }//GEN-LAST:event_field4MouseExited

    private void field5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field5MouseEntered
        SetTransparent(5);
    }//GEN-LAST:event_field5MouseEntered

    private void field5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field5MouseExited
        RemoveTransparent(5);
    }//GEN-LAST:event_field5MouseExited

    private void field6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field6MouseEntered
        SetTransparent(6);
    }//GEN-LAST:event_field6MouseEntered

    private void field6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field6MouseExited
        RemoveTransparent(6);
    }//GEN-LAST:event_field6MouseExited

    private void field7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field7MouseEntered
        SetTransparent(7);
    }//GEN-LAST:event_field7MouseEntered

    private void field7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field7MouseExited
        RemoveTransparent(7);
    }//GEN-LAST:event_field7MouseExited

    private void field8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field8MouseEntered
        SetTransparent(8);
    }//GEN-LAST:event_field8MouseEntered

    private void field8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_field8MouseExited
        RemoveTransparent(8);
    }//GEN-LAST:event_field8MouseExited

    private void reconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reconnectButtonActionPerformed
        Form form = new Form();
        form.setLocationRelativeTo(null);
        
        setVisible(false);
        dispose();
        
        form.setVisible(true);
    }//GEN-LAST:event_reconnectButtonActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane LayeredPanel;
    private javax.swing.JPanel field0;
    private javax.swing.JPanel field1;
    private javax.swing.JPanel field2;
    private javax.swing.JPanel field3;
    private javax.swing.JPanel field4;
    private javax.swing.JPanel field5;
    private javax.swing.JPanel field6;
    private javax.swing.JPanel field7;
    private javax.swing.JPanel field8;
    private javax.swing.JPanel fieldPanel;
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
    private javax.swing.JLabel upperLabel;
    // End of variables declaration//GEN-END:variables
}
