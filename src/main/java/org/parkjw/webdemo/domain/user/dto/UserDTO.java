package org.parkjw.webdemo.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;
import org.parkjw.webdemo.domain.common.dto.DTO;

public class UserDTO {

	@Getter
	@Setter
	@Builder
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class View implements DTO {

		private Long id;

		private String email;

		private String name;

	}

	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	public static class Create implements DTO {

		@NotNull
		String email;

		@NotNull
		String name;

		@NotNull
		String password;

	}

	@Getter
	@Setter
	@Builder
	@ToString
	@AllArgsConstructor
	public static class Update implements DTO {

		@NotNull
		Long id;

		@NotNull
		String email;

		@NotNull
		String name;

		@NotNull
		String password;

	}
}
