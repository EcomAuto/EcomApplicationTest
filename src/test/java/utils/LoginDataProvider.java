package utils;

import java.util.List;

import org.testng.annotations.DataProvider;

import models.InvalidLoginModel;

public class LoginDataProvider {

	@DataProvider(name = "loginAccountData")
    public Object[][] getData() {
        return new Object[][]{
        	{"Login With Valid Details For Person: John","John", "Doe", DataGenerator.generateRandomEmail("John", "Doe"), "Pass@12345"},
            {"Login With Valid Details For Person: Jane","Jane", "Smith", DataGenerator.generateRandomEmail("Jane", "Smith"), "Pass@12345"},
            {"Login With Valid Details For Person: Alice","Alice", "Walker", DataGenerator.generateRandomEmail("Alice", "Walker"), "Pass@12345"}
        };
    }
	
	@DataProvider(name = "verifyLoginAccountData")
    public Object[][] getDataVerify() {
        return new Object[][]{
        	{"Login With Valid Details For Person: Mishra", "mishra654@gmail.com", "Chiku@12345"},
            {"Login With Valid Details For Person: Deepansh", "deepu12@gmail.com", "Deep@12345"},
            {"Login With Valid Details For Person: Anshul", "ans123@gmail.com", "Anshul@12345"}
        };
    }
	
	@DataProvider(name = "excelInvalidLoginData")
    public Object[][] getDataFromExcel() {
        String path = "src/test/resources/testdata/LoginNegativeData.xlsx";
        List<InvalidLoginModel> list = ExcelReaderUtil.readInvalidLoginData(path);
        return list.stream().map(data -> new Object[]{data}).toArray(Object[][]::new);
    }
    
}
