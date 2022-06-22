package dev.omedia.structural.adapter;

public class CompanyAdapter extends LegalEntity{

    public CompanyAdapter(Company company) {
        super(company.getCompanyCode());
    }

}
