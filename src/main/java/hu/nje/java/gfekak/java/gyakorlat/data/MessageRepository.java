package hu.nje.java.gfekak.java.gyakorlat.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author danielbodi
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByOrderBySentAtDesc();
}
