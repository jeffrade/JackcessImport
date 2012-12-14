import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
public abstract class ImportProcessorImpl<E> implements ImportProcessor<E> {
	
	protected List<E> list = new ArrayList<E>();

	@Override
	public void createObjects(Table table, List<String> columnNames) throws IOException, Exception{
		Map<String, Object> row = null;
	    int rowCount = 0;
	    while ((rowCount++ < Long.MAX_VALUE) && (row = table.getNextRow()) != null) {
	    	E e = this.createObject();
	    	int colIndex = 0;
	    	
	    	for(Iterator<Object> iter = row.values().iterator(); iter.hasNext(); iter.hasNext()) {
		        Object obj = iter.next();
				String val = obj == null ? "" : obj.toString();
				
				try{
					this.mapColumn(columnNames.get(colIndex), val, e);
				} catch (Exception ex){
					ex.printStackTrace();
				}
				
	    		colIndex++;
	    	}
	    	
	    	list.add(e);
	    }
	}
	
	public List<E> getList(){
		return list;
	}

	@Override
	public abstract E createObject();
	
	@Override
	public abstract void mapColumn(String column, String value, Object o) throws Exception;

}
