package com.zsb.bluex.example;

import com.zsb.bluex.core.anno.BluexType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@BluexType
@Data
@RequiredArgsConstructor
public class TestStudent {
    private String name;
    private Integer age;
    private List<String> hobbies = new ArrayList<>();
}
