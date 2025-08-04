package com.zsb.bluex.example;

import com.zsb.bluex.core.anno.BluexType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@BluexType
@Data
@NoArgsConstructor
public class TestStudent implements Serializable {
    private String name;
    private Integer age;
    private List<String> hobbies = new ArrayList<>();
}
