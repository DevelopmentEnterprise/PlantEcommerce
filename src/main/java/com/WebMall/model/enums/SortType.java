package com.WebMall.model.enums;

public enum SortType {
    POPULARITY("popularity"),
    RATING("rating"),
    PRICE("price"),
    DISCOUNT("discount"),
    NAME("name");

    private final String value;

    SortType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SortType fromString(String textValue){
        for(SortType sortType : SortType.values()){
            if (sortType.value.equalsIgnoreCase(textValue)){
                return sortType;
            }
        }

        return null;
    }
}
