// ==========================================
// ЧАСТЬ B. КЛАССЫ И ОБЪЕКТЫ (СИСТЕМА УЧЕБНЫХ КУРСОВ)
// ==========================================

class Student {
    // [ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ]: Подсчёт количества созданных студентов
    private static int studentCount = 0;

    /*
     * [ОТВЕТ НА ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ]:
     * Вопрос: Почему поле 'name' не должно быть static?
     *
     * Ответ: Поле 'static' является общим для всех объектов данного класса.
     * Если сделать 'name' статическим, то при создании каждого нового объекта
     * имя абсолютно всех студентов будет перезаписываться последним введенным именем.
     * Имя студента (name) — это индивидуальная характеристика конкретного экземпляра
     * (instance field), поэтому оно должно храниться отдельно в каждом объекте.
     */
    private String name;
    private String studentId;
    private double gpa;

    public Student(String name, String studentId, double gpa) {
        this.name = name;
        this.studentId = studentId;
        this.gpa = gpa;
        studentCount++;
    }

    public String getName() { return name; }
    public String getStudentId() { return studentId; }
    public double getGpa() { return gpa; }
    public static int getStudentCount() { return studentCount; }

    public void improveGpa(double delta) {
        if (delta > 0) {
            this.gpa += delta;
        }
    }

    public void printInfo() {
        System.out.println("Студент: " + name + " | ID: " + studentId + " | GPA: " + gpa);
    }
}

class Course {
    private static int courseCount = 0;
    private String title;
    private String code;
    private int credits;

    public Course(String title, String code, int credits) {
        this.title = title;
        this.code = code;
        this.credits = credits;
        courseCount++;
    }

    public String getTitle() { return title; }
    public String getCode() { return code; }
    public int getCredits() { return credits; }
    public static int getCourseCount() { return courseCount; }

    public void printInfo() {
        System.out.println("Курс: " + title + " (" + code + ") | Кредиты: " + credits);
    }
}

class Enrollment {
    private Student student;
    private Course course;
    private Double grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.grade = 0.0;
    }

    public void assignGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            this.grade = grade;
        } else {
            System.out.println("ОШИБКА: Оценка " + grade + " недопустима! Должна быть в диапазоне от 0 до 100.");
        }
    }

    public void printSummary() {
        System.out.println("Зачисление -> Студент: " + student.getName() +
                " | Курс: " + course.getTitle() +
                " | Оценка: " + grade);
    }
}

// ==========================================
// ОСНОВНОЙ КЛАСС ДЛЯ ЗАПУСКА (Practice2.java)
// ==========================================
public class Practice2 {
    public static void main(String[] args) {

        // ---------------------------------------------------------
        // ЧАСТЬ A. ЗАДАНИЕ 1. Анализ результатов группы
        // ---------------------------------------------------------
        System.out.println("=== ЧАСТЬ A. ЗАДАНИЕ 1: Анализ результатов группы ===");
        int[] scores = {78, 91, 65, 88, 73, 95, 54, 82, 69, 91};

        // ДҰРЫС: [0] индексі қосылды
        int min = scores[0];
        int max = scores[0];
        int sum = 0;
        int count80Plus = 0;
        int evenCount = 0;

        for (int i = 0; i < scores.length; i++) {
            System.out.println("Индекс " + i + ": " + scores[i]);
            if (scores[i] < min) min = scores[i];
            if (scores[i] > max) max = scores[i];
            sum += scores[i];
            if (scores[i] >= 80) count80Plus++;
            if (scores[i] % 2 == 0) evenCount++;
        }
        double avg = (double) sum / scores.length;

        System.out.println("Минимальный балл: " + min);
        System.out.println("Максимальный балл: " + max);
        System.out.println("Сумма: " + sum + " | Средний балл: " + avg);
        System.out.println("Студентов с баллом 80+: " + count80Plus);
        System.out.println("Количество чётных баллов: " + evenCount);
        System.out.println();

        // ---------------------------------------------------------
        // ЧАСТЬ A. ЗАДАНИЕ 2. Таблица оценок
        // ---------------------------------------------------------
        System.out.println("=== ЧАСТЬ A. ЗАДАНИЕ 2: Таблица оценок ===");
        int[][] grades = {
                {78, 91, 88},
                {65, 72, 70},
                {90, 94, 89},
                {81, 76, 85}
        };

        // ДҰРЫС: [0][0] индексі қосылды
        int maxGrade = grades[0][0];
        int maxRow = 0, maxCol = 0;
        double bestStudentAvg = -1;
        int bestStudentIndex = -1;

        for (int row = 0; row < grades.length; row++) {
            int rowSum = 0;
            System.out.print("Студент " + row + ": ");
            for (int col = 0; col < grades[row].length; col++) {
                System.out.print(grades[row][col] + "\t");
                rowSum += grades[row][col];

                if (grades[row][col] > maxGrade) {
                    maxGrade = grades[row][col];
                    maxRow = row;
                    maxCol = col;
                }
            }
            double studentAvg = (double) rowSum / grades[row].length;
            System.out.println("| Средний балл: " + studentAvg);

            if (studentAvg > bestStudentAvg) {
                bestStudentAvg = studentAvg;
                bestStudentIndex = row;
            }
        }

        System.out.print("Средний балл по предметам: ");
        // ДҰРЫС: grades[0].length арқылы бағандар саны (3) алынды
        int numCols = grades[0].length;
        for (int col = 0; col < numCols; col++) {
            int colSum = 0;
            for (int row = 0; row < grades.length; row++) {
                colSum += grades[row][col];
            }
            System.out.print(((double) colSum / grades.length) + "\t");
        }
        System.out.println();
        System.out.println("Максимальный элемент таблицы: " + maxGrade + " [позиция: row=" + maxRow + ", col=" + maxCol + "]");
        System.out.println("Студент с самым высоким средним баллом: индекс " + bestStudentIndex + " (балл: " + bestStudentAvg + ")");
        System.out.println();

        // ---------------------------------------------------------
        // ЧАСТЬ A. ЗАДАНИЕ 3. Обработка текста
        // ---------------------------------------------------------
        System.out.println("=== ЧАСТЬ A. ЗАДАНИЕ 3: Обработка текста ===");
        String text = " Java, Python, java, C++, Kotlin, JAVA, Python ";
        String trimmedText = text.trim();
        String[] words = trimmedText.split(",");

        int javaCount = 0;
        int longWordsCount = 0;
        StringBuilder filteredWords = new StringBuilder();

        for (String w : words) {
            String cleanWord = w.trim();
            System.out.println("Слово: '" + cleanWord + "' | Длина: " + cleanWord.length());

            if (cleanWord.equalsIgnoreCase("java")) {
                javaCount++;
            }
            if (cleanWord.length() > 4) {
                longWordsCount++;
            }
            if (cleanWord.length() >= 5) {
                if (filteredWords.length() > 0) filteredWords.append(", ");
                filteredWords.append(cleanWord);
            }
        }
        System.out.println("Количество вхождений 'Java' (без учёта регистра): " + javaCount);
        System.out.println("Количество слов длиной более 4 символов: " + longWordsCount);
        System.out.println("Новая строка со словами длиной 5+ символов: " + filteredWords.toString());
        System.out.println();

        // ---------------------------------------------------------
        // ЧАСТЬ A. ЗАДАНИЕ 4. Отчёт через StringBuilder
        // ---------------------------------------------------------
        System.out.println("=== ЧАСТЬ A. ЗАДАНИЕ 4: Отчёт через StringBuilder ===");
        StringBuilder report = new StringBuilder();
        report.append("ОТЧЁТ ПО ГРУППЕ\n");
        report.append("Количество: ").append(scores.length).append("\n");
        report.append("Средний балл: ").append(avg).append("\n");
        report.append("Максимум: ").append(max).append("\n");
        report.append("Минимум: ").append(min).append("\n");
        report.append("80+ студентов: ").append(count80Plus);

        String finalReport = report.toString();
        System.out.println(finalReport);
        System.out.println();

        // ---------------------------------------------------------
        // ЧАСТЬ B. ЭТАП 4: Проверка работы классов (Main)
        // ---------------------------------------------------------
        System.out.println("=== ЧАСТЬ B: Проверка классов и объектов ===");

        Student s1 = new Student("Amina", "S001", 3.6);
        Student s2 = new Student("Dias", "S002", 3.2);

        Course c1 = new Course("Java Programming", "CS101", 5);
        Course c2 = new Course("Database Systems", "CS102", 4);

        Enrollment e1 = new Enrollment(s1, c1);
        Enrollment e2 = new Enrollment(s1, c2);
        Enrollment e3 = new Enrollment(s2, c1);

        e1.assignGrade(95.0);
        e2.assignGrade(88.0);
        e3.assignGrade(120.0);

        s1.printInfo();
        s2.printInfo();
        c1.printInfo();
        c2.printInfo();

        e1.printSummary();
        e2.printSummary();
        e3.printSummary();

        System.out.println("Всего создано курсов (Course.getCourseCount): " + Course.getCourseCount());
        System.out.println("Всего создано студентов (Student.getStudentCount): " + Student.getStudentCount());
    }
}