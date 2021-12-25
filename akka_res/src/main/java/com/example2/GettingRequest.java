package com.example2;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.GreeterBot;
import com.exampleOtdely.BehaviorForApplication;


/**
 * даннный класс получает ссылку на заявку
 */
public class GettingRequest extends AbstractBehavior<Request.Getting> {

    //фабричный метод создания актора CreateRequest
    public static Behavior<Request.Getting> create(String message) {
        return Behaviors.setup(context -> new GettingRequest(context, message));
    }

    private final String message;
    private GettingRequest(ActorContext<Request.Getting> context, String message) {
        super(context);
        this.message = message;
    }


    @Override
    public Receive<Request.Getting> createReceive() {
        return newReceiveBuilder().onMessage(Request.Getting.class, this::onGettingTo).build();
    }

    //поведение - вызов behaviorforapplication и вызывает поведение NotifOfNotConsidered
    private Behavior<Request.Getting>onGettingTo(Request.Getting message) {
        getContext().getLog().info("Заявка поступила на сервис");
        ActorRef<BehaviorForApplication.Command> createBehaviorActor =
                getContext().spawn(BehaviorForApplication.create(), "idet_vibor_povedeniya");
        createBehaviorActor.tell(BehaviorForApplication.Status.NOTCONSIDERED);
        return this;
        }
}
