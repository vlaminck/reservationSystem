dataSource {
  pooled = true
  driverClassName = "org.hsqldb.jdbcDriver"
  username = "sa"
  password = ""
}
hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = true
  cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {
  development {
    dataSource {
//      if (System.getProperty('db.inmemory')) {
        dbCreate = "create-drop" // one of 'create', 'create-drop','update'
        url = "jdbc:hsqldb:mem:devDB"
//      } else {
//        dbCreate = "create-drop"
//        url = "jdbc:mysql://localhost/res_system"
//        driverClassName = "com.mysql.jdbc.Driver"
//        username = "res_system"
//        password = "r3s3rv4t10n"
//        dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
//      }
    }
  }
  test {
    dataSource {
      dbCreate = "create-drop"
      url = "jdbc:hsqldb:mem:testDb"
    }
  }
  production {
    dataSource {
      pooled = true
      driverClassName = "com.mysql.jdbc.Driver"
      dbCreate = "update" // one of 'create', 'create-drop','update'
      username = 'root'
      password = 'mysql'
      url = 'jdbc:mysql://db.cypksy9undoh.us-east-1.rds.amazonaws.com:3306/reservationSystem'
      dialect = org.hibernate.dialect.MySQL5InnoDBDialect
      properties {
        validationQuery = "SELECT 1"
        testOnBorrow = true
        testOnReturn = true
        testWhileIdle = true
        timeBetweenEvictionRunsMillis = 1000 * 60 * 30
        numTestsPerEvictionRun = 3
        minEvictableIdleTimeMillis = 1000 * 60 * 30
      }
    }
  }
}
