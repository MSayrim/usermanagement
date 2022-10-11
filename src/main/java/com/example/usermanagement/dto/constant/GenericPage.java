package com.example.usermanagement.dto.constant;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
public class GenericPage {

    private int size;
    private int page;

    public Pageable getPageable(){
        return PageRequest.of(page,size);
    }
}