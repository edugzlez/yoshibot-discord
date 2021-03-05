package com.castaland.YoshiBOT.commands;

import com.castaland.YoshiBOT.DCommand;
import com.castaland.utils.Dices;
import com.castaland.utils.Stats;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class DicesCommand implements DCommand {
    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {

        if (event.getChannelType() == ChannelType.TEXT) {
            TextChannel channel = event.getTextChannel();
            try {
                short num = 2;

                if (args.size() == 2) {
                    num = Short.parseShort(args.get(1));
                    if (num <= 0) throw new NumberFormatException();
                }

                int[] results = Dices.throwDices(num);

                if (num <= 5) {
                    channel.sendMessage(":game_die: Has lanzado **" + num + "** dados con resultados:\n " + Arrays.toString(results)).queue();
                } else {
                    double[] resultsDouble = Arrays.stream(results).asDoubleStream().toArray();

                    double mean = Stats.getMean(resultsDouble);
                    double mode = Stats.getMode(resultsDouble);

                    channel.sendMessage(":game_die: Has lanzado **" + num + "** dados.\n" +
                            "**Media**: " + mean + " \n" +
                            "**Moda**: " + mode).queue();
                }
            } catch (NumberFormatException e) {
                channel.sendMessage("Solo puedo tomar valores entre 1 y "+Short.MAX_VALUE).queue();
            }

        }
    }
}
