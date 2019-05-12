package com.Stiw3054.groupProject;

public class ObjectTable {
    private String [] arrayRK=new String[100];
    private String [] arraySno=new String[100] ;
    private String [] arrayName=new String[100] ;
    private  String [] arrayRtg=new String[100] ;
    private String [] arrayState=new String[100];
    private  String [] arrayPts=new String[100] ;
    private String [] arrayCat =new String[100];
    private String word;
    private int count ;
    public ObjectTable() {

    }

    public String[] getArrayRK() {
        return arrayRK;
    }

    public String[] getArraySno() {
        return arraySno;
    }

    public String[] getArrayName() {
        return arrayName;
    }

    public String[] getArrayRtg() {
        return arrayRtg;
    }

    public String[] getArrayState() {
        return arrayState;
    }

    public String[] getArrayPts() {
        return arrayPts;
    }

    public String[] getArrayCat() {
        return arrayCat;
    }

    public void setArrayRK(String RK, int count) {
        arrayRK[count] = RK ;
    }

    public void setArraySno(String Sno,int count) {
        arraySno [count]= Sno ;
    }

    public void setArrayName(String Name,int count) {
        arrayName [count] = Name ;
    }

    public void setArrayRtg(String Rtg,int count) {
        arrayRtg [count] = Rtg ;
    }

    public void setArrayState(String State,int count) {

        arrayState [count] = State ;
    }

    public void setArrayPts(String Pts,int count) {

        arrayPts [count] = Pts ;
    }

    public void setArrayCat(String Cat,int count) {
        if (count==0)
            arrayCat [count] = "Category";
        else
        arrayCat [count] = Cat;
    }
}
