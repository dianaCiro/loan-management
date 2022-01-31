package com.meli.loan.repositoryimpl;

import com.meli.loan.entity.LoanEntity;
import com.meli.loan.jpa.JpaLoanRepository;
import com.meli.loan.mapper.LoanMapperEntity;
import com.meli.loan.model.Loan;
import com.meli.loan.model.LoanFilter;
import com.meli.loan.model.PagedLoan;
import com.meli.loan.repository.LoanRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Is responsible for registering the loans in the database.
 */
@Repository
public class LoanRepositoryImpl implements LoanRepository {

    /**
     * JpaLoanRepository dependency to database access.
     */
    private JpaLoanRepository jpaLoanRepository;

    /**
     * LoanMapperEntity Dependency to mapper objects.
     */
    private LoanMapperEntity loanMapper;

    /**
     * EntityManager dependency to database access.
     */
    private EntityManager entityManager;

    private static final String DATE_FROM_PARAM = "from";
    private static final String DATE_TO_PARAM = "to";
    private static final String DATE_PARAM = "date";

    /**
     * The loanRepositoryImpl constructor.
     *
     * @param jpaLoanRepository
     */
    public LoanRepositoryImpl(JpaLoanRepository jpaLoanRepository, LoanMapperEntity loanMapper, EntityManager entityManager) {
        this.jpaLoanRepository = jpaLoanRepository;
        this.loanMapper = loanMapper;
        this.entityManager = entityManager;
    }

    /**
     * Persists the loan object in database.
     *
     * @param loan to save.
     */
    @Override
    public void create(Loan loan) {
        jpaLoanRepository.save(loanMapper.convertDomainToEntityObject(loan));
    }

    /**
     * Retrieves loans with pagination.
     * @param loanFilter instance.
     * @return PagedLoan instance.
     */
    @Override
    public PagedLoan retrieveLoans(LoanFilter loanFilter) {
        int totalElements = (int) jpaLoanRepository.count(getSpec(loanFilter));

        TypedQuery<LoanEntity> typedQuery = entityManager.createQuery(this.createQuery(loanFilter),
                LoanEntity.class);
        this.setQueryParam(loanFilter, typedQuery);

        int elements = loanFilter.getPage() * loanFilter.getLimit();
        List<LoanEntity> loanEntities = typedQuery.setFirstResult(elements)
                .setMaxResults(loanFilter.getLimit()).getResultList();

        List<Loan> loans = loanEntities.stream().map(loanEntity -> this.loanMapper.convertEntityToDomainObject(loanEntity)).collect(Collectors.toList());

        int totalPages = totalElements / loanFilter.getLimit();
        int mod = totalElements % loanFilter.getLimit();
        totalPages = mod == 0 ? totalPages : totalPages + 1;

        return new PagedLoan(loans, totalPages, totalElements);
    }

    /**
     * Retrieves an optional.
     * @param loanId to find the optional
     * @return Optional<Loan> instance.
     */
    @Override
    public Optional<Loan> findById(String loanId) {
        Optional<LoanEntity> optionalLoanEntity = jpaLoanRepository.findById(loanId);

        if(optionalLoanEntity.isPresent()){
            return Optional.of(loanMapper.convertEntityToDomainObject(optionalLoanEntity.get()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Build the query with filters.
     * @param loanFilter params to filter.
     * @return a query string.
     */
    private String createQuery(LoanFilter loanFilter) {

        StringBuilder query = new StringBuilder("SELECT l FROM loan l WHERE 1=1  ");

        if (loanFilter.getFrom() != null) {
            query.append(" AND l.date >= :from ");
        }
        if (loanFilter.getTo() != null) {
            query.append(" AND l.date <= :to ");
        }

        if (loanFilter.getSortColumn() != null) {
            query.append("ORDER BY l.").append(loanFilter.getSortColumn()).append(" ").append(loanFilter.getSortDirection());
        }

        return query.toString();
    }

    /**
     * Build the query params.
     * @param loanFilter params to filter.
     * @param typedQuery Indicates the entity to avoid casts.
     */
    private void setQueryParam(LoanFilter loanFilter, TypedQuery<LoanEntity> typedQuery) {
        if (loanFilter.getFrom() != null) {
            typedQuery.setParameter(DATE_FROM_PARAM, loanFilter.getFrom());
        }
        if (loanFilter.getTo() != null) {
            typedQuery.setParameter(DATE_TO_PARAM, loanFilter.getTo());
        }
    }

    /**
     * It defines a specification as a predicate over a loanEntity.
     * @param loanFilter params to filter.
     * @return Specification<LoanEntity> instance.
     */
    private Specification<LoanEntity> getSpec(LoanFilter loanFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicateFilters(root, criteriaBuilder, predicates, loanFilter);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    /**
     * Build the specification filters.
     * @param root is the loanEntity reference.
     * @param criteriaBuilder instance  to construct criteria queries.
     * @param predicates instance to build the query restrictions.
     * @param loanFilter params to filter.
     */
    private void predicateFilters(Root<LoanEntity> root, CriteriaBuilder criteriaBuilder, List<Predicate> predicates,
                                  LoanFilter loanFilter) {

        if (loanFilter.getFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(DATE_PARAM), loanFilter.getFrom()));
        }
        if (loanFilter.getTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(DATE_PARAM), loanFilter.getTo()));
        }
    }
}
