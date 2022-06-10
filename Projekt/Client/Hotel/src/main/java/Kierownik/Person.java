package Kierownik;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;
import java.io.Serializable;

/**
 *
 * @author Marcin Bonar
 *
 */

public class Person implements Serializable {

    String firstName;
    String lastName;
    String age;
    String gender;
    String pesel;
    String place;
    String salary;
    String position;
    CheckBox select;

    public Person(String fName, String lName, String age, String gender, String pesel, String place, String salary,String position) {
        this.firstName = new String(fName);
        this.lastName = new String(lName);
        this.age = new String(age);
        this.gender = new String(gender);
        this.pesel = new String(pesel);
        this.place = new String(place);
        this.salary = new String(salary);
        this.position = new String(position);
        this.select = new CheckBox();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        this.firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String fName) {
        this.lastName = fName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender=gender;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel=pesel;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place=place;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary=salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position=position;
    }

    public CheckBox getSelect(){
        return select;
    }

    public void setSelect(CheckBox select){
        this.select = select;
    }

}

