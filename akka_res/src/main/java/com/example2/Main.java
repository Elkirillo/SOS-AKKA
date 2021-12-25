package com.example2;

import akka.actor.typed.ActorSystem;
import com.example.GreeterMain;

import java.io.IOException;

public class Main {
    public static void main (String[] args) {
        final ActorSystem<CreateRequest.MyRequest> actorReq = ActorSystem.create(CreateRequest.create(), "Create_request");

        //#main-send-messages
        actorReq.tell(new CreateRequest.MyRequest("Zayavka_dlya_resheniya"));

        try {
            System.out.println(">>> Press ENTER to exit <<<");
            System.in.read();
        } catch (IOException ignored) {
        } finally {
            actorReq.terminate();
        }
    }
}
