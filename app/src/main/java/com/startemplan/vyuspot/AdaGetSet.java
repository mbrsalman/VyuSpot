package com.startemplan.vyuspot;

/**
 * Created by User on 19-11-2015.
 */
public class AdaGetSet  {

    String _NAME;
    String _LOCATION;
    String _NUMBER;
    String _LANDMARK;
    String _BRAND;
    String _NEED;


    public AdaGetSet(){}


    public AdaGetSet(String name,String mail,String num,String service,String brand,String detail){

        this._NAME = name;
        this._LOCATION = mail;
        this._NUMBER = num;
        this._LANDMARK=service;
        this._BRAND=brand;
        this._NEED = detail;
    }


    public String get_NAME() {
        return _NAME;
    }

    public String get_LOCATION() {
        return _LOCATION;
    }

    public String get_NUMBER() {
        return _NUMBER;
    }

    public String get_LANDMARK() {
        return _LANDMARK;
    }

    public String get_BRAND() {
        return _BRAND;
    }

    public String get_NEED() {
        return _NEED;
    }



    public void set_NAME(String _NAME) {
        this._NAME = _NAME;
    }

    public void set_LOCATION(String _LOCATION) {
        this._LOCATION = _LOCATION;
    }

    public void set_NUMBER(String _NUMBER) {
        this._NUMBER = _NUMBER;
    }

    public void set_LANDMARK(String _LANDMARK) {
        this._LANDMARK = _LANDMARK;
    }

    public void set_BRAND(String _BRAND) {
        this._BRAND = _BRAND;
    }

    public void set_NEED(String _NEED) {
        this._NEED = _NEED;
    }
}

