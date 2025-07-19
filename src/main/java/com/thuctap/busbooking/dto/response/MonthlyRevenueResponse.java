package com.thuctap.busbooking.dto.response;

public class MonthlyRevenueResponse {
    private String month;
    private Double totalRevenue;

    public MonthlyRevenueResponse(String month, Double totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}

