package app;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import model.AcademicLearningPoint;
import model.AssessmentType;
import model.CareerLearningPoint;
import model.DifficultyLevel;
import model.EnergyLevel;
import model.IndustryLevel;
import model.LearningPoint;
import model.Milestone;
import model.NextPoint;
import model.PersonalLearningPoint;
import model.PriorityLevel;
import model.ProgressRecord;
import model.ReflectionFrequency;
import model.StudySession;
import model.WellnessCategory;
import service.Point;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Point point = new Point();

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("alegerea ta: ");
            String chosenOption = scanner.nextLine();

            switch (chosenOption) {
                case "1":
                    addLearner(point);
                    break;
                case "2":
                    addLearningPoint(point);
                    break;
                case "3":
                    showAllLearningPoints(point);
                    break;
                case "4":
                    showLearningPointsByCategory(point);
                    break;
                case "5":
                    setCurrentEnergyState(point);
                    break;
                case "6":
                    showCompatibleLearningPoints(point);
                    break;
                case "7":
                    generateNextPoint(point);
                    break;
                case "8":
                    addMilestone(point);
                    break;
                case "9":
                    showActiveMilestones(point);
                    break;
                case "10":
                    updateMilestoneProgress(point);
                    break;
                case "11":
                    createStudySession(point);
                    break;
                case "12":
                    showAllStudySessions(point);
                    break;
                case "13":
                    markStudySessionCompleted(point);
                    break;
                case "14":
                    showSortedLearningPoints(point);
                    break;
                case "15":
                    showProgressHistory(point);
                    break;
                case "16":
                    showGeneralStatistics(point);
                    break;
                case "17":
                    showAcademicAnalytics(point);
                    break;
                case "18":
                    showCareerAnalytics(point);
                    break;
                case "19":
                    showWellnessAnalytics(point);
                    break;
                case "0":
                    running = false;
                    System.out.println("ai iesit din point. bye!");
                    break;
                default:
                    System.out.println("hmm... punctul asta nu exista in meniu. incearca din nou.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("point.");
        System.out.println("        organizeaza-ti invatarea dupa energie, nu doar timp   ");
        System.out.println("alege urmatorul punct din traseul tau:");
        System.out.println();

        System.out.println("[ start ]");
        System.out.println("1  - cine invata? adauga learner");

        System.out.println();
        System.out.println("[ learning points ]");
        System.out.println("2  - adauga un learning point nou");
        System.out.println("3  - vezi toate learning point-urile");
        System.out.println("4  - vezi learning point-urile dintr-o categorie");
        System.out.println("14 - vezi learning point-urile ordonate");

        System.out.println();
        System.out.println("[ energy + next point ]");
        System.out.println("5  - seteaza-ti energia curenta");
        System.out.println("6  - vezi ce se potriveste cu energia ta acum");
        System.out.println("7  - lasa aplicatia sa-ti sugereze urmatorul punct");

        System.out.println();
        System.out.println("[ milestones + progres ]");
        System.out.println("8  - adauga un milestone");
        System.out.println("9  - vezi milestone-urile active");
        System.out.println("10 - actualizeaza progresul unui milestone");
        System.out.println("15 - vezi istoricul progresului");

        System.out.println();
        System.out.println("[ study sessions ]");
        System.out.println("11 - creeaza o sesiune de studiu");
        System.out.println("12 - vezi toate sesiunile");
        System.out.println("13 - marcheaza o sesiune ca finalizata");

        System.out.println();
        System.out.println("[ analytics & insights ]");
        System.out.println("16 - statistici generale (academic/career/personal breakdown)");
        System.out.println("17 - analiza academica (credite, evaluari)");
        System.out.println("18 - analiza cariera (progresie, salariu, mentor)");
        System.out.println("19 - analiza wellness (reflexie, categorii)");

        System.out.println();
        System.out.println("[ exit ]");
        System.out.println("0  - iesire");
    }

    private static void addLearner(Point point) {
        System.out.println();
        System.out.println("hai sa vedem cine foloseste aplicatia.");

        System.out.print("nume complet: ");
        String fullName = scanner.nextLine();

        System.out.print("email: ");
        String emailAddress = scanner.nextLine();

        System.out.println();
        System.out.println("learner adaugat cu succes:");
        System.out.println(point.addLearner(fullName, emailAddress));
    }

    private static void addLearningPoint(Point point) {
        System.out.println();
        System.out.println("ce fel de punct vrei sa adaugi in traseul tau?");
        System.out.println("alegeri posibile: 1 = academic, 2 = professional, 3 = personal");
        System.out.print("tipul ales: ");
        String pointType = scanner.nextLine();

        System.out.print("titlu (ex: istoria artei / java / certificare / public speaking): ");
        String pointTitle = scanner.nextLine();

        System.out.print("descriere: ");
        String pointDescription = scanner.nextLine();

        DifficultyLevel difficultyLevel = readDifficultyLevel();
        PriorityLevel priorityLevel = readPriorityLevel();
        EnergyLevel requiredEnergyLevel = readEnergyLevel();

        System.out.println();

        switch (pointType) {
            case "1":
                addAcademicLearningPoint(point, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel);
                break;
            case "2":
                addCareerLearningPoint(point, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel);
                break;
            case "3":
                addPersonalLearningPoint(point, pointTitle, pointDescription, difficultyLevel, priorityLevel, requiredEnergyLevel);
                break;
            default:
                System.out.println("tip invalid. punctul nu a fost adaugat...");
        }
    }

    // collect Academic point specific attributes
    private static void addAcademicLearningPoint(Point point, String title, String description,
            DifficultyLevel difficulty, PriorityLevel priority, EnergyLevel energy) {
        System.out.println(" Academic Learning Point Details");

        System.out.print("subiectul (ex: algoritmi, fizica, analiza): ");
        String subjectName = scanner.nextLine();

        System.out.print("puncte credit (ex: 300.0): ");
        double creditPoints = Double.parseDouble(scanner.nextLine());

        AssessmentType assessmentType = readAssessmentType();

        System.out.print("nota tinta (ex: A, B+, C): ");
        String targetGrade = scanner.nextLine();

        System.out.println();
        System.out.println("academic point adaugat:");
        System.out.println(point.addAcademicLearningPoint(
                title, description, difficulty, priority, energy,
                subjectName, creditPoints, assessmentType, targetGrade
        ));
    }

    // collect career point specific attributes
    private static void addCareerLearningPoint(Point point, String title, String description,
            DifficultyLevel difficulty, PriorityLevel priority, EnergyLevel energy) {
        System.out.println("Career/Professional Learning Point Details");

        System.out.print("skill track (ex: backend, data science, leadership): ");
        String skillTrack = scanner.nextLine();

        System.out.print("certificari necesare (ex: AWS certified, PMP, none): ");
        String requiredCertifications = scanner.nextLine();

        IndustryLevel industryLevel = readIndustryLevel();

        System.out.print("e nevoie de mentor? (da/nu): ");
        String mentorInput = scanner.nextLine().toLowerCase();
        boolean mentorRequired = mentorInput.equals("da") || mentorInput.equals("yes");

        System.out.print("estimare impact salariu % (ex: 15.5): ");
        double salaryImpact = Double.parseDouble(scanner.nextLine());

        System.out.println();
        System.out.println("professional point adaugat:");
        System.out.println(point.addCareerLearningPoint(
                title, description, difficulty, priority, energy,
                skillTrack, requiredCertifications, industryLevel, mentorRequired, salaryImpact
        ));
    }

    // collect personal point specific attributes
    private static void addPersonalLearningPoint(Point point, String title, String description,
            DifficultyLevel difficulty, PriorityLevel priority, EnergyLevel energy) {
        System.out.println("Personal Development Learning Point Details");

        System.out.print("area de focus (ex: meditatie, lectura, fitness, socializare): ");
        String personalFocusArea = scanner.nextLine();

        WellnessCategory wellnessCategory = readWellnessCategory();

        ReflectionFrequency reflectionFrequency = readReflectionFrequency();

        System.out.print("metrici de succes (ex: 30 zile consistente, 1 carte/luna, 10k pasi/zi): ");
        String successMetrics = scanner.nextLine();

        System.out.println();
        System.out.println("personal point adaugat:");
        System.out.println(point.addPersonalLearningPoint(
                title, description, difficulty, priority, energy,
                personalFocusArea, wellnessCategory, reflectionFrequency, successMetrics
        ));
    }

    // afiseaza toate learning point-urile
    private static void showAllLearningPoints(Point point) {
        List<LearningPoint> learningPoints = point.getAllLearningPoints();

        System.out.println();
        System.out.println("toate punctele. tale de invatare:");

        if (learningPoints.isEmpty()) {
            System.out.println("inca nu ai adaugat niciun learning point.");
            return;
        }

        for (LearningPoint currentPoint : learningPoints) {
            System.out.println(currentPoint);
        }
    }

    private static void showLearningPointsByCategory(Point point) {
        System.out.println();
        System.out.print("alege categoria (alegeri posibile: academic / career / personal): ");
        String categoryName = scanner.nextLine();

        List<LearningPoint> filteredPoints = point.getLearningPointsByCategory(categoryName);

        System.out.println();
        System.out.println("puncte gasite in categoria \"" + categoryName + "\":");

        if (filteredPoints.isEmpty()) {
            System.out.println("nu exista activitati in categoria asta");
            return;
        }

        for (LearningPoint currentPoint : filteredPoints) {
            System.out.println(currentPoint);
        }
    }

    private static void setCurrentEnergyState(Point point) {
        System.out.println();
        System.out.println("cum te simti azi? care crezi ca e levelul tau de energie LOW, MEDIUM sau HIGH?");
        EnergyLevel currentEnergyLevel = readEnergyLevel();

        System.out.print("de ce crezi asta? (ex: azi am energie / sunt obosit si vreau ceva usor): ");
        String stateNotes = scanner.nextLine();

        System.out.println();
        System.out.println("energy state actualizat:");
        System.out.println(point.setCurrentEnergyState(currentEnergyLevel, stateNotes));
    }

    private static void showCompatibleLearningPoints(Point point) {
        List<LearningPoint> compatiblePoints = point.getCompatibleLearningPointsForCurrentEnergy();

        System.out.println();
        System.out.println("ce se potriveste cu energia ta acum:");

        if (compatiblePoints.isEmpty()) {
            System.out.println("nu exista activitati compatibile sau nu ai setat energia curenta.");
            return;
        }

        for (LearningPoint currentPoint : compatiblePoints) {
            System.out.println(currentPoint);
        }
    }

    private static void generateNextPoint(Point point) {
        NextPoint nextPoint = point.generateNextPointRecommendation();

        System.out.println();
        System.out.println("next point pentru momentul asta:");

        if (nextPoint == null) {
            System.out.println("nu s-a putut genera o recomandare");
            return;
        }

        System.out.println(nextPoint);
    }

    // adauga un milestone
    private static void addMilestone(Point point) {
        System.out.println();
        System.out.println("adauga un milestone nou");

        System.out.print("titlu milestone (ex: bazele criptografiei / lectura lunii / proiect oop): ");
        String milestoneTitle = scanner.nextLine();

        System.out.print("descriere milestone (ex: sa inteleg cum functioneaza hashing-ul in criptografie): ");
        String milestoneDescription = scanner.nextLine();

        System.out.print("valoare tinta (ex: 10 [capitole terminate]/ 100 [pagini citite] / 5 [exercitii rezolvate]): ");
        int targetValue = Integer.parseInt(scanner.nextLine());

        System.out.print("deadline in format yyyy-mm-dd (ex: 2026-05-15): ");
        LocalDate deadline = LocalDate.parse(scanner.nextLine());

        System.out.println();
        System.out.println("milestone adaugat:");
        System.out.println(point.addMilestone(
                milestoneTitle,
                milestoneDescription,
                targetValue,
                deadline
        ));
    }

    private static void showActiveMilestones(Point point) {
        List<Milestone> activeMilestones = point.getActiveMilestones();

        System.out.println();
        System.out.println("milestone-uri active:");

        if (activeMilestones.isEmpty()) {
            System.out.println("nu ai milestone-uri active momentan");
            return;
        }

        for (Milestone currentMilestone : activeMilestones) {
            System.out.println(currentMilestone);
        }
    }

    // actualizeaza progresul unui milestone
    private static void updateMilestoneProgress(Point point) {
        System.out.println();
        System.out.println("actualizeaza progresul unui milestone.");

        System.out.print("id milestone (ex: 1): ");
        int milestoneId = Integer.parseInt(scanner.nextLine());

        System.out.print("noua valoare a progresului (ex: 4 / 20 / 80): ");
        int updatedValue = Integer.parseInt(scanner.nextLine());

        System.out.print("note despre progres (ex: am terminat capitolul despre Renastere): ");
        String updateNotes = scanner.nextLine();

        boolean updated = point.updateMilestoneProgress(
                milestoneId,
                updatedValue,
                updateNotes
        );

        System.out.println();

        if (updated) {
            System.out.println("progres actualizat cu succes");
        } else {
            System.out.println("milestone inexistent");
        }
    }

    // creeaza o sesiune noua de studiu
    private static void createStudySession(Point point) {
        System.out.println();
        System.out.println("creezi o sesiune noua");
        System.out.println("mai intai, uite ce learning point-uri exista:");

        showAllLearningPoints(point);

        System.out.println();
        System.out.print("id learning point ales (ex: 1): ");
        int learningPointId = Integer.parseInt(scanner.nextLine());

        System.out.print("data sesiunii in format yyyy-mm-dd (ex: 2026-04-22): ");
        LocalDate sessionDate = LocalDate.parse(scanner.nextLine());

        System.out.print("cate minute planifici? (ex: 30 / 60 / 90): ");
        int plannedMinutes = Integer.parseInt(scanner.nextLine());

        System.out.print("note pentru sesiune (ex: focus pe recapitulare oop): ");
        String sessionNotes = scanner.nextLine();

        StudySession studySession = point.createStudySession(
                learningPointId,
                sessionDate,
                plannedMinutes,
                sessionNotes
        );

        System.out.println();

        if (studySession == null) {
            System.out.println("nu s-a putut crea sesiunea. verifica id-ul.");
        } else {
            System.out.println("study session creata:");
            System.out.println(studySession);
        }
    }

    // afiseaza toate sesiunile
    private static void showAllStudySessions(Point point) {
        List<StudySession> studySessions = point.getAllStudySessions();

        System.out.println();
        System.out.println("toate sesiunile tale:");

        if (studySessions.isEmpty()) {
            System.out.println("nu exista study session-uri.");
            return;
        }

        for (StudySession currentSession : studySessions) {
            System.out.println(currentSession);
        }
    }

    // marcheaza o sesiune ca finalizata
    private static void markStudySessionCompleted(Point point) {
        System.out.println();
        System.out.println("marcheaza o sesiune ca finalizata.");

        System.out.print("id sesiune (ex: 1): ");
        int sessionId = Integer.parseInt(scanner.nextLine());

        boolean updated = point.markStudySessionCompleted(sessionId);

        System.out.println();

        if (updated) {
            System.out.println("gata, sesiunea a fost marcata ca finalizata.");
        } else {
            System.out.println("sesiune inexistenta.");
        }
    }

    private static void showSortedLearningPoints(Point point) {
        System.out.println();
        System.out.println("learning point-uri ordonate:");

        if (point.getSortedLearningPoints().isEmpty()) {
            System.out.println("nu exista activitati.");
            return;
        }

        for (LearningPoint currentPoint : point.getSortedLearningPoints()) {
            System.out.println(currentPoint);
        }
    }

    // afiseaza istoricul de progres
    private static void showProgressHistory(Point point) {
        List<ProgressRecord> progressHistory = point.getProgressHistory();

        System.out.println();
        System.out.println("istoricul tau de progres:");

        if (progressHistory.isEmpty()) {
            System.out.println("inca nu exista inregistrari de progres.");
            return;
        }

        for (ProgressRecord currentRecord : progressHistory) {
            System.out.println(currentRecord);
        }
    }

    // display general breakdown statistics
    private static void showGeneralStatistics(Point point) {
        System.out.println();
        System.out.println("STATISTICI GENERALE");
        System.out.println(point.getLearningPointTypeBreakdown());
        System.out.println();
    }

    // display academic-specific insights
    private static void showAcademicAnalytics(Point point) {
        System.out.println();
        System.out.println("ANALIZA ACADEMICA");

        List<AcademicLearningPoint> academicPoints = point.getAllAcademicPoints();

        if (academicPoints.isEmpty()) {
            System.out.println("nu exista learning points academice.");
            System.out.println();
            return;
        }

        System.out.println("\nDETALII PUNCTE ACADEMICE");
        for (AcademicLearningPoint ap : academicPoints) {
            System.out.println("\n  Titlu: " + ap.getPointTitle());
            System.out.println("  Subiect: " + ap.getSubjectName());
            System.out.println("  Tip evaluare: " + ap.getAssessmentType().getDescription());
            System.out.println("  Nota tinta: " + ap.getTargetGrade());
            System.out.println("  Credite: " + ap.getCreditPoints());
            
            System.out.println("  Evaluare completa? " + (ap.isAssessmentComplete() ? "DA" : "NU"));
            
            System.out.println("  Credite estimate:");
            System.out.println("    - La 45% (fail): " + ap.calculateCreditsEarned(45) + " ");
            System.out.println("    - La 55% (pass slab): " + ap.calculateCreditsEarned(55) + " ");
            System.out.println("    - La 75% (bine): " + ap.calculateCreditsEarned(75) + " ");
            System.out.println("    - La 90% (excellent): " + ap.calculateCreditsEarned(90) + " ");
        }

        double totalCredits = point.calculateTotalAcademicCreditsEarned();
        System.out.println("\nREZUMAT");
        System.out.println(String.format("TOTAL CREDITE ACADEMICE (estimate 80%%): %.2f ", totalCredits));
        System.out.println();
    }

    // Display career-specific insights
    private static void showCareerAnalytics(Point point) {
        System.out.println();
        System.out.println("ANALIZA CARIERA");

        List<String> careerProgression = point.getCareerProgressionSummary();

        if (careerProgression.isEmpty()) {
            System.out.println("nu exista learning points de cariera.");
            System.out.println();
            return;
        }

        System.out.println("\nPROGRESIE CARIERA");
        for (String progression : careerProgression) {
            System.out.println("  " + progression);
        }

        double salaryImpact = point.calculateTotalSalaryImpact();
        List<CareerLearningPoint> mentorNeeded = point.getCareerPointsRequiringMentor();

        System.out.println("\nDETALII PUNCTE PROFESIONALE");
        for (String summary : careerProgression) {
            // Extract and show individual point details
            System.out.println("  • " + summary);
        }

        System.out.println("\n ANALIZA CERIFICATII & MENTOR ");
        for (CareerLearningPoint cp : point.getAllCareerPoints()) {
            // USE hasPrerequisiteCertifications() method
            boolean hasPrerequisites = cp.hasPrerequisiteCertifications();
            System.out.println("\n  Skill: " + cp.getSkillTrack());
            System.out.println("  Certificari necesare: " + (cp.getRequiredCertifications().isEmpty() ? "NONE" : cp.getRequiredCertifications()));
            System.out.println("  Prerequisite satisfacute? " + (hasPrerequisites ? "DA" : "NU"));
            System.out.println("  Mentor necesar? " + (cp.isMentorRequired() ? "DA" : "NU"));
        }

        System.out.println("\nREZUMAT ");
        System.out.println(String.format("TOTAL SALARY IMPACT POTENTIAL: +%.1f%%", salaryImpact));
        System.out.println("Puncte care necesita mentor: " + mentorNeeded.size());
        
        if (!mentorNeeded.isEmpty()) {
            for (CareerLearningPoint cp : mentorNeeded) {
                System.out.println("    * " + cp.getPointTitle() + " (" + cp.getIndustryLevel() + ")");
            }
        }
        System.out.println();
    }

    // personal wellness insights
    private static void showWellnessAnalytics(Point point) {
        System.out.println();
        System.out.println("ANALIZA WELLNESS & REFLEXIE");

        List<String> reflectionSchedule = point.getPersonalPointsReflectionSchedule();

        if (reflectionSchedule.isEmpty()) {
            System.out.println("nu exista learning points personale.");
            System.out.println();
            return;
        }

        System.out.println("\n CALENDAR DE REFLEXIE");
        for (String schedule : reflectionSchedule) {
            System.out.println("  " + schedule);
        }

        System.out.println("\n DETALII PUNCTE PERSONALE");
        for (PersonalLearningPoint pp : point.getAllPersonalPoints()) {
            System.out.println("\n  Focus area: " + pp.getPersonalFocusArea());
            
            System.out.println("  Wellness category: " + pp.getWellnessDescription());
            
            System.out.println("  Frecventa reflexie: " + pp.getReflectionFrequency());
            System.out.println("  Metrici succes: " + pp.getSuccessMetrics());
            
            LocalDate nextReflection = pp.scheduleNextReflection();
            LocalDate today = LocalDate.now();
            long daysUntilReflection = java.time.temporal.ChronoUnit.DAYS.between(today, nextReflection);
            
            System.out.println("  Ultima reflexie: " + pp.getLastReflectionDate());
            System.out.println("  Urmatoarea reflexie: " + nextReflection);
            System.out.println("  Zile pana la reflexie: " + daysUntilReflection);
            System.out.println("  Status: " + (pp.isCompleted() ? "COMPLETED" : "IN PROGRESS"));
        }

        System.out.println("\nDISTRIBUTIE WELLNESS");
        for (WellnessCategory category : WellnessCategory.values()) {
            List<PersonalLearningPoint> categoryPoints = point.getPersonalPointsByWellnessCategory(category);
            System.out.println("  " + category + " (" + categoryPoints.size() + " points) - " + category.getDescription());
        }
        System.out.println();
    }

    // citeste dificultatea
    private static DifficultyLevel readDifficultyLevel() {
        System.out.println();
        System.out.println("nivel de dificultate - alegeri posibile: 1 = easy, 2 = medium, 3 = hard");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return DifficultyLevel.EASY;
            case "2":
                return DifficultyLevel.MEDIUM;
            case "3":
                return DifficultyLevel.HARD;
            default:
                System.out.println("optiune invalida. mergem pe medium.");
                return DifficultyLevel.MEDIUM;
        }
    }

    private static PriorityLevel readPriorityLevel() {
        System.out.println();
        System.out.println("nivel de prioritate - alegeri posibile: 1 = low, 2 = medium, 3 = high");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return PriorityLevel.LOW;
            case "2":
                return PriorityLevel.MEDIUM;
            case "3":
                return PriorityLevel.HIGH;
            default:
                System.out.println("optiune invalida. mergem pe medium.");
                return PriorityLevel.MEDIUM;
        }
    }

    private static EnergyLevel readEnergyLevel() {
        System.out.println();
        System.out.println("nivel de energie - alegeri posibile: 1 = low, 2 = medium, 3 = high");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return EnergyLevel.LOW;
            case "2":
                return EnergyLevel.MEDIUM;
            case "3":
                return EnergyLevel.HIGH;
            default:
                System.out.println("optiune invalida. mergem pe medium.");
                return EnergyLevel.MEDIUM;
        }
    }

    private static AssessmentType readAssessmentType() {
        System.out.println();
        System.out.println("tip evaluare - alegeri: 1 = exam, 2 = project, 3 = presentation, 4 = assignment");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return AssessmentType.EXAM;
            case "2":
                return AssessmentType.PROJECT;
            case "3":
                return AssessmentType.PRESENTATION;
            case "4":
                return AssessmentType.ASSIGNMENT;
            default:
                System.out.println("optiune invalida. mergem pe ASSIGNMENT.");
                return AssessmentType.ASSIGNMENT;
        }
    }

    private static IndustryLevel readIndustryLevel() {
        System.out.println();
        System.out.println("nivel skill - alegeri: 1 = beginner, 2 = intermediate, 3 = advanced, 4 = expert");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return IndustryLevel.BEGINNER;
            case "2":
                return IndustryLevel.INTERMEDIATE;
            case "3":
                return IndustryLevel.ADVANCED;
            case "4":
                return IndustryLevel.EXPERT;
            default:
                System.out.println("optiune invalida. mergem pe INTERMEDIATE.");
                return IndustryLevel.INTERMEDIATE;
        }
    }

    private static WellnessCategory readWellnessCategory() {
        System.out.println();
        System.out.println("categorie wellness - alegeri: 1 = physical, 2 = mental, 3 = emotional, 4 = social, 5 = spiritual");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return WellnessCategory.PHYSICAL;
            case "2":
                return WellnessCategory.MENTAL;
            case "3":
                return WellnessCategory.EMOTIONAL;
            case "4":
                return WellnessCategory.SOCIAL;
            case "5":
                return WellnessCategory.SPIRITUAL;
            default:
                System.out.println("optiune invalida. mergem pe MENTAL.");
                return WellnessCategory.MENTAL;
        }
    }

    private static ReflectionFrequency readReflectionFrequency() {
        System.out.println();
        System.out.println("frecventa reflectie - alegeri: 1 = weekly, 2 = biweekly, 3 = monthly");
        System.out.print("alegerea ta: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return ReflectionFrequency.WEEKLY;
            case "2":
                return ReflectionFrequency.BIWEEKLY;
            case "3":
                return ReflectionFrequency.MONTHLY;
            default:
                System.out.println("optiune invalida. mergem pe WEEKLY.");
                return ReflectionFrequency.WEEKLY;
        }
    }
}
