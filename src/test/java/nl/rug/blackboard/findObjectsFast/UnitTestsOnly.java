package nl.rug.blackboard.findObjectsFast;

import nl.rug.blackboard.findObjectsFast.search.CourseResultConverterTest;
import nl.rug.blackboard.findObjectsFast.search.UserResultConverterTest;
import nl.rug.junit.UnitTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Categories.IncludeCategory({UnitTest.class})
@Suite.SuiteClasses({
		CourseResultConverterTest.class,
		UserResultConverterTest.class
})
public class UnitTestsOnly {
}
