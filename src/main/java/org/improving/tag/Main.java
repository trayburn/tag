package org.improving.tag;

import org.improving.tag.commands.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringContext.class);

        Game game = context.getBean(Game.class);
        game.run();

        long elapsedTicks = game.getEndTime().getTime() -
                game.getStartTime().getTime();
        double elapsedSeconds = elapsedTicks / 1000.0;
        System.out.println("We were running for " + elapsedSeconds + "s.");
    }
}
