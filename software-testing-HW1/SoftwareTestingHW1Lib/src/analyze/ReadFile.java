package analyze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static String readFile(String filePath) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            String line;

            while ((line = buffer.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return 	stringBuilder.toString();                    
        }
    }
}
