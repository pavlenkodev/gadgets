package tech.itpark.marketplace.specifications;


import org.springframework.data.jpa.domain.Specification;
import tech.itpark.marketplace.model.Product;

import javax.persistence.criteria.*;

public class ProductSpecification implements Specification<Product> {

    private ProductSearchCriteria criteria;

    public ProductSpecification(ProductSearchCriteria productSearchCriteria) {
        this.criteria = productSearchCriteria;
    }

    @Override
    public Predicate toPredicate
            (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
