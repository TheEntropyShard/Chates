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

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MessageComponent extends JPanel {
    public static final Color LEFT_COLOR = Color.decode("#182533");
    public static final Color RIGHT_COLOR = Color.decode("#2b5278");

    public MessageComponent(String text, MessageSide side) {
        this.setOpaque(false);

        if (side == MessageSide.LEFT) {
            this.setLayout(new MigLayout("insets 0", "[]push", "[]"));
        } else if (side == MessageSide.RIGHT) {
            this.setLayout(new MigLayout("insets 0", "push[]", "[]"));
        }

        Color background;

        if (side == MessageSide.LEFT) {
            background = MessageComponent.LEFT_COLOR;
        } else if (side == MessageSide.RIGHT) {
            background = MessageComponent.RIGHT_COLOR;
        } else {
            throw new IllegalArgumentException("Unknown side: " + side);
        }

        MessagePanel panel = new MessagePanel(text, background);
        this.add(panel);
    }
}
