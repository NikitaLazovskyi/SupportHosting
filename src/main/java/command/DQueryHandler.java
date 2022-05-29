package command;

import entity.QueryEntity;
import entity.Reply;
import exception.InvalidQueryException;
import util.Converter;
import util.Validation;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DQueryHandler implements LineHandler {
    @Override
    public String handle(Collection<Reply> replies, String[] split) {
        if (!Validation.validateServiceAndQuestionTypes(split[1])) {
            throw new InvalidQueryException("invalid service id: " + split[1]);
        }
        String[] temp = split[1].split("[.]");
        Integer serviceId = temp[0].equals("*") ? null : Integer.valueOf(temp[0]);
        Integer variationId = temp.length > 1 ? Integer.valueOf(temp[1]) : null;

        if (!Validation.validateServiceAndQuestionTypes(split[2])) {
            throw new InvalidQueryException("invalid question type id: " + split[2]);
        }
        temp = split[2].split("[.]");
        Integer questionTypeId = temp[0].equals("*") ? null : Integer.valueOf(temp[0]);
        Integer categoryId = temp.length > 1 ? Integer.valueOf(temp[1]) : null;
        Integer subCategoryId = temp.length > 2 ? Integer.valueOf(temp[2]) : null;

        if (!Validation.validateType(split[3])) {
            throw new InvalidQueryException("invalid answer type id: " + split[3]);
        }
        String pn = split[3];


        temp = split[4].split("[-]");
        if (!Validation.validateDate(temp[0])) {
            throw new InvalidQueryException("invalid date: " + temp[0]);
        }
        LocalDate dateFrom = Converter.convertDate(temp[0]);
        LocalDate dateTo;
        if (temp.length > 1) {
            if (!Validation.validateDate(temp[1])) {
                throw new InvalidQueryException("invalid date: " + temp[1]);
            }
            dateTo = Converter.convertDate(temp[1]);
        }else{
            dateTo = LocalDate.now();
        }

        QueryEntity dto = new QueryEntity(serviceId, variationId, questionTypeId, categoryId, subCategoryId, pn, dateFrom, dateTo);

        List<Reply> rep = replies.stream().filter(
                dto::filter).collect(Collectors.toList());
        if (rep.size() == 0) {
            return "-";
        } else {
            Integer asDouble = (int) rep.stream().mapToInt(Reply::getMinutes).average().getAsDouble();
            return asDouble + "";
        }
    }
}
