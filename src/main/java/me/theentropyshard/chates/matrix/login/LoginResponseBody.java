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

import com.google.gson.annotations.SerializedName;

public class LoginResponseBody {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("expires_in_ms")
    private long expiresInMs;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("well_known")
    private WellKnown wellKnown;

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public long getExpiresInMs() {
        return this.expiresInMs;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public String getUserId() {
        return this.userId;
    }

    public WellKnown getWellKnown() {
        return this.wellKnown;
    }

    public static final class WellKnown {
        @SerializedName("m.homeserver")
        private Homeserver homeserver;

        @SerializedName("m.identity_server")
        private IdentityServer identityServer;

        public Homeserver getHomeserver() {
            return this.homeserver;
        }

        public IdentityServer getIdentityServer() {
            return this.identityServer;
        }

        public static final class Homeserver {
            private String baseUrl;

            public String getBaseUrl() {
                return this.baseUrl;
            }
        }

        public static final class IdentityServer {
            private String baseUrl;

            public String getBaseUrl() {
                return this.baseUrl;
            }
        }
    }
}
