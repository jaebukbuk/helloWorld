package helloworld.common.h2db;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@MapperScan(value = "helloworld", annotationClass = Mapper.class, sqlSessionFactoryRef = "h2dbSqlSessionFactory")
public class H2dbDataSourceConfig {
	
    @Primary
    @Bean(name = "h2dbDataSource")
    @ConfigurationProperties(prefix = "helloworld.datasource.h2db")
    public DataSource h2dbDataSource()  {
        return DataSourceBuilder.create().build();
    }
    
    @Primary
	@Bean(name = "h2dbSqlSessionFactory")
	public SqlSessionFactory readerSqlSessionFactory(@Qualifier("h2dbDataSource") DataSource h2dbDataSource,
			                                         ApplicationContext applicationContext) throws Exception {
    	
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //sqlSessionFactoryBean.setPlugins(new SqlLogInterceptor( SqlExecutor));
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
        sqlSessionFactoryBean.setDataSource(h2dbDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/h2db/**/*Mapper.xml"));
		sqlSessionFactoryBean.setTransactionFactory(null);
		sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/configuration.xml"));
        //sqlSessionFactoryBean.setTypeAliasesPackage("hshop.application.domain.ap");
        return sqlSessionFactoryBean.getObject();
    }

	@Primary
	@Bean(name = "h2dbTransactionManager")
	public PlatformTransactionManager untdTransactionManager(@Qualifier("h2dbDataSource") DataSource h2dbDataSource) {
		
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(h2dbDataSource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
}
