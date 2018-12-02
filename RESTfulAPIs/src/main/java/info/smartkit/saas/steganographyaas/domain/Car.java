package info.smartkit.saas.steganographyaas.domain;

import io.leangen.graphql.annotations.GraphQLQuery;
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
    @GraphQLQuery(name = "id", description = "A Car's id.")
    private Long id;
    @NotNull
    @GraphQLQuery(name = "name", description = "A Car's name.")
    private String name;

}
