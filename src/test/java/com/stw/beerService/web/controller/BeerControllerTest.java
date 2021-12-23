package com.stw.beerService.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stw.beerService.domain.Beer;
import com.stw.beerService.repositories.BeerRepository;
import com.stw.beerService.web.model.BeerDto;
import com.stw.beerService.web.model.BeerStyleEnum;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Usuario
 */
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages="com.stw.beerService.web.mappers")
public class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;
    
    @Test
    void getBeerById() throws Exception {
        given(beerRepository.findById(any()))
                .willReturn(Optional.of(Beer.builder().build()));
        
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveNewBeer() throws Exception {

        BeerDto beerDto = BeerDto.builder().id(null).createdDate(null).
                lastModifiedDate(null).version(null).
                beerName("Some Beer"). 
                beerStyle(BeerStyleEnum.ALE).
                upc(83232982398L).
                price(BigDecimal.valueOf(12.5)).
                quantityOnHand(Integer.valueOf("10")).
                build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {
        BeerDto beerDto = BeerDto.builder().id(null).createdDate(null).
                lastModifiedDate(null).version(null).
                beerName("beer").
                beerStyle(BeerStyleEnum.ALE).
                upc(83232982398L).
                price(BigDecimal.valueOf(12.5)).
                quantityOnHand(Integer.valueOf("10")).
                build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
    
}
