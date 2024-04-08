package ge.tbcitacademy.tests;

import ge.tbcitacademy.dataprovider.CustomDataProvider;
import org.testng.annotations.Factory;

public class SwoopTestsFactory {
    @Factory(dataProvider = "factoryDataProvider", dataProviderClass = CustomDataProvider.class)
    public Object[] factoryExecutor(String sectionName){
        return new Object[] {
                new ParametrizedSwoopTests2(sectionName)
        };
    }
}
