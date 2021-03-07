package com.castaland.YoshiBOT.commands;

import com.castaland.YoshiBOT.DCommand;
import com.castaland.utils.JsonReader;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class TiempoCommand implements DCommand {
    private int candeleda_temperatura;
    private String candeleda_cielo, candeleda_cielo_icon;

    private int madrid_temperatura;
    private String madrid_cielo, madrid_cielo_icon;

    HashMap<String, String> codes;


    private Calendar lastSeenDate = null;

    public TiempoCommand() {
        codes = new HashMap<String, String>();

        codes.put("11", ":sunny:");
        codes.put("12", ":partly_sunny:");
        codes.put("13", ":partly_sunny:");
        codes.put("14", ":white_sun_cloud:");
        codes.put("15", ":cloud:");
        codes.put("16", ":cloud:");
        codes.put("17", ":sunny:");

        codes.put("11n", ":crescent_moon:");
        codes.put("12n", ":crescent_moon: :cloud:");
        codes.put("13n", ":crescent_moon: :cloud:");
        codes.put("14n", ":crescent_moon: :cloud:");
        codes.put("15n", ":cloud:");
        codes.put("16n", ":cloud:");
        codes.put("17n", ":crescent_moon:");

        codes.put("23", ":white_sun_rain_cloud:");
        codes.put("24", ":white_sun_rain_cloud:");
        codes.put("25", ":cloud_rain:");
        codes.put("26", ":cloud_rain:");
        codes.put("27", ":cloud: :white_sun_rain_cloud:");

        codes.put("23n", ":crescent_moon: :cloud_rain:");
        codes.put("24n", ":crescent_moon: :cloud_rain:");
        codes.put("25n", ":cloud_rain:");
        codes.put("26n", ":cloud_rain:");
        codes.put("27n", ":crescent_moon: :cloud_rain:");

        codes.put("33", ":partly_sunny: :cloud_snow:");
        codes.put("34", ":partly_sunny: :cloud_snow: ");
        codes.put("35", ":cloud_snow: ");
        codes.put("36", ":cloud_snow:");

        codes.put("33n", ":crescent_moon: :cloud_snow:");
        codes.put("34n", ":crescent_moon: :cloud_snow: ");
        codes.put("35n", ":cloud_snow: ");
        codes.put("36n", ":cloud_snow:");

        codes.put("43", ":white_sun_rain_cloud:");
        codes.put("44", ":white_sun_rain_cloud:");
        codes.put("45", ":cloud_rain:");
        codes.put("46", ":cloud_rain:");

        codes.put("43n", ":crescent_moon: :cloud_rain:");
        codes.put("44n", ":crescent_moon: :cloud_rain:");
        codes.put("45n", ":cloud_rain:");
        codes.put("46n", ":cloud_rain:");

        codes.put("51", ":white_sun_small_cloud: :thunder_cloud_rain:");
        codes.put("52", ":white_sun_small_cloud: :thunder_cloud_rain:");
        codes.put("53", ":thunder_cloud_rain: :thunder_cloud_rain:");
        codes.put("54", ":thunder_cloud_rain: :thunder_cloud_rain:");

        codes.put("51n", ":crescent_moon: :thunder_cloud_rain:");
        codes.put("52n", ":crescent_moon: :thunder_cloud_rain:");
        codes.put("53n", ":thunder_cloud_rain: :thunder_cloud_rain:");
        codes.put("54n", ":thunder_cloud_rain: :thunder_cloud_rain:");

        codes.put("61", ":white_sun_small_cloud: :thunder_cloud_rain:");
        codes.put("62", ":white_sun_small_cloud: :thunder_cloud_rain:");
        codes.put("63", ":thunder_cloud_rain: :thunder_cloud_rain:");
        codes.put("64", ":thunder_cloud_rain: :thunder_cloud_rain:");

        codes.put("61n", ":crescent_moon: :thunder_cloud_rain:");
        codes.put("62n", ":crescent_moon: :thunder_cloud_rain:");
        codes.put("63n", ":thunder_cloud_rain: :thunder_cloud_rain:");
        codes.put("64n", ":thunder_cloud_rain: :thunder_cloud_rain:");

        codes.put("71", ":white_sun_small_cloud: :cloud_snow:");
        codes.put("72", ":white_sun_small_cloud: :cloud_snow:");
        codes.put("73", ":cloud_snow: :cloud_snow:");
        codes.put("74", ":cloud_snow: :cloud_snow:");

        codes.put("71n", ":crescent_moon: :cloud_snow:");
        codes.put("72n", ":crescent_moon: :cloud_snow:");
        codes.put("73n", ":cloud_snow: :cloud_snow:");
        codes.put("74n", ":cloud_snow: :cloud_snow:");

        codes.put("81", ":dash: ");
        codes.put("82", ":dash: ");
        codes.put("83", ":dash: :dash:");

        codes.put("81n", ":dash: ");
        codes.put("82n", ":dash: ");
        codes.put("83n", ":dash: :dash:");
    }

    @Override
    public void execute(MessageReceivedEvent event, List<String> args) {
        if (event.getChannelType() == ChannelType.TEXT) { // verifies if channel is textchannel
            TextChannel channel = event.getTextChannel();
            try {
                Calendar now = Calendar.getInstance();

                if (lastSeenDate == null || now.after(lastSeenDate))
                    refresh();

                channel.sendMessage(":earth_africa: **Tiempo**\n" +
                        String.format(":small_blue_diamond: %s     :thermometer: %d ºC     %s  %s \n", "Candeleda", candeleda_temperatura, candeleda_cielo_icon, candeleda_cielo) +
                        String.format(":small_blue_diamond: %s           :thermometer: %d ºC     %s  %s", "Madrid", madrid_temperatura, madrid_cielo_icon, madrid_cielo)
                ).queue();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void refresh() {
        try {
            JSONObject data_candeleda = JsonReader.readJsonFromUrl("https://www.el-tiempo.net/api/json/v2/provincias/05/municipios/05047");
            JSONObject data_madrid = JsonReader.readJsonFromUrl("https://www.el-tiempo.net/api/json/v2/provincias/28/municipios/28079");

            candeleda_temperatura = data_candeleda.getInt("temperatura_actual");
            candeleda_cielo = data_candeleda.getJSONObject("stateSky").getString("description");
            String candeleda_cielo_id = data_candeleda.getJSONObject("stateSky").getString("id");
            candeleda_cielo_icon = "";

            candeleda_cielo_icon = codes.getOrDefault(candeleda_cielo_id, ":white_small_square:");

            madrid_temperatura = data_madrid.getInt("temperatura_actual");
            madrid_cielo = data_madrid.getJSONObject("stateSky").getString("description");
            String madrid_cielo_id = data_madrid.getJSONObject("stateSky").getString("id");
            madrid_cielo_icon = "";

            madrid_cielo_icon = codes.getOrDefault(madrid_cielo_id, ":white_small_square:");

            lastSeenDate = Calendar.getInstance();
            lastSeenDate.add(Calendar.MINUTE, 10);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
