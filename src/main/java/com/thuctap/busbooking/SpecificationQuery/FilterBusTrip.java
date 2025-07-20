package com.thuctap.busbooking.SpecificationQuery;

import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusTrip;
import com.thuctap.busbooking.entity.User;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterBusTrip {

    public static Specification<BusTrip> filterBusTrip(Integer id, Integer busRouteId, LocalDateTime departureTime, Integer busId, Integer driverId, Integer status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo ID
            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            // Lọc theo tuyến xe (busRouteId)
            if (busRouteId != null) {
                Join<BusTrip, BusRoute> busRouteJoin = root.join("busRoute", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(busRouteJoin.get("id"), busRouteId));
            }

            // Lọc theo thời gian khởi hành (departureTime)
            if (departureTime != null) {
                predicates.add(criteriaBuilder.equal(root.get("departureTime"), departureTime));
            }

            // Lọc theo xe (busId)
            if (busId != null) {
                Join<BusTrip, Bus> busJoin = root.join("bus", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(busJoin.get("id"), busId));
            }

            // Lọc theo tài xế (driverId)
            if (driverId != null) {
                Join<BusTrip, User> driverJoin = root.join("user", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(driverJoin.get("id"), driverId));
            }

            // Lọc theo trạng thái
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            // Kết hợp tất cả các điều kiện bằng AND
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}