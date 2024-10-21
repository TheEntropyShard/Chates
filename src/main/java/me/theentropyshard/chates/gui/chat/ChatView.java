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

import javax.swing.*;
import java.awt.*;

public class ChatView extends JPanel {
    private final ChatHeader chatHeader;
    private final ChatComponent chatComponent;
    private final ChatInput chatInput;

    public ChatView() {
        super(new BorderLayout());

        this.chatHeader = new ChatHeader("Chat", 10);
        this.add(this.chatHeader, BorderLayout.NORTH);

        this.chatComponent = new ChatComponent();
        this.add(this.chatComponent, BorderLayout.CENTER);

        this.chatInput = new ChatInput(this.chatComponent);
        this.add(this.chatInput, BorderLayout.SOUTH);
    }
}
