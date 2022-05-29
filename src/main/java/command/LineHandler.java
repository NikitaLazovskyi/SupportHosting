package command;

import entity.Reply;

import java.util.Collection;

public interface LineHandler {
    String handle(Collection<Reply> replies, String[] split);
}
