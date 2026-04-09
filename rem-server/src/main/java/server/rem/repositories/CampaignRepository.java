package server.rem.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import server.rem.entities.Business;
import server.rem.entities.Campaign;

import java.util.List;
import java.util.Optional;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, String>, JpaSpecificationExecutor<Campaign> {
    @EntityGraph(attributePaths = { "business" })
    Optional<Campaign> findById(String id);

    @EntityGraph(attributePaths = { "business" })
    List<Campaign> findAllByBusiness(Business business);
}
