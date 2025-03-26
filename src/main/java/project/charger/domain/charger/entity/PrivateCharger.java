package project.charger.domain.charger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PrivateCharger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int privateChargerId;
    int userId;
}
