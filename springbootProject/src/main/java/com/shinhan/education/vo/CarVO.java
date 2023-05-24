package com.shinhan.education.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode*/
@Data
@Builder//를 하려면 all+no 아규먼트 둘 다 추가해주어야한다
@NoArgsConstructor
@AllArgsConstructor
public class CarVO {
	private String model;
	private int price;
	
}
