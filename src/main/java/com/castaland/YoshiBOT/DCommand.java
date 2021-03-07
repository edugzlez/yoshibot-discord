package com.castaland.YoshiBOT;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public interface DCommand {

    void execute(MessageReceivedEvent event, List<String> args);

    static List<String> getParams(String message) {
        return Arrays.asList(message.split(" "));
    }
}

