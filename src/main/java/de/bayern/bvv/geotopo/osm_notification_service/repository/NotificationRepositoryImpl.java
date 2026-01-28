package de.bayern.bvv.geotopo.osm_notification_service.repository;

import de.bayern.bvv.geotopo.osm_notification_service.entity.NotificationEntity;
import de.bayern.bvv.geotopo.osm_notification_service.dto.BoundingBox;
import de.bayern.bvv.geotopo.osm_notification_service.dto.NotificationFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final EntityManager entityManager;

    /**
     * Find notifications by NotificationFilter.
     */
    @Override
    public List<NotificationEntity> findByNotificationFilter(NotificationFilter notificationFilter) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<NotificationEntity> query = builder.createQuery(NotificationEntity.class);
        Root<NotificationEntity> root = query.from(NotificationEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by bounding box.
        BoundingBox boundingBox = notificationFilter.boundingBox();
        if (boundingBox != null) {
            predicates.add(this.createBoundingBoxPredicate(builder, root));
        }

        // Filter by state.
        if (notificationFilter.state() != null) {
            predicates.add(builder.equal(root.get("state"), notificationFilter.state()));
        }

        // Filter by group identifier.
        if (notificationFilter.groupId() != null) {
            predicates.add(builder.equal(root.get("groupId"), notificationFilter.groupId()));
        }

        // Filter by group state.
        if (notificationFilter.groupState() != null) {
            predicates.add(builder.equal(root.get("groupState"), notificationFilter.groupState()));
        }

        // Create query.
        query.where(builder.and(predicates.toArray(new Predicate[0]))).orderBy(builder.asc(root.get("id")));
        TypedQuery<NotificationEntity> typedQuery = this.entityManager.createQuery(query);

        if (boundingBox != null) {
            typedQuery.setParameter("minX", boundingBox.minX())
                      .setParameter("maxX", boundingBox.maxX())
                      .setParameter("minY", boundingBox.minY())
                      .setParameter("maxY", boundingBox.maxY());
        }

        return typedQuery.getResultList();
    }

    /**
     * Create bounding box predicate.
     */
    private Predicate createBoundingBoxPredicate(CriteriaBuilder builder, Root<NotificationEntity> root) {
        ParameterExpression<Double> minX = builder.parameter(Double.class, "minX");
        ParameterExpression<Double> maxX = builder.parameter(Double.class, "maxX");
        ParameterExpression<Double> minY = builder.parameter(Double.class, "minY");
        ParameterExpression<Double> maxY = builder.parameter(Double.class, "maxY");

        Expression<Object> stMakeEnvelope = builder.function("ST_MakeEnvelope", Object.class,
                minX, maxX, minY, maxY, builder.literal(4326)
        );

        Expression<Boolean> stIntersects = builder.function("ST_Intersects", Boolean.class,
                root.get("geom"), stMakeEnvelope);

        return builder.isTrue(stIntersects);
    }
}
