package world.globalcargo.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "requests")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private boolean handled = false;
}