package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import server.rem.entities.Template;

public interface TemplateRepository  extends JpaRepository<Template, String>, JpaSpecificationExecutor<Template> {
    
}
