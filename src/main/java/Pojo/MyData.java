package Pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "email",
        "first_name",
        "last_name",
        "avatar"
})
public class MyData {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("avatar")
    private String avatar;

}







