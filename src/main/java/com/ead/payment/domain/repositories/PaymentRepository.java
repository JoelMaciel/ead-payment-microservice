package com.ead.payment.domain.repositories;

import com.ead.payment.domain.models.PaymentModel;
import com.ead.payment.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel, UUID>, JpaSpecificationExecutor<PaymentModel> {

    Optional<PaymentModel> findTopByUserOrderByPaymentRequestDateDesc(UserModel userModel);

    @Query(value = "select * from payment where user_id = :userId and payment_id = :paymentId", nativeQuery = true)
    Optional<PaymentModel> findPaymentByUser(@Param("userId") UUID userId, @Param("paymentId") UUID paymentId);
}
