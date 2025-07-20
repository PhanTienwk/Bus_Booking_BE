package com.thuctap.busbooking.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RevenueStatisticResponse {

    private float totalRevenue;
    private int totalCost;
    private float profit;

    public RevenueStatisticResponse(float totalRevenue, int totalCost) {
        this.totalRevenue = totalRevenue;
        this.totalCost = totalCost;
        this.profit = totalRevenue - totalCost;
    }

    public float getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }
}
