package helloworld.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "zz")
public class CommApiReqDTO extends CommApiAbstDTO {

    @Schema(description = "asdasd", example="tttest")
    @JsonIgnore
    private String      ttttest;
}
