'use strict';

var translations = {
    'entity.id': 'Id',
    
    'customer.name': 'Name',
    'customer.address': 'Address',
    'customer.city': 'City',
    'customer.employmentStatus': 'Employment status',
    
    'payment.amount': 'Amount',
    'payment.date': 'Date',
    
	
	// Java Server messages ====================
    'error.org.hibernate.validator.constraints.NotEmpty.message': 'may not be empty',
    'error.javax.validation.constraints.Min.message': 'must be greater than or equal to {{value}}',
    'error.javax.validation.constraints.Pattern.message': 'must match "{{regexp}}"',
    
	// Derby DB
    'error.java.sql.SQLIntegrityConstraintViolationException': 'Cannot delete an object which is still referenced to by other objects.',
	// MySQL DB
	'error.com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException': 'Cannot delete an object which is still referenced to by other objects.',
	
	
	// Node.js Server messages ====================
	'error.required': 'may not be empty',
	'error.naturalNonZero': 'must be greater than or equal to 1',
    'error.pattern': 'must match the expected pattern',
	
	'error.ER_ROW_IS_REFERENCED_2': 'Cannot delete an object which is still referenced to by other objects.',
};
