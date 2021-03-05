package com.castaland.YoshiBOT;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageListener extends ListenerAdapter {

    private Map<String, DCommand> commands;

    public MessageListener() {
        commands = new HashMap<String, DCommand>();
    }

    public void registerCommand(String cmd_text, DCommand cmd) {
        commands.put(cmd_text, cmd);
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        String message = event.getMessage().getContentRaw();

        List<String> params = DCommand.getParams(message);

        if(params.size() > 0 && commands.containsKey(params.get(0))) {
            commands.get(params.get(0)).execute(event, params);
        }

        System.out.println(
                event.getAuthor().getId()+":"+
                        event.getAuthor().getName()+"::"+
                        event.getMessage().getContentDisplay()
        );
    }
}
