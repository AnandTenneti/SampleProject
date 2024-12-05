package com.dataprovider;

public class TestData {
    public enum ElementType {
        INPUT("Input"),
        BUTTON("Button"),
        SELECT("Select"),
        TEXTAREA("Textarea"),
        LABEL("Label");

        private String description;

        ElementType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
