package com.zsb.bluex.defaults.libs;

import com.zsb.bluex.core.anno.BluexFunction;
import com.zsb.bluex.core.anno.BluexFunctionLib;
import com.zsb.bluex.core.param.INPUT;
import com.zsb.bluex.core.param.OUTPUT;
import com.zsb.bluex.defaults.enums.RequestMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@BluexFunctionLib(category = "Spring支持|RestTemplate")
public class SysLib_RestTemplate {

    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        // 强制 RestTemplate 使用 UTF-8 编码
        restTemplate.getMessageConverters().removeIf(c -> c instanceof StringHttpMessageConverter);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @BluexFunction(category = "发起GET请求")
    public static void GetForString(
            INPUT<String> Url,
            OUTPUT<String> Out
    ) {
        Out.value = restTemplate.getForObject(Url.value, String.class);
    }

    @BluexFunction(category = "发起POST请求")
    public static void PostForString(
            INPUT<String> Url,
            INPUT<Object> Body,
            OUTPUT<String> Out
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // 请求体为 JSON
        headers.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());

        HttpEntity<Object> entity = new HttpEntity<>(Body.value, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(Url.value, entity, String.class);

        Out.value = response.getBody();
    }

    @BluexFunction(category = "自定义Exchange请求")
    public static void Exchange(
            INPUT<String> Url,
            INPUT<RequestMethod> Method,
            INPUT<Object> Body,
            INPUT<Map<String, String>> Headers,
            OUTPUT<String> Out
    ) {
        HttpMethod httpMethod = HttpMethod.valueOf(Method.value.name());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(HttpHeaders.ACCEPT_CHARSET, StandardCharsets.UTF_8.name());

        if (Headers.value != null) {
            Headers.value.forEach(httpHeaders::add);
        }

        HttpEntity<Object> entity = new HttpEntity<>(Body.value, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(
                Url.value,
                httpMethod,
                entity,
                String.class
        );

        Out.value = response.getBody();
    }
}
