package com.castaland.YoshiBOT;

import com.castaland.YoshiBOT.commands.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.User;

import javax.security.auth.login.LoginException;

public class Bot {

    public static JDA jda;
    public static User self;
    public static String eduid = "436516214487908353";
    public static String token;

    public static void main(String[] args) throws LoginException {
        if(args.length > 0) {
            token = args[0];
            JDABuilder builder = JDABuilder.createDefault(token);

            MessageListener msglistener = new MessageListener();

            msglistener.registerCommand("!tiempo", new TiempoCommand());
            msglistener.registerCommand("!dado", new DiceCommand());
            msglistener.registerCommand("!dados", new DicesCommand());
            msglistener.registerCommand("!hora", new HourCommand());
            msglistener.registerCommand("!primo", new PrimeCommand());

            builder.addEventListeners(msglistener);
            jda = builder.build();
            self = jda.getSelfUser();
        } else {
            System.err.println("No has metido una token como argumento.");
        }
    }
}
