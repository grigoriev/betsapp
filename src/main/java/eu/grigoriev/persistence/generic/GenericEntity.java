package eu.grigoriev.persistence.generic;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface GenericEntity<PrimaryKey> {
    @JsonIgnore
    PrimaryKey getPK();
}
