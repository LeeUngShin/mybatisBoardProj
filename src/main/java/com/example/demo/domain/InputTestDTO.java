package com.example.demo.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InputTestDTO {
	
	private Long idx;
	private String name;
	private String email;
	private String food;
	private List<String> hobbies;
	private String hobbiesString;
	private String content;
	
    public String getHobbiesAsString() {
        if (hobbies == null || hobbies.isEmpty()) {
            return "";
        }
        return String.join(",", hobbies);
    }
}
