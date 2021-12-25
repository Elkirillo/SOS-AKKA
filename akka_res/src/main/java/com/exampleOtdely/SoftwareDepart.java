package com.exampleOtdely;


import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example2.Request;

/**
 * Данный актор принимает ссылку или значение в БД
 * Решает уже отдел
 * После решение передает поведение, что заявка решена
 * И в какой отдел нужно явится за ее решением
 * */
public class SoftwareDepart extends AbstractBehavior<Request.Getting> {

    //для поведение конструтора
    public static Behavior<Request.Getting> create(){
        return Behaviors.setup(context -> new SoftwareDepart(context));
    }
    //конструктор
    private SoftwareDepart(ActorContext<Request.Getting> context) {
        super(context);
        getContext().getLog().info("отдел ПО");

    }

    @Override
    public Receive<Request.Getting> createReceive() {
        return newReceiveBuilder()
                .onMessage(Request.Getting.class, this::someWork).build();
    }

    //тут в дальнейшем будет браться заявка из БД и решаться
    public Behavior<Request.Getting> someWork(Request.Getting comand) {
        ActorRef<BehaviorForApplication.Command> createBehaviorActor =
                getContext().spawn(BehaviorForApplication.create(), "idet_vibor_povedeniya12");
        createBehaviorActor.tell(BehaviorForApplication.Status.RESOLVE);
        return this;
    }

}
