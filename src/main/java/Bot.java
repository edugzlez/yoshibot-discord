import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

public class Bot extends ListenerAdapter {

    public static JDA jda;
    public static User self;
    public static String eduid = "436516214487908353";

    public static void main(String[] args) throws LoginException, ParseException {
        JDABuilder builder = JDABuilder.createDefault("ODEyMDc3NzE5OTY0NjE0NjU2.YC7gUA.oun-Mowr0rNld72Snl1m4TuOc0U");
        builder.addEventListeners(new Bot());
        jda = builder.build();
        self = jda.getSelfUser();
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);
        List<User> mentions = event.getMessage().getMentionedUsers();
        String message = event.getMessage().getContentRaw();
        TextChannel channel = event.getTextChannel();

        if (event.getAuthor().getId().equals(eduid)) {
            if (message.equalsIgnoreCase("!apagar")) {
                channel.sendMessage("¡Me apago!").queue();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jda.shutdown();
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
        else if (message.equalsIgnoreCase("!primo")) {
            BigInteger primo = BigInteger.probablePrime(randInt(2, 32), new Random());
            channel.sendMessage(":exclamation:  "+primo.toString()).queue();
        }
        System.out.println(
                event.getAuthor().getId()+":"+
                        event.getAuthor().getName()+"::"+
                        event.getMessage().getContentDisplay()
        );
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
