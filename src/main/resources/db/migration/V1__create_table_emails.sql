
CREATE TABLE tb_emails (
                           id UUID PRIMARY KEY,
                           email_to VARCHAR(255) NOT NULL,
                           subject VARCHAR(255) NOT NULL,
                           body TEXT NOT NULL,
                           send_date_time TIMESTAMP NOT NULL,
                           status_email VARCHAR(20) NOT NULL
)