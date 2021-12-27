
package com.stw.beerService.services;

import com.stw.beerService.domain.Beer;
import com.stw.beerService.repositories.BeerRepository;
import com.stw.beerService.web.mappers.BeerMapper;
import com.stw.beerService.web.model.BeerDto;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@AllArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;
    
    @Override
    public BeerDto getById(UUID beerId) {
        Beer beer=null;
        Optional<Beer> beerOp = beerRepository.findById(beerId);
        if (beerOp.isPresent())   
            beer=beerOp.get();
        else    
        {
            System.out.println("Not found");
            return null;
        }
        BeerDto beerDto= beerMapper.beerToBeerDto(beer);
        return beerDto;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        //beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(
                beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
         Optional<Beer> opbeer=beerRepository.findById(beerId);

         Beer beer=new Beer();
         if (opbeer.isPresent())
         {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());
         }

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    } 

}
