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
import me.theentropyshard.chates.gui.chat.ChatView;
import me.theentropyshard.chates.gui.laf.DarkChatesLaf;

import javax.swing.*;
import java.awt.*;

public class TestGui {
    public TestGui() {
        FlatLaf.registerCustomDefaultsSource("themes");
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
        DarkChatesLaf.setup();

        JFrame frame = new JFrame("Chates");

        ChatView chatView = new ChatView();
        chatView.setPreferredSize(new Dimension(1280, 720));

        frame.add(chatView, BorderLayout.CENTER);
        frame.pack();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
