package nl.rug.junit;

/**
 * Marker for test cases that <em>do</em> need a configured Blackboard
 * environment and database to run.
 *
 * Note that the following directories should be in the classpath during
 * the test:
 * <ul>
 *   <li>test/resources</li>
 *   <li>WEB-INF/classes</li>
 * </ul>
 * You must provide a system property <code>bbservices_config</code> with the
 * full path to a blackboard configuration file. For example
 * <code>../vagrant/2014.10/blackboard/config/unittest-service-config.properties</code>
 */
public interface IntegrationTest {}