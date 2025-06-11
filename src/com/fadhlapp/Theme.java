package com.fadhlapp;

import javax.swing.*;
import java.awt.*;

public class Theme {
    public static final Color BG = Color.BLACK;
    public static final Color FG = Color.WHITE;
    public static final Color RED = new Color(200,30,30);
    public static final Color YELLOW = new Color(220,220,0);
    public static final Color BLUE = new Color(40,40,200);

    public static void apply(Container c) {
        apply(c, BG, FG);
    }

    public static void apply(Container c, Color bg, Color fg) {
        c.setBackground(bg);
        if(c instanceof JComponent) {
            ((JComponent) c).setForeground(fg);
        }
        for(Component comp : c.getComponents()) {
            if(comp instanceof Container) {
                apply((Container) comp, bg, fg);
            } else {
                comp.setBackground(bg);
                comp.setForeground(fg);
            }
        }
    }
}
