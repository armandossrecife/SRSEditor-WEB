/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

/**
 *
 * @author helcio.soares
 */
public class IntellisenseListener implements ActionListener {

    private JTextComponent jTextComponent;
    private List<String> listIntellisense;
    private Intellisense intellisense;
    private AbstractParser parse;

    public IntellisenseListener(JTextComponent jTextComponent, AbstractParser parse) {
        this.jTextComponent = jTextComponent;
        this.parse          = parse;

        KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, KeyEvent.CTRL_MASK, false);
        jTextComponent.registerKeyboardAction(this, keystroke, JComponent.WHEN_FOCUSED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        intellisense = new Intellisense(jTextComponent);
        intellisense.iniciaIntellisence(parse);
    }

}
