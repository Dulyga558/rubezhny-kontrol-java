import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// 1. ENUM: Оқу статустары
enum EnrollmentStatus {
    ACTIVE,
    COMPLETED,
    DROPPED
}

// 2. INTERFACE: Іздеуге арналған ортақ контракт
interface Searchable<T> {
    T findByIdOrCode(String key);
}

// 3. STUDENT КЛАССЫ
class Student {
    private final String id;
    private String name;
    private double gpa;

    public Student(String id, String name, double gpa) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Студент ID-і бос болмауы керек!");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Студент аты бос болмауы керек!");
        }
        if (!Double.isFinite(gpa) || gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA 0.0 мен 4.0 аралығында болуы тиіс!");
        }
        this.id = id.strip();
        this.name = name.strip();
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', gpa=" + gpa + "}";
    }
}

// 4. COURSE КЛАССЫ
class Course {
    private final String code;
    private final String title;
    private final int credits;

    public Course(String code, String title, int credits) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Курс коды бос болмауы керек!");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Курс атауы бос болмауы керек!");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Кредит саны оң сан болуы тиіс!");
        }
        this.code = code.strip().toUpperCase();
        this.title = title.strip();
        this.credits = credits;
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return "Course{code='" + code + "', title='" + title + "', credits=" + credits + "}";
    }
}

// 5. ENROLLMENT КЛАССЫ
class Enrollment {
    private final Student student;
    private final Course course;
    private EnrollmentStatus status;
    private Double grade;

    public Enrollment(Student student, Course course) {
        this.student = Objects.requireNonNull(student, "Student required");
        this.course = Objects.requireNonNull(course, "Course required");
        this.status = EnrollmentStatus.ACTIVE;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public EnrollmentStatus getStatus() { return status; }
    public Double getGrade() { return grade; }

    public void assignGrade(double grade) {
        if (this.status != EnrollmentStatus.ACTIVE) {
            throw new IllegalStateException("Тек белсенді оқуға баға қоюға болады!");
        }
        if (!Double.isFinite(grade) || grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException("Баға 0 мен 100 аралығында болуы тиіс!");
        }
        this.grade = grade;
        this.status = EnrollmentStatus.COMPLETED;
    }

    public void drop() {
        if (this.status != EnrollmentStatus.ACTIVE) {
            throw new IllegalStateException("Тек белсенді курс сабағынан шығуға болады!");
        }
        this.status = EnrollmentStatus.DROPPED;
    }

    @Override
    public String toString() {
        return "Enrollment{student=" + student.getName() + 
               ", course=" + course.getCode() + 
               ", status=" + status + 
               ", grade=" + grade + "}";
    }
}

// 6. СЕРВИС КЛАССЫ
class StudentManagementSystem implements Searchable<Student> {
    private final List<Student> students = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(Objects.requireNonNull(student));
    }

    public void addCourse(Course course) {
        courses.add(Objects.requireNonNull(course));
    }

    public Enrollment enroll(String studentId, String courseCode) {
        Student student = findByIdOrCode(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Студент табылмады: " + studentId);
        }

        Course course = findCourseByCode(courseCode);
        if (course == null) {
            throw new IllegalArgumentException("Курс табылмады: " + courseCode);
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollments.add(enrollment);
        return enrollment;
    }

    @Override
    public Student findByIdOrCode(String studentId) {
        for (Student s : students) {
            if (s.getId().equalsIgnoreCase(studentId)) {
                return s;
            }
        }
        return null;
    }

    public Course findCourseByCode(String courseCode) {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(courseCode)) {
                return c;
            }
        }
        return null;
    }

    public void printSummary() {
        System.out.println("\n=== ЖҮЙЕДЕГІ ТІРКЕЛУЛЕР БАРЛЫҒЫ ===");
        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }
}

// 7. МЕЙН КЛАССЫ
public class Main {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();

        Student s1 = new Student("S001", "Амина", 3.8);
        Student s2 = new Student("S002", "Диас", 3.2);
        system.addStudent(s1);
        system.addStudent(s2);

        Course c1 = new Course("CS202", "Programming 2", 5);
        Course c2 = new Course("MATH101", "Calculus", 4);
        system.addCourse(c1);
        system.addCourse(c2);

        Enrollment e1 = system.enroll("S001", "CS202");
        Enrollment e2 = system.enroll("S002", "MATH101");

        e1.assignGrade(95.0);
        e2.drop();

        system.printSummary();

        System.out.println("\n=== ІЗДЕУ ТЕКСЕРУІ ===");
        Student found = system.findByIdOrCode("S001");
        System.out.println("Табылған студент: " + found);

        try {
            e1.assignGrade(90.0);
        } catch (IllegalStateException e) {
            System.out.println("\nВалидация ұсталды: " + e.getMessage());
        }
    }
}