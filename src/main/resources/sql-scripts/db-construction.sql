CREATE TABLE public.et_user
(
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    email character varying(30) NOT NULL,
    CONSTRAINT pk_user_id PRIMARY KEY (id),
    CONSTRAINT unique_user_name UNIQUE (name),
    CONSTRAINT unique_email_name UNIQUE (email)
)
WITH (
    OIDS = FALSE
);

CREATE TABLE public.et_seat
(
    id integer NOT NULL,
    number character varying(3) NOT NULL,
    user_id integer,
    price numeric(9, 2) NOT NULL DEFAULT 0.00,
    CONSTRAINT pk_seat_id PRIMARY KEY (id),
    CONSTRAINT unique_seat_number UNIQUE (number),
    CONSTRAINT fk_seat_user_id FOREIGN KEY (user_id)
        REFERENCES public.et_user (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
WITH (
    OIDS = FALSE
);

CREATE SEQUENCE public.et_user_seq
    INCREMENT 1
    START 1
;

CREATE SEQUENCE public.et_seat_seq
    INCREMENT 1
    START 1
;

INSERT INTO et_user VALUES ((SELECT nextval('et_user_seq')), 'ehfco', 'ehfco@github.com');
INSERT INTO et_user VALUES ((SELECT nextval('et_user_seq')), 'johndoe', 'johndoe@somemail.com');

INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '1a', null, 100);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '1b', null, 100);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '1c', null, 100);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '1d', null, 100);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '2a', null, 75.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '2b', null, 75.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '2c', null, 75.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '2d', null, 75.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '3a', null, 51.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '3b', null, 51.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '3c', null, 51.99);
INSERT INTO et_seat VALUES ((SELECT nextval('et_seat_seq')), '3d', null, 51.99);