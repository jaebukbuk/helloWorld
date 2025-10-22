package helloworld.application.adapter.out.h2db.api.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApiH2dbMapper {
	
	public String selectTest();
}
