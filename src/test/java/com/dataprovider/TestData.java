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

    public enum WaitTime {
        WAIT_FOR_3S(3000),
        WAIT_FOR_5S(5000),
        WAIT_FOR_10S(10000);

        private int waitingTime;

        WaitTime(int waitingTime) {
            this.waitingTime = waitingTime;
        }

        public int getWaitTime() {
            return waitingTime;
        }
    }
}
