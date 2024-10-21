package com.smsm.news;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//@RequiredArgsConstructor
@Service
public class NewsService {

    @Value("${naver.api.client.id}")
    private String clientId;

    @Value("${naver.api.client.secret}")
    private String clientSecret;

    private final RestTemplate restTemplate;

    public NewsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public NewsResponse fetchLatestEconomicNews() {
        String url = "https://openapi.naver.com/v1/search/news.json?query=경제&display=10&start=1&sort=sim";

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // 네이버 API 호출
        ResponseEntity<NewsResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, NewsResponse.class);
        NewsResponse newsResponse = response.getBody();

        if (newsResponse != null && newsResponse.getItems() != null) {
            // 각 뉴스 아이템의 제목에서 HTML 태그 제거
            newsResponse.getItems().forEach(item -> item.setTitle(removeHtmlTags(item.getTitle())));
        }

        return newsResponse;
    }

    // HTML 태그를 제거하는 메서드
    private String removeHtmlTags(String title) {
        return title.replaceAll("<[^>]*>", "");  // 모든 HTML 태그를 제거
    }
}
