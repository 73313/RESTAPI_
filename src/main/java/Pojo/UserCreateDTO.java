package Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateDTO {
    private String name;
    private String job;
    private String id;
    private String createdAt;

}
