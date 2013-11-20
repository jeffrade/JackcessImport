import java.io.File;

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
public class ImportLauncher {

	public static void main(String[] args){
		String errorMessage = null;
		File mdbFile = new File("C://file.mdb");
		
		try{
			errorMessage = ImportUtility.startImport(mdbFile);

			if(errorMessage == null){
				errorMessage = "Import is Complete!";
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			if(mdbFile != null){
				mdbFile.delete();
			}
			
			System.out.println(errorMessage);
		}

		return;
	}
	
}
