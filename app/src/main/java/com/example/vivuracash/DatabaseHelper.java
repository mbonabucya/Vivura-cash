package com.example.vivuracash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String DB_NAME = "Vivuracash.db";

    private static final String table1="cashIn";
    private static final String table2="cashOut";
    private static final String table3="Report_cashIn";
    private static  final String table5="Report_cashOut";
    //private static final String table4="users";



    public DatabaseHelper(Context context) {
        super(context, "Vivuracash.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table users(First_Name TEXT,Last_Name TEXT, phone TEXT primary key, password TEXT)");
        sqLiteDatabase.execSQL("create Table table1(CashIn_id INTEGER primary key AUTOINCREMENT,amount INTEGER,reason TEXT,payment_mode TEXT,created_at TEXT,item TeXT,FOREIGN KEY (CashIn_id) REFERENCES users(phone))");
        sqLiteDatabase.execSQL("create Table table2(Cashout_id INTEGER primary key AUTOINCREMENT,Out_amount INTEGER,Out_reason TEXT,Out_payment_mode TEXT,created_at TEXT,activity TeXT,FOREIGN KEY (Cashout_id) REFERENCES users(phone))");
        sqLiteDatabase.execSQL("create Table table3(Report_id INTEGER primary key AUTOINCREMENT, Biz_name TEXT, Biz_contact TEXT,FOREIGN KEY (Report_id) REFERENCES users(phone))");
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");


        //create tables
        //String cashIn="Create Table " +table1+"(id INTEGER primary key,amount TEXT,reason TEXT,payment_mode TEXT)";
       // String cashOut="Create Table "+table2+"(id INTEGER primary key, amount TEXT, reason TEXT,payment_mode TEXT)";
        //String Report_cashIn="CREATE TABLE "+table3+"(id INTEGER primary key, First_Name foreignKEY)";
       // String Report_cashOut="CREATE TABLE"+table5+"()";
        //sqLiteDatabase.execSQL(cashIn);
        //sqLiteDatabase.execSQL(cashOut);
       // sqLiteDatabase.execSQL(Report_cashIn);
       // sqLiteDatabase.execSQL(cashIn);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop Table  if exists users");
        sqLiteDatabase.execSQL("drop table if exists table1");
        sqLiteDatabase.execSQL("drop table if exists table2");
        sqLiteDatabase.execSQL("drop table if exists table3");
        onCreate(sqLiteDatabase);
    }

    public Boolean InsertData(String phone, String password,String fname,String lname) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("phone", phone);
        values.put("password", password);
        values.put("First_Name",fname);
        values.put("Last_Name ",lname);

        long results = sqLiteDatabase.insert("users", null, values);
        if (results == -1) {
            return false;
        } else {
            return true;
        }


    }

    public Boolean checkUsername(String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where phone=?", new String[]{phone});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean getcurrentUser()
    {

        return true;
    }

    public Boolean checkUsernamePassword(String phone, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users where phone=? and password=?", new String[]{phone, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean updatepass(String phone, String password) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);

        long results = sqLiteDatabase.update("users",values,"phone=?",new String[]{phone} );
        if (results == -1) {
            return false;
        } else {
            return true;
        }
    }
//chash in  adding income in personal
    public Boolean add_income(int AMOUNT, String REASON, String  PAYMENT,String ITEM) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("amount", AMOUNT);
        values.put("reason", REASON);
        values.put("payment_mode",PAYMENT);
        values.put("created_at", getDateTime());
        values.put("item",ITEM);



        long results = sqLiteDatabase.insert(" table1 ", null, values);
        if (results == -1) {
            return false;
        } else {
            return true;
        }


    }

    ///add cash out
    public Boolean add_expenses(int AMOUNT, String REASON, String  PAYMENT,String activity) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Out_amount", AMOUNT);
        values.put("Out_reason", REASON);
        values.put("Out_payment_mode",PAYMENT);
        values.put("created_at",getDateTime());
        values.put("activity",activity);

        long results = sqLiteDatabase.insert("table2", null, values);
        if (results == -1) {
            return false;
        } else {
            return true;
        }



    }

    // we have created a new method for reading all the courses.
    public ArrayList<businessModel> readBusiness() {
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorBusinesses = db.rawQuery("SELECT * FROM " + table3, null);

        // on below line we are creating a new array list.
        ArrayList<businessModel> BusinessModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorBusinesses.moveToFirst()) {
            do {

                BusinessModalArrayList.add(new businessModel(cursorBusinesses.getInt(0), cursorBusinesses.getString(2),
                                cursorBusinesses.getString(3)));
                        ////
            } while (cursorBusinesses.moveToFirst());
        }
        cursorBusinesses.close();
        return BusinessModalArrayList;
    }


    public ArrayList<Income_Personal_Model> Show_personal_income() {
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor_Personal_income = db.rawQuery("SELECT * FROM table1 ORDER BY Cashin_id DESC where " , null);
        //Cursor cursor = db.rawQuery("select * from table1 where phone=?", new String[]{phone});
        // on below line we are creating a new array list.
        ArrayList<Income_Personal_Model> Income_ModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursor_Personal_income.moveToFirst()) {
            do {

                Income_ModalArrayList.add(new Income_Personal_Model(cursor_Personal_income.getInt(0),
                        cursor_Personal_income.getString(1)+" "+"RWF",
                        cursor_Personal_income.getString(2),cursor_Personal_income.getString(4),cursor_Personal_income.getString(5)));
                ////
            } while (cursor_Personal_income.moveToNext());
        }
        cursor_Personal_income.close();
        return Income_ModalArrayList;
    }


    //Get all expenses



    public ArrayList<personal_expenses_Model> Show_personal_Expenses() {
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor_Personal_expenses= db.rawQuery("SELECT * FROM table2" , null);

        // on below line we are creating a new array list.
        ArrayList<personal_expenses_Model> Expenses_ModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursor_Personal_expenses.moveToFirst()) {
            do {

                Expenses_ModalArrayList.add(new personal_expenses_Model(cursor_Personal_expenses.getInt(0),
                        cursor_Personal_expenses.getString(1)+" "+"RWF",
                        cursor_Personal_expenses.getString(2),cursor_Personal_expenses.getString(4),cursor_Personal_expenses.getString(5)));
                ////
            } while (cursor_Personal_expenses.moveToNext());
        }
        cursor_Personal_expenses.close();
        return Expenses_ModalArrayList;
    }

/*
        public Cursor alldata(){

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM table1" , null);
                return cursor;

    }

 */


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }



    // add Businesses


    public Boolean add_biz(String biz_name,String biz_contact) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(" Biz_name", biz_name);
        values.put("Biz_contact", biz_contact);

        long results = sqLiteDatabase.insert(" table3", null, values);
        if (results == -1) {
            return false;
        } else {
            return true;
        }


    }
    public int calculatingTotalIncome()
    {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sum(amount) from " + table1, null);
        if (cursor.moveToFirst()) result = cursor.getInt(0);
        cursor.close();
        db.close();
        return result;
        /*String query = "select sum(amount)from"+" "+table1;
        Cursor res = db.rawQuery(query,null);
        return res;*/
    }

public int TotalIncome() {
    int total= 0;
    SQLiteDatabase db = getReadableDatabase();

    Cursor cursor = db.rawQuery(
            "SELECT SUM(amount) FROM table1", null);
    if (cursor.moveToFirst()) {
        total= cursor.getInt(0);
    }

    return total;

}

public int TotalExpense(){
        int expenses=0;

        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SUM(Out_amount) from table2",null);
        if (cursor.moveToFirst()){
            expenses=cursor.getInt(0);
        }

        return expenses;
}


public int TotalBalance(){
        int balance=0;
       int expenses=  TotalExpense();
       int income= TotalIncome();

       balance =income-expenses;

       return balance;

}
// Get current user data
    public ArrayList<Income_Personal_Model> Show_Current_user_personal_income() {
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor_Personal_income = db.rawQuery("SELECT * FROM table1 ORDER BY CashIn_id DESC" , null);
        //Cursor cursor = db.rawQuery("select * from table1 where phone=?", new String[]{phone});
        // on below line we are creating a new array list.
        ArrayList<Income_Personal_Model> Income_ModalArrayList = new ArrayList<>();

        // moving our cursor to first position.

        if (cursor_Personal_income.moveToFirst()) {
            do {

                Income_ModalArrayList.add(new Income_Personal_Model(cursor_Personal_income.getInt(0),
                        cursor_Personal_income.getString(1)+" "+"RWF",
                        cursor_Personal_income.getString(2),cursor_Personal_income.getString(4),cursor_Personal_income.getString(5)));
                ////
            } while (cursor_Personal_income.moveToNext());
        }
        cursor_Personal_income.close();
        return Income_ModalArrayList;
    }

}