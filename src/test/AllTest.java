package test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectPackages({
	"test.controllers",
	"test.models",
	"test.views"	
})

public class AllTest {
	
}
