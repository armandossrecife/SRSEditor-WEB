/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import br.ufpi.dc.entidades.entity.Conceito;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

/**
 *
 * @author helcio.soares
 */
public class Intellisense1 {
    KeyListener keyListener;
    JPopupMenu jPopupMenu1;
    ImageIcon icone1, icone2;
    MouseListener mouseListener;
    private JList list = new JList();
    private JTextComponent jTextComponent;
    private List<String> listIntellisense;

    public Intellisense1(JTextComponent jTextComponent, List<String> listIntellisense) {
        this.jTextComponent = jTextComponent;
        this.listIntellisense = listIntellisense;
        iniciaIntellisence();
        contruirListeners();
    }

    public void iniciaIntellisence() {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    construirLista();
                    jPopupMenu1 = new JPopupMenu();
//                    JScrollPane  jScrollPane = new JScrollPane();
//                    jScrollPane.add(list);
//                    jScrollPane.setOpaque(true);
//                    jScrollPane.setPreferredSize(new Dimension(100, 500));
                    jPopupMenu1.add(list);
                    int dotPosition = jTextComponent.getCaretPosition();
                    Rectangle popupLocation = jTextComponent.modelToView(dotPosition);
                    jPopupMenu1.show(jTextComponent, popupLocation.x - 5, popupLocation.y + 17);
                    list.setSelectedIndex(0);
                    list.requestFocus();
                } catch (BadLocationException badLocationException) {
                    System.err.println("Oops");
                }
            }
        };
        KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.CTRL_MASK, false);
        jTextComponent.registerKeyboardAction(actionListener, keystroke, JComponent.WHEN_FOCUSED);
    }

    public void construirLista() {
        DefaultListModel listModel = new DefaultListModel();
        for (String conceito : listIntellisense) {
            listModel.addElement(conceito);
            
        }
        list = new JList(listModel);
        list.setCellRenderer(new MyListCellThing(icone1));
        list.addKeyListener(keyListener);
        list.addMouseListener(mouseListener);

    }

    public void contruirListeners() {
        Color cor = new Color(248, 248, 248);

        ActionListener anActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextComponent.setText(jTextComponent.getText() + e.getActionCommand()); //To change body of generated methods, choose Tools | Templates.
            }
        };

        keyListener = (new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jTextComponent.setText(jTextComponent.getText() + list.getSelectedValue().toString());
                    jPopupMenu1.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });

        mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        jTextComponent.setText(jTextComponent.getText() + list.getSelectedValue().toString());
                        jPopupMenu1.setVisible(false);
                    }
                }
            }
        };
    }
  
}
