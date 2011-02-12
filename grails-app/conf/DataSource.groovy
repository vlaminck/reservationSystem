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
      if (System.getProperty('db.inmemory')) {
        dbCreate = "create-drop" // one of 'create', 'create-drop','update'
        url = "jdbc:hsqldb:mem:devDB"
      } else {
        dbCreate = "create-drop"
        url = "jdbc:mysql://localhost/res_system"
        driverClassName = "com.mysql.jdbc.Driver"
        username = "res_system"
        password = "r3s3rv4t10n"
//        dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
      }
    }
  }
  test {
    dataSource {
      dbCreate = "update"
      url = "jdbc:hsqldb:mem:testDb"
    }
  }
  production {
    dataSource {
      dbCreate = "update"
      url = "jdbc:hsqldb:file:prodDb;shutdown=true"
    }
  }
}
