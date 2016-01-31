package ch.codebulb.crudletdemo.server.config;

import java.sql.SQLException;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.tools.schemaframework.IndexDefinition;

/**
 * @author Ivan Rodriguez Murillo
 */
// from https://gist.github.com/iromu/6864061
public class CamelCaseSessionCustomizer implements SessionCustomizer {
  @Override
	public void customize(Session session) throws SQLException {
		for (ClassDescriptor descriptor : session.getDescriptors().values()) {
			// Only change the table name for non-embedable entities with no
			// @Table already
			if (!descriptor.getTables().isEmpty() && descriptor.getAlias().equalsIgnoreCase(descriptor.getTableName())) {
				String tableName = addUnderscores(descriptor.getTableName());
				descriptor.setTableName(tableName);
				for (IndexDefinition index : descriptor.getTables().get(0).getIndexes()) {
					index.setTargetTable(tableName);
				}
			}
			for (DatabaseMapping mapping : descriptor.getMappings()) {
				// Only change the column name for non-embedable entities with
				// no @Column already
				if (mapping.getField() != null && !mapping.getAttributeName().isEmpty()
						&& mapping.getField().getName().equalsIgnoreCase(mapping.getAttributeName())) {
					mapping.getField().setName(addUnderscores(mapping.getAttributeName()));
				}
			}
		}
	}

	private static String addUnderscores(String name) {
		StringBuffer buf = new StringBuffer(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toUpperCase();
	}

}