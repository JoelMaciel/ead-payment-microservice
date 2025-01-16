package com.ead.payment.domain.repositories;

import com.ead.payment.domain.models.CreditCardModel;
import com.ead.payment.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardModel, UUID> {

    Optional<CreditCardModel> findByUser(UserModel userModel);
}
