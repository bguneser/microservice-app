package accountservice.dto;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;

}
