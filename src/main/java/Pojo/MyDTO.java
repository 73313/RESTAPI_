package Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class MyDTO {
    private  String email;
    private String password;

}
