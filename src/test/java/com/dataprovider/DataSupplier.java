package com.dataprovider;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class DataSupplier {
    Faker faker = new Faker();

    @DataProvider(name = "loginTestData")
    public String[] loginData() {
        String[] data = new String[2];
        data[0] = faker.name().fullName();
        data[1] = faker.name().fullName();

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
