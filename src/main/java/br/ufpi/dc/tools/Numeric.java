/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpi.dc.tools;

import java.text.DecimalFormat;

/**
 *
 * @author helcio.soares
 */
public class Numeric {

    public static Float formatFloat(Float numero) {
        DecimalFormat df = new DecimalFormat("0.000");
        return new Float(df.format(numero).replace(",", "."));
    }

}
