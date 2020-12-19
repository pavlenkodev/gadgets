package tech.itpark.marketplace.specifications;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ProductSearchCriteria {
    private String key;
    private String operation;
    private Object value;

}
