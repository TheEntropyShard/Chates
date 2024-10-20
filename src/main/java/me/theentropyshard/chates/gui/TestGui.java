/*
 * Chates - https://github.com/TheEntropyShard/Chates
 * Copyright (C) 2024 TheEntropyShard
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

package me.theentropyshard.chates.gui;

import com.formdev.flatlaf.FlatLaf;
import me.theentropyshard.chates.gui.laf.LightChatesLaf;
import me.theentropyshard.chates.gui.message.MessageComponent;
import me.theentropyshard.chates.gui.message.MessageSide;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TestGui {
    public TestGui() {
        FlatLaf.registerCustomDefaultsSource("themes");
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
        LightChatesLaf.setup();

        JFrame frame = new JFrame("Chates");

        JPanel testPanel = new JPanel(new MigLayout("insets 0, flowy, nogrid", "grow"));
        testPanel.setBorder(new EmptyBorder(8, 8, 8,8));
        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.add(testPanel, BorderLayout.PAGE_START);

        testPanel.add(new MessageComponent("Короче пилим тут клиент для матрикца", MessageSide.LEFT), "growx");
        testPanel.add(new MessageComponent("Вот, пока сделал только этот view", MessageSide.LEFT), "growx");
        testPanel.add(new MessageComponent("Надо сделать базовый чат и сделать отправления сообщений,\n" +
            "а потом еще и получение, но это уже не так просто,\n" +
            "как я посмотрел", MessageSide.RIGHT), "growx");
        testPanel.add(new MessageComponent("Вот, пока так)", MessageSide.RIGHT), "growx");
        testPanel.add(new MessageComponent("Не знаю, что получится", MessageSide.RIGHT), "growx");

        JScrollPane modCardsScrollPane = new JScrollPane(borderPanel);
        modCardsScrollPane.setPreferredSize(new Dimension(1280, 720));
        frame.add(modCardsScrollPane, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
