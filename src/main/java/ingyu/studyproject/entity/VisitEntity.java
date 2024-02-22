package ingyu.studyproject.entity;

import jakarta.persistence.*;

@Entity
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ipAddr;

    public VisitEntity(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public VisitEntity() {

    }
}
