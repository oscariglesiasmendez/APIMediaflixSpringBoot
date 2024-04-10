package com.castelao.mediaflix_v4.dto.pages;

import java.util.List;

import com.castelao.mediaflix_v4.dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPageDto {
    
	private List<BookDto> books;
    private long total;
    private int skip;
    private int limit;

    
}