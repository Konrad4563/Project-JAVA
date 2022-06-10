package messages;

import javafx.scene.control.CheckBox;
import java.io.Serializable;

/**
 *
 * @author Marcin Bonar
 *
 */

public class Pracownik implements Serializable {
    String name;
    String surname;
    int age;
    String gender;
    String pesel;
    String placeOfResidence;
    float salary;
    String position;

    public Pracownik(String name, String surname, int age, String gender, String pesel, String placeOfResidence, float salary,String position){
        this.name = name;
        this.surname=surname;
        this.age=age;
        this.gender=gender;
        this.pesel=pesel;
        this.placeOfResidence=placeOfResidence;
        this.salary=salary;
        this.position=position;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public String getGender() {
        return gender;
    }


    public String getPesel() {
        return pesel;
    }


    public String getPlaceOfResidence() {
        return placeOfResidence;
    }


    public float getSalary() {
        return salary;
    }


    public String getSurname() {
        return surname;
    }

    public String getPosition() {return position;}
}
