package service;

import model.AcademicLearningPoint;
import model.CareerLearningPoint;
import model.Category;
import model.DifficultyLevel;
import model.EnergyLevel;
import model.EnergyState;
import model.Learner;
import model.LearningPoint;
import model.Milestone;
import model.NextPoint;
import model.PersonalLearningPoint;
import model.PriorityLevel;
import model.ProgressRecord;
import model.SessionStatus;
import model.StudySession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// clasa service care contine logica principala a aplicatiei
public class PointService {

    // colectie pentru utilizatori
    private final List<Learner> learners;

    // colectie pentru toate activitatile de invatare
    private final List<LearningPoint> learningPoints;

    // colectie sortata automat pentru activitati
    private final TreeSet<LearningPoint> sortedLearningPoints;

    // map pentru milestone-uri, unde cheia este id-ul
    private final Map<Integer, Milestone> milestoneMap;

    // colectie pentru sesiuni de studiu
    private final List<StudySession> studySessions;

    // colectie pentru istoricul progresului
    private final List<ProgressRecord> progressHistory;

    // colectie pentru recomandarile generate
    private final List<NextPoint> nextPointHistory;

    // map pentru categorii implicite
    private final Map<String, Category> categoryMap;

    // energia curenta a utilizatorului
    private EnergyState currentEnergyState;

    // contoare simple pentru generarea id-urilor
    private int learnerIdCounter = 1;
    private int learningPointIdCounter = 1;
    private int milestoneIdCounter = 1;
    private int energyStateIdCounter = 1;
    private int progressRecordIdCounter = 1;
    private int studySessionIdCounter = 1;
    private int nextPointIdCounter = 1;

    public PointService() {
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

    // initializam cele 3 categorii de baza ale aplicatiei
    private void initializeDefaultCategories() {
        Category academicCategory = new Category(
                1,
                "academica",
                "activitati pentru facultate, examene si proiecte academice"
        );

        Category careerCategory = new Category(
                2,
                "profesionala",
                "activitati pentru cariera, skill-uri si dezvoltare profesionala"
        );

        Category personalCategory = new Category(
                3,
                "personala",
                "activitati pentru sine, lectura si dezvoltare personala"
        );

        categoryMap.put(academicCategory.getCategoryName().toLowerCase(), academicCategory);
        categoryMap.put(careerCategory.getCategoryName().toLowerCase(), careerCategory);
        categoryMap.put(personalCategory.getCategoryName().toLowerCase(), personalCategory);
    }

    // returneaza toate categoriile existente
    public List<Category> getAllCategories() {
        return new ArrayList<>(categoryMap.values());
    }

    // cauta categoria dupa nume
    public Category getCategoryByName(String categoryName) {
        if (categoryName == null) {
            return null;
        }

        return categoryMap.get(categoryName.toLowerCase());
    }

    // adauga un learner nou
    public Learner addLearner(String fullName, String emailAddress, String growthDirection) {
        Learner learner = new Learner(
                learnerIdCounter++,
                fullName,
                emailAddress,
                growthDirection
        );

        learners.add(learner);
        return learner;
    }

    // returneaza toti utilizatorii
    public List<Learner> getAllLearners() {
        return learners;
    }

    // adauga un learning point academic
    public LearningPoint addAcademicLearningPoint(String pointTitle, String pointDescription,
                                                  DifficultyLevel difficultyLevel,
                                                  PriorityLevel priorityLevel,
                                                  EnergyLevel requiredEnergyLevel,
                                                  String subjectName) {
        Category academicCategory = getCategoryByName("academica");

        AcademicLearningPoint academicPoint = new AcademicLearningPoint(
                learningPointIdCounter++,
                pointTitle,
                pointDescription,
                difficultyLevel,
                priorityLevel,
                requiredEnergyLevel,
                academicCategory,
                subjectName
        );

        learningPoints.add(academicPoint);
        sortedLearningPoints.add(academicPoint);

        return academicPoint;
    }

    // adauga un learning point profesional
    public LearningPoint addCareerLearningPoint(String pointTitle, String pointDescription,
                                                DifficultyLevel difficultyLevel,
                                                PriorityLevel priorityLevel,
                                                EnergyLevel requiredEnergyLevel,
                                                String skillTrack) {
        Category careerCategory = getCategoryByName("profesionala");

        CareerLearningPoint careerPoint = new CareerLearningPoint(
                learningPointIdCounter++,
                pointTitle,
                pointDescription,
                difficultyLevel,
                priorityLevel,
                requiredEnergyLevel,
                careerCategory,
                skillTrack
        );

        learningPoints.add(careerPoint);
        sortedLearningPoints.add(careerPoint);

        return careerPoint;
    }

    // adauga un learning point personal
    public LearningPoint addPersonalLearningPoint(String pointTitle, String pointDescription,
                                                  DifficultyLevel difficultyLevel,
                                                  PriorityLevel priorityLevel,
                                                  EnergyLevel requiredEnergyLevel,
                                                  String personalFocusArea) {
        Category personalCategory = getCategoryByName("personala");

        PersonalLearningPoint personalPoint = new PersonalLearningPoint(
                learningPointIdCounter++,
                pointTitle,
                pointDescription,
                difficultyLevel,
                priorityLevel,
                requiredEnergyLevel,
                personalCategory,
                personalFocusArea
        );

        learningPoints.add(personalPoint);
        sortedLearningPoints.add(personalPoint);

        return personalPoint;
    }

    // returneaza toate activitatile de invatare
    public List<LearningPoint> getAllLearningPoints() {
        return learningPoints;
    }

    // returneaza activitatile dintr-o anumita categorie
    public List<LearningPoint> getLearningPointsByCategory(String categoryName) {
        List<LearningPoint> filteredPoints = new ArrayList<>();

        for (LearningPoint currentPoint : learningPoints) {
            if (currentPoint.getCategory().getCategoryName().equalsIgnoreCase(categoryName)) {
                filteredPoints.add(currentPoint);
            }
        }

        return filteredPoints;
    }

    // returneaza activitatile sortate automat
    public TreeSet<LearningPoint> getSortedLearningPoints() {
        return sortedLearningPoints;
    }

    // seteaza energia curenta a utilizatorului
    public EnergyState setCurrentEnergyState(EnergyLevel energyLevel, String stateNotes) {
        currentEnergyState = new EnergyState(
                energyStateIdCounter++,
                LocalDate.now(),
                energyLevel,
                stateNotes
        );

        return currentEnergyState;
    }

    // returneaza energia curenta
    public EnergyState getCurrentEnergyState() {
        return currentEnergyState;
    }

    // afiseaza activitatile compatibile cu energia curenta
    public List<LearningPoint> getCompatibleLearningPointsForCurrentEnergy() {
        List<LearningPoint> compatiblePoints = new ArrayList<>();

        if (currentEnergyState == null) {
            return compatiblePoints;
        }

        for (LearningPoint currentPoint : learningPoints) {
            boolean hasEnoughEnergy =
                    currentEnergyState.getEnergyLevel().ordinal() >= currentPoint.getRequiredEnergyLevel().ordinal();

            if (!currentPoint.isCompleted() && hasEnoughEnergy) {
                compatiblePoints.add(currentPoint);
            }
        }

        return compatiblePoints;
    }

    // adauga un milestone nou
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

    // returneaza toate milestone-urile
    public List<Milestone> getAllMilestones() {
        return new ArrayList<>(milestoneMap.values());
    }

    // returneaza doar milestone-urile active
    public List<Milestone> getActiveMilestones() {
        List<Milestone> activeMilestones = new ArrayList<>();

        for (Milestone currentMilestone : milestoneMap.values()) {
            if (!currentMilestone.isCompleted()) {
                activeMilestones.add(currentMilestone);
            }
        }

        return activeMilestones;
    }

    // actualizeaza progresul unui milestone si salveaza istoricul
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

    // returneaza istoricul progresului
    public List<ProgressRecord> getProgressHistory() {
        return progressHistory;
    }

    // creeaza o sesiune de studiu pentru un learning point
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

    // returneaza toate sesiunile
    public List<StudySession> getAllStudySessions() {
        return studySessions;
    }

    // marcheaza o sesiune ca finalizata
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

    // genereaza recomandarea smart next point
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

            boolean hasEnoughEnergy =
                    currentEnergyState.getEnergyLevel().ordinal() >= currentPoint.getRequiredEnergyLevel().ordinal();

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

        String whyThisPoint =
                "recomandarea a fost generata pe baza energiei curente, a prioritatii si a dificultatii activitatii";

        NextPoint nextPoint = new NextPoint(
                nextPointIdCounter++,
                bestPoint,
                whyThisPoint,
                bestScore
        );

        nextPointHistory.add(nextPoint);
        return nextPoint;
    }

    // calculeaza scorul unei recomandari
    private int calculateRecommendationScore(LearningPoint learningPoint, EnergyLevel currentEnergyLevel) {
        int score = 0;

        // prioritatea conteaza mult
        score += learningPoint.getPriorityLevel().ordinal() * 10;

        // dificultatea conteaza moderat
        score += learningPoint.getDifficultyLevel().ordinal() * 5;

        // daca energia se potriveste perfect, dam bonus
        if (learningPoint.getRequiredEnergyLevel() == currentEnergyLevel) {
            score += 20;
        } else if (currentEnergyLevel.ordinal() > learningPoint.getRequiredEnergyLevel().ordinal()) {
            score += 10;
        }

        return score;
    }

    // cauta un learning point dupa id
    public LearningPoint findLearningPointById(int learningPointId) {
        for (LearningPoint currentPoint : learningPoints) {
            if (currentPoint.getId() == learningPointId) {
                return currentPoint;
            }
        }

        return null;
    }

    // adauga cateva date demo pentru testare rapida
    public void seedSampleData() {
        addLearner(
                "andreea cacenschi",
                "andreea@example.com",
                "software development"
        );

        addAcademicLearningPoint(
                "pregatire examen java",
                "recapitulare mostenire, colectii si clase abstracte",
                DifficultyLevel.HARD,
                PriorityLevel.HIGH,
                EnergyLevel.HIGH,
                "programare avansata pe obiecte"
        );

        addCareerLearningPoint(
                "exersare java oop",
                "rezolvare exercitii pentru clase, obiecte si metode",
                DifficultyLevel.MEDIUM,
                PriorityLevel.HIGH,
                EnergyLevel.MEDIUM,
                "java"
        );

        addPersonalLearningPoint(
                "lectura de seara",
                "citirea unui capitol pentru echilibru si reflectie",
                DifficultyLevel.EASY,
                PriorityLevel.MEDIUM,
                EnergyLevel.LOW,
                "lectura"
        );

        addMilestone(
                "bazele java",
                "parcurgerea notiunilor de baza pentru proiect",
                10,
                LocalDate.now().plusDays(14)
        );

        setCurrentEnergyState(
                EnergyLevel.MEDIUM,
                "energie buna pentru activitati moderate"
        );
    }
}