package com.zsb.bluex.defaults.types;

import com.zsb.bluex.core.anno.BluexType;
import com.zsb.bluex.defaults.enums.DayEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@BluexType
@Data
@NoArgsConstructor
public class Student implements Serializable {
    private String name;
    private Integer age;
    private List<String> hobbies = new ArrayList<>();
    private DayEnum day;
}
