package world.globalcargo.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "operators")
@Data
public class Operators {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "department")
    private String department;
}