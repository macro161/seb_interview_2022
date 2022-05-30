package com.pentathlon.seb.util;

import com.pentathlon.seb.domain.Athlete;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CsvUtil {

    // Stolen code from StackOverflow
    public static List<Athlete> readAthletesFromCSV() {
        List<Athlete> Athletes = new ArrayList<>();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("Athlete_Results.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {

            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");
                Athlete Athlete = createAthlete(attributes);
                Athletes.add(Athlete);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return Athletes;
    }

    // Stolen code from StackOverflow
    private static Athlete createAthlete(String[] metadata) {
        Athlete athlete = new Athlete();

        athlete.setName(metadata[0]);
        athlete.setFencingVictories(Integer.parseInt(metadata[1]));
        athlete.setSwimmingTime(Float.valueOf(metadata[2].replace(":", "")));
        athlete.setRidingKnockingDown(Integer.parseInt(metadata[3]));
        athlete.setRidingRefusal(Integer.parseInt(metadata[4]));
        athlete.setRidingDisobedienceLeading(Integer.parseInt(metadata[5]));
        athlete.setShootingTargetScore(Integer.parseInt(metadata[6]));
        athlete.setRunTime(Float.valueOf(metadata[7].replace(":", "")));

        return athlete;
    }
    
}
