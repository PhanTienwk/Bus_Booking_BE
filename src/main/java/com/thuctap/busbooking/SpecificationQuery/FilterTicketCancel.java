package com.thuctap.busbooking.SpecificationQuery;

import com.thuctap.busbooking.entity.Ticket;
import com.thuctap.busbooking.entity.Invoice;
import com.thuctap.busbooking.entity.User;
import com.thuctap.busbooking.entity.SeatPosition;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.BankDetails;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterTicketCancel {

    public static Specification<Ticket> filterTickets(
            String name,
            String phone,
            String email,
            Integer status,
            String seatName,
            String bankAccountNumber,
            Double minAmount,
            Double maxAmount,
            String startTime,
            String endTime
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Join với Invoice để truy cập các trường liên quan
            Join<Ticket, Invoice> invoiceJoin = root.join("invoice", JoinType.INNER);

            // Lọc theo tên khách hàng (từ User thông qua Invoice)
            if (name != null && !name.trim().isEmpty()) {
                Join<Invoice, User> userJoin = invoiceJoin.join("user", JoinType.INNER);
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(userJoin.get("name")),
                        "%" + name.trim().toLowerCase() + "%"
                ));
            }

            // Lọc theo số điện thoại (từ Invoice)
            if (phone != null && !phone.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        invoiceJoin.get("phone"),
                        "%" + phone.trim() + "%"
                ));
            }

            // Lọc theo email (từ Invoice)
            if (email != null && !email.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(invoiceJoin.get("email")),
                        "%" + email.trim().toLowerCase() + "%"
                ));
            }

            // Lọc theo trạng thái (từ Ticket)
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            // Lọc theo tên ghế (từ SeatPosition)
            if (seatName != null && !seatName.trim().isEmpty()) {
                Join<Ticket, SeatPosition> seatJoin = root.join("seatPosition", JoinType.INNER);
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(seatJoin.get("name")),
                        "%" + seatName.trim().toLowerCase() + "%"
                ));
            }

            // Lọc theo số tài khoản ngân hàng (từ BankDetails)
            if (bankAccountNumber != null && !bankAccountNumber.trim().isEmpty()) {
                Join<Invoice, BankDetails> bankJoin = invoiceJoin.join("bank", JoinType.INNER);
                predicates.add(criteriaBuilder.like(
                        bankJoin.get("bankAccountNumber"),
                        "%" + bankAccountNumber.trim() + "%"
                ));
            }

            // Lọc theo khoản tiền tối thiểu (từ Invoice)
            if (minAmount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        invoiceJoin.get("totalAmount"),
                        minAmount
                ));
            }

            // Lọc theo khoản tiền tối đa (từ Invoice)
            if (maxAmount != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        invoiceJoin.get("totalAmount"),
                        maxAmount
                ));
            }

            // Lọc theo khoảng thời gian khởi hành (từ BusTrip)
            if (startTime != null && !startTime.trim().isEmpty()) {
                Join<Ticket, BusTrip> busTripJoin = root.join("busTrip", JoinType.INNER);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                        busTripJoin.get("departureTime"),
                        LocalDateTime.parse(startTime)
                ));
            }
            if (endTime != null && !endTime.trim().isEmpty()) {
                Join<Ticket, BusTrip> busTripJoin = root.join("busTrip", JoinType.INNER);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(
                        busTripJoin.get("departureTime"),
                        LocalDateTime.parse(endTime)
                ));
            }

            // Kết hợp tất cả các điều kiện bằng AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}