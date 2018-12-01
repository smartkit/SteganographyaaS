package info.smartkit.saas.steganographyaas.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Car {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;

}
