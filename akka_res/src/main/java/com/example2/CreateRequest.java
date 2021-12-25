package com.example2;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.Greeter;

/**
 * Пользователь создает заявку нашу заявку
 */
public class CreateRequest extends AbstractBehavior<CreateRequest.MyRequest> {

    //здесь мы будем передавать нашу заявку
    public static class MyRequest  {
        public final String myrequest;
        String whom;

        public MyRequest(String request) {
            this.myrequest = request;
        }
    }

    //фабричный метод создания актора CreateRequest
    public static Behavior<MyRequest> create() { return Behaviors.setup(CreateRequest::new); }

    private final ActorRef<Request.Getting> gettingrequest;

    private CreateRequest(ActorContext<MyRequest> context) {
        super(context);
        //#create-actors
        gettingrequest = context.spawn(GettingRequest.create("some message"), "id_zayavki:");
    }


    @Override
    public Receive<MyRequest> createReceive() {
        return  newReceiveBuilder()
                .onMessage(MyRequest.class, this::onSendRequest)
                .build();
    }

    //Метод отправляет gettingrequest актору заявку
    public Behavior<MyRequest> onSendRequest(MyRequest command) {
        getContext().getLog().info("Заявка отправлена на создание");
        //#create-actors
        ActorRef<Request.Getting> currentRequest = getContext().spawn(Request.create(), "id_zayavki_2:");
        currentRequest.tell(new Request.Getting("message"));
        gettingrequest.tell(new Request.Getting("message2"));
        return this;
    }
}
