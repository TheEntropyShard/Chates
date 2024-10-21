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
import me.theentropyshard.chates.utils.SwingUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ChatComponent extends JScrollPane {
    private static final BufferedImage WALLPAPER = SwingUtils.getImage("/wallpapers/wallpaper.jpg");

    private final JPanel messagesPanel;

    public ChatComponent() {
        this.messagesPanel = new JPanel();
        this.messagesPanel.setOpaque(false);
        this.messagesPanel.setLayout(new MigLayout("insets 0, flowy, nogrid", "grow"));
        this.messagesPanel.setBorder(new EmptyBorder(8, 8, 8,8));

        JPanel borderPanel = new JPanel(new BorderLayout());
        borderPanel.setOpaque(false);
        borderPanel.add(this.messagesPanel, BorderLayout.PAGE_START);

        this.messagesPanel.add(new MessageComponent("Короче пилим тут клиент", MessageSide.LEFT), "growx");
        this.messagesPanel.add(new MessageComponent("Вот, пока сделал только этот view", MessageSide.LEFT), "growx");
        this.messagesPanel.add(new MessageComponent("Надо сделать базовый чат и сделать отправления сообщений,\n" +
            "а потом еще и получение, но это уже не так просто,\n" +
            "как я посмотрел", MessageSide.RIGHT), "growx");
        this.messagesPanel.add(new MessageComponent("Вот, пока так)", MessageSide.RIGHT), "growx");
        this.messagesPanel.add(new MessageComponent("Не знаю, что получится", MessageSide.RIGHT), "growx");

        this.setViewportView(borderPanel);
        this.getViewport().setOpaque(false);
        this.setOpaque(false);

        this.setUI(new FlatSmoothScrollPaneUI());
    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics graphics = g.create();
        graphics.drawImage(
            ChatComponent.WALLPAPER, 0, 0,
            this.getParent().getParent().getParent().getWidth(),
            this.getParent().getParent().getParent().getHeight(),
            null
        );
        graphics.dispose();

        super.paintChildren(g);
    }

    public void scrollDown() {
        JScrollBar scrollBar = this.getVerticalScrollBar();
        scrollBar.setValue(scrollBar.getMaximum());
    }

    public void addMessageLast(MessageComponent component) {
        this.messagesPanel.add(component, "growx");
    }

    public void addMessageToRandomSide(String text) {
        MessageSide side = new Random().nextBoolean() ? MessageSide.LEFT : MessageSide.RIGHT;

        this.addMessageLast(new MessageComponent(text, side));
    }
}
