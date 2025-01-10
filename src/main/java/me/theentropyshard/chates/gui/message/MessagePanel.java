/*
 * Chates - https://github.com/TheEntropyShard/Chates
 * Copyright (C) 2024-2025 TheEntropyShard
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package me.theentropyshard.chates.gui.message;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MessagePanel extends JPanel {
    public MessagePanel(String text, Color background) {
        super(new BorderLayout());

        this.setBorder(new CompoundBorder(null, new EmptyBorder(8, 8, 8, 8)));

        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font(FlatRobotoFont.FAMILY_LIGHT, Font.PLAIN, 13));
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(background);
        textPane.setEditable(false);
        textPane.setText(text);

        this.add(textPane, BorderLayout.CENTER);

        FlatSVGIcon flatSVGIcon = new FlatSVGIcon(MessagePanel.class.getResource("/checkmark_read.svg"))
            .setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.CYAN.darker()));

        JLabel timeLabel = new JLabel("12:34");
        timeLabel.setVerticalAlignment(JLabel.BOTTOM);
        timeLabel.setFont(new Font(FlatRobotoFont.FAMILY_LIGHT, Font.PLAIN, 12));
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        timeLabel.setIcon(flatSVGIcon);

        this.add(timeLabel, BorderLayout.EAST);

        this.setBackground(background);
        this.setOpaque(false);
        this.setMinimumSize(new Dimension(100, 30));
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D.Double rectangle = new RoundRectangle2D.Double(
            0, 0, this.getWidth() - 1, this.getHeight() - 1, 10, 10
        );

        g2d.setColor(this.getBackground());
        g2d.fill(rectangle);
        g2d.dispose();

        super.paintChildren(g);
    }
}
