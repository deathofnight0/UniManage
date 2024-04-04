import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class pincode {
    public static String[] find(String pin) {
        String[] str = new String[2];
        try {
            URL url = new URL("https://api.postalpincode.in/pincode/" + pin);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String json = response.toString();

                json = json.substring(1, json.length() - 1);
                String city = json.split("\"District\":\"")[1].split("\"")[0];
                String state = json.split("\"State\":\"")[1].split("\"")[0];
                str[0] = city;
                str[1] = state;
            } else {
                str[0] = null;
            }
            conn.disconnect();
        } catch (Exception e) {
            System.out.println("Pincode incorrect");
        }
        return str;

    }
}
