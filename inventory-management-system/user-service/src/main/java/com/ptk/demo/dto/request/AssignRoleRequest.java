package com.ptk.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleRequest {
	
	@NotNull(message = "Role ID is required")
    private Long roleId;
}
