package example.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@Import(value = DatabaseConfiguration.class)
@ComponentScan(
	basePackages = "example", 
	includeFilters = @Filter({ 
		Service.class, 
		Repository.class, 
		Component.class 
	})
)
public class ApplicationConfiguration {

}
