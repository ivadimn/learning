package ru.ivadimn.notes.database;

/**
 * Created by vadim on 05.03.2017.
 */

public interface Row {
    public Values getValues();
    public void setValues(Values values);
    public String[] getValuesString();
}
