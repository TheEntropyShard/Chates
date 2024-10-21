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

package me.theentropyshard.chates.gui.chat;

import me.theentropyshard.chates.gui.FlatSmoothScrollPaneUI;
import me.theentropyshard.chates.gui.message.MessageComponent;
import me.theentropyshard.chates.gui.message.MessageSide;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatComponent extends JScrollPane {
    private final JPanel messagesPanel;

    public ChatComponent() {
        this.messagesPanel = new JPanel();
        this.messagesPanel.setLayout(new MigLayout("insets 0, flowy, nogrid", "grow"));
        this.messagesPanel.setBorder(new EmptyBorder(8, 8, 8,8));

        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.add(this.messagesPanel, BorderLayout.PAGE_START);

        this.messagesPanel.add(new MessageComponent("Короче пилим тут клиент для матрикца", MessageSide.LEFT), "growx");
        this.messagesPanel.add(new MessageComponent("Вот, пока сделал только этот view", MessageSide.LEFT), "growx");
        this.messagesPanel.add(new MessageComponent("Надо сделать базовый чат и сделать отправления сообщений,\n" +
            "а потом еще и получение, но это уже не так просто,\n" +
            "как я посмотрел", MessageSide.RIGHT), "growx");
        this.messagesPanel.add(new MessageComponent("Вот, пока так)", MessageSide.RIGHT), "growx");
        this.messagesPanel.add(new MessageComponent("Не знаю, что получится", MessageSide.RIGHT), "growx");

        this.setViewportView(borderPanel);

        this.setUI(new FlatSmoothScrollPaneUI());
    }
}
