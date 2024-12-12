package com.frenesie.collectif.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
	private Integer id;
	private String genre;
	
	@Override
	public String toString() {
		return "Genre [id=" + id + ", genre" + genre + "]";
	}
}
