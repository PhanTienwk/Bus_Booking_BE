package com.thuctap.busbooking.SpecificationQuery;

import com.thuctap.busbooking.entity.Bus;
import com.thuctap.busbooking.entity.BusType;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

import java.util.ArrayList;
import java.util.List;

public class FilterBus {

    public static Specification<Bus> filterBus(Integer id, String name, Integer busTypeId, Integer status) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Lọc theo ID
            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            // Lọc theo tên (khớp một phần, không phân biệt hoa thường)
            if (name != null && !name.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.trim().toLowerCase() + "%"
                ));
            }

            // Lọc theo loại xe (busTypeId)
            if (busTypeId != null) {
                Join<Bus, BusType> busTypeJoin = root.join("busType", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(busTypeJoin.get("id"), busTypeId));
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