package dev.omedia.structural.adapter;

public class Company {
    private final String companyCode;
    private final String address;

    public Company(String companyCode, String address) {
        this.companyCode = companyCode;
        this.address = address;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public String getAddress() {
        return address;
    }
}
