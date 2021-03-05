package com.castaland.YoshiBOT.commands;

import com.castaland.YoshiBOT.DCommand;
import com.castaland.utils.Primes;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.math.BigInteger;
import java.util.List;

public class PrimeCommand implements DCommand {
    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        if (event.getChannelType() == ChannelType.TEXT) {
            BigInteger rprime = Primes.randPrime();
            event.getTextChannel().sendMessage(":exclamation:  "+rprime).queue();
        }
    }
}
