/**
 * Copyright(c) 2022-2023 Hyundai Mall All right reserved.
 * This software is the proprietary information of Hyundai Mall.
 * @Description : 
 * @FileName    : AlmlIntlApiAbstDTO.java
 * @Date        : 2022. 11. 7.
 * @Author      : h2o018
 */
package helloworld.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public abstract class CommApiAbstDTO {  
    
    @Schema(description = "test", example="tessset")
    private String  test;
    
    @Schema(description = "세션ID ")
    @Hidden
    private String  sessId;
    
    @Schema(description = "Remote IP")
    @Hidden
    private String  remoteIp;

    public CommApiAbstDTO() {}
}
