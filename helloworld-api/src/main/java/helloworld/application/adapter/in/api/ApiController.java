package helloworld.application.adapter.in.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import helloworld.application.service.api.port.in.ApiServiceInPort;

@RequestMapping(value = "/api/v1", produces = "application/json;charset=UTF-8")
@RestController
//@Slf4j
public class ApiController {
	private final ApiServiceInPort apiServiceInPort;
	
	ApiController(ApiServiceInPort apiServiceInPort){
		this.apiServiceInPort = apiServiceInPort;
	}
	
	@PostMapping("/qry/{almlType}")
	public void getTest() {

		try {
			apiServiceInPort.getTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
