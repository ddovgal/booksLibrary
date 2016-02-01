package ua.error_404.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class SpecificationsBuilder<T> {

    private List<Specification<T>> specifications;

    public SpecificationsBuilder() {
        specifications = new ArrayList<>();
    }

    public SpecificationsBuilder(List<Specification<T>> specifications) {
        this.specifications = specifications;
    }

    public void addSpecification(Specification<T> specification) {
        specifications.add(specification);
    }

    public Specification<T> buildBySpecsConjunction() {
        if (specifications.isEmpty()) return null;

        Specification<T> result = specifications.get(0);
        for (Specification<T> spec : specifications) result = Specifications.where(result).and(spec);
        return result;
    }

    public Specification<T> buildBySpecsDisjunction() {
        if (specifications.isEmpty()) return null;

        Specification<T> result = specifications.get(0);
        for (Specification<T> spec : specifications) result = Specifications.where(result).or(spec);
        return result;
    }

}
