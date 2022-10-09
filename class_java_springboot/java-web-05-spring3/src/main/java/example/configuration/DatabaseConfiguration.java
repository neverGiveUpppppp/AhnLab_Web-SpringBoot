package example.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

@Configuration
public class DatabaseConfiguration {

	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * DataSource bean 등록
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("1234");
		dataSource.setPort(3307);
		dataSource.setDatabaseName("profile");
		dataSource.setUrl("jdbc:mysql://localhost:3307/profile");
		return dataSource;
	}

	/**
	 * mapper 스캐너 빈 등록
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer configurer = new MapperScannerConfigurer();
		configurer.setBasePackage("example.mapper");
		return configurer;
	}

	/**
	 * SqlSessionFactory 빈 등록
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean
			.setMapperLocations(new PathMatchingResourcePatternResolver()
			.getResources("classpath:/config/mapper/**.xml"));
		return factoryBean.getObject();
	}
}
