package com.thuctap.busbooking.dto.response;

public class MonthlyFinanceResponse {
    private String month;
    private Long totalTrips;
    private Double revenue;
    private Double cost;
    private Double profit;

    public MonthlyFinanceResponse(String month, Long totalTrips, Double revenue, Double cost, Double profit) {
        this.month = month;
        this.totalTrips = totalTrips;
        this.revenue = revenue;
        this.cost = cost;
        this.profit = profit;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getTotalTrips() {
        return totalTrips;
    }

    public void setTotalTrips(Long totalTrips) {
        this.totalTrips = totalTrips;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
