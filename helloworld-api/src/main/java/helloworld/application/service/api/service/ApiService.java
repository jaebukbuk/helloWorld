package helloworld.application.service.api.service;

import org.springframework.stereotype.Service;

import helloworld.application.service.api.port.in.ApiServiceInPort;
import helloworld.application.service.api.port.out.h2db.ApiH2dbOutPort;

@Service
public class ApiService implements ApiServiceInPort{

	private final ApiH2dbOutPort apiH2dbOutPort;
	
	public ApiService(ApiH2dbOutPort apiH2dbOutPort) {
		this.apiH2dbOutPort = apiH2dbOutPort;
	}
			
	@Override
	public void getTest() {
		System.out.println("들어옴");
		System.out.println(apiH2dbOutPort.selectTest());
	}
}
