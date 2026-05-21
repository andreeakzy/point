package model;

//recomandarea smart 
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

    public LearningPoint getRecommendedPoint() {
        return recommendedPoint;
    }

    public void setRecommendedPoint(LearningPoint recommendedPoint) {
        this.recommendedPoint = recommendedPoint;
    }

    public String getWhyThisPoint() {
        return whyThisPoint;
    }

    public void setWhyThisPoint(String whyThisPoint) {
        this.whyThisPoint = whyThisPoint;
    }

    public int getRecommendationScore() {
        return recommendationScore;
    }

    public void setRecommendationScore(int recommendationScore) {
        this.recommendationScore = recommendationScore;
    }

    @Override
    public String toString() {
        return "NextPoint{"
                + "id=" + getId()
                + ", recommendedPoint=" + recommendedPoint.getPointTitle()
                + ", whyThisPoint='" + whyThisPoint + '\''
                + ", recommendationScore=" + recommendationScore
                + '}';
    }
}
