package helloworld.application.adapter.in.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import helloworld.application.service.api.port.in.ApiServiceInPort;
import helloworld.domain.CommApiReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RequestMapping(value = "/api/v1", produces = "application/json;charset=UTF-8")
@RestController
public class ApiController {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	private final ApiServiceInPort apiServiceInPort;
	
	ApiController(ApiServiceInPort apiServiceInPort){
		this.apiServiceInPort = apiServiceInPort;
	}
	
	@Operation(summary = "API001 : [API] - DB 테스트", description = "[API] - DB 테스트", method = "POST")
    @Tag(name = "API 1.1. [API] - DB 테스트")
	@PostMapping("/test")
	public void getTest(@RequestBody CommApiReqDTO commApiReqDTO) {

		try {
			apiServiceInPort.getTest();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
