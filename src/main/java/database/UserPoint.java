package database;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table (name="POINTS")
public class UserPoint implements Serializable{

    @Id
    @SequenceGenerator(name = "idGeneratorSeq", sequenceName = "sq_ai_table")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "idGeneratorSeq")
    @Column(name = "ID")
    private int id;
    @Column(name = "x")
    private int x;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "y")
    private double y;
    @Column(name = "r")
    private double r;
    @Column(name = "dte")
    private Date date;
    @Column(name = "workTime")
    private double workTime;
    @Column(name = "answer")
    private String answer;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(double workTime) {
        this.workTime = workTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }



}
