
package com.stw.beerService.web.mappers;

import com.stw.beerService.domain.Beer;
import com.stw.beerService.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
 BeerDto beerToBeerDto(Beer beer);
 Beer beerDtoToBeer(BeerDto beerDto);
}
