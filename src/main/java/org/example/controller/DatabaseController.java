package org.example.controller;

import org.example.model.AllData;
import org.example.repository.DatabaseRepository;

import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseController {

    private final DatabaseRepository databaseRepository;

    public DatabaseController() {
        this.databaseRepository = new DatabaseRepository();
    }

    public void getAllData(String tableName) {
        AllData alldata = new AllData();
        System.out.print("Enter table name: ");
        alldata.setTableName(tableName);

        try {
            HashMap<String,Object> allData = databaseRepository.getAllData(alldata.getTableName());

            for (String data : allData.keySet()) {
                System.out.println(data + ": " + allData.get(data));
            }
            System.out.println("------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String tableName, int id, String columnName, String value) throws SQLException {
        AllData newData = new AllData();
        newData.setTableName(tableName);
        newData.setId(id);
        newData.setColumnName(columnName);
        newData.setValue(value);

        databaseRepository.updateRecord(newData);
    }

    public void createTable(String tableName, String columnDefinitionsInput) {

        AllData newTable = new AllData();

        newTable.setTableName(tableName);
        newTable.setQuery(columnDefinitionsInput);

        databaseRepository.createTable(newTable);
    }
}


