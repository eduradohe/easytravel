CREATE DATABASE easytravel
  WITH ENCODING='UTF8'
       CONNECTION LIMIT=-1;

CREATE TABLE public.user
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

ALTER TABLE public.user
    OWNER to postgres;

CREATE TABLE public.flight
(
    id integer NOT NULL,
    number character varying(7) NOT NULL,
    CONSTRAINT pk_flight_id PRIMARY KEY (id),
    CONSTRAINT unique_flight_number UNIQUE (number)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.flight
    OWNER to postgres;

CREATE TABLE public.seat
(
    id integer NOT NULL,
    number character varying(3) NOT NULL,
    flight_id integer NOT NULL,
    user_id integer NOT NULL,
    price numeric(9, 2) NOT NULL DEFAULT 0.00,
    CONSTRAINT pk_seat_id PRIMARY KEY (id),
    CONSTRAINT unique_seat_number UNIQUE (number),
    CONSTRAINT fk_seat_flight_id FOREIGN KEY (flight_id)
        REFERENCES public.flight (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT,
    CONSTRAINT fk_seat_user_id FOREIGN KEY (user_id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE RESTRICT
)
WITH (
    OIDS = FALSE
);

ALTER TABLE public.seat
    OWNER to postgres;

CREATE SEQUENCE public.user_seq
    INCREMENT 1
    START 1
;

ALTER SEQUENCE public.user_seq
    OWNER TO postgres;

CREATE SEQUENCE public.flight_seq
    INCREMENT 1
    START 1
;

ALTER SEQUENCE public.flight_seq
    OWNER TO postgres;

CREATE SEQUENCE public.seat_seq
    INCREMENT 1
    START 1
;

ALTER SEQUENCE public.seat_seq
    OWNER TO postgres;