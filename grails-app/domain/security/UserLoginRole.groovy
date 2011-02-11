package security

import org.apache.commons.lang.builder.HashCodeBuilder

class UserLoginRole implements Serializable {

	UserLogin userLogin
	Role role

	boolean equals(other) {
		if (!(other instanceof UserLoginRole)) {
			return false
		}

		other.userLogin?.id == userLogin?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (userLogin) builder.append(userLogin.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static UserLoginRole get(long userLoginId, long roleId) {
		find 'from UserLoginRole where userLogin.id=:userLoginId and role.id=:roleId',
			[userLoginId: userLoginId, roleId: roleId]
	}

	static UserLoginRole create(UserLogin userLogin, Role role, boolean flush = false) {
		new UserLoginRole(userLogin: userLogin, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(UserLogin userLogin, Role role, boolean flush = false) {
		UserLoginRole instance = UserLoginRole.findByUserLoginAndRole(userLogin, role)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(UserLogin userLogin) {
		executeUpdate 'DELETE FROM UserLoginRole WHERE userLogin=:userLogin', [userLogin: userLogin]
	}

	static void removeAll(Role role) {
		executeUpdate 'DELETE FROM UserLoginRole WHERE role=:role', [role: role]
	}

	static mapping = {
		id composite: ['role', 'userLogin']
		version false
	}
}
