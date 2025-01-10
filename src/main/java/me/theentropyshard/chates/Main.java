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

package me.theentropyshard.chates;

import com.google.gson.JsonObject;
import me.theentropyshard.chates.gui.TestGui;
import me.theentropyshard.chates.matrix.MatrixHttpApi;
import me.theentropyshard.chates.matrix.login.LoginRequestBody;
import me.theentropyshard.chates.matrix.login.LoginResponseBody;
import me.theentropyshard.chates.matrix.message.MessageSendRequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        apiTest();
        //new TestGui();
    }

    static String ROOM_ID = System.getenv("MATRIX_ROOM");

    private static void apiTest() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://matrix.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        LoginRequestBody requestBody = new LoginRequestBody(
            new LoginRequestBody.Identifier("m.id.user", System.getenv("MATRIX_USERNAME")),
            System.getenv("MATRIX_PASSWORD"), "m.login.password"
        );

        MatrixHttpApi matrixHttpApi = retrofit.create(MatrixHttpApi.class);
        Call<LoginResponseBody> login = matrixHttpApi.login(requestBody);
        Response<LoginResponseBody> execute;
        String accessToken = "";
        try {
            execute = login.execute();
            accessToken = execute.body().getAccessToken();
            System.out.println(accessToken);
            /*JsonObject helloFromMyApi = matrixHttpApi.sendMessage(
                new MessageSendRequestBody(
                    "hello from my api",
                    "m.text"
                ),
                "",
                "m.room.message",
                UUID.randomUUID().toString(),
                "Bearer " + accessToken
            ).execute().body();

            MessageResponseBody messageResponseBody = null;
            ErrorResponseBody responseBody = null;
            if(helloFromMyApi.has("event_id")) {
                messageResponseBody = new Gson().fromJson(helloFromMyApi, MessageResponseBody.class);
            } else {
                responseBody = new Gson().fromJson(helloFromMyApi, ErrorResponseBody.class);
            }
            System.out.println(
                messageResponseBody == null ?
                    responseBody.getErrorCode() + " " + responseBody.getError() :
                    messageResponseBody.getEventId()
            );*/

            String at = accessToken;
            Thread thread = new Thread(() -> {
                try {
                    String next_batch = "";
                    Response<JsonObject> initialResp = matrixHttpApi.initialSync(30000, "Bearer " + at).execute();
                    //System.out.println(initialResp.code());
                    JsonObject body = initialResp.body();
                    System.out.println("initial sync: " + body);

                    next_batch = body.get("next_batch").getAsString();

                    while (true) {
                        try {
                            Response<JsonObject> syncResp = matrixHttpApi.sync(30000, next_batch, "Bearer " + at).execute();
                            System.out.println(syncResp.code());
                            JsonObject syncBody = syncResp.body();
                            System.out.println(syncBody);
                            next_batch = syncBody.get("next_batch").getAsString();

                            System.out.println(next_batch);

                            System.out.println("sync: " + body);
                        } catch (SocketTimeoutException timeoutException) {

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Scanner scanner = new Scanner(System.in);
            while (true) {
                if (scanner.hasNextLine()) {
                    String message = scanner.nextLine();

                    if (message.equals("exit")) {
                        break;
                    }

                    System.out.println(message);

                    matrixHttpApi.sendMessage(
                        new MessageSendRequestBody(
                            message,
                            "m.text"
                        ),
                        ROOM_ID,
                        "m.room.message",
                        UUID.randomUUID().toString(),
                        "Bearer " + accessToken
                    ).execute();

                    try {
                        Response<JsonObject> r = matrixHttpApi.getMessages(
                            ROOM_ID,
                            "b",
                            3,
                            "Bearer " + accessToken
                        ).execute();
                        System.out.println(r.code());
                        System.out.println(r.message());
                        System.out.println(r.isSuccessful());
                        System.out.println(r.errorBody());
                        System.out.println(r.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                matrixHttpApi.logout("Bearer " + accessToken).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
