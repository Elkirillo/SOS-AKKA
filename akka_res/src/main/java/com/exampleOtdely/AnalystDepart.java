package com.exampleOtdely;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example2.CreateRequest;
import com.example2.GettingRequest;
import com.example2.Request;

/** данный актор анализирует заявку, заносит ее в бд, вызывает актор вызова
 * вызывает актор отдела, который будет решать эту проблему
 * так же передаст актору поведения сообщение, что заявка решается и в каком отделе
* */
public class AnalystDepart extends AbstractBehavior<Request.Getting> {
    //для поведение конструтора
    public static Behavior<Request.Getting> create(){
        return Behaviors.setup(context -> new AnalystDepart(context));
    }
    //заявка
    //конструктор
    private AnalystDepart(ActorContext<Request.Getting> context) {
        super(context);
        //#create-actors
    }

    @Override
    public Receive<Request.Getting> createReceive() {
        return newReceiveBuilder()
                .onMessage(Request.Getting.class, this::onSendNotif).build();
    }

    //здесь передадим заявку в отдел
    //позже тут будет создано создание актора-отдела
    //а его имя вернется в актор-behaviour
    public Behavior<Request.Getting> onSendNotif(Request.Getting comand) {
        ActorRef<BehaviorForApplication.Command> createBehaviorActor =
                getContext().spawn(BehaviorForApplication.create(), "idet_vibor_povedeniya");
        createBehaviorActor.tell(BehaviorForApplication.Status.GETTING);
        return this;
    }

}
