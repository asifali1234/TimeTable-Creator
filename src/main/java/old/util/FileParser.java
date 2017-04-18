package old.util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser implements Closeable {

    private BufferedReader bufferedReader;

    public FileParser(String name) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(name));
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
    }

    public String[] parse(String patternString) throws IOException {
        String line;
        Pattern pattern = Pattern.compile(patternString);
        while ((line = bufferedReader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String[] match = new String[matcher.groupCount()];
                for (int i = 0; i < match.length; i++) {
                    match[i] = matcher.group(i + 1);
                }
                return match;
            }
        }

        return null;
    }

}
