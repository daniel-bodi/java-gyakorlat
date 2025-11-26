package hu.nje.java.gfekak.java.gyakorlat.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author danielbodi
 */
@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String> {
}
