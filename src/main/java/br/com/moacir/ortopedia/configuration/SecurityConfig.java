/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.moacir.ortopedia.configuration;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/javax.faces.resources/**").permitAll()//Permitindo JavaScript e CSS
                .antMatchers("/pages/index.xhtml").hasAnyRole("Usuario", "Cliente")
                .antMatchers("/pages/cliente/**").hasAnyRole("Usuario")
                .antMatchers("/pages/medico/**").hasAnyRole("Usuario")
                .antMatchers("/pages/usuario/**").hasAnyRole("Usuario")
                .antMatchers("/pages/videoaula/**").hasAnyRole("Usuario")
                .antMatchers("/pages/videoinformativo/**").hasAnyRole("Usuario")
                .and()
                .formLogin().loginPage("/login.xhtml")
                .permitAll().failureUrl("/login.xhtml?error=true")//Login
                .and()
                .logout().logoutSuccessUrl("/login.xhtml")//Logout
                .and()
                .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select email, senha as password, true as enabled\n"
                        + "from pessoa\n"
                        + "where email = ?")
                .authoritiesByUsernameQuery("select email, 'ROLE_'||dtype as authority\n"
                        + "from pessoa\n"
                        + "where email = ?")
                .and()
                .inMemoryAuthentication().withUser("admin").password("{noop}123").roles("Usuario");
    }

}
