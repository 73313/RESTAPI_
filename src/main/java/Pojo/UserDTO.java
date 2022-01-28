package Pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data",
        "support"
})
public class UserDTO{

    @JsonProperty("data")
    private MyData data;
    @JsonProperty("support")
    private MySupport support;

}
