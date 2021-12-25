package com.example2;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.example.Greeter;
import com.example.GreeterBot;

import java.util.Objects;

/**
 * Тут будет заноситься заявка в бд
 */
public class Request extends AbstractBehavior<Request.Getting> {

    public static final class Getting {
        public final String descripRequest;

        public Getting(String descripRequest) {
            this.descripRequest = descripRequest;
        }
    }

    public static Behavior<Getting> create() {
        return Behaviors.setup(Request::new);
    }

    private Request(ActorContext<Getting> context) { super(context); }

    @Override
    public Receive<Getting> createReceive() {
        return newReceiveBuilder().onMessage(Getting.class, this::onGetting).build();
    }

    private Behavior<Getting> onGetting(Getting command) {
        getContext().getLog().info("Заявка создана");
        //здесь поместим ее в бд
        return this;
    }
}
