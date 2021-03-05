package com.castaland.YoshiBOT;

import com.castaland.utils.Dices;
import com.castaland.utils.Hour;
import com.castaland.utils.JsonReader;
import com.castaland.utils.Primes;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;

public class MessageListener extends ListenerAdapter {


    public MessageListener() {

    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);


        String message = event.getMessage().getContentRaw();
        TextChannel channel = event.getTextChannel();

        if (event.getAuthor().getId().equals(Bot.eduid)) {
            if (message.equalsIgnoreCase("!apagar")) {
                channel.sendMessage("¡Me apago!").queue();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Bot.jda.shutdown();
            }
        }

        if (message.equalsIgnoreCase("!tiempo")) {
            try {
                JSONObject data = JsonReader.readJsonFromUrl("https://www.el-tiempo.net/api/json/v2/provincias/05/municipios/05047");
                int temperatura_actual = data.getInt("temperatura_actual");
                String cielo = data.getJSONObject("stateSky").getString("description");

                channel.sendMessage(":earth_africa:  La temperatura es de "+temperatura_actual+"ºC y el cielo está "+cielo+" en Candeleda.").queue();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if (message.equalsIgnoreCase("!dado")) {
            short rprime = Dices.throwDice();
            channel.sendMessage(":game_die: Ha salido el número **"+rprime+"**").queue();
        }
        else if (message.equalsIgnoreCase("!dados")) {
            short[] die = Dices.throwDices(2);
            channel.sendMessage(":game_die: Han salido los números **"+die[0]+"** y  **"+die[1]+"**").queue();
        }
        else if (message.equalsIgnoreCase("!primo")) {
            BigInteger rprime = Primes.randPrime();
            channel.sendMessage(":exclamation:  "+rprime.toString()).queue();
        }
        else if(message.equalsIgnoreCase("!hora")) {
            String hora_mexico = Hour.getHour("America/Mexico_City");
            String hora_madrid = Hour.getHour("Europe/Madrid");
            channel.sendMessage("**Hora actual**\n :flag_es:  "+hora_madrid+" (Europa/Madrid)\n :flag_mx:  "+hora_mexico+" (América/Ciudad de México)").queue();
        }
        System.out.println(
                event.getAuthor().getId()+":"+
                        event.getAuthor().getName()+"::"+
                        event.getMessage().getContentDisplay()
        );
    }
}
