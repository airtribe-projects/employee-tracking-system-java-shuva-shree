package com.airtribe.EmployeeTrackingSystem.repo;

import com.airtribe.EmployeeTrackingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public default User findByEmail(String email) {
        return null;
    }
}
