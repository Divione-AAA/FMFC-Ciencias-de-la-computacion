package metabolico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * MetabolicRoute modela una ruta metabólica pendiente de análisis.
 */
public class MetabolicRoute {

    private static int nextRouteId = 1;

    private final int id;
    private final int sourceId;
    private final int targetId;
    private final double clinicalRelevance;
    private int reactionCount;
    private final List<Integer> dependencyIds;

    public MetabolicRoute(int sourceId, int targetId, double clinicalRelevance, int reactionCount) {
        this.id = nextRouteId++;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.clinicalRelevance = clinicalRelevance;
        this.reactionCount = Math.max(0, reactionCount);
        this.dependencyIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getSourceId() {
        return sourceId;
    }

    public int getTargetId() {
        return targetId;
    }

    public double getClinicalRelevance() {
        return clinicalRelevance;
    }

    public int getReactionCount() {
        return reactionCount;
    }

    public void setReactionCount(int reactionCount) {
        this.reactionCount = Math.max(0, reactionCount);
    }

    public void addDependency(int dependencyRouteId) {
        if (!dependencyIds.contains(dependencyRouteId)) {
            dependencyIds.add(dependencyRouteId);
        }
    }

    public List<Integer> getDependencyIds() {
        return Collections.unmodifiableList(dependencyIds);
    }

    @Override
    public String toString() {
        return String.format("Route[%d] %d -> %d | relevance=%.2f | reactions=%d | deps=%s",
                id, sourceId, targetId, clinicalRelevance, reactionCount, dependencyIds);
    }
}
