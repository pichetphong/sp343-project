package com.example.utcckitchen.services;

import com.example.utcckitchen.models.Customer;
import com.example.utcckitchen.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
}
