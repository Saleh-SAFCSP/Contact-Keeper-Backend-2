package contactkeeper.demo.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
public class Contact  {
    @Id
    private String id = UUID.randomUUID().toString().toUpperCase();

    @NotNull(message = "Username is required")
    private String name;

    @NotNull(message = "Phone Number is required")
    private String phoneNumber;

    private String userId;
}
