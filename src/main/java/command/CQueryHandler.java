package command;

import entity.Reply;
import exception.InvalidQueryException;
import util.Converter;
import util.Validation;

import java.time.LocalDate;
import java.util.Collection;

public class CQueryHandler implements LineHandler{
    @Override
    public String handle(Collection<Reply> replies, String[] split) {
        if (!Validation.validateServiceAndQuestionTypes(split[1])) {
            throw new InvalidQueryException("invalid service id: " + split[1]);
        }
        String[] temp = split[1].split("[.]");
        Integer serviceId = Integer.valueOf(temp[0]);
        Integer variationId = temp.length > 1 ? Integer.valueOf(temp[1]) : null;


        if (!Validation.validateServiceAndQuestionTypes(split[2])) {
            throw new InvalidQueryException("invalid question type id: " + split[2]);
        }
        temp = split[2].split("[.]");
        Integer questionTypeId = Integer.valueOf(temp[0]);
        Integer categoryId = temp.length > 1 ? Integer.valueOf(temp[1]) : null;
        Integer subCategoryId = temp.length > 2 ? Integer.valueOf(temp[2]) : null;

        if (!Validation.validateType(split[3])) {
            throw new InvalidQueryException("invalid answer type id: " + split[3]);
        }
        String pn = split[3];

        if (!Validation.validateDate(split[4])) {
            throw new InvalidQueryException("invalid date: " + split[4]);
        }
        LocalDate date = Converter.convertDate(split[4]);

        Integer minutes = Integer.valueOf(split[5]);

        Reply reply = new Reply(serviceId, variationId, questionTypeId, categoryId, subCategoryId, pn, date, minutes);
        replies.add(reply);
        return null;
    }
}
