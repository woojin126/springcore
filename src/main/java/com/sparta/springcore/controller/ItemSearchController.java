package com.sparta.springcore.controller;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.springcore.dto.ItemDto;
import com.sparta.springcore.service.ItemSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemSearchController {

    private final ItemSearchService itemSearchService;
    // Controller 가 자동으로 해주는 일
// 1. API Request 의 파라미터 값에서 검색어 추출 -> query 변수
// 5. API Response 보내기
// 5.1) response 의 header 설정
// 5.2) response 의 body 설정
    @GetMapping("/api/search")
    @ResponseBody
    public List<ItemDto> getItems(@RequestParam String query) throws IOException {
        List<ItemDto> itemDtoList = itemSearchService.getItems(query);
        return itemDtoList;
    }

}