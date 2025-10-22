package helloworld.application.adapter.out.h2db.api;

import org.springframework.stereotype.Repository;

import helloworld.application.adapter.out.h2db.api.mapper.ApiH2dbMapper;
import helloworld.application.service.api.port.out.h2db.ApiH2dbOutPort;

@Repository
public class ApiH2dbAdapter implements ApiH2dbOutPort{
	
    private final ApiH2dbMapper     apiH2dbMapper; 

    public ApiH2dbAdapter( ApiH2dbMapper apiH2dbMapper) {
        this.apiH2dbMapper     = apiH2dbMapper;
    }

	@Override
	public String selectTest() {
		return apiH2dbMapper.selectTest();
	}
}
