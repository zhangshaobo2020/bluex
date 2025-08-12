package com.zsb.bluex.web.mapping;

import com.zsb.bluex.core.graph.GraphView;
import com.zsb.bluex.model.entity.BluexTask;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TaskGraph implements Serializable {
    private BluexTask task;
    private GraphView graph;
}
