package com.stw.beerService.repositories;

import com.stw.beerService.domain.Beer;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Usuario
 */
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{
    
}
