package ro.tibi.csv.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ro.tibi.csv.dto.ClientSearchDTO;
import ro.tibi.csv.repository.Client;

public class DaoSpecifications {
	
	public static Specification<Client> findByCriteria(final ClientSearchDTO searchCriteria) {

        return new Specification<Client>() {

            @Override
            public Predicate toPredicate(
                Root<Client> root,
                CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (searchCriteria.getSecurityCode() != null && !searchCriteria.getSecurityCode().isEmpty()) {
                    predicates.add(cb.equal(root.get("securityCode"), searchCriteria.getSecurityCode()));
                }
                if (searchCriteria.getName() != null && !searchCriteria.getName().isEmpty()) {
                    predicates.add(cb.like(root.get("lastName"), "%"+searchCriteria.getName()+"%"));
                }
                if (searchCriteria.getFirstName() != null && !searchCriteria.getFirstName().isEmpty()) {
                    predicates.add(cb.like(root.get("firstName1"), "%"+searchCriteria.getFirstName()+"%"));
                }
                

                return cb.and(predicates.toArray(new Predicate[] {}));
            }
        };
    }
}
