package Modelo;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Eventos {

    public void textKeyPress(KeyEvent e) {
        char caracter = e.getKeyChar();
        char minChar = 'a';
        char maxChar = 'z';
        char backSpace = (char) KeyEvent.VK_BACK_SPACE;

        if ((caracter < minChar || caracter > maxChar) && (caracter < Character.toUpperCase(minChar) || caracter > Character.toUpperCase(maxChar))
                && (caracter != backSpace)) {

            e.consume();
        }
    }

    public void numberKeyPress(KeyEvent e) {
        char caracter = e.getKeyChar();
        char minDigit = '0';
        char maxDigit = '9';
        char backSpace = (char) KeyEvent.VK_BACK_SPACE;

        if ((caracter < minDigit || caracter > maxDigit) && (caracter != backSpace)) {
            e.consume();
        }
    }

    public void numberDecimalKeyPress(KeyEvent e, JTextField textField) {
        char caracter = e.getKeyChar();
        char minDigit = '0';
        char maxDigit = '9';
        char dot = '.';
        char backSpace = (char) KeyEvent.VK_BACK_SPACE;

        if ((caracter < minDigit || caracter > maxDigit) && textField.getText().contains(".") && (caracter != backSpace)) {
            e.consume();
        } else if ((caracter < minDigit || caracter > maxDigit) && (caracter != dot) && (caracter != backSpace)) {
            e.consume();
        }
    }
}
