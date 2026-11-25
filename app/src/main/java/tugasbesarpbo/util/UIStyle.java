
package tugasbesarpbo.util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UIStyle {

    public static final Color NAVY = new Color(23, 37, 84);
    public static final Color BLUE = new Color(37, 99, 235);
    public static final Color LIGHT_BLUE = new Color(219, 234, 254);
    public static final Color BACKGROUND = new Color(241, 245, 249);
    public static final Color WHITE = Color.WHITE;
    public static final Color TEXT = new Color(30, 41, 59);
    public static final Color GRAY = new Color(100, 116, 139);
    public static final Color BORDER = new Color(226, 232, 240);
    public static final Color GREEN = new Color(22, 163, 74);
    public static final Color ORANGE = new Color(217, 119, 6);
    public static final Color RED = new Color(220, 38, 38);

    public static Font getTitleFont() {
        return new Font("Segoe UI", Font.BOLD, 22);
    }

    public static Font getHeadingFont() {
        return new Font("Segoe UI", Font.BOLD, 16);
    }

    public static Font getNormalFont() {
        return new Font("Segoe UI", Font.PLAIN, 14);
    }

    public static Font getButtonFont() {
        return new Font("Segoe UI", Font.BOLD, 13);
    }

    public static void stylePrimaryButton(JButton button) {
        styleButton(button, BLUE);
    }

    public static void styleSuccessButton(JButton button) {
        styleButton(button, GREEN);
    }

    public static void styleDangerButton(JButton button) {
        styleButton(button, RED);
    }

    public static void styleWarningButton(JButton button) {
        styleButton(button, ORANGE);
    }

    public static void styleSecondaryButton(JButton button) {
        button.setBackground(new Color(226, 232, 240));
        button.setForeground(TEXT);
        button.setFont(getButtonFont());
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new java.awt.Cursor(
                java.awt.Cursor.HAND_CURSOR));
    }

    private static void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(WHITE);
        button.setFont(getButtonFont());
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new java.awt.Cursor(
                java.awt.Cursor.HAND_CURSOR));
    }

    public static void styleTextField(JTextField field) {
        field.setFont(getNormalFont());
        field.setForeground(TEXT);
        field.setBackground(WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(BORDER, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    public static void styleLabel(JLabel label) {
        label.setFont(getNormalFont());
        label.setForeground(TEXT);
    }
}
