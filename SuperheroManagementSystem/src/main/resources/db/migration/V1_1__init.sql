CREATE TABLE superhero (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  pseudonym varchar(100) NOT NULL,
  publisher varchar(50) NOT NULL,
  first_appearance_on DATE NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_name (pseudonym)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE skills (
    id bigint(20) NOT NULL auto_increment,
    name VARCHAR(50),
    superhero_id bigint(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (superhero_id) REFERENCES superhero(id),
    UNIQUE KEY UK_skill (name,superhero_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE allies (
    id bigint(20) NOT NULL auto_increment,
    superhero_id bigint(20) NOT NULL,
    ally_id bigint(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (superhero_id) REFERENCES superhero(id),
    FOREIGN KEY (ally_id) REFERENCES superhero(id),
    UNIQUE KEY UK_ally (superhero_id,ally_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;