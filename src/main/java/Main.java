import service.handler.FileHandler;
import service.handler.RequestHandler;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileHandler handler = new FileHandler("requests.txt", "output.txt");
        RequestHandler requestHandler = new RequestHandler();

        String[] read = handler.read();

        List<String> handled = requestHandler.handle(read);

        handler.write(handled);
    }
}

