package taba5.Artvis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import taba5.Artvis.dto.flask.ExhibitionContentsRequestDto;
import taba5.Artvis.dto.flask.RatingRequestDto;
import taba5.Artvis.dto.flask.FlaskRequestDto;
import taba5.Artvis.dto.flask.FlaskResponseDto;

@Service
@RequiredArgsConstructor
public class FlaskService {

    //데이터를 JSON 객체로 변환하기 위해서 사용
    private final ObjectMapper objectMapper;
    private final String FlaskURL ="http://25.13.126.193:8080";

    @Transactional
    public FlaskResponseDto getCollaborative(RatingRequestDto dto) throws JsonProcessingException {
        return getFlaskResponseDto(objectMapper.writeValueAsString(dto), dto);
    }
    @Transactional
    public FlaskResponseDto getContentsBased(ExhibitionContentsRequestDto dto) throws JsonProcessingException {
        return getFlaskResponseDto(objectMapper.writeValueAsString(dto), dto);
    }

    private FlaskResponseDto getFlaskResponseDto(String s, FlaskRequestDto dto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(s, headers);
        String url = FlaskURL + "/" ;

        return restTemplate.postForObject(url, entity, FlaskResponseDto.class);
    }

}