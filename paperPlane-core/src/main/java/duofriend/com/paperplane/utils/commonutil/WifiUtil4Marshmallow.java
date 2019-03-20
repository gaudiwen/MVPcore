package duofriend.com.paperplane.utils.commonutil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lgx on 2018/7/21 0021.
 */

public class WifiUtil4Marshmallow {
    public static String getwifiMacAddress() {

        try {
            return loadFileAsString("/sys/class/net/wlan0/address")
                    .toUpperCase().substring(0, 17);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String loadFileAsString(String filePath)
            throws IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
}
