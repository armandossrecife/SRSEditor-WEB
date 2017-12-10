/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import br.ufpi.dc.controle.analiseTexto.AnalisePeriodo;
import br.ufpi.dc.entidades.entity.Conceito;
import br.ufpi.dc.controle.grammar.tools.UnderlineListener;
import br.ufpi.dc.controle.grammar.tools.VerboseListener;
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
public class Intellisense {

    KeyListener keyListener;
    JPopupMenu jPopupMenu1;
    ImageIcon icone1, icone2;
    MouseListener mouseListener;
    private JList list = new JList();
    private JTextComponent jTextComponent;
    private int caretPosition;
    private String frasePrimeiraParte;
    private String fraseSegundaParte;

    public Intellisense(JTextComponent jTextComponent) {
        this.jTextComponent = jTextComponent;
        contruirListeners();
    }

    public void iniciaIntellisence(AbstractParser parse) {
        try {
            list = construirLista(parse);
            if (list != null && (validarListaErro() || validarEtiquetas() || (parse instanceof ParserDescricaoCasoDeUso))) {
                JScrollPane pnLista = new JScrollPane(list);
                pnLista.setBorder(javax.swing.BorderFactory.createEtchedBorder());
                pnLista.setViewportView(list);
                jPopupMenu1 = new JPopupMenu();
                jPopupMenu1.add(pnLista);
                int dotPosition = jTextComponent.getCaretPosition();
                Rectangle popupLocation = jTextComponent.modelToView(dotPosition);
                jPopupMenu1.show(jTextComponent, popupLocation.x - 5, popupLocation.y + 17);
                list.setSelectedIndex(0);

                list.requestFocus();
            }
        } catch (BadLocationException badLocationException) {
            System.err.println("Oops");
        }

    }

    private boolean validarEtiquetas() {
        if (AnalisePeriodo.etiquetasSentenca.equals("SISTEMA")
                && AnalisePeriodo.etiquetasSentenca.equals("SISTEMADEVE")) {
            return false;
        }
        return true;
    }

    public JList construirLista(AbstractParser parse) {
        this.caretPosition = jTextComponent.getCaretPosition();
        frasePrimeiraParte = jTextComponent.getText().substring(0, caretPosition);
        fraseSegundaParte = jTextComponent.getText().substring(caretPosition, jTextComponent.getText().length());
        DefaultListModel defaultListModel = new DefaultListModel();
        defaultListModel = parse.avaliaFrase(frasePrimeiraParte);
        JList listTemp = null;
        if (defaultListModel.getSize() != 0) {
            listTemp = new JList(defaultListModel);
            listTemp.setCellRenderer(new MyListCellThing(icone1));
            listTemp.addKeyListener(keyListener);
            listTemp.addMouseListener(mouseListener);
        }
        return listTemp;
    }

    public void contruirListeners() {
        Color cor = new Color(248, 248, 248);

        ActionListener anActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jTextComponent.setText(jTextComponent.getText() + e.getActionCommand());
            }
        };

        keyListener = (new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    jTextComponent.setText(setText(list.getSelectedValue().toString()));
                    jTextComponent.setCaretPosition(caretPosition);
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
                        jTextComponent.setText(setText(list.getSelectedValue().toString()));
                        jTextComponent.setCaretPosition(caretPosition);
                        jPopupMenu1.setVisible(false);
                    }
                }
            }
        };
    }

    private String setText(String resultado) {
        String primeiroEspaco = "";
        String segundoEspaco = "";

        try {
            primeiroEspaco = (frasePrimeiraParte.charAt(frasePrimeiraParte.length() - 1) == ' ') ? "" : " ";
            segundoEspaco = (fraseSegundaParte.charAt(0) == ' ') ? "" : " ";
        } catch (Exception e) {
        }

        String temp = frasePrimeiraParte + primeiroEspaco + resultado + segundoEspaco + fraseSegundaParte;
        caretPosition = frasePrimeiraParte.length() + (primeiroEspaco + resultado + segundoEspaco).length();

        return temp;

    }

    private boolean validarListaErro() {
        for (String erro : UnderlineListener.erroList) {
            if (erro.contains("extraneous input")) {
                return false;
            }
            if (erro.contains("no viable alternative at input")) {
                return false;
            }
        }
        return true;
    }

}
