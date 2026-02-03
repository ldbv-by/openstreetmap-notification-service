package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationGroupEntity;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationGroupFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationGroupRepositoryImpl implements NotificationGroupRepositoryCustom {

    private final EntityManager entityManager;

    /**
     * Find notification groups by NotificationGroupFilter.
     */
    @Override
    public List<NotificationGroupEntity> findByNotificationGroupFilter(NotificationGroupFilter notificationGroupFilter) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<NotificationGroupEntity> query = builder.createQuery(NotificationGroupEntity.class);
        Root<NotificationGroupEntity> root = query.from(NotificationGroupEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by group state.
        if (notificationGroupFilter.groupState() != null) {
            predicates.add(builder.equal(root.get("state"), notificationGroupFilter.groupState()));
        }

        // Filter by description.
        if (notificationGroupFilter.description()!= null) {
            predicates.add(builder.like(root.get("description"), '%' + notificationGroupFilter.description() + '%'));
        }

        // Create query.
        query.where(builder.and(predicates.toArray(new Predicate[0]))).orderBy(builder.asc(root.get("id")));
        TypedQuery<NotificationGroupEntity> typedQuery = this.entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}
