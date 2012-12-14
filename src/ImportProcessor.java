import java.io.IOException;
import java.util.List;

import com.healthmarketscience.jackcess.Table;

public interface ImportProcessor<E> {
	
	public void createObjects(Table table, List<String> columnNames) throws IOException, Exception;

	public E createObject();
	
	public List<E> getList();
	
	public void mapColumn(String column, String value, Object o) throws Exception;

}
