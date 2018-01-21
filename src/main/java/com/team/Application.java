package com.team;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageHelper;

@SpringBootApplication			//@SpringBootApplication = (默认属性)@Configuration + @EnableAutoConfiguration + @ComponentScan
@MapperScan("com.team.dao")		//查找报指定包及其子包下面的dao接口
@EnableCaching					//开启注解缓存
public class Application{
	private static Logger logger = Logger.getLogger(Application.class);

    /**
     * 数据源，druid连接池
     */
    @Value("${spring.datasource.type}")  
    private Class<? extends DataSource> dataSourceType;  

    @Bean(name="dataSource", destroyMethod = "close", initMethod="init")  
    @ConfigurationProperties(prefix = "spring.datasource")  
    public DataSource dataSource() { 
    	logger.info("-------------------- writeDataSource init ---------------------"); 
        return DataSourceBuilder.create().type(dataSourceType).build();
    }
    
    /**
     * 配置SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean  
    public SqlSessionFactory sqlSessionFactory() throws Exception {  
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
        sqlSessionFactoryBean.setDataSource(dataSource());
        //分页插件  
        PageHelper pageHelper = new PageHelper();  
        Properties props = new Properties();  
        props.setProperty("reasonable", "true");  
        props.setProperty("supportMethodsArguments", "true");  
        props.setProperty("returnPageInfo", "check");  
        props.setProperty("params", "count=countSql");  
        pageHelper.setProperties(props);  
        //添加插件  
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});  
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));  
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);  
        return sqlSessionFactoryBean.getObject();  
    } 
    
    /**  
     * 配置事务管理器  
     */  
    @Bean(name="transactionManager")  
    @Primary  
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) throws Exception {  
        return new DataSourceTransactionManager(dataSource);  
    }  
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
     return new EmbeddedServletContainerCustomizer() {
      @Override
      public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setSessionTimeout(3600);//单位为S
      }
     };
    }

    /**
     * 启动入口main方法
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        logger.info("SpringBoot Start Success!");
    }

    
}
