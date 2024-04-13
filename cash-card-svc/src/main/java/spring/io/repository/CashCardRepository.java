package spring.io.repository;

import org.springframework.data.repository.CrudRepository;
import spring.io.model.CashCard;

public interface CashCardRepository extends CrudRepository<CashCard, Long> {
}
