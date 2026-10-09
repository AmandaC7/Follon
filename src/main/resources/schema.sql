CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    skills TEXT,
    foto BYTEA,
    field_of_interest TEXT[]
);

CREATE TABLE IF NOT EXISTS hackathon (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    location VARCHAR(150),
    format VARCHAR(50),
    start_date DATE,
    end_date DATE,
    registration_start DATE,
    registration_end DATE,
    prize DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS badges (
    id BIGSERIAL PRIMARY KEY,
    hackathon_id BIGINT NOT NULL,
    foto BYTEA,
    CONSTRAINT fk_badges_hackathon
        FOREIGN KEY (hackathon_id) REFERENCES hackathon(id)
);

CREATE TABLE IF NOT EXISTS team (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    hackathon_id BIGINT NOT NULL,
    badge_id BIGINT,
    CONSTRAINT fk_team_hackathon
        FOREIGN KEY (hackathon_id) REFERENCES hackathon(id),
    CONSTRAINT fk_team_badge
        FOREIGN KEY (badge_id) REFERENCES badges(id)
);

CREATE TABLE IF NOT EXISTS participation (
    user_id BIGINT NOT NULL,
    hackathon_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, hackathon_id),
    CONSTRAINT fk_participation_user
        FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_participation_hackathon
        FOREIGN KEY (hackathon_id) REFERENCES hackathon(id)
);

CREATE TABLE IF NOT EXISTS team_request (
    team_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status VARCHAR(30) NOT NULL,
    PRIMARY KEY (team_id, user_id),
    CONSTRAINT fk_team_request_team
        FOREIGN KEY (team_id) REFERENCES team(id),
    CONSTRAINT fk_team_request_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS submission (
    id BIGSERIAL PRIMARY KEY,
    team_id BIGINT NOT NULL,
    project_name VARCHAR(150) NOT NULL,
    description TEXT,
    github VARCHAR(255),
    demo VARCHAR(255),
    CONSTRAINT fk_submission_team
        FOREIGN KEY (team_id) REFERENCES team(id)
);

CREATE TABLE IF NOT EXISTS evaluation (
    id BIGSERIAL PRIMARY KEY,
    submission_id BIGINT NOT NULL,
    judge_id BIGINT NOT NULL,
    innovation INTEGER,
    execution INTEGER,
    design INTEGER,
    impact INTEGER,
    feedback TEXT,
    CONSTRAINT fk_evaluation_submission
        FOREIGN KEY (submission_id) REFERENCES submission(id),
    CONSTRAINT fk_evaluation_judge
        FOREIGN KEY (judge_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS field_of_interest (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);