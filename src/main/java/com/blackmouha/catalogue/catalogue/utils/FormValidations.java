package com.blackmouha.catalogue.catalogue.utils;

public class FormValidations {
    private String referenceError;
    private String designationError;
    private String priceError;
    private String quantityError;

    public FormValidations() {
        this.referenceError = null;
        this.designationError = null;
        this.priceError = null;
        this.quantityError = null;
    }

    public FormValidations(String referenceError, String designationError, String priceError, String quantityError) {
        this.referenceError = referenceError;
        this.designationError = designationError;
        this.priceError = priceError;
        this.quantityError = quantityError;
    }

    public String getReferenceError() {
        return referenceError;
    }

    public void setReferenceError(String referenceError) {
        this.referenceError = referenceError;
    }

    public String getDesignationError() {
        return designationError;
    }

    public void setDesignationError(String designationError) {
        this.designationError = designationError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    @Override
    public String toString() {
        if (referenceError == null && designationError == null && priceError == null && quantityError == null) {
            return "";
        }
        return '{' +
            "\"reference\": \"" + referenceError + '"' +
            ", \"designation\": \"" + designationError + '"' +
            ", \"price\": \"" + priceError + '"' +
            ", \"quantity\": " + quantityError + '"' +
            '}';
    }
}
