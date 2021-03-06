package javaee.hw8.demo.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class RoleEntity {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "name", unique = true)
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientRoles")
    @ToString.Exclude private List<ClientEntity> clientsRole;

}
