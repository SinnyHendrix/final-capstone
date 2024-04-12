package com.techelevator.dao;

import com.techelevator.model.Card;
import com.techelevator.model.Collection;

import java.util.List;

public interface CollectionDao {

    public List<Card> getAllCards();

    public List<Collection> getAllCollections();
}
