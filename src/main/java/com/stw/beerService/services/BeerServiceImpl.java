
package com.stw.beerService.services;

import com.stw.beerService.domain.Beer;
import com.stw.beerService.repositories.BeerRepository;
import com.stw.beerService.web.mappers.BeerMapper;
import com.stw.beerService.web.model.BeerDto;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Setter
@ConfigurationProperties(prefix="com.stw",ignoreUnknownFields=false)
public class BeerServiceImpl implements BeerService {

    public String propertyExample; 
    
    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;
    
    @Cacheable(cacheNames= "beerCache")
    @Override
    public BeerDto getById(UUID beerId) {
        log.debug("PropertyExample="+
                propertyExample);
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

    @Cacheable(cacheNames="beerUpcCache")
    @Override
    public BeerDto getByUpc(String beerUpc) {
        Beer beer= beerRepository.findByUpc(beerUpc);
        if (beer==null)
        {
            System.out.println("Not found");
            return null;
        }
        BeerDto beerDto= beerMapper.beerToBeerDto(beer);
        return beerDto;
    }

}
