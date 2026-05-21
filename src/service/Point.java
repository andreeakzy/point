package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import model.AcademicLearningPoint;
import model.AssessmentType;
import model.CareerLearningPoint;
import model.Category;
import model.DifficultyLevel;
import model.EnergyLevel;
import model.EnergyState;
import model.IndustryLevel;
import model.Learner;
import model.LearningPoint;
import model.Milestone;
import model.NextPoint;
import model.PersonalLearningPoint;
import model.PriorityLevel;
import model.ProgressRecord;
import model.ReflectionFrequency;
import model.SessionStatus;
import model.StudySession;
import model.WellnessCategory;

public class Point {

    private final List<Learner> learners;

    private final List<LearningPoint> learningPoints;

    private final TreeSet<LearningPoint> sortedLearningPoints;

    private final Map<Integer, Milestone> milestoneMap;

    private final List<StudySession> studySessions;

    private final List<ProgressRecord> progressHistory;

    private final List<NextPoint> nextPointHistory;

    private final Map<String, Category> categoryMap;

    private EnergyState currentEnergyState;

    private int learnerIdCounter = 1;
    private int learningPointIdCounter = 1;
    private int milestoneIdCounter = 1;
    private int energyStateIdCounter = 1;
    private int progressRecordIdCounter = 1;
    private int studySessionIdCounter = 1;
    private int nextPointIdCounter = 1;

    public Point() {
        learners = new ArrayList<>();
        learningPoints = new ArrayList<>();
        sortedLearningPoints = new TreeSet<>();
        milestoneMap = new LinkedHashMap<>();
        studySessions = new ArrayList<>();
        progressHistory = new ArrayList<>();
        nextPointHistory = new ArrayList<>();
        categoryMap = new LinkedHashMap<>();

        initializeDefaultCategories();
    }

    private void initializeDefaultCategories() {
        Category academicCategory = new Category(
                1, "academic", "learning activities pentru facultate, examene si proiecte academice"
        );

        Category careerCategory = new Category(
                2, "career", "learning activities pentru cariera, skill-uri si profesional development"
        );

        Category personalCategory = new Category(
                3, "personal", "learning activities pentru sine, lectura si personal growth"
        );

        categoryMap.put(academicCategory.getCategoryName().toLowerCase(), academicCategory);
        categoryMap.put(careerCategory.getCategoryName().toLowerCase(), careerCategory);
        categoryMap.put(personalCategory.getCategoryName().toLowerCase(), personalCategory);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryMap.values());
    }

    public Category getCategoryByName(String categoryName) {
        if (categoryName == null) {
            return null;
        }

        return categoryMap.get(categoryName.toLowerCase());
    }

    public Learner addLearner(String fullName, String emailAddress) {
        Learner learner = new Learner(
                learnerIdCounter++,
                fullName,
                emailAddress
        );

        learners.add(learner);
        return learner;
    }

    public List<Learner> getAllLearners() {
        return learners;
    }

    public LearningPoint addAcademicLearningPoint(String pointTitle, String pointDescription,
            DifficultyLevel difficultyLevel,
            PriorityLevel priorityLevel,
            EnergyLevel requiredEnergyLevel,
            String subjectName,
            double creditPoints,
            AssessmentType assessmentType,
            String targetGrade) {
        Category academicCategory = getCategoryByName("academic");

        AcademicLearningPoint academicPoint = new AcademicLearningPoint(
                learningPointIdCounter++,
                pointTitle,
                pointDescription,
                difficultyLevel,
                priorityLevel,
                requiredEnergyLevel,
                academicCategory,
                subjectName,
                creditPoints,
                assessmentType,
                targetGrade
        );

        learningPoints.add(academicPoint);
        sortedLearningPoints.add(academicPoint);

        return academicPoint;
    }

    public LearningPoint addCareerLearningPoint(String pointTitle, String pointDescription,
            DifficultyLevel difficultyLevel,
            PriorityLevel priorityLevel,
            EnergyLevel requiredEnergyLevel,
            String skillTrack,
            String requiredCertifications,
            IndustryLevel industryLevel,
            boolean mentorRequired,
            double salaryImpactEstimate) {
        Category careerCategory = getCategoryByName("career");

        CareerLearningPoint careerPoint = new CareerLearningPoint(
                learningPointIdCounter++,
                pointTitle,
                pointDescription,
                difficultyLevel,
                priorityLevel,
                requiredEnergyLevel,
                careerCategory,
                skillTrack,
                requiredCertifications,
                industryLevel,
                mentorRequired,
                salaryImpactEstimate
        );

        learningPoints.add(careerPoint);
        sortedLearningPoints.add(careerPoint);

        return careerPoint;
    }

    public LearningPoint addPersonalLearningPoint(String pointTitle, String pointDescription,
            DifficultyLevel difficultyLevel,
            PriorityLevel priorityLevel,
            EnergyLevel requiredEnergyLevel,
            String personalFocusArea,
            WellnessCategory wellnessCategory,
            ReflectionFrequency reflectionFrequency,
            String successMetrics) {
        Category personalCategory = getCategoryByName("personal");

        PersonalLearningPoint personalPoint = new PersonalLearningPoint(
                learningPointIdCounter++,
                pointTitle,
                pointDescription,
                difficultyLevel,
                priorityLevel,
                requiredEnergyLevel,
                personalCategory,
                personalFocusArea,
                wellnessCategory,
                reflectionFrequency,
                successMetrics
        );

        learningPoints.add(personalPoint);
        sortedLearningPoints.add(personalPoint);

        return personalPoint;
    }

    public List<LearningPoint> getAllLearningPoints() {
        return learningPoints;
    }

    public List<LearningPoint> getLearningPointsByCategory(String categoryName) {
        List<LearningPoint> filteredPoints = new ArrayList<>();

        for (LearningPoint currentPoint : learningPoints) {
            if (currentPoint.getCategory().getCategoryName().equalsIgnoreCase(categoryName)) {
                filteredPoints.add(currentPoint);
            }
        }

        return filteredPoints;
    }

    public TreeSet<LearningPoint> getSortedLearningPoints() {
        return sortedLearningPoints;
    }

    public EnergyState setCurrentEnergyState(EnergyLevel energyLevel, String stateNotes) {
        currentEnergyState = new EnergyState(
                energyStateIdCounter++,
                LocalDate.now(),
                energyLevel,
                stateNotes
        );

        return currentEnergyState;
    }

    public EnergyState getCurrentEnergyState() {
        return currentEnergyState;
    }

    public List<LearningPoint> getCompatibleLearningPointsForCurrentEnergy() {
        List<LearningPoint> compatiblePoints = new ArrayList<>();

        if (currentEnergyState == null) {
            return compatiblePoints;
        }

        for (LearningPoint currentPoint : learningPoints) {
            boolean hasEnoughEnergy
                    = currentEnergyState.getEnergyLevel().ordinal() >= currentPoint.getRequiredEnergyLevel().ordinal();

            if (!currentPoint.isCompleted() && hasEnoughEnergy) {
                compatiblePoints.add(currentPoint);
            }
        }

        return compatiblePoints;
    }

    public Milestone addMilestone(String milestoneTitle, String milestoneDescription,
            int targetValue, LocalDate deadline) {
        Milestone milestone = new Milestone(
                milestoneIdCounter++,
                milestoneTitle,
                milestoneDescription,
                targetValue,
                0,
                deadline
        );

        milestoneMap.put(milestone.getId(), milestone);
        return milestone;
    }

    public List<Milestone> getAllMilestones() {
        return new ArrayList<>(milestoneMap.values());
    }

    public List<Milestone> getActiveMilestones() {
        List<Milestone> activeMilestones = new ArrayList<>();

        for (Milestone currentMilestone : milestoneMap.values()) {
            if (!currentMilestone.isCompleted()) {
                activeMilestones.add(currentMilestone);
            }
        }

        return activeMilestones;
    }

    public boolean updateMilestoneProgress(int milestoneId, int updatedValue, String updateNotes) {
        Milestone milestone = milestoneMap.get(milestoneId);

        if (milestone == null) {
            return false;
        }

        int previousValue = milestone.getCurrentValue();
        milestone.setCurrentValue(updatedValue);

        ProgressRecord progressRecord = new ProgressRecord(
                progressRecordIdCounter++,
                milestone.getMilestoneTitle(),
                previousValue,
                updatedValue,
                LocalDate.now(),
                updateNotes
        );

        progressHistory.add(progressRecord);
        return true;
    }

    public List<ProgressRecord> getProgressHistory() {
        return progressHistory;
    }

    public StudySession createStudySession(int learningPointId, LocalDate sessionDate,
            int plannedMinutes, String sessionNotes) {
        LearningPoint chosenPoint = findLearningPointById(learningPointId);

        if (chosenPoint == null) {
            return null;
        }

        StudySession studySession = new StudySession(
                studySessionIdCounter++,
                chosenPoint,
                sessionDate,
                plannedMinutes,
                SessionStatus.PLANNED,
                sessionNotes
        );

        studySessions.add(studySession);
        return studySession;
    }

    public List<StudySession> getAllStudySessions() {
        return studySessions;
    }

    public boolean markStudySessionCompleted(int sessionId) {
        for (StudySession currentSession : studySessions) {
            if (currentSession.getId() == sessionId) {
                currentSession.setSessionStatus(SessionStatus.COMPLETED);
                currentSession.getLinkedPoint().setCompleted(true);
                return true;
            }
        }

        return false;
    }

    public NextPoint generateNextPointRecommendation() {
        if (currentEnergyState == null) {
            return null;
        }

        LearningPoint bestPoint = null;
        int bestScore = -1;

        for (LearningPoint currentPoint : learningPoints) {
            if (currentPoint.isCompleted()) {
                continue;
            }

            boolean hasEnoughEnergy
                    = currentEnergyState.getEnergyLevel().ordinal() >= currentPoint.getRequiredEnergyLevel().ordinal();

            if (!hasEnoughEnergy) {
                continue;
            }

            int pointScore = calculateRecommendationScore(currentPoint, currentEnergyState.getEnergyLevel());

            if (pointScore > bestScore) {
                bestScore = pointScore;
                bestPoint = currentPoint;
            }
        }

        if (bestPoint == null) {
            return null;
        }

        String whyThisPoint
                = "this has been recommended because it matches your current energy level and has a good balance of priority and difficulty";

        NextPoint nextPoint = new NextPoint(
                nextPointIdCounter++,
                bestPoint,
                whyThisPoint,
                bestScore
        );

        nextPointHistory.add(nextPoint);
        return nextPoint;
    }

    private int calculateRecommendationScore(LearningPoint learningPoint, EnergyLevel currentEnergyLevel) {
        int score = 0;

        score += learningPoint.getPriorityLevel().ordinal() * 10;

        score += learningPoint.getDifficultyLevel().ordinal() * 5;

        if (learningPoint.getRequiredEnergyLevel() == currentEnergyLevel) {
            score += 20;
        } else if (currentEnergyLevel.ordinal() > learningPoint.getRequiredEnergyLevel().ordinal()) {
            score += 10;
        }

        return score;
    }

    public LearningPoint findLearningPointById(int learningPointId) {
        for (LearningPoint currentPoint : learningPoints) {
            if (currentPoint.getId() == learningPointId) {
                return currentPoint;
            }
        }

        return null;
    }

    // ===== ACADEMIC ANALYTICS =====
    // Calculate total completed academic credits earned
    public double calculateTotalAcademicCreditsEarned() {
        double totalCredits = 0;
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof AcademicLearningPoint && point.isCompleted()) {
                AcademicLearningPoint academicPoint = (AcademicLearningPoint) point;
                // Default to 80% score if not explicitly set (can be enhanced later)
                totalCredits += academicPoint.calculateCreditsEarned(80);
            }
        }
        
        return totalCredits;
    }

    // Get all academic points with their assessment details
    public List<AcademicLearningPoint> getAllAcademicPoints() {
        List<AcademicLearningPoint> academicPoints = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof AcademicLearningPoint) {
                academicPoints.add((AcademicLearningPoint) point);
            }
        }
        
        return academicPoints;
    }

    // get career progression summary for all professional points
    public List<String> getCareerProgressionSummary() {
        List<String> progressionSummary = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof CareerLearningPoint) {
                CareerLearningPoint careerPoint = (CareerLearningPoint) point;
                String summary = careerPoint.getPointTitle() + " - " + careerPoint.estimateCareerProgression();
                progressionSummary.add(summary);
            }
        }
        
        return progressionSummary;
    }

    // get estimated total salary impact from completed career points
    public double calculateTotalSalaryImpact() {
        double totalImpact = 0;
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof CareerLearningPoint && point.isCompleted()) {
                CareerLearningPoint careerPoint = (CareerLearningPoint) point;
                totalImpact += careerPoint.getSalaryImpactEstimate();
            }
        }
        
        return totalImpact;
    }

    // get all career points that require mentor support
    public List<CareerLearningPoint> getCareerPointsRequiringMentor() {
        List<CareerLearningPoint> mentorNeeded = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof CareerLearningPoint) {
                CareerLearningPoint careerPoint = (CareerLearningPoint) point;
                if (careerPoint.isMentorRequired() && !careerPoint.isCompleted()) {
                    mentorNeeded.add(careerPoint);
                }
            }
        }
        
        return mentorNeeded;
    }

    // get all personal points with next reflection schedule
    public List<String> getPersonalPointsReflectionSchedule() {
        List<String> reflectionSchedule = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof PersonalLearningPoint) {
                PersonalLearningPoint personalPoint = (PersonalLearningPoint) point;
                LocalDate nextReflection = personalPoint.scheduleNextReflection();
                String schedule = personalPoint.getPersonalFocusArea() + " - Next reflection: " + nextReflection 
                    + " (" + personalPoint.getReflectionFrequency() + ")";
                reflectionSchedule.add(schedule);
            }
        }
        
        return reflectionSchedule;
    }

    // get personal points by wellness category
    public List<PersonalLearningPoint> getPersonalPointsByWellnessCategory(WellnessCategory category) {
        List<PersonalLearningPoint> filteredPoints = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof PersonalLearningPoint) {
                PersonalLearningPoint personalPoint = (PersonalLearningPoint) point;
                if (personalPoint.getWellnessCategory() == category) {
                    filteredPoints.add(personalPoint);
                }
            }
        }
        
        return filteredPoints;
    }

    // get all career points with their details
    public List<CareerLearningPoint> getAllCareerPoints() {
        List<CareerLearningPoint> careerPoints = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof CareerLearningPoint) {
                careerPoints.add((CareerLearningPoint) point);
            }
        }
        
        return careerPoints;
    }

    // get all personal points with their details
    public List<PersonalLearningPoint> getAllPersonalPoints() {
        List<PersonalLearningPoint> personalPoints = new ArrayList<>();
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof PersonalLearningPoint) {
                personalPoints.add((PersonalLearningPoint) point);
            }
        }
        
        return personalPoints;
    }

    // get learning point type count
    public String getLearningPointTypeBreakdown() {
        int academicCount = 0;
        int careerCount = 0;
        int personalCount = 0;
        
        for (LearningPoint point : learningPoints) {
            if (point instanceof AcademicLearningPoint) {
                academicCount++;
            } else if (point instanceof CareerLearningPoint) {
                careerCount++;
            } else if (point instanceof PersonalLearningPoint) {
                personalCount++;
            }
        }
        
        return String.format("Academic: %d | Career: %d | Personal: %d | Total: %d",
                academicCount, careerCount, personalCount, learningPoints.size());
    }
}
