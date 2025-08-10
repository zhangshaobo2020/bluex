package com.zsb.bluex.web.socket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LogRecord implements Serializable {
    private String level;
    private String message;

    public LogRecord(String level, String message) {
        this.level = level;
        this.message = message;
    }
}
