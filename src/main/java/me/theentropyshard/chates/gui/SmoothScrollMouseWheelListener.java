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

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class SmoothScrollMouseWheelListener implements MouseWheelListener, ActionListener {
    private final JScrollBar scrollBar;
    private final float addition;
    private final float deceleration;
    private final Timer timer;

    private int wheelRotation;
    private float velocity;

    public SmoothScrollMouseWheelListener(JScrollBar scrollBar) {
        this(scrollBar, 15, 15.0f, 0.08f);
    }

    public SmoothScrollMouseWheelListener(JScrollBar scrollBar, int timerDelay, float addition, float deceleration) {
        this.scrollBar = scrollBar;

        this.addition = addition;
        this.deceleration = deceleration;

        this.timer = new Timer(timerDelay, this);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        this.wheelRotation = e.getWheelRotation();
        this.velocity = 1;

        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.velocity < 0f) {
            this.timer.stop();

            return;
        }

        int value = this.scrollBar.getValue();
        int newValue = (int) (value + this.wheelRotation * this.addition *
                SmoothScrollMouseWheelListener.easeInOutQuad(this.velocity));
        this.scrollBar.setValue(newValue);

        this.velocity -= this.deceleration;
    }

    private static float easeInOutQuad(float x) {
        return x < 0.5 ? 2 * x * x : (float) (1 - Math.pow(-2 * x + 2, 2) / 2);
    }
}