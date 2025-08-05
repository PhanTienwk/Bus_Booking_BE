package com.thuctap.busbooking.repository;

import com.thuctap.busbooking.dto.response.MonthlyFinanceResponse;
import com.thuctap.busbooking.dto.response.MonthlyRevenueResponse;
import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thuctap.busbooking.entity.Invoice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT SUM(i.totalAmount) FROM Invoice i WHERE i.status = :status")
    Float sumTotalAmountByStatus(@Param("status") int status);

    Invoice findById(int integer);

    @Query("SELECT SUM(i.totalAmount) FROM Invoice i WHERE i.busTrip.departureTime BETWEEN :start AND :end AND i.status = 3")
    Float sumTotalAmountByTripDepartureTimeBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT new com.thuctap.busbooking.dto.response.MonthlyRevenueResponse(" +
            "CAST(FUNCTION('DATE_FORMAT', b.departureTime, '%Y-%m') AS string), " +
            "SUM(i.totalAmount)) " +
            "FROM Invoice i " +
            "JOIN i.busTrip b " +
            "WHERE b.departureTime BETWEEN :start AND :end AND i.status = 3 " +
            "GROUP BY FUNCTION('DATE_FORMAT', b.departureTime, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', b.departureTime, '%Y-%m')")
    List<MonthlyRevenueResponse> findMonthlyRevenueBetween(@Param("start") LocalDateTime start,
                                                           @Param("end") LocalDateTime end);

    @Query("SELECT new com.thuctap.busbooking.dto.response.MonthlyFinanceResponse(" +
            "CONCAT('', FUNCTION('DATE_FORMAT', b.departureTime, '%Y-%m')), " +
            "COUNT(b.id), " +
            "SUM(i.totalAmount), " +
            "SUM(DISTINCT b.costOperating + b.costIncurred), " +
            "SUM(i.totalAmount) - SUM(DISTINCT b.costOperating + b.costIncurred)) " +
            "FROM Invoice i " +
            "JOIN i.busTrip b " +
            "WHERE b.departureTime BETWEEN :start AND :end AND i.status = 3 " +
            "GROUP BY FUNCTION('DATE_FORMAT', b.departureTime, '%Y-%m') " +
            "ORDER BY FUNCTION('DATE_FORMAT', b.departureTime, '%Y-%m')")
    List<MonthlyFinanceResponse> getMonthlyFinance(@Param("start") LocalDateTime start,
                                                   @Param("end") LocalDateTime end);

    List<Invoice> findByBusTripId(Integer tripId);

    List<Invoice> findByUserId(Integer id);
}
