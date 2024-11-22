package com.dataprovider;

import org.testng.annotations.DataProvider;

public class DataSupplier {

    @DataProvider(name = "loginTestData")
    public String[] loginData() {
        String[] data = new String[2];
        data[0] = "Admin";
        data[1] = "Admin1";

        return data;
    }

    @DataProvider(name = "elementTypeTestdata")
    public String[] getElementTypeData() {
        String[] data = new String[5];
        data[0] = "Button";
        data[1] = "Input";
        data[2] = "Textarea";
        data[3] = "Select";
        data[4] = "Label";
        return data;
    }


}
