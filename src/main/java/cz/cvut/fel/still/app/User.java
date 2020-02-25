package cz.cvut.fel.still.app;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Builder
public class User {
    private String id;
    private String name;
    private String email;
}
