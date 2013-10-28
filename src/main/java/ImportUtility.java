import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.Table;

/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * GNU General Public License, see <http://www.gnu.org/licenses/>
 */
public class ImportUtility {
	
	public static String startImport(File file){
		String returnError = null;
		String processError = null;
		
		try{
			processError = processFile(file);
		} catch (Exception e){
			e.printStackTrace();
		}

		if(processError != null && !processError.isEmpty()){
			returnError = returnError == null ? "" : returnError;
			returnError += processError;
		}
		
		return returnError;
	}
	
	public static String processFile(File file) throws IOException, Exception {
		String errorMessage = "";
		Database db = Database.open(file);
		
		ImportProcessor<Record> recordsProcessor = new ImportRecordProcessor<Record>();
		errorMessage += processTable(db, "RECORD_TABLE", recordsProcessor);
		List<Record> records = recordsProcessor.getList();
		for(Record record : records){
			System.out.println(record.toString());
		}

		db.flush();
		db.close();
		db = null;
		
		return errorMessage;
	}

	private static String processTable(Database db, String tableName, ImportProcessor<?> processor) throws IOException, Exception{
		Table tbl = db.getTable(tableName);
		
		if(tbl != null){
			tbl.reset();
			processor.createObjects(tbl, getColumnNames(tbl));
			return "";
		} else {
			return "`" + tableName + "` TABLE NOT FOUND IN YOUR MPP DATABASE! |";
		}
	}

	private static List<String> getColumnNames(Table table) {
		List<String> columnNames = new ArrayList<String>();
		
		for(Iterator<Column> iter = table.getColumns().iterator(); iter.hasNext(); iter.hasNext()) {
			Column col = iter.next();
			columnNames.add(col.getName());
	    }
		
		return columnNames;
	}
	
	public static String trimToLength(final String str, final int len){
		return str.length() > len ? str.substring(0, len - 1) : str;
	}

}
