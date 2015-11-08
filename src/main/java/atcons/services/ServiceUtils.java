package atcons.services;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Stateless
public class ServiceUtils {

    public String getContentFromResource(String url) throws IOException {
        URL resource = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(resource.openStream()));

        StringBuilder builder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            builder.append(inputLine);
        in.close();
        return builder.toString();
    }
}
