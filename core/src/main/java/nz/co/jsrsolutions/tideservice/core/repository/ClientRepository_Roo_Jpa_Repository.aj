// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.repository;

import nz.co.jsrsolutions.tideservice.core.domain.Client;
import nz.co.jsrsolutions.tideservice.core.repository.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

privileged aspect ClientRepository_Roo_Jpa_Repository {
    
    declare parents: ClientRepository extends JpaRepository<Client, Long>;
    
    declare parents: ClientRepository extends JpaSpecificationExecutor<Client>;
    
    declare @type: ClientRepository: @Repository;
    
}