package com.kaedesbingo;


import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.util.ArrayDeque;
import java.util.Queue;
import javax.inject.Inject;


class GroupService
{
    private final OkHttpClient http;
    private final Gson gson;
    private String endpoint = "https://example.invalid/bingo"; // TODO: point to your backend
    private final Queue<TaskCompletion> outbox = new ArrayDeque<>();


    @Inject GroupService(OkHttpClient http, Gson gson){ this.http = http; this.gson = gson; }


    GroupResponse createGroup(String password)
    {
        try
        {
            Request req = new Request.Builder()
                    .url(endpoint + "/groups")
                    .post(RequestBody.create(MediaType.parse("application/json"),
                            gson.toJson(new CreateGroupBody(password))))
                    .build();
            try (Response r = http.newCall(req).execute())
            {
                if (!r.isSuccessful() || r.body() == null) return null;
                return gson.fromJson(r.body().charStream(), GroupResponse.class);
            }
        }
        catch (Exception e){ return null; }
    }


    boolean joinGroup(String key, String password)
    {
// TODO: implement real join
        return true;
    }


    void enqueueSync(TaskCompletion c)
    {
        if (c != null) outbox.add(c);
// TODO: schedule background flush
    }


    static class CreateGroupBody { CreateGroupBody(String password){ this.password = password; } String password; }
}