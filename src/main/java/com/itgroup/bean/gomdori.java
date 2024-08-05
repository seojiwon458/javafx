package com.itgroup.bean;

public class gomdori {
    private String id;
    private String name;
    private String ssn;
    private String address;
    private String gender;
    private String email;
    private String hiredate;

    //6) getter,setter,toString만들기


    public void setEmail(String email) {
        this.email = email;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSSN(String ssn) {
        this.ssn = ssn;
    }

    public void setaddres(String address) {
        this.address = address;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void sethiredate(String hiredate) {
        this.hiredate = hiredate;
    }



    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getHiredate() {
        return hiredate;
    }

    public String getssn() {

        return ssn;
    }

    public String getaddress() {
        return null;
    }




    @Override
    public String toString() {
        return "gomdori{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ssn=" + ssn +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", hiredate='" + hiredate + '\'' +
                '}';
    }


}
