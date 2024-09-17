CREATE TABLE credit_card (
    card_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    card_holder_full_name VARCHAR(150) NOT NULL,
    card_holder_cpf VARCHAR(20) NOT NULL,
    credit_card_number VARCHAR(20) NOT NULL,
    expiration_date VARCHAR(10) NOT NULL,
    cvv_code VARCHAR(3) NOT NULL,
    user_id UUID UNIQUE NOT NULL,

    CONSTRAINT fk_credit_card_user
    FOREIGN KEY (user_id)
    REFERENCES users (user_id) ON DELETE CASCADE
);
