package com.techelevator.controller;

import com.techelevator.dao.CardDao;
import com.techelevator.dao.CollectionDao;
import com.techelevator.model.Card;
import com.techelevator.model.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionController {

    @Autowired
    private CollectionDao collectionDao;


    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = collectionDao.getAllCards();
        return ResponseEntity.ok(cards);
    }

    @GetMapping("")
    public ResponseEntity<List<Collection>> getAllCollections() {
        List<Collection> collections = collectionDao.getAllCollections();
        return ResponseEntity.ok(collections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Card>> getAllCardsByCollectionId(@PathVariable("id") int collectionId) {
        List<Card> cards = collectionDao.getAllCardsByCollection(collectionId);

        if(cards == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(cards);
        }
    }



}
