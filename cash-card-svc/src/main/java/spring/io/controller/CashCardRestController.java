package spring.io.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.io.model.CashCard;
import spring.io.repository.CashCardRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardRestController {

    private CashCardRepository repository;

    public CashCardRestController(CashCardRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/{requestId}")
    private ResponseEntity<CashCard> findCashCard(@PathVariable Long requestId) {
        Optional<CashCard> cashCard = repository.findById(requestId);
        return cashCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody CashCard card, UriComponentsBuilder ucb) {
        CashCard savedCard = repository.save(card);
        URI location = ucb.path("/cashcards/{id}").buildAndExpand(savedCard.id()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    private ResponseEntity<Iterable<CashCard>> findAllCashCards() {
        return ResponseEntity.ok(repository.findAll());
    }

}