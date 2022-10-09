package example.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import example.configuration.ApplicationConfiguration;
import example.configuration.DispatcherConfiguration;

public class ExampleInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// applicationConfiguration 등록
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(ApplicationConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// DispatcherConfiguration 등록
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(DispatcherConfiguration.class);

		// servlet 등록
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(applicationContext));
		
		System.out.println("dispatcher : " + dispatcher);
		
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		// utf8 필터 등록
		FilterRegistration.Dynamic filter = servletContext.addFilter("characterEncodingFilter",
				CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.setInitParameter("forceEncoding", "true");
		filter.addMappingForUrlPatterns(null, false, "/*");

	}

}
