package com.castaland.YoshiBOT.commands;

import com.castaland.YoshiBOT.DCommand;
import com.castaland.utils.Dices;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

public class DiceCommand implements DCommand {
    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        if (event.getChannelType() == ChannelType.TEXT) {
            TextChannel channel = event.getTextChannel();
            int rprime = Dices.throwDice();
            channel.sendMessage(":game_die: Ha salido el número **" + rprime + "**").queue();
        }
    }
}
