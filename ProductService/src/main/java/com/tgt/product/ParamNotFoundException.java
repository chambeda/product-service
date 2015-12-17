package com.tgt.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="no product matching")
public class ParamNotFoundException extends RuntimeException {

}
