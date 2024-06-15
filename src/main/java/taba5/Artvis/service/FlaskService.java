package taba5.Artvis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import taba5.Artvis.dto.flask.*;
@Slf4j
@Service
@RequiredArgsConstructor
public class FlaskService {

    //데이터를 JSON 객체로 변환하기 위해서 사용
    private final ObjectMapper objectMapper;
    private final String FlaskURL ="http://25.13.126.193:5000/";

    @Transactional
    public FlaskResponseDto getCollaborativeHome(CollaborativeHomeRequestDto dto, String url) throws JsonProcessingException {
        return getFlaskResponseDto(objectMapper.writeValueAsString(dto), dto, url);
    }
    @Transactional
    public FlaskResponseDto getContentsBased(ContentsDetailRequestDto dto, String url) throws JsonProcessingException {
        return getFlaskResponseDto(objectMapper.writeValueAsString(dto), dto, url);
    }

    private FlaskResponseDto getFlaskResponseDto(String s, FlaskRequestDto dto, String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(s, headers);
        String destinationUrl = FlaskURL + url;
        log.info("entity : {}", entity.getBody());
        return restTemplate.postForObject(destinationUrl, entity, FlaskResponseDto.class);
    }

}