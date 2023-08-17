package Modelo;

import javax.swing.*;
import java.awt.event.KeyEvent;



public abstract class Evento {
    public abstract void manejarEvento(KeyEvent e, JTextField textField);}

class EventoTexto extends Evento {
    @Override
    public void manejarEvento(KeyEvent e, JTextField textField) {
        char caracter = e.getKeyChar();
        if ((caracter < 'a' || caracter > 'z') && (caracter < 'A' || caracter > 'Z')
                && (caracter != (char) KeyEvent.VK_BACK_SPACE) && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();}}
}

class EventoNumerico extends Evento {
    @Override
    public void manejarEvento(KeyEvent e, JTextField textField) {
        char caracter = e.getKeyChar();
        if ((caracter < '0' || caracter > '9') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();}}
}

class EventoDecimal extends Evento {
    @Override
    public void manejarEvento(KeyEvent e, JTextField textField) {
        char caracter = e.getKeyChar();
        if ((caracter < '0' || caracter > '9') && textField.getText().contains(".") && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();
        } else if ((caracter < '0' || caracter > '9') && (caracter != '.') && (caracter != (char) KeyEvent.VK_BACK_SPACE)) {
            e.consume();}}
}

