
package es.izquierdofp.retrovault.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ConsolaColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable tabla, Object valor, boolean seleccionado,
            boolean tieneFoco, int fila, int columna) {

        JLabel etiqueta = (JLabel) super.getTableCellRendererComponent(
                tabla, valor, seleccionado, tieneFoco, fila, columna);

        if (!seleccionado) {

            Object valorConsola = tabla.getModel().getValueAt(fila, 2);

            String consola = valorConsola != null
                    ? valorConsola.toString() : "";

            Color colorFondo = switch (consola) {
                case "NES" -> new Color(70, 20, 20);
                case "SNES" -> new Color(45, 20, 65);
                case "Mega Drive" -> new Color(20, 30, 75);
                case "Game Boy" -> new Color(20, 55, 30);
                case "PlayStation" -> new Color(15, 40, 70);
                case "Nintendo 64" -> new Color(55, 20, 55);
                case "Game Gear" -> new Color(65, 35, 10);
                case "Arcade" -> new Color(60, 50, 10);
                default -> new Color(35, 35, 35);
            };

            etiqueta.setBackground(colorFondo);
            etiqueta.setForeground(Color.WHITE);
        }

        return etiqueta;
    }
}
