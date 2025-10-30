package world.globalcargo.crm.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "requests")
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;


    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "commentary", length = 1000)
    private String commentary;

    @Column(nullable = false)
    private boolean handled = false;

    @ManyToOne
    @JoinColumn(name = "courses_id",nullable = false)
    private Courses course;

    @ManyToMany
    @JoinTable(
            name = "request_operators",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "operator_id")
    )
    private List<Operators> operators;
}