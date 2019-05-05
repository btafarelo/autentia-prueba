package com.github.btafarelo.autentia.prueba.commons;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*", exposedHeaders = {"X-Total-Count"} )
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface IRestController {

    public static final int PAGE_SIZE = 5;
}
