package util;

import domain.Load;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataReader {

    public static Scanner scanner = new Scanner(System.in);
    public static Properties prop = new Properties();

    public static List<Load> loadData(String com, String fileName) {

        List<Load> loads = new ArrayList<>();

        try {
            loads = readFile(fileName);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return loads;
    }

    public static List loadForces() {

        List forces = new ArrayList();

        Properties property = new Properties();

        try (InputStream inputStream = DataReader.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (inputStream == null) {
                System.out.println("Sorry, unable to find config.properties");
                return forces;
            }

            property.load(inputStream);
            String loadValues = property.getProperty("load");
            System.out.println(loadValues);
            String[] loads = loadValues.split(" ");
            for (String load : loads) {
                forces.add(load);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forces;
    }

    public static double calculateFriction(List<Load> data, int number, int load) throws IOException {

        Properties prop = new Properties();

        prop.load(DataReader.class.getClassLoader().getResourceAsStream("config.properties"));
        //Distance from rotation axis to sensor (mm)
        String disL = prop.getProperty("disL");
        double L = Double.valueOf(disL);
        //Distance from the axis of rotation to the middle of the ring (mm)
        String disR = prop.getProperty("disR");
        double R = Double.valueOf(disR);
        //Range of selection
        String range = prop.getProperty("range");
        int d = Integer.valueOf(range);
        //Coefficient of friction
        double f = 0;
        //average coefficient of friction

        Load ff = data.get(0);// to load correct data

        for (int i = 0; i < d; i++) {
            //Load value on the sensor (N)
            double F = data.get(number + i - ff.getId()).getLoad();
            //Coefficient of friction
            f = f + ((F * L * 9.81) / (load * R));
        }
        f = f / d;
        //Rounding to the third character
        double newDouble = new BigDecimal(f).setScale(3, RoundingMode.HALF_UP).doubleValue();

        return newDouble;
    }


    public static List<Load> readFile(String fileName) throws FileNotFoundException {

        List<Load> loads = new ArrayList<>();

        exists(fileName);


        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String newLine = line.replace(',', '.');
                //System.out.println(newLine);
                String[] divideLine = parserLine(newLine);

                Load load = new Load();

                load.setId(Integer.parseInt(divideLine[0].trim()));
                load.setLoad(Double.parseDouble(divideLine[1]));

                loads.add(load);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loads;
    }

    public static boolean writeFile(Map<Integer, Double> map, File file) {

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, true), StandardCharsets.UTF_8))) {

            for (Map.Entry<Integer, Double> entry : map.entrySet()) {
                //System.out.printf("%6d\\t f = %.3f\n", entry.getKey(), entry.getValue());
                String num = Double.toString(entry.getValue());
                String newLine = num.replace('.', ',');
                String str = String.format("%4d\t%s", entry.getKey(), newLine);
                writer.write(str);
                writer.write(System.lineSeparator());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }

    private static String[] parserLine(String newLine) {
        Pattern p = Pattern.compile("(?<key>\\d{1,5})[ |\\t]+(?<value>\\d{1}\\.\\d{3}).*");
        Matcher matcher = p.matcher(newLine);
        String[] strings = new String[2];
        while (matcher.find()) {
            strings[0] = matcher.group("key");
            // System.out.println(strings[0]);
            strings[1] = matcher.group("value");
            // System.out.println(strings[1]);
        }
        return strings;
    }


}
