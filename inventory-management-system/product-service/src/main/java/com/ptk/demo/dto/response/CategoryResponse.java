package com.ptk.demo.dto.response;


import java.time.LocalDateTime;
import java.util.List;

//import com.ptk.demo.dto.request.CategoryTranslationRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
	
	private Long id;

    private String name;

    private String description;
    
//    private List<CategoryTranslationRequest> translations; 

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
