BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE card (
    card_id int,
    card_name varchar(100) NOT NULL,
    manacost varchar(20) NOT NULL,
    colors varchar(10) NOT NULL,
    coloridenitity varchar(10) NOT NULL,
    type varchar(50),
    subtype varchar(50),
    rarity varchar(20),
    set varchar(20),
    set_name varchar(50),
    text varchar(250),
    artist varchar(100),
    image BYTEA
)

CREATE TABLE collection (
    collection_id SERIAL,
    collection_name varchar(100) NOT NULL,
    CONSTRAINT user_id
        FOREIGN KEY(user_id)
            REFERENCES users(user_id),
    cards card[]
);

COMMIT TRANSACTION;
