package com.nisha.googleflights;

/**
 * Created by Nisha on 17-08-2017.
 */

public class Flights {


    String fuser;
    String fLevg;
    String fGoing;
    String fDdate;
    String fRdate;
    String fPassenger;
    String fPassengercls;



    public Flights() {

    }

    public Flights(String fLevg, String fGoing,String fDdate,String fRdate,String fPassenger,String fPassengercls,String fuser){

        this.fuser=fuser;
        this.fLevg=fLevg;
        this.fGoing=fGoing;
        this.fDdate=fDdate;
        this.fRdate=fRdate;
        this.fPassenger=fPassenger;
        this.fPassengercls=fPassengercls;
    }

    public String getfLevg() {
        return fLevg;
    }

    public String getfGoing() {
        return fGoing;
    }

    public String getfDdate() {
        return fDdate;
    }

    public String getfRdate() {
        return fRdate;
    }


    public String getfPassenger() {
        return fPassenger;
    }

    public String getfPassengercls() {
        return fPassengercls;
    }

    public String getFuser() {
        return fuser;
    }
}
