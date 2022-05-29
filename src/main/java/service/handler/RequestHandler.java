package service.handler;

import command.CQueryHandler;
import command.DQueryHandler;
import command.LineHandler;
import entity.Reply;
import exception.InvalidQueryException;

import java.io.IOException;
import java.util.*;

public class RequestHandler {

    private final List<Reply> replies;
    private final Map<String, LineHandler> handlers;

    public RequestHandler() {
        replies = new ArrayList<>();
        handlers = new HashMap<>();
        init();
    }

    private void init() {
        handlers.put("C", new CQueryHandler());
        handlers.put("D", new DQueryHandler());
    }

    public List<String> handle(String[] requests) throws IOException {
        List<String> res = new ArrayList<>();
        for (String request : requests) {
            String operate = operate(request);
            if (operate != null) {
                res.add(operate);
            }
        }
        return res;
    }


    private String operate(String line) {
        String[] split = line.split(" ");
        String res = null;
        String command = split[0];
        if (handlers.containsKey(command.toUpperCase(Locale.ROOT))) {
            res = handlers.get(command).handle(replies, split);
        }
        return res;
    }
}
