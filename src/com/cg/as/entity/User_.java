package com.cg.as.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
		
	    public static volatile SingularAttribute<User, Integer> id;
	    public static volatile SingularAttribute<User, String> name;
	    public static volatile SingularAttribute<User, String> password;
	    public static volatile SingularAttribute<User, String> email;
	    public static volatile SingularAttribute<User, Long> mobile;
	    public static volatile SingularAttribute<User, String> role;
}
