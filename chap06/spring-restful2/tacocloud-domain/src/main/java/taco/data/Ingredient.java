package taco.data;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
// @TODO 虽然IDEA警告 Class 'Ingredient' should have [public, protected] no-arg constructor
// 但是能跑通
public class Ingredient {

    @Id
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}

