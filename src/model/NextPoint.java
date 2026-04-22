package model;

// clasa care reprezinta recomandarea smart generata de sistem
public class NextPoint extends BaseEntity {
    private LearningPoint recommendedPoint;
    private String whyThisPoint;
    private int recommendationScore;

    public NextPoint() {
    }

    public NextPoint(int id, LearningPoint recommendedPoint, String whyThisPoint, int recommendationScore) {
        super(id);
        this.recommendedPoint = recommendedPoint;
        this.whyThisPoint = whyThisPoint;
        this.recommendationScore = recommendationScore;
    }

    // getter pentru activitatea recomandata
    public LearningPoint getRecommendedPoint() {
        return recommendedPoint;
    }

    // setter pentru activitatea recomandata
    public void setRecommendedPoint(LearningPoint recommendedPoint) {
        this.recommendedPoint = recommendedPoint;
    }

    // getter pentru explicatia recomandarii
    public String getWhyThisPoint() {
        return whyThisPoint;
    }

    // setter pentru explicatia recomandarii
    public void setWhyThisPoint(String whyThisPoint) {
        this.whyThisPoint = whyThisPoint;
    }

    // getter pentru scorul recomandarii
    public int getRecommendationScore() {
        return recommendationScore;
    }

    // setter pentru scorul recomandarii
    public void setRecommendationScore(int recommendationScore) {
        this.recommendationScore = recommendationScore;
    }

    @Override
    public String toString() {
        return "NextPoint{" +
                "id=" + getId() +
                ", recommendedPoint=" + recommendedPoint.getPointTitle() +
                ", whyThisPoint='" + whyThisPoint + '\'' +
                ", recommendationScore=" + recommendationScore +
                '}';
    }
}