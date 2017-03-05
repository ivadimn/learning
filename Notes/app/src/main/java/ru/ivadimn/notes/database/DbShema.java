package ru.ivadimn.notes.database;

/**
 * Created by vadim on 05.03.2017.
 */

public interface DbShema {
    public String getTableName();
    public int getTypeColumn(int index);
    public String[] getColumns();
    public int getColumnsCount();
    public String getKeyColumn();
    public String getWhere();
}
