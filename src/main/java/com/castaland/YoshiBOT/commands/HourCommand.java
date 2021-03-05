package com.castaland.YoshiBOT.commands;

import com.castaland.YoshiBOT.DCommand;
import com.castaland.utils.Hour;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class HourCommand implements DCommand {
    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        if (event.getChannelType() == ChannelType.TEXT) {
            String hora_mexico = Hour.getHour("America/Mexico_City");
            String hora_madrid = Hour.getHour("Europe/Madrid");
            event.getTextChannel().sendMessage("**Hora actual**\n" +
                    ":flag_es:  "+hora_madrid+" (Europa/Madrid)\n" +
                    ":flag_mx:  "+hora_mexico+" (América/Ciudad de México)").queue();
        }
    }
}
