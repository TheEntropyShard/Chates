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

package me.theentropyshard.chates.matrix.login;

public class LoginRequestBody {
    private Identifier identifier;
    private String password;
    private String type;

    public LoginRequestBody(Identifier identifier, String password, String type) {
        this.identifier = identifier;
        this.password = password;
        this.type = type;
    }

    public static final class Identifier {
        private String type;
        private String user;

        public Identifier(String type, String user) {
            this.type = type;
            this.user = user;
        }
    }
}
