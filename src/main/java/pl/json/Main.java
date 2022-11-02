package pl.json;

import org.decimal4j.util.DoubleRounder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> strJson = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/messages.txt"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                strJson.add(line);
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (String line : strJson) {

                JSONParser parser = new JSONParser();
                Object object = parser.parse(line);
                JSONObject mainJsonObject = (JSONObject) object;

                //Object type = mainJsonObject.get("type");
                JSONObject jsonObjectProduct = (JSONObject) mainJsonObject.get("product");
                Object id = jsonObjectProduct.get("id");
                Object quantity = jsonObjectProduct.get("quantity");
                Object value = jsonObjectProduct.get("value");

                double ppi = (Double.parseDouble(value.toString())) / (Double.parseDouble(quantity.toString()));

                StringBuilder consoleOut = new StringBuilder();
                consoleOut
                        .append("Product ")
                        .append(id)
                        .append(" quantity: ")
                        .append(quantity)
                        .append(", value: ")
                        .append(value)
                        .append(", ppi: ")
                        .append(DoubleRounder.round(ppi, 5))
                        .toString();

                System.out.println(line);
                System.out.println(consoleOut);
                System.out.println();

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
