package app;

import model.DifficultyLevel;
import model.EnergyLevel;
import model.LearningPoint;
import model.Milestone;
import model.NextPoint;
import model.PriorityLevel;
import model.ProgressRecord;
import model.StudySession;
import service.PointService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// clasa principala din care porneste aplicatia
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PointService pointService = new PointService();

        // incarcam cateva date demo pentru testare rapida
        pointService.seedSampleData();

        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("introdu numarul optiunii alese: ");
            String chosenOption = scanner.nextLine();

            switch (chosenOption) {
                case "1":
                    addLearner(pointService);
                    break;
                case "2":
                    addLearningPoint(pointService);
                    break;
                case "3":
                    showAllLearningPoints(pointService);
                    break;
                case "4":
                    showLearningPointsByCategory(pointService);
                    break;
                case "5":
                    setCurrentEnergyState(pointService);
                    break;
                case "6":
                    showCompatibleLearningPoints(pointService);
                    break;
                case "7":
                    generateNextPoint(pointService);
                    break;
                case "8":
                    addMilestone(pointService);
                    break;
                case "9":
                    showActiveMilestones(pointService);
                    break;
                case "10":
                    updateMilestoneProgress(pointService);
                    break;
                case "11":
                    createStudySession(pointService);
                    break;
                case "12":
                    showAllStudySessions(pointService);
                    break;
                case "13":
                    markStudySessionCompleted(pointService);
                    break;
                case "14":
                    showSortedLearningPoints(pointService);
                    break;
                case "15":
                    showProgressHistory(pointService);
                    break;
                case "0":
                    running = false;
                    System.out.println("aplicatia point s-a inchis.");
                    break;
                default:
                    System.out.println("optiune invalida. incearca din nou.");
            }

            System.out.println();
        }

        scanner.close();
    }

    // afiseaza meniul principal grupat pe zone
    private static void printMenu() {
        System.out.println();
        System.out.println("======================================================");
        System.out.println("point - aplicatie smart pentru invatare bazata pe energie");
        System.out.println("======================================================");
        System.out.println("alege numarul actiunii dorite:");
        System.out.println();

        System.out.println("[ learner ]");
        System.out.println("1  - adauga learner");

        System.out.println();
        System.out.println("[ learning points ]");
        System.out.println("2  - adauga learning point");
        System.out.println("3  - afiseaza toate learning point-urile");
        System.out.println("4  - afiseaza learning point-urile dupa categorie");
        System.out.println("14 - afiseaza learning point-urile sortate");

        System.out.println();
        System.out.println("[ energy + recomandare ]");
        System.out.println("5  - seteaza energia curenta");
        System.out.println("6  - afiseaza activitatile compatibile cu energia curenta");
        System.out.println("7  - genereaza recomandarea next point");

        System.out.println();
        System.out.println("[ milestone + progres ]");
        System.out.println("8  - adauga milestone");
        System.out.println("9  - afiseaza milestone-urile active");
        System.out.println("10 - actualizeaza progresul unui milestone");
        System.out.println("15 - afiseaza istoricul de progres");

        System.out.println();
        System.out.println("[ study sessions ]");
        System.out.println("11 - creeaza study session");
        System.out.println("12 - afiseaza toate study session-urile");
        System.out.println("13 - marcheaza o sesiune ca finalizata");

        System.out.println();
        System.out.println("[ sistem ]");
        System.out.println("0  - iesire");
        System.out.println("======================================================");
    }

    // adauga un utilizator nou
    private static void addLearner(PointService pointService) {
        System.out.println();
        System.out.println("adauga learner nou");
        System.out.print("nume complet: ");
        String fullName = scanner.nextLine();

        System.out.print("email: ");
        String emailAddress = scanner.nextLine();

        System.out.print("directie de crestere academica / profesionala / personala: ");
        String growthDirection = scanner.nextLine();

        System.out.println();
        System.out.println("learner adaugat cu succes:");
        System.out.println(pointService.addLearner(fullName, emailAddress, growthDirection));
    }

    // adauga un learning point nou
    private static void addLearningPoint(PointService pointService) {
        System.out.println();
        System.out.println("ce tip de learning point vrei sa adaugi?");
        System.out.println("1 - academic");
        System.out.println("2 - profesional");
        System.out.println("3 - personal");
        System.out.print("introdu numarul tipului ales: ");
        String pointType = scanner.nextLine();

        System.out.print("titlu: ");
        String pointTitle = scanner.nextLine();

        System.out.print("descriere: ");
        String pointDescription = scanner.nextLine();

        DifficultyLevel difficultyLevel = readDifficultyLevel();
        PriorityLevel priorityLevel = readPriorityLevel();
        EnergyLevel requiredEnergyLevel = readEnergyLevel();

        System.out.print("detaliu specific pentru acest tip de activitate: ");
        String specificDetail = scanner.nextLine();

        System.out.println();

        switch (pointType) {
            case "1":
                System.out.println("learning point academic adaugat:");
                System.out.println(pointService.addAcademicLearningPoint(
                        pointTitle,
                        pointDescription,
                        difficultyLevel,
                        priorityLevel,
                        requiredEnergyLevel,
                        specificDetail
                ));
                break;
            case "2":
                System.out.println("learning point profesional adaugat:");
                System.out.println(pointService.addCareerLearningPoint(
                        pointTitle,
                        pointDescription,
                        difficultyLevel,
                        priorityLevel,
                        requiredEnergyLevel,
                        specificDetail
                ));
                break;
            case "3":
                System.out.println("learning point personal adaugat:");
                System.out.println(pointService.addPersonalLearningPoint(
                        pointTitle,
                        pointDescription,
                        difficultyLevel,
                        priorityLevel,
                        requiredEnergyLevel,
                        specificDetail
                ));
                break;
            default:
                System.out.println("tip invalid. learning point-ul nu a fost adaugat.");
        }
    }

    // afiseaza toate activitatile de invatare
    private static void showAllLearningPoints(PointService pointService) {
        List<LearningPoint> learningPoints = pointService.getAllLearningPoints();

        System.out.println();
        System.out.println("toate learning point-urile:");

        if (learningPoints.isEmpty()) {
            System.out.println("nu exista learning point-uri.");
            return;
        }

        for (LearningPoint currentPoint : learningPoints) {
            System.out.println(currentPoint);
        }
    }

    // afiseaza activitatile filtrate dupa categorie
    private static void showLearningPointsByCategory(PointService pointService) {
        System.out.println();
        System.out.print("introdu categoria dorita (academica / profesionala / personala): ");
        String categoryName = scanner.nextLine();

        List<LearningPoint> filteredPoints = pointService.getLearningPointsByCategory(categoryName);

        System.out.println();
        System.out.println("rezultate pentru categoria: " + categoryName);

        if (filteredPoints.isEmpty()) {
            System.out.println("nu exista activitati in aceasta categorie.");
            return;
        }

        for (LearningPoint currentPoint : filteredPoints) {
            System.out.println(currentPoint);
        }
    }

    // seteaza energia curenta a utilizatorului
    private static void setCurrentEnergyState(PointService pointService) {
        System.out.println();
        System.out.println("seteaza energia curenta");
        EnergyLevel currentEnergyLevel = readEnergyLevel();

        System.out.print("note despre starea ta curenta: ");
        String stateNotes = scanner.nextLine();

        System.out.println();
        System.out.println("energy state actualizat:");
        System.out.println(pointService.setCurrentEnergyState(currentEnergyLevel, stateNotes));
    }

    // afiseaza activitatile potrivite pentru energia curenta
    private static void showCompatibleLearningPoints(PointService pointService) {
        List<LearningPoint> compatiblePoints = pointService.getCompatibleLearningPointsForCurrentEnergy();

        System.out.println();
        System.out.println("activitati compatibile cu energia curenta:");

        if (compatiblePoints.isEmpty()) {
            System.out.println("nu exista activitati compatibile sau energia nu este setata.");
            return;
        }

        for (LearningPoint currentPoint : compatiblePoints) {
            System.out.println(currentPoint);
        }
    }

    // genereaza recomandarea smart
    private static void generateNextPoint(PointService pointService) {
        NextPoint nextPoint = pointService.generateNextPointRecommendation();

        System.out.println();
        System.out.println("recomandarea smart next point:");

        if (nextPoint == null) {
            System.out.println("nu s-a putut genera o recomandare.");
            return;
        }

        System.out.println(nextPoint);
    }

    // adauga un milestone nou
    private static void addMilestone(PointService pointService) {
        System.out.println();
        System.out.println("adauga milestone nou");

        System.out.print("titlu milestone: ");
        String milestoneTitle = scanner.nextLine();

        System.out.print("descriere milestone: ");
        String milestoneDescription = scanner.nextLine();

        System.out.print("valoare tinta: ");
        int targetValue = Integer.parseInt(scanner.nextLine());

        System.out.print("deadline (format yyyy-mm-dd): ");
        LocalDate deadline = LocalDate.parse(scanner.nextLine());

        System.out.println();
        System.out.println("milestone adaugat:");
        System.out.println(pointService.addMilestone(
                milestoneTitle,
                milestoneDescription,
                targetValue,
                deadline
        ));
    }

    // afiseaza milestone-urile active
    private static void showActiveMilestones(PointService pointService) {
        List<Milestone> activeMilestones = pointService.getActiveMilestones();

        System.out.println();
        System.out.println("milestone-uri active:");

        if (activeMilestones.isEmpty()) {
            System.out.println("nu exista milestone-uri active.");
            return;
        }

        for (Milestone currentMilestone : activeMilestones) {
            System.out.println(currentMilestone);
        }
    }

    // actualizeaza progresul unui milestone
    private static void updateMilestoneProgress(PointService pointService) {
        System.out.println();
        System.out.println("actualizeaza progresul unui milestone");

        System.out.print("id milestone: ");
        int milestoneId = Integer.parseInt(scanner.nextLine());

        System.out.print("noua valoare a progresului: ");
        int updatedValue = Integer.parseInt(scanner.nextLine());

        System.out.print("note despre progres: ");
        String updateNotes = scanner.nextLine();

        boolean updated = pointService.updateMilestoneProgress(
                milestoneId,
                updatedValue,
                updateNotes
        );

        System.out.println();

        if (updated) {
            System.out.println("progres actualizat cu succes.");
        } else {
            System.out.println("milestone inexistent.");
        }
    }

    // creeaza o sesiune noua de studiu
    private static void createStudySession(PointService pointService) {
        System.out.println();
        System.out.println("creeaza study session");
        System.out.println("mai intai vezi ce learning point-uri exista:");

        showAllLearningPoints(pointService);

        System.out.println();
        System.out.print("introdu id-ul learning point-ului ales: ");
        int learningPointId = Integer.parseInt(scanner.nextLine());

        System.out.print("introdu data sesiunii (format yyyy-mm-dd): ");
        LocalDate sessionDate = LocalDate.parse(scanner.nextLine());

        System.out.print("cate minute planifici pentru aceasta sesiune?: ");
        int plannedMinutes = Integer.parseInt(scanner.nextLine());

        System.out.print("note pentru sesiune: ");
        String sessionNotes = scanner.nextLine();

        StudySession studySession = pointService.createStudySession(
                learningPointId,
                sessionDate,
                plannedMinutes,
                sessionNotes
        );

        System.out.println();

        if (studySession == null) {
            System.out.println("nu s-a putut crea sesiunea. id invalid.");
        } else {
            System.out.println("study session creata:");
            System.out.println(studySession);
        }
    }

    // afiseaza toate sesiunile
    private static void showAllStudySessions(PointService pointService) {
        List<StudySession> studySessions = pointService.getAllStudySessions();

        System.out.println();
        System.out.println("toate study session-urile:");

        if (studySessions.isEmpty()) {
            System.out.println("nu exista study session-uri.");
            return;
        }

        for (StudySession currentSession : studySessions) {
            System.out.println(currentSession);
        }
    }

    // marcheaza sesiunea ca finalizata
    private static void markStudySessionCompleted(PointService pointService) {
        System.out.println();
        System.out.println("marcheaza o sesiune ca finalizata");

        System.out.print("introdu id-ul sesiunii: ");
        int sessionId = Integer.parseInt(scanner.nextLine());

        boolean updated = pointService.markStudySessionCompleted(sessionId);

        System.out.println();

        if (updated) {
            System.out.println("sesiunea a fost marcata ca finalizata.");
        } else {
            System.out.println("sesiune inexistenta.");
        }
    }

    // afiseaza activitatile sortate automat
    private static void showSortedLearningPoints(PointService pointService) {
        System.out.println();
        System.out.println("learning point-uri sortate:");

        if (pointService.getSortedLearningPoints().isEmpty()) {
            System.out.println("nu exista activitati.");
            return;
        }

        for (LearningPoint currentPoint : pointService.getSortedLearningPoints()) {
            System.out.println(currentPoint);
        }
    }

    // afiseaza istoricul de progres
    private static void showProgressHistory(PointService pointService) {
        List<ProgressRecord> progressHistory = pointService.getProgressHistory();

        System.out.println();
        System.out.println("istoricul de progres:");

        if (progressHistory.isEmpty()) {
            System.out.println("nu exista inca inregistrari de progres.");
            return;
        }

        for (ProgressRecord currentRecord : progressHistory) {
            System.out.println(currentRecord);
        }
    }

    // citeste nivelul de dificultate de la tastatura
    private static DifficultyLevel readDifficultyLevel() {
        System.out.println();
        System.out.println("alege nivelul de dificultate:");
        System.out.println("1 - easy");
        System.out.println("2 - medium");
        System.out.println("3 - hard");
        System.out.print("introdu numarul corespunzator: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return DifficultyLevel.EASY;
            case "2":
                return DifficultyLevel.MEDIUM;
            case "3":
                return DifficultyLevel.HARD;
            default:
                System.out.println("optiune invalida. se foloseste medium.");
                return DifficultyLevel.MEDIUM;
        }
    }

    // citeste prioritatea de la tastatura
    private static PriorityLevel readPriorityLevel() {
        System.out.println();
        System.out.println("alege nivelul de prioritate:");
        System.out.println("1 - low");
        System.out.println("2 - medium");
        System.out.println("3 - high");
        System.out.print("introdu numarul corespunzator: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return PriorityLevel.LOW;
            case "2":
                return PriorityLevel.MEDIUM;
            case "3":
                return PriorityLevel.HIGH;
            default:
                System.out.println("optiune invalida. se foloseste medium.");
                return PriorityLevel.MEDIUM;
        }
    }

    // citeste nivelul de energie de la tastatura
    private static EnergyLevel readEnergyLevel() {
        System.out.println();
        System.out.println("alege nivelul de energie actual:");
        System.out.println("1 - low");
        System.out.println("2 - medium");
        System.out.println("3 - high");
        System.out.print("introdu numarul corespunzator: ");
        String chosenOption = scanner.nextLine();

        switch (chosenOption) {
            case "1":
                return EnergyLevel.LOW;
            case "2":
                return EnergyLevel.MEDIUM;
            case "3":
                return EnergyLevel.HIGH;
            default:
                System.out.println("optiune invalida. se foloseste medium.");
                return EnergyLevel.MEDIUM;
        }
    }
}