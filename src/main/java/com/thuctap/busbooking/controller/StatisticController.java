package com.thuctap.busbooking.controller;

import com.thuctap.busbooking.dto.request.TimeRangeRequest;
import com.thuctap.busbooking.dto.response.*;
import com.thuctap.busbooking.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticController {

    BusRepository busRepository;
    BusRouteRepository busRouteRepository;
    BusTripRepository busTripRepository;
    InvoiceRepository invoiceRepository;
    BusStationRepository busStationRepository;

    @GetMapping("/dashboard")
    public ApiResponse<StatisticsResponse> getStatistics() {
        long totalBus = busRepository.countByStatus(1);
        long totalBusRoute = busRouteRepository.countByStatus(1);
        long totalBusTrip = busTripRepository.countByStatus(1);
        long totalBusStation = busStationRepository.countByStatus(1);

        StatisticsResponse response = new StatisticsResponse(
                totalBus,
                totalBusRoute,
                totalBusTrip,
                totalBusStation
        );

        return ApiResponse.<StatisticsResponse>builder()
                .result(response)
                .message("Lấy thống kê thành công")
                .code(1000)
                .build();
    }

    @PostMapping("/dashboard/revenue")
    public ApiResponse<RevenueStatisticResponse> getRevenueStatistics(@RequestBody TimeRangeRequest request) {
        LocalDateTime from = request.getFromDate();
        LocalDateTime to = request.getToDate();

        Float totalRevenue = invoiceRepository.sumTotalAmountByTripDepartureTimeBetween(from, to);
        if (totalRevenue == null) totalRevenue = 0f;

        Integer totalCost = busTripRepository.sumPriceByDepartureTimeBetween(from, to);
        if (totalCost == null) totalCost = 0;

        RevenueStatisticResponse result = new RevenueStatisticResponse(totalRevenue, totalCost);

        return ApiResponse.<RevenueStatisticResponse>builder()
                .code(1000)
                .message("Thống kê tài chính thành công")
                .result(result)
                .build();
    }

    @PostMapping("/dashboard/monthly-revenue")
    public ApiResponse<List<MonthlyRevenueResponse>> getMonthlyRevenueStatistics(@RequestBody TimeRangeRequest request) {
        LocalDateTime from = request.getFromDate();
        LocalDateTime to = request.getToDate();

        List<MonthlyRevenueResponse> monthlyRevenueList = invoiceRepository.findMonthlyRevenueBetween(from, to);

        return ApiResponse.<List<MonthlyRevenueResponse>>builder()
                .code(1000)
                .message("Thống kê doanh thu theo tháng thành công")
                .result(monthlyRevenueList)
                .build();
    }

    @PostMapping("/dashboard/cost-summary")
    public ApiResponse<CostSummaryResponse> getCostSummary(@RequestBody TimeRangeRequest request) {
        LocalDateTime from = request.getFromDate();
        LocalDateTime to = request.getToDate();

        Float costOperating = busTripRepository.sumCostOperatingBetween(from, to);
        Float costIncurred = busTripRepository.sumCostIncurredBetween(from, to);

        if (costOperating == null) costOperating = 0f;
        if (costIncurred == null) costIncurred = 0f;

        CostSummaryResponse response = CostSummaryResponse.builder()
                .costOperating(costOperating)
                .costIncurred(costIncurred)
                .totalCost(costOperating + costIncurred)
                .build();

        return ApiResponse.<CostSummaryResponse>builder()
                .result(response)
                .message("Lấy thống kê chi phí thành công")
                .code(1000)
                .build();
    }

    @PostMapping("/dashboard/monthly-finance")
    public ApiResponse<List<MonthlyFinanceResponse>> getMonthlyFinance(@RequestBody TimeRangeRequest request) {
        LocalDateTime from = request.getFromDate();
        LocalDateTime to = request.getToDate();

        List<MonthlyFinanceResponse> data = invoiceRepository.getMonthlyFinance(from, to);

        return ApiResponse.<List<MonthlyFinanceResponse>>builder()
                .code(1000)
                .message("Lấy bảng tài chính theo tháng thành công")
                .result(data)
                .build();
    }
}
