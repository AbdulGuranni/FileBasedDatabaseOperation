package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class FileUtils {
    public void writeTextFile(ResultSet resultSet) throws IOException, SQLException {
        // writing result set to a text file
        PrintWriter writer = new PrintWriter(new FileWriter("result_set.txt"));
        writeResultSetHeader(resultSet, writer);
        writeResultSetData(resultSet, writer);
        writer.close();

    }

    // method to write header to the text file
    private void writeResultSetHeader(ResultSet resultSet, PrintWriter writer) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            writer.print(columnName);
            if (i < columnCount) {
                writer.print(", ");
            } else {
                writer.println();
            }
        }
    }

    // method to write data to the text file
    private void writeResultSetData(ResultSet resultSet, PrintWriter writer) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                writer.print(resultSet.getString(i));
                if (i < columnCount) {
                    writer.print(", ");
                } else {
                    writer.println();
                }
            }
        }
    }
}
