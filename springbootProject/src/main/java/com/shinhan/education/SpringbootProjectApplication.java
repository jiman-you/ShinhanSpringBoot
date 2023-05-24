package com.shinhan.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
//프로젝트 생성시 패키지의 하위에 있는 패키지는 자동 스캔된다
//@ComponentScan(basePackages = {"com.shinhan"}) //컴포넌트  스캔할 위치찾기
//@EntityScan("com.shinhan") //객체 위치찾기
//@EnableJpaRepositories("com.shinhan") //JPA Repository 활성화 자동임
public class SpringbootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProjectApplication.class, args);
	}

}
