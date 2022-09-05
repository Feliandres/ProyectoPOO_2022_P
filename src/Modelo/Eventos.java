package Modelo;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Eventos {

    public void textKeyPress (KeyEvent e) {
        char caracter = e.getKeyChar();
        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z')
            && (caracter != (char) KeyEvent.VK_BACK_SPACE) && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {

            e.consume();
        }
    }

    public void numberKeyPress (KeyEvent e) {
        char caracter = e.getKeyChar();
        if ((caracter < '0' || caracter > '9') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();
        }
    }

    public void numberDecimalKeyPress(KeyEvent e, JTextField textField) {
        char caracter = e.getKeyChar();
        if ((caracter < '0' || caracter > '9') && textField.getText().contains(".") && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();
        } else if ((caracter < '0' || caracter > '9') && (caracter != '.') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();
        }
    }

}
