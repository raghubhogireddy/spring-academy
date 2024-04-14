package spring.io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import spring.io.model.CashCard;

public interface CashCardRepository extends CrudRepository<CashCard, Long>, PagingAndSortingRepository<CashCard, Long> {
    Page<CashCard> findByOwner(String name, PageRequest amount);

    CashCard findByIdAndOwner(Long requestedId, String name);
}
