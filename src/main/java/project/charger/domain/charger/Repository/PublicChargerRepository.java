package project.charger.domain.charger.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.charger.domain.charger.entity.PublicCharger;

@Repository
public interface PublicChargerRepository extends JpaRepository<PublicCharger, Integer> {

}
