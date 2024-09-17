package com.ead.payment.domain.repositories;

import com.ead.payment.domain.models.CreditCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardModel, UUID> {
}
