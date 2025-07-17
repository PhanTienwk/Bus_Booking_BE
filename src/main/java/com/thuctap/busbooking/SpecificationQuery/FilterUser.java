package com.thuctap.busbooking.SpecificationQuery;

import com.thuctap.busbooking.entity.Account;
import com.thuctap.busbooking.entity.Role;
import com.thuctap.busbooking.entity.User;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterUser {

    public static Specification<User> filterUsers(String name, Integer gender, LocalDateTime birthday, String phone, String email, Integer status, Integer roleId) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (roleId != null) {
                Join<User, Account> accountJoin = root.join("account", JoinType.LEFT);
                Join<Account, Role> roleJoin = accountJoin.join("role", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(roleJoin.get("id"), roleId));
            }

            if (name != null && !name.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.trim().toLowerCase() + "%"
                ));
            }

            if (gender != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }

            if (birthday != null) {
                LocalDateTime startOfDay = birthday.toLocalDate().atStartOfDay();
                LocalDateTime endOfDay = startOfDay.plusDays(1);

                predicates.add(criteriaBuilder.between(
                        root.get("birthDate"), startOfDay, endOfDay
                ));
            }

            if (phone != null && !phone.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                        root.get("phone"),
                        "%" + phone.trim() + "%"
                ));
            }

            if (email != null && !email.trim().isEmpty()) {
                Join<Object, Object> accountJoin = root.join("account", JoinType.LEFT);
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(accountJoin.get("email")),
                        "%" + email.trim().toLowerCase() + "%"
                ));
            }

            if (status != null) {
                Join<Object, Object> accountJoin = root.join("account", JoinType.LEFT);
                predicates.add(criteriaBuilder.equal(accountJoin.get("status"), status));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
