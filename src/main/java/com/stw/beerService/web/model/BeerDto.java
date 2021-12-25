
package com.stw.beerService.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *          ScienceTechWorks
 * @author Ramon.Talavera@gmail.com 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    
  @Null //ReadOnly property: Let the system control its value
  private UUID id;
  
  @Null
  private Integer version;
  
  @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ",shape=JsonFormat.Shape.STRING)
  @Null
  private OffsetDateTime createdDate;
 
  @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ",shape=JsonFormat.Shape.STRING)  
  @Null
  private OffsetDateTime lastModifiedDate;
  
  @NotBlank
  private String beerName;
  
  @NotNull
  private BeerStyleEnum beerStyle;
  
  @Positive
  @NotNull
  private Long upc;
  
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  @Positive
  @NotNull
  private BigDecimal price;
  
  
  private Integer quantityOnHand;
}
