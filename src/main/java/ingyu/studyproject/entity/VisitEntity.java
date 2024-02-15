package ingyu.studyproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "visit")
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ipAddr;

    public VisitEntity(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
