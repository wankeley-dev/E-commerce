package com.example.Learn.WaterDeliveryApp.Repository;

        import com.example.Learn.WaterDeliveryApp.Entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
