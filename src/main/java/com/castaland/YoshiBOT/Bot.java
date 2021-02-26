package com.castaland.YoshiBOT;

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
            builder.addEventListeners(new MessageListener());
            jda = builder.build();
            self = jda.getSelfUser();
        } else {
            System.err.println("No has metido una token como argumento.");
        }

    }
}
