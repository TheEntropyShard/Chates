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

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;

public class ChatInput extends JPanel {
    private final JTextArea messageInputArea;

    public ChatInput() {
        super(new BorderLayout());

        this.messageInputArea = new JTextArea(1, 20);
        Document document = this.messageInputArea.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                /*int length = document.getLength();
                String text = null;
                try {
                    text = document.getText(length - 1, 1);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(text.replace("\n", "\\n"));
                if (text.equals("\n")) {
                    ChatInput.adjustInputHeight(ChatInput.this.messageInputArea, ChatInput.this);
                }*/

                if (messageInputArea.getLineCount() <= 10) {
                    ChatInput.adjustInputHeight(ChatInput.this.messageInputArea, ChatInput.this);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                /*int length = document.getLength();
                String text = null;
                try {
                    text = document.getText(length - 1, 1);
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println(text.replace("\n", "\\n"));
                if (text.equals("\n")) {
                    ChatInput.adjustInputHeight(ChatInput.this.messageInputArea, ChatInput.this);
                }*/

                if (messageInputArea.getLineCount() <= 10) {
                    ChatInput.adjustInputHeight(ChatInput.this.messageInputArea, ChatInput.this);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        ((PlainDocument) document).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.equals("\n") && ChatInput.this.messageInputArea.getLineCount() > 10 &&
                    ChatInput.this.messageInputArea.getText().trim().isEmpty()) {

                    return;
                }

                super.replace(fb, offset, length, text, attrs);
            }
        });

        JScrollPane scrollPane = new JScrollPane(
            this.messageInputArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setUI(new FlatSmoothScrollPaneUI());

        this.add(scrollPane, BorderLayout.CENTER);
    }

    private static void adjustInputHeight(JTextArea textArea, Component component) {
        // Calculate the preferred size based on the number of lines
        int lineCount = textArea.getLineCount();

        /*if (lineCount > 10 && textArea.getText().trim().isEmpty()) {
            return;
        }*/

        int lineHeight = textArea.getFontMetrics(textArea.getFont()).getHeight();
        int newHeight = lineCount * lineHeight;

        // Set the new preferred size
        Dimension newSize = new Dimension(textArea.getPreferredSize().width, newHeight);
        component.setPreferredSize(newSize);
        component.revalidate(); // Revalidate to apply the new size
        component.repaint(); // Repaint to refresh the display
    }
}
