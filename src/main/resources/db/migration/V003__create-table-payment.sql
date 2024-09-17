CREATE TABLE payment (
    payment_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payment_control VARCHAR(20) NOT NULL,
    payment_request_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    payment_completion_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    payment_expiration_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    last_digits_credit_card VARCHAR(4) NOT NULL,
    value_paid DECIMAL(10, 2) NOT NULL,
    payment_message VARCHAR(200),
    recurrence BOOLEAN NOT NULL,
    user_id UUID NOT NULL,

    CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id) ON DELETE CASCADE
);
