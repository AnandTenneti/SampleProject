package com.dataprovider;

public class TestData {

    public static final String USER_DIR = System.getProperty("user.dir");
    public static final String PATH_TO_TEST_FILES_DIR = "/src/test/resources/testfiles/";
    public static final String PATH_TO_CONFIG_FILE = "/src/main/resources/config.properties";
    public static final String PATH_TO_EXCEL_FILE = "/src/test/resources/login.xlsx";

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
        WAIT_FOR_3S(3),
        WAIT_FOR_5S(5),
        WAIT_FOR_10S(10);

        private int waitingTime;

        WaitTime(int waitingTime) {
            this.waitingTime = waitingTime;
        }

        public int getWaitTime() {
            return waitingTime;
        }
    }
}
