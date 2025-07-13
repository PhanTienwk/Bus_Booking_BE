package com.thuctap.busbooking.SpecificationQuery;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Province;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

import java.util.ArrayList;
import java.util.List;
public class FilterBusStation {

    public static Specification<BusStation> filterBusStation(Integer id, String name, String address, String phone, Integer provinceId, Integer status) {
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

            // Lọc theo địa chỉ (khớp một phần, không phân biệt hoa thường)
            if (address != null && !address.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("address")),
                        "%" + address.trim().toLowerCase() + "%"
                ));
            }

            // Lọc theo số điện thoại (khớp một phần)
            if (phone != null && !phone.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get("phone"),
                        "%" + phone.trim() + "%"
                ));
            }

            // Lọc theo tỉnh/thành phố
            if (provinceId != null) {
                Join<BusStation, Province> provinceJoin = root.join("province", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(provinceJoin.get("id"), provinceId));
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
