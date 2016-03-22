package nl.rug.junit;

/**
 * Marker for test cases that do <em>not</em> need a configured Blackboard
 * environment and database to run.
 *
 * Note that the following directories should be in the classpath during
 * the test:
 * <ul>
 *   <li>test/resources</li>
 * </ul>
 */
public interface UnitTest {}
