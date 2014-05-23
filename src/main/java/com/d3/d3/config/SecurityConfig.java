package com.d3.d3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("1234").password("1234").roles("USER");
        //http://www.journaldev.com/2715/spring-security-in-servlet-web-application-using-dao-jdbc-in-memory-authentication
        /*for(User u: LoginController.s) {    // Esto se hace con eso del JDBC o algo parecido XD XD XD
            auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles("USER");
        }*/
        /*final String findUserQuery = "select username,password "
         + "from User " + "where username = ?";
         DataSource ds = null;
         auth.jdbcAuthentication().dataSource(ds).usersByUsernameQuery(findUserQuery).rolePrefix("USER");*/
    }
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/**").not().authenticated()
                .and()
                .formLogin().loginPage("/login.html").failureUrl("/login.html?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login.html?logout")
                .and()
                .csrf().disable(); //esto debería activarse, pero no se mostrarlo en thymeleaf
    }
    //.csrf() is optional, enabled by default, if using WebSecurityConfigurerAdapter constructor
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin.html/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login.html").failureUrl("/login.html?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login.html?logout")
                .and()
                .csrf().disable(); //esto debería activarse, pero no se mostrarlo en thymeleaf
            
        /*http.authorizeRequests()
                .antMatchers("/user.html", "/user/edit.html").access("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login.html").failureUrl("/login.html?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login.html?logout")
                .and()
                .csrf().disable(); //esto debería activarse, pero no se mostrarlo en thymeleaf*/

    /*}*/
}
