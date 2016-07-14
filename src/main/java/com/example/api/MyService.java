package com.example.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/")
public class MyService {
    @POST
    @Consumes("text/plain")
    public void postText(String body) {
        System.out.println("MyService.postText body=" + body);
    }

    @POST
    @Consumes("text/csv")
    public void postCsv(String body) {
        System.out.println("MyService.postCsv body=" + body);
    }

    @POST
    @Consumes("text/tab-separated-values")
    public void postTsv(String body) {
        System.out.println("MyService.postTsv body=" + body);
    }
}
