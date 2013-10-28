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
public class ImportRecordProcessor<E> extends ImportProcessorImpl<E> {

	@SuppressWarnings("unchecked")
	@Override
	public E createObject() {
		return (E) new Record();
	}

	@Override
	public void mapColumn(String column, String value, Object o) throws Exception {
		Record record = (Record) o;
		
		if(column.equalsIgnoreCase("ID")){
			record.setId(Long.valueOf(value));
		} else if(column.equalsIgnoreCase("FIRST_NAME")){
			record.setFirstName(value);
		} else if (column.equalsIgnoreCase("LAST_NAME")) {
			record.setLastName(value);
		} else if (column.equalsIgnoreCase("ACTIVE")) {
			record.setActive(Boolean.valueOf(value));
		}
	}

}
