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

package me.theentropyshard.chates.matrix;

import com.google.gson.JsonObject;
import me.theentropyshard.chates.matrix.login.LoginRequestBody;
import me.theentropyshard.chates.matrix.login.LoginResponseBody;
import me.theentropyshard.chates.matrix.message.MessageSendRequestBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface MatrixHttpApi {
    @POST("_matrix/client/v3/login")
    Call<LoginResponseBody> login(@Body LoginRequestBody loginBody);

    @PUT("_matrix/client/v3/rooms/{roomId}/send/{eventType}/{txnId}")
    Call<JsonObject> sendMessage(@Body MessageSendRequestBody requestBody,
                                 @Path("roomId") String roomId,
                                 @Path("eventType") String eventType,
                                 @Path("txnId") String transactionId,
                                 @Header("Authorization") String accessToken
    );

    @GET("_matrix/client/v3/rooms/{roomId}/messages")
    Call<JsonObject> getMessages(
        @Path("roomId") String roomId,
        @Query("dir") String dir,
        @Query("limit") int limit,
        @Header("Authorization") String accessToken
    );

    @GET("_matrix/client/v3/sync")
    Call<JsonObject> initialSync(
        @Query("timeout") int timeout, @Header("Authorization") String accessToken
    );

    @GET("_matrix/client/v3/sync")
    Call<JsonObject> sync(
        @Query("timeout") int timeout,
        @Query("since") String since,
        @Header("Authorization") String accessToken
    );

    @POST("_matrix/client/v3/logout")
    Call<Object> logout(
        @Header("Authorization") String accessToken
    );
}
