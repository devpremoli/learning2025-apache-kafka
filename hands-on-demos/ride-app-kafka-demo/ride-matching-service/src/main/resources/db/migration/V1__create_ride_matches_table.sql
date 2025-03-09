CREATE SEQUENCE ride_matches_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE ride_matches (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    driver_id VARCHAR(50) NOT NULL,
    pickup_location VARCHAR(255) NOT NULL,
    dropoff_location VARCHAR(255) NOT NULL,
    status VARCHAR(50),
    timestamp TIMESTAMP
);
