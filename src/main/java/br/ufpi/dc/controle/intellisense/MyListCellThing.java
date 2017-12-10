/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.controle.intellisense;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author helcio.soares
 */
public class MyListCellThing extends JLabel implements ListCellRenderer {

    ImageIcon icon;

    public MyListCellThing(ImageIcon icon) {
        setOpaque(true);
        this.icon = icon;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        // Assumes the stuff in the list has a pretty toString
        setText(value.toString());
        setIcon(icon);
        Color cor = new Color(248, 248, 248);
        // based on the index you set the color.  This produces the every other effect.
        if (index % 2 == 0) {
            setBackground(cor);
        } else {
            setBackground(Color.WHITE);
        }

        if (isSelected) {

            setBackground(Color.LIGHT_GRAY);

        }
        return this;
    }
}
