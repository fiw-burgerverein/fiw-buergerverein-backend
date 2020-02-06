package com.buergervereinHSH.BackendProject.auth.dataAccessObject;

import com.buergervereinHSH.BackendProject.auth.model.GeschStelle;
import org.springframework.data.repository.CrudRepository;

public interface GeschStellenDao extends CrudRepository<GeschStelle, String> {

    GeschStelle findByName(String name);
}
