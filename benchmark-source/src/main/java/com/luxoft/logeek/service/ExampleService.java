package com.luxoft.logeek.service;

import com.luxoft.logeek.dto.Dto;

public interface ExampleService {
	long doIneffectively(Long id, Dto Dto);

	long doEffectively(Long id, Dto Dto);

	long doMostEffectively(Long id, Dto Dto);
}
