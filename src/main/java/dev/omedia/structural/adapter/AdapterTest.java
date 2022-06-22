package dev.omedia.structural.adapter;

public class AdapterTest {
    public static void test() {
        Company company = new Company("31456", "Rustaveli av.");
        LegalEntityPrinter.printLegalEntity(new CompanyAdapter(company));
    }
}
