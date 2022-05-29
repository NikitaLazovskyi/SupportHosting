package entity;

import java.time.LocalDate;

public class QueryEntity {
    private Integer serviceId;
    private Integer variationId;
    private Integer questionTypeId;
    private Integer categoryId;
    private Integer subCategoryId;
    private String pn;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public QueryEntity(Integer serviceId, Integer variationId, Integer questionTypeId, Integer categoryId, Integer subCategoryId, String pn, LocalDate dateFrom, LocalDate dateTo) {
        this.serviceId = serviceId;
        this.variationId = variationId;
        this.questionTypeId = questionTypeId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.pn = pn;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public boolean filter(Reply reply){

        boolean areServicesMatch = serviceId == null || reply.getServiceId().equals(serviceId);

        boolean areVariationsMatch;
        if (serviceId == null){
            areVariationsMatch = true;
        }else{
            areVariationsMatch = variationId == null || (reply.getVariationId() != null && reply.getVariationId().equals(variationId));
        }

        boolean areQuestionTypesMatch = questionTypeId == null || reply.getQuestionTypeId().equals(questionTypeId);

        boolean areCategoriesMatch;
        if (questionTypeId == null){
            areCategoriesMatch = true;
        }else{
            areCategoriesMatch = categoryId == null || (reply.getCategoryId() != null && reply.getCategoryId().equals(categoryId));
        }

        boolean areSubCategoriesMatch;
        if (questionTypeId == null){
            areSubCategoriesMatch = true;
        }else {
            areSubCategoriesMatch = subCategoryId == null || (reply.getSubCategoryId() != null &&  reply.getSubCategoryId().equals(subCategoryId));
        }

        boolean arePNMatch = pn.equals(reply.getPn());

        boolean areDateMatch = (reply.getDate().isEqual(dateFrom) || reply.getDate().isAfter(dateFrom)) && reply.getDate().isBefore(dateTo);

        return areServicesMatch
                && areVariationsMatch
                && areQuestionTypesMatch
                && areCategoriesMatch
                && areSubCategoriesMatch
                && arePNMatch
                && areDateMatch;
    }

    @Override
    public String toString() {
        return "ReplyDto{" +
                "serviceId=" + serviceId +
                ", variationId=" + variationId +
                ", questionTypeId=" + questionTypeId +
                ", categoryId=" + categoryId +
                ", subCategoryId=" + subCategoryId +
                ", pn='" + pn + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Integer getVariationId() {
        return variationId;
    }

    public void setVariationId(Integer variationId) {
        this.variationId = variationId;
    }

    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }


}
