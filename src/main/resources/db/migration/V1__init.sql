CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);
CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role VARCHAR(50) NOT NULL,
                            PRIMARY KEY (user_id, role),
                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE achievements (
                              id SERIAL PRIMARY KEY,
                              title VARCHAR(100) NOT NULL,
                              description TEXT NOT NULL,
                              points INT NOT NULL
);


CREATE TABLE user_achievements (
                                   id SERIAL PRIMARY KEY,
                                   user_id BIGINT NOT NULL,
                                   achievement_id BIGINT NOT NULL,
                                   date_achieved TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   PRIMARY KEY (user_id, achievement_id),
                                   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                   FOREIGN KEY (achievement_id) REFERENCES achievements(id) ON DELETE CASCADE
);
CREATE TABLE player_stats (
                              id SERIAL PRIMARY KEY,
                              user_id BIGINT NOT NULL UNIQUE,
                              total_points INT DEFAULT 0,
                              total_achievements INT DEFAULT 0,
                              ranking INT DEFAULT 0,
                              FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
