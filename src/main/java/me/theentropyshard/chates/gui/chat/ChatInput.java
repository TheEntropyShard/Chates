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

package me.theentropyshard.chates.gui.chat;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.ui.FlatScrollBarUI;
import com.formdev.flatlaf.ui.FlatUIUtils;
import me.theentropyshard.chates.gui.FlatSmoothScrollPaneUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChatInput extends JPanel {
    public static final int CHAT_INPUT_HEIGHT = 46;

    private final JButton attachButton;
    private final JTextArea messageInputArea;
    private final JButton sendButton;

    public ChatInput(ChatComponent chatComponent) {
        super(new BorderLayout());

        Color background = Color.decode("#182533");
        this.setBackground(background);

        this.attachButton = new JButton(
            new FlatSVGIcon(ChatInput.class.getResource("/paperclip.svg"))
                .setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.GRAY))
        );
        this.attachButton.setContentAreaFilled(false);
        this.attachButton.setFocusPainted(false);

        this.add(this.attachButton, BorderLayout.WEST);

        this.messageInputArea = new JTextArea(1, 20) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (this.getDocument().getLength() > 0) {
                    return;
                }

                String text = "Write a message...";
                FontMetrics fontMetrics = g.getFontMetrics();
                int y = (this.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
                FlatUIUtils.drawString(
                    this, g, text, this.getBounds().x + 8, y
                );
            }

            @Override
            protected boolean processKeyBinding(KeyStroke ks, KeyEvent e, int condition, boolean pressed) {
                if (ks.getKeyCode() == KeyEvent.VK_BACK_SPACE && this.getDocument().getLength() == 0) {
                    return false;
                }

                return super.processKeyBinding(ks, e, condition, pressed);
            }
        };

        InputMap inputMap = this.messageInputArea.getInputMap(JComponent.WHEN_FOCUSED);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter_send_message");

        ActionMap actionMap = this.messageInputArea.getActionMap();

        actionMap.put("enter_send_message", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = messageInputArea.getText();
                System.out.println("sending message: " + text);
                chatComponent.addMessageToRandomSide(text);
                chatComponent.revalidate();
                messageInputArea.setText("");
                chatComponent.scrollDown();
            }
        });

        this.messageInputArea.setBackground(background);
        Document document = this.messageInputArea.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (ChatInput.this.messageInputArea.getLineCount() <= 10) {
                    ChatInput.adjustInputHeight(ChatInput.this.messageInputArea, ChatInput.this);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (ChatInput.this.messageInputArea.getLineCount() <= 10) {
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
                    text.trim().isEmpty()) {

                    return;
                }

                super.replace(fb, offset, length, text, attrs);
            }
        });

        this.messageInputArea.setBorder(new EmptyBorder(6, 8, 4,0));

        JPanel inputBorderPanel = new JPanel(new BorderLayout());
        inputBorderPanel.setBackground(background);
        inputBorderPanel.add(this.messageInputArea, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(
            inputBorderPanel,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.getVerticalScrollBar().setUI(new FlatScrollBarUI() {
            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

            }
        });

        scrollPane.setUI(new FlatSmoothScrollPaneUI());

        this.add(scrollPane, BorderLayout.CENTER);

        this.sendButton = new JButton(
            new FlatSVGIcon(ChatInput.class.getResource("/plane.svg"))
                .setColorFilter(new FlatSVGIcon.ColorFilter(color -> Color.GRAY))
        );
        this.sendButton.setContentAreaFilled(false);
        this.sendButton.setFocusPainted(false);

        this.add(this.sendButton, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(100, ChatInput.CHAT_INPUT_HEIGHT));
        this.setBorder(new EmptyBorder(8, 8, 8, 8));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private static void adjustInputHeight(JTextArea textArea, Component component) {
        // Calculate the preferred size based on the number of lines
        int lineCount = textArea.getLineCount();
        int lineHeight = textArea.getFontMetrics(textArea.getFont()).getHeight();
        int newHeight = lineCount * lineHeight;

        // Set the new preferred size
        if (newHeight < ChatInput.CHAT_INPUT_HEIGHT) {
            newHeight = ChatInput.CHAT_INPUT_HEIGHT;
        }
        Dimension newSize = new Dimension(textArea.getPreferredSize().width, newHeight);
        component.setPreferredSize(newSize);
        component.revalidate(); // Revalidate to apply the new size
        component.repaint(); // Repaint to refresh the display
    }
}
