--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: server_properties; Type: TABLE; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE TABLE server_properties (
    property_key character varying(250) NOT NULL,
    property_value character varying(255) NOT NULL
);


ALTER TABLE server_properties OWNER TO bulldozer;

--
-- Name: users; Type: TABLE; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE TABLE users (
    username text NOT NULL,
    password text NOT NULL,
    id integer NOT NULL
);


ALTER TABLE users OWNER TO bulldozer;

--
-- Name: users_usersCol_seq; Type: SEQUENCE; Schema: public; Owner: bulldozer
--

CREATE SEQUENCE "users_usersCol_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "users_usersCol_seq" OWNER TO bulldozer;

--
-- Name: users_usersCol_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: bulldozer
--

ALTER SEQUENCE "users_usersCol_seq" OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: bulldozer
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('"users_usersCol_seq"'::regclass);


--
-- Data for Name: server_properties; Type: TABLE DATA; Schema: public; Owner: bulldozer
--

INSERT INTO server_properties VALUES ('de.refactorco.Bulldozer.net.web.WebServer.host', '127.0.0.1');
INSERT INTO server_properties VALUES ('de.refactorco.Bulldozer.net.web.WebServer.port', '8080');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: bulldozer
--

INSERT INTO users VALUES ('test', '$shiro1$SHA-256$500000$lXaJB+sajeFMnSjg4loSCg==$WGj9VeaHAmCKwDRogWCJD0oUwE15VVUO7eQsPnW3J1k=', 1);


--
-- Name: users_usersCol_seq; Type: SEQUENCE SET; Schema: public; Owner: bulldozer
--

SELECT pg_catalog.setval('"users_usersCol_seq"', 1, true);


--
-- Name: server_properties_pkey; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY server_properties
    ADD CONSTRAINT server_properties_pkey PRIMARY KEY (property_key);


--
-- Name: unique_id; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT unique_id PRIMARY KEY (id);


--
-- Name: unique_name; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT unique_name UNIQUE (username);


--
-- Name: unique_usersCol; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "unique_usersCol" UNIQUE (id);


--
-- Name: users_usersCol_key; Type: CONSTRAINT; Schema: public; Owner: bulldozer; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT "users_usersCol_key" UNIQUE (id);


--
-- Name: index_name; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX index_name ON users USING btree (username);


--
-- Name: index_usersCol; Type: INDEX; Schema: public; Owner: bulldozer; Tablespace: 
--

CREATE INDEX "index_usersCol" ON users USING btree (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: Jordan
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "Jordan";
GRANT ALL ON SCHEMA public TO "Jordan";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

