
package com.stw.beerService.services;

import com.stw.beerService.web.model.BeerDto;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
public interface BeerService {

    public BeerDto getById(UUID beerId);

    public BeerDto saveNewBeer(BeerDto beerDto);

    public BeerDto updateBeer(UUID beerId, BeerDto beerDto);
}
