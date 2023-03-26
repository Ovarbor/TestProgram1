import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class FilesReader {

    public String readFileContents(String path)
    {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            skipFirstLine(bufferedReader);
            return bufferedReader.lines().collect(Collectors.joining("\r"));
        } catch (IOException e) {
            System.out.println("Невозможно считать файл. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }

    private void skipFirstLine(BufferedReader reader){
        for (int i = 0; i < 1; i++) {
            try {
                reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
