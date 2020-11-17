import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.Date;

@ManagedBean
@SessionScoped
public class Point implements Serializable {
    private int x;
    private double y;
    private double r = 2f;
    private Date date;
    private double workTime;
    private String answer;
    private String err = "NO";

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

    public void valueChangeY(ValueChangeEvent e) {
        this.y = Double.parseDouble(e.getNewValue().toString());
        System.out.println(y);
    }


}
