package com.ead.payment.domain.repositories;

import com.ead.payment.domain.models.PaymentModel;
import com.ead.payment.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> {

//    Optional<PaymentModel> findTopByUserOrderByPaymentRequestDateDesc(UUID userId);

    Optional<PaymentModel> findTopByUserOrderByPaymentRequestDateDesc(UserModel userModel);

}
