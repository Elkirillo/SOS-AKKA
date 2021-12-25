package com.exampleOtdely;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example2.GettingRequest;
import com.example2.Request;

public class BehaviorForApplication extends AbstractBehavior<BehaviorForApplication.Command> {

    public interface Command{}

    //...........   Реализация интерфейса command ..............
    public enum Status implements Command {
        RESOLVE, GETTING, NOTCONSIDERED
    }


    // static фабричный метод,Behavior.setup возвращает ссылку на контекст актора
    public static Behavior<Command> create(){
        return Behaviors.setup(context -> new BehaviorForApplication(context));
    }

    // Конструктор
    private BehaviorForApplication(ActorContext<Command> context) {
        super(context);
    }

    //выбор поведения
    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals(Status.NOTCONSIDERED,this::NotifOfNotConsidered)
                .onMessageEquals(Status.RESOLVE,this::SendMessToNotif)
                .onMessageEquals(Status.GETTING,this::NotifOfGetting)
                .build();
    }

    //вызывает актор -аналитик
    public Behavior<Command> NotifOfNotConsidered(){
        getContext().getLog().info("Заявка заявка на рассмотрении");
        ActorRef<Request.Getting> sayTo = getContext().spawn(AnalystDepart.create(), "command_request");
        sayTo.tell(new Request.Getting("Somedescrib"));
        return this;
    }

    //поведение когда заявка будет проанализирована
    //пока создание актора отдела сделано тут, но потом сюда просто придет ссылка с его именем
    public Behavior<Command> NotifOfGetting(){
        getContext().getLog().info("Заявка передана в отдел: ");
        ActorRef<Request.Getting> sayTo = getContext().spawn(SoftwareDepart.create(), "command_request1");
        sayTo.tell(new Request.Getting("Somedescrib"));
        return this;
    }

    //поведение когда заявка завершиться
    public Behavior<Command> SendMessToNotif(){
        getContext().getLog().info("Заявка решена");
        return this;
    }


}
