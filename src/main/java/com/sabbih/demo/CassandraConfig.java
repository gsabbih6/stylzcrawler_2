//package com.sabbih.demo;
////
////import com.datastax.oss.driver.api.core.CqlSession;
////import com.sabbih.demo.CategoryRepository;
////import com.sabbih.demo.ColourRepository;
////import com.sabbih.demo.Processor;
////import com.sabbih.demo.ProductRepository;
////import com.sabbih.demo.StoreRepository;
////import org.apache.commons.logging.Log;
////import org.apache.commons.logging.LogFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.context.annotation.PropertySource;
////import org.springframework.core.env.Environment;
////import org.springframework.data.cassandra.config.*;
////import org.springframework.data.cassandra.core.convert.CassandraConverter;
////import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
////import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
////import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
////import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
////
////import java.util.List;
////
////@Configuration
////@EnableCassandraRepositories(basePackageClasses =
////        {ProductRepository.class, StoreRepository.class, CategoryRepository.class, ColourRepository.class})
////@PropertySource(value = {"classpath:cassandra.properties"})
////public class CassandraConfig extends AbstractCassandraConfiguration {
////    private static final Log LOGGER = LogFactory.getLog(CassandraConfig.class);
////
////    @Autowired
////    Environment env;
////
////    @Override
////    public SchemaAction getSchemaAction() {
////
////        return SchemaAction.CREATE_IF_NOT_EXISTS;
////    }
////
////    @Override
////    protected String getKeyspaceName() {
////        return env.getProperty("cassandra.keyspace");
////    }
////
//////    @Override
//////    protected int getPort() {
//////        return Integer.parseInt(env.getProperty("cassandra.port"));
//////    }
//////
//////    @Override
//////    protected String getContactPoints() {
//////        return env.getProperty("cassandra.contactpoints");
//////    }
////////    @Override
////////    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
////////        final CreateKeyspaceSpecification specification =
////////                CreateKeyspaceSpecification.createKeyspace(env.getProperty("cassandra.keyspace"))
////////                        .ifNotExists()
////////                        .with(KeyspaceOption.DURABLE_WRITES, true)
////////                        .withSimpleReplication();
////////        return List.of(specification);
////////    }
////
//////    @Override
//////    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//////        return List.of(DropKeyspaceSpecification.dropKeyspace(env.getProperty("cassandra.keyspace")));
//////    }
//////    @Override
//////    public String[] getEntityBasePackages() {
//////        return new String[] { "com.sabbih.demo.Category","com.sabbih.demo.Colour",
//////                "com.sabbih.demo.Product","com.sabbih.demo.Store" };
//////    }
////
////
////    @Override
////    public CqlSessionFactoryBean cassandraSession() {
////        CqlSessionFactoryBean cassandraSession = super.cassandraSession();//super session should be called only once
////        cassandraSession.setUsername(env.getProperty("cassandra.user"));
////        cassandraSession.setPassword(env.getProperty("cassandra.pass"));
////        cassandraSession.setContactPoints(env.getProperty("cassandra.contactpoints"));
////        cassandraSession.setPort(Integer.parseInt(env.getProperty("cassandra.port")));
////        return cassandraSession;
////    }
//////    getp
////}
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
//import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class CassandraConfig extends AbstractCassandraConfiguration {
//
//    public static final String KEYSPACE = "mystylekeyspace";
//
//    @Override
//    public SchemaAction getSchemaAction() {
//        return SchemaAction.CREATE_IF_NOT_EXISTS;
//    }
//
//    @Override
//    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
//        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);
//
//        return Arrays.asList(specification);
//    }
//
//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//    }
//
//    @Override
//    protected String getKeyspaceName() {
//        return KEYSPACE;
//    }
//
//    @Override
//    public String[] getEntityBasePackages() {
//        return new String[]{"com.sabbih.demo"};
//    }
//}