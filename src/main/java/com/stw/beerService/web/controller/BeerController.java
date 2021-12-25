
package com.stw.beerService.web.controller;

import com.stw.beerService.services.BeerService;
import com.stw.beerService.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * ScienceTechWorks
 */

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;
    
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId){
       return new ResponseEntity<>(beerService.getById(beerId),
               HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto){

        beerDto=beerService.saveNewBeer(beerDto);
        return new ResponseEntity<>(beerDto.getId(),HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
        beerService.updateBeer(beerId,beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
