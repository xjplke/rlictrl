package cn.adfi.rlictrl.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SecurityConfig {
	
	@Autowired
	public void init(DataSource dataSource){
		DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
	
	    //设置authenticator
	    ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
	    authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
	    securityManager.setAuthenticator(authenticator);
	
	    //设置authorizer
	    ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
	    authorizer.setPermissionResolver(new WildcardPermissionResolver());
	    securityManager.setAuthorizer(authorizer);
	
	    //设置Realm
	    /*
	    DruidDataSource ds = new DruidDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	    ds.setUrl("jdbc:mysql://localhost:3306/shiro");
	    ds.setUsername("root");
	    ds.setPassword("");*/
	
	    JdbcRealm jdbcRealm = new JdbcRealm();
	    jdbcRealm.setDataSource(dataSource);
	    jdbcRealm.setPermissionsLookupEnabled(true);
	    securityManager.setRealms(Arrays.asList((Realm) jdbcRealm));
	
	    //将SecurityManager设置到SecurityUtils 方便全局使用
	    SecurityUtils.setSecurityManager(securityManager);
	
	}
	
	/*
	@Bean(name="restfilter")
	public HttpMethodPermissionFilter createFilter(){
		return new HttpMethodPermissionFilter();
	}*/
	
	@Bean
	public FilterRegistrationBean restFilter(){
	    FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
	    filterRegBean.setFilter(new ShiroFilter());
	    List<String> urlPatterns = new ArrayList<String>();
	    urlPatterns.add("/*");
	    filterRegBean.setUrlPatterns(urlPatterns);
	    return filterRegBean;
	}
}
