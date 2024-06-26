package team.haedal.gifticionfunding.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import team.haedal.gifticionfunding.dto.response.GifticonResponse;
import team.haedal.gifticionfunding.dto.response.GifticonDetailResponse;
import team.haedal.gifticionfunding.service.GifticonServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GifticonController.class)
public class GifticonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GifticonServiceImpl gifticonService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("기프티콘 조회 테스트")
    void getGifticons() throws Exception {
        //given
        //gifticonService에 대한 Mock test
        List<GifticonResponse> gifticonResponseList = new ArrayList<>();
        gifticonResponseList.add(new GifticonResponse(1L,"기프티콘 이름",10000,"http://example.jpg"));
        given(gifticonService.getGifticons()).willReturn(
                gifticonResponseList
        );

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/gifticon")
                        .with(oauth2Login())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    @DisplayName("기프티콘 상세 조회 테스트")
    void getGifticonDetail() throws Exception {
        //given
        //gifticonService에 대한 Mock test
        given(gifticonService.getGifticonDetail(1L)).willReturn(
                new GifticonDetailResponse(
                        1L,
                        "카페라떼",
                        "스타벅스",
                        "http://image.png",
                        LocalDate.of(2025,5,5))
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/gifticon/detail")
                                .with(oauth2Login())
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("id", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.brand").exists())
                .andExpect(jsonPath("$.imageUrl").exists())
                .andExpect(jsonPath("$.expirationDate").exists())
                .andDo(print());

        // verify : 메소드가 실행이 됐는지를 확인
        verify(gifticonService).getGifticonDetail(1L);

    }
}
