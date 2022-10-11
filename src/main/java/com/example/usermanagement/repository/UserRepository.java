package com.example.usermanagement.repository;

import com.example.usermanagement.entity.User;
import com.example.usermanagement.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Page<User> findAll(Specification<User> userSpecification, Pageable pageable);

    List<User> findAllByRoleAndActiveTrue(Role role);
    Optional<User> findByIdAndActiveTrue(String id);
    Optional<User> findByUsernameAndActiveTrue(String username);
    User findAllByUsernameAndActiveTrue(String username);
}
