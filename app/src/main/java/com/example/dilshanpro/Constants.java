package com.example.dilshanpro;

public class Constants {

    public static final String DB_NAME = "PLAYER_RECORDS_DB";

    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "PLAYER_RECORDS_TABLE";

    public static final String P_ID = "ID";
    public static final String P_IMAGE = "IMAGE";
    public static final String P_NAME = "NAME";
    public static final String P_AGE = "AGE";
    public static final String P_REGNO = "REGNO";
    public static final String P_SCORE = "SCORE";
    public static final String P_OVERS = "OVERS";
    public static final String P_CENT = "CENT";
    public static final String P_HCENT = "HCENT";
    public static final String P_WICKETS = "WICKETS";
    public static final String P_POSITION = "POSITION";

    public static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + P_IMAGE + " TEXT,"
            + P_NAME + " TEXT,"
            + P_AGE + " TEXT,"
            + P_REGNO + " TEXT,"
            + P_SCORE + " TEXT,"
            + P_OVERS + " TEXT,"
            + P_CENT + " TEXT,"
            + P_HCENT + " TEXT,"
            + P_WICKETS + " TEXT,"
            + P_POSITION + " TEXT"
    +")";
}

